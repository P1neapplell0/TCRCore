package com.p1nero.tcrcore.mixin.epicfightx;

import com.asanginxst.epicfightx.data.EFXLootTables;
import net.minecraftforge.event.LootTableLoadEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EFXLootTables.class)
public class EFXLootMixin {

    @Inject(method = "modifyVanillaLootPools", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$modifyVanillaLootPools(LootTableLoadEvent event, CallbackInfo ci) {
        ci.cancel();
    }

}
