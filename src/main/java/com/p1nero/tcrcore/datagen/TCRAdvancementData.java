package com.p1nero.tcrcore.datagen;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.item.TCRItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.p1nero.ss.item.SwordSoaringItems;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TCRAdvancementData extends ForgeAdvancementProvider {

    public static final String PRE = "advancement." + TCRCoreMod.MOD_ID + ".";
    public TCRAdvancementData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper helper) {
        super(output, registries, helper, List.of(new TCRAdvancements()));

    }

    public static class TCRAdvancements implements AdvancementGenerator {
        private Consumer<Advancement> consumer;
        private ExistingFileHelper helper;

        @SuppressWarnings("unused")
        @Override
        public void generate(HolderLookup.@NotNull Provider provider, @NotNull Consumer<Advancement> consumer, @NotNull ExistingFileHelper existingFileHelper) {
            this.consumer = consumer;
            this.helper = existingFileHelper;

            Advancement root = Advancement.Builder.advancement()
                    .display(ModItems.STORM_EYE.get(),
                            Component.translatable(PRE + TCRCoreMod.MOD_ID),
                            Component.translatable(PRE + TCRCoreMod.MOD_ID + ".desc"),
                            ResourceLocation.fromNamespaceAndPath(Cataclysm.MODID,"textures/block/azure_seastone_bricks.png"),
                            FrameType.TASK, false, false, false)
                    .addCriterion(TCRCoreMod.MOD_ID, new ImpossibleTrigger.TriggerInstance())
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, TCRCoreMod.MOD_ID), existingFileHelper);

            Advancement vatansever = Advancement.Builder.advancement()
                    .display(SwordSoaringItems.VATANSEVER.get(),
                            SwordSoaringItems.VATANSEVER.get().getDescription(),
                            Component.literal(""),
                            null,
                            FrameType.CHALLENGE, true, true, false)
                    .addCriterion(TCRCoreMod.MOD_ID, InventoryChangeTrigger.TriggerInstance.hasItems(SwordSoaringItems.VATANSEVER.get()))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "vatansever"), existingFileHelper);

            //ftb解锁武器和盔甲
            Advancement unlock_weapon_armor_book = registerAdvancement(root, "unlock_weapon_armor_book", FrameType.CHALLENGE, TCRItems.MYSTERIOUS_WEAPONS.get(), false, false, true);

            //ftb解锁魔法和boss
            Advancement unlock_magic_and_boss = registerAdvancement(root, "unlock_magic_and_boss", FrameType.CHALLENGE, TCRItems.NECROMANCY_SCROLL.get(), false, false, true);

            //ftb解锁轮回绝境图鉴
            Advancement unlock_epic_boss = registerAdvancement(root, "unlock_epic_boss", FrameType.CHALLENGE, TCRItems.WITHER_SOUL_STONE.get(), false, false, true);

        }

        public Advancement registerItemAdvancement(Advancement parent, ItemLike display, boolean specialDesc) {
            if(!specialDesc) {
                return registerItemAdvancement(parent, display);
            }
            String disc = "item." + display.asItem();
            ItemStack itemStack = display.asItem().getDefaultInstance();
            return Advancement.Builder.advancement()
                    .parent(parent)
                    .display(display,
                            display.asItem().getName(itemStack),
                            Component.translatable(itemStack.getDescriptionId() + ".adv_desc"),
                            null,
                            FrameType.GOAL, true, true, false)
                    .addCriterion(disc, InventoryChangeTrigger.TriggerInstance.hasItems(itemStack.getItem()))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, disc), helper);
        }

        public Advancement registerItemAdvancement(Advancement parent, ItemLike display) {
            String disc = "item." + display.asItem();
            ItemStack itemStack = display.asItem().getDefaultInstance();
            MutableComponent desc = Component.empty();
            List<Component> descList = new ArrayList<>();
            itemStack.getItem().appendHoverText(itemStack, null, descList, TooltipFlag.NORMAL);
            for(Component component : descList){
                desc.append("\n").append(component);
            }
            return Advancement.Builder.advancement()
                    .parent(parent)
                    .display(display,
                            display.asItem().getName(itemStack),
                            desc,
                            null,
                            FrameType.GOAL, true, true, false)
                    .addCriterion(disc, InventoryChangeTrigger.TriggerInstance.hasItems(itemStack.getItem()))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, disc), helper);
        }

        public Advancement registerAdvancement(Advancement parent, String name, FrameType type, ItemLike display, boolean showToast, boolean announceToChat, boolean hidden) {
            return Advancement.Builder.advancement()
                    .parent(parent)
                    .display(display,
                            Component.translatable(PRE + name),
                            Component.translatable(PRE + name + ".desc"),
                            null,
                            type, showToast, announceToChat, hidden)
                    .addCriterion(name, new ImpossibleTrigger.TriggerInstance())
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name), helper);
        }

        public Advancement registerAdvancement(Advancement parent, String name, FrameType type, ItemLike display, boolean showToast, boolean announceToChat, boolean hidden, CriterionTriggerInstance triggerInstance) {
            return Advancement.Builder.advancement()
                    .parent(parent)
                    .display(display,
                            Component.translatable(PRE + name),
                            Component.translatable(PRE + name + ".desc"),
                            null,
                            type, showToast, announceToChat, hidden)
                    .addCriterion(name, triggerInstance)
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name), helper);
        }

        public Advancement registerAdvancement(Advancement parent, String name, FrameType type, ItemLike display, CriterionTriggerInstance instance) {
            return Advancement.Builder.advancement()
                    .parent(parent)
                    .display(display,
                            Component.translatable(PRE + name),
                            Component.translatable(PRE + name + ".desc"),
                            null,
                            type, true, true, true)
                    .addCriterion(name, instance)
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name), helper);
        }

        public Advancement registerAdvancement(Advancement parent, String name, FrameType type, ItemLike display) {
            return registerAdvancement(parent, name, type, display, true, true, true);
        }

    }

    public static void revokeAllAdvancement(ServerPlayer serverPlayer) {
        serverPlayer.server.getAdvancements().getAllAdvancements().forEach(advancement -> {
            revokeAdvancement(advancement, serverPlayer);
        });
    }

    public static boolean revokeAdvancement(ResourceLocation id,  ServerPlayer serverPlayer) {
        Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(id);
        return revokeAdvancement(advancement, serverPlayer);
    }

    public static boolean revokeAdvancement(Advancement advancement, ServerPlayer serverPlayer) {
        if(advancement != null) {
            AdvancementProgress advancementprogress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
            if (!advancementprogress.hasProgress()) {
                return false;
            } else {
                for(String s : advancementprogress.getCompletedCriteria()) {
                    serverPlayer.getAdvancements().revoke(advancement, s);
                }

                return true;
            }
        }
        return false;
    }

    public static void finishAdvancement(Advancement advancement, ServerPlayer serverPlayer) {
        AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
        if (!progress.isDone()) {
            for (String criteria : progress.getRemainingCriteria()) {
                serverPlayer.getAdvancements().award(advancement, criteria);
            }
        }
    }

    public static void finishAdvancement(String name, ServerPlayer serverPlayer) {
        Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name));
        if (advancement == null) {
            TCRCoreMod.LOGGER.error("advancement:\"{}\" is null!", name);
            return;
        }
        finishAdvancement(advancement, serverPlayer);
    }

    public static boolean isDone(String name, ServerPlayer serverPlayer) {
        Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name));
        if (advancement == null) {
            TCRCoreMod.LOGGER.info("advancement:\"{}\" is null!", name);
            return false;
        }
        return isDone(advancement, serverPlayer);
    }

    public static boolean isDone(Advancement advancement, ServerPlayer serverPlayer) {
        AdvancementProgress advancementProgress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
        return advancementProgress.isDone();
    }

}
