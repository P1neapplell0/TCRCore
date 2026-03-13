package com.p1nero.tcrcore.dialog.extension;

import com.brass_amber.ba_bt.entity.hostile.golem.EndGolem;
import com.brass_amber.ba_bt.init.BTEntityType;
import com.hm.efn.registries.EFNItem;
import com.p1nero.dialog_lib.api.component.DialogueComponentBuilder;
import com.p1nero.dialog_lib.api.entity.EntityDialogueExtension;
import com.p1nero.dialog_lib.api.entity.IEntityDialogueExtension;
import com.p1nero.dialog_lib.client.screen.DialogueScreen;
import com.p1nero.dialog_lib.client.screen.builder.StreamDialogueScreenBuilder;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCREntityCapabilityProvider;
import com.p1nero.tcrcore.client.sound.TCRSounds;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

@EntityDialogueExtension(modId = TCRCoreMod.MOD_ID)
public class EndGolemDialogExtension implements IEntityDialogueExtension<EndGolem> {

    @Override
    public EntityType<EndGolem> getEntityType() {
        return BTEntityType.END_GOLEM.get();
    }

    @Override
    public boolean canInteractWith(Player player, EndGolem endGolem) {
        return !TCREntityCapabilityProvider.getTCREntityPatch(endGolem).isFighting();
    }

    @Override
    public void onPlayerInteract(Player player, EndGolem currentTalking, InteractionHand hand) {
        IEntityDialogueExtension.super.onPlayerInteract(player, currentTalking, hand);
        if(player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SMOKE, currentTalking.getX(), currentTalking.getY(), currentTalking.getZ(), 50, 0, 0.1, 0, 0.1);
        }
    }

    @Override
    public DialogueScreen getDialogScreen(StreamDialogueScreenBuilder streamDialogueScreenBuilder, LocalPlayer localPlayer, EndGolem endGolem, CompoundTag compoundTag) {
        DialogueComponentBuilder builder = new DialogueComponentBuilder(endGolem, TCRCoreMod.MOD_ID);
        streamDialogueScreenBuilder
                .start(0)
                .addOption(builder.opt(0, TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get().getDescription()), builder.ans(1))
                .addOption(builder.opt(1, EFNItem.YAMATO_DMC4_IN_SHEATH.get().getDescription()), builder.ans(2))
                .thenExecute((byte) 1)//起身
                .addOption(2, 3)
                .thenExecute((byte) 2)//鼓点1
                .addOption(3, 4)
                .thenExecute((byte) 3)//鼓点2
                .addOption(4, 5)
                .thenExecute((byte) 4)//鼓点3
                .addFinalOption(5, (byte) 5);//开打
        return streamDialogueScreenBuilder.build();
    }

    @Override
    public void handleNpcInteraction(EndGolem endGolem, ServerPlayer player, int interactionID) {
        if(interactionID == 5) {
            TCREntityCapabilityProvider.getTCREntityPatch(endGolem).setFighting(true);
        }
        if(interactionID == 4) {
            endGolem.level().playSound(null, player.getX(), player.getY() + 0.75, player.getZ(), TCRSounds.DRUMBEAT_3.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            return;
        }

        if(interactionID == 3) {
            endGolem.level().playSound(null, player.getX(), player.getY() + 0.75, player.getZ(), TCRSounds.DRUMBEAT_2.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            return;
        }

        if(interactionID == 2) {
            endGolem.level().playSound(null, player.getX(), player.getY() + 0.75, player.getZ(), TCRSounds.DRUMBEAT_1.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            return;
        }

        setConservingPlayer(null, endGolem);
    }
}
