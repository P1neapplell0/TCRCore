package com.p1nero.tcrcore.dialog.custom.handler.no_entity;

import com.p1nero.dialog_lib.api.component.DialogNode;
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
public class ResetGameProgressScreenHandler {
    public static final String name = "reset_game";

    public static void addScreen() {
        ScreenDialogueBuilder builder = new ScreenDialogueBuilder(TCRCoreMod.MOD_ID, name);
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if(localPlayer == null) {
            return;
        }
        StreamDialogueScreenBuilder screenBuilder = new StreamDialogueScreenBuilder(Component.empty(), ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name));

        screenBuilder.setCustomTitle(Component.literal("").append(TCREntities.AINE.get().getDescription().copy().withStyle(ChatFormatting.AQUA)).append(": \n"));

        DialogNode root = new DialogNode(builder.ans(0))
                .addChild(new DialogNode(builder.ans(1), builder.opt(0))
                        .addLeaf(builder.opt(0), 1)
                        .addLeaf(builder.opt(1)))
                .addLeaf(builder.opt(1));

        Minecraft.getInstance().setScreen(screenBuilder.buildWith(root));
    }

    public static void onGenerateZH(TCRLangProvider generator) {
        generator.addScreenAns(name, 0, "确定要重置所有游戏进度吗？重置进度将保留所有物品数据，仅重置所有任务进度。并且在单人模式下，所有怪物将获得增强！");
        generator.addScreenOpt(name, 0, "§4[确定]");
        generator.addScreenAns(name, 1, "§4你确定吗？该操作不可撤回！");
        generator.addScreenOpt(name, 1, "[再想想]");
    }

    public static void onGenerateEN(TCRLangProvider generator) {
        generator.addScreenAns(name, 0, "Are you sure you want to reset all game progress? Resetting progress will retain all item data and only reset all task progress. Additionally, in single-player mode, all monsters will be enhanced!");
        generator.addScreenOpt(name, 0, "§4[Confirm]");
        generator.addScreenAns(name, 1, "§4Are you absolutely sure? This action cannot be undone!");
        generator.addScreenOpt(name, 1, "[Think again]");
    }

}
