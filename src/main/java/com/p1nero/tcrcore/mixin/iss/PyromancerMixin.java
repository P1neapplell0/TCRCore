package com.p1nero.tcrcore.mixin.iss;

import io.redspace.ironsspellbooks.entity.mobs.wizards.pyromancer.PyromancerEntity;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(PyromancerEntity.class)
public class PyromancerMixin {

    @Shadow(remap = false)
    @Nullable
    protected MerchantOffers offers;

    @Inject(method = {"getOffers", "m_6616_"}, at = @At("RETURN"), remap = false)
    private void tcr$getOffers(CallbackInfoReturnable<MerchantOffers> cir) {
        if(this.offers != null) {
            this.offers.removeIf(merchantOffer -> merchantOffer.getResult().is(ItemRegistry.SCROLL.get()));
        }
    }
}
