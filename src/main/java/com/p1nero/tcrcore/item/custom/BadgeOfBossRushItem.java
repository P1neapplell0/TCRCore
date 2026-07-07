package com.p1nero.tcrcore.item.custom;

import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BadgeOfBossRushItem extends ProofOfAdventureItem{

    public BadgeOfBossRushItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, level, list, flag);
        String timeUsed = itemStack.getOrCreateTag().getString("time_used");
        String ownerName = itemStack.getOrCreateTag().getString("owner_name");
        String difficulty = itemStack.getOrCreateTag().getString("difficulty");
        list.add(TCRCoreMod.getInfo("time_used", timeUsed));
        list.add(TCRCoreMod.getInfo("difficulty", Component.translatable("options.difficulty." + difficulty)));
        list.add(TCRCoreMod.getInfo("dragon_owner", ownerName));
    }
}
