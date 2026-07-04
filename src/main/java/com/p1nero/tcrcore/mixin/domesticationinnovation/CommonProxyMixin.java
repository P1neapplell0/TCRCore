package com.p1nero.tcrcore.mixin.domesticationinnovation;

import com.github.alexthe668.domesticationinnovation.server.CommonProxy;
import com.github.alexthe668.domesticationinnovation.server.item.DIItemRegistry;
import com.p1nero.tcrcore.TCRCoreMod;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.minecraft.ChatFormatting;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({CommonProxy.class})
public class CommonProxyMixin {
    @Inject(
        method = {"onInteractWithEntity"},
        at = {@At("HEAD")},
        cancellable = true,
        remap = false
    )
    private void tcr$onInteractWithEntity(PlayerInteractEvent.EntityInteract event, CallbackInfo ci) {
        if (event.getItemStack().is(DIItemRegistry.COLLAR_TAG.get()) && !(event.getTarget() instanceof DragonBase)) {
            event.getEntity().displayClientMessage(TCRCoreMod.getInfo("only_work_on_dragon").withStyle(ChatFormatting.RED), true);
            ci.cancel();
        }

    }
}
