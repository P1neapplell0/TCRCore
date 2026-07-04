package com.p1nero.tcrcore.mixin.wom;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reascer.wom.world.entity.WOMEntities;
import reascer.wom.world.item.WOMItems;
import reascer.wom.world.loot.WOMLootDropTables;
import yesman.epicfight.api.forgeevent.SkillLootTableRegistryEvent;

/**
 * 移除所有技能书掉落，仅保留实体掉落
 */
@Mixin(WOMLootDropTables.class)
public class WOMDropLootTableMixin {

    @Inject(method = "SkillLootDrop", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$loot(SkillLootTableRegistryEvent event, CallbackInfo ci) {
        ci.cancel();
        event
                .add(WOMEntities.LUPUS_REX.get(),
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                )
                .add(WOMEntities.EVIL_SKELETON.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.COAL)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                )
                .add(WOMEntities.EVIL_SKELETON.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                )
                .add(WOMEntities.EVIL_SKELETON.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                )
                .add(WOMEntities.EVIL_SKELETON.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Blocks.WITHER_SKELETON_SKULL))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.2F, 0.1F))
                )
                .add(WOMEntities.EVIL_SKELETON.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(WOMItems.DEMON_SEAL.get()))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F))
                )
                .add(WOMEntities.HOLLOW.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.IRON_NUGGET)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 8.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))
                )
                .add(WOMEntities.LYCANTH.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(WOMItems.FUR.get()))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.3F, 0.15F))
                )
                .add(WOMEntities.LYCANTH.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(WOMItems.ClAW.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                )
                .add(WOMEntities.SAULOMONK.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(WOMItems.DEMON_BRACELET.get()))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F))
                                .add(LootItem.lootTableItem(WOMItems.CELERITY_BRACELET.get()))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F))
                                .add(LootItem.lootTableItem(WOMItems.OBSIDIAN_BRACELET.get()))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F))
                );
    }

}
