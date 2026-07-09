package com.p1nero.tcrcore.capability;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.entity.custom.mimic.TCRMimic;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.OpenEndScreenPacket;
import com.p1nero.tcrcore.network.packet.clientbound.SyncTCRPlayerPacket;
import com.p1nero.tcrcore.utils.EntityUtils;
import com.p1nero.tcrcore.utils.FTBTeamUtils;
import com.p1nero.tcrcore.utils.ItemUtils;
import com.p1nero.tcrcore.utils.WorldUtils;
import com.yesman.epicskills.registry.entry.EpicSkillsItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import org.merlin204.mimic.worldgen.WraithonDimensions;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TCRPlayer {

    @Nullable
    private final Player original;
    public static final String PLAYER_NAME = "player_name";
    private int sardine;
    public static int SARDINE_COUNT;
    private CompoundTag data = new CompoundTag();
    private double healthAdder = 0;
    private int tickAfterBossDieLeft;
    private int tickAfterBless;
    private BlockPos blessPos = BlockPos.ZERO;
    private Item blessItem;

    //=======共鸣石冷却任务计时=======
    private boolean resonanceStoneInCooldown;
    private long resonanceStoneStartTime;
    public static final int RESONANCE_STONE_COOLDOWN = 3600;

    //=======指路粒子=====
    private int spawnParticleTimer = 0;
    private final int particleCount = 20;
    private Vec3 from = Vec3.ZERO;
    private Vec3 dir = Vec3.ZERO;
    //=======任务系统===========
    private List<TCRQuestManager.Quest> currentQuests = new ArrayList<>();
    private List<Integer> finishedQuests = new ArrayList<>();

    public TCRPlayer(@Nullable Player player) {
        this.original = player;
    }

    public void addQuest(TCRQuestManager.Quest quest) {
        if (currentQuests.contains(quest)) {
            return;
        }
        currentQuests.add(quest);
    }

    public boolean finishQuest(TCRQuestManager.Quest quest) {
        if(currentQuests.remove(quest)) {
            finishedQuests.add(quest.getId());
            return true;
        }
        return false;
    }

    public boolean hasFinished(TCRQuestManager.Quest quest) {
        return finishedQuests.contains(quest.getId());
    }

    public List<TCRQuestManager.Quest> getCurrentQuests() {
        return currentQuests;
    }

    public void startWaitingResonanceStoneCharge(ServerPlayer serverPlayer) {
        resonanceStoneStartTime = serverPlayer.serverLevel().getGameTime();//防止玩家使用add无效
        resonanceStoneInCooldown = true;
        TCRQuests.WAIT_RESONANCE_STONE_CHARGE.start(serverPlayer);
    }

    public void setSardine(int sardine) {
        this.sardine = sardine;
    }

    public int getSardine() {
        return sardine;
    }

    public static void updateSardineCount(ServerPlayer serverPlayer) {
        if(serverPlayer.server.isSingleplayer()) {
            SARDINE_COUNT = TCRCapabilityProvider.getTCRPlayer(serverPlayer).sardine;
        }
    }

    public double getHealthAdder() {
        return healthAdder;
    }

    public void setHealthAdder(double healthAdder) {
        this.healthAdder = healthAdder;
    }

    public void setTickAfterBless(int tickAfterBless) {
        this.tickAfterBless = tickAfterBless;
    }

    public void setBlessItem(Item blessItem) {
        this.blessItem = blessItem;
    }

    public boolean inBlessing() {
        return this.tickAfterBless > 1;
    }

    public void setBlessPos(BlockPos blessPos) {
        this.blessPos = blessPos;
    }

    public void setTickAfterBossDieLeft(int tickAfterBossDieLeft) {
        this.tickAfterBossDieLeft = tickAfterBossDieLeft;
    }

    private PathfinderMob currentTalkingEntity;

    public void setCurrentTalkingEntity(@Nullable PathfinderMob currentTalkingEntity) {
        this.currentTalkingEntity = currentTalkingEntity;
    }

    public boolean getBoolean(String key) {
        return data.getBoolean(key);
    }

    public double getDouble(String key) {
        return data.getDouble(key);
    }

    public String getString(String key) {
        return data.getString(key);
    }

    public void putBoolean(String key, boolean value) {
        data.putBoolean(key, value);
    }

    public void putDouble(String key, double v) {
        data.putDouble(key, v);
    }

    public void putString(String k, String v) {
        data.putString(k, v);
    }

    public CompoundTag getData() {
        return data;
    }

    public CompoundTag saveNBTData(CompoundTag tag) {
        tag.put("customDataManager", data);
        tag.putDouble("healthAdder", healthAdder);
        tag.putInt("tickAfterBossDieLeft", tickAfterBossDieLeft);
        tag.putInt("tickAfterBless", tickAfterBless);
        if(blessPos != null) {
            tag.put("blessPos", NbtUtils.writeBlockPos(blessPos));
        }
        if(blessItem != null &&ForgeRegistries.ITEMS.containsValue(blessItem)) {
            tag.putString("blessItem", ForgeRegistries.ITEMS.getKey(blessItem).toString());
        }
        tag.putBoolean("resonanceStoneInCooldown", resonanceStoneInCooldown);
        tag.putLong("resonanceStoneStartTime", resonanceStoneStartTime);
        tag.putInt("questSize", currentQuests.size());
        for (int i = 0; i < currentQuests.size(); i++) {
            tag.putInt("quest_" + i, currentQuests.get(i).getId());
        }
        tag.putInt("finishedQuestSize", finishedQuests.size());
        for (int i = 0; i < finishedQuests.size(); i++) {
            tag.putInt("finished_quest_" + i, finishedQuests.get(i));
        }
        tag.putInt("sardine", sardine);
        return tag;
    }

    public void loadNBTData(CompoundTag tag) {
        data = tag.getCompound("customDataManager");
        healthAdder = tag.getDouble("healthAdder");
        tickAfterBossDieLeft = tag.getInt("tickAfterBossDieLeft");
        tickAfterBless = tag.getInt("tickAfterBless");
        blessItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(tag.getString("blessItem")));
        blessPos = NbtUtils.readBlockPos(tag.getCompound("blessPos"));
        currentQuests.clear();
        resonanceStoneInCooldown = tag.getBoolean("resonanceStoneInCooldown");
        resonanceStoneStartTime = tag.getLong("resonanceStoneStartTime");
        int questSize = tag.getInt("questSize");
        for (int i = 0; i < questSize; i++) {
            String key = "quest_" + i;
            int id = tag.getInt(key);
            currentQuests.add(TCRQuestManager.getQuestById(id));
        }
        finishedQuests.clear();
        int finishedQuestSize = tag.getInt("finishedQuestSize");
        for (int i = 0; i < finishedQuestSize; i++) {
            String key = "finished_quest_" + i;
            int id = tag.getInt(key);
            finishedQuests.add(id);
        }
        sardine = tag.getInt("sardine");
    }

    /**
     * 给ftb团队用的
     */
    public boolean copyFromFTBTeamMember(ServerPlayer serverPlayer) {
        return copyFromFTBTeamMember(TCRCapabilityProvider.getTCRPlayer(serverPlayer));
    }

    /**
     * @param old 被复制的
     * @return 复制或被复制
     */
    public boolean copyFromFTBTeamMember(TCRPlayer old) {
        if(this == old) {
            return false;
        }
        // 从完成的多的复制到完成得少的身上
        if(old.finishedQuests.size() < this.finishedQuests.size()) {
            old.copyFrom(this);
            return false;
        }
        this.copyFrom(old);
        return true;
    }

    public void copyFrom(TCRPlayer old) {
        this.data = old.data;
        this.healthAdder = old.healthAdder;
        this.resonanceStoneInCooldown = old.resonanceStoneInCooldown;
        this.resonanceStoneStartTime = old.resonanceStoneStartTime;
        this.tickAfterBossDieLeft = old.tickAfterBossDieLeft;
        this.tickAfterBless = old.tickAfterBless;
        this.currentQuests = old.currentQuests;
        this.finishedQuests = old.finishedQuests;
        this.sardine = old.sardine;
    }

    public void clear() {
        copyFrom(new TCRPlayer(original));
    }

    public void playDirectionParticle(Vec3 from, Vec3 to) {
        this.from = from;
        this.dir = to.subtract(from).normalize();
        this.spawnParticleTimer = this.particleCount;
    }

    public void syncToClient(ServerPlayer serverPlayer) {
        PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new SyncTCRPlayerPacket(saveNBTData(new CompoundTag())), serverPlayer);
    }

    public void tick(Player player) {
        if (player.isLocalPlayer()) {

        } else if (player instanceof ServerPlayer serverPlayer) {
            ServerLevel serverLevel = serverPlayer.serverLevel();
            handleTalking(serverPlayer);
            handleAfterFinalBossFight(serverPlayer);
            handleBless(serverLevel, serverPlayer);
            handleParticle(serverPlayer);
            handleResonanceStoneCooldown(serverPlayer);
            handleUpdateNearestQuest(serverPlayer);
        }
    }

    /**
     * 处理任务靠近自动追踪
     */
    private void handleUpdateNearestQuest(ServerPlayer serverPlayer) {
        if(serverPlayer.tickCount % 10 == 0) {
            if(WorldUtils.inMainLand(serverPlayer)) {
                trySearchNearestQuest(WorldUtils.AINE_BLOCK_POS, serverPlayer);
                trySearchNearestQuest(WorldUtils.ORNN_BLOCK_POS, serverPlayer);
                trySearchNearestQuest(WorldUtils.CHRONOS_SOL_BLOCK_POS, serverPlayer);
                trySearchNearestQuest(WorldUtils.FERRY_GIRL_BLOCK_POS, serverPlayer);
            }
        }
    }

    private void trySearchNearestQuest(BlockPos targetPos, ServerPlayer serverPlayer) {
        if(serverPlayer.position().closerThan(targetPos.getCenter(), 10)) {
            BlockPos currentTrackingPos = TCRQuestManager.getCurrentQuest(serverPlayer).getTrackingPos();
            if(currentTrackingPos == null || !currentTrackingPos.closerThan(targetPos, 5)) {
                for (TCRQuestManager.Quest quest : TCRQuestManager.getQuests(serverPlayer)) {
                    if(quest.getTrackingPos() != null && quest.getTrackingPos().closerThan(targetPos, 5)) {
                        TCRQuestManager.setCurrentQuest(serverPlayer, quest);
                        EntityUtils.playLocalSound(serverPlayer, SoundEvents.EXPERIENCE_ORB_PICKUP);
                        serverPlayer.displayClientMessage(TCRCoreMod.getInfo("quest_updated").withStyle(ChatFormatting.GOLD), true);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 传送石冷却，6000tick后再完成充能
     */
    private void handleResonanceStoneCooldown(ServerPlayer serverPlayer) {
        if(!resonanceStoneInCooldown) {
            return;
        }
        long currentTime = serverPlayer.serverLevel().getGameTime();
        //冷却结束，根据任务给予共鸣石
        if(currentTime < resonanceStoneStartTime || currentTime - resonanceStoneStartTime > RESONANCE_STONE_COOLDOWN) {
            TCRQuests.WAIT_RESONANCE_STONE_CHARGE.finish(serverPlayer, true);
            //开启海洋章，用前一个眼是否完成来判断
            if(PlayerDataManager.desertEyeGotten.get(serverPlayer) && !TCRQuests.TALK_TO_CHRONOS_2.isFinished(serverPlayer)) {
                TCRQuests.TALK_TO_CHRONOS_2.start(serverPlayer);
            }
            //开启烈焰章，用前一个眼是否完成来判断
            if(PlayerDataManager.cursedEyeGotten.get(serverPlayer) && !TCRQuests.TALK_TO_CHRONOS_6.isFinished(serverPlayer)) {
                TCRQuests.TALK_TO_CHRONOS_6.start(serverPlayer);
            }
            //开启地狱章，用前一个眼是否完成来判断
            if(PlayerDataManager.flameEyeGotten.get(serverPlayer) && !TCRQuests.TALK_TO_CHRONOS_8.isFinished(serverPlayer)) {
                TCRQuests.TALK_TO_CHRONOS_8.start(serverPlayer);
            }
            //开天堂章，用前一个眼是否完成来判断
            if(PlayerDataManager.monstEyeGotten.get(serverPlayer) && !TCRQuests.TALK_TO_CHRONOS_11.isFinished(serverPlayer)) {
                TCRQuests.TALK_TO_CHRONOS_11.start(serverPlayer);
            }
            resonanceStoneInCooldown = false;
        }
    }

    private void handleParticle(ServerPlayer serverPlayer) {
        double step = 5.0 / particleCount;
        if (spawnParticleTimer > 0) {
            spawnParticleTimer--;
            for (int i = particleCount - spawnParticleTimer; i <= particleCount; i++) {
                ParticleOptions particle = ParticleTypes.END_ROD;
                double distance = i * step;
                Vec3 particlePos = from.add(dir.scale(distance).add(0, i * 0.1, 0));
                serverPlayer.serverLevel().sendParticles(
                        particle,
                        particlePos.x,
                        particlePos.y,
                        particlePos.z,
                        0,
                        dir.x, dir.y, dir.z,
                        0.1f
                );
            }
        }
    }

    private void handleTalking(ServerPlayer player) {
        if (this.currentTalkingEntity != null && this.currentTalkingEntity.isAlive()) {
            this.currentTalkingEntity.getLookControl().setLookAt(player);
            this.currentTalkingEntity.getNavigation().stop();
            if (this.currentTalkingEntity.distanceTo(player) > 8) {
                this.currentTalkingEntity = null;
            }
        }
    }

    private void handleAfterFinalBossFight(ServerPlayer player) {
        //Boss战后的返回倒计时
        if (tickAfterBossDieLeft > 0) {
            tickAfterBossDieLeft--;
            //保险
            new ArrayList<TCRMimic>(player.server.getLevel(WraithonDimensions.THE_LETHEAN_SEA_LEVEL_KEY).getEntities(TCREntities.TCR_MIMIC.get(), (LivingEntity::isAlive)))
                    .forEach(Entity::discard);
            if (tickAfterBossDieLeft % 40 == 0) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 0.8F, 0.5F + tickAfterBossDieLeft / 400.0F);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BELL_BLOCK, SoundSource.BLOCKS, 0.8F, 0.5F + tickAfterBossDieLeft / 400.0F);
            }
            player.displayClientMessage(TCRCoreMod.getInfo("second_after_boss_die_left", tickAfterBossDieLeft / 20).withStyle(ChatFormatting.BOLD, ChatFormatting.RED), true);
            if (tickAfterBossDieLeft == 0) {
                PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new OpenEndScreenPacket(), player);
            }
        }
    }

    private void handleBless(ServerLevel serverLevel, ServerPlayer serverPlayer) {
        //女神像祈福
        if (tickAfterBless > 0) {
            tickAfterBless--;
            if (tickAfterBless % 10 == 0) {
                // 计算进度比例 (从100到1，所以进度是1.0到0.01)
                float progress = 1.0f - (tickAfterBless / 100.0f);

                // 播放紫水晶音效，音调随进度逐渐增高
                float pitch = 0.5f + progress * 1.5f; // 从0.5到2.0
                serverLevel.playSound(null, blessPos, SoundEvents.AMETHYST_BLOCK_CHIME,
                        SoundSource.AMBIENT, 3.0F, pitch);

                // 如果进度较高，额外播放一个音效增加史诗感
                if (progress > 0.7f) {
                    serverLevel.playSound(null, blessPos, SoundEvents.BEACON_AMBIENT,
                            SoundSource.AMBIENT, 3.0F, 0.8f + progress * 0.4f);
                }

                double centerX = blessPos.getX() + 0.5;
                double centerY = blessPos.getY() + 1.0;
                double centerZ = blessPos.getZ() + 0.5;

                // 根据进度计算粒子半径和数量（随进度增加而增大）
                double baseRadius = 2.0;
                double maxRadius = 6.0;
                double currentRadius = baseRadius + (maxRadius - baseRadius) * progress;

                int baseParticleCount = 8;
                int maxParticleCount = 32;
                int currentParticleCount = baseParticleCount + (int) ((maxParticleCount - baseParticleCount) * progress);

                // 在水平面上创建圆形粒子效果
                for (int i = 0; i < currentParticleCount; i++) {
                    double angle = 2 * Math.PI * i / currentParticleCount;

                    double x = centerX + currentRadius * Math.cos(angle);
                    double z = centerZ + currentRadius * Math.sin(angle);
                    double y = centerY + serverLevel.random.nextDouble() * 2.0;

                    // 从圆周向中心发射粒子
                    double speedX = (centerX - x) * 0.1;
                    double speedZ = (centerZ - z) * 0.1;
                    double speedY = 0.1;

                    serverLevel.sendParticles(ParticleTypes.END_ROD,
                            x, y, z,
                            1,
                            speedX, speedY, speedZ,
                            0.05);
                }

                // 在垂直方向上也创建粒子效果，数量也随进度增加
                int baseVerticalCount = 4;
                int maxVerticalCount = 12;
                int currentVerticalCount = baseVerticalCount + (int) ((maxVerticalCount - baseVerticalCount) * progress);

                double verticalRadius = 1.5 + progress * 2.5; // 垂直粒子半径也从1.5到4.0

                for (int i = 0; i < currentVerticalCount; i++) {
                    double angle = 2 * Math.PI * i / currentVerticalCount;

                    double x = centerX + verticalRadius * Math.cos(angle);
                    double z = centerZ + verticalRadius * Math.sin(angle);

                    // 从底部向上发射粒子
                    serverLevel.sendParticles(ParticleTypes.END_ROD,
                            x, centerY - 1, z,
                            1,
                            0, 0.2, 0,
                            0.02);
                }

                // 在中心位置添加一些随机粒子，数量也随进度增加
                int baseRandomCount = 3;
                int maxRandomCount = 10;
                int currentRandomCount = baseRandomCount + (int) ((maxRandomCount - baseRandomCount) * progress);

                for (int i = 0; i < currentRandomCount; i++) {
                    double offsetX = (serverLevel.random.nextDouble() - 0.5) * 2.0;
                    double offsetY = serverLevel.random.nextDouble() * 2.0;
                    double offsetZ = (serverLevel.random.nextDouble() - 0.5) * 2.0;

                    serverLevel.sendParticles(ParticleTypes.END_ROD,
                            centerX + offsetX, centerY + offsetY, centerZ + offsetZ,
                            1,
                            0, 0, 0,
                            0.1);
                }
            }
            //加血加耐
            if (tickAfterBless == 0) {
                final double oldAdder = healthAdder;
                ItemStack item = blessItem == null ? serverPlayer.getMainHandItem() : blessItem.getDefaultInstance();
                if (item.is(ModItems.STORM_EYE.get())  && !PlayerDataManager.stormEyeBlessed.get(serverPlayer)) {
                    healthAdder += 2.0;
                    PlayerDataManager.stormEyeBlessed.put(serverPlayer, true);
                } else if (item.is(ModItems.ABYSS_EYE.get()) && !PlayerDataManager.abyssEyeBlessed.get(serverPlayer)) {
                    healthAdder += 2.0;
                    PlayerDataManager.abyssEyeBlessed.put(serverPlayer, true);
                } else if (item.is(ModItems.DESERT_EYE.get()) && !PlayerDataManager.desertEyeBlessed.get(serverPlayer)) {
                    healthAdder += 2.0;
                    PlayerDataManager.desertEyeBlessed.put(serverPlayer, true);
                } else if (item.is(ModItems.CURSED_EYE.get()) && !PlayerDataManager.cursedEyeBlessed.get(serverPlayer)) {
                    healthAdder += 2.0;
                    PlayerDataManager.cursedEyeBlessed.put(serverPlayer, true);
                } else if (item.is(ModItems.FLAME_EYE.get()) && !PlayerDataManager.flameEyeBlessed.get(serverPlayer)) {
                    healthAdder += 2.0;
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.END_PORTAL_SPAWN), SoundSource.PLAYERS, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 1.0F, 1.0F, serverPlayer.getRandom().nextInt()));
                    PlayerDataManager.flameEyeBlessed.put(serverPlayer, true);
                } else if (item.is(ModItems.MONSTROUS_EYE.get()) && !PlayerDataManager.monstEyeBlessed.get(serverPlayer)) {
                    healthAdder += 2.0;
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.END_PORTAL_SPAWN), SoundSource.PLAYERS, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 1.0F, 1.0F, serverPlayer.getRandom().nextInt()));
                    PlayerDataManager.monstEyeBlessed.put(serverPlayer, true);
                } else if (item.is(ModItems.MECH_EYE.get()) && !PlayerDataManager.mechEyeBlessed.get(serverPlayer)) {
                    healthAdder += 2.0;
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.END_PORTAL_SPAWN), SoundSource.PLAYERS, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 1.0F, 1.0F, serverPlayer.getRandom().nextInt()));
                    PlayerDataManager.mechEyeBlessed.put(serverPlayer, true);
                } else if (item.is(ModItems.VOID_EYE.get()) && !PlayerDataManager.voidEyeBlessed.get(serverPlayer)) {
                    healthAdder += 2.0;
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.END_PORTAL_SPAWN), SoundSource.PLAYERS, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 1.0F, 1.0F, serverPlayer.getRandom().nextInt()));
                    PlayerDataManager.voidEyeBlessed.put(serverPlayer, true);
                }
                if (oldAdder < healthAdder) {
                    updateHealth(serverPlayer, true, oldAdder);
                    FTBTeamUtils.syncDataToTeamMembers(serverPlayer);
                    ItemUtils.addItemEntity(serverPlayer, EpicSkillsItems.ABILIITY_STONE.get(), 2, ChatFormatting.GOLD.getColor());
                    FTBTeamUtils.onlineTeamMembersDoIgnoreSelf(serverPlayer, member -> {
                        TCRCapabilityProvider.getTCRPlayer(member).updateHealth(member, true, 0);
                        ItemUtils.addItemEntity(member, EpicSkillsItems.ABILIITY_STONE.get(), 2, ChatFormatting.GOLD.getColor());
                    });
                    if(TCRQuestManager.hasQuest(serverPlayer, TCRQuests.BLESS_ON_THE_GODNESS_STATUE)){
                        TCRQuests.BLESS_ON_THE_GODNESS_STATUE.finish(serverPlayer, true);
                    }
                } else {
                    serverPlayer.displayClientMessage(TCRCoreMod.getInfo("nothing_happen_after_bless"), false);
                }
            }
        }
    }

    public void updateHealth(ServerPlayer serverPlayer, boolean showTip, double originalAdder) {
        final UUID HEALTH_MODIFIER_UUID = UUID.fromString("11451419-1981-0234-1234-123456789abc");
        float preHealth = serverPlayer.getHealth();
        float preMaxHealth = serverPlayer.getMaxHealth();
        AttributeInstance maxHealthAttr = serverPlayer.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttr != null) {
            maxHealthAttr.removeModifier(HEALTH_MODIFIER_UUID);
            AttributeModifier healthModifier = new AttributeModifier(
                    HEALTH_MODIFIER_UUID,
                    "health_boost",
                    healthAdder,
                    AttributeModifier.Operation.ADDITION
            );
            maxHealthAttr.addPermanentModifier(healthModifier);
            serverPlayer.setHealth(preHealth * serverPlayer.getMaxHealth() / preMaxHealth);
            if (showTip) {
                serverPlayer.displayClientMessage(Component.translatable(Attributes.MAX_HEALTH.getDescriptionId()).withStyle(ChatFormatting.RED).append(" + " + (healthAdder - originalAdder)), false);
            }
        }
        AttributeInstance staminaAttr = serverPlayer.getAttribute(EpicFightAttributes.MAX_STAMINA.get());
        if (staminaAttr != null) {
            staminaAttr.removeModifier(HEALTH_MODIFIER_UUID);
            AttributeModifier staminaModifier = new AttributeModifier(
                    HEALTH_MODIFIER_UUID,
                    "stamina_boost",
                    (healthAdder / 2.5F),
                    AttributeModifier.Operation.ADDITION
            );
            staminaAttr.addPermanentModifier(staminaModifier);
            if (showTip) {
                serverPlayer.displayClientMessage(Component.translatable(EpicFightAttributes.MAX_STAMINA.get().getDescriptionId()).withStyle(ChatFormatting.GOLD).append(String.format(" + %.1f", ((healthAdder - originalAdder) / 2.5F))), false);
            }
        }
    }
}
