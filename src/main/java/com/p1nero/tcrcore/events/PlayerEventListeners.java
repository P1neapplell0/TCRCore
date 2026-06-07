package com.p1nero.tcrcore.events;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.brass_amber.ba_bt.entity.hostile.golem.EndGolem;
import com.brass_amber.ba_bt.init.BTEntityType;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.hm.efn.EFN;
import com.hm.efn.gameasset.EFNSkills;
import com.p1nero.battle_field1.worldgen.PBF1Dimensions;
import com.p1nero.cataclysm_dimension.CataclysmDimensionMod;
import com.p1nero.cataclysm_dimension.worldgen.CataclysmDimensions;
import com.p1nero.dpr.gameassets.DPRSkills;
import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.p1nero_ec.capability.PECDataManager;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.*;
import com.p1nero.tcrcore.datagen.TCRAdvancementData;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_boss.FakeBossNpc;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_end_golem.FakeEndGolem;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.*;
import com.p1nero.tcrcore.save_data.TCRDimSaveData;
import com.p1nero.tcrcore.utils.EntityUtils;
import com.p1nero.tcrcore.utils.FTBTeamUtils;
import com.p1nero.tcrcore.utils.ItemUtils;
import com.p1nero.tcrcore.utils.WorldUtils;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import com.p1nero.tudigong.item.TDGItems;
import com.wintercogs.beyonddimensions.api.dimensionnet.DimensionsNet;
import com.wintercogs.beyonddimensions.api.storage.key.impl.ItemStackKey;
import com.wintercogs.beyonddimensions.common.init.BDItems;
import com.wintercogs.beyonddimensions.common.menu.DimensionsNetMenu;
import com.yesman.epicskills.registry.entry.EpicSkillsItems;
import com.yesman.epicskills.registry.entry.EpicSkillsSkillTrees;
import com.yesman.epicskills.skilltree.SkillTree;
import com.yesman.epicskills.world.capability.SkillTreeProgression;
import com.yungnickyoung.minecraft.betterendisland.world.IDragonFight;
import dev.ftb.mods.ftbteams.api.Team;
import dev.ftb.mods.ftbteams.api.event.PlayerChangedTeamEvent;
import dev.ftb.mods.ftbteams.api.event.PlayerJoinedPartyTeamEvent;
import dev.ftb.mods.ftbteams.api.event.TeamCreatedEvent;
import net.blay09.mods.waystones.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.p1nero.ss.SwordSoaringMod;
import net.p1nero.ss.gameassets.skills.SwordControllerSkills;
import org.merlin204.leonidas.item.LeonidasItems;
import org.merlin204.wraithon.util.PositionTeleporter;
import org.merlin204.wraithon.worldgen.WraithonDimensions;
import top.theillusivec4.curios.api.event.CurioEquipEvent;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlot;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.gamerule.EpicFightGameRules;

import java.util.*;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID)
public class PlayerEventListeners {

