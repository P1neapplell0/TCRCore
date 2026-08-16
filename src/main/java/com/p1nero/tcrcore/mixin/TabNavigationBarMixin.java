package com.p1nero.tcrcore.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.p1nero.tcrcore.client.gui.TCRMenuBackgroundRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.tabs.TabNavigationBar;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TabNavigationBar.class)
public abstract class TabNavigationBarMixin {

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"
            )
    )
    private void tcr$replaceHeaderSeparator(GuiGraphics guiGraphics,
                                             ResourceLocation texture,
                                             int x, int y,
                                             float uOffset, float vOffset,
                                             int width, int height,
                                             int textureWidth, int textureHeight,
                                             Operation<Void> original) {
        original.call(guiGraphics,
                TCRMenuBackgroundRenderer.replaceDirtSeparator(texture),
                x, y, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }
}
