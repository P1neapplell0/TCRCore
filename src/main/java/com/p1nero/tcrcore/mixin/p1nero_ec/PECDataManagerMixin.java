package com.p1nero.tcrcore.mixin.p1nero_ec;

import com.p1nero.p1nero_ec.capability.PECDataManager;
import com.p1nero.tcrcore.utils.FTBTeamUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 修武器解锁同步问题= =
 */
@Mixin(PECDataManager.class)
public abstract class PECDataManagerMixin {

    @Inject(method = "putData(Lnet/minecraft/world/entity/player/Player;Ljava/lang/String;Z)V", at = @At("TAIL"), remap = false)
    private static void tcr$putBoolData(Player player, String key, boolean value, CallbackInfo ci) {
        if(player instanceof ServerPlayer serverPlayer) {
            FTBTeamUtils.onlineTeamMembersDoWithSelf(serverPlayer, member -> {
                PECDataManager.getPECPlayer(member).putBoolean(key, value);
            });
        }
    }

}
