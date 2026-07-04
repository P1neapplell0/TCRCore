package com.p1nero.tcrcore.mixin.cataclysm;

import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ignited_Revenant_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.LLibrary_Boss_Monster;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ignited_Revenant_Entity.class)
public abstract class Ignited_Revenant_EntityMixin extends LLibrary_Boss_Monster {

    @Shadow(remap = false)
    public abstract boolean getIsAnger();

    @Shadow(remap = false)
    public abstract int getShieldDurability();

    @Shadow(remap = false)
    public abstract void setShieldDurability(int ShieldDurability);

    public Ignited_Revenant_EntityMixin(EntityType entity, Level world) {
        super(entity, world);
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void tcr$hurt(DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = source.getDirectEntity();
        if (!this.level().isClientSide && this.getIsAnger() && entity instanceof LivingEntity livingEntity) {
            double itemDamage = livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
            if ((double)damage >= itemDamage + itemDamage / (double)2.0F && this.getShieldDurability() < 4) {
                this.playSound(SoundEvents.WITHER_BREAK_BLOCK, 1.0F, 1.5F);
                this.setShieldDurability(this.getShieldDurability() + 1);
                cir.setReturnValue(false);
            }
        }
        cir.setReturnValue(super.hurt(source, damage));
    }
}
