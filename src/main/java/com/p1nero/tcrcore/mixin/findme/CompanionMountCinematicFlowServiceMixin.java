package com.p1nero.tcrcore.mixin.findme;

import com.kuzhi.findme.common.CompanionMoveType;
import com.p1nero.tcrcore.compat.FindMeDragonCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.kuzhi.findme.server.CompanionMountCinematicFlowService")
public abstract class CompanionMountCinematicFlowServiceMixin {

    @Inject(
            method = {"scheduleMountCinematic", "scheduleDirectFlyingCatchCinematic"},
            at = @At("HEAD"),
            remap = false
    )
    private static void tcr$preserveDragonFlightPhysics(
            ServerPlayer player,
            LivingEntity mount,
            CompanionMoveType moveType,
            @Coerce Object mode,
            boolean restoredFromStorage,
            boolean sendArrivalMagic,
            CallbackInfo ci
    ) {
        if (moveType == CompanionMoveType.FLY) {
            FindMeDragonCompat.forceFlight(mount);
        }
    }

    @Inject(
            method = {"scheduleMountCinematic", "scheduleDirectFlyingCatchCinematic"},
            at = @At("TAIL"),
            remap = false
    )
    private static void tcr$startDragonDive(
            ServerPlayer player,
            LivingEntity mount,
            CompanionMoveType moveType,
            @Coerce Object mode,
            boolean restoredFromStorage,
            boolean sendArrivalMagic,
            CallbackInfo ci
    ) {
        if (moveType == CompanionMoveType.FLY && tcr$isFallingFlyingRescue(mode)) {
            FindMeDragonCompat.beginDiveRescue(mount);
        } else {
            FindMeDragonCompat.clearDiveRescue(mount);
            if (moveType == CompanionMoveType.FLY) {
                FindMeDragonCompat.forceFlight(mount);
            }
        }
    }

    @Inject(method = "restoreCinematicPhysics", at = @At("TAIL"), remap = false)
    private static void tcr$finishDragonFlight(
            @Coerce Object cinematic,
            LivingEntity mount,
            CallbackInfo ci
    ) {
        FindMeDragonCompat.finishCinematicFlight(mount);
    }

    private static boolean tcr$isFallingFlyingRescue(Object mode) {
        return mode instanceof Enum<?> value && "FALL_RESCUE_FLY".equals(value.name());
    }
}
