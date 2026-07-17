package com.p1nero.tcrcore.mixin.findme;

import com.kuzhi.findme.common.CompanionKind;
import com.kuzhi.findme.common.CompanionMoveType;
import com.p1nero.tcrcore.compat.FindMeDragonCompat;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.kuzhi.findme.server.CompanionEntityClassifier")
public abstract class CompanionEntityClassifierMixin {

    @Inject(
            method = "moveType(Lnet/minecraft/world/entity/Entity;Lcom/kuzhi/findme/common/CompanionKind;)Lcom/kuzhi/findme/common/CompanionMoveType;",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void tcr$classifyFlyCapableDragon(
            Entity entity,
            CompanionKind kind,
            CallbackInfoReturnable<CompanionMoveType> cir
    ) {
        if (kind == CompanionKind.MOUNT && FindMeDragonCompat.canFly(entity)) {
            cir.setReturnValue(CompanionMoveType.FLY);
        }
    }
}
