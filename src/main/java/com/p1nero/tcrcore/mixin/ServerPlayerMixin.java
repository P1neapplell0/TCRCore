package com.p1nero.tcrcore.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 当 connection 为 null 时跳过 sendSystemMessage，
 * 避免在某些边界情况（如玩家离线瞬间）抛出 NullPointerException。
 */
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    @Shadow
    public ServerGamePacketListenerImpl connection;

    @Inject(method = "sendSystemMessage(Lnet/minecraft/network/chat/Component;Z)V", at = @At("HEAD"), cancellable = true)
    private void tcr$sendSystemMessage(Component pComponent, boolean pBypassHiddenChat, CallbackInfo ci) {
        if (this.connection == null) {
            ci.cancel();
        }
    }

}
