package com.p1nero.tcrcore.mixin.create;

import com.p1nero.tcrcore.TCRCoreMod;
import net.createmod.ponder.foundation.registration.PonderLocalization;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(PonderLocalization.class)
public class PonderLocalizationMixin {

    @Shadow(remap = false) @Final public Map<ResourceLocation, Map<String, String>> specific;

    @Inject(method = "getSpecific", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$getSpecific(ResourceLocation sceneId, String k, CallbackInfoReturnable<String> cir) {
        if(sceneId.getNamespace().equals(TCRCoreMod.MOD_ID)) {
            cir.setReturnValue(I18n.get(specific.get(sceneId).get(k)));//套一层，万一是翻译键也能用
        }
    }

}
