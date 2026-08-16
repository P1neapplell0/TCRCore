package com.p1nero.tcrcore.mixin;

import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.TabButton;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/** Uses the dirt-free 1.21 tab artwork while preserving 1.20 tab behavior. */
@Mixin(TabButton.class)
public abstract class TabButtonMixin {

    private static final ResourceLocation TAB = tcr$texture("tab");
    private static final ResourceLocation TAB_HIGHLIGHTED = tcr$texture("tab_highlighted");
    private static final ResourceLocation TAB_SELECTED = tcr$texture("tab_selected");
    private static final ResourceLocation TAB_SELECTED_HIGHLIGHTED =
            tcr$texture("tab_selected_highlighted");

    @Redirect(
            method = "renderWidget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitNineSliced(Lnet/minecraft/resources/ResourceLocation;IIIIIIIIIIII)V"
            )
    )
    private void tcr$replaceDirtTabTexture(GuiGraphics guiGraphics,
                                           ResourceLocation original,
                                           int x, int y, int width, int height,
                                           int leftBorder, int topBorder,
                                           int rightBorder, int bottomBorder,
                                           int sourceWidth, int sourceHeight,
                                           int uOffset, int vOffset) {
        ResourceLocation texture = switch (vOffset / 24) {
            case 0 -> TAB_SELECTED;
            case 1 -> TAB_SELECTED_HIGHLIGHTED;
            case 3 -> TAB_HIGHLIGHTED;
            default -> TAB;
        };

        int middleWidth = Math.max(0, width - 4);
        guiGraphics.blit(texture, x, y, 2, height,
                0.0F, 0.0F, 2, 24, 130, 24);
        if (middleWidth > 0) {
            guiGraphics.blit(texture, x + 2, y, middleWidth, height,
                    2.0F, 0.0F, 126, 24, 130, 24);
        }
        guiGraphics.blit(texture, x + width - 2, y, 2, height,
                128.0F, 0.0F, 2, 24, 130, 24);
    }

    private static ResourceLocation tcr$texture(String name) {
        return ResourceLocation.fromNamespaceAndPath(
                TCRCoreMod.MOD_ID, "textures/gui/tab/" + name + ".png");
    }
}
