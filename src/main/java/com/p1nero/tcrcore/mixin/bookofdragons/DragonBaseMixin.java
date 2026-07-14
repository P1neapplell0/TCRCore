package com.p1nero.tcrcore.mixin.bookofdragons;

import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.EntityUtils;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.magister.bookofdragons.entity.component.simulation.DragonNeedsSystem;
import net.magister.bookofdragons.event.DragonDiscoveryEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import software.bernie.geckolib.animatable.GeoEntity;

import java.util.UUID;

@Mixin(DragonBase.class)
public abstract class DragonBaseMixin extends TamableAnimal implements GeoEntity, ContainerListener {

    @Shadow(remap = false)
    public abstract void setGrowthProgress(int progress);

    @Shadow(remap = false)
    public abstract int getGrowthProgress();

    @Shadow(remap = false)
    public abstract DragonNeedsSystem getNeedsSystem();

    @Shadow
    public abstract boolean isTame();

    @Shadow(remap = false)
    public abstract int getGrowthStage();

    @Shadow(remap = false)
    public abstract void setTamingRitualCompleted(boolean completed);

    @Shadow(remap = false)
    public abstract void setAwaitingTamingRitual(boolean awaiting);

    @Shadow(remap = false)
    public abstract void setTamingRitualTimer(int timer);

    @Shadow(remap = false)
    public abstract void setCommand(int command);

    @Shadow(remap = false)
    public abstract void addAffection(UUID playerUUID, int amount);

    protected DragonBaseMixin(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    /**
     * 简化养大流程，任何食物都可以喂
     */
    @Inject(method = "mobInteract", at = @At("TAIL"))
    private void tcr$mobInteract(Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack mainHand = pPlayer.getMainHandItem();
        if (!pPlayer.level().isClientSide && mainHand.is(TCRItems.DIVINE_FRAGMENT.get())) {
            if(!this.isOwnedBy(pPlayer)) {
                this.tame(pPlayer);
                this.setTamingRitualCompleted(true);
                this.setAwaitingTamingRitual(false);
                this.setTamingRitualTimer(0);
                this.setCommand(2);
                this.addAffection(pPlayer.getUUID(), 10);
                DragonDiscoveryEventHandler.onDragonTamed(pPlayer, (DragonBase) (Object) this);
            }
            this.setGrowthProgress(this.getGrowthProgress() + 6000);
            if (!pPlayer.isCreative()) {
                mainHand.shrink(1);
            }
        }
        FoodProperties foodProperties = mainHand.getItem().getFoodProperties(mainHand, pPlayer);
        if (pPlayer instanceof ServerPlayer serverPlayer && foodProperties != null && this.isTame()) {
            this.setGrowthProgress(this.getGrowthProgress() + foodProperties.getNutrition() * 200);
            EntityUtils.playLocalSound(serverPlayer, SoundEvents.GENERIC_EAT);
            this.getNeedsSystem().setFoodLevel((int) (DragonNeedsSystem.getMaxHunger() * 0.8));
            if (!pPlayer.isCreative()) {
                mainHand.shrink(1);
            }
            pPlayer.displayClientMessage(Component.literal("§6Growth: §f" + this.getGrowthProgress() + "/36000 §7| §6Stage: §f" + this.getGrowthStage() + "/2"), true);
        }
    }

    /**
     * 龙养大后
     */
    @Inject(method = "setGrowthProgress", at = @At("HEAD"), remap = false)
    private void tcr$setGrowthProgress(int progress, CallbackInfo ci) {
        if (this.getGrowthStage() >= 2) {
            if (this.getOwner() instanceof ServerPlayer player) {
                if (!TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL.isFinished(player) && TCRQuestManager.hasQuest(player, TCRQuests.TAME_DRAGON)) {
                    TCRQuests.TAME_DRAGON.finish(player, true);
                    TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL.start(player);
                }
            }
        }
    }

}
