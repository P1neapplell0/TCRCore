package com.p1nero.tcrcore.network.packet.clientbound;
import com.p1nero.dialog_lib.network.packet.BasePacket;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.network.packet.clientbound.helper.DistHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import yesman.epicfight.config.ClientConfig;

public record CSTipPacket() implements BasePacket {
    @Override
    public void encode(FriendlyByteBuf buf) {
    }

    public static CSTipPacket decode(FriendlyByteBuf buf) {
        return new CSTipPacket();
    }

    @Override
    public void execute(Player playerEntity) {
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            DistHelper.runClient(() -> () -> {
                if(!ClientConfig.activateComputeShader) {
                    Minecraft.getInstance().player.displayClientMessage(TCRCoreMod.getInfo("cs_warning"), false);
                }
            });
        }
    }
}