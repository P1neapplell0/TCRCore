package com.p1nero.tcrcore.mixin.epicfightx;

import com.asanginxst.epicfightx.utils.AnimationUtils;
import com.nameless.indestructible.world.capability.Utils.IAdvancedCapability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@Mixin(AnimationUtils.class)
public class EFXAnimationUtilsMixin {

    @Inject(method = "calculateWeaponSpeedWithCap", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$calculateWeaponSpeedWithCap(DynamicAnimation animation, LivingEntityPatch<?> entityPatch, float speedCap, float defaultBasisSpeed, CallbackInfoReturnable<Float> cir) {
        if(animation instanceof AttackAnimation) {
            if(entityPatch instanceof IAdvancedCapability advancedCapability) {
                cir.setReturnValue(advancedCapability.getAttackSpeed());
            }
        }
    }

}
