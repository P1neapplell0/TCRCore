package com.p1nero.tcrcore.entity.custom.mimic;

import com.google.common.collect.Maps;
import com.merlin204.avalon.entity.ai.AvalonAnimatedAttackGoal;
import com.merlin204.avalon.entity.ai.AvalonCombatBehaviors;
import com.merlin204.avalon.epicfight.AvalonFctions;
import com.merlin204.avalon.item.IChangeArmatureItem;
import com.merlin204.avalon.item.animationitem.IAvalonAnimationItem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.merlin204.mimic.copy.CopyAnimationInfo;
import org.merlin204.mimic.entity.MimicPatch;
import org.merlin204.mimic.entity.proteus.ProteusEntity;
import org.merlin204.mimic.entity.shadow.ShadowMimicEntity;
import org.merlin204.mimic.epicfight.MimicAnimations;
import reascer.wom.animation.attacks.BasicMultipleAttackAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPChangeLivingMotion;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.Faction;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.merlin204.avalon.util.AvalonParticleUtils.createJointSphereParticles;

public class TCRMimicPatch<T extends ProteusEntity> extends MimicPatch<T> {

    protected Map<Integer, Set<Pair<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>>>> phaseLivingMotions;
    protected Map<Integer, AvalonCombatBehaviors.Builder<MimicPatch<?>>> weaponAttackMotions;

    private int phaseOld;

    @Override
    public Faction getFaction() {
        return AvalonFctions.EMPTY;
    }

    @Override
    public void tick(LivingEvent.LivingTickEvent event) {
        super.tick(event);
        if (!original.level().isClientSide){
            if(original.getPhase() == 2) {
                original.setPhase(3);
            }

            if (phaseOld != original.getPhase()){
                this.setAIAsInfantry();
                modifyLivingMotionByPhase(false);
            }

            if (original.getHealth() <= original.getMaxHealth()*0.5F && original.getPhase() <= 1 && !getEntityState().inaction()){
                this.playAnimationSynchronized(MimicAnimations.PHASE_2,0);
            }
        }else {
            if (phaseOld < original.getPhase()){
                original.stopMusic();
                original.startMusic();
                if (original.getPhase() == 3){
                    createJointSphereParticles(this, Armatures.BIPED.get().chest, ParticleTypes.LARGE_SMOKE, 1, 0.3, 0.3, 200);
                }
            }
        }
        phaseOld = original.getPhase();
    }

    @Override
    protected void initAI() {
        super.initAI();
        setAIAsInfantry();
    }

    @Override
    public void onConstructed(T entityIn) {
        super.onConstructed(entityIn);
        setPhaseLivingMotions();
        if (!original.level().isClientSide){
            setMaxStunShield(150);
            setStunShield(150);
        }
    }

    protected AvalonCombatBehaviors.Builder<MimicPatch<?>> getHoldingItemWeaponMotionBuilder() {
        int phase = original.getPhase();
        if (this.weaponAttackMotions != null && this.weaponAttackMotions.containsKey(phase)) {
            return this.weaponAttackMotions.get(phase);
        }
        return TCRMimicCombatBehaviors.PHASE1;
    }

    public void setAIAsInfantry() {
        AvalonCombatBehaviors.Builder<MimicPatch<?>> builder = this.getHoldingItemWeaponMotionBuilder();
        if (builder != null) {
            this.original.goalSelector.addGoal(0, new AvalonAnimatedAttackGoal<>(this, builder.build(this)));
        }
    }

    protected void setPhaseLivingMotions() {
        this.phaseLivingMotions = Maps.newHashMap();
        this.phaseLivingMotions.put(1, Set.of(
                Pair.of(LivingMotions.IDLE, MimicAnimations.IDLE),
                Pair.of(LivingMotions.WALK, MimicAnimations.WALK),
                Pair.of(LivingMotions.DEATH, MimicAnimations.DEATH)
        ));
        this.phaseLivingMotions.put(2, Set.of(
                Pair.of(LivingMotions.IDLE, MimicAnimations.IDLE),
                Pair.of(LivingMotions.WALK, MimicAnimations.WALK),
                Pair.of(LivingMotions.DEATH, MimicAnimations.DEATH)
        ));
        this.phaseLivingMotions.put(3, Set.of(
                Pair.of(LivingMotions.IDLE, MimicAnimations.IDLE_END),
                Pair.of(LivingMotions.WALK, MimicAnimations.WALK_END),
                Pair.of(LivingMotions.DEATH, MimicAnimations.DEATH)
        ));
        this.weaponAttackMotions = Maps.newHashMap();
        this.weaponAttackMotions.put(1, TCRMimicCombatBehaviors.PHASE1);
        this.weaponAttackMotions.put(2, TCRMimicCombatBehaviors.PHASE1);
        this.weaponAttackMotions.put(3, TCRMimicCombatBehaviors.PHASE3);

    }

