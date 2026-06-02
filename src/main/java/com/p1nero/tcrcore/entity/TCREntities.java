package com.p1nero.tcrcore.entity;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.entity.custom.CustomColorItemEntity;
import com.p1nero.tcrcore.entity.custom.SimpleMobPatch;
import com.p1nero.tcrcore.entity.custom.aine_iris.AineEntity;
import com.p1nero.tcrcore.entity.custom.boss_rush.BossRushManagerEntity;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_boss.FakeBossNpc;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_boss.FakeBossPatch;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_end_golem.FakeEndGolem;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_sky_golem.FakeSkyGolem;
import com.p1nero.tcrcore.entity.custom.ferry_girl.FerryGirlEntity;
import com.p1nero.tcrcore.entity.custom.chronos_sol.ChronosSolEntity;
import com.p1nero.tcrcore.entity.custom.mimic.TCRMimic;
import com.p1nero.tcrcore.entity.custom.mimic.TCRMimicPatch;
import com.p1nero.tcrcore.entity.custom.ornn.OrnnEntity;
import com.p1nero.tcrcore.entity.custom.tutorial_golem.TutorialGolem;
import com.p1nero.tcrcore.entity.custom.tutorial_humanoid.TutorialHumanoid;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.forgeevent.EntityPatchRegistryEvent;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.entitypatch.Factions;
import yesman.epicfight.world.capabilities.entitypatch.mob.IronGolemPatch;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TCREntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TCRCoreMod.MOD_ID);

    public static final RegistryObject<EntityType<CustomColorItemEntity>> CUSTOM_COLOR_ITEM = register("custom_color_item",
            EntityType.Builder.<CustomColorItemEntity>of(CustomColorItemEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(6).updateInterval(20));

    public static final RegistryObject<EntityType<ChronosSolEntity>> CHRONOS_SOL = register("chronos_sol",
            EntityType.Builder.of(ChronosSolEntity::new, MobCategory.CREATURE).sized(0.8f, 3.0F).fireImmune());

    public static final RegistryObject<EntityType<FerryGirlEntity>> FERRY_GIRL = register("ferry_girl",
            EntityType.Builder.of(FerryGirlEntity::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());

    public static final RegistryObject<EntityType<AineEntity>> AINE = register("aine",
            EntityType.Builder.of(AineEntity::new, MobCategory.CREATURE).sized(0.8f, 3.0F).fireImmune());

    public static final RegistryObject<EntityType<OrnnEntity>> ORNN = register("ornn",
            EntityType.Builder.of(OrnnEntity::new, MobCategory.CREATURE).sized(4f, 4f).fireImmune());

    public static final RegistryObject<EntityType<FakeSkyGolem>> FAKE_SKY_GOLEM = register("fake_sky_golem",
            EntityType.Builder.<FakeSkyGolem>of(FakeSkyGolem::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());

    public static final RegistryObject<EntityType<FakeEndGolem>> FAKE_END_GOLEM = register("fake_end_golem",
            EntityType.Builder.<FakeEndGolem>of(FakeEndGolem::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());

    public static final RegistryObject<EntityType<TutorialGolem>> TUTORIAL_GOLEM = register("tutorial_golem",
            EntityType.Builder.of(TutorialGolem::new, MobCategory.CREATURE).sized(1.4F, 2.7f).fireImmune());

    public static final RegistryObject<EntityType<TutorialHumanoid>> TUTORIAL_HUMANOID = register("tutorial_humanoid",
            EntityType.Builder.of(TutorialHumanoid::new, MobCategory.CREATURE).sized(0.8F, 1.9F).fireImmune());

    public static final RegistryObject<EntityType<TCRMimic>> TCR_MIMIC = register("tcr_mimic",
            EntityType.Builder.of(TCRMimic::new, MobCategory.CREATURE).sized(0.8f, 2.5f).fireImmune());

    public static final RegistryObject<EntityType<FakeBossNpc>> FAKE_MALEDICTUS_HUMANOID = register("fake_maledictus_humanoid",
            EntityType.Builder.of(FakeBossNpc::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());
    public static final RegistryObject<EntityType<FakeBossNpc>> FAKE_IGNIS_HUMANOID = register("fake_ignis_humanoid",
            EntityType.Builder.of(FakeBossNpc::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());
    public static final RegistryObject<EntityType<FakeBossNpc>> FAKE_NETHERITE_HUMANOID = register("fake_netherite_humanoid",
            EntityType.Builder.of(FakeBossNpc::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());
    public static final RegistryObject<EntityType<FakeBossNpc>> FAKE_SCYLLA_HUMANOID = register("fake_scylla_humanoid",
            EntityType.Builder.of(FakeBossNpc::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());
    public static final RegistryObject<EntityType<FakeBossNpc>> FAKE_ENDER_GUARDIAN_HUMANOID = register("fake_ender_guardian_humanoid",
            EntityType.Builder.of(FakeBossNpc::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());
    public static final RegistryObject<EntityType<FakeBossNpc>> FAKE_HARBINGER_HUMANOID = register("fake_harbinger_humanoid",
            EntityType.Builder.of(FakeBossNpc::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());
    public static final RegistryObject<EntityType<FakeBossNpc>> FAKE_LEVIATHAN_HUMANOID = register("fake_leviathan_humanoid",
            EntityType.Builder.of(FakeBossNpc::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());
    public static final RegistryObject<EntityType<FakeBossNpc>> FAKE_ANCIENT_REMNANT_HUMANOID = register("fake_ancient_remnant_humanoid",
            EntityType.Builder.of(FakeBossNpc::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());

    public static final RegistryObject<EntityType<BossRushManagerEntity>> BOSS_RUSH_MANAGER = register("boss_rush_manager",
            EntityType.Builder.of(BossRushManagerEntity::new, MobCategory.CREATURE).sized(0.1f, 0.1f).clientTrackingRange(32).updateInterval(20).fireImmune());


    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(name, () -> entityTypeBuilder.build(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name).toString()));
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(FAKE_MALEDICTUS_HUMANOID.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_IGNIS_HUMANOID.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_NETHERITE_HUMANOID.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_SCYLLA_HUMANOID.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_ENDER_GUARDIAN_HUMANOID.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_HARBINGER_HUMANOID.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_LEVIATHAN_HUMANOID.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_ANCIENT_REMNANT_HUMANOID.get(), ChronosSolEntity.setAttributes());

        event.put(TCR_MIMIC.get(), TCRMimic.setAttributes());
        event.put(BOSS_RUSH_MANAGER.get(), BossRushManagerEntity.setAttributes());

        event.put(CHRONOS_SOL.get(), ChronosSolEntity.setAttributes());
        event.put(FERRY_GIRL.get(), ChronosSolEntity.setAttributes());
        event.put(ORNN.get(), ChronosSolEntity.setAttributes());
        event.put(AINE.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_SKY_GOLEM.get(), ChronosSolEntity.setAttributes());
        event.put(FAKE_END_GOLEM.get(), ChronosSolEntity.setAttributes());
        event.put(TUTORIAL_GOLEM.get(), TutorialGolem.setAttributes());
        event.put(TUTORIAL_HUMANOID.get(), TutorialHumanoid.setAttributes());

    }

    @SubscribeEvent
    public static void setPatch(EntityPatchRegistryEvent event) {
        event.getTypeEntry().put(TUTORIAL_GOLEM.get(), (entity) -> IronGolemPatch::new);
        event.getTypeEntry().put(FAKE_SKY_GOLEM.get(), (entity) -> () -> new SimpleMobPatch<>(Factions.VILLAGER));
        event.getTypeEntry().put(FAKE_END_GOLEM.get(), (entity) -> () -> new SimpleMobPatch<>(Factions.VILLAGER));

        event.getTypeEntry().put(TCR_MIMIC.get(), (entity) -> TCRMimicPatch::new);

        event.getTypeEntry().put(FAKE_MALEDICTUS_HUMANOID.get(), (entity) -> FakeBossPatch::new);
        event.getTypeEntry().put(FAKE_IGNIS_HUMANOID.get(), (entity) -> FakeBossPatch::new);
        event.getTypeEntry().put(FAKE_NETHERITE_HUMANOID.get(), (entity) -> FakeBossPatch::new);
        event.getTypeEntry().put(FAKE_SCYLLA_HUMANOID.get(), (entity) -> FakeBossPatch::new);
        event.getTypeEntry().put(FAKE_ENDER_GUARDIAN_HUMANOID.get(), (entity) -> FakeBossPatch::new);
        event.getTypeEntry().put(FAKE_HARBINGER_HUMANOID.get(), (entity) -> FakeBossPatch::new);
        event.getTypeEntry().put(FAKE_LEVIATHAN_HUMANOID.get(), (entity) -> FakeBossPatch::new);
        event.getTypeEntry().put(FAKE_ANCIENT_REMNANT_HUMANOID.get(), (entity) -> FakeBossPatch::new);

    }

    @SubscribeEvent
    public static void setArmature(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Armatures.registerEntityTypeArmature(TUTORIAL_GOLEM.get(), Armatures.IRON_GOLEM);
            Armatures.registerEntityTypeArmature(FAKE_SKY_GOLEM.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(FAKE_END_GOLEM.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(TCR_MIMIC.get(), Armatures.BIPED);

            Armatures.registerEntityTypeArmature(FAKE_MALEDICTUS_HUMANOID.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(FAKE_IGNIS_HUMANOID.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(FAKE_NETHERITE_HUMANOID.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(FAKE_SCYLLA_HUMANOID.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(FAKE_ENDER_GUARDIAN_HUMANOID.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(FAKE_HARBINGER_HUMANOID.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(FAKE_LEVIATHAN_HUMANOID.get(), Armatures.BIPED);
            Armatures.registerEntityTypeArmature(FAKE_ANCIENT_REMNANT_HUMANOID.get(), Armatures.BIPED);
        });
    }

}
