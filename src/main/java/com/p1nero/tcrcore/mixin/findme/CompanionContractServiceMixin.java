package com.p1nero.tcrcore.mixin.findme;

import com.kuzhi.findme.server.CompanionContractService;
import com.p1nero.tcrcore.capability.TCRQuests;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

/**
 * 当 CompanionContractService 的契约仪式开始时，
 * 推进驯龙支线任务：结束 TAME_DRAGON_BIND_DRAGON，开启 TAME_DRAGON_BACK_TO_FERRY_GIRL_2。
 */
@Mixin(CompanionContractService.class)
public abstract class CompanionContractServiceMixin {

    @Inject(method = "start", at = @At("HEAD"), remap = false)
    private static void tcr$onStart(ServerPlayer player, LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if (player == null || !(target instanceof DragonBase)) {
            return;
        }
        if (TCRQuests.TAME_DRAGON_BIND_DRAGON != null) {
            TCRQuests.TAME_DRAGON_BIND_DRAGON.finish(player, true);
        }
        if (TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL_2 != null) {
            TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL_2.start(player);
        }
    }

}