    @Override
    public void onStartTracking(ServerPlayer trackingPlayer) {
        setPhaseLivingMotions();
        this.modifyLivingMotionByPhase(true);
    }

    @Override
    protected void serverTick(LivingEvent.LivingTickEvent event) {
        super.serverTick(event);
        Vec3 Pos = this.getOriginal().position();
        Level world = this.getOriginal().level();
        AABB searchArea = new AABB(
                Pos.x - 60, Pos.y - 2, Pos.z - 60,
                Pos.x + 60, Pos.y + 2, Pos.z + 60
        );

        List<ShadowMimicEntity> entities = world.getEntitiesOfClass(
                ShadowMimicEntity.class,
                searchArea,
                e -> e.isAlive()
                        && e.getOwner() == original
        );
        if (entities.isEmpty() && original.getPhase() == 2){
            setStunShield(99999999);
            this.original.setPhase(3);
        }

    }

    @Override
    public void tickNearbyEntity(LivingEntity entity) {
        super.tickNearbyEntity(entity);
        if (entity instanceof TCRMimic proteus){
            if (proteus.getHealth() <= original.getHealth()){
                CompoundTag copy = new CompoundTag();
                proteus.saveCopy(copy);
                original.loadCopy(copy);
                proteus.discard();
            }
        }
    }

    @Override
    public OpenMatrix4f getModelMatrix(float partialTicks) {
        return super.getModelMatrix(partialTicks).scale(1.5F, 1.5F, 1.5F);
    }

    /**
     * 移除buff复制
     */
    @Override
    public void tryToLearnAnimation(LivingEntity livingEntity){
        LivingEntityPatch<?> livingEntityPatch = EpicFightCapabilities.getEntityPatch(livingEntity,LivingEntityPatch.class);
        if (livingEntityPatch != null && livingEntityPatch.getArmature() instanceof HumanoidArmature){

            if (livingEntityPatch.getAnimator().getPlayerFor(null).getAnimation().get() instanceof AttackAnimation animation){
                ItemStack main = livingEntity.getMainHandItem();
                ItemStack off = livingEntity.getOffhandItem();
                if (main.getItem() instanceof IAvalonAnimationItem || main.getItem() instanceof IChangeArmatureItem
                        || off.getItem() instanceof IAvalonAnimationItem || off.getItem() instanceof IChangeArmatureItem
                        || animation instanceof BasicMultipleAttackAnimation) {
                    return;
                }
                if (main != ItemStack.EMPTY){
                    CopyAnimationInfo copyAnimationInfo = new CopyAnimationInfo(animation.getAccessor(),this,livingEntity.getMainHandItem(),livingEntity.getOffhandItem());
                    if (animation.getTotalTime() < 8F){
                        this.copyMap.put(animation.getAccessor(),copyAnimationInfo);
                    }
                }
            }
        }
    }

    public void modifyLivingMotionByPhase(boolean onStartTracking) {
        Map<LivingMotion, AssetAccessor<? extends StaticAnimation>> oldLivingAnimations = this.getAnimator().getLivingAnimations();
        Map<LivingMotion, AssetAccessor<? extends StaticAnimation>> newLivingAnimations = Maps.newHashMap();

        int phase = this.original.getPhase();

        boolean hasChange = false;

        if (this.phaseLivingMotions != null) {

            if (phaseLivingMotions.containsKey(phase)) {
                Set<Pair<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>>> animModifierSet =
                        phaseLivingMotions.get(phase);

                for (Pair<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> pair : animModifierSet) {
                    LivingMotion motion = pair.getFirst();
                    AnimationManager.AnimationAccessor<? extends StaticAnimation> newAnim = pair.getSecond();

                    // 检查动画是否发生变化
                    if (oldLivingAnimations.containsKey(motion)) {
                        if (oldLivingAnimations.get(motion) != newAnim) {
                            hasChange = true;
                        }
                    } else {
                        // 新增的动作类型也算变化
                        hasChange = true;
                    }

                    // 添加到新动画映射
                    newLivingAnimations.put(motion, newAnim);
                }
            }
        }

        // 检查是否有被移除的动作
        if (!hasChange) {
            for (LivingMotion oldMotion : oldLivingAnimations.keySet()) {
                if (!newLivingAnimations.containsKey(oldMotion)) {
                    hasChange = true;
                    break;
                }
            }
        }

        // 如果有变化或需要强制更新
        if (hasChange || onStartTracking) {
            this.getAnimator().resetLivingAnimations();
            newLivingAnimations.forEach(this.getAnimator()::addLivingAnimation);

            SPChangeLivingMotion msg = new SPChangeLivingMotion(this.original.getId());
            msg.putEntries(newLivingAnimations.entrySet());
            EpicFightNetworkManager.sendToAllPlayerTrackingThisEntity(msg, this.original);
        }
    }
}