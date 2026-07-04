package com.p1nero.tcrcore.client.ponder;

import com.almostreliable.summoningrituals.altar.AltarBlockEntity;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.hm.efn.registries.EFNItem;
import com.p1nero.p1nero_ec.gameassets.PECAnimations;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.item.TCRItems;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.createmod.ponder.api.ParticleEmitter;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class TCRBossesPonderScene {

    public static void addScyllaScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("add_scylla", TCRBossEntities.SCYLLA_HUMANOID.get().getDescription().getString());

        BlockPos altarPos = util.grid().at(2, 0, 2);
        Vec3 altarSurface = util.vector().blockSurface(altarPos, Direction.UP);

        ItemStack catalyst = new ItemStack(Items.GHAST_TEAR);

        ItemStack item1 = new ItemStack(ModItems.ESSENCE_OF_THE_STORM.get());

        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.scaleSceneView(1.0F);

        scene.overlay().showText(20)
                .text(item1.getItem().getDescription().getString() + " × " + item1.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item1);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });
        scene.idle(20);

        scene.overlay().showText(20)
                .text(catalyst.getItem().getDescription().getString() + " × " + catalyst.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, catalyst);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.getInventory().getVanillaInv().clearContent();
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        ParticleEmitter emitter = scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.SOUL, Vec3.ZERO);
        scene.effects().emitParticles(altarSurface.add(0, 1.2, 0), emitter, 8, 2);
        scene.idle(20);

        scene.world().createEntity(level -> {
            LivingEntity entity = TCRBossEntities.SCYLLA_HUMANOID.get().create(level);
            if(entity != null) {
                entity.setPos(altarSurface);
                entity.setItemInHand(InteractionHand.MAIN_HAND, ModItems.CERAUNUS.get().getDefaultInstance());
                entity.yRotO = 180;
                entity.setYRot(180);
                entity.yHeadRotO = 180;
                entity.yHeadRot = 180;
                EpicFightCapabilities.getUnparameterizedEntityPatch(entity, LivingEntityPatch.class).ifPresent(entityPatch -> {
                    entityPatch.setYRot(180);
                    entityPatch.setYRotO(180);
                    entityPatch.getClientAnimator().resetLivingAnimations();
                    entityPatch.playAnimation(PECAnimations.ASTRAPE_AUTO3, 0.15F);
                });
            }
            return entity;
        });
        scene.idle(20);
        scene.markAsFinished();
    }

    public static void addAncientRemainScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("add_ancient_remain", TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get().getDescription().getString());

        BlockPos altarPos = util.grid().at(2, 0, 2);
        Vec3 altarSurface = util.vector().blockSurface(altarPos, Direction.UP);

        ItemStack catalyst = new ItemStack(Items.GHAST_TEAR);

        ItemStack item1 = new ItemStack(ModItems.ANCIENT_METAL_INGOT.get());
        ItemStack item2 = new ItemStack(ModItems.NECKLACE_OF_THE_DESERT.get());
        ItemStack item3 = new ItemStack(Items.BONE, 11);

        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.scaleSceneView(1.0F);

        scene.overlay().showText(20)
                .text(item1.getItem().getDescription().getString() + " × " + item1.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item1);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });
        scene.idle(20);

        scene.overlay().showText(20)
                .text(item2.getItem().getDescription().getString() + " × " + item2.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item2);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(item3.getItem().getDescription().getString() + " × " + item3.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item3);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(catalyst.getItem().getDescription().getString() + " × " + catalyst.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, catalyst);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.getInventory().getVanillaInv().clearContent();
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        ParticleEmitter emitter = scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.SOUL, Vec3.ZERO);
        scene.effects().emitParticles(altarSurface.add(0, 1.2, 0), emitter, 8, 2);
        scene.idle(20);

        scene.world().createEntity(level -> {
            LivingEntity entity = TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get().create(level);
            if(entity != null) {
                entity.setPos(altarSurface);
                entity.setItemInHand(InteractionHand.MAIN_HAND, ModItems.ANCIENT_SPEAR.get().getDefaultInstance());
                entity.yRotO = 180;
                entity.setYRot(180);
                entity.yHeadRotO = 180;
                entity.yHeadRot = 180;
                EpicFightCapabilities.getUnparameterizedEntityPatch(entity, LivingEntityPatch.class).ifPresent(entityPatch -> {
                    entityPatch.setYRot(180);
                    entityPatch.setYRotO(180);
                    entityPatch.getClientAnimator().resetLivingAnimations();
                    entityPatch.playAnimation(PECAnimations.ASTRAPE_AUTO3, 0.15F);
                });
            }
            return entity;
        });
        scene.idle(20);
        scene.markAsFinished();
    }

    public static void addEnderGuardianScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("add_ender_guardian", TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get().getDescription().getString());

        BlockPos altarPos = util.grid().at(2, 0, 2);
        Vec3 altarSurface = util.vector().blockSurface(altarPos, Direction.UP);

        ItemStack catalyst = new ItemStack(Items.GHAST_TEAR);

        ItemStack item1 = new ItemStack(TCRItems.VOID_CORE.get());
        ItemStack item3 = new ItemStack(Items.SHULKER_SHELL, 1);

        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.scaleSceneView(1.0F);

        scene.overlay().showText(20)
                .text(item1.getItem().getDescription().getString() + " × " + item1.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item1);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });
        scene.idle(20);

        scene.idle(20);
        scene.overlay().showText(20)
                .text(item3.getItem().getDescription().getString() + " × " + item3.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item3);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(catalyst.getItem().getDescription().getString() + " × " + catalyst.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, catalyst);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.getInventory().getVanillaInv().clearContent();
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        ParticleEmitter emitter = scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.SOUL, Vec3.ZERO);
        scene.effects().emitParticles(altarSurface.add(0, 1.2, 0), emitter, 8, 2);
        scene.idle(20);

        scene.world().createEntity(level -> {
            LivingEntity entity = TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get().create(level);
            if(entity != null) {
                entity.setPos(altarSurface);
                entity.setItemInHand(InteractionHand.MAIN_HAND, ModItems.GAUNTLET_OF_GUARD.get().getDefaultInstance());
                entity.yRotO = 180;
                entity.setYRot(180);
                entity.yHeadRotO = 180;
                entity.yHeadRot = 180;
                EpicFightCapabilities.getUnparameterizedEntityPatch(entity, LivingEntityPatch.class).ifPresent(entityPatch -> {
                    entityPatch.setYRot(180);
                    entityPatch.setYRotO(180);
                    entityPatch.getClientAnimator().resetLivingAnimations();
                    entityPatch.playAnimation(PECAnimations.BEDIVERE_SKILL_A, 0.15F);
                });
            }
            return entity;
        });
        scene.idle(20);
        scene.markAsFinished();
    }

    public static void addHarbingerScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("add_harbinger", TCRBossEntities.HARBINGER_HUMANOID.get().getDescription().getString());

        BlockPos altarPos = util.grid().at(2, 0, 2);
        Vec3 altarSurface = util.vector().blockSurface(altarPos, Direction.UP);

        ItemStack catalyst = new ItemStack(Items.GHAST_TEAR);

        ItemStack item1 = new ItemStack(ModItems.WITHERITE_INGOT.get());
        ItemStack item2 = new ItemStack(Items.NETHER_STAR, 1);

        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.scaleSceneView(1.0F);

        scene.overlay().showText(20)
                .text(item1.getItem().getDescription().getString() + " × " + item1.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item1);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });
        scene.idle(20);

        scene.overlay().showText(20)
                .text(item2.getItem().getDescription().getString() + " × " + item2.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item2);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(catalyst.getItem().getDescription().getString() + " × " + catalyst.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, catalyst);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.getInventory().getVanillaInv().clearContent();
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        ParticleEmitter emitter = scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.SOUL, Vec3.ZERO);
        scene.effects().emitParticles(altarSurface.add(0, 1.2, 0), emitter, 8, 2);
        scene.idle(20);

        scene.world().createEntity(level -> {
            LivingEntity entity = TCRBossEntities.HARBINGER_HUMANOID.get().create(level);
            if(entity != null) {
                entity.setPos(altarSurface);
                entity.setItemInHand(InteractionHand.MAIN_HAND, EFNItem.HF_MURASAMA.get().getDefaultInstance());
                entity.yRotO = 180;
                entity.setYRot(180);
                entity.yHeadRotO = 180;
                entity.yHeadRot = 180;
                EpicFightCapabilities.getUnparameterizedEntityPatch(entity, LivingEntityPatch.class).ifPresent(entityPatch -> {
                    entityPatch.setYRot(180);
                    entityPatch.setYRotO(180);
                    entityPatch.getClientAnimator().resetLivingAnimations();
                    entityPatch.playAnimation(PECAnimations.ASTRAPE_AUTO3, 0.15F);
                });
            }
            return entity;
        });
        scene.idle(20);
        scene.markAsFinished();
    }

    public static void addIgnisScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("add_ignis", TCRBossEntities.IGNIS_HUMANOID.get().getDescription().getString());

        BlockPos altarPos = util.grid().at(2, 0, 2);
        Vec3 altarSurface = util.vector().blockSurface(altarPos, Direction.UP);

        ItemStack catalyst = new ItemStack(Items.GHAST_TEAR);

        ItemStack item2 = new ItemStack(Items.MAGMA_BLOCK, 5);
        ItemStack item4 = new ItemStack(ModItems.IGNITIUM_INGOT.get(), 1);

        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.scaleSceneView(1.0F);

        scene.overlay().showText(20)
                .text(item2.getItem().getDescription().getString() + " × " + item2.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item2);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(item4.getItem().getDescription().getString() + " × " + item4.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item4);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(catalyst.getItem().getDescription().getString() + " × " + catalyst.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, catalyst);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.getInventory().getVanillaInv().clearContent();
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        ParticleEmitter emitter = scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.SOUL, Vec3.ZERO);
        scene.effects().emitParticles(altarSurface.add(0, 1.2, 0), emitter, 8, 2);
        scene.idle(20);

        scene.world().createEntity(level -> {
            LivingEntity entity = TCRBossEntities.IGNIS_HUMANOID.get().create(level);
            if(entity != null) {
                entity.setPos(altarSurface);
                entity.setItemInHand(InteractionHand.MAIN_HAND, ModItems.THE_INCINERATOR.get().getDefaultInstance());
                entity.yRotO = 180;
                entity.setYRot(180);
                entity.yHeadRotO = 180;
                entity.yHeadRot = 180;
                EpicFightCapabilities.getUnparameterizedEntityPatch(entity, LivingEntityPatch.class).ifPresent(entityPatch -> {
                    entityPatch.setYRot(180);
                    entityPatch.setYRotO(180);
                    entityPatch.getClientAnimator().resetLivingAnimations();
                    entityPatch.playAnimation(PECAnimations.THE_INCINERATOR_SKILL3, 0.15F);
                });
            }
            return entity;
        });
        scene.idle(20);
        scene.markAsFinished();
    }

    public static void addLeviathanScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("add_leviathan", TCRBossEntities.LEVIATHAN_HUMANOID.get().getDescription().getString());

        BlockPos altarPos = util.grid().at(2, 0, 2);
        Vec3 altarSurface = util.vector().blockSurface(altarPos, Direction.UP);

        ItemStack catalyst = new ItemStack(Items.GHAST_TEAR);

        ItemStack item1 = new ItemStack(TCRItems.ABYSS_CORE.get(), 1);

        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.scaleSceneView(1.0F);

        scene.overlay().showText(20)
                .text(item1.getItem().getDescription().getString() + " × " + item1.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item1);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(catalyst.getItem().getDescription().getString() + " × " + catalyst.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, catalyst);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.getInventory().getVanillaInv().clearContent();
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        ParticleEmitter emitter = scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.SOUL, Vec3.ZERO);
        scene.effects().emitParticles(altarSurface.add(0, 1.2, 0), emitter, 8, 2);
        scene.idle(20);

        scene.world().createEntity(level -> {
            LivingEntity entity = TCRBossEntities.LEVIATHAN_HUMANOID.get().create(level);
            if(entity != null) {
                entity.setPos(altarSurface);
                entity.setItemInHand(InteractionHand.MAIN_HAND, ModItems.TIDAL_CLAWS.get().getDefaultInstance());
                entity.yRotO = 180;
                entity.setYRot(180);
                entity.yHeadRotO = 180;
                entity.yHeadRot = 180;
                EpicFightCapabilities.getUnparameterizedEntityPatch(entity, LivingEntityPatch.class).ifPresent(entityPatch -> {
                    entityPatch.setYRot(180);
                    entityPatch.setYRotO(180);
                    entityPatch.getClientAnimator().resetLivingAnimations();
                    entityPatch.playAnimation(PECAnimations.CLAW_SKILL3, 0.15F);
                });
            }
            return entity;
        });
        scene.idle(20);
        scene.markAsFinished();
    }

    public static void addMaledictusScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("add_maledictus", TCRBossEntities.MALEDICTUS_HUMANOID.get().getDescription().getString());

        BlockPos altarPos = util.grid().at(2, 0, 2);
        Vec3 altarSurface = util.vector().blockSurface(altarPos, Direction.UP);

        ItemStack catalyst = new ItemStack(Items.GHAST_TEAR);

        ItemStack item1 = new ItemStack(ItemRegistry.FROZEN_BONE_SHARD.get(), 3);
        ItemStack item3 = new ItemStack(ModItems.CURSIUM_INGOT.get(), 1);

        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.scaleSceneView(1.0F);

        scene.overlay().showText(20)
                .text(item1.getItem().getDescription().getString() + " × " + item1.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item1);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(item3.getItem().getDescription().getString() + " × " + item3.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item3);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(catalyst.getItem().getDescription().getString() + " × " + catalyst.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, catalyst);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.getInventory().getVanillaInv().clearContent();
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        ParticleEmitter emitter = scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.SOUL, Vec3.ZERO);
        scene.effects().emitParticles(altarSurface.add(0, 1.2, 0), emitter, 8, 2);
        scene.idle(20);

        scene.world().createEntity(level -> {
            LivingEntity entity = TCRBossEntities.MALEDICTUS_HUMANOID.get().create(level);
            if(entity != null) {
                entity.setPos(altarSurface);
                entity.setItemInHand(InteractionHand.MAIN_HAND, ModItems.SOUL_RENDER.get().getDefaultInstance());
                entity.yRotO = 180;
                entity.setYRot(180);
                entity.yHeadRotO = 180;
                entity.yHeadRot = 180;
                EpicFightCapabilities.getUnparameterizedEntityPatch(entity, LivingEntityPatch.class).ifPresent(entityPatch -> {
                    entityPatch.setYRot(180);
                    entityPatch.setYRotO(180);
                    entityPatch.getClientAnimator().resetLivingAnimations();
                    entityPatch.playAnimation(PECAnimations.SOUL_RENDER_SKILL3, 0.15F);
                });
            }
            return entity;
        });
        scene.idle(20);
        scene.markAsFinished();
    }

    public static void addNetheriteScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("add_netherite", TCRBossEntities.NETHERITE_HUMANOID.get().getDescription().getString());

        BlockPos altarPos = util.grid().at(2, 0, 2);
        Vec3 altarSurface = util.vector().blockSurface(altarPos, Direction.UP);

        ItemStack catalyst = new ItemStack(Items.GHAST_TEAR);

        ItemStack item1 = new ItemStack(Items.CRYING_OBSIDIAN, 3);
        ItemStack item3 = new ItemStack(ModItems.MONSTROUS_HORN.get(), 1);

        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.scaleSceneView(1.0F);

        scene.overlay().showText(20)
                .text(item1.getItem().getDescription().getString() + " × " + item1.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item1);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(item3.getItem().getDescription().getString() + " × " + item3.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, item3);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.overlay().showText(20)
                .text(catalyst.getItem().getDescription().getString() + " × " + catalyst.getCount())
                .attachKeyFrame()
                .pointAt(altarSurface)
                .placeNearTarget();

        scene.idle(20);

        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.handleInteraction(null, catalyst);
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        scene.idle(20);
        scene.world().modifyBlockEntity(altarPos, AltarBlockEntity.class, altarBlockEntity -> {
            altarBlockEntity.getInventory().getVanillaInv().clearContent();
            if(altarBlockEntity.getLevel() != null) {
                altarBlockEntity.getLevel().sendBlockUpdated(altarBlockEntity.getBlockPos(), altarBlockEntity.getBlockState(), altarBlockEntity.getBlockState(), 3);
            }
        });

        ParticleEmitter emitter = scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.SOUL, Vec3.ZERO);
        scene.effects().emitParticles(altarSurface.add(0, 1.2, 0), emitter, 8, 2);
        scene.idle(20);

        scene.world().createEntity(level -> {
            LivingEntity entity = TCRBossEntities.NETHERITE_HUMANOID.get().create(level);
            if(entity != null) {
                entity.setPos(altarSurface);
                entity.setItemInHand(InteractionHand.MAIN_HAND, ModItems.INFERNAL_FORGE.get().getDefaultInstance());
                entity.yRotO = 180;
                entity.setYRot(180);
                entity.yHeadRotO = 180;
                entity.yHeadRot = 180;
                EpicFightCapabilities.getUnparameterizedEntityPatch(entity, LivingEntityPatch.class).ifPresent(entityPatch -> {
                    entityPatch.setYRot(180);
                    entityPatch.setYRotO(180);
                    entityPatch.getClientAnimator().resetLivingAnimations();
                    entityPatch.playAnimation(PECAnimations.ASTRAPE_AUTO3, 0.15F);
                });
            }
            return entity;
        });
        scene.idle(20);
        scene.markAsFinished();
    }

}
