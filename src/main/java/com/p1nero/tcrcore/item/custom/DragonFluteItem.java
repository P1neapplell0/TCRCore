package com.p1nero.tcrcore.item.custom;

import com.p1nero.tcrcore.TCRCoreMod;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.magister.bookofdragons.event.DragonDiscoveryEventHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DragonFluteItem extends SimpleDescriptionItem {

    public DragonFluteItem(Properties properties) {
        super(properties, false);
    }

    public @NotNull InteractionResult useOn(UseOnContext onContext) {
        if (!onContext.getLevel().isClientSide) {
            Player pPlayer = onContext.getPlayer();
            ItemStack stack = onContext.getItemInHand();
            LivingEntity livingEntity = releaseEntity(stack, onContext.getLevel(), onContext.getClickLocation());
            if(livingEntity instanceof DragonBase dragonBase && pPlayer != null) {
                if(dragonBase.canBeMountedBy(pPlayer)) {
                    boolean ridingSuccess = pPlayer.startRiding(dragonBase);
                    if (ridingSuccess) {
                        dragonBase.setTarget(null);
                        dragonBase.getNavigation().stop();
                        dragonBase.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
                        dragonBase.targetSelector.getRunningGoals().forEach(WrappedGoal::stop);
                        dragonBase.getMoveControl().setWantedPosition(dragonBase.getX(), dragonBase.getY(), dragonBase.getZ(), 0.0F);
                    }
                }
                if(dragonBase.getOwnerUUID() == null) {
                    dragonBase.tame(pPlayer);
                    dragonBase.setTamingRitualCompleted(true);
                    dragonBase.setAwaitingTamingRitual(false);
                    dragonBase.setTamingRitualTimer(0);
                    dragonBase.addAffection(pPlayer.getUUID(), 10);
                    DragonDiscoveryEventHandler.onDragonTamed(pPlayer, dragonBase);
                }
            }
            if(livingEntity != null) {
                if(stack.getOrCreateTag().getBoolean("tcr_temp")) {
                    stack.shrink(1);
                }
            }
        }
        return super.useOn(onContext);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack itemStack, Player player, @NotNull LivingEntity entity, @NotNull InteractionHand hand) {
        if (!player.level().isClientSide && !itemStack.getOrCreateTag().contains("entity") && !entity.isVehicle()) {
            if(entity instanceof DragonBase) {
                saveToItem(player.getItemInHand(hand), entity);
            } else {
                player.displayClientMessage(TCRCoreMod.getInfo("only_work_on_dragon"), true);
            }
        }
        return super.interactLivingEntity(itemStack, player, entity, hand);
    }

    public static boolean containsDragon(ItemStack itemStack) {
        return itemStack.getOrCreateTag().contains("entity");
    }

    @Nullable
    public static LivingEntity releaseEntity(ItemStack itemStack, Level level, Vec3 spawnPos) {
        CompoundTag tag = itemStack.getOrCreateTag();
        EntityType<?> entityType = EntityType.byString(tag.getString("entity")).orElse(null);
        if (entityType != null && entityType.create(level) instanceof LivingEntity livingEntity) {
            livingEntity.load(tag);
            livingEntity.setPos(spawnPos);
            level.addFreshEntity(livingEntity);
            tag.remove("entity");
            tag.remove("owner_name");
            return livingEntity;
        }
        return null;
    }

    public static void saveToItem(ItemStack itemStack, LivingEntity entity) {
        entity.removeEffect(MobEffects.GLOWING);
        CompoundTag tag = itemStack.getOrCreateTag();
        tag.putString("entity", EntityType.getKey(entity.getType()).toString());
        entity.saveWithoutId(tag);
        if(entity instanceof OwnableEntity ownableEntity && ownableEntity.getOwner() instanceof Player player) {
            tag.putString("owner_name", player.getGameProfile().getName());
        }
        entity.discard();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, level, list, flag);
        CompoundTag tag = itemStack.getOrCreateTag();
        if(tag.getBoolean("tcr_temp")) {
            list.add(TCRCoreMod.getInfo("temp_dragon_flute").withStyle(ChatFormatting.GOLD));
        }
        if(tag.contains("entity")) {
            EntityType.byString(tag.getString("entity")).ifPresent(entityType ->
                    list.add(TCRCoreMod.getInfo("containing_dragon", entityType.getDescription())));
        }
        if(tag.contains("owner_name")) {
            String ownerName = tag.getString("owner_name");
            list.add(TCRCoreMod.getInfo("dragon_owner", ownerName));
        }
    }

    /**
     * 包含实体的时候就发光
     */
    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return itemStack.hasTag() && itemStack.getOrCreateTag().contains("entity");
    }
}
