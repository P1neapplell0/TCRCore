package com.p1nero.tcrcore.events;

import com.aetherteam.aether.entity.monster.dungeon.Mimic;
import com.aetherteam.aether.entity.monster.dungeon.Valkyrie;
import com.brass_amber.ba_bt.block.block.BTChestBlock;
import com.brass_amber.ba_bt.entity.block.BTMonolith;
import com.brass_amber.ba_bt.entity.hostile.golem.*;
import com.brass_amber.ba_bt.init.BTEntityType;
import com.brass_amber.ba_bt.init.BTExtras;
import com.brass_amber.ba_bt.init.BTItems;
import com.brass_amber.ba_bt.util.GolemType;
import com.cerbon.bosses_of_mass_destruction.item.BMDItems;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.The_Leviathan_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Ancient_Remnant.Ancient_Remnant_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Maledictus.Maledictus_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.NewNetherite_Monstrosity.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Scylla.Scylla_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.Kobolediator_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.The_Prowler_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.Wadjet_Entity;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.dodo.dodosmobs.entity.InternalAnimationMonster.IABossMonsters.Bone_Chimera_Entity;
import com.github.dodo.dodosmobs.init.ModEntities;
import com.hm.efn.registries.EFNItem;
import com.hm.efn.registries.EFNMobEffectRegistry;
import com.nameless.indestructible.world.capability.AdvancedCustomHumanoidMobPatch;
import com.p1nero.battle_field1.PBF1Mod;
import com.p1nero.battle_field1.worldgen.PBF1Dimensions;
import com.p1nero.bountiful_npc.villager.BountifulVillagers;
import com.p1nero.cataclysm_dimension.worldgen.CataclysmDimensions;
import com.p1nero.entityrespawner.EntityRespawnerMod;
import com.p1nero.entityrespawner.entity.SoulEntity;
import com.p1nero.p1nero_ec.capability.PECDataManager;
import com.p1nero.tcr_bosses.entity.cataclysm.BaseBossEntity;
import com.p1nero.tcr_bosses.entity.cataclysm.ancient_remnant.AncientRemnantHumanoid;
import com.p1nero.tcr_bosses.entity.cataclysm.ender_gardian.EnderGuardianHumanoid;
import com.p1nero.tcr_bosses.entity.cataclysm.harbinger.HarbingerHumanoid;
import com.p1nero.tcr_bosses.entity.cataclysm.ignis.IgnisHumanoid;
import com.p1nero.tcr_bosses.entity.cataclysm.leviathan.LeviathanHumanoid;
import com.p1nero.tcr_bosses.entity.cataclysm.maledictus.MaledictusHumanoid;
import com.p1nero.tcr_bosses.entity.cataclysm.netherite.NetheriteHumanoid;
import com.p1nero.tcr_bosses.entity.cataclysm.scylla.ScyllaHumanoid;
import com.p1nero.tcr_bosses.entity.custom.BaseSmallBossEntity;
import com.p1nero.tcr_bosses.entity.custom.aether.gilded_hunter.GildedHunterEntity;
import com.p1nero.tcr_bosses.entity.custom.aether.valkyrie.ValkyrieQueenEntity;
import com.p1nero.tcr_bosses.entity.custom.nether.evening_ghost.EveningGhostEntity;
import com.p1nero.tcr_bosses.entity.custom.nether.golden_executor.GoldenExecutorEntity;
import com.p1nero.tcr_bosses.mixins.AbstractGolemInvoker;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.*;
import com.p1nero.tcrcore.client.sound.WraithonMusicPlayer;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_end_golem.FakeEndGolem;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_sky_golem.FakeSkyGolem;
import com.p1nero.tcrcore.entity.custom.mimic.TCRMimic;
import com.p1nero.tcrcore.entity.custom.tutorial_golem.TutorialGolem;
import com.p1nero.tcrcore.entity.custom.tutorial_humanoid.TutorialHumanoid;
import com.p1nero.tcrcore.gameassets.TCRSkills;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.item.custom.DragonFluteItem;
import com.p1nero.tcrcore.save_data.TCRDimSaveData;
import com.p1nero.tcrcore.save_data.TCRMainLevelSaveData;
import com.p1nero.tcrcore.utils.EntityUtils;
import com.p1nero.tcrcore.utils.FTBTeamUtils;
import com.p1nero.tcrcore.utils.ItemUtils;
import com.p1nero.tcrcore.utils.WorldUtils;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import com.yesman.epicskills.registry.entry.EpicSkillsItems;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.genzyuro.uniqueaccessories.registry.UAItems;
import net.kenddie.fantasyarmor.item.FAItems;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.mehvahdjukaar.dummmmmmy.network.ClientBoundDamageNumberMessage;
import net.mehvahdjukaar.dummmmmmy.network.NetworkHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.shelmarow.combat_evolution.ai.iml.ILivingEntityData;
import net.shelmarow.combat_evolution.ai.util.CEPatchUtils;
import org.jetbrains.annotations.Nullable;
import org.merlin204.leonidas.entity.LeonidasEntity;
import org.merlin204.wraithon.entity.wraithon.WraithonEntity;
import org.merlin204.wraithon.worldgen.WraithonDimensions;
import reascer.wom.main.WeaponsOfMinecraft;
import reascer.wom.world.entity.mob.EvilSkeleton;
import reascer.wom.world.entity.mob.Hollow;
import reascer.wom.world.entity.mob.Lycanth;
import reascer.wom.world.entity.mob.Saulomonk;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.client.input.EpicFightKeyMappings;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.EntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.EpicFightDamageSource;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.effect.EpicFightMobEffects;

import java.util.*;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID)
public class LivingEntityEventListeners {

    public static final String TRIGGERED = "death_triggered";

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        Entity attacker = event.getSource().getEntity();
        //禁用原版攻击
        if (attacker instanceof Player player && player.level().isClientSide) {
            if (player.isLocalPlayer()) {
                EpicFightCapabilities.getUnparameterizedEntityPatch(player, PlayerPatch.class).ifPresent(playerPatch -> {
                    if (playerPatch.isVanillaMode()) {
                        event.setCanceled(true);
                        player.displayClientMessage(TCRCoreMod.getInfo("press_to_open_battle_mode", EpicFightKeyMappings.SWITCH_MODE.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.RED), true);
                    }
                });
            }
        }

