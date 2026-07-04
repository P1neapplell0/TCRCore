package com.p1nero.tcrcore.mixin.sophisticatedbackpacks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.*;
import net.p3pp3rf1y.sophisticatedcore.upgrades.magnet.MagnetUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.magnet.MagnetUpgradeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Consumer;

/**
 * 修磁铁升级无法触发拾取
 */
@Mixin(MagnetUpgradeWrapper.class)
public abstract class MagnetUpgradeWrapperMixin extends UpgradeWrapperBase<MagnetUpgradeWrapper, MagnetUpgradeItem>
        implements IContentsFilteredUpgrade, ITickableUpgrade, IPickupResponseUpgrade {

    @Shadow(remap = false)
    protected abstract boolean tryToInsertItem(ItemEntity itemEntity);

    @Shadow(remap = false)
    private static void playItemPickupSound(Level world, @NotNull Player player) {
    }

    @Shadow(remap = false)
    protected abstract boolean canNotPickup(Entity pickedUpEntity, @Nullable Entity entity);

    @Shadow(remap = false)
    @Final
    private ContentsFilterLogic filterLogic;
    @Unique
    private static final int COOLDOWN_TICKS = 10;
    @Unique
    private static final int FULL_COOLDOWN_TICKS = 40;

    protected MagnetUpgradeWrapperMixin(IStorageWrapper storageWrapper, ItemStack upgrade, Consumer<ItemStack> upgradeSaveHandler) {
        super(storageWrapper, upgrade, upgradeSaveHandler);
    }

    @Inject(method = "pickupItems", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$pickUpItems(Entity entity, Level world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        List<ItemEntity> itemEntities = world.getEntitiesOfClass(ItemEntity.class, new AABB(pos).inflate(upgradeItem.getRadius()), e -> true);
        if (itemEntities.isEmpty()) {
            cir.setReturnValue(COOLDOWN_TICKS);
            return;
        }

        Player player = entity instanceof Player ? (Player) entity : null;

        int cooldown = FULL_COOLDOWN_TICKS;
        for (ItemEntity itemEntity : itemEntities) {
            if(player != null) {
                int hook = ForgeEventFactory.onItemPickup(itemEntity, player);
                if (hook < 0) continue;
            }
            if (!itemEntity.isAlive() || itemEntity.pickupDelay == ItemEntity.INFINITE_PICKUP_DELAY || !filterLogic.matchesFilter(itemEntity.getItem()) || canNotPickup(itemEntity, entity)) {
                continue;
            }
            //防止被消耗
            ItemStack itemStack = itemEntity.getItem().copy();
            if (tryToInsertItem(itemEntity)) {
                if (player != null) {
                    ForgeEventFactory.firePlayerItemPickupEvent(player, itemEntity, itemStack);
                    playItemPickupSound(world, player);
                }
                cooldown = COOLDOWN_TICKS;
            }
        }
        cir.setReturnValue(cooldown);
    }

}
