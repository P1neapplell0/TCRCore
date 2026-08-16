package com.p1nero.tcrcore.mixin;

import com.p1nero.tcrcore.client.gui.TCRMenuBackgroundRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SelectWorldScreen.class)
public abstract class SelectWorldScreenMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void tcr$renderBackground(GuiGraphics guiGraphics, int mouseX,
                                      int mouseY, float partialTick,
                                      CallbackInfo ci) {
        Screen screen = (Screen) (Object) this;
        TCRMenuBackgroundRenderer.renderRegion(
                guiGraphics, screen.width, screen.height,
                0, 0, screen.width, screen.height);
    }
}
