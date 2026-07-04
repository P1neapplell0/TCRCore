package com.p1nero.tcrcore.gameassets;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.hm.efn.gameasset.animations.EFNGreatSwordAnimations;
import com.nameless.falchion.gameasset.FalchionSkills;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TCRWeaponPresets {

    public static final Function<Item, CapabilityItem.Builder> THE_INCINERATOR = (item) ->
            WeaponCapability.builder().category(CapabilityItem.WeaponCategories.GREATSWORD)
                    .styleProvider((entityPatch) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(new MultiOBBCollider(3, 0.6, 0.6, 1.8F, 0.0F, 0.0F, -1.3F))
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemStack -> TCRSkills.THE_INCINERATOR_INNATE))
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            EFNGreatSwordAnimations.NG_GREATSWORD_AUTO1,
                            EFNGreatSwordAnimations.NG_GREATSWORD_AUTO2,
                            EFNGreatSwordAnimations.NG_GREATSWORD_AUTO3,
                            EFNGreatSwordAnimations.NG_GREATSWORD_DASH,
                            EFNGreatSwordAnimations.NG_GREATSWORD_AIRSLASH)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, EFNGreatSwordAnimations.NG_GREATSWORD_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, EFNGreatSwordAnimations.NG_GREATSWOED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, EFNGreatSwordAnimations.NG_GREATSWORD_RUN);

    public static final Function<Item, CapabilityItem.Builder> FALCHION = (item) ->
            WeaponCapability.builder().category(CapabilityItem.WeaponCategories.SPEAR)
                    .styleProvider((playerpatch) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(ColliderPreset.SPEAR)
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            AnimationsX.SPEAR_TWOHAND_AUTO1,
                            AnimationsX.SPEAR_TWOHAND_AUTO2,
                            ExtraAnimations.SPEAR_TWOHAND_AUTO3,
                            ExtraAnimations.SPEAR_TWOHAND_AUTO4,
                            ExtraAnimations.SPEAR_TWOHAND_AUTO5,
                            Animations.SPEAR_DASH,
                            AnimationsX.SPEAR_TWOHAND_AIR_SLASH)
                    .newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SPEAR_MOUNT_ATTACK)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> FalchionSkills.FALCHION_ART)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, AnimationsX.BIPED_HOLD_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, AnimationsX.BIPED_WALK_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, AnimationsX.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, AnimationsX.BIPED_RUN_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, AnimationsX.BIPED_HOLD_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, AnimationsX.SPEAR_GUARD);

    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "the_incinerator"), THE_INCINERATOR);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "falchion"), FALCHION);
    }
}
