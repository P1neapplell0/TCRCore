package com.p1nero.tcrcore.mixin.aether;

import com.aetherteam.aether.client.event.listeners.MenuListener;
import net.minecraftforge.client.event.ScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * cancel Aether's menu button
 */
@Mixin(MenuListener.class)
public class MenuHooksMixin {

    @Inject(method = "onGuiInitialize", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onGuiInitialize(ScreenEvent.Init.Post event, CallbackInfo ci) {
        ci.cancel();
    }

}
