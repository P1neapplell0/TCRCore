package com.p1nero.tcrcore.mixin.wom;

import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import reascer.wom.animation.attacks.BasicMultipleAttackAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@Mixin(BasicMultipleAttackAnimation.class)
public abstract class WOMBasicMultipleAttackAnimationMixin extends AttackAnimation {

    public WOMBasicMultipleAttackAnimationMixin(float transitionTime, float antic, float preDelay, float contact, float recovery, @Nullable Collider collider, Joint colliderJoint, AnimationManager.AnimationAccessor<? extends AttackAnimation> accessor, AssetAccessor<? extends Armature> armature) {
        super(transitionTime, antic, preDelay, contact, recovery, collider, colliderJoint, accessor, armature);
    }

    @Inject(method = "getCoordVector", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$getCoordVector(LivingEntityPatch<?> entitypatch, AssetAccessor<? extends DynamicAnimation> dynamicAnimation, CallbackInfoReturnable<Vec3> cir) {
        cir.setReturnValue(super.getCoordVector(entitypatch, dynamicAnimation));
    }

    @Inject(method = "hurtCollidingEntities", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$hurtCollidingEntities(LivingEntityPatch<?> entitypatch, float prevElapsedTime, float elapsedTime, EntityState prevState, EntityState state, Phase phase, CallbackInfo ci) {
        super.hurtCollidingEntities(entitypatch, prevElapsedTime, elapsedTime, prevState, state, phase);
        ci.cancel();
    }

}
