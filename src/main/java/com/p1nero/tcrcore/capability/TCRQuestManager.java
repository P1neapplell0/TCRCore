package com.p1nero.tcrcore.capability;

import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.RefreshClientQuestsPacket;
import com.p1nero.tcrcore.utils.FTBTeamUtils;
import com.p1nero.tcrcore.utils.XaeroWaypointUtils;
import dev.ftb.mods.ftbteams.api.Team;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import xaero.common.minimap.waypoints.WaypointVisibilityType;
import xaero.hud.minimap.waypoint.WaypointColor;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class TCRQuestManager {
    public static final String PRE = "tcrcore.quest.";
    public static final Map<Integer, Quest> QUEST_MAP = new HashMap<>();
    public static int id = 0;
    public static Quest EMPTY;

    public static void init() {
        QUEST_MAP.clear();
        id = 0;
        EMPTY = create("empty");//不知道为嘛默认0改不了= =
        TCRQuests.init();
    }

    public static Quest getQuestById(int id) {
        return QUEST_MAP.getOrDefault(id, EMPTY);
    }

    public static Quest getQuestByKey(String key) {
        AtomicReference<Quest> toReturn = new AtomicReference<>();
        QUEST_MAP.values().forEach(quest -> {
            if(quest.getKey().equals(key)) {
                toReturn.set(quest);
            }
        });
        return toReturn.get();
    }

    public static Quest getCurrentQuest(Player player) {
        return getQuestById(PlayerDataManager.currentQuestId.getInt(player));
    }

    public static void setCurrentQuest(Player player, Quest quest) {
        PlayerDataManager.currentQuestId.put(player, quest.getId());
    }

    public static int getCurrentQuestId(Player player) {
        return PlayerDataManager.currentQuestId.getInt(player);
    }

    public static Quest create(String desc) {
        Quest quest = new Quest(id, PRE + desc);
        QUEST_MAP.put(id, quest);
        id++;
        return quest;
    }

    public static boolean hasQuest(Player player) {
        return PlayerDataManager.currentQuestId.get(player) != EMPTY.id;
    }

    public static boolean hasQuest(Player player, Quest quest) {
        return getQuests(player).contains(quest);
    }

    public static List<Quest> getQuests(Player player) {
        TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(player);
        return tcrPlayer.getCurrentQuests();
    }

    public static Collection<Quest> getAllQuests() {
        return QUEST_MAP.values();
    }

    public static void startQuest(ServerPlayer player, Quest quest) {
        startQuest(player, quest, true);
    }

    public static void startQuest(ServerPlayer player, Quest quest, boolean changeCurrentTask) {
        if (quest.equals(EMPTY)) {
            return;
        }
        TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(player);
        tcrPlayer.addQuest(quest);
        if(changeCurrentTask) {
            PlayerDataManager.currentQuestId.put(player, quest.getId());
        }
        ensureQuest(player);
        tcrPlayer.syncToClient(player);
        PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new RefreshClientQuestsPacket(), player);

        //同步任务数据给队友
        Team team = FTBTeamUtils.getTeam(player);
        if(team != null) {
            team.getOnlineMembers().forEach(teamPlayer -> {
                if(teamPlayer != player) {
                    ServerPlayer toBroadcast = TCRCapabilityProvider.getTCRPlayer(teamPlayer).copyFromFTBTeamMember(tcrPlayer) ? teamPlayer : player;
                    ensureQuest(toBroadcast);
                    tcrPlayer.syncToClient(toBroadcast);
                    PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new RefreshClientQuestsPacket(), toBroadcast);
                }
            });
        }

        //大地图标点，仅大地图可见
        if(quest.getTrackingPos() != null && quest.getDimension().equals(player.level().dimension())) {
            if(TCRCoreMod.isXaeroMapLoaded()) {
                XaeroWaypointUtils.sendWaypoint(player, TCRCoreMod.getInfoKey("quest_map_mark"), quest.getTrackingPos(), WaypointColor.GOLD, WaypointVisibilityType.WORLD_MAP_LOCAL);
            }
        }
    }

    /**
     * @return 是否成功移除
     */
    public static boolean finishQuest(ServerPlayer player, Quest quest) {
        return finishQuest(player, quest, false);
    }

    /**
     * @return 是否成功移除
     */
    public static boolean finishQuest(ServerPlayer player, Quest quest, boolean force) {
        int currentQuestId = PlayerDataManager.currentQuestId.getInt(player);
        if (!force && quest.getId() != currentQuestId) {
            return false;
        }
        TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(player);
        if(!tcrPlayer.finishQuest(quest)) {
            return false;
        }
        //当前追踪的任务被完成了则要切换一下任务，否则不变
        if(quest.getId() == currentQuestId) {
            List<Quest> currentQuests = tcrPlayer.getCurrentQuests();
            if (!currentQuests.isEmpty()) {
                PlayerDataManager.currentQuestId.put(player, currentQuests.get(currentQuests.size() - 1).getId());
            } else {
                PlayerDataManager.currentQuestId.put(player, EMPTY.getId());
            }
        }
        ensureQuest(player);
        tcrPlayer.syncToClient(player);
        PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new RefreshClientQuestsPacket(), player);

        //同步任务数据给队友
        Team team = FTBTeamUtils.getTeam(player);
        if(team != null) {
            team.getOnlineMembers().forEach(teamPlayer -> {
                if(teamPlayer != player) {
                    ServerPlayer toBroadcast = TCRCapabilityProvider.getTCRPlayer(teamPlayer).copyFromFTBTeamMember(tcrPlayer) ? teamPlayer : player;
                    ensureQuest(toBroadcast);
                    tcrPlayer.syncToClient(toBroadcast);
                    PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new RefreshClientQuestsPacket(), toBroadcast);
                }
            });
        }
        //移除大地图标点
        if(quest.getTrackingPos() != null && quest.getDimension().equals(player.level().dimension())) {
            if(TCRCoreMod.isXaeroMapLoaded()) {
                XaeroWaypointUtils.removeWaypoint(player, TCRCoreMod.getInfoKey("quest_map_mark"), quest.getTrackingPos());
            }
        }
        playFinishSound(player, quest);
        return true;
    }

    public static void playFinishSound(ServerPlayer player, Quest quest) {
        SoundEvent soundEvent = SoundEvents.PLAYER_LEVELUP;
        if(quest.finishSound != null) {
            soundEvent = quest.finishSound;
        }
        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundEvent), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
    }

    /**
     * 保险措施，确保当前选中的任务位于任务列表内。否则选第一个
     */
    public static void ensureQuest(Player player) {
        Quest selectedQuest = getCurrentQuest(player);
        List<Quest> quests = TCRCapabilityProvider.getTCRPlayer(player).getCurrentQuests();
        if (selectedQuest == null || isEmptyQuest(selectedQuest) || !quests.contains(selectedQuest)) {
            for (TCRQuestManager.Quest quest : quests) {
                if (!isEmptyQuest(quest)) {
                    PlayerDataManager.currentQuestId.put(player, quest.getId());
                    break;
                }
            }
        }
    }

    public static boolean isEmptyQuest(TCRQuestManager.Quest quest) {
        return quest == null || quest.equals(TCRQuestManager.EMPTY);
    }

    public static boolean hasFinished(Player player, Quest quest) {
        TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(player);
        return tcrPlayer.hasFinished(quest);
    }

    public static class Quest {

        private final int id;
        private final String key;
        private MutableComponent shortDesc;
        private MutableComponent desc;
        private MutableComponent title;
        private ResourceLocation icon;
        private BlockPos trackingPos;
        private ResourceKey<Level> dimension;
        private SoundEvent finishSound;
        private List<ItemStack> rewards = null;

        //id统一管理
        private Quest(int id, String key) {
            this.id = id;
            this.key = key;
            desc = Component.translatable(key + ".desc");
            shortDesc = Component.translatable(key + ".short_desc");
            title = Component.translatable(key + ".title");
        }

        public Quest titleParam(Object... objects) {
            title = Component.translatable(key + ".title", objects);
            return this;
        }

        public Quest shortDescParam(Object... objects) {
            shortDesc = Component.translatable(key + ".short_desc", objects);
            return this;
        }

        public Quest descParam(Object... objects) {
            desc = Component.translatable(key + ".desc", objects);
            return this;
        }

        public void start(ServerPlayer player) {
            TCRQuestManager.startQuest(player, this);
        }

        public void start(ServerPlayer player, boolean changeCurrentTask) {
            TCRQuestManager.startQuest(player, this, changeCurrentTask);
        }

        /**
         * @return 是否成功移除
         */
        public boolean finish(ServerPlayer player) {
            return TCRQuestManager.finishQuest(player, this);
        }

        /**
         * @return 是否成功移除
         */
        public boolean finish(ServerPlayer player, boolean force) {
            return TCRQuestManager.finishQuest(player, this, force);
        }

        public boolean isFinished(Player player) {
            return TCRQuestManager.hasFinished(player, this);
        }

        @Nullable
        public SoundEvent getFinishSound() {
            return finishSound;
        }

        @Nullable
        public ResourceLocation getIcon() {
            return icon;
        }

        @Nullable
        public BlockPos getTrackingPos() {
            return trackingPos;
        }

        public ResourceKey<Level> getDimension() {
            return dimension;
        }

        public MutableComponent getTitle() {
            return title;
        }

        public MutableComponent getShortDesc() {
            return shortDesc;
        }

        public MutableComponent getDesc() {
            return desc;
        }

        public String getKey() {
            return key;
        }

        public int getId() {
            return id;
        }

        @Nullable
        public List<ItemStack> getRewards() {
            return rewards;
        }

        public Quest withFinishSound(SoundEvent sound) {
            this.finishSound = sound;
            return this;
        }

        public Quest withIcon(ResourceLocation icon) {
            this.icon = icon;
            return this;
        }

        public Quest withTrackingPos(BlockPos trackingPos, ResourceKey<Level> dimension) {
            this.trackingPos = trackingPos;
            this.dimension = dimension;
            return this;
        }

        public Quest withRewards(Item... items) {
            this.rewards = Arrays.stream(items).map(Item::getDefaultInstance).toList();
            return this;
        }

        public Quest withRewards(ItemStack... itemStack) {
            this.rewards = List.of(itemStack);
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Quest quest = (Quest) o;
            return id == quest.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }
}
