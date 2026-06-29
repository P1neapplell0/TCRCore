package com.p1nero.tcrcore.network.packet.serverbound;
import com.p1nero.dialog_lib.network.packet.BasePacket;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.ItemUtils;
import com.p1nero.tcrcore.utils.WorldUtils;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.merlin204.wraithon.util.PositionTeleporter;

public record EndScreenCallbackPacket() implements BasePacket {

    @Override
    public void encode(FriendlyByteBuf buf) {
    }

    public static EndScreenCallbackPacket decode(FriendlyByteBuf buf) {
        return new EndScreenCallbackPacket();
    }

    @Override
    public void execute(Player player) {
        if (player instanceof ServerPlayer serverPlayer && player.getServer() != null) {
            ServerLevel real = player.getServer().getLevel(TCRDimensions.REAL_LEVEL_KEY);
            if (real != null) {
                if(TCRQuests.KILL_MAD_CHRONOS.finish(serverPlayer, true)) {
                    PlayerDataManager.gameCleared.put(serverPlayer, true);
                    PlayerDataManager.finalBossKilled.put(serverPlayer, true);
                    ItemUtils.addItem(player, TCRItems.STONE_OF_REINCARNATION.get().getDefaultInstance());
                    serverPlayer.changeDimension(real, new PositionTeleporter(new BlockPos(WorldUtils.BED_POS)));
                }
            }
        }
    }
}