    public static void onPlayerTeamChanged(PlayerJoinedPartyTeamEvent playerJoinedPartyTeamEvent) {
        ServerPlayer serverPlayer = playerJoinedPartyTeamEvent.getPlayer();
        Team team = playerJoinedPartyTeamEvent.getTeam();
        if(team == null) {
            return;
        }
        FTBTeamUtils.syncDataFromTeam(serverPlayer, team);
        if(!team.getOwner().equals(serverPlayer.getUUID())) {
            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("join_party_warning").withStyle(ChatFormatting.GOLD), false);
        }
    }

    public static void onTeamCreated(TeamCreatedEvent teamCreatedEvent) {
        if(teamCreatedEvent.getTeam().isPartyTeam()) {
            teamCreatedEvent.getCreator().displayClientMessage(TCRCoreMod.getInfo("join_party_warning").withStyle(ChatFormatting.GOLD), false);
        }
    }

    @SubscribeEvent
    public static void onPlayerAdvancementEarn(AdvancementEvent.AdvancementEarnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            String path = event.getAdvancement().getId().getPath();
            String namespace = event.getAdvancement().getId().getNamespace();
            if (namespace.equals(TCRCoreMod.MOD_ID)) {
                if (path.equals("vatansever")) {
                    player.getCapability(SkillTreeProgression.SKILL_TREE_PROGRESSION).ifPresent(skillTreeProgression -> {
                        ResourceKey<SkillTree> resourceKey = ResourceKey.create(SkillTree.SKILL_TREE_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(SwordSoaringMod.MOD_ID, "sword_soaring_skills"));
                        skillTreeProgression.unlockTree(resourceKey, player);
                        skillTreeProgression.unlockNode(resourceKey, SwordControllerSkills.RAIN_SWORD, player);
                        skillTreeProgression.unlockNode(resourceKey, SwordControllerSkills.SCREEN_SWORD, player);
                        skillTreeProgression.unlockNode(resourceKey, SwordControllerSkills.KILL_AURA_1, player);
                        skillTreeProgression.unlockNode(resourceKey, SwordControllerSkills.KILL_AURA_2, player);
                    });

                    player.displayClientMessage(TCRCoreMod.getInfo("unlock_new_skill", SwordControllerSkills.RAIN_SWORD.getDisplayName()), false);
                    player.displayClientMessage(TCRCoreMod.getInfo("unlock_new_skill", SwordControllerSkills.SCREEN_SWORD.getDisplayName()), false);
                    player.displayClientMessage(TCRCoreMod.getInfo("unlock_new_skill", SwordControllerSkills.KILL_AURA_1.getDisplayName()), false);
                    player.displayClientMessage(TCRCoreMod.getInfo("unlock_new_skill", SwordControllerSkills.KILL_AURA_2.getDisplayName()), false);
                }
            }

            if (namespace.equals("minecraft") && path.equals("recipes/transportation/oak_boat")) {
                PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PlayTitlePacket(PlayTitlePacket.RIPTIDE_TUTORIAL), player);
            }

            if (namespace.equals(EFN.MODID) && (path.equals("yamato_dmc4_in_sheath") || path.equals("yamato_dmc_in_sheath"))) {
                if (!TCRQuestManager.hasFinished(player, TCRQuests.TALK_TO_ORNN_YAMATO)) {
                    TCRQuests.TALK_TO_ORNN_YAMATO.start(player);
                }
            }

        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer serverPlayer) {
            //如果同步成功就不用发包了
            if(!FTBTeamUtils.syncDataFromTeam(serverPlayer)) {
                TCRCapabilityProvider.syncPlayerDataToClient(serverPlayer);
            }
            TCRPlayer.updateSardineCount(serverPlayer);
            handleFirstJoin(serverPlayer, false);
        }
    }

    public static void handleFirstJoin(ServerPlayer serverPlayer, boolean isNGPlus) {
        if (!PlayerDataManager.firstJoint.get(serverPlayer)) {
            serverPlayer.setRespawnPosition(TCRDimensions.SANCTUM_LEVEL_KEY, new BlockPos(WorldUtils.START_POS), 90, true, false);
            ServerLevel targetLevel = serverPlayer.server.getLevel(TCRDimensions.SANCTUM_LEVEL_KEY);
            serverPlayer = (ServerPlayer) serverPlayer.changeDimension(targetLevel, new PositionTeleporter(new BlockPos(WorldUtils.START_POS)));
            TCRAdvancementData.finishAdvancement(TCRCoreMod.MOD_ID, serverPlayer);
            if (!isNGPlus) {
                serverPlayer.server.getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).set(true, serverPlayer.server);
                serverPlayer.server.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).set(false, serverPlayer.server);
                serverPlayer.server.getGameRules().getRule(GameRules.RULE_DISABLE_RAIDS).set(true, serverPlayer.server);
                serverPlayer.server.getGameRules().getRule(EpicFightGameRules.SKILL_REPLACE_COOLDOWN.getRuleKey()).set(200, serverPlayer.server);
                ServerPlayer finalServerPlayer = serverPlayer;
                ResourceKey<SkillTree> dpr = ResourceKey.create(SkillTree.SKILL_TREE_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath("dodge_parry_reward", "passive"));
                serverPlayer.getCapability(SkillTreeProgression.SKILL_TREE_PROGRESSION).ifPresent(skillTreeProgression -> {
                    skillTreeProgression.unlockNode(EpicSkillsSkillTrees.BATTLEBORN, EFNSkills.EFN_DODGE_ROLL, finalServerPlayer);
                    skillTreeProgression.unlockNode(EpicSkillsSkillTrees.BATTLEBORN, EFNSkills.EFN_DODGE_STEP, finalServerPlayer);
                    skillTreeProgression.unlockNode(EpicSkillsSkillTrees.BATTLEBORN, EFNSkills.EFN_PARRY, finalServerPlayer);
                    skillTreeProgression.unlockNode(dpr, DPRSkills.STAMINA1, finalServerPlayer);
                });
                addSkill(serverPlayer, EFNSkills.EFN_DODGE_ROLL, SkillSlots.DODGE);
                addSkill(serverPlayer, EFNSkills.EFN_PARRY, SkillSlots.GUARD);
                addSkill(serverPlayer, DPRSkills.STAMINA1, SkillSlots.PASSIVE1);

                DimensionsNet net = addBeyondDimensionNet(serverPlayer);

                ItemUtils.addItem(serverPlayer, Items.LANTERN, 1);
                ItemUtils.addItem(serverPlayer, Items.BREAD, 32);
                ItemUtils.addItem(serverPlayer, EpicSkillsItems.ABILIITY_STONE.get(), 1);
                ItemUtils.addItem(serverPlayer, TCRItems.RESET_SKILL_STONE.get(), 1);
                ItemUtils.addItem(serverPlayer, TDGItems.TUDI_COMMAND_SPELL.get(), 1);

                net.getUnifiedStorage().insert(new ItemStackKey(BDItems.XP_EXCHANGE_ITEM.get().getDefaultInstance()), 1, false);
            }
            PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new OpenCustomDialogPacket(OpenCustomDialogPacket.GAME_START), serverPlayer);
            TCRQuests.TALK_TO_AINE_0.start(serverPlayer);
            TCRQuests.TALK_TO_CHRONOS_0.start(serverPlayer);

            if (!isNGPlus) {
                PECDataManager.resetAll(serverPlayer, true);
            }

            PlayerDataManager.firstJoint.put(serverPlayer, true);
        }

        if (TCRCoreMod.hasCoolMod()) {
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 9999, 9999));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9999, 9999));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK, 9999, 9999));
        }

        PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new CSTipPacket(), serverPlayer);
    }

    public static DimensionsNet addBeyondDimensionNet(ServerPlayer player) {
        DimensionsNet net = DimensionsNet.getNetFromPlayer(player);
        if (net != null) {
            return net;
        }
        DimensionsNet.createNewNetForPlayer(player, Long.MAX_VALUE, Integer.MAX_VALUE);
        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.8F, 1.0F);
        return DimensionsNet.getNetFromPlayer(player);
    }

    public static void addSkill(ServerPlayer player, Skill skill, SkillSlot slot) {
        ServerPlayerPatch playerpatch = EpicFightCapabilities.getEntityPatch(player, ServerPlayerPatch.class);
        SkillContainer skillContainer = playerpatch.getSkillCapability().getSkillContainerFor(slot);

        if (skillContainer.setSkill(skill)) {
            if (skill.getCategory().learnable()) {
                playerpatch.getSkillCapability().addLearnedSkill(skill);
            }

            EpicFightNetworkManager.sendToPlayer(skillContainer.createSyncPacketToLocalPlayer(), player);
            EpicFightNetworkManager.sendToAllPlayerTrackingThisEntity(skillContainer.createSyncPacketToRemotePlayer(), player);
        }
    }

    @SubscribeEvent
    public static void onPlayerInteractBlock(PlayerInteractEvent.RightClickBlock event) {

        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            BlockState blockState = event.getLevel().getBlockState(event.getPos());
            //第一次交互给传送石和提示
            if (blockState.is(ModBlocks.waystone)) {
                if (!PlayerDataManager.wayStoneInteracted.get(serverPlayer)) {
                    ItemUtils.addItemEntity(serverPlayer, net.blay09.mods.waystones.item.ModItems.warpStone.getDefaultInstance());
                    serverPlayer.displayClientMessage(TCRCoreMod.getInfo("press_to_open_portal_screen2"), false);
                    PlayerDataManager.wayStoneInteracted.put(serverPlayer, true);
                }
            }

            //和女神像交互的处理
            else if (blockState.is(com.github.L_Ender.cataclysm.init.ModBlocks.GODDESS_STATUE.get()) && ItemUtils.eyesItems.contains(serverPlayer.getMainHandItem().getItem())) {
                TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(serverPlayer);
                ServerLevel serverLevel = serverPlayer.serverLevel();
                BlockPos blessPos = event.getPos();
                serverLevel.playSound(null, blessPos, SoundEvents.BEACON_AMBIENT,
                        SoundSource.AMBIENT, 0.7F, 0.5F + serverLevel.random.nextFloat() * 0.3F);

                if (!tcrPlayer.inBlessing()) {
                    tcrPlayer.setTickAfterBless(100);
                    tcrPlayer.setBlessPos(event.getPos());
                    tcrPlayer.setBlessItem(serverPlayer.getMainHandItem().getItem());
                }
            }

            //击败boss前禁止交互
            else if (CataclysmDimensions.LEVELS.contains(serverPlayer.serverLevel().dimension())) {
                boolean isChest = event.getLevel().getBlockState(event.getPos()).is(Blocks.CHEST) || event.getLevel().getBlockState(event.getPos()).is(noobanidus.mods.lootr.init.ModBlocks.CHEST.get());
                if (isChest && !TCRDimSaveData.get(serverPlayer.serverLevel()).isBossKilled()) {
                    serverPlayer.displayClientMessage(TCRCoreMod.getInfo("dim_block_no_interact"), true);
                    event.setCanceled(true);
                }
            }
        }

    }

    public static final Set<Item> illegalItems = new HashSet<>();

    @SubscribeEvent
    public static void onCurioEquip(CurioEquipEvent event) {
        if (illegalItems.contains(event.getStack().getItem())) {
            ///connection可能null，估计优化模组干的= =
            if (event.getEntity() instanceof ServerPlayer player && player.connection != null  && !player.isCreative()) {
                player.displayClientMessage(TCRCoreMod.getInfo("illegal_item_tip"), true);
                event.setResult(Event.Result.DENY);
                event.getStack().shrink(1);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {

            if (event.player.isLocalPlayer() && WorldUtils.inMainLand(event.player)) {
                if (isNearBarrier(event.player)) {
                    event.player.displayClientMessage(TCRCoreMod.getInfo("hit_barrier"), true);
                }
            }
            if (event.player instanceof ServerPlayer serverPlayer) {
                if (WorldUtils.inMainLand(serverPlayer) && serverPlayer.isCreative()) {
                    if (serverPlayer.isSprinting()) {
                        serverPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 2, false, false, true));
                    }
                }

            }

        }

    }

    public static boolean isNearBarrier(Player player) {
        if (player.noPhysics) {
            return false;
        } else {
            float f = player.getDimensions(player.getPose()).width * 1.5F;
            AABB aabb = AABB.ofSize(player.getEyePosition(), f, 1.0E-6D, f);
            return BlockPos.betweenClosedStream(aabb).anyMatch((p_201942_) -> {
                BlockState blockstate = player.level().getBlockState(p_201942_);
                return blockstate.is(Blocks.BARRIER);
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer serverPlayer) {
            TCRCapabilityProvider.syncPlayerDataToClient(serverPlayer);
            player.setHealth(player.getMaxHealth());
            EpicFightCapabilities.getUnparameterizedEntityPatch(player, ServerPlayerPatch.class).ifPresent(serverPlayerPatch -> {
                serverPlayerPatch.setStamina(serverPlayerPatch.getMaxStamina());
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerBreak(PlayerEvent.BreakSpeed event) {
        if (event.getEntity().isEyeInFluid(FluidTags.WATER) && !EnchantmentHelper.hasAquaAffinity(event.getEntity())) {
            event.setNewSpeed(event.getOriginalSpeed() * 5);
        }

        if (!event.getEntity().onGround()) {
            event.setNewSpeed(event.getOriginalSpeed() * 5);
        }
    }

    @SubscribeEvent
    public static void onPlayerTryToEnterDim(EntityTravelToDimensionEvent event) {

        //维度重置时候的保护
        if (event.getEntity().level() instanceof ServerLevel serverLevel && CataclysmDimensions.LEVELS.contains(event.getDimension())) {
            ServerLevel targetLevel = serverLevel.getServer().getLevel(event.getDimension());
            TCRDimSaveData dimSaveData = TCRDimSaveData.get(targetLevel);
            if (dimSaveData.isResetting() || CataclysmDimensionMod.RESOURCE_LOCATION_INTEGER_MAP.getOrDefault(targetLevel.dimension().location(), 0) > 0) {
                if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                    serverPlayer.displayClientMessage(TCRCoreMod.getInfo("dim_demending", dimSaveData.getResetCooldown() / 20), false);
                }
                event.setCanceled(true);
                return;
            }
            if (event.getEntity() instanceof ServerPlayer serverPlayer && serverPlayer.tickCount < 300) {
                event.setCanceled(true);
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("dim_demending", (300 - serverPlayer.tickCount) / 20), false);
            }
        }

        if (event.getEntity() instanceof ServerPlayer serverPlayer) {

            //允许创造和旁观进
            if (!serverPlayer.isCreative() && !serverPlayer.isSpectator()) {

                if (event.getDimension() == PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY) {
                    ServerLevel targetLevel = serverPlayer.server.getLevel(PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY);
                    if (targetLevel != null) {
                        if (!EntityUtils.hasAllowedPlayerCount(targetLevel)) {
                            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("dim_max_players"), true);
                            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("use_command_to_modify_limit", "/gamerule maxInfiniteSamsaraPlayerCount <count>").withStyle(ChatFormatting.RED), false);
                            serverPlayer.setGameMode(GameType.SPECTATOR);
                        }
                    }
                } else if (event.getDimension() == TCRDimensions.REAL_LEVEL_KEY) {
                    //卡在中间，只有击败最终boss才能进，后日谈完成后也不能进
                    if (TCRQuests.TALK_TO_AINE_GAME_CLEAR.isFinished(serverPlayer) || !TCRQuests.KILL_MAD_CHRONOS.isFinished(serverPlayer)) {
                        event.setCanceled(true);
                        serverPlayer.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                    }
                } else if (event.getDimension() == Level.NETHER) {
                    if (!(TCRQuests.GO_TO_NETHER.isFinished(serverPlayer) || TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_NETHER))) {
                        event.setCanceled(true);
                        serverPlayer.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                    }
                } else if (event.getDimension() == Level.END) {
                    if (!(TCRQuests.GO_TO_THE_END.isFinished(serverPlayer) || TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_THE_END))) {
                        event.setCanceled(true);
                        serverPlayer.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                    }
                } else if (event.getDimension() == AetherDimensions.AETHER_LEVEL) {
                    if (!(TCRQuests.GO_TO_AETHER.isFinished(serverPlayer) || TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_AETHER))) {
                        event.setCanceled(true);
                        serverPlayer.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                    }
                } else if (CataclysmDimensions.LEVELS.contains(event.getDimension())) {
                    ServerLevel targetLevel = serverPlayer.server.getLevel(event.getDimension());
                    if (targetLevel != null) {
                        if (!EntityUtils.hasAllowedPlayerCount(targetLevel)) {
                            event.setCanceled(true);
                            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("dim_max_players"), false);
                            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("use_command_to_modify_limit", "/gamerule maxInfiniteSamsaraPlayerCount <count>").withStyle(ChatFormatting.RED), false);
                        }
                    }

                    if ((event.getDimension() == CataclysmDimensions.CATACLYSM_SANCTUM_FALLEN_LEVEL_KEY && !PlayerDataManager.stormEyeGotten.get(serverPlayer))
                            || (event.getDimension() == CataclysmDimensions.CATACLYSM_INFERNOS_MAW_LEVEL_KEY && !PlayerDataManager.flameEyeGotten.get(serverPlayer))
                            || (event.getDimension() == CataclysmDimensions.CATACLYSM_ETERNAL_FROSTHOLD_LEVEL_KEY && !PlayerDataManager.cursedEyeGotten.get(serverPlayer))
                            || (event.getDimension() == CataclysmDimensions.CATACLYSM_PHARAOHS_BANE_LEVEL_KEY && !PlayerDataManager.desertEyeGotten.get(serverPlayer))
                            || (event.getDimension() == CataclysmDimensions.CATACLYSM_ABYSSAL_DEPTHS_LEVEL_KEY && !PlayerDataManager.abyssEyeGotten.get(serverPlayer))
                            || (event.getDimension() == CataclysmDimensions.CATACLYSM_SOULS_ANVIL_LEVEL_KEY && !PlayerDataManager.monstEyeGotten.get(serverPlayer))
                            || (event.getDimension() == CataclysmDimensions.CATACLYSM_BASTION_LOST_LEVEL_KEY && !PlayerDataManager.voidEyeGotten.get(serverPlayer))
                            || (event.getDimension() == CataclysmDimensions.CATACLYSM_FORGE_OF_AEONS_LEVEL_KEY && !PlayerDataManager.mechEyeGotten.get(serverPlayer))) {
                        serverPlayer.displayClientMessage(TCRCoreMod.getInfo("can_not_enter_before_finish"), false);
                        event.setCanceled(true);
                    }
                }
            }

        }
    }

    @SubscribeEvent
    public static void onPlayerEnteredDim(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            TCRCapabilityProvider.syncPlayerDataToClient(serverPlayer);
            if (event.getFrom() == WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY) {
                ServerLevel wraithonLevel = serverPlayer.server.getLevel(WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY);
                if (wraithonLevel != null && wraithonLevel.players().isEmpty()) {
                    EntityUtils.safelyClearAll(wraithonLevel);
                    TCRDimSaveData.get(wraithonLevel).setBossSummoned(false);
                }
            }
            if (CataclysmDimensions.LEVELS.contains(event.getTo())) {
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("reset_when_no_player").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
                if (serverPlayer.serverLevel().players().size() <= 1) {
                    TCRDimSaveData.get(serverPlayer.getServer().getLevel(event.getTo())).setBossKilled(false);
                }
                //和安聊聊幻境
                if (!TCRQuests.TALK_TO_AINE_CLOUDLAND.isFinished(serverPlayer) && !TCRQuestManager.hasQuest(serverPlayer, TCRQuests.TALK_TO_AINE_CLOUDLAND)) {
                    TCRQuests.TALK_TO_AINE_CLOUDLAND.start(serverPlayer);
                    PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new OpenCustomDialogPacket(OpenCustomDialogPacket.FIRST_ENTER_CLOUDLAND), serverPlayer);
                }
            }
            //处理使用共鸣石任务
            if (event.getTo().equals(Level.OVERWORLD)) {
                if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_OVERWORLD_OCEAN)) {
                    TCRQuests.GO_TO_OVERWORLD_OCEAN.finish(serverPlayer, true);
                    TCRQuests.USE_OCEAN_RESONANCE_STONE.start(serverPlayer);
                }
                if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_OVERWORLD_CURSED)) {
                    TCRQuests.GO_TO_OVERWORLD_CURSED.finish(serverPlayer, true);
                    TCRQuests.USE_CURSED_RESONANCE_STONE.start(serverPlayer);
                }
                if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_OVERWORLD_CORE)) {
                    TCRQuests.GO_TO_OVERWORLD_CORE.finish(serverPlayer, true);
                    TCRQuests.USE_CORE_RESONANCE_STONE.start(serverPlayer);
                }
                if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_OVERWORLD_END)) {
                    TCRQuests.GO_TO_OVERWORLD_END.finish(serverPlayer, true);
                    TCRQuests.USE_END_RESONANCE_STONE.start(serverPlayer);
                }
            }
            if (event.getTo().equals(Level.NETHER)) {
                if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_NETHER)) {
                    TCRQuests.GO_TO_NETHER.finish(serverPlayer, true);
                    TCRQuests.USE_NETHER_RESONANCE_STONE.start(serverPlayer);
                }
            }
            if (event.getTo().equals(AetherDimensions.AETHER_LEVEL)) {
                if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_AETHER)) {
                    TCRQuests.GO_TO_AETHER.finish(serverPlayer, true);
                    TCRQuests.USE_AETHER_RESONANCE_STONE.start(serverPlayer);
                }
            }
            if (event.getTo().equals(Level.END)) {
                if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_THE_END)) {
                    TCRQuests.GO_TO_THE_END.finish(serverPlayer, true);
                    TCRQuests.GET_VOID_EYE.start(serverPlayer);
                    ServerLevel end = serverPlayer.server.getLevel(Level.END);
                    if (end != null) {
                        IDragonFight dragonFight = (IDragonFight) end.getDragonFight();
                        if (dragonFight != null) {
                            dragonFight.betterendisland$reset(true);
                            //移除末地傀儡和末地假傀儡和石碑
                            List<EndGolem> list1 = List.copyOf(end.getEntities(BTEntityType.END_GOLEM.get(), LivingEntity::isAlive));
                            list1.forEach(Entity::discard);
                            List<FakeEndGolem> list2 = List.copyOf(end.getEntities(TCREntities.FAKE_END_GOLEM.get(), LivingEntity::isAlive));
                            list2.forEach(Entity::discard);
                            List<Entity> list3 = List.copyOf(end.getEntities(BTEntityType.END_MONOLITH.get(), Entity::isAlive));
                            list3.forEach(Entity::discard);
                        }
                    }
                }
            }
            if (event.getTo().equals(PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY)) {
                if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.GO_TO_SAMSARA)) {
                    TCRQuests.GO_TO_SAMSARA.finish(serverPlayer, true);
                }
            }

            //维度没有人就重制末影龙，方便多人
            if (event.getFrom().equals(Level.END)) {
                ServerLevel end = serverPlayer.server.getLevel(Level.END);
                if (end != null && end.players().isEmpty()) {
                    IDragonFight dragonFight = (IDragonFight) end.getDragonFight();
                    if (dragonFight != null) {
                        dragonFight.betterendisland$reset(true);
                        //移除末地傀儡和末地假傀儡和石碑
                        List<EndGolem> list1 = List.copyOf(end.getEntities(BTEntityType.END_GOLEM.get(), LivingEntity::isAlive));
                        list1.forEach(Entity::discard);
                        List<FakeEndGolem> list2 = List.copyOf(end.getEntities(TCREntities.FAKE_END_GOLEM.get(), LivingEntity::isAlive));
                        list2.forEach(Entity::discard);
                        List<Entity> list3 = List.copyOf(end.getEntities(BTEntityType.END_MONOLITH.get(), Entity::isAlive));
                        list3.forEach(Entity::discard);
                    }
                }
            }

            //摆床
            if (event.getTo().equals(TCRDimensions.REAL_LEVEL_KEY)) {

                if (!serverPlayer.serverLevel().getBlockState(new BlockPos(WorldUtils.BED_POS)).is(Blocks.WHITE_BED)) {
                    serverPlayer.serverLevel().setBlockAndUpdate(new BlockPos(WorldUtils.BED_POS).east(), Blocks.WHITE_BED.defaultBlockState().setValue(BlockStateProperties.BED_PART, BedPart.HEAD).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST));
                    serverPlayer.serverLevel().setBlockAndUpdate(new BlockPos(WorldUtils.BED_POS), Blocks.WHITE_BED.defaultBlockState().setValue(BlockStateProperties.BED_PART, BedPart.FOOT).setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST));
                }

                ServerLevel realLevel = serverPlayer.server.getLevel(TCRDimensions.REAL_LEVEL_KEY);
                if (realLevel != null && !TCRDimSaveData.get(realLevel).isBossSummoned()) {

                    double[] angles = {
                            0, Math.PI / 4, Math.PI / 2, 3 * Math.PI / 4,
                            Math.PI, 5 * Math.PI / 4, 3 * Math.PI / 2, 7 * Math.PI / 4
                    };

                    List<EntityType<FakeBossNpc>> entityTypes = Arrays.asList(
                            TCREntities.FAKE_MALEDICTUS_HUMANOID.get(),
                            TCREntities.FAKE_IGNIS_HUMANOID.get(),
                            TCREntities.FAKE_NETHERITE_HUMANOID.get(),
                            TCREntities.FAKE_SCYLLA_HUMANOID.get(),
                            TCREntities.FAKE_ENDER_GUARDIAN_HUMANOID.get(),
                            TCREntities.FAKE_HARBINGER_HUMANOID.get(),
                            TCREntities.FAKE_LEVIATHAN_HUMANOID.get(),
                            TCREntities.FAKE_ANCIENT_REMNANT_HUMANOID.get()
                    );

                    for (int i = 0; i < 8; i++) {
                        double angle = angles[i];
                        double distance = 5 + realLevel.random.nextInt(4);
                        double dx = distance * Math.cos(angle);
                        double dz = distance * Math.sin(angle);

                        FakeBossNpc fakeBossNpc = entityTypes.get(i).create(realLevel);
                        if (fakeBossNpc != null) {
                            fakeBossNpc.setPos(WorldUtils.BED_POS.getX() + dx, WorldUtils.BED_POS.getY(), WorldUtils.BED_POS.getZ() + dz);
                            Vec3 dir = new BlockPos(WorldUtils.BED_POS).getCenter().subtract(fakeBossNpc.position());
                            float yRot = (float) MathUtils.getYRotOfVector(dir);
                            fakeBossNpc.setYRot(yRot);
                            fakeBossNpc.setYBodyRot(yRot);
                            fakeBossNpc.getLookControl().setLookAt(WorldUtils.BED_POS.getX(), WorldUtils.BED_POS.getY() + 2, WorldUtils.BED_POS.getZ());
                            realLevel.addFreshEntity(fakeBossNpc);
                        }
                    }
                    TCRDimSaveData.get(realLevel).setBossSummoned(true);
                }
            }

            //离开后后日谈
            //无人看懂的艺术，删了吧ToT
