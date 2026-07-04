package com.p1nero.tcrcore.block.entity;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class DesertAltarBlockEntity extends AbstractAltarBlockEntity {
    public DesertAltarBlockEntity(BlockPos pos, BlockState blockState) {
        super(TCRBlockEntities.DESERT_ALTAR_BLOCK_ENTITY.get(), pos, blockState, ModItems.DESERT_EYE.get());
    }

    @Override
    public void onPlayerInteract(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        super.onPlayerInteract(pState, pLevel, pPos, pPlayer, pHand, pHit);
        if(pPlayer instanceof ServerPlayer serverPlayer && TCRQuestManager.hasQuest(pPlayer, TCRQuests.PUT_DESERT_EYE_ON_ALTAR)) {
            TCRQuests.PUT_DESERT_EYE_ON_ALTAR.finish(serverPlayer, true);
            TCRQuests.KILL_DESERT_EYE.start(serverPlayer);
        }
    }

    @Override
    public void setActivated(Player player, boolean activated) {
        PlayerDataManager.desertEyeActivated.put(player, activated);
    }

    @Override
    public boolean isActivated(Player player) {
        return PlayerDataManager.desertEyeActivated.get(player);
    }

    @Override
    protected ParticleOptions getSpawnerParticle() {
        return ParticleTypes.LAVA;
    }

    @Override
    public boolean checkBossKilled(Player player) {
        return PlayerDataManager.desertEyeKilled.get(player);
    }

    @Override
    public boolean checkEyeFound(Player player) {
        return PlayerDataManager.desertEyeGotten.get(player);
    }

    @Override
    public int getColor() {
        return ChatFormatting.YELLOW.getColor();
    }
}
