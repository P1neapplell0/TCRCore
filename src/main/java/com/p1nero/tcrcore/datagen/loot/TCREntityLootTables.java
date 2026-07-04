package com.p1nero.tcrcore.datagen.loot;

import com.brass_amber.ba_bt.init.BTItems;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.item.TCRItems;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TCREntityLootTables extends EntityLootSubProvider {

    protected TCREntityLootTables() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(TCREntities.CHRONOS_SOL.get(), emptyLootTable());
        this.add(TCREntities.FERRY_GIRL.get(), emptyLootTable());
        this.add(TCREntities.ORNN.get(), emptyLootTable());
        this.add(TCREntities.TUTORIAL_GOLEM.get(), emptyLootTable());
        this.add(TCREntities.TUTORIAL_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.AINE.get(), emptyLootTable());
        this.add(TCREntities.FAKE_SKY_GOLEM.get(), emptyLootTable());
        this.add(TCREntities.FAKE_END_GOLEM.get(), emptyLootTable());
        this.add(TCREntities.FAKE_MALEDICTUS_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.FAKE_IGNIS_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.FAKE_NETHERITE_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.FAKE_SCYLLA_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.FAKE_ENDER_GUARDIAN_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.FAKE_HARBINGER_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.FAKE_LEVIATHAN_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.FAKE_ANCIENT_REMNANT_HUMANOID.get(), emptyLootTable());
        this.add(TCREntities.TCR_MIMIC.get(), emptyLootTable());
        this.add(TCREntities.BOSS_RUSH_MANAGER.get(), emptyLootTable());
        this.add(TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get(), LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(TCRItems.DESERT_FRAGMENT.get()))
                )
        );
        this.add(TCRBossEntities.HARBINGER_HUMANOID.get(), LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(TCRItems.MECH_FRAGMENT.get()))
                )
        );
        this.add(TCRBossEntities.IGNIS_HUMANOID.get(), LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(TCRItems.FLAME_FRAGMENT.get()))
                )
        );
        this.add(TCRBossEntities.LEVIATHAN_HUMANOID.get(), LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(TCRItems.ABYSS_FRAGMENT.get()))
                )
        );
        this.add(TCRBossEntities.SCYLLA_HUMANOID.get(), LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(TCRItems.STORM_FRAGMENT.get()))
                )
        );
        this.add(TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get(), LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(TCRItems.ENDER_FRAGMENT.get()))
                )
        );
        this.add(TCRBossEntities.MALEDICTUS_HUMANOID.get(), LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(TCRItems.SOUL_FRAGMENT.get()))
                )
        );
        this.add(TCRBossEntities.NETHERITE_HUMANOID.get(), LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(TCRItems.NETHERITE_FRAGMENT.get()))
                )
        );
        this.add(TCRBossEntities.IGNIS_SHIELD.get(), emptyLootTable());

        this.add(TCRBossEntities.GILDED_HUNTER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TCRItems.NINE_HEAVEN_DARKSTEEL.get())
                                .setWeight(75)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                        .add(LootItem.lootTableItem(TCRItems.NINE_HEAVEN_DARKSTEEL.get())
                                .setWeight(25)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(12.0F))
                        .add(LootItem.lootTableItem(ItemRegistry.ARCANE_ESSENCE.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(BTItems.SKY_MONOLITH_KEY.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TCRItems.DIVINE_FRAGMENT.get()))));
        this.add(TCRBossEntities.VALKYRIE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TCRItems.NINE_HEAVEN_DARKSTEEL.get())
                                .setWeight(75)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))
                        .add(LootItem.lootTableItem(TCRItems.NINE_HEAVEN_DARKSTEEL.get())
                                .setWeight(25)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(24.0F))
                        .add(LootItem.lootTableItem(ItemRegistry.ARCANE_ESSENCE.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(BTItems.SKY_MONOLITH_KEY.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TCRItems.DIVINE_FRAGMENT.get()))));
        this.add(TCRBossEntities.GOLDEN_EXECUTOR.get(),  LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TCRItems.BLOOD_LOTUS.get())
                                .setWeight(75)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))
                        .add(LootItem.lootTableItem(TCRItems.BLOOD_LOTUS.get())
                                .setWeight(25)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(24.0F))
                        .add(LootItem.lootTableItem(ItemRegistry.ARCANE_ESSENCE.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(BTItems.NETHER_MONOLITH_KEY.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ItemRegistry.DECREPIT_KEY.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ItemRegistry.DIVINE_SOULSHARD.get()))));
        this.add(TCRBossEntities.EVENING_GHOST.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TCRItems.BLOOD_LOTUS.get())
                                .setWeight(75)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                        .add(LootItem.lootTableItem(TCRItems.BLOOD_LOTUS.get())
                                .setWeight(25)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(12.0F))
                        .add(LootItem.lootTableItem(ItemRegistry.ARCANE_ESSENCE.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(BTItems.NETHER_MONOLITH_KEY.get()))));
        this.add(TCRBossEntities.CITADEL_KEEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ItemRegistry.CINDER_ESSENCE.get())
                                .setWeight(75)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                        .add(LootItem.lootTableItem(ItemRegistry.CINDER_ESSENCE.get())
                                .setWeight(25)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP)
                                .when(LootItemRandomChanceCondition.randomChance(0.2f))))); 
    }

    public LootTable.Builder emptyLootTable() {
        return LootTable.lootTable();
    }

    public LootTable.Builder fromEntityLootTable(EntityType<?> parent) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootTableReference.lootTableReference(parent.getDefaultLootTable())));
    }

    private static LootTable.Builder sheepLootTableBuilderWithDrop(ItemLike wool) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(wool))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootTableReference.lootTableReference(EntityType.SHEEP.getDefaultLootTable())));
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        ArrayList<RegistryObject<EntityType<?>>> list = new ArrayList<>(TCREntities.REGISTRY.getEntries());
        list.addAll(TCRBossEntities.REGISTRY.getEntries());
        return list.stream().map(RegistryObject::get);
    }
}