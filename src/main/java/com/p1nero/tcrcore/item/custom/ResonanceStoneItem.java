package com.p1nero.tcrcore.item.custom;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import com.p1nero.tcrcore.capability.TCRPlayer;
import com.p1nero.tcrcore.utils.WorldUtil;
import com.yesman.epicskills.registry.entry.EpicSkillsSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ResonanceStoneItem extends Item {

    protected final ResourceLocation targetStructure;
    protected final ResourceKey<Level> dimension;
    protected final int y;
    public static final int SURFACE = 999;
    protected final BiConsumer<BlockPos, ServerPlayer> callback;
    protected final Predicate<ServerPlayer> predicate;
    protected boolean ignoreFounded = true;

    public ResonanceStoneItem(Properties properties, ResourceLocation targetStructure, int y, ResourceKey<Level> dimension, Predicate<ServerPlayer> predicate, BiConsumer<BlockPos, ServerPlayer> callback) {
        super(properties);
        this.targetStructure = targetStructure;
        this.dimension = dimension;
        this.callback = callback;
        this.predicate = predicate;
        this.y = y;
    }

    public ResonanceStoneItem(Properties properties, ResourceLocation targetStructure, int y, ResourceKey<Level> dimension, Predicate<ServerPlayer> predicate, BiConsumer<BlockPos, ServerPlayer> callback, boolean ignoreFounded) {
        this(properties, targetStructure, y, dimension, predicate, callback);
        this.ignoreFounded = ignoreFounded;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if(player instanceof ServerPlayer serverPlayer && !itemStack.getOrCreateTag().getBoolean("searching")) {
            if(predicate.test(serverPlayer) && level.dimension().equals(dimension)) {
                itemStack.getOrCreateTag().putBoolean("searching", true);
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("resonance_stone_working", this.getDescription()), true);
                serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(EpicSkillsSounds.GAIN_ABILITY_POINTS.get()), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                BlockPos pos = null;
                try {
                    pos = WorldUtil.getNearbyStructurePos(serverPlayer, targetStructure.toString(), y, ignoreFounded);
                } catch (Exception e) {
                    TCRCoreMod.LOGGER.error("TCRCore : Error finding structure [{}]: ", targetStructure, e);
                    player.displayClientMessage(TCRCoreMod.getInfo("resonance_search_failed", targetStructure).withStyle(ChatFormatting.RED), false);
                    itemStack.getOrCreateTag().putBoolean("searching", false);
                }
                if(pos != null) {
                    if(y == SURFACE) {
                        pos = WorldUtil.getSurfaceBlockPos(serverPlayer.serverLevel(), pos);
                    }
                    TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(player);
                    tcrPlayer.playDirectionParticle(player.getEyePosition(), new Vec3(pos.getX(), player.getEyeY(), pos.getZ()));
                    itemStack.shrink(1);
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(EpicSkillsSounds.GAIN_ABILITY_POINTS.get()), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                    if(!TCRCoreMod.isXaeroMapLoaded() && !TCRCoreMod.isJourneyMapLoaded()) {
                        ResonanceStoneItem.handleNoXaeroMap(Component.literal(targetStructure.toString()), pos, serverPlayer);
                    }
                    callback.accept(pos, serverPlayer);
                } else {
                    player.displayClientMessage(TCRCoreMod.getInfo("resonance_search_failed", targetStructure).withStyle(ChatFormatting.RED), false);
                    itemStack.getOrCreateTag().putBoolean("searching", false);
                }
            } else {
                player.displayClientMessage(TCRCoreMod.getInfo("can_not_do_this_too_early"), false);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, level, list, flag);
        list.add(TCRCoreMod.getInfo("resonance_stone_usage").withStyle(ChatFormatting.GRAY));
        String dimensionNameKey = Util.makeDescriptionId("travelerstitles", dimension.location());
        if(Language.getInstance().has(dimensionNameKey)) {
            list.add(Component.translatable(dimensionNameKey));
        }
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }

    public static void handleNoXaeroMap(Component prefix, BlockPos pos, ServerPlayer serverPlayer) {
        String s = pos.getY() == ResonanceStoneItem.SURFACE ? String.valueOf(WorldUtil.getSurfaceBlockPos(serverPlayer.serverLevel(), pos.getX(), pos.getZ()).getY()) : String.valueOf(pos.getY());
        s = " " + s + " ";
        String finalS = s;
        Component location = ComponentUtils.wrapInSquareBrackets(Component.translatable("chat.coordinates", pos.getX(), s, pos.getZ()))
                .withStyle((style) -> style
                        .withColor(ChatFormatting.GREEN)
                        .withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp @s " + pos.getX() + finalS + pos.getZ()))
                        .withHoverEvent(new HoverEvent(net.minecraft.network.chat.HoverEvent.Action.SHOW_TEXT, Component.translatable("chat.coordinates.tooltip"))));

        serverPlayer.displayClientMessage(Component.literal("Map not loaded. Failed to mark pos!").withStyle(ChatFormatting.RED), false);
        serverPlayer.displayClientMessage(prefix.copy().withStyle(ChatFormatting.GOLD), false);
        serverPlayer.displayClientMessage(location, false);
    }

}
