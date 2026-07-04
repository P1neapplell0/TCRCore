package com.p1nero.tcrcore.mixin.wom;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraftforge.event.LootTableLoadEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reascer.wom.world.item.WOMItems;
import reascer.wom.world.loot.WOMChestLootTables;

@Mixin(WOMChestLootTables.class)
public class WOMChestLootTableMixin {

    @Inject(method = "modifyVanillaLootPools", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$loot(LootTableLoadEvent event, CallbackInfo ci) {

        if (event.getName().equals(BuiltInLootTables.SIMPLE_DUNGEON)) {
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.HERRSCHER.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.GESETZ.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.TORMENTED_MIND.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.RUINE.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.SOLAR.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.ANTITHEUS.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.NAPOLEON.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.SATSUJIN.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.AGONY.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.MOONLESS.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(100)).build());
        }

        if (event.getName().equals(BuiltInLootTables.END_CITY_TREASURE)) {
            event.getTable().addPool(LootPool.lootPool().add(LootItem.lootTableItem(WOMItems.ENDER_BLASTER.get()).setWeight(1)).add(LootItem.lootTableItem(Items.AIR).setWeight(20)).build());
        }

        ci.cancel();
    }

}
