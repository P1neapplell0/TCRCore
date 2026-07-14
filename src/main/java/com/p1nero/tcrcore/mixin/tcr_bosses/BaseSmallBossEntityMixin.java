package com.p1nero.tcrcore.mixin.tcr_bosses;

import com.p1nero.tcr_bosses.entity.custom.BaseSmallBossEntity;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseSmallBossEntity.class)
public abstract class BaseSmallBossEntityMixin extends PathfinderMob {

    @Shadow(remap = false)
    public abstract boolean hasSpawnPos();

    @Shadow(remap = false)
    public abstract void setSpawnPos(BlockPos pos);

    protected BaseSmallBossEntityMixin(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    /**
     * 设置出生点方便复活，从 LivingEntityEventListeners.onLivingJoin 移入 tick
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void tcr$setSpawnPosOnTick(CallbackInfo ci) {
        if (!this.level().isClientSide && !this.hasSpawnPos()) {
            if (FMLEnvironment.production) {
                ServerLevel serverLevel = (ServerLevel) this.level();
                BlockPos pos = this.getOnPos();
                while (!serverLevel.getBlockState(pos).isAir()) {
                    pos = pos.above();
                }
                this.setSpawnPos(this.getOnPos());
            } else {
                TCRCoreMod.LOGGER.info("开发环境，跳过设置[{}]的出生点", this.getDisplayName().getString());
            }
        }
    }

}
