package com.p1nero.tcrcore.mixin.indestructible;

import com.nameless.indestructible.world.ai.CombatBehaviors.WanderMotionSet;
import com.nameless.indestructible.world.ai.goal.AdvancedCombatGoal;
import com.nameless.indestructible.world.capability.Utils.IAdvancedCapability;
import com.p1nero.tcr_bosses.entity.custom.BaseSmallBossEntity;
import com.p1nero.tcrcore.utils.EntityUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Enemy;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import java.util.List;

@Mixin(AdvancedCombatGoal.class)
public class AdvancedCombatGoalMixin<T extends MobPatch<?>> {

    @Shadow(remap = false)
    @Final
    protected T mobPatch;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tcr$tick(CallbackInfo ci) {
        if(tcr$check()) {
            ci.cancel();
        }
    }

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void tcr$canUse(CallbackInfoReturnable<Boolean> cir) {
        if(tcr$check()) {
            cir.setReturnValue(false);
        }
    }

    @Unique
    private boolean tcr$check() {
        List<Entity> list = EntityUtils.getNearByEntities(this.mobPatch.getOriginal(), 6);
        if(this.mobPatch.getOriginal() instanceof BaseSmallBossEntity) {
            return false;
        }
        if(list.stream().anyMatch(entity -> {
            if(entity instanceof Enemy) {
                LivingEntityPatch<?> livingEntityPatch = EpicFightCapabilities.getEntityPatch(entity, LivingEntityPatch.class);
                if(livingEntityPatch == null) {
                    return false;
                }
                if(this.mobPatch.getTarget() != null && this.mobPatch.getTarget() == livingEntityPatch.getTarget()) {
                    if(this.mobPatch.getTarget().distanceTo(this.mobPatch.getOriginal()) < this.mobPatch.getTarget().distanceTo(livingEntityPatch.getOriginal())) {
                        return false;
                    }
                }
                return livingEntityPatch.getEntityState().inaction();
            }
            return false;
        })) {
            if(this.mobPatch instanceof IAdvancedCapability iAdvancedCapability) {
                iAdvancedCapability.actStrafing(new WanderMotionSet(20, 10, -0.8f, 0f));
                return true;
            }
        }
        return false;
    }

}
