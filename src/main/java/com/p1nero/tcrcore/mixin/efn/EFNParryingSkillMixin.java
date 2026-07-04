package com.p1nero.tcrcore.mixin.efn;

import com.hm.efn.gameasset.EFNSKillDataKeys;
import com.hm.efn.skill.guard.EFNParryingSkill;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.world.damagesource.EpicFightDamageTypeTags;

@Mixin(EFNParryingSkill.class)
public abstract class EFNParryingSkillMixin {

    @Shadow(remap = false)
    private int SHAKEDETECTIONCOUNT;

    /**
     * 添加抖刀警告
     */
    @Inject(method = "startHolding", at = @At("TAIL"), remap = false)
    private void tcr$startHolding(SkillContainer container, CallbackInfo ci) {
        if (container.getExecutor().getOriginal() instanceof ServerPlayer serverPlayer) {
            SkillDataManager dataManager = container.getDataManager();
            int shakePenaltyCount = dataManager.getDataValue(EFNSKillDataKeys.EFN_SHAKE_PENALTY_COUNT.get());
            if (shakePenaltyCount > this.SHAKEDETECTIONCOUNT) {
                int effectivePenalty = shakePenaltyCount - this.SHAKEDETECTIONCOUNT;
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("shake_penalty_warning", effectivePenalty).withStyle(ChatFormatting.RED), true);
            }
        }
    }

    /**
     * 增加招架源
     */
    @Inject(method = "isBlockableSource", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$isBlockableSource(DamageSource damageSource, boolean advanced, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!damageSource.is(EpicFightDamageTypeTags.UNBLOCKALBE)
                && !damageSource.is(DamageTypeTags.IS_FALL)
                && !damageSource.is(DamageTypes.FELL_OUT_OF_WORLD)
                && !damageSource.is(DamageTypes.IN_WALL));
    }

}
