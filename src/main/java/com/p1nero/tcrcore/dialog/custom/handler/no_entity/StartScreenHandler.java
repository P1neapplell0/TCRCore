package com.p1nero.tcrcore.dialog.custom.handler.no_entity;

import com.p1nero.dialog_lib.client.screen.builder.StreamDialogueScreenBuilder;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.datagen.lang.TCRLangProvider;
import com.p1nero.tcrcore.entity.TCREntities;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StartScreenHandler {
    public static final String name = "start_screen";

    public static void addScreen() {
        ScreenDialogueBuilder builder = new ScreenDialogueBuilder(TCRCoreMod.MOD_ID, name);
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if(localPlayer == null) {
            return;
        }
        StreamDialogueScreenBuilder screenBuilder = new StreamDialogueScreenBuilder(Component.empty(), ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name));

        screenBuilder.setCustomTitle(Component.literal("").append(localPlayer.getDisplayName().copy().withStyle(ChatFormatting.AQUA)).append(": \n"));

        screenBuilder.start(builder.ans(0, TCREntities.AINE.get().getDescription(), TCREntities.AINE.get().getDescription()))
                        .addOption(builder.opt(0), builder.ans(1, TCREntities.AINE.get().getDescription()))
                                .addFinalOption(builder.opt(1));

        Minecraft.getInstance().setScreen(screenBuilder.build());
    }

    public static void onGenerateZH(TCRLangProvider generator) {
        generator.addScreenAns(name, 0, "%s！%s！（你的嘴里不停地念着这个名字）");
        generator.addScreenOpt(name, 0, "继续");
        generator.addScreenAns(name, 1, "这是什么地方？好大的圣殿！先进去看看吧，说不定%s在里面等我");
        generator.addScreenOpt(name, 1, "进入圣殿");
    }

    public static void onGenerateEN(TCRLangProvider generator) {
        generator.addScreenAns(name, 0, "%s! %s! (You keep repeating this name over and over.)");
        generator.addScreenOpt(name, 0, "Continue");
        generator.addScreenAns(name, 1, "What is this place? Such a grand sanctuary! Let’s go inside and take a look—maybe %s is waiting for me in there.");
        generator.addScreenOpt(name, 1, "Enter the Sanctuary");
    }

}
