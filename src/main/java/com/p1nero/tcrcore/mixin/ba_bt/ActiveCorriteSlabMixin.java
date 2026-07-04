package com.p1nero.tcrcore.mixin.ba_bt;

import com.brass_amber.ba_bt.block.block.ActiveCorriteSlab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ActiveCorriteSlab.class)
public class ActiveCorriteSlabMixin {

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void tcr$getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext p_56071_, CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(blockState.getShape(blockGetter, blockPos));
    }

}
