package com.p1nero.tcrcore.entity.custom.fake_npc;

import com.p1nero.dialog_lib.api.entity.custom.IEntityNpc;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public abstract class FakeNPCEntity extends PathfinderMob implements IEntityNpc, OwnableEntity {

    protected boolean canBeHurt;

    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(FakeNPCEntity.class, EntityDataSerializers.OPTIONAL_UUID);

    public FakeNPCEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if(owner != null && owner.level().dimension() == this.level().dimension()) {
            this.getLookControl().setLookAt(owner.getX(), owner.getEyeY(), owner.getZ());
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float value) {
        if(canBeHurt) {
            super.hurt(source, value);
        }
        return false;
    }

    public void setOwner(LivingEntity livingEntity) {
        this.getEntityData().set(OWNER_UUID, Optional.of(livingEntity.getUUID()));
    }

    public void setOwnerUuid(@Nullable UUID uuid) {
        this.getEntityData().set(OWNER_UUID, Optional.ofNullable(uuid));
    }

    @Override
    public @Nullable UUID getOwnerUUID() {
        return this.getEntityData().get(OWNER_UUID).orElse(null);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.empty());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (this.getOwnerUUID() != null) {
            tag.putUUID("Owner", this.getOwnerUUID());
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        UUID uuid;
        if (tag.hasUUID("Owner")) {
            uuid = tag.getUUID("Owner");
        } else {
            String s = tag.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }
        if (uuid != null) {
            try {
                this.setOwnerUuid(uuid);
            } catch (Throwable ignored) {
            }
        }
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }
}
