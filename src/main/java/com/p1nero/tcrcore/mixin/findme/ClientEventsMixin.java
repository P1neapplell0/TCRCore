package com.p1nero.tcrcore.mixin.findme;

import com.kuzhi.findme.client.ClientEvents;
import com.kuzhi.findme.client.CompanionWheelScreen;
import com.kuzhi.findme.client.VehicleWheelScreen;
import com.kuzhi.findme.common.CompanionKind;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientEvents.class)
public abstract class ClientEventsMixin {

    @Shadow(remap = false)
    private static boolean mountWasDown;

    @Shadow(remap = false)
    private static boolean companionWasDown;

    @Shadow(remap = false)
    private static boolean rawMountDown;

    @Shadow(remap = false)
    private static boolean rawCompanionDown;

    @Shadow(remap = false)
    private static boolean mountWheelOpened;

    @Shadow(remap = false)
    private static boolean companionWheelOpened;

    @Inject(method = "tickKey", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$ignoreKeyWithScreen(CompanionKind kind, KeyMapping key, CallbackInfo ci) {
        if (!tcr$hasBlockingScreen()) {
            return;
        }

        if (kind == CompanionKind.MOUNT) {
            mountWasDown = false;
            rawMountDown = false;
            mountWheelOpened = false;
        } else {
            companionWasDown = false;
            rawCompanionDown = false;
            companionWheelOpened = false;
        }
        ci.cancel();
    }

    @Inject(method = "isKeyDown", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$reportKeyUpWithScreen(CompanionKind kind, KeyMapping key, CallbackInfoReturnable<Boolean> cir) {
        if (tcr$hasBlockingScreen()) {
            cir.setReturnValue(false);
        }
    }

    private static boolean tcr$hasBlockingScreen() {
        Screen screen = Minecraft.getInstance().screen;
        return screen != null && !(screen instanceof CompanionWheelScreen) && !(screen instanceof VehicleWheelScreen);
    }
}