//            if (event.getFrom().equals(TCRDimensions.REAL_LEVEL_KEY)) {
//                if (!TCRQuestManager.hasFinished(serverPlayer, TCRQuests.TALK_TO_AINE_GAME_CLEAR) && TCRQuestManager.hasFinished(serverPlayer, TCRQuests.KILL_MAD_CHRONOS)) {
//                    TCRQuests.TALK_TO_AINE_GAME_CLEAR.start(serverPlayer);
//                }
//            }
            if(event.getFrom().equals(PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY)) {
                if(serverPlayer.isSpectator()) {
                    serverPlayer.setGameMode(GameType.SPECTATOR);
                }
            }
//            updateHealth(serverPlayer, event.getFrom());
//            updateHealth(serverPlayer, event.getTo());
        }
    }

    /**
     * 动态改变多人血量，不用了，改用单独模组直接全局改之
     */
    public static void updateHealth(ServerPlayer serverPlayer, ResourceKey<Level> levelResourceKey) {
        if (CataclysmDimensions.LEVELS.contains(levelResourceKey)) {
            ServerLevel targetLevel = serverPlayer.server.getLevel(levelResourceKey);
            if (targetLevel != null) {
                int playerCnt = targetLevel.players().size();
                double healthMultiplier = 1.0;
                if (playerCnt == 2) {
                    healthMultiplier = 1.6;
                } else if (playerCnt == 3) {
                    healthMultiplier = 2.0;
                } else if (playerCnt >= 4) {
                    healthMultiplier = 2.4;
                }

                final double finalMultiplier = healthMultiplier;
                final UUID HEALTH_MODIFIER_UUID = UUID.fromString("11451419-1981-0234-1234-123456789abc");

                targetLevel.getAllEntities().forEach(entity -> {
                    if (entity instanceof LivingEntity living && living.isAlive() && !(living instanceof Player)) {
                        float preHealth = living.getHealth();
                        float preMaxHealth = living.getMaxHealth();
                        AttributeInstance maxHealthAttr = living.getAttribute(Attributes.MAX_HEALTH);
                        if (maxHealthAttr != null) {
                            maxHealthAttr.removeModifier(HEALTH_MODIFIER_UUID);
                            if (playerCnt > 1) {
                                AttributeModifier healthModifier = new AttributeModifier(
                                        HEALTH_MODIFIER_UUID,
                                        "team_health_boost",
                                        finalMultiplier - 1,
                                        AttributeModifier.Operation.MULTIPLY_TOTAL
                                );
                                maxHealthAttr.addPermanentModifier(healthModifier);
                                living.setHealth(preHealth * living.getMaxHealth() / preMaxHealth);
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerReadyPickupItem(EntityItemPickupEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            //未完成过前置时则不能捡起，防止多人游戏捡了别人的眼睛，进度直接乱了
            if (!TCRQuests.USE_LAND_RESONANCE_STONE.isFinished(player) && event.getItem().getItem().is(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get())) {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                event.setCanceled(true);
            }
            if (!TCRQuests.USE_OCEAN_RESONANCE_STONE.isFinished(player) && event.getItem().getItem().is(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get())) {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                event.setCanceled(true);
            }
            if (!TCRQuests.USE_CURSED_RESONANCE_STONE.isFinished(player) && event.getItem().getItem().is(com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get())) {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                event.setCanceled(true);
            }
            if (!TCRQuests.USE_CORE_RESONANCE_STONE.isFinished(player) && event.getItem().getItem().is(ModItems.FLAME_EYE.get())) {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                event.setCanceled(true);
            }
            if (!TCRQuests.USE_NETHER_RESONANCE_STONE.isFinished(player) && event.getItem().getItem().is(ModItems.MONSTROUS_EYE.get())) {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                event.setCanceled(true);
            }
            if (!(TCRQuests.GET_WITHER_EYE.isFinished(player) || TCRQuestManager.hasQuest(player, TCRQuests.GET_WITHER_EYE)) && event.getItem().getItem().is(ModItems.MECH_EYE.get())) {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                event.setCanceled(true);
            }
            if (!TCRQuests.TALK_TO_SKY_GOLEM.isFinished(player) && event.getItem().getItem().is(ModItems.STORM_EYE.get())) {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                event.setCanceled(true);
            }
        }

    }

    /**
     * 开箱的时候删非法物品，省得出现拿到了用不了= =
     */
    @SubscribeEvent
    public static void onPlayerOpenContainer(PlayerContainerEvent.Open event) {
        if(event.getContainer() instanceof DimensionsNetMenu) {
            return;
        }
        for (int i = 0; i < event.getContainer().slots.size(); i++) {
            ItemStack itemStack = event.getContainer().getItems().get(i);
            if(itemStack.is(LeonidasItems.THE_LOST_HELMET.get()) && event.getEntity() instanceof ServerPlayer serverPlayer) {
                if(!TCRQuests.FIND_HELMET_IN_OCEAN_MONUMENT.isFinished(serverPlayer)) {
                    TCRQuests.FIND_HELMET_IN_OCEAN_MONUMENT.finish(serverPlayer, true);
                }
            }
            if (illegalItems.contains(itemStack.getItem())) {
                itemStack.setCount(0);
            }
        }
    }

    /**
     * 捡起物品后才视为成功获取，才爆特效，而非用击杀判定
     * {@link LivingEntityEventListeners#onLivingDeath(LivingDeathEvent)}
     */
    @SubscribeEvent
    public static void onItemPickedUp(PlayerEvent.ItemPickupEvent event) {
        ItemStack itemStack = event.getStack();
        if (event.getEntity() instanceof ServerPlayer player) {

            if(itemStack.is(TCRItems.PROOF_OF_BOSS_RUSH.get())) {
                giveOracleEffect(player, TCRItems.PROOF_OF_BOSS_RUSH.get());
            }

            if(itemStack.is(LeonidasItems.THE_LOST_HELMET.get()) && TCRQuestManager.hasQuest(player, TCRQuests.FIND_HELMET_IN_OCEAN_MONUMENT) && !TCRQuests.FIND_HELMET_IN_OCEAN_MONUMENT.isFinished(player)) {
                TCRQuests.FIND_HELMET_IN_OCEAN_MONUMENT.finish(player, true);
            }

            //持有任务时捡起来才推进进度
            if (TCRQuestManager.hasQuest(player, TCRQuests.GET_DESERT_EYE) && itemStack.is(ModItems.DESERT_EYE.get())) {
                giveOracleEffect(player, ModItems.DESERT_EYE.get());
                PlayerDataManager.desertEyeGotten.put(player, true);
                //完成收回眼睛的任务
                TCRQuests.GET_DESERT_EYE.finish(player, true);
                TCRQuests.TALK_TO_CHRONOS_1.start(player);
            }
            if (TCRQuestManager.hasQuest(player, TCRQuests.GET_OCEAN_EYE) && itemStack.is(ModItems.ABYSS_EYE.get())) {
                giveOracleEffect(player, ModItems.ABYSS_EYE.get());
                PlayerDataManager.abyssEyeGotten.put(player, true);
                TCRQuests.GET_OCEAN_EYE.finish(player, true);
                //点神像和点祭坛
                if (!PlayerDataManager.abyssEyeActivated.get(player)) {
                    TCRQuests.PUT_ABYSS_EYE_ON_ALTAR.start(player, false);
                }
                if (!PlayerDataManager.abyssEyeBlessed.get(player)) {
                    TCRQuests.BLESS_ON_THE_GODNESS_STATUE.start(player, false);
                }
                TCRQuests.TALK_TO_CHRONOS_3.start(player);
            }
            if (TCRQuestManager.hasQuest(player, TCRQuests.GET_CURSED_EYE) && itemStack.is(ModItems.CURSED_EYE.get())) {
                giveOracleEffect(player, ModItems.CURSED_EYE.get());
                PlayerDataManager.cursedEyeGotten.put(player, true);
                TCRQuests.GET_CURSED_EYE.finish(player, true);
                if (!PlayerDataManager.cursedEyeActivated.get(player)) {
                    TCRQuests.PUT_CURSED_EYE_ON_ALTAR.start(player);
                }
                if (!PlayerDataManager.cursedEyeBlessed.get(player)) {
                    TCRQuests.BLESS_ON_THE_GODNESS_STATUE.start(player);
                }
                TCRQuests.TALK_TO_CHRONOS_5.start(player);
                TCRQuests.TALK_TO_AINE_MAGIC.start(player);
            }
            if (TCRQuestManager.hasQuest(player, TCRQuests.GET_FLAME_EYE) && itemStack.is(ModItems.FLAME_EYE.get())) {
                giveOracleEffect(player, ModItems.FLAME_EYE.get());
                PlayerDataManager.flameEyeGotten.put(player, true);
                TCRQuests.GET_FLAME_EYE.finish(player, true);
                if (!PlayerDataManager.flameEyeActivated.get(player)) {
                    TCRQuests.PUT_FLAME_EYE_ON_ALTAR.start(player);
                }
                if (!PlayerDataManager.flameEyeBlessed.get(player)) {
                    TCRQuests.BLESS_ON_THE_GODNESS_STATUE.start(player);
                }
                TCRQuests.TALK_TO_AINE_1.start(player);
                TCRQuests.TALK_TO_CHRONOS_7.start(player);
            }
            if (TCRQuestManager.hasQuest(player, TCRQuests.GET_MONST_EYE) && itemStack.is(ModItems.MONSTROUS_EYE.get())) {
                giveOracleEffect(player, ModItems.MONSTROUS_EYE.get());
                PlayerDataManager.monstEyeGotten.put(player, true);
                TCRQuests.GET_MONST_EYE.finish(player, true);
                if (!PlayerDataManager.monstEyeActivated.get(player)) {
                    TCRQuests.PUT_MONST_EYE_ON_ALTAR.start(player);
                }
                if (!PlayerDataManager.monstEyeBlessed.get(player)) {
                    TCRQuests.BLESS_ON_THE_GODNESS_STATUE.start(player);
                }
                TCRQuests.TALK_TO_CHRONOS_9.start(player);
            }
            if (TCRQuestManager.hasQuest(player, TCRQuests.GET_WITHER_EYE) && itemStack.is(ModItems.MECH_EYE.get())) {
                giveOracleEffect(player, ModItems.MECH_EYE.get());
                PlayerDataManager.mechEyeGotten.put(player, true);
                TCRQuests.GET_WITHER_EYE.finish(player, true);
                if (!PlayerDataManager.mechEyeActivated.get(player)) {
                    TCRQuests.PUT_MECH_EYE_ON_ALTAR.start(player);
                }
                if (!PlayerDataManager.mechEyeBlessed.get(player)) {
                    TCRQuests.BLESS_ON_THE_GODNESS_STATUE.start(player);
                }
                TCRQuests.TALK_TO_CHRONOS_10.start(player);
            }

            if (TCRQuestManager.hasQuest(player, TCRQuests.GET_STORM_EYE) && itemStack.is(ModItems.STORM_EYE.get())) {
                giveOracleEffect(player, ModItems.STORM_EYE.get());
                PlayerDataManager.stormEyeGotten.put(player, true);
                TCRQuests.GET_STORM_EYE.finish(player, true);
                if (!PlayerDataManager.stormEyeActivated.get(player)) {
                    TCRQuests.PUT_STORM_EYE_ON_ALTAR.start(player);
                }
                if (!PlayerDataManager.stormEyeBlessed.get(player)) {
                    TCRQuests.BLESS_ON_THE_GODNESS_STATUE.start(player);
                }
                TCRQuests.TALK_TO_AINE_2.start(player);
                TCRQuests.TALK_TO_CHRONOS_12.start(player);
            }

            if (TCRQuestManager.hasQuest(player, TCRQuests.GET_VOID_EYE) && itemStack.is(ModItems.VOID_EYE.get())) {
                giveOracleEffect(player, ModItems.VOID_EYE.get());
                PlayerDataManager.voidEyeGotten.put(player, true);
                TCRQuests.GET_VOID_EYE.finish(player, true);
                if (!PlayerDataManager.voidEyeActivated.get(player)) {
                    TCRQuests.PUT_VOID_EYE_ON_ALTAR.start(player);
                }
                if (!PlayerDataManager.voidEyeBlessed.get(player)) {
                    TCRQuests.BLESS_ON_THE_GODNESS_STATUE.start(player);
                }
                TCRQuests.TALK_TO_CHRONOS_END.start(player);
            }

            //捡起百兵图后
            if (itemStack.is(TCRItems.MYSTERIOUS_WEAPONS.get()) && !TCRQuestManager.hasFinished(player, TCRQuests.TALK_TO_ORNN_1)) {
                giveOracleEffect(player, TCRItems.MYSTERIOUS_WEAPONS.get());
            }

        }

    }

    public static void giveOracleEffect(ServerPlayer player, Item toDisplay) {
        PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PlayItemPickupParticlePacket(toDisplay.getDefaultInstance()), player);
        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.TOTEM_USE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
    }

}
