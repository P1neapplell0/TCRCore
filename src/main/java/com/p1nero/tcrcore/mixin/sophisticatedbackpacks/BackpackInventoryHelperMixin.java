package com.p1nero.tcrcore.mixin.sophisticatedbackpacks;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IPickupResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeHandler;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 尝试修复拾取升级影响进度的bug，本体不加背包但是防止bug还是加一下
 */
@Mixin(InventoryHelper.class)
public abstract class BackpackInventoryHelperMixin {

    @Shadow(remap = false)
    private static void playPickupSound(Level level, @NotNull Player player) {
    }

    @Inject(method = "runPickupOnPickupResponseUpgrades(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/p3pp3rf1y/sophisticatedcore/upgrades/UpgradeHandler;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void tcr$runPickupOnPickupResponseUpgrades(Level world, Player player, UpgradeHandler upgradeHandler, ItemStack remainingStack, boolean simulate, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack copy = remainingStack.copy();
        ItemEntity temp = new ItemEntity(world, 0, 0, 0, copy);
        for(IPickupResponseUpgrade pickupUpgrade : upgradeHandler.getWrappersThatImplement(IPickupResponseUpgrade.class)) {
            int countBeforePickup = remainingStack.getCount();
            remainingStack = pickupUpgrade.pickup(world, remainingStack, simulate);
            if (!simulate && player != null && remainingStack.getCount() != countBeforePickup) {
                ForgeEventFactory.firePlayerItemPickupEvent(player, temp, copy);
                playPickupSound(world, player);
            }

            if (remainingStack.isEmpty()) {
                cir.setReturnValue(ItemStack.EMPTY);
            }
        }
        cir.setReturnValue(remainingStack);
        temp.discard();
    }

}
