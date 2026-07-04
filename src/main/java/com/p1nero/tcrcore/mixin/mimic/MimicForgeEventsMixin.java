package com.p1nero.tcrcore.mixin.mimic;

import com.p1nero.tcrcore.entity.custom.mimic.TCRMimic;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.merlin204.mimic.event.ForgeEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeEvents.class)
public class MimicForgeEventsMixin {

    @Inject(method = "onLivingDrops", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onLivingDrop(LivingDropsEvent event, CallbackInfo ci) {
        if(event.getEntity() instanceof TCRMimic) {
            ci.cancel();
        }
    }

}
