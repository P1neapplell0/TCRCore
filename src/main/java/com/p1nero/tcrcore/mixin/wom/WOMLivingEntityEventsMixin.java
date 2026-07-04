package com.p1nero.tcrcore.mixin.wom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reascer.wom.events.WOMLivingEntityEvents;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import static reascer.wom.events.WOMLivingEntityEvents.*;

@Mixin(WOMLivingEntityEvents.class)
public class WOMLivingEntityEventsMixin {

    @Inject(method = "onSpawnEvent", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onLivingSpawn(MobSpawnEvent event, CallbackInfo ci) {
        ci.cancel();
    }

    /**
     * 确保不卡无敌
     */
    @Inject(method = "onUpdateEvent", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onLivingTick(LivingEvent.LivingTickEvent event, CallbackInfo ci) {
        ci.cancel();
        LivingEntity entity = event.getEntity();
        if(entity.isInvulnerable() && !entity.level().isClientSide) {
            entity.setInvulnerable(false);
            LivingEntityPatch<?> entityPatch = EpicFightCapabilities.getEntityPatch(entity, LivingEntityPatch.class);
            if (entityPatch != null) {
                for(String tag : event.getEntity().getTags()) {
                    if (tag.contains("wom_ultimate_Invulnerable")) {
                        event.getEntity().getTags().remove(tag);
                        break;
                    }
                }
            }
        }
        AntiStunlock(event, entity);
        TimedSakuraSlashes(event, entity);
        LunarEclipse(event, entity);
        SolarIgnited(event);
        Blackout(event);
    }
}
