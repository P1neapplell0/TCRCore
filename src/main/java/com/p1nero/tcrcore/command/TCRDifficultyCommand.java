package com.p1nero.tcrcore.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.save_data.TCRMainLevelSaveData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Difficulty;

public class TCRDifficultyCommand {

    private static final SuggestionProvider<CommandSourceStack> DIFFICULTY_SUGGESTIONS =
            (context, builder) -> SharedSuggestionProvider.suggest(new String[]{"easy", "normal", "hard"}, builder);

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("tcrcore")
                        .then(Commands.literal("difficulty")
                                .then(Commands.argument("level", StringArgumentType.word())
                                        .suggests(DIFFICULTY_SUGGESTIONS)
                                        .executes(TCRDifficultyCommand::setDifficulty)
                                )
                        )
        );
    }

    private static int setDifficulty(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        MinecraftServer server = source.getServer();

        if (!source.hasPermission(2) && server.isDedicatedServer()) {
            source.sendFailure(TCRCoreMod.getInfo("difficulty_change_failed").withStyle(ChatFormatting.RED));
            return 0;
        }

        // 获取参数并解析为 Difficulty 枚举
        String levelName = StringArgumentType.getString(context, "level");
        Difficulty difficulty = Difficulty.byName(levelName);
        if (difficulty == null) {
            source.sendFailure(TCRCoreMod.getInfo("difficulty_change_failed").withStyle(ChatFormatting.RED));
            return 0;
        }

        // 通过 TCRMainLevelSaveData 保存难度（传入 MinecraftServer）
        TCRMainLevelSaveData.get(server).setDifficulty(difficulty);

        // 发送成功反馈
        source.sendSuccess(() -> TCRCoreMod.getInfo("difficulty_change_success", difficulty.getSerializedName()).withStyle(ChatFormatting.GREEN), true);
        return 1;
    }
}