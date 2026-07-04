package com.p1nero.tcrcore.mixin.epicskills;

import com.p1nero.tcrcore.TCRCoreMod;
import com.yesman.epicskills.client.input.EpicSkillsKeyMappings;
import com.yesman.epicskills.world.item.AbilityStoneItem;
import net.minecraft.ChatFormatting;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbilityStoneItem.class)
public class AbilityStoneItemClientMixin {

    @Inject(method = "use", at = @At("TAIL"))
    private void tcr$use(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if(level.isClientSide) {
            player.displayClientMessage(TCRCoreMod.getInfo("press_to_open_skill_tree", EpicSkillsKeyMappings.OPEN_SKILL_TREE.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)), true);
        }
    }

}
