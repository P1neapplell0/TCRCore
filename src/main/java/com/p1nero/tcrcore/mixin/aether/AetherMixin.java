package com.p1nero.tcrcore.mixin.aether;

import com.aetherteam.aether.Aether;
import net.minecraftforge.event.AddPackFindersEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Aether.class)
public class AetherMixin {

    /**
     * 都不需要，爬爬爬
     */
    @Inject(method = "packSetup", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$packSetup(AddPackFindersEvent event, CallbackInfo ci) {
        ci.cancel();
    }

}
