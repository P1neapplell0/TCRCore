package com.p1nero.tcrcore.events;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.mojang.blaze3d.vertex.PoseStack;
import com.p1nero.battle_field1.worldgen.PBF1Dimensions;
import com.p1nero.dialog_lib.events.ClientNpcEntityDialogueEvent;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.client.TCRKeyMappings;
import com.p1nero.tcrcore.client.gui.*;
import com.p1nero.tcrcore.dialog.custom.handler.HandleIronGolemDialog;
import com.p1nero.tcrcore.dialog.custom.handler.HandleVillagerDialog;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shelmarow.combat_evolution.api.event.ShowExecutionIconEvent;

import java.util.*;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID, value = Dist.CLIENT)
public class ClientForgeEvents {

    public static Set<Button> buttonsInCreateWorldScreen = new HashSet<>();

    public static final ResourceLocation BACKGROUND_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/light_dirt_background.png");

    public static MutableComponent component = null, component2 = null, component3 = null;
    public static List<FormattedCharSequence> component2Lines = null;

    public static void initSamsaraTip() {
        if (component == null) {
            Font font = Minecraft.getInstance().font;
            component = TCRCoreMod.getInfo("exit_spectator_in_pbf1", TCRKeyMappings.EXIT_SPECTATOR.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD));
            component2 = TCRCoreMod.getInfo("use_command_to_modify_limit1");
            component3 = TCRCoreMod.getInfo("use_command_to_modify_limit2", "/gamerule maxInfiniteSamsaraPlayerCount <count>");
            int wrapWidth = font.width(component);

            if (wrapWidth < 100) {
                wrapWidth = 100;
            }

            component2Lines = font.split(component2, wrapWidth);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event){
        if(event.phase == TickEvent.Phase.END) {
            CustomQuestOverlayRenderer.tick();
            BTSpawnerBlockIndicatorRenderer.tick();
            if(!buttonsInCreateWorldScreen.isEmpty()) {
                buttonsInCreateWorldScreen.forEach(button -> {
                    if(button.isHovered()) {
                        int dx = (int)(Math.random() * 100) - 50;
                        int dy = (int)(Math.random() * 100) - 50;
                        button.setPosition(button.getX() + dx, button.getY() + dy);
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Pre event) {
        if(!Minecraft.getInstance().isPaused() && Minecraft.getInstance().screen == null && Minecraft.getInstance().player != null) {
            if(Minecraft.getInstance().player.level().dimension() == PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY && Minecraft.getInstance().player.isSpectator()) {

                if (component == null) {
                    initSamsaraTip();
                }

                GuiGraphics guiGraphics = event.getGuiGraphics();
                Font font = Minecraft.getInstance().font;
                PoseStack poseStack = guiGraphics.pose();
                poseStack.pushPose();

                int x = 10;
                int y = 10;

                guiGraphics.drawString(font, component, x, y, 0xFFFFFF, true);
                y += font.lineHeight + 2;
                guiGraphics.drawString(font, component2, x, y, 0xFFFFFF, true);
                y += font.lineHeight + 2;
                guiGraphics.drawString(font, component3, x, y, 0xFFFFFF, true);//忽然不是很想提示如何破解
                //未知bug，拆不了= =，只能手动拆了
//                y += font.lineHeight + 2;
//                for (FormattedCharSequence line : component2Lines) {
//                    guiGraphics.drawString(font, line, x, y, 0xFFFFFF, true);
//                    y += font.lineHeight + 2;
//                }

                poseStack.popPose();
            }
        }
    }

    /**
     * 过时了，应该用dialog extension
     */
    @SubscribeEvent
    public static void onDialogSend(ClientNpcEntityDialogueEvent event) {
        if(event.getSelf() instanceof Villager villager) {
            HandleVillagerDialog.openDialogScreen(villager, event.getLocalPlayer(), event.getServerData());
        }
        if(event.getSelf() instanceof IronGolem ironGolem) {
            HandleIronGolemDialog.openDialogScreen(ironGolem, event.getLocalPlayer(), event.getServerData());
        }
    }

    @SubscribeEvent
    public static void onRenderBackground(ScreenEvent.BackgroundRendered event) {
        if(Minecraft.getInstance().level == null && !(event.getScreen() instanceof LevelLoadingScreen)) {
            event.getGuiGraphics().blit(BACKGROUND_LOCATION, 0, 0, 0, 0.0F, 0.0F, event.getScreen().width, event.getScreen().height, 32, 32);
//            event.getGuiGraphics().fill(0, 0, event.getScreen().width, event.getScreen().height, FastColor.ABGR32.color(255, 255, 255, 255));
        }

    }

    @SubscribeEvent
    public static void onShowExecutionIcon(ShowExecutionIconEvent event) {
        LivingEntity livingEntity = event.getTarget().getOriginal();
        if (livingEntity.hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

}