package com.p1nero.tcrcore.mixin.findme;

import com.kuzhi.findme.server.CompanionManager;
import com.p1nero.tcrcore.compat.FindMeDragonCompat;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CompanionManager.class)
public abstract class CompanionManagerMixin {

    @Inject(method = "forceFlyingAnimationPose", at = @At("HEAD"), remap = false)
    private static void tcr$setDragonFlightMode(LivingEntity living, CallbackInfo ci) {
        FindMeDragonCompat.forceCinematicFlightPose(living);
    }
}
