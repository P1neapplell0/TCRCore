package com.p1nero.tcrcore.mixin.iss;

import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import io.redspace.ironsspellbooks.item.CinderousSoulcallerItem;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(CinderousSoulcallerItem.class)
public class CinderousSoulcallerItemMixin {

    /**
     * 在物品使用前检查附近72格内是否存在指定Boss实体，
     * 若存在则阻止使用并提示玩家。
     */
    @Inject(method = "use", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$use(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (!level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            AABB aabb = player.getBoundingBox().inflate(72);
            List<LivingEntity> bosses = level.getEntitiesOfClass(LivingEntity.class, aabb, entity ->
                    entity.getType() == TCRBossEntities.GOLDEN_EXECUTOR.get() ||
                            entity.getType() == EntityRegistry.FIRE_BOSS.get()
            );
            if (!bosses.isEmpty()) {
                player.displayClientMessage(
                        TCRCoreMod.getInfo("boss_nearby_cannot_use_cinderous_soulcaller").withStyle(ChatFormatting.GOLD),
                        true
                );
                cir.setReturnValue(InteractionResultHolder.fail(stack));
            }
        }
    }

    /**
     * 取消物品消耗，使用后不减少物品数量。
     */
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private void tcr$cancelShrink(ItemStack instance, int amount) {
        // 不消耗物品
    }

}
