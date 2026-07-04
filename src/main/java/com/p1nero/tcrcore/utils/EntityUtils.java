package com.p1nero.tcrcore.utils;

import com.p1nero.battle_field1.worldgen.PBF1Dimensions;
import com.p1nero.cataclysm_dimension.worldgen.CataclysmDimensions;
import com.p1nero.tcrcore.gamerule.TCRGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.shelmarow.combat_evolution.ai.util.BehaviorUtils;
import net.shelmarow.combat_evolution.execution.ExecutionHandler;
import net.shelmarow.combat_evolution.execution.ExecutionTask;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager;
import net.shelmarow.combat_evolution.tickTask.TickTaskManager;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EntityUtils {

    public static void adminsDo(ServerLevel targetLevel, Consumer<ServerPlayer> consumer) {
        targetLevel.getServer().getPlayerList().getPlayers().forEach(serverPlayer -> {
            if(serverPlayer.hasPermissions(2)) {
                consumer.accept(serverPlayer);
            }
        });
    }

    public static boolean hasNonCreativeOrSpectator(ServerLevel targetLevel) {
        if(targetLevel.players().isEmpty()) {
            return false;
        }
        return targetLevel.players().stream()
                .anyMatch(p -> !p.isCreative() && !p.isSpectator());
    }

    public static int countOfNoneCreativeOrSpectator(ServerLevel targetLevel) {
        return targetLevel.players().stream()
                .filter(p -> !p.isCreative() && !p.isSpectator()).toList().size();
    }

    public static boolean hasAllowedPlayerCount(ServerLevel targetLevel) {
        if(PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY.equals(targetLevel.dimension())) {
            return countOfNoneCreativeOrSpectator(targetLevel) < TCRGameRules.getMaxInfiniteSamsaraPlayerCount(targetLevel);
        }
        if(CataclysmDimensions.LEVELS.contains(targetLevel.dimension())) {
            return countOfNoneCreativeOrSpectator(targetLevel) < TCRGameRules.getMaxCloudlandPlayerCount(targetLevel);
        }
        return true;
    }

    public static void destroyNearby(Entity living, float scale, boolean drop) {
        if(living == null) {
            return;
        }
        Vec3 offset = living.getLookAngle().normalize().scale(scale);
        int ox = Mth.floor(living.getX() + offset.x);
        int oy = Mth.floor(living.getY() + (double)0.25F);
        int oz = Mth.floor(living.getZ() + offset.z);
        int width = Mth.ceil(living.getBbWidth() / 2.0F);
        int height = Mth.ceil(living.getBbHeight());
        boolean playEffectFlag = false;

        for(int ix = ox - width; ix <= ox + width; ++ix) {
            for(int iy = oy; iy <= oy + height; ++iy) {
                for(int iz = oz - width; iz <= oz + width; ++iz) {
                    BlockPos pos = new BlockPos(ix, iy, iz);
                    BlockState state = living.level().getBlockState(pos);
                    if (state.getBlock() instanceof FireBlock) {
                        living.level().destroyBlock(pos, false, living);
                    } else {
                        playEffectFlag |= living.level().destroyBlock(pos, drop, living);
                    }
                }
            }
        }

        if (playEffectFlag) {
            living.level().gameEvent(living, GameEvent.BLOCK_DESTROY, living.blockPosition());
        }
    }

    public static void entityForceExecuteToDie(LivingEntity executor, LivingEntity target) {
        if (executor != null && target != null) {
            PlayerPatch<?> executorPatch = EpicFightCapabilities.getEntityPatch(executor, PlayerPatch.class);
            LivingEntityPatch<?> targetPatch = EpicFightCapabilities.getEntityPatch(target, LivingEntityPatch.class);
            if (targetPatch != null && executorPatch != null) {
                if(!executorPatch.isEpicFightMode()) {
                    executorPatch.toEpicFightMode(true);
                }
                ExecutionTypeManager.Type executionType = ExecutionHandler.getExecutionType(executorPatch, targetPatch);
                ExecutionHandler.ExecutionTransform transform = calculateExecutionPosition(executor.level(), executor, target, executionType.offset());
                BehaviorUtils.stopCurrentBehavior(executor);
                BehaviorUtils.stopCurrentBehavior(target);
                executor.setDeltaMovement(Vec3.ZERO);
                target.setDeltaMovement(Vec3.ZERO);
                Vec3 executionPos = transform.position();
                executor.teleportTo(executionPos.x, executionPos.y, executionPos.z);
                TickTaskManager.addTask(target.getUUID(), new ExecutionTask(executor, target, executionType, transform, executionType.totalTick()){
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        target.kill();
                    }
                });
            }
        }
    }

    private static ExecutionHandler.ExecutionTransform calculateExecutionPosition(Level level, LivingEntity executor, LivingEntity target, Vec3 offset) {
        float yaw = target.getYRot();
        return findPosAround(level, executor, target, offset, yaw);
    }

    private static ExecutionHandler.ExecutionTransform findPosAround(Level level, LivingEntity executor, LivingEntity target, Vec3 offset, float yaw) {
        double rad = Math.toRadians(yaw);
        double forwardX = -Math.sin(rad);
        double forwardZ = Math.cos(rad);
        double rightX = Math.cos(rad);
        double rightZ = Math.sin(rad);
        double offsetX = forwardX * offset.x + rightX * offset.z;
        double offsetY = offset.y;
        double offsetZ = forwardZ * offset.x + rightZ * offset.z;
        Vec3 testPos = target.position().add(offsetX, offsetY, offsetZ);
        return new ExecutionHandler.ExecutionTransform(testPos, yaw);
    }

    public static void safelyClearAll(ServerLevel serverLevel) {
        List<Entity> newList = new ArrayList<>();
        serverLevel.getAllEntities().forEach(newList::add);
        for(Entity entity : newList) {
            entity.discard();
        }
    }

    public static void playLocalSound(ServerPlayer player, SoundEvent soundEvent) {
        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundEvent), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
    }

    public static void playLocalSound(ServerPlayer player, Vec3 pos, SoundEvent soundEvent) {
        player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundEvent), SoundSource.PLAYERS, pos.x(), pos.y(), pos.z(), 1.0F, 1.0F, player.getRandom().nextInt()));
    }

    /**
     * 获取视线和目标位置连线的夹角
     */
    public static double getDegree(Entity target, Entity self){
        return getDegree(target.position(), self);
    }

    /**
     * 获取视线和目标位置连线的夹角
     */
    public static double getDegree(Vec3 target, Entity self){
        Vec3 targetToBoss = target.subtract(self.position());
        Vec2 targetToBossV2 = new Vec2(((float) targetToBoss.x), ((float) targetToBoss.z));
        Vec3 view = self.getViewVector(1.0F);
        Vec2 viewV2 = new Vec2(((float) view.x), ((float) view.z));
        double angleRadians = Math.acos(targetToBossV2.dot(viewV2)/(targetToBossV2.length() * viewV2.length()));
        return Math.toDegrees(angleRadians);
    }

    /**
     * 获取视线和目标视线的夹角
     */
    public static double getViewDegree(Entity target, Entity self){
        Vec3 targetView = target.getViewVector(1.0F);
        Vec2 targetViewV2 = new Vec2(((float) targetView.x), ((float) targetView.z));
        Vec3 view = self.getViewVector(1.0F);
        Vec2 viewV2 = new Vec2(((float) view.x), ((float) view.z));
        double angleRadians = Math.acos(targetViewV2.dot(viewV2)/(targetViewV2.length() * viewV2.length()));
        return Math.toDegrees(angleRadians);
    }

    /**
     * 返回一个范围
     * @param pos 中心位置
     * @param offset 半径
     * @return 以pos为中心offset的两倍为边长的一个正方体
     */
    public static AABB getPlayerAABB(BlockPos pos, int offset){
        return new AABB(pos.offset(offset,offset,offset),pos.offset(-offset,-offset,-offset));
    }

    public static boolean isInFront(Entity target, Entity self, double degree){
        return Math.abs(getDegree(target, self)) <= degree;
    }

    /**
     * 啥比Mojang获取附近实体非要视线对着才算
     */
    public static List<Entity> getNearByEntities(Entity self, int offset){
        return self.level().getEntities(self, getPlayerAABB(self.getOnPos(), offset), entity -> entity.distanceTo(self) < offset);
    }

    public static <T extends Entity> List<T> getNearByEntities(Level level, Vec3 center, int radius, Class<T> entityClass){
        return level.getEntitiesOfClass(entityClass, (new AABB(center, center)).inflate(radius));
    }

    /**
     * 获取附近的玩家
     */
    public static List<Player> getNearByPlayers(LivingEntity self, int offset){
        return self.level().getNearbyPlayers(TargetingConditions.forNonCombat(), self, getPlayerAABB(self.getOnPos(), offset));
    }

    public static <T extends LivingEntity> List<T> getNearByEntities(Class<T> aClass, LivingEntity self, int offset){
        return self.level().getNearbyEntities(aClass, TargetingConditions.forNonCombat(), self, getPlayerAABB(self.getOnPos(), offset));
    }

    public static void nearPlayerDo(LivingEntity self, int radius, Consumer<Player> consumer) {
        Vec3 center = self.position();
        self.level().getEntitiesOfClass(Player.class, (new AABB(center, center)).inflate(radius)).forEach(consumer);
    }

    public static void nearPlayerDo(Level level, Vec3 center, int radius, Consumer<Player> consumer) {
        level.getEntitiesOfClass(Player.class, (new AABB(center, center)).inflate(radius)).forEach(consumer);
    }

}
