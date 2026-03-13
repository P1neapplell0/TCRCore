package com.p1nero.tcrcore.mixin;

import com.github.dodo.dodosmobs.entity.InternalAnimationMonster.IABossMonsters.Bone_Chimera_Entity;
import com.github.dodo.dodosmobs.entity.InternalAnimationMonster.IABossMonsters.IABoss_monster;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCREntityCapabilityProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.p1nero.tcrcore.events.ForgeEvents.BOSS_BAR_MANAGER;

/**
 * 对话控制开打
 */
@Mixin(Bone_Chimera_Entity.class)
public abstract class Bone_ChimeraMixin extends IABoss_monster {
    @Unique
    private ServerBossEvent tcr$bossBar;
    public Bone_ChimeraMixin(EntityType entity, Level world) {
        super(entity, world);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void tcr$init(EntityType<?> entity, Level world, CallbackInfo ci) {
        if(!world.isClientSide) {
            tcr$bossBar = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS);
            BOSS_BAR_MANAGER.put(this, tcr$bossBar);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tcr$tick(CallbackInfo ci) {
        if(!level().isClientSide) {
            if(!TCREntityCapabilityProvider.getTCREntityPatch(this).isFighting()) {
                this.setTarget(null);
                this.attackTicks = 0;
                this.attackCooldown = 100;
                this.setAttackState(0);
            }
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void tcr$hurt(DamageSource damagesource, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(!TCREntityCapabilityProvider.getTCREntityPatch(this).isFighting()) {
            if(damagesource.getEntity() instanceof Player player) {
                player.displayClientMessage(TCRCoreMod.getInfo("talk_to_start").withStyle(ChatFormatting.GOLD), true);
            }
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "die", at = @At("HEAD"))
    private void tcr$die(DamageSource source, CallbackInfo ci) {
        if(!level().isClientSide) {
            tcr$bossBar.removeAllPlayers();
        }
    }

}
