package com.p1nero.tcrcore.item.custom;

import com.p1nero.battle_field1.PBF1Mod;
import com.p1nero.battle_field1.worldgen.PBF1Dimensions;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.entity.custom.boss_rush.BossRushManagerEntity;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.ItemUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BossRushManagerItem extends SimpleDescriptionItem {

    public BossRushManagerItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (!(pLevel instanceof ServerLevel serverLevel)) {
            return InteractionResultHolder.sidedSuccess(itemStack, true);
        }
        if (serverLevel.dimension() != PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY) {
            return InteractionResultHolder.fail(itemStack);
        }
        if (!serverLevel.getEntities(TCREntities.BOSS_RUSH_MANAGER.get(), Entity::isAlive).isEmpty()) {
            return InteractionResultHolder.fail(itemStack);
        }

        List<EntityType<?>> bossList = new ArrayList<>(List.of(
                TCRBossEntities.MALEDICTUS_HUMANOID.get(),
                TCRBossEntities.IGNIS_HUMANOID.get(),
                TCRBossEntities.NETHERITE_HUMANOID.get(),
                TCRBossEntities.SCYLLA_HUMANOID.get(),
                TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get(),
                TCRBossEntities.HARBINGER_HUMANOID.get(),
                TCRBossEntities.LEVIATHAN_HUMANOID.get(),
                TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get()
        ));
        Collections.shuffle(bossList, new Random());

        BossRushManagerEntity bossRushManagerEntity = TCREntities.BOSS_RUSH_MANAGER.get().create(serverLevel);
        if (bossRushManagerEntity == null) {
            return InteractionResultHolder.fail(itemStack);
        }
        bossRushManagerEntity.setPos(PBF1Mod.START_POS.above(5).getCenter());
        bossRushManagerEntity.setBossList(bossList);
        bossRushManagerEntity.onBossRushStarted();
        serverLevel.addFreshEntity(bossRushManagerEntity);

        return InteractionResultHolder.sidedSuccess(itemStack, false);
    }
}