        if (attacker instanceof ServerPlayer serverPlayer) {
            EpicFightCapabilities.getUnparameterizedEntityPatch(serverPlayer, PlayerPatch.class).ifPresent(playerPatch -> {
                if (playerPatch.isVanillaMode()) {
                    event.setCanceled(true);
                }
            });
        }

    }

    @SubscribeEvent
    public static void onEntityDrop(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide) {
            if (entity instanceof IronGolem ironGolem && WorldUtils.isInStructure(ironGolem, WorldUtils.SKY_GOLEM)) {
                ItemUtils.addItemEntity(entity, TCRItems.DIVINE_FRAGMENT.get(), 1, ChatFormatting.AQUA.getColor());
                event.setCanceled(true);
                return;
            }

            if (entity instanceof Pillager) {
                if (entity.getRandom().nextFloat() < 0.2F) {
                    ItemUtils.addItemEntity(entity, Items.GOLD_INGOT, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.1F) {
                    ItemUtils.addItemEntity(entity, Items.DIAMOND, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.01F) {
                    ItemUtils.addItemEntity(entity, Items.NETHERITE_INGOT, 1, 0xc000ff);
                }
            } else if (entity instanceof Enemy) {
                if (entity.getRandom().nextFloat() < 0.1F) {
                    ItemUtils.addItemEntity(entity, Items.IRON_INGOT, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.03F) {
                    ItemUtils.addItemEntity(entity, Items.AMETHYST_SHARD, 1, 0xc000ff);
                } else if (entity.getRandom().nextFloat() < 0.01F) {
                    ItemUtils.addItemEntity(entity, FAItems.MOON_CRYSTAL.get(), 1, 0xc000ff);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (event.isCanceled() || livingEntity.getPersistentData().getBoolean(TRIGGERED)) {
            return;
        }
        //防止重复刷
        livingEntity.getPersistentData().putBoolean(TRIGGERED, true);
        Vec3 center = livingEntity.position();

        //只处理boss
        if (livingEntity.getType().is(Tags.EntityTypes.BOSSES) && !livingEntity.level().isClientSide) {

            //附近玩家都处理击败
            livingEntity.level().getEntitiesOfClass(ServerPlayer.class, (new AABB(center, center)).inflate(30)).forEach(player -> {
                ServerLevel serverLevel = player.serverLevel();
                if (player.isSpectator() || player.isCreative()) {
                    player.displayClientMessage(TCRCoreMod.getInfo("creative_may_lost_progress").withStyle(ChatFormatting.RED), false);
                    return;
                }
                player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                //击败祭坛内的boss
                if (livingEntity instanceof Scylla_Entity) {
                    if (!PlayerDataManager.stormEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.stormEyeKilled.put(player, true);
                        TCRQuests.KILL_STORM_EYE.finish(player, true);
                    }
                    ItemUtils.addItemEntity(player, ItemRegistry.LIGHTNING_BOTTLE.get(), 4, ChatFormatting.GOLD.getColor().intValue());
                } else if (livingEntity instanceof Ignis_Entity) {
                    if (!PlayerDataManager.flameEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.flameEyeKilled.put(player, true);
                        TCRQuests.KILL_FLAME_EYE.finish(player, true);
                    }
                    ItemUtils.addItemEntity(player, Items.BLAZE_ROD, 4, ChatFormatting.GOLD.getColor().intValue());
                } else if (livingEntity instanceof The_Leviathan_Entity) {
                    if (!PlayerDataManager.abyssEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.abyssEyeKilled.put(player, true);
                        TCRQuests.KILL_ABYSS_EYE.finish(player, true);
                    }
                    ItemUtils.addItemEntity(player, Items.ECHO_SHARD, 6, ChatFormatting.GOLD.getColor().intValue());
                } else if (livingEntity instanceof Maledictus_Entity) {
                    if (!PlayerDataManager.cursedEyeKilled.get(player)) {
                        givePlayerAward(player, 2, TCRItems.DUAL_BOKKEN.get());
                        PlayerDataManager.cursedEyeKilled.put(player, true);
                        TCRQuests.KILL_CURSED_EYE.finish(player, true);
                    }
                    ItemUtils.addItemEntity(player, ItemRegistry.FROZEN_BONE_SHARD.get(), 6, ChatFormatting.GOLD.getColor().intValue());
                    ItemUtils.addItemEntity(player, BMDItems.SOUL_STAR.get(), 2, ChatFormatting.GOLD.getColor().intValue());
                } else if (livingEntity instanceof Ancient_Remnant_Entity) {
                    if (!PlayerDataManager.desertEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.desertEyeKilled.put(player, true);
                        TCRQuests.KILL_DESERT_EYE.finish(player, true);
                    }
                } else if (livingEntity instanceof Ender_Guardian_Entity) {
                    if (!PlayerDataManager.voidEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.voidEyeKilled.put(player, true);
                        TCRQuests.KILL_VOID_EYE.finish(player, true);
                    }
                    ItemUtils.addItemEntity(player, Items.ENDER_EYE, 4, ChatFormatting.GOLD.getColor().intValue());
                    ItemUtils.addItemEntity(player, Items.SHULKER_SHELL, 5, ChatFormatting.GOLD.getColor().intValue());
                } else if (livingEntity instanceof Netherite_Monstrosity_Entity) {
                    if (!PlayerDataManager.monstEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.monstEyeKilled.put(player, true);
                        TCRQuests.KILL_MONST_EYE.finish(player, true);
                    }
                    ItemUtils.addItemEntity(player, Items.NETHERITE_INGOT, 1, ChatFormatting.GOLD.getColor().intValue());
                } else if (livingEntity instanceof The_Harbinger_Entity) {
                    if (!PlayerDataManager.mechEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.mechEyeKilled.put(player, true);
                        TCRQuests.KILL_MECH_EYE.finish(player, true);
                    }
                    ItemUtils.addItemEntity(player, Items.NETHER_STAR, 1, ChatFormatting.GOLD.getColor().intValue());
                }

                //处理主线的boss掉落眼睛
                else if (livingEntity instanceof LandGolem) {
                    //有任务才会掉
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_DESERT_EYE)) {
                        givePlayerAward(player, 1, serverPlayer -> {
                            if (TCRMainLevelSaveData.get(serverPlayer.serverLevel()).isHardDifficulty()) {
                                ItemUtils.addItemEntity(serverPlayer, TCRItems.ARTIFACT_TICKET.get(), 1);
                            }
                        }, false);
                        ItemUtils.addItemEntity(player, ModItems.DESERT_EYE.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
                    }
                    ItemUtils.addItemEntity(player, ItemRegistry.FIREFLY_JAR_ITEM.get(), 3, ChatFormatting.YELLOW.getColor().intValue());
                    ItemUtils.addItemEntity(player, ItemRegistry.SHRIVING_STONE.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
                } else if (livingEntity instanceof OceanGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_OCEAN_EYE)) {
                        givePlayerAward(player, 1, serverPlayer -> {
                            if (TCRMainLevelSaveData.get(serverPlayer.serverLevel()).isHardDifficulty()) {
                                ItemUtils.addItemEntity(serverPlayer, Items.NAUTILUS_SHELL, 6);
                            }
                        }, false);
                        ItemUtils.addItemEntity(player, ModItems.ABYSS_EYE.get(), 1, ChatFormatting.BLUE.getColor().intValue());
                        ItemUtils.addItemEntity(player, ItemRegistry.ICY_FANG.get(), 1, ChatFormatting.BLUE.getColor().intValue());
                    }
                    ItemUtils.addItemEntity(player, Items.HEART_OF_THE_SEA, 1, ChatFormatting.AQUA.getColor().intValue());
                } else if (livingEntity instanceof LeonidasEntity) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_CURSED_EYE)) {
                        givePlayerAward(player, 1, false);
                        ItemUtils.addItemEntity(player, ModItems.CURSED_EYE.get(), 1, ChatFormatting.DARK_GREEN.getColor().intValue());
                        ItemUtils.addItemEntity(player, TCRItems.NECROMANCY_SCROLL.get(), 1, ChatFormatting.DARK_GREEN.getColor().intValue());
                    }
                } else if (livingEntity instanceof CoreGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_FLAME_EYE)) {
                        givePlayerAward(player, 1, serverPlayer -> {
                            if (TCRMainLevelSaveData.get(serverPlayer.serverLevel()).isHardDifficulty()) {
                                ItemUtils.addItemEntity(serverPlayer, UAItems.SUN_STONE.get(), 1);
                            }
                        }, false);
                        ItemUtils.addItemEntity(player, ModItems.FLAME_EYE.get(), 1, ChatFormatting.RED.getColor().intValue());
                    }
                    ItemUtils.addItemEntity(player, EFNItem.DUSKFIRE_INGOT.get(), 1, ChatFormatting.RED.getColor().intValue());
                    ItemUtils.addItemEntity(player, ItemRegistry.CINDER_ESSENCE.get(), 1, ChatFormatting.RED.getColor().intValue());
                } else if (livingEntity instanceof GoldenExecutorEntity) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_NETHER_MONOLITH_KEY_1)) {
                        TCRQuests.GET_NETHER_MONOLITH_KEY_1.finish(player, true);
                    }
                    if (TCRMainLevelSaveData.get(player.serverLevel()).isHardDifficulty()) {
                        ItemUtils.addItemEntity(player, ItemRegistry.BLOODY_VELLUM.get(), 1, ChatFormatting.DARK_RED.getColor().intValue());
                    }
                } else if (livingEntity instanceof EveningGhostEntity) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_NETHER_MONOLITH_KEY_2)) {
                        TCRQuests.GET_NETHER_MONOLITH_KEY_2.finish(player, true);
                    }
                    if (TCRMainLevelSaveData.get(player.serverLevel()).isHardDifficulty()) {
                        ItemUtils.addItemEntity(player, Items.NETHERITE_SCRAP, 3, ChatFormatting.DARK_RED.getColor().intValue());
                    }
                } else if (livingEntity instanceof NetherGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_MONST_EYE)) {
                        givePlayerAward(player, 1, serverPlayer -> {
                            if (TCRMainLevelSaveData.get(player.serverLevel()).isHardDifficulty()) {
                                ItemUtils.addItemEntity(player, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1, ChatFormatting.DARK_RED.getColor().intValue());
                            }
                        }, false);
                        ItemUtils.addItemEntity(player, ModItems.MONSTROUS_EYE.get(), 1, ChatFormatting.DARK_RED.getColor().intValue());
                    }
                    ItemUtils.addItemEntity(player, EFNItem.DUSKFIRE_INGOT.get(), 1, ChatFormatting.RED.getColor().intValue());
                } else if (livingEntity instanceof WitherBoss) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_WITHER_EYE)) {
                        givePlayerAward(player, 1, serverPlayer -> {
                            if (TCRMainLevelSaveData.get(serverPlayer.serverLevel()).isHardDifficulty()) {
                                ItemUtils.addItemEntity(serverPlayer, Items.WITHER_ROSE, 3, ChatFormatting.DARK_RED.getColor().intValue());
                            }
                            ItemUtils.addItemEntity(serverPlayer, TCRItems.WITHER_SOUL_STONE.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                        }, false);
                        ItemUtils.addItemEntity(player, ModItems.MECH_EYE.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                    }
                    ItemUtils.addItemEntity(player, Items.WITHER_ROSE, 1, ChatFormatting.DARK_RED.getColor().intValue());
                } else if (livingEntity instanceof ValkyrieQueenEntity) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_AETHER_MONOLITH_KEY_1)) {
                        TCRQuests.GET_AETHER_MONOLITH_KEY_1.finish(player, true);
                    }
                    if (TCRMainLevelSaveData.get(player.serverLevel()).isHardDifficulty()) {
                        ItemUtils.addItemEntity(player, ItemRegistry.LIGHTNING_BOTTLE.get(), 2, ChatFormatting.AQUA.getColor().intValue());
                    }
                } else if (livingEntity instanceof GildedHunterEntity) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_AETHER_MONOLITH_KEY_2)) {
                        TCRQuests.GET_AETHER_MONOLITH_KEY_2.finish(player, true);
                    }
                    if (TCRMainLevelSaveData.get(player.serverLevel()).isHardDifficulty()) {
                        ItemUtils.addItemEntity(player, Items.DIAMOND, 5, ChatFormatting.AQUA.getColor().intValue());
                    }
                } else if (livingEntity instanceof SkyGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_STORM_EYE)) {
                        if(!serverLevel.getEntities(TCREntities.FAKE_SKY_GOLEM.get(),
                                skyGolem -> skyGolem.getOwnerUUID() == player.getUUID()).isEmpty()) {
                            //已经生了就不能重复刷
                            return;
                        }
                        givePlayerAward(player, 1, serverPlayer -> {
                            if (TCRMainLevelSaveData.get(serverPlayer.serverLevel()).isHardDifficulty()) {
                                ItemUtils.addItemEntity(serverPlayer, TCRItems.RARE_ARTIFACT_TICKET.get(), 1);
                            }
                        }, false);
                        FakeSkyGolem fakeSkyGolem = new FakeSkyGolem(player);
                        fakeSkyGolem.setPos(player.position());
                        fakeSkyGolem.setGlowingTag(true);
                        player.serverLevel().addFreshEntity(fakeSkyGolem);
                        TCRQuests.TALK_TO_SKY_GOLEM.start(player);
                    }
                    ItemUtils.addItemEntity(player, ItemRegistry.DIVINE_PEARL.get(), 3, ChatFormatting.GOLD.getColor().intValue());
                    ItemUtils.addItemEntity(player, ItemRegistry.SHRIVING_STONE.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                } else if (livingEntity instanceof EndGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_VOID_EYE)) {
                        if(!serverLevel.getEntities(TCREntities.FAKE_END_GOLEM.get(),
                                fakeEndGolem -> fakeEndGolem.getOwnerUUID() == player.getUUID()).isEmpty()) {
                            //已经生了就不能重复刷
                            return;
                        }
                        givePlayerAward(player, 2, serverPlayer -> {
                            if (TCRMainLevelSaveData.get(serverPlayer.serverLevel()).isHardDifficulty()) {
                                ItemUtils.addItemEntity(serverPlayer, UAItems.HERO_EMBLEM.get(), 1);
                            }
                        }, false);
                        FakeEndGolem fakeEndGolem = new FakeEndGolem(player);
                        fakeEndGolem.setPos(player.position().add(0, 3, 0));
                        fakeEndGolem.setGlowingTag(true);
                        player.serverLevel().addFreshEntity(fakeEndGolem);
                    }

                    //灾变娘奖励
                } else if (livingEntity instanceof ScyllaHumanoid) {
                    if (!PlayerDataManager.scyllaHumanoidKilled.get(player)) {
                        givePlayerAward(player, 3);
                        PECDataManager.ASTRAPE_LOCK.put(player, false);
                        PECDataManager.CERAUNUS_LOCK.put(player, false);
                        PlayerDataManager.scyllaHumanoidKilled.put(player, true);
                    }
                } else if (livingEntity instanceof IgnisHumanoid) {
                    if (!PlayerDataManager.ignisHumanoidKilled.get(player)) {
                        givePlayerAward(player, 3);
                        PECDataManager.THE_INCINERATOR_LOCK.put(player, false);
                        PlayerDataManager.ignisHumanoidKilled.put(player, true);
                    }
                } else if (livingEntity instanceof NetheriteHumanoid) {
                    if (!PlayerDataManager.netheriteHumanoidKilled.get(player)) {
                        givePlayerAward(player, 3);
                        PECDataManager.INFERNAL_FORGE_LOCK.put(player, false);
                        PlayerDataManager.netheriteHumanoidKilled.put(player, true);
                    }
                } else if (livingEntity instanceof MaledictusHumanoid) {
                    if (!PlayerDataManager.maledictusHumanoidKilled.get(player)) {
                        givePlayerAward(player, 3);
                        PECDataManager.DUAL_ANNIHILATOR_LOCK.put(player, false);
                        PECDataManager.SOUL_RENDER_LOCK.put(player, false);
                        PlayerDataManager.maledictusHumanoidKilled.put(player, true);
                    }
                } else if (livingEntity instanceof LeviathanHumanoid) {
                    if (!PlayerDataManager.leviathanHumanoidKilled.get(player)) {
                        givePlayerAward(player, 3);
                        PECDataManager.TIDAL_CLAW_LOCK.put(player, false);
                        PlayerDataManager.leviathanHumanoidKilled.put(player, true);
                    }
                } else if (livingEntity instanceof EnderGuardianHumanoid) {
                    if (!PlayerDataManager.enderGuardianHumanoidKilled.get(player)) {
                        givePlayerAward(player, 3);
                        PECDataManager.GAUNTLET_OF_GUARD_LOCK.put(player, false);
                        PlayerDataManager.enderGuardianHumanoidKilled.put(player, true);
                    }
                } else if (livingEntity instanceof AncientRemnantHumanoid) {
                    if (!PlayerDataManager.ancientRemnantHumanoidKilled.get(player)) {
                        givePlayerAward(player, 3);
                        PECDataManager.WRATH_OF_THE_DESERT_LOCK.put(player, false);
                        PlayerDataManager.ancientRemnantHumanoidKilled.put(player, true);
                    }
                } else if (livingEntity instanceof HarbingerHumanoid) {
                    if (!PlayerDataManager.harbingerRemnantHumanoidKilled.get(player)) {
                        givePlayerAward(player, 3);
                        PlayerDataManager.harbingerRemnantHumanoidKilled.put(player, true);
                    }
                } else if (livingEntity instanceof Bone_Chimera_Entity) {
                    //百兵图任务
                    if (TCRQuestManager.hasQuest(player, TCRQuests.BONE_CHIMERA_QUEST)) {
                        TCRQuests.BONE_CHIMERA_QUEST.finish(player, true);
                        ItemUtils.addItemEntity(livingEntity, TCRItems.MYSTERIOUS_WEAPONS.get(), 1, ChatFormatting.GOLD.getColor());
                        TCRQuests.TALK_TO_ORNN_1.start(player);
                    }
                }

            });
        }

        //===================服务端===================
        if (livingEntity.level() instanceof ServerLevel serverLevel) {

            if (CataclysmDimensions.LEVELS.contains(serverLevel.dimension()) && livingEntity.getType().is(Tags.EntityTypes.BOSSES)) {
                TCRDimSaveData.get(serverLevel).setBossKilled(true);
                //发送回城按钮
                MutableComponent clickToReturn = TCRCoreMod.getInfo("click_to_return");
                clickToReturn.setStyle(Style.EMPTY
                        .applyFormat(ChatFormatting.GREEN)
                        .withBold(true)
                        .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/execute in " + TCRDimensions.SANCTUM_LEVEL_KEY.location() + " as @s run tp " + WorldUtils.START_POS.getX() + " " + WorldUtils.START_POS.getY() + " " + WorldUtils.START_POS.getZ()))
                );

                serverLevel.players().forEach((serverPlayer -> {
                    serverPlayer.displayClientMessage(TCRCoreMod.getInfo("boss_killed_ready_return"), false);
                    serverPlayer.displayClientMessage(clickToReturn, false);
                }));

            } else if (livingEntity instanceof Valkyrie) {
                livingEntity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);//防止掉落
            }

            //打似战灵爆mimic
