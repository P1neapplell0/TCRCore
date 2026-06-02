package com.p1nero.tcrcore.item.custom;

import com.github.L_Ender.cataclysm.items.CuriosItem.CuriosItem;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.function.Predicate;

public class ProofOfAdventureItem extends CuriosItem implements ICurioItem {

    private Predicate<Player> predicate;

    public ProofOfAdventureItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if(slotContext.entity() instanceof Player player && !player.isCreative()) {
            if(predicate != null && predicate.test(player)) {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), true);
                return false;
            }
        }
        return true;
    }

    public ProofOfAdventureItem withPredicate(Predicate<Player> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable(this.getDescriptionId() + ".usage").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        return true;
    }
}
