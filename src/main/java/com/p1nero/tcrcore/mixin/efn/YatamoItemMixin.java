package com.p1nero.tcrcore.mixin.efn;

import com.hm.efn.gameasset.EFNEnchantment;
import com.hm.efn.item.custom.YamatoItem;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Set;

/**
 * 没有附魔的话就添加描述
 */
@Mixin(YamatoItem.class)
public class YatamoItemMixin {

    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void tcr$appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag, CallbackInfo ci) {
        Set<Enchantment> enchantments = EnchantmentHelper.getEnchantments(stack).keySet();
        if(!enchantments.contains(EFNEnchantment.YAMATO_GUARD.get())) {
            tooltip.add(TCRCoreMod.getInfo("yamato_skill_lock", Component.translatable(EFNEnchantment.YAMATO_GUARD.get().getDescriptionId()).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.RED));
        }
        if(!enchantments.contains(EFNEnchantment.YAMATO_DOPPELGANGER.get())) {
            tooltip.add(TCRCoreMod.getInfo("yamato_skill_lock", Component.translatable(EFNEnchantment.YAMATO_DOPPELGANGER.get().getDescriptionId()).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.RED));
        }
        if(!enchantments.contains(EFNEnchantment.YAMATO_JUDGEMENT_CUT_END.get())) {
            tooltip.add(TCRCoreMod.getInfo("yamato_skill_lock", Component.translatable(EFNEnchantment.YAMATO_JUDGEMENT_CUT_END.get().getDescriptionId()).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.RED));
        }
        if(!enchantments.contains(EFNEnchantment.YAMATO_SUMMONED_SWORD.get())) {
            tooltip.add(TCRCoreMod.getInfo("yamato_skill_lock", Component.translatable(EFNEnchantment.YAMATO_SUMMONED_SWORD.get().getDescriptionId()).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.RED));
        }
        if(!enchantments.contains(EFNEnchantment.YAMATO_HEAVY_RAIN.get())) {
            tooltip.add(TCRCoreMod.getInfo("yamato_skill_lock", Component.translatable(EFNEnchantment.YAMATO_HEAVY_RAIN.get().getDescriptionId()).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.RED));
        }
    }

}
