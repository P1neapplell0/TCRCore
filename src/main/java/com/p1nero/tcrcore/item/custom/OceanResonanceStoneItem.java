package com.p1nero.tcrcore.item.custom;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import com.p1nero.tcrcore.capability.TCRPlayer;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.compat.JourneyMapCompat;
import com.p1nero.tcrcore.utils.XaeroWaypointUtil;
import com.p1nero.tcrcore.utils.WorldUtil;
import com.yesman.epicskills.registry.entry.EpicSkillsSounds;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import xaero.hud.minimap.waypoint.WaypointColor;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class OceanResonanceStoneItem extends ResonanceStoneItem {

    public OceanResonanceStoneItem(Properties properties, ResourceLocation targetStructure, int y, ResourceKey<Level> dimension, Predicate<ServerPlayer> predicate, BiConsumer<BlockPos, ServerPlayer> callback) {
        super(properties, targetStructure, y, dimension, predicate, callback);
    }

    /**
     * 额外搜索奇美拉地牢，用super怕丢了一个。。
     */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player instanceof ServerPlayer serverPlayer && !itemStack.getOrCreateTag().getBoolean("searching")) {
            if (predicate.test(serverPlayer) && level.dimension().equals(Level.OVERWORLD)) {
                itemStack.getOrCreateTag().putBoolean("searching", true);
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("resonance_stone_working", this.getDescription()), true);
                serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(EpicSkillsSounds.GAIN_ABILITY_POINTS.get()), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));

                BlockPos pos = null;
                try {
                    pos = WorldUtil.getNearbyStructurePos(serverPlayer, targetStructure.toString(), y);
                } catch (Exception e) {
                    TCRCoreMod.LOGGER.error("TCRCore : Error finding structure [{}]: ", targetStructure, e);
                    player.displayClientMessage(TCRCoreMod.getInfo("resonance_search_failed", targetStructure).withStyle(ChatFormatting.RED), false);
                    itemStack.getOrCreateTag().putBoolean("searching", false);
                }

                BlockPos pos1 = null;
                try {
                    //以海洋塔为中心搜呱呱村的位置
                    pos1 = WorldUtil.getNearbyStructurePos(serverPlayer.serverLevel(), pos, WorldUtil.RIBBIT_VILLAGE, 130);
                } catch (Exception e) {
                    TCRCoreMod.LOGGER.error("TCRCore : Error finding structure [" + WorldUtil.RIBBIT_VILLAGE + "]: ", e);
                    player.displayClientMessage(TCRCoreMod.getInfo("resonance_search_failed", WorldUtil.RIBBIT_VILLAGE).withStyle(ChatFormatting.RED), false);
                    itemStack.getOrCreateTag().putBoolean("searching", false);
                }
                TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(player);
                if (pos != null) {
                    tcrPlayer.playDirectionParticle(player.getEyePosition(), new Vec3(pos.getX(), player.getEyeY(), pos.getZ()));
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(EpicSkillsSounds.GAIN_ABILITY_POINTS.get()), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                } else {
                    player.displayClientMessage(TCRCoreMod.getInfo("resonance_search_failed", targetStructure).withStyle(ChatFormatting.RED), false);
                    itemStack.getOrCreateTag().putBoolean("searching", false);
                }
                if (pos1 != null) {
                    pos1 = WorldUtil.getSurfaceBlockPos(serverPlayer.serverLevel(), pos1);
                    tcrPlayer.playDirectionParticle(player.getEyePosition(), new Vec3(pos1.getX(), player.getEyeY(), pos1.getZ()));
                    if (TCRCoreMod.isXaeroMapLoaded()) {
                        XaeroWaypointUtil.sendWaypoint(serverPlayer, "ribbit_village_mark", Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtil.RIBBIT_VILLAGE))), pos1, WaypointColor.BLUE);
                    }
                    if (TCRCoreMod.isJourneyMapLoaded()) {
                        JourneyMapCompat.sendWaypoint(serverPlayer, "ribbit_village_mark", Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtil.RIBBIT_VILLAGE))), pos1, ChatFormatting.BLUE);
                    }
                } else {
                    player.displayClientMessage(TCRCoreMod.getInfo("resonance_search_failed", WorldUtil.RIBBIT_VILLAGE).withStyle(ChatFormatting.RED), false);
                    itemStack.getOrCreateTag().putBoolean("searching", false);
                }
                //保险，俩都找到再消耗
                if (pos != null && pos1 != null) {
                    itemStack.shrink(1);
                    if (!TCRCoreMod.isXaeroMapLoaded() && !TCRCoreMod.isJourneyMapLoaded()) {
                        ResonanceStoneItem.handleNoXaeroMap(Component.literal(targetStructure.toString()), pos, serverPlayer);
                        ResonanceStoneItem.handleNoXaeroMap(Component.literal(WorldUtil.RIBBIT_VILLAGE), pos1, serverPlayer);
                    }
                    callback.accept(pos, serverPlayer);
                    TCRQuests.RIBBITS_QUEST.start(serverPlayer);
                } else {
                    itemStack.getOrCreateTag().putBoolean("searching", false);
                }
            } else {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), false);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }
}
