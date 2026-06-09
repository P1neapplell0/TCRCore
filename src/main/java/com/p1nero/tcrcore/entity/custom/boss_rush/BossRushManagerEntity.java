package com.p1nero.tcrcore.entity.custom.boss_rush;

import com.p1nero.entityrespawner.entity.EntityRespawnerEntities;
import com.p1nero.entityrespawner.entity.SoulEntity;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BossRushManagerEntity extends PathfinderMob {

    public static final String BOSS_RUSH_TAG = "TCRBossRushEntity";

    private static final int CHECK_INTERVAL = 20;
    private static final String BOSS_LIST_TAG = "BossList";
    private static final String INDEX_TAG = "BossIndex";
    private static final String STARTED_TAG = "BossRushStarted";
    private static final String FINISHED_TAG = "BossRushFinished";
    private static final String CURRENT_BOSS_SPAWNED_TAG = "CurrentBossSpawned";

    private final ServerBossEvent serverBossEvent = new ServerBossEvent(Component.empty(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);

    private List<EntityType<?>> bossList = List.of();

    private int index;
    private boolean bossRushStarted;
    private boolean bossRushFinished;
    private boolean currentBossSpawned;
    private int nextCheckTick;

    public BossRushManagerEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoAi(true);
        this.setNoGravity(true);
        this.noPhysics = true;
        this.noCulling = true;
    }

    public void setBossList(List<EntityType<?>> bossList) {
        this.bossList = List.copyOf(bossList);
        this.refreshBossBar();
    }

    public void onBossRushStarted() {
        this.bossRushStarted = true;
        this.bossRushFinished = false;
        this.currentBossSpawned = false;
        this.index = 0;
        this.nextCheckTick = 0;
        this.serverBossEvent.setVisible(true);
        this.refreshBossBar();
    }

    public void onBossRushFinished() {
        if (this.bossRushFinished) {
            this.discard();
            return;
        }
        this.bossRushFinished = true;
        this.serverBossEvent.setProgress(1.0F);
        this.serverBossEvent.setVisible(false);
        this.serverBossEvent.removeAllPlayers();
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.players().forEach(serverPlayer -> {
                if (!serverPlayer.isSpectator()) {
                    PlayerDataManager.bossRushFinished.put(serverPlayer, true);
                    ItemStack proof = TCRItems.PROOF_OF_BOSS_RUSH.get().getDefaultInstance();
                    String timeUsed = getTime();
                    proof.getOrCreateTag().putString("time_used", timeUsed);
                    proof.getOrCreateTag().putString("owner_name", serverPlayer.getGameProfile().getName());
                    ItemUtils.addItem(serverPlayer, proof, true);
                    serverPlayer.connection.send(new ClientboundSetTitleTextPacket(TCRCoreMod.getInfo("time_used", timeUsed).withStyle(ChatFormatting.GOLD)));
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 1.0F, 1.0F, serverPlayer.getRandom().nextInt()));
                }
            });
        }
        this.discard();
    }

    private String getTime() {
        int totalSeconds = tickCount / 20;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return minutes + "m" + seconds + "s";
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .build();
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(0.0D, 0.0D, 0.0D);
        if (this.level().isClientSide && tickCount % 10 == 0) {
            for (int i = 0; i < 3; ++i) {
                level().addParticle(ParticleTypes.SOUL, this.getX() + (this.random.nextDouble() - (double) 0.5F) * (double) 0.5F, this.getY() + this.random.nextDouble(), this.getZ() + (this.random.nextDouble() - (double) 0.5F) * (double) 0.5F, 0.0F, 0.05F, 0.0F);
            }
        }
        if (this.level().isClientSide || tickCount < 100 || !this.bossRushStarted || this.bossRushFinished) {
            return;
        }
        if (this.tickCount >= this.nextCheckTick && this.level() instanceof ServerLevel serverLevel) {
            this.nextCheckTick = this.tickCount + CHECK_INTERVAL;
            this.tickBossRush(serverLevel);
        }
    }

    private void tickBossRush(ServerLevel serverLevel) {
        if (this.bossList.isEmpty() || this.index >= this.bossList.size()) {
            this.onBossRushFinished();
            return;
        }
        EntityType<?> currentBossType = this.getCurrentBossType();
        if (currentBossType == null) {
            this.onBossRushFinished();
            return;
        }

        //必须等这玩意儿复活，不然可能重复召唤boss
        if (!serverLevel.getEntities(EntityRespawnerEntities.SOUL_ENTITY.get(), Entity::isAlive).isEmpty()) {
            return;
        }

        if (!this.currentBossSpawned) {
            this.spawnCurrentBoss(serverLevel);
            return;
        }

        boolean hasAliveBoss = !serverLevel.getEntities(currentBossType, entity -> !entity.isRemoved() && entity.getTags().contains(BOSS_RUSH_TAG)).isEmpty();

        if (hasAliveBoss) {
            this.refreshBossBar();
            return;
        }
        this.index++;
        this.currentBossSpawned = false;//延迟出现，确保至少有2s的时间间隔（展示一下进度条hhhh）
    }

    private void spawnCurrentBoss(ServerLevel serverLevel) {
        EntityType<?> currentBossType = this.getCurrentBossType();
        if (currentBossType == null) {
            this.onBossRushFinished();
            return;
        }
        Entity entity = currentBossType.create(serverLevel);
        if (entity == null) {
            this.index++;
            this.currentBossSpawned = false;
            if (this.index >= this.bossList.size()) {
                this.onBossRushFinished();
            }
            return;
        }
        entity.addTag(BOSS_RUSH_TAG);//标记，玩家死了不复活
        entity.addTag(SoulEntity.TAG);//防止掉重置用的物品
        entity.moveTo(this.getX(), this.getY(), this.getZ(), serverLevel.random.nextFloat() * 360.0F, 0.0F);
        if (entity instanceof Mob mob) {
            mob.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(this.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
        }
        serverLevel.addFreshEntity(entity);
        this.currentBossSpawned = true;
        this.refreshBossBar();
    }

    private EntityType<?> getCurrentBossType() {
        if (this.index < 0 || this.index >= this.bossList.size()) {
            return null;
        }
        return this.bossList.get(this.index);
    }

    private void refreshBossBar() {
        if (this.bossList.isEmpty()) {
            this.serverBossEvent.setName(Component.literal("Boss Rush"));
            this.serverBossEvent.setProgress(0.0F);
            return;
        }
        int displayIndex = Math.min(this.index + 1, this.bossList.size());
        EntityType<?> currentBossType = this.getCurrentBossType();
        Component bossName = currentBossType == null ? Component.literal("Unknown") : currentBossType.getDescription();
        Component name = bossName.copy().append(Component.literal(" | " + displayIndex + "/" + this.bossList.size() + " " + getTime()));
        this.serverBossEvent.setName(name);
        this.serverBossEvent.setProgress(Math.min(1.0F, displayIndex / (float) this.bossList.size()));
        this.serverBossEvent.setVisible(true);
        level().players().forEach(player -> player.displayClientMessage(name, true));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        ListTag listTag = new ListTag();
        for (EntityType<?> bossType : this.bossList) {
            ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(bossType);
            if (key != null) {
                listTag.add(StringTag.valueOf(key.toString()));
            }
        }
        pCompound.put(BOSS_LIST_TAG, listTag);
        pCompound.putInt(INDEX_TAG, this.index);
        pCompound.putBoolean(STARTED_TAG, this.bossRushStarted);
        pCompound.putBoolean(FINISHED_TAG, this.bossRushFinished);
        pCompound.putBoolean(CURRENT_BOSS_SPAWNED_TAG, this.currentBossSpawned);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        List<EntityType<?>> restoredBossList = new ArrayList<>();
        ListTag listTag = pCompound.getList(BOSS_LIST_TAG, CompoundTag.TAG_STRING);
        for (int i = 0; i < listTag.size(); i++) {
            ResourceLocation key = ResourceLocation.tryParse(listTag.getString(i));
            if (key == null) {
                continue;
            }
            EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(key);
            if (type != null) {
                restoredBossList.add(type);
            }
        }
        this.bossList = List.copyOf(restoredBossList);
        this.index = Math.max(0, pCompound.getInt(INDEX_TAG));
        this.bossRushStarted = pCompound.getBoolean(STARTED_TAG);
        this.bossRushFinished = pCompound.getBoolean(FINISHED_TAG);
        this.currentBossSpawned = pCompound.getBoolean(CURRENT_BOSS_SPAWNED_TAG);
        this.nextCheckTick = 0;
        this.refreshBossBar();
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isInvisible() {
        return true;
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.serverBossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.serverBossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void remove(@NotNull RemovalReason pReason) {
        this.serverBossEvent.removeAllPlayers();
        super.remove(pReason);
    }

    @Override
    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return false;
    }
}
