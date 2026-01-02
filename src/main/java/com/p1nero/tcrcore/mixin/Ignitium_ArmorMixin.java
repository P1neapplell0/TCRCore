package com.p1nero.tcrcore.mixin;

import com.github.L_Ender.cataclysm.items.Ignitium_Armor;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.effect.TCREffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ignitium_Armor.class)
public class Ignitium_ArmorMixin {
    @Inject(method = "getArmorTexture", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$getTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type, CallbackInfoReturnable<String> cir) {
        if(entity instanceof LivingEntity living && living.hasEffect(TCREffects.SOUL_INCINERATOR.get())) {
            cir.setReturnValue(TCRCoreMod.MOD_ID + ":textures/armor/ignitium_soul_armor" + (slot == EquipmentSlot.LEGS ? "_legs.png" : ".png"));
        }
    }
}
