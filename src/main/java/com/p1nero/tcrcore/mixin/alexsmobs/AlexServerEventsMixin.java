package com.p1nero.tcrcore.mixin.alexsmobs;

import com.github.alexthe666.alexsmobs.event.ServerEvents;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerEvents.class)
public class AlexServerEventsMixin {

    @Inject(method = "onPlayerLoggedIn", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event, CallbackInfo ci) {
        ci.cancel();
    }

}
