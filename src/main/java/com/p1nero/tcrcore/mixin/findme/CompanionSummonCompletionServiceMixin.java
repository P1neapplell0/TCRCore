package com.p1nero.tcrcore.mixin.findme;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.p1nero.tcrcore.compat.FindMeDragonCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "com.kuzhi.findme.server.CompanionSummonCompletionService")
public abstract class CompanionSummonCompletionServiceMixin {

    @ModifyExpressionValue(
            method = "completeMountSummon",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/kuzhi/findme/server/CompanionCinematicLandingService;shouldDirectFlyingRescue(Lnet/minecraft/server/level/ServerPlayer;)Z"
            ),
            remap = false
    )
    private static boolean tcr$useFlyingApproachForDragon(
            boolean directRescue,
            ServerPlayer player,
            LivingEntity living
    ) {
        return directRescue && !FindMeDragonCompat.canFly(living);
    }
}
