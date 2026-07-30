package com.p1nero.tcrcore.entity.custom.mimic;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.save_data.TCRDimSaveData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.merlin204.mimic.entity.proteus.ProteusEntity;
import org.merlin204.wraithon.worldgen.WraithonDimensions;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class TCRMimic extends ProteusEntity {
    public TCRMimic(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ARMOR, 30)
                .add(Attributes.FOLLOW_RANGE, 72)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1000)
                .add(Attributes.ATTACK_SPEED,1.2F)
                .add(EpicFightAttributes.IMPACT.get(), 1)
                .add(EpicFightAttributes.ARMOR_NEGATION.get(), 10.0F)
                .add(EpicFightAttributes.MAX_STRIKES.get(), 50.0F)
                .add(EpicFightAttributes.WEIGHT.get(), 0).build();
    }

    @Override
    protected float getEquipmentDropChance(@NotNull EquipmentSlot pSlot) {
        return 0;
    }

    @Override
    public void heal(float value) {

    }

    @Override
    public boolean hurt(@NotNull DamageSource damageSource, float amount) {
        if(damageSource.is(DamageTypes.FALL)) {
            return false;
        }
        if(damageSource.isCreativePlayer()) {//方便debug
            this.setPhase(2);
            this.die(damageSource);
            return true;
        }
        boolean hurt = super.hurt(damageSource, amount);
        if (!this.level().isClientSide && damageSource.getEntity() instanceof Player player) {
            int copiedAnimations = this.getPatch().copyMap.size();
            int defense = Math.round((1.0F - Math.min(1.5F, copiedAnimations * 0.8F) * 0.3F) * 100.0F);
            player.displayClientMessage(TCRCoreMod.getInfo("mimic_defense", this.getDisplayName(), defense, this.getDisplayName()), true);
        }
        return hurt;
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        if(this.getServer() != null) {
            ServerLevel mimicLevel = this.getServer().getLevel(WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY);
            if(mimicLevel != null) {
                TCRDimSaveData.get(mimicLevel).setBossSummoned(false);
            }
        }
    }

    public void resetMemory() {
        this.getPatch().copyMap.clear();
    }

    @Override
    public boolean removeWhenFarAway(double v) {
        return false;
    }
}
