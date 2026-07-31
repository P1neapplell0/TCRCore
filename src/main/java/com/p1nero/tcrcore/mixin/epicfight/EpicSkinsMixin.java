package com.p1nero.tcrcore.mixin.epicfight;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.online.EpicSkins;
import yesman.epicfight.client.world.capabilites.entitypatch.player.AbstractClientPlayerPatch;
import yesman.epicfight.config.ClientConfig;

@Mixin(EpicSkins.class)
public abstract class EpicSkinsMixin {

    @Inject(method = {"initEpicSkins", "initDefaultCape"}, at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$disableCapeSimulationWhenCosmeticsAreDisabled(AbstractClientPlayerPatch<?> playerPatch, CallbackInfo ci) {
        if (!ClientConfig.enableCosmetics) {
            ci.cancel();
        }
    }
}
