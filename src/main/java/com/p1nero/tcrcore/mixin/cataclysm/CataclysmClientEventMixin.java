package com.p1nero.tcrcore.mixin.cataclysm;

import com.github.L_Ender.cataclysm.client.event.ClientEvent;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientEvent.class)
public class CataclysmClientEventMixin {
    @Inject(method = "MovementInput", at = @At("HEAD"), cancellable = true, remap = false)
    public void tcr$movementInput(MovementInputUpdateEvent event, CallbackInfo ci) {
        ci.cancel();
    }
}
