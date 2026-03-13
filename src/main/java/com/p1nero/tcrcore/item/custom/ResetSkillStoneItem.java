package com.p1nero.tcrcore.item.custom;

import com.hm.efn.gameasset.EFNSkills;
import com.p1nero.dpr.gameassets.DPRSkills;
import com.yesman.epicskills.network.NetworkManager;
import com.yesman.epicskills.network.client.ClientBoundDeallocateAbilityPoints;
import com.yesman.epicskills.network.client.ClientBoundReloadSkillTree;
import com.yesman.epicskills.registry.entry.EpicSkillsSkillTrees;
import com.yesman.epicskills.registry.entry.EpicSkillsSounds;
import com.yesman.epicskills.skilltree.SkillTree;
import com.yesman.epicskills.world.capability.SkillTreeProgression;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.skill.SkillSlots;

import static com.p1nero.tcrcore.events.PlayerEventListeners.addSkill;

public class ResetSkillStoneItem extends SimpleDescriptionItem{

    public ResetSkillStoneItem(Properties properties) {
        super(properties, true);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(player instanceof ServerPlayer serverPlayer) {
            ResourceKey<SkillTree> dpr = ResourceKey.create(SkillTree.SKILL_TREE_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath("dodge_parry_reward", "passive"));
            player.getCapability(SkillTreeProgression.SKILL_TREE_PROGRESSION).ifPresent((skillTreeProgression) -> {
                skillTreeProgression.deallocateAbilityPoints(true);
                skillTreeProgression.unlockNode(EpicSkillsSkillTrees.BATTLEBORN, EFNSkills.EFN_DODGE_ROLL, serverPlayer);
                skillTreeProgression.unlockNode(EpicSkillsSkillTrees.BATTLEBORN, EFNSkills.EFN_DODGE_STEP, serverPlayer);
                skillTreeProgression.unlockNode(dpr, DPRSkills.STAMINA1, serverPlayer);

            });
            addSkill(serverPlayer, EFNSkills.EFN_DODGE_ROLL, SkillSlots.DODGE);
            addSkill(serverPlayer, EFNSkills.EFN_PARRY, SkillSlots.GUARD);
            addSkill(serverPlayer, DPRSkills.STAMINA1, SkillSlots.PASSIVE1);
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        player.playSound(EpicSkillsSounds.GAIN_ABILITY_POINTS.get(), 1.0F, 1.0F);
        return InteractionResultHolder.consume(itemstack);
    }
}
