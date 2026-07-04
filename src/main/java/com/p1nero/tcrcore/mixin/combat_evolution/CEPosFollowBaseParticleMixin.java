package com.p1nero.tcrcore.mixin.combat_evolution;

import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.shelmarow.combat_evolution.client.particle.follow.CEPosFollowBaseParticle;
import net.shelmarow.combat_evolution.client.particle.warning.BypassDodgeParticle;
import net.shelmarow.combat_evolution.client.particle.warning.BypassGuardParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CEPosFollowBaseParticle.class)
public abstract class CEPosFollowBaseParticleMixin extends TextureSheetParticle {

    @Shadow(remap = false) protected int entityID;

    protected CEPosFollowBaseParticleMixin(ClientLevel p_108323_, double p_108324_, double p_108325_, double p_108326_) {
        super(p_108323_, p_108324_, p_108325_, p_108326_);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tcr$tick(CallbackInfo ci) {
        if(this.lifetime % 10 == 0) {
            Entity entity = this.level.getEntity(this.entityID);
            if (entity instanceof Player player) {
                if(((Object)this) instanceof BypassDodgeParticle) {
                    player.displayClientMessage(TCRCoreMod.getInfo("can_not_dodge").withStyle(ChatFormatting.RED), true);
                }
                if(((Object)this) instanceof BypassGuardParticle) {
                    player.displayClientMessage(TCRCoreMod.getInfo("can_not_guard").withStyle(ChatFormatting.RED), true);
                }
            }
        }
    }

}
