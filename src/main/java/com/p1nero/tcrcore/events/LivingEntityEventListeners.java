package com.p1nero.tcrcore.events;

import com.brass_amber.ba_bt.entity.block.BTMonolith;
import com.brass_amber.ba_bt.entity.hostile.golem.*;
import com.brass_amber.ba_bt.init.BTEntityType;
import com.brass_amber.ba_bt.init.BTExtras;
import com.brass_amber.ba_bt.init.BTItems;
import com.brass_amber.ba_bt.util.GolemType;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.The_Leviathan_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Ancient_Remnant.Ancient_Remnant_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Maledictus.Maledictus_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.NewNetherite_Monstrosity.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Scylla.Scylla_Entity;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.alexthe668.domesticationinnovation.server.entity.TameableUtils;
import com.github.dodo.dodosmobs.entity.InternalAnimationMonster.IABossMonsters.Bone_Chimera_Entity;
import com.hm.efn.registries.EFNItem;
import com.merlin204.sg.item.SGItems;
import com.obscuria.aquamirae.Aquamirae;
import com.obscuria.aquamirae.AquamiraeUtils;
import com.obscuria.aquamirae.common.entities.CaptainCornelia;
import com.obscuria.aquamirae.registry.AquamiraeItems;
import com.p1nero.battle_field1.PBF1Mod;
import com.p1nero.battle_field1.worldgen.PBF1Dimensions;
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
import com.p1nero.tcr_bosses.mixins.AbstractGolemInvoker;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.*;
import com.p1nero.tcrcore.client.sound.CorneliaMusicPlayer;
import com.p1nero.tcrcore.client.sound.WraithonMusicPlayer;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_end_golem.FakeEndGolem;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_sky_golem.FakeSkyGolem;
import com.p1nero.tcrcore.entity.custom.mimic.TCRMimic;
import com.p1nero.tcrcore.gameassets.TCRSkills;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.item.custom.DragonFluteItem;
import com.p1nero.tcrcore.save_data.TCRDimSaveData;
import com.p1nero.tcrcore.utils.EntityUtil;
import com.p1nero.tcrcore.utils.ItemUtil;
import com.p1nero.tcrcore.utils.WorldUtil;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import com.yesman.epicskills.registry.entry.EpicSkillsItems;
import net.kenddie.fantasyarmor.item.FAItems;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sonmok14.fromtheshadows.server.entity.mob.BulldrogiothEntity;
import org.merlin204.wraithon.entity.wraithon.WraithonEntity;
import org.merlin204.wraithon.worldgen.WraithonDimensions;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.client.input.EpicFightKeyMappings;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.EntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.*;

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

            if (entity instanceof IronGolem ironGolem && WorldUtil.isInStructure(ironGolem, WorldUtil.SKY_GOLEM)) {
                ItemUtil.addItemEntity(entity, TCRItems.DIVINE_FRAGMENT.get(), 1, ChatFormatting.AQUA.getColor());
                event.setCanceled(true);
            }

            if (entity instanceof Pillager) {
                if (entity.getRandom().nextFloat() < 0.2F) {
                    ItemUtil.addItemEntity(entity, Items.GOLD_INGOT, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.1F) {
                    ItemUtil.addItemEntity(entity, Items.DIAMOND, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.01F) {
                    ItemUtil.addItemEntity(entity, Items.NETHERITE_INGOT, 1, 0xc000ff);
                }
                if (!event.getEntity().getLootTable().toString().endsWith("captain") && entity.hasGlowingTag()) {
                    ItemUtil.addItemEntity(entity, AquamiraeItems.SHELL_HORN.get(), 1, ChatFormatting.GOLD.getColor());
                }
            } else if (entity instanceof Enemy) {
                if (entity.getRandom().nextFloat() < 0.1F) {
                    ItemUtil.addItemEntity(entity, Items.IRON_INGOT, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.03F) {
                    ItemUtil.addItemEntity(entity, Items.AMETHYST_SHARD, 1, 0xc000ff);
                } else if (entity.getRandom().nextFloat() < 0.01F) {
                    ItemUtil.addItemEntity(entity, FAItems.MOON_CRYSTAL.get(), 1, 0xc000ff);
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
        if (livingEntity.getType().is(Tags.EntityTypes.BOSSES)) {

            //附近玩家都处理击败
            livingEntity.level().getEntitiesOfClass(ServerPlayer.class, (new AABB(center, center)).inflate(30)).forEach(player -> {

                if (player.isSpectator() || player.isCreative()) {
                    player.displayClientMessage(TCRCoreMod.getInfo("creative_may_lost_progress").withStyle(ChatFormatting.RED), false);
                    return;
                }

                //击败祭坛内的boss
                if (livingEntity instanceof Scylla_Entity) {
                    if (!PlayerDataManager.stormEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.stormEyeKilled.put(player, true);
                    }
                } else if (livingEntity instanceof Ignis_Entity) {
                    if (!PlayerDataManager.flameEyeKilled.get(player)) {
                        givePlayerAward(player, 5);
                        PlayerDataManager.flameEyeKilled.put(player, true);
                    }
                } else if (livingEntity instanceof The_Leviathan_Entity) {
                    if (!PlayerDataManager.abyssEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.abyssEyeKilled.put(player, true);
                    }
                } else if (livingEntity instanceof Maledictus_Entity) {
                    if (!PlayerDataManager.cursedEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.cursedEyeKilled.put(player, true);
                    }
                } else if (livingEntity instanceof Ancient_Remnant_Entity) {
                    if (!PlayerDataManager.desertEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.desertEyeKilled.put(player, true);
                    }
                } else if (livingEntity instanceof Ender_Guardian_Entity) {
                    if (!PlayerDataManager.voidEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.voidEyeKilled.put(player, true);
                    }
                } else if (livingEntity instanceof Netherite_Monstrosity_Entity) {
                    if (!PlayerDataManager.monstEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.monstEyeKilled.put(player, true);
                    }
                } else if (livingEntity instanceof The_Harbinger_Entity) {
                    if (!PlayerDataManager.mechEyeKilled.get(player)) {
                        givePlayerAward(player, 2);
                        PlayerDataManager.mechEyeKilled.put(player, true);
                    }
                }

                //处理主线的boss掉落眼睛
                else if (livingEntity instanceof LandGolem) {
                    //有任务才会掉
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_DESERT_EYE)) {
                        givePlayerAward(player, 1);
                        ItemUtil.addItemEntity(player, ModItems.DESERT_EYE.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
                        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                    }
                } else if (livingEntity instanceof OceanGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_OCEAN_EYE)) {
                        givePlayerAward(player, 1);
                        ItemUtil.addItemEntity(player, ModItems.ABYSS_EYE.get(), 1, ChatFormatting.BLUE.getColor().intValue());
                        ItemUtil.addItemEntity(player, AquamiraeItems.SHIP_GRAVEYARD_ECHO.get(), 1, ChatFormatting.BLUE.getColor().intValue());
                        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                    }
                } else if (livingEntity instanceof CaptainCornelia) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_CURSED_EYE)) {
                        givePlayerAward(player, 1);
                        ItemUtil.addItemEntity(player, ModItems.CURSED_EYE.get(), 1, ChatFormatting.DARK_GREEN.getColor().intValue());
                        ItemUtil.addItemEntity(player, TCRItems.NECROMANCY_SCROLL.get(), 1, ChatFormatting.DARK_GREEN.getColor().intValue());
                        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                    }
                } else if (livingEntity instanceof CoreGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_FLAME_EYE)) {
                        givePlayerAward(player, 1);
                        ItemUtil.addItemEntity(player, ModItems.FLAME_EYE.get(), 1, ChatFormatting.RED.getColor().intValue());
                        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                    }
                    ItemUtil.addItemEntity(player, EFNItem.DUSKFIRE_INGOT.get(), 1, ChatFormatting.RED.getColor().intValue());
                } else if (livingEntity instanceof NetherGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_MONST_EYE)) {
                        givePlayerAward(player, 1);
                        ItemUtil.addItemEntity(player, ModItems.MONSTROUS_EYE.get(), 1, ChatFormatting.DARK_RED.getColor().intValue());
                        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                    }
                    ItemUtil.addItemEntity(player, EFNItem.DUSKFIRE_INGOT.get(), 1, ChatFormatting.RED.getColor().intValue());
                } else if (livingEntity instanceof WitherBoss) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_WITHER_EYE)) {
                        givePlayerAward(player, 1);
                        ItemUtil.addItemEntity(player, ModItems.MECH_EYE.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                        ItemUtil.addItemEntity(player, TCRItems.WITHER_SOUL_STONE.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                    }
                } else if (livingEntity instanceof SkyGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_STORM_EYE)) {
                        givePlayerAward(player, 1);
                        FakeSkyGolem fakeSkyGolem = new FakeSkyGolem(player);
                        fakeSkyGolem.setPos(player.position());
                        player.serverLevel().addFreshEntity(fakeSkyGolem);
                        TCRQuests.TALK_TO_SKY_GOLEM.start(player);
                    }
                } else if (livingEntity instanceof EndGolem) {
                    if (TCRQuestManager.hasQuest(player, TCRQuests.GET_VOID_EYE)) {
                        givePlayerAward(player, 2);
                        FakeEndGolem fakeEndGolem = new FakeEndGolem(player);
                        fakeEndGolem.setPos(player.position());
                        player.serverLevel().addFreshEntity(fakeEndGolem);
                    }
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
                        ItemUtil.addItemEntity(livingEntity, TCRItems.MYSTERIOUS_WEAPONS.get(), 1, ChatFormatting.GOLD.getColor());
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
                        .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/execute in " + TCRDimensions.SANCTUM_LEVEL_KEY.location() + " as @s run tp " + WorldUtil.START_POS.getX() + " " + WorldUtil.START_POS.getY() + " " + WorldUtil.START_POS.getZ()))
                );

                serverLevel.players().forEach((serverPlayer -> {
                    serverPlayer.displayClientMessage(TCRCoreMod.getInfo("boss_killed_ready_return"), false);
                    serverPlayer.displayClientMessage(clickToReturn, false);
                }));

            }

            //打似战灵爆mimic
            if (livingEntity instanceof WraithonEntity) {
                if (serverLevel.getEntities(TCREntities.TCR_MIMIC.get(), (Entity::isAlive)).isEmpty()) {
                    TCREntities.TCR_MIMIC.get().spawn(serverLevel, livingEntity.getOnPos().above(5), MobSpawnType.MOB_SUMMONED);
                }
            }

            //打似最终boss处理通关
            if (livingEntity instanceof TCRMimic) {
                serverLevel.players().forEach(serverPlayer -> {
                    if (TCRQuestManager.hasQuest(serverPlayer, TCRQuests.KILL_MAD_CHRONOS)) {
                        TCRCapabilityProvider.getTCRPlayer(serverPlayer).setTickAfterBossDieLeft(600);
                    }
                });
            }

            if(livingEntity instanceof DragonBase dragonBase) {
                if(dragonBase.getOwnerUUID() != null) {
                    BlockPos bedPos = TameableUtils.getPetBedPos(event.getEntity());
                    if(bedPos == null) {
                        if(dragonBase.getOwner() instanceof Player player) {
                            ItemStack itemStack = TCRItems.DRAGON_FLUTE.get().getDefaultInstance();
                            DragonFluteItem.saveToItem(itemStack, dragonBase);
                            itemStack.getOrCreateTag().putBoolean("tcr_temp", true);
                            ItemUtil.addItemEntity(player, itemStack);
                            player.displayClientMessage(TCRCoreMod.getInfo("dragon_die_back").withStyle(ChatFormatting.GOLD), false);
                        }
                    }

                }
            }

            //云鲸塔的顺便掉个钥匙
            if (livingEntity instanceof IronGolem ironGolem && WorldUtil.isInStructure(livingEntity, WorldUtil.SKY_GOLEM) && !livingEntity.getPersistentData().getBoolean("already_respawn")) {
                //秽土转生
                EntityRespawnerMod.addToRespawn(ironGolem, 60, true);
                ItemUtil.addItemEntity(livingEntity, SGItems.GOLEM_HEART.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                ItemUtil.addItemEntity(livingEntity, TCRItems.DIVINE_FRAGMENT.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                ItemUtil.addItemEntity(livingEntity, BTItems.SKY_MONOLITH_KEY.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                livingEntity.getPersistentData().putBoolean("already_respawn", true);
            }

            //末影龙似了直接连战末地傀儡
            if (livingEntity instanceof EnderDragon) {
                if (livingEntity.getServer() != null) {
                    ServerLevel end = livingEntity.getServer().getLevel(Level.END);
                    if (end != null && end.getEntities(BTEntityType.END_GOLEM.get(), (LivingEntity::isAlive)).isEmpty()
                            && end.getEntities(BTEntityType.END_MONOLITH.get(), Entity::isAlive).isEmpty()) {
                        BlockPos spawnPos = WorldUtil.getSurfaceBlockPos(end, 0, 0).above(5);
                        BTEntityType.END_GOLEM.get().spawn(end, spawnPos, MobSpawnType.MOB_SUMMONED);
                    }
                }
            }

            if (livingEntity instanceof Bone_Chimera_Entity boneChimeraEntity) {
                if (WorldUtil.isInStructure(livingEntity, WorldUtil.BONE_CHIMERA_STRUCTURE) && !livingEntity.getPersistentData().getBoolean("already_respawn")) {
                    //偷懒，直接秽土转生
                    SoulEntity soulEntity = EntityRespawnerMod.addToRespawn(boneChimeraEntity, 200, true);
                    if (boneChimeraEntity.getPersistentData().contains("spawnX") && soulEntity != null) {
                        soulEntity.setPos(readSpawnPos(boneChimeraEntity));
                    }
                    livingEntity.getPersistentData().putBoolean("already_respawn", true);
                }
                ForgeEvents.BOSS_BAR_MANAGER.remove(boneChimeraEntity);
            }

            //彩蛋物品
            if (livingEntity instanceof Maledictus_Entity) {
                ItemUtil.addItemEntity(livingEntity, TCRItems.DUAL_BOKKEN.get(), 1, ChatFormatting.LIGHT_PURPLE.getColor());
            }

            //傀儡死了掉石碑
            if (livingEntity instanceof AbstractGolem abstractGolem) {
                EntityType<BTMonolith> entityType = GolemType.getMonolithFor(abstractGolem.golemType);
                Item item = getMonolithKeyItemFor(abstractGolem.golemType);
                BlockPos home = livingEntity.getEntityData().get(((AbstractGolemInvoker) livingEntity).getSpawnPosKey());
                BTMonolith monolith;
                if (entityType == BTEntityType.END_MONOLITH.get()) {
                    BlockPos pos = WorldUtil.getSurfaceBlockPos(serverLevel, 0, 0).above(3);
                    monolith = entityType.spawn(serverLevel, pos, MobSpawnType.MOB_SUMMONED);
                } else {
                    monolith = entityType.spawn(serverLevel, home, MobSpawnType.MOB_SUMMONED);
                }
                if (monolith != null) {
                    monolith.setKeyCountInEntity(1);//第二次只要一个
                }
                ItemUtil.addItemEntity(livingEntity, item, 1, ChatFormatting.GOLD.getColor());
            }

            //===================服务端玩家===================
            if (livingEntity instanceof ServerPlayer serverPlayer && !event.isCanceled()) {
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("death_info"), false);

                if (serverPlayer.level().dimension() == WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY) {
                    //清理战灵维度
                    ServerLevel wraithonLevel = serverPlayer.server.getLevel(WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY);
                    if (wraithonLevel != null && wraithonLevel.players().isEmpty()) {
                        EntityUtil.safelyClearAll(wraithonLevel);
                        TCRDimSaveData.get(wraithonLevel).setBossSummoned(false);
                    }
                }

                EntityUtil.getNearByEntities(serverPlayer, 20).forEach(entity -> {
                    if (!(entity instanceof OwnableEntity) && entity instanceof LivingEntity living) {
                        //防堆命机制
                        living.setHealth(living.getMaxHealth());
                        living.removeAllEffects();
                        if (living instanceof Bone_Chimera_Entity boneChimeraEntity) {
                            boneChimeraEntity.setStanding(false);//重置阶段
                        }
                        if(living instanceof TCRMimic tcrMimic) {
                            tcrMimic.resetMemory();
                        }
                    }
                });
            }

        }

        //===================双端===================
        if (livingEntity instanceof CaptainCornelia) {
            if (livingEntity.level().isClientSide) {
                //修它bgm播放bug，不知道现在修了没
                CorneliaMusicPlayer.stopBossMusic(livingEntity);
            } else {
                ItemUtil.addItemEntity(livingEntity, EFNItem.DEEPDARK_HEART.get(), 1, ChatFormatting.LIGHT_PURPLE.getColor().intValue());
            }
        }

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

    public static void givePlayerAward(ServerPlayer player, int count) {
        ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), count, ChatFormatting.YELLOW.getColor().intValue());
        if (count > 2) {
            ItemUtil.addItemEntity(player, TCRItems.RARE_ARTIFACT_TICKET.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
        } else {
            ItemUtil.addItemEntity(player, TCRItems.ARTIFACT_TICKET.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
        }
    }

    @SubscribeEvent
    public static void onLivingAboutToHurt(LivingAttackEvent event) {
        if (TCRCoreMod.hasCheatMod()) {
            event.getEntity().setHealth(0);
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
        }


        //重生保护
        if (event.getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
            if (boneChimeraEntity.isDeadOrDying()) {
                event.setCanceled(true);
            }
        }

        if (event.getEntity() instanceof ServerPlayer serverPlayer) {

            //被打死重置状态
            if (event.getSource().getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
                boneChimeraEntity.getPersistentData().putBoolean("fighting", false);
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
        if(event.getEntity() instanceof EndGolem endGolem) {
            if(!endGolem.level().isClientSide) {
                if(!TCREntityCapabilityProvider.getTCREntityPatch(endGolem).isFighting()) {
                    if(event.getSource().getEntity() instanceof Player player) {
                        player.displayClientMessage(TCRCoreMod.getInfo("talk_to_start").withStyle(ChatFormatting.GOLD), true);
                    }
                    event.setCanceled(true);
                }
            }
        }

    }

    /**
     * 血量上限超过200时最大伤害只能32%
     */
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if(event.getEntity() instanceof BaseBossEntity baseBossEntity) {
            if(baseBossEntity.getMaxHealth() > 200) {
                float max = baseBossEntity.getMaxHealth() * 0.32F;
                if(event.getAmount() > max) {
                    event.setAmount(max);
                }
            }
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
        if ((WorldUtil.inMainLand(event.getEntity()) || WorldUtil.inReal(event.getEntity())) && event.getEntity() instanceof Enemy) {
            event.setResult(Event.Result.DENY);
        }
    }

    public static Set<EntityType<?>> illegalEntityTypes = new HashSet<>();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onMobSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (event.getEntity() instanceof Pillager pillager && AquamiraeUtils.isInIceMaze(event.getEntity())) {
            if (event.getEntity().getRandom().nextFloat() < 0.1) {
                pillager.getPersistentData().putString("DeathLootTable", Aquamirae.MODID + ":entities/maze_captain");
                pillager.setGlowingTag(true);
            }
            if (event.getEntity().getLootTable().toString().endsWith("captain")) {
                event.getEntity().setGlowingTag(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingJoin(EntityJoinLevelEvent event) {

        if (event.getEntity().level().isClientSide) {
            return;
        }

        ServerLevel serverLevel = (ServerLevel) event.getEntity().level();

        if(serverLevel.getServer().isSingleplayer() && TCRPlayer.SARDINE_COUNT > 0) {
            if (event.getEntity() instanceof LivingEntity living && (living.getType().is(Tags.EntityTypes.BOSSES) || living instanceof Enemy)) {
                EntityPatch<?> entitypatch = EpicFightCapabilities.getEntityPatch(living, LivingEntityPatch.class);
                if (entitypatch != null && entitypatch.isInitialized() && !event.getEntity().getTags().contains("tcr-stronger-mob")) {
                    AttributeInstance entityMaxHealth = living.getAttribute(Attributes.MAX_HEALTH);
                    AttributeModifier boostedHealth = new AttributeModifier(UUID.fromString("5a70f02c-7ca0-43c5-a766-2be3d68461a2"), "tcr.sardine_health", TCRPlayer.SARDINE_COUNT, AttributeModifier.Operation.MULTIPLY_TOTAL);
                    if (entityMaxHealth != null) {
                        entityMaxHealth.removeModifier(boostedHealth);
                        entityMaxHealth.addPermanentModifier(boostedHealth);
                    }

                    AttributeInstance entityAttackDamage = living.getAttribute(Attributes.ATTACK_DAMAGE);
                    AttributeModifier boostedDamage = new AttributeModifier(UUID.fromString("5a70f02c-7ca0-43c5-a766-2be3d68461a2"), "tcr.sardine_damage", TCRPlayer.SARDINE_COUNT, AttributeModifier.Operation.MULTIPLY_TOTAL);
                    if (entityAttackDamage != null) {
                        entityAttackDamage.removeModifier(boostedDamage);
                        entityAttackDamage.addPermanentModifier(boostedDamage);
                    }

                    living.heal(living.getMaxHealth());
                    living.addTag("tcr-stronger-mob");
                }
            }
        }

        if (event.getEntity() instanceof BulldrogiothEntity bulldrogiothEntity) {
            if (WorldUtil.isInStructure(bulldrogiothEntity, WorldUtil.RIBBIT_VILLAGE)) {
                bulldrogiothEntity.setGlowingTag(true);
                saveSpawnPos(bulldrogiothEntity);
            }
        }

        if (event.getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
            if (WorldUtil.isInStructure(boneChimeraEntity, WorldUtil.BONE_CHIMERA_STRUCTURE)) {
                saveSpawnPos(boneChimeraEntity);
            }
        }

        if (event.getEntity() instanceof Drowned drowned) {
            if (WorldUtil.isInStructure(drowned, WorldUtil.OCEAN_GOLEM)) {
                drowned.getPersistentData().putBoolean("spawn_in_ocean_tower", true);
            }
        }

        if (illegalEntityTypes.contains(event.getEntity().getType())) {
            event.setCanceled(true);
            return;
        }

        //移除远古守卫者在海洋塔的生成
        if (event.getEntity() instanceof Guardian guardian && WorldUtil.isInStructure(guardian, WorldUtil.OCEAN_GOLEM)) {
            event.setCanceled(true);
        }

        UUID uuid = UUID.fromString("d4c3b2a1-f6e5-8b7a-0d9c-cba987654321");
        if (event.getEntity() instanceof IronGolem ironGolem) {
            if (WorldUtil.isInStructure(ironGolem, WorldUtil.SKY_GOLEM)) {
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
            witherBoss.getAttribute(Attributes.MAX_HEALTH).removeModifier(uuid);
            AttributeModifier healthBoost = new AttributeModifier(uuid, "Wither Health Boost", 1, AttributeModifier.Operation.MULTIPLY_BASE);
            witherBoss.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(healthBoost);
            witherBoss.setHealth(witherBoss.getMaxHealth());
        }

        //保护措施
        if (event.getEntity() instanceof WraithonEntity wraithonEntity) {
            if (!serverLevel.getEntities(TCREntities.TCR_MIMIC.get(), LivingEntity::isAlive).isEmpty()) {
                wraithonEntity.discard();
            }
        }

    }

    @SubscribeEvent
    public static void livingEquipmentChange(LivingEquipmentChangeEvent event) {
//        if(event.getEntity() instanceof Player player) {
//            if(player.level().dimension().equals(PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY)) {
//                if(!EntityUtil.getNearByEntities(BaseBossEntity.class, player, 50).isEmpty()) {
//
//                }
//            }
//        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if(event.getEntity() instanceof EndGolem endGolem) {
            if(!endGolem.level().isClientSide) {
                if(!TCREntityCapabilityProvider.getTCREntityPatch(endGolem).isFighting()) {
                    endGolem.setTarget(null);
                }
            }
        }
    }

    public static void saveSpawnPos(Entity entity) {
        if (!entity.getPersistentData().contains("spawnX")) {
            entity.getPersistentData().putDouble("spawnX", entity.getX());
            entity.getPersistentData().putDouble("spawnY", entity.getY());
            entity.getPersistentData().putDouble("spawnZ", entity.getZ());
        }
    }

    public static Vec3 readSpawnPos(Entity entity) {
        return new Vec3(entity.getPersistentData().getDouble("spawnX"),
                entity.getPersistentData().getDouble("spawnY"),
                entity.getPersistentData().getDouble("spawnZ"));
    }

}