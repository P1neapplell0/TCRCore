package com.p1nero.tcrcore.mixin.mimic;

import com.mojang.blaze3d.platform.Window;
import com.p1nero.tcrcore.entity.custom.mimic.TCRMimic;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import org.merlin204.mimic.client.gui.BossBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BossBar.class)
public class MimicBossBarMixin {

    @Inject(method = "drawName", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$renderName(String name, GuiGraphics guiGraphics, int y, LerpingBossEvent event, Entity boss, CallbackInfo ci) {
        if (boss instanceof TCRMimic) {
            ci.cancel();
            Font font = Minecraft.getInstance().font;
            Window window = Minecraft.getInstance().getWindow();
            Component nameComponent = boss.getDisplayName().copy().withStyle(ChatFormatting.GOLD);

            int nameWidth = font.width(nameComponent);

            int nameX = (window.getGuiScaledWidth() - nameWidth) / 2;
            int nameY = y + 5;

            guiGraphics.drawString(font, nameComponent, nameX, nameY, 0xFFFFFFFF, true);
        }
    }

}
