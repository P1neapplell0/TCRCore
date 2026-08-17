package com.p1nero.tcrcore.mixin;

import com.apexhosting.partnerlink.ApexPartnerLinkScreen;
import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.TitleScreenModUpdateIndicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

import static net.minecraft.client.gui.screens.TitleScreen.COPYRIGHT_TEXT;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    @Shadow
    @Nullable
    private RealmsNotificationsScreen realmsNotificationsScreen;

    @Shadow
    protected abstract boolean realmsNotificationsEnabled();

    @Shadow(remap = false)
    private TitleScreenModUpdateIndicator modUpdateNotification;

    @Shadow
    protected abstract void createDemoMenuOptions(int p_96773_, int p_96774_);

    @Shadow
    protected abstract void createNormalMenuOptions(int p_96764_, int p_96765_);

    @Shadow
    @Nullable
    private SplashRenderer splash;

    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "init", cancellable = true)
    private void tcr$addCustomButton(CallbackInfo ci) {
        ci.cancel();
        if (this.splash == null) {
            this.splash = this.minecraft.getSplashManager().getSplash();
        }

        int i = this.font.width(COPYRIGHT_TEXT);
        int j = this.width - i - 2;
        int k = 24;
        int l = this.height / 4 + 48;
        Button modButton = null;
        if (this.minecraft.isDemo()) {
            this.createDemoMenuOptions(l, 24);
        } else {
            this.createNormalMenuOptions(l, 24);
            modButton = this.addRenderableWidget(Button.builder(Component.translatable("fml.menu.mods"), button -> this.minecraft.setScreen(new net.minecraftforge.client.gui.ModListScreen(this)))
                    .pos(this.width / 2 - 100, l + 24 * 2).size(98, 20).build());
        }
        modUpdateNotification = net.minecraftforge.client.gui.TitleScreenModUpdateIndicator.init(((TitleScreen)(Object)this), modButton);

        this.addRenderableWidget(new ImageButton(this.width / 2 - 124, l + 72 + 12, 20, 20, 0, 106, 20, Button.WIDGETS_LOCATION, 256, 256, (p_280830_) -> {
            this.minecraft.setScreen(new LanguageSelectScreen(this, this.minecraft.options, this.minecraft.getLanguageManager()));
        }, Component.translatable("narrator.button.language")));
        this.addRenderableWidget(Button.builder(Component.translatable("menu.options"), (p_280838_) -> {
            this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
        }).bounds(this.width / 2 - 100, l + 72 + 12, 98, 20).build());
        this.addRenderableWidget(Button.builder(Component.translatable("menu.quit"), (p_280831_) -> {
            this.minecraft.stop();
        }).bounds(this.width / 2 + 2, l + 72 + 12, 98, 20).build());
        this.addRenderableWidget(new ImageButton(this.width / 2 + 104, l + 72 + 12, 20, 20, 0, 0, 20, Button.ACCESSIBILITY_TEXTURE, 32, 64, (p_280835_) -> {
            this.minecraft.setScreen(new AccessibilityOptionsScreen(this, this.minecraft.options));
        }, Component.translatable("narrator.button.accessibility")));
        this.minecraft.setConnectedToRealms(false);
        if (this.realmsNotificationsScreen == null) {
            this.realmsNotificationsScreen = new RealmsNotificationsScreen();
        }

        if (this.realmsNotificationsEnabled()) {
            this.realmsNotificationsScreen.init(this.minecraft, this.width, this.height);
        }

        int buttonWidth = 200;
        int buttonHeight = 20;
        int x = (this.width - buttonWidth) / 2;
        int y = this.height / 4 + 48 + 72 + 12 + 24;

        this.addRenderableWidget(Button.builder(Component.literal("Get a Server!"), button -> {
            Minecraft.getInstance().setScreen(new ApexPartnerLinkScreen());
//            Util.getPlatform().openUri("https://apexhost.gg/P1nero");
        }).bounds(x, y, buttonWidth, buttonHeight).build());
    }
}