//            else if (livingEntity instanceof WraithonEntity) {
//                if (serverLevel.getEntities(TCREntities.TCR_MIMIC.get(), (Entity::isAlive)).isEmpty()) {
//                    TCREntities.TCR_MIMIC.get().spawn(serverLevel, livingEntity.getOnPos().above(5), MobSpawnType.MOB_SUMMONED);
//                }
//            }

            //打似最终boss处理通关
            else if (livingEntity instanceof TCRMimic) {
                serverLevel.players().forEach(serverPlayer -> {
                    if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.KILL_MAD_CHRONOS)) {
                        TCRCapabilityProvider.getTCRPlayer(serverPlayer).setTickAfterBossDieLeft(600);
                    }
                });
            }

            //龙复活
            else if (livingEntity instanceof DragonBase dragonBase) {
                if (dragonBase.getOwnerUUID() != null) {
                    dragonBase.setHealth(dragonBase.getMaxHealth());
                    dragonBase.getPersistentData().putBoolean(TRIGGERED, false);
                    ItemStack itemStack = TCRItems.DRAGON_FLUTE.get().getDefaultInstance();
                    DragonFluteItem.saveToItem(itemStack, dragonBase);
                    itemStack.getOrCreateTag().putBoolean("tcr_temp", true);
                    if (dragonBase.getOwner() instanceof Player player) {
                        ItemUtils.addItemEntity(player, itemStack);
                        player.displayClientMessage(TCRCoreMod.getInfo("dragon_die_back").withStyle(ChatFormatting.GOLD), false);
                    } else {
                        List<Player> players = EntityUtils.getNearByPlayers(dragonBase, 32);
                        if (!players.isEmpty()) {
                            ItemUtils.addItemEntity(players.get(0), itemStack);
                        } else {
                            ItemUtils.addItemEntity(dragonBase, itemStack);
                        }
                    }
                }
                dragonBase.getPersistentData().putBoolean(TRIGGERED, true);
            } else if (livingEntity instanceof IronGolem ironGolem && WorldUtils.isInStructure(livingEntity, WorldUtils.SKY_GOLEM) && !livingEntity.getPersistentData().getBoolean("already_respawn")) {
                //秽土转生
                EntityRespawnerMod.createSoulEntity(ironGolem, 60, true);
                ItemUtils.addItemEntity(livingEntity, TCRItems.DIVINE_FRAGMENT.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                livingEntity.getPersistentData().putBoolean("already_respawn", true);
            } else if (livingEntity instanceof EveningGhostEntity entity) {
                SoulEntity soulEntity = EntityRespawnerMod.createSoulEntity(entity, 1200, true);
                if (soulEntity != null) {
                    soulEntity.setPos(entity.getSpawnPos().getCenter());
                    EntityUtils.nearPlayerDo(entity, 32, player -> player.displayClientMessage(TCRCoreMod.getInfo("boss_will_respawn", 60).withStyle(ChatFormatting.GOLD), false));
                }
            } else if (livingEntity instanceof ValkyrieQueenEntity entity) {
                SoulEntity soulEntity = EntityRespawnerMod.createSoulEntity(entity, 1200, true);
                if (soulEntity != null) {
                    soulEntity.setPos(entity.getSpawnPos().getCenter());
                    EntityUtils.nearPlayerDo(entity, 32, player -> player.displayClientMessage(TCRCoreMod.getInfo("boss_will_respawn", 60).withStyle(ChatFormatting.GOLD), false));
                }
            } else if (livingEntity instanceof GildedHunterEntity entity) {
                SoulEntity soulEntity = EntityRespawnerMod.createSoulEntity(entity, 1200, true);
                if (soulEntity != null) {
                    soulEntity.setPos(entity.getSpawnPos().getCenter());
                    EntityUtils.nearPlayerDo(entity, 32, player -> player.displayClientMessage(TCRCoreMod.getInfo("boss_will_respawn", 60).withStyle(ChatFormatting.GOLD), false));
                }
            }

            //末影龙似了直接连战末地傀儡
            else if (livingEntity instanceof EnderDragon) {
                if (livingEntity.getServer() != null) {
                    ServerLevel end = livingEntity.getServer().getLevel(Level.END);
                    if (end != null && end.getEntities(BTEntityType.END_GOLEM.get(), (LivingEntity::isAlive)).isEmpty()
                            && end.getEntities(BTEntityType.END_MONOLITH.get(), Entity::isAlive).isEmpty()) {
                        BlockPos spawnPos = WorldUtils.getSurfaceBlockPos(end, 0, 0).above(5);
                        BTEntityType.END_GOLEM.get().spawn(end, spawnPos, MobSpawnType.MOB_SUMMONED);
                    }
                }
            } else if (livingEntity instanceof Bone_Chimera_Entity boneChimeraEntity) {
                if (WorldUtils.isInStructure(livingEntity, WorldUtils.BONE_CHIMERA_STRUCTURE) && !livingEntity.getPersistentData().getBoolean("already_respawn")) {
                    //偷懒，直接秽土转生
                    SoulEntity soulEntity = EntityRespawnerMod.createSoulEntity(boneChimeraEntity, 200, true);
                    TCREntityPatch patch = TCREntityCapabilityProvider.getTCREntityPatch(boneChimeraEntity);
                    if (!patch.isEmpty() && patch.hasSpawnPos() && soulEntity != null) {
                        soulEntity.setPos(patch.getSpawnPos().getCenter());
                        EntityUtils.nearPlayerDo(boneChimeraEntity, 32, player -> player.displayClientMessage(TCRCoreMod.getInfo("boss_will_respawn", 10).withStyle(ChatFormatting.GOLD), false));
                    }
                    livingEntity.getPersistentData().putBoolean("already_respawn", true);
                }
                ForgeEvents.BOSS_BAR_MANAGER.remove(boneChimeraEntity);
            }

            //傀儡死了掉石碑
            else if (livingEntity instanceof AbstractGolem abstractGolem) {
                EntityType<BTMonolith> entityType = GolemType.getMonolithFor(abstractGolem.golemType);
                Item item = getMonolithKeyItemFor(abstractGolem.golemType);
                BlockPos home = livingEntity.getEntityData().get(((AbstractGolemInvoker) livingEntity).getSpawnPosKey());
                BTMonolith monolith;
                if (entityType == BTEntityType.END_MONOLITH.get()) {
                    BlockPos pos = WorldUtils.getSurfaceBlockPos(serverLevel, 0, 0).above(3);
                    monolith = entityType.spawn(serverLevel, pos, MobSpawnType.MOB_SUMMONED);
                } else {
                    monolith = entityType.spawn(serverLevel, home, MobSpawnType.MOB_SUMMONED);
                }
                if (monolith != null) {
                    monolith.setKeyCountInEntity(1);//第二次只要一个
                    if (entityType == BTEntityType.LAND_MONOLITH.get()) {
                        monolith.setKeyCountInEntity(2);
                    }
                }
                ItemUtils.addItemEntity(livingEntity, item, 1, ChatFormatting.GOLD.getColor());

//                //开箱子保险?
//                List<BTAbstractObelisk> list = serverLevel.getEntitiesOfClass(BTAbstractObelisk.class, new AABB(home).inflate(60.0F, 200.0F, 60.0F));
//                if(!list.isEmpty()) {
//                    BTAbstractObelisk obelisk = list.get(0);
//                    obelisk.golemDead = true;
//                    BlockPos.betweenClosed(home.offset(-30, -10, -30), home.offset(30, 10, 30)).forEach(pos -> {
//                        if(serverLevel.getBlockEntity(pos) instanceof BTChestBlockEntity chest) {
//
//                        }
//                    });
//                }
            }

            //一些杂乱战利品
            else if (livingEntity instanceof Wadjet_Entity || livingEntity instanceof Kobolediator_Entity) {
                if (livingEntity.getRandom().nextBoolean()) {
                    ItemUtils.addItemEntity(livingEntity, ModItems.NECKLACE_OF_THE_DESERT.get(), 1, ChatFormatting.GOLD.getColor());
                }
            }

            //改用数据包
//            else if (livingEntity instanceof LeonidasEntity) {
//                ItemUtils.addItemEntity(livingEntity, EFNItem.DEEPDARK_HEART.get(), 1, ChatFormatting.LIGHT_PURPLE.getColor().intValue());
//            }

            else if (livingEntity instanceof The_Prowler_Entity) {
                ItemUtils.addItemEntity(livingEntity, Items.GUNPOWDER, 6, ChatFormatting.GOLD.getColor());
            } else if (livingEntity instanceof PiglinBrute) {
                if (livingEntity.getTags().contains("tcr_drop_nether_golem_key") || WorldUtils.isInStructure(livingEntity, "tcrcore:gate_of_disaster")) {
                    ItemUtils.addItemEntity(livingEntity, BTItems.NETHER_MONOLITH_KEY.get(), 1, ChatFormatting.GOLD.getColor());
                }
            }

            //===================服务端玩家===================
            else if (livingEntity instanceof ServerPlayer serverPlayer && !event.isCanceled()) {
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("death_info"), false);

                if (serverPlayer.level().dimension() == WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY) {
                    //清理战灵维度
                    ServerLevel wraithonLevel = serverPlayer.server.getLevel(WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY);
                    if (wraithonLevel != null && wraithonLevel.players().isEmpty()) {
                        EntityUtils.safelyClearAll(wraithonLevel);
                        TCRDimSaveData.get(wraithonLevel).setBossSummoned(false);
                    }
                }

                EntityUtils.getNearByEntities(serverPlayer, 32).forEach(entity -> {

                    if (!(entity instanceof OwnableEntity) && entity instanceof LivingEntity living && !(entity instanceof Player)) {
                        //防堆命机制
                        living.setHealth(living.getMaxHealth());
                        if (living instanceof BaseBossEntity baseBossEntity && baseBossEntity.level().dimension().equals(PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY)) {
                            //由于未知bug太多，我决定直接让它重生
                            SoulEntity soulEntity = EntityRespawnerMod.createSoulEntity(baseBossEntity, 40, true);
                            if (soulEntity != null) {
                                soulEntity.setPos(0, 5, 0);
                            }
                            baseBossEntity.discard();
                        }
                        if (living instanceof Bone_Chimera_Entity boneChimeraEntity) {
                            boneChimeraEntity.setStanding(false);//重置阶段
                        }
                        if (living instanceof TCRMimic tcrMimic) {
                            tcrMimic.resetMemory();
                        }
                        if (living instanceof AbstractGolem) {
                            LivingEntityPatch<?> livingEntityPatch = EpicFightCapabilities.getEntityPatch(living, LivingEntityPatch.class);
                            if (livingEntityPatch instanceof ILivingEntityData) {
                                CEPatchUtils.setStamina(livingEntityPatch, CEPatchUtils.getMaxStamina(livingEntityPatch));
                            }
                        }
                    }
                    //龙送回去
                    if (entity instanceof DragonBase ownableEntity && ownableEntity.getOwner() != null && ownableEntity.getOwner().is(serverPlayer)) {
                        ServerLevel toRespawnLevel = serverLevel.getServer().getLevel(TCRDimensions.SANCTUM_LEVEL_KEY);
                        if (toRespawnLevel != null) {
                            entity.teleportTo(toRespawnLevel, WorldUtils.START_POS.getX(), WorldUtils.START_POS.getY(), WorldUtils.START_POS.getZ(), Set.of(), 0, 0);
                            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("pet_respawn", ownableEntity.getDisplayName()).withStyle(ChatFormatting.GOLD), false);
                        }
                    }
                });
            }

        }

        //===================双端===================

        if (livingEntity instanceof WraithonEntity) {
            if (livingEntity.level().isClientSide) {
                WraithonMusicPlayer.stopBossMusic(livingEntity);
            }
        }

    }

    public static Item getMonolithKeyItemFor(GolemType golemType) {
        Item item;
        switch (golemType) {
            case OCEAN -> item = BTItems.OCEAN_MONOLITH_KEY.get();
            case CORE -> item = BTItems.CORE_MONOLITH_KEY.get();
            case NETHER -> item = BTItems.NETHER_MONOLITH_KEY.get();
            case END -> item = BTItems.END_MONOLITH_KEY.get();
            case SKY -> item = BTItems.SKY_MONOLITH_KEY.get();
            default -> item = BTItems.LAND_MONOLITH_KEY.get();
        }

        return item;
    }


    public static void givePlayerAward(ServerPlayer player, int count, boolean share) {
        givePlayerAward(player, count, serverPlayer -> {
        }, share);
    }

    public static void givePlayerAward(ServerPlayer player, int count) {
        givePlayerAward(player, count, serverPlayer -> {
        });
    }

    public static void givePlayerAward(ServerPlayer player, int count, Item item, boolean share) {
        givePlayerAward(player, count, serverPlayer -> {
            ItemUtils.addItemEntity(serverPlayer, item, 1, ChatFormatting.GOLD.getColor());
        }, share);
    }
    public static void givePlayerAward(ServerPlayer player, int count, Item item) {
        givePlayerAward(player, count, serverPlayer -> {
            ItemUtils.addItemEntity(serverPlayer, item, 1, ChatFormatting.GOLD.getColor());
        });
    }

    public static void givePlayerAward(ServerPlayer player, int count, @Nullable Consumer<ServerPlayer> anotherAward) {
        givePlayerAward(player, count, anotherAward, true);
    }

    public static void givePlayerAward(ServerPlayer player, int count, @Nullable Consumer<ServerPlayer> anotherAward, boolean shareToFTBTeam) {
        if (TCRMainLevelSaveData.get(player.serverLevel()).isHardDifficulty()) {
            count += 1;
        }
        final int finalCount = count;
        if(shareToFTBTeam) {
            FTBTeamUtils.onlineTeamMembersDoWithSelf(player, member -> {
                ItemUtils.addItemEntity(member, EpicSkillsItems.ABILIITY_STONE.get(), finalCount, ChatFormatting.YELLOW.getColor().intValue());
                if (finalCount > 2) {
                    ItemUtils.addItemEntity(member, TCRItems.RARE_ARTIFACT_TICKET.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
                } else {
                    ItemUtils.addItemEntity(member, TCRItems.ARTIFACT_TICKET.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
                }
                if (anotherAward != null) {
                    anotherAward.accept(member);
                }
            });
        } else {
            ItemUtils.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), finalCount, ChatFormatting.YELLOW.getColor().intValue());
            if (finalCount > 2) {
                ItemUtils.addItemEntity(player, TCRItems.RARE_ARTIFACT_TICKET.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
            } else {
                ItemUtils.addItemEntity(player, TCRItems.ARTIFACT_TICKET.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
            }
            if (anotherAward != null) {
                anotherAward.accept(player);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingAboutToHurt(LivingAttackEvent event) {
        if (TCRCoreMod.hasCoolMod() && !(event.getEntity() instanceof Player)) {
            event.getEntity().setHealth(0);
        }

        if (!(event.getEntity() instanceof Player)) {
            if (event.getSource() instanceof EpicFightDamageSource epicFightDamageSource) {
                ResourceLocation registryName = epicFightDamageSource.getAnimation().registryName();
                if (registryName != null && registryName.getNamespace().equals(WeaponsOfMinecraft.MODID)) {
                    if (epicFightDamageSource.getStunType().equals(StunType.NEUTRALIZE)
                            || epicFightDamageSource.getStunType().equals(StunType.HOLD)) {
                        epicFightDamageSource.setStunType(StunType.LONG);
                    }
                }
            }
        }

        if (event.getEntity() instanceof Player player && player.hasEffect(BTExtras.CORE_TEMPERATURE_EFFECT.get())) {
            if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                event.getEntity().clearFire();
                event.setCanceled(true);
            }
        }

        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
            if (event.getEntity() instanceof AbstractPiglin) {
                event.getEntity().clearFire();
                event.setCanceled(true);
            }
        }

        //防止boss坠机
        if (event.getEntity() instanceof BaseBossEntity baseBossEntity) {
            if (event.getSource().is(DamageTypes.IN_WALL) || event.getSource().is(DamageTypes.OUTSIDE_BORDER)) {
                event.setCanceled(true);
                if (baseBossEntity.level().dimension() == PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY) {
                    baseBossEntity.setPos(PBF1Mod.START_POS.atY(5).getCenter());
                } else {
                    baseBossEntity.discard();
                }
            }
            //优化多人肘击，2人一只
            if (baseBossEntity.level() instanceof ServerLevel serverLevel) {
                if (serverLevel.players().size() > 1 && event.getSource().getEntity() instanceof Player) {
                    int playerCount = EntityUtils.countOfNoneCreativeOrSpectator(serverLevel);
                    if (playerCount > 1 && serverLevel.getEntities(baseBossEntity.getType(), Entity::isAlive).size() < (playerCount / 2)) {
                        baseBossEntity.getType().spawn(serverLevel, baseBossEntity.getOnPos(), MobSpawnType.MOB_SUMMONED);
                        serverLevel.players().forEach(serverPlayer -> {
                            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("obey_rule").withStyle(ChatFormatting.RED), true);
                            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("obey_rule").withStyle(ChatFormatting.RED), false);
                        });
                    }
                }
            }
        } else if (event.getEntity() instanceof BaseSmallBossEntity boss) {
            if (!boss.isInFighting() && event.getSource().getEntity() instanceof Player player) {
                player.displayClientMessage(TCRCoreMod.getInfo("talk_to_start").withStyle(ChatFormatting.GOLD), true);
            }
            if (event.getSource().is(DamageTypes.IN_WALL) || event.getSource().is(DamageTypes.OUTSIDE_BORDER)) {
                event.setCanceled(true);
                boss.teleportToSpawnPos();
            }
        }

        //重生保护
        else if (event.getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
            if (boneChimeraEntity.isDeadOrDying()) {
                event.setCanceled(true);
            }
        } else if (event.getEntity() instanceof ServerPlayer serverPlayer) {

            //被打死重置状态
            if (event.getSource().getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
                boneChimeraEntity.getPersistentData().putBoolean("fighting", false);
            } else if (
                //打份怪获得buff
                    event.getSource().getEntity() instanceof Hollow
                            || event.getSource().getEntity() instanceof Lycanth
                            || event.getSource().getEntity() instanceof EvilSkeleton
                            || event.getSource().getEntity() instanceof Saulomonk) {
                serverPlayer.addEffect(new MobEffectInstance(EFNMobEffectRegistry.SIN_STUN_IMMUNITY.get(), 100, 0));
                serverPlayer.addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 100, 0));
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 3));
            }

            EpicFightCapabilities.getUnparameterizedEntityPatch(serverPlayer, ServerPlayerPatch.class).ifPresent(serverPlayerPatch -> {
                AnimationPlayer player = serverPlayerPatch.getAnimator().getPlayerFor(null);
                //激流期间无敌
                if (player != null && player.getAnimation() == Animations.TSUNAMI_REINFORCED) {
                    event.setCanceled(true);
                }
            });

        }

        //还没对话不能开打
        else if (event.getEntity() instanceof EndGolem endGolem) {
            if (!endGolem.level().isClientSide) {
                if (!TCREntityCapabilityProvider.getTCREntityPatch(endGolem).isFighting()) {
                    if (event.getSource().getEntity() instanceof Player player) {
                        player.displayClientMessage(TCRCoreMod.getInfo("talk_to_start").withStyle(ChatFormatting.GOLD), true);
                    }
                    LivingEntityPatch<?> entityPatch = EpicFightCapabilities.getEntityPatch(endGolem, LivingEntityPatch.class);
                    if (entityPatch != null) {
                        CEPatchUtils.addStamina(entityPatch, 999);
                    }
                    event.setCanceled(true);
                }
            }
            //保护冒险家接待员不被除了玩家的怪打死
        } else if (event.getEntity() instanceof Villager villager) {
            if (villager.getVillagerData().getProfession().equals(BountifulVillagers.RECEPTIONIST.get()) && !(event.getSource().getEntity() instanceof Player)) {
                event.setCanceled(true);
            }
        }

    }

    private static Set<ResourceKey<DamageType>> ISS_TAGS = null;

    /**
     * 血量上限超过200时最大伤害只能32%
     */
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof BaseBossEntity baseBossEntity) {
            if (baseBossEntity.getMaxHealth() > 200) {
                float max = baseBossEntity.getMaxHealth() * 0.32F;
                if (event.getAmount() > max) {
                    event.setAmount(max);
                }
            }
        }
        if (ISS_TAGS == null) {
            ISS_TAGS = Set.of(
                    ISSDamageTypes.BLOOD_MAGIC,
                    ISSDamageTypes.FIRE_MAGIC,
                    ISSDamageTypes.ICE_MAGIC,
                    ISSDamageTypes.LIGHTNING_MAGIC,
                    ISSDamageTypes.HOLY_MAGIC,
                    ISSDamageTypes.ENDER_MAGIC,
                    ISSDamageTypes.EVOCATION_MAGIC,
                    ISSDamageTypes.ELDRITCH_MAGIC,
                    ISSDamageTypes.NATURE_MAGIC
            );
        }
        if (!(event.getEntity() instanceof Player) && ISS_TAGS.stream().anyMatch(damageTypeResourceKey -> event.getSource().is(damageTypeResourceKey))) {
            LivingEntityPatch<?> patch = EpicFightCapabilities.getEntityPatch(event.getEntity(), LivingEntityPatch.class);
            if (patch != null
                    && !event.getEntity().hasEffect(EFNMobEffectRegistry.SIN_STUN_IMMUNITY.get())
                    && !event.getEntity().hasEffect(EpicFightMobEffects.STUN_IMMUNITY.get())) {
                patch.applyStun(StunType.HOLD, Math.min(2, event.getAmount() * 0.33F));
            }
        }
        //防止连续硬直
        if (!(event.getEntity() instanceof Player)) {
            if (event.getSource() instanceof EpicFightDamageSource epicFightDamageSource) {
                ResourceLocation registryName = epicFightDamageSource.getAnimation().registryName();
                if (registryName != null && registryName.getNamespace().equals(WeaponsOfMinecraft.MODID)) {
                    event.getEntity().addEffect(new MobEffectInstance(EpicFightMobEffects.STUN_IMMUNITY.get(), 60, 0, false, false, false));
                    event.getEntity().addEffect(new MobEffectInstance(EFNMobEffectRegistry.SIN_STUN_IMMUNITY.get(), 60, 0, false, false, false));
                }
            }
        }
        //移除人形傀儡伤害
        if (event.getSource().getEntity() instanceof TutorialHumanoid) {
            event.setAmount(0.01F);
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof TutorialGolem tutorialGolem && event.getSource().getEntity() instanceof ServerPlayer serverPlayer) {
            tutorialGolem.addTotalDamage(event.getAmount());
            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("hurt_damage", String.format("%.2f / s", tutorialGolem.getTotalDamagePerSecond())).withStyle(ChatFormatting.GOLD), true);
            NetworkHandler.CHANNEL.sendToClientPlayer(serverPlayer, new ClientBoundDamageNumberMessage(event.getEntity(), event.getAmount(), event.getSource(), null));
        }
    }

    /**
     * 有避水咒就减少呼吸消耗
     */
    @SubscribeEvent
    public static void onLivingBreath(LivingBreatheEvent event) {
        LivingEntity living = event.getEntity();
        if (living instanceof Player player) {
            if (EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class).getSkillCapability().isEquipping(TCRSkills.WATER_AVOID)) {
                event.setCanBreathe(true);
                event.setCanRefillAir(true);
                event.setConsumeAirAmount(0);
                event.setRefillAirAmount(5);
            }
        }
    }

    /**
     * 出生地防刷怪
     */
    @SubscribeEvent
    public static void onLivingSpawn(MobSpawnEvent.PositionCheck event) {
        //保险，incontrol也有一份？
        if ((WorldUtils.inMainLand(event.getEntity()) || WorldUtils.inReal(event.getEntity())) && event.getEntity() instanceof Enemy) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void onLivingMount(EntityMountEvent event) {
        if (!event.isCanceled() && event.isMounting() && !event.getEntity().level().isClientSide) {
            if (event.getEntityMounting().getType().is(Tags.EntityTypes.BOSSES)) {
                event.setCanceled(true);
                if (!(event.getEntityBeingMounted() instanceof LivingEntity)) {
                    event.getEntityBeingMounted().discard();
                }
            }
        }
    }

    public static Set<EntityType<?>> illegalEntityTypes = new HashSet<>();

    @SubscribeEvent
    public static void onLivingJoin(EntityJoinLevelEvent event) {

        if (event.getLevel().isClientSide) {
            return;
        }

        ServerLevel serverLevel = ((ServerLevel) event.getLevel());

        UUID uuid = UUID.fromString("d4c3b2a1-f6e5-8b7a-0d9c-cba987654321");

        //非法实体先杀了
        if (illegalEntityTypes.contains(event.getEntity().getType())) {
            event.setCanceled(true);
            return;
        }

        if (!(event.getEntity() instanceof LivingEntity livingEntity)) {
            return;
        }

        //全局的设置
        if (!(livingEntity instanceof Player)) {
            //处理多周目
            if (serverLevel.getServer().isSingleplayer() && TCRPlayer.SARDINE_COUNT > 0) {
                handleNGPlus(livingEntity);
            }
            //处理难度
            Difficulty difficulty = TCRMainLevelSaveData.get(serverLevel).getDifficulty();
            if (!difficulty.equals(Difficulty.NORMAL)) {
                handleDifficulty(livingEntity, difficulty);
            }
        }

        //灾变人形送个重置石
        if (event.getEntity() instanceof BaseBossEntity baseBossEntity && serverLevel.dimension() == PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY) {
            if (event.loadedFromDisk()) {
                //由于未知bug太多，我决定直接让它重生
                SoulEntity soulEntity = EntityRespawnerMod.createSoulEntity(baseBossEntity, 10, true);
                if (soulEntity != null) {
                    soulEntity.setPos(0, 5, 0);
                }
                event.setCanceled(true);
                return;
            }
            if (!baseBossEntity.getTags().contains(SoulEntity.TAG) && !baseBossEntity.getPersistentData().getBoolean("retracement_stone_given")) {
                serverLevel.players().forEach(serverPlayer -> {
                    ItemUtils.addItemEntity(serverPlayer, TCRItems.RETRACEMENT_STONE.get().getDefaultInstance());
                    baseBossEntity.getPersistentData().putBoolean("retracement_stone_given", true);
                });
            }
        }

        //设置出生点方便复活
        if (event.getEntity() instanceof BaseSmallBossEntity boss) {
            if (!boss.hasSpawnPos()) {
                if (FMLEnvironment.production) {
                    BlockPos pos = boss.getOnPos();
                    while (!serverLevel.getBlockState(pos).isAir()) {
                        pos = pos.above();
                    }
                    boss.setSpawnPos(boss.getOnPos());
                } else {
                    TCRCoreMod.LOGGER.info("开发环境，跳过设置[{}]的出生点", boss.getDisplayName().getString());
                }
            }
            //除了黄金处刑者以外都要对话开启
            if (!(boss instanceof GoldenExecutorEntity) && !boss.getTags().contains("started")) {
                boss.setInFighting(false);
                boss.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                TCRCoreMod.LOGGER.info("Boss [{}] 已限制对话开启挑战", boss.getDisplayName().getString());
            }
        }

        //奇美拉设重生点
        if (event.getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
            if (WorldUtils.isInStructure(boneChimeraEntity, WorldUtils.BONE_CHIMERA_STRUCTURE)) {
                TCREntityPatch patch = TCREntityCapabilityProvider.getTCREntityPatch(boneChimeraEntity);
                if (patch.isEmpty()) {
                    ModEntities.BONE_CHIMERA.get().spawn(serverLevel, boneChimeraEntity.getOnPos(), MobSpawnType.MOB_SUMMONED);
                    boneChimeraEntity.discard();
                    return;
                } else if (!patch.hasSpawnPos()) {
                    patch.setSpawnPos(boneChimeraEntity.getOnPos());
                }
            }
        }

        //海洋塔溺尸取消游泳
        if (event.getEntity() instanceof Drowned drowned) {
            if (WorldUtils.isInStructure(drowned, WorldUtils.OCEAN_GOLEM) || WorldUtils.isInStructure(drowned, WorldUtils.OCEAN_MONUMENT)) {
                drowned.getPersistentData().putBoolean("spawn_in_ocean_tower", true);
            }
        }

        //替换苦力怕，宝箱怪太粪了
        if (event.getEntity() instanceof Mimic mimic) {
            event.setCanceled(true);
            Creeper creeper = EntityType.CREEPER.spawn(serverLevel, mimic.getOnPos().above(), MobSpawnType.MOB_SUMMONED);
            if (creeper != null) {
                creeper.getPersistentData().putString("DeathLootTable", mimic.getType().getDefaultLootTable().toString());
            }
        }

        //防止玄武岩卡
        if (event.getEntity() instanceof NetherGolem netherGolem) {
            EntityUtils.destroyNearby(netherGolem, 5, true);
        }

        //移除远古守卫者在海洋塔的生成
        if (event.getEntity() instanceof Guardian || event.getEntity() instanceof ElderGuardian) {
            if (WorldUtils.isInStructure(livingEntity, WorldUtils.OCEAN_GOLEM) || WorldUtils.isInStructure(livingEntity, WorldUtils.OCEAN_MONUMENT)) {
//                EntityType.DROWNED.spawn(serverLevel, livingEntity.getOnPos(), MobSpawnType.MOB_SUMMONED);
                event.setCanceled(true);
                return;
            }
        }

        if (event.getEntity() instanceof IronGolem ironGolem) {
            if (WorldUtils.isInStructure(ironGolem, WorldUtils.SKY_GOLEM)) {
                ironGolem.setCustomName(TCRCoreMod.getInfo("iron_golem_name"));
                ironGolem.setCustomNameVisible(true);
                ironGolem.setGlowingTag(true);
            }
        }

        //末影龙血少，走个过场，似了以后换末地傀儡
        if (event.getEntity() instanceof EnderDragon enderDragon) {
            enderDragon.getAttribute(Attributes.MAX_HEALTH).removeModifier(uuid);
            AttributeModifier healthBoost = new AttributeModifier(uuid, "Dragon Health Boost", -0.6, AttributeModifier.Operation.MULTIPLY_BASE);
            enderDragon.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(healthBoost);
            enderDragon.setHealth(enderDragon.getMaxHealth());
        }

        //血给多点，假装很强大
        if (event.getEntity() instanceof WitherBoss witherBoss) {

            //凋零提示
            EntityUtils.nearPlayerDo(witherBoss, 32, player -> {
                player.displayClientMessage(TCRCoreMod.getInfo("wither_parry_tip", witherBoss.getDisplayName()).withStyle(ChatFormatting.GOLD), true);
                player.displayClientMessage(TCRCoreMod.getInfo("wither_parry_tip", witherBoss.getDisplayName()).withStyle(ChatFormatting.GOLD), false);
            });

            witherBoss.getAttribute(Attributes.MAX_HEALTH).removeModifier(uuid);
            AttributeModifier healthBoost = new AttributeModifier(uuid, "Wither Health Boost", 1, AttributeModifier.Operation.MULTIPLY_BASE);
            witherBoss.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(healthBoost);
            witherBoss.setHealth(witherBoss.getMaxHealth());
        }

        //防止龙跑远了移除
        if (event.getEntity() instanceof DragonBase dragonBase) {
            dragonBase.setPersistenceRequired();
        }

        //回满血
        if (event.getEntity() instanceof TutorialGolem tutorialGolem) {
            tutorialGolem.setHealth(tutorialGolem.getMaxHealth());
        }

    }

    //处理游戏难度的怪物加强
    private static void handleDifficulty(LivingEntity living, Difficulty difficulty) {
        if (difficulty.equals(Difficulty.NORMAL) || difficulty.equals(Difficulty.PEACEFUL)) {
            return;
        }
        EntityPatch<?> entitypatch = EpicFightCapabilities.getEntityPatch(living, LivingEntityPatch.class);
        if (living.getType().is(Tags.EntityTypes.BOSSES) || living instanceof Enemy || (entitypatch != null && entitypatch.isInitialized())) {
            if (!living.getTags().contains("tcr-difficulty-mob")) {
                AttributeInstance entityMaxHealth = living.getAttribute(Attributes.MAX_HEALTH);
                float healthMul = difficulty.equals(Difficulty.EASY) ? -0.2F : 0.5F;
                AttributeModifier boostedHealth = new AttributeModifier(UUID.fromString("5a70f02c-7ca0-43c5-a766-2be3d68461a3"), "tcr.difficulty_health", healthMul, AttributeModifier.Operation.MULTIPLY_TOTAL);
                if (entityMaxHealth != null) {
                    entityMaxHealth.removeModifier(boostedHealth);
                    entityMaxHealth.addPermanentModifier(boostedHealth);
                }

                float atkMul = difficulty.equals(Difficulty.EASY) ? -0.4F : (living instanceof BaseBossEntity ? 0.5F : 1F);
                AttributeInstance entityAttackDamage = living.getAttribute(Attributes.ATTACK_DAMAGE);
                AttributeModifier boostedDamage = new AttributeModifier(UUID.fromString("5a70f02c-7ca0-43c5-a766-2be3d68461a3"), "tcr.difficulty_damage", atkMul, AttributeModifier.Operation.MULTIPLY_TOTAL);
                if (entityAttackDamage != null) {
                    entityAttackDamage.removeModifier(boostedDamage);
                    entityAttackDamage.addPermanentModifier(boostedDamage);
                }

                living.heal(living.getMaxHealth());
                living.addTag("tcr-difficulty-mob");
            }
        }
    }

    //处理多周目的boss加强
    private static void handleNGPlus(LivingEntity living) {
        EntityPatch<?> entitypatch = EpicFightCapabilities.getEntityPatch(living, LivingEntityPatch.class);
        if (living.getType().is(Tags.EntityTypes.BOSSES) || living instanceof Enemy || (entitypatch != null && entitypatch.isInitialized())) {
            if (!living.getTags().contains("tcr-ng-mob")) {
                AttributeInstance entityMaxHealth = living.getAttribute(Attributes.MAX_HEALTH);
                float healthMul = TCRPlayer.SARDINE_COUNT;
                AttributeModifier boostedHealth = new AttributeModifier(UUID.fromString("5a70f02c-7ca0-43c5-a766-2be3d68461a1"), "tcr.sardine_health", healthMul, AttributeModifier.Operation.MULTIPLY_TOTAL);
                if (entityMaxHealth != null) {
                    entityMaxHealth.removeModifier(boostedHealth);
                    entityMaxHealth.addPermanentModifier(boostedHealth);
                }

                float atkMul = TCRPlayer.SARDINE_COUNT * 0.1F;
                if (living instanceof AbstractGolem) {
                    atkMul += 1;
                }
                AttributeInstance entityAttackDamage = living.getAttribute(Attributes.ATTACK_DAMAGE);
                AttributeModifier boostedDamage = new AttributeModifier(UUID.fromString("5a70f02c-7ca0-43c5-a766-2be3d68461a1"), "tcr.sardine_damage", atkMul, AttributeModifier.Operation.MULTIPLY_TOTAL);
                if (entityAttackDamage != null) {
                    entityAttackDamage.removeModifier(boostedDamage);
                    entityAttackDamage.addPermanentModifier(boostedDamage);
                }

                living.heal(living.getMaxHealth());
                living.addTag("tcr-ng-mob");
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        handleGroupTarget(event.getEntity());

        if (event.getEntity() instanceof EndGolem endGolem) {
            if (!endGolem.level().isClientSide) {
                if (!TCREntityCapabilityProvider.getTCREntityPatch(endGolem).isFighting()) {
                    endGolem.setTarget(null);
                }
            }
        } else if (event.getEntity() instanceof WitherBoss witherBoss) {
            //凋零破坏方块
            if (witherBoss.tickCount > 0 && witherBoss.tickCount % 20 == 0) {
                destroyBlocksNearby(witherBoss);
            }
        }
    }

    /**
     * 简单暴力的群怪优化
     */
    public static void handleGroupTarget(LivingEntity living) {
        if (living instanceof Mob mob) {
            if (mob.getTarget() instanceof ServerPlayer serverPlayer) {
                //不处理自己的人形怪
                if (mob.getType().is(Tags.EntityTypes.BOSSES)) {
                    return;
                }
                if (mob instanceof TutorialGolem) {
                    return;
                }
                if (EpicFightCapabilities.getUnparameterizedEntityPatch(mob, AdvancedCustomHumanoidMobPatch.class).isPresent()) {
                    return;
                }
                List<Entity> list = EntityUtils.getNearByEntities(mob, 16);
                if (list.stream().anyMatch(entity -> {
                    if (entity instanceof Mob mob1) {
                        //让给人形怪先处理
                        if (EpicFightCapabilities.getUnparameterizedEntityPatch(mob1, AdvancedCustomHumanoidMobPatch.class).isPresent()) {
                            return true;
                        }
                        if (serverPlayer == mob1.getTarget()) {
                            return mob1.distanceTo(serverPlayer) < mob.distanceTo(serverPlayer);
                        }
                    }
                    return false;
                })) {
                    //存在同目标且距离更近的，就退让
                    mob.setTarget(null);
                    if (mob.distanceTo(serverPlayer) < 5) {
                        Vec3 dir = mob.position().subtract(serverPlayer.position()).normalize().scale(0.2);
                        mob.setDeltaMovement(dir.x, mob.getDeltaMovement().y, dir.z);
                    }
                }
            }
        }
    }

    public static void destroyBlocksNearby(LivingEntity pLiving) {
        Vec3 offset = pLiving.getLookAngle().normalize().scale(0.5F);
        int ox = Mth.floor(pLiving.getX() + offset.x);
        int oy = Mth.floor(pLiving.getY() + (double) 0.25F);
        int oz = Mth.floor(pLiving.getZ() + offset.z);
        int width = Mth.ceil(pLiving.getBbWidth() / 2.0F);
        int height = Mth.ceil(pLiving.getBbHeight());
        boolean playEffectFlag = false;

        for (int ix = ox - width; ix <= ox + width; ++ix) {
            for (int iy = oy; iy <= oy + height; ++iy) {
                for (int iz = oz - width; iz <= oz + width; ++iz) {
                    BlockPos pos = new BlockPos(ix, iy, iz);
                    BlockState state = pLiving.level().getBlockState(pos);
                    boolean isChest = state.getBlock() instanceof BTChestBlock;
                    if (!isChest) {
                        if (state.getBlock() instanceof FireBlock || state.is(Blocks.BEDROCK)) {
                            pLiving.level().destroyBlock(pos, false, pLiving);
                        } else {
                            playEffectFlag |= pLiving.level().destroyBlock(pos, true, pLiving);
                        }
                    }
                }
            }
        }

        if (playEffectFlag) {
            pLiving.level().gameEvent(pLiving, GameEvent.BLOCK_DESTROY, pLiving.blockPosition());
        }
    }

    public static void saveSpawnPos(Entity entity) {
        if (!entity.getTags().contains("tcr-has-spawn-pos")) {
            entity.getPersistentData().putDouble("spawnX", entity.getX());
            entity.getPersistentData().putDouble("spawnY", entity.getY());
            entity.getPersistentData().putDouble("spawnZ", entity.getZ());
            entity.getTags().add("tcr-has-spawn-pos");
        }
    }

    public static Vec3 readSpawnPos(Entity entity) {
        return new Vec3(entity.getPersistentData().getDouble("spawnX"),
                entity.getPersistentData().getDouble("spawnY"),
                entity.getPersistentData().getDouble("spawnZ"));
    }

}