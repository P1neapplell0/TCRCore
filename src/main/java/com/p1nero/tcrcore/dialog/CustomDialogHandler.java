package com.p1nero.tcrcore.dialog;

import com.p1nero.dialog_lib.events.ServerCustomInteractEvent;
import com.p1nero.dialog_lib.events.ServerNpcEntityInteractEvent;
import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import com.p1nero.tcrcore.capability.TCRPlayer;
import com.p1nero.tcrcore.datagen.TCRAdvancementData;
import com.p1nero.tcrcore.events.PlayerEventListeners;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.PlayTitlePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID)
public class CustomDialogHandler {

    @SubscribeEvent
    public static void onLivingDialog(ServerNpcEntityInteractEvent event) {
        if (event.getSelf() instanceof Villager) {
            TCRCapabilityProvider.getTCRPlayer(event.getServerPlayer()).setCurrentTalkingEntity(null);
        }
    }

    @SubscribeEvent
    public static void onCustomDialog(ServerCustomInteractEvent event) {
        if (Objects.equals(event.getModId(), TCRCoreMod.MOD_ID)) {
            ServerPlayer serverPlayer = event.getServerPlayer();
            if (event.getId().getPath().equals("start_screen")) {
                PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PlayTitlePacket(PlayTitlePacket.OPEN_BACKPACK_TUTORIAL), serverPlayer);
            } else if (event.getId().getPath().equals("reset_game") && event.getInteractId() != 0) {
                if (!serverPlayer.isCreative()) {
                    serverPlayer.getMainHandItem().shrink(1);
                }
                //二阶段，清理数据，回出生点

                int newSardine = TCRCapabilityProvider.clearPlayerData(serverPlayer);
                TCRAdvancementData.revokeAllAdvancement(serverPlayer);
                if (serverPlayer.server.isSingleplayer()) {
                    TCRPlayer.SARDINE_COUNT = newSardine;
                }
                PlayerEventListeners.handleFirstJoin(serverPlayer, true);
            }
        }
    }

}
