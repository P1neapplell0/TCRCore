package com.p1nero.tcrcore.client.gui;

import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public final class TCRMenuBackgroundRenderer {

    private static final ResourceLocation BLURRED_COVER = ResourceLocation.fromNamespaceAndPath(
            TCRCoreMod.MOD_ID, "textures/menu/background_blurred.png");
    private static final ResourceLocation DARK_DIRT_BACKGROUND =
            ResourceLocation.withDefaultNamespace("textures/gui/options_background.png");
    private static final ResourceLocation LIGHT_DIRT_BACKGROUND =
            ResourceLocation.withDefaultNamespace("textures/gui/light_dirt_background.png");
    private static final ResourceLocation HEADER_SEPARATOR =
            ResourceLocation.withDefaultNamespace("textures/gui/header_separator.png");
    private static final ResourceLocation FOOTER_SEPARATOR =
            ResourceLocation.withDefaultNamespace("textures/gui/footer_separator.png");
    private static final ResourceLocation CLEAN_HEADER_SEPARATOR =
            ResourceLocation.fromNamespaceAndPath(
                    TCRCoreMod.MOD_ID, "textures/gui/header_separator.png");
    private static final ResourceLocation CLEAN_FOOTER_SEPARATOR =
            ResourceLocation.fromNamespaceAndPath(
                    TCRCoreMod.MOD_ID, "textures/gui/footer_separator.png");
    private static final int TEXTURE_WIDTH = 480;
    private static final int TEXTURE_HEIGHT = 270;

    private TCRMenuBackgroundRenderer() {
    }

    public static boolean isDirtBackground(ResourceLocation texture) {
        return DARK_DIRT_BACKGROUND.equals(texture) || LIGHT_DIRT_BACKGROUND.equals(texture);
    }

    public static ResourceLocation replaceDirtSeparator(ResourceLocation texture) {
        if (HEADER_SEPARATOR.equals(texture)) {
            return CLEAN_HEADER_SEPARATOR;
        }
        if (FOOTER_SEPARATOR.equals(texture)) {
            return CLEAN_FOOTER_SEPARATOR;
        }
        return texture;
    }

    public static void renderRegion(GuiGraphics guiGraphics, int width, int height,
                                    int regionX, int regionY,
                                    int regionWidth, int regionHeight) {
        if (width <= 0 || height <= 0) {
            return;
        }

        int left = Mth.clamp(regionX, 0, width);
        int top = Mth.clamp(regionY, 0, height);
        int right = Mth.clamp(regionX + regionWidth, 0, width);
        int bottom = Mth.clamp(regionY + regionHeight, 0, height);
        if (left >= right || top >= bottom) {
            return;
        }

        float scale = Math.max(width / (float) TEXTURE_WIDTH,
                height / (float) TEXTURE_HEIGHT);
        int renderedWidth = Mth.ceil(TEXTURE_WIDTH * scale);
        int renderedHeight = Mth.ceil(TEXTURE_HEIGHT * scale);
        int x = (width - renderedWidth) / 2;
        int y = (height - renderedHeight) / 2;

        guiGraphics.enableScissor(left, top, right, bottom);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(BLURRED_COVER, x, y, renderedWidth, renderedHeight,
                0.0F, 0.0F, TEXTURE_WIDTH, TEXTURE_HEIGHT,
                TEXTURE_WIDTH, TEXTURE_HEIGHT);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.disableScissor();
    }
}
