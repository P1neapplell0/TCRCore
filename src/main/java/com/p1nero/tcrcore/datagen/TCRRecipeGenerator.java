package com.p1nero.tcrcore.datagen;

import com.aetherteam.aether.block.AetherBlocks;
import com.bobmowzie.mowziesmobs.server.item.ItemHandler;
import com.cerbon.bosses_of_mass_destruction.item.BMDItems;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.datagen.tags.TCRItemTags;
import com.p1nero.tcrcore.item.TCRItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import yesman.epicfight.world.item.EpicFightItems;

import java.util.function.Consumer;

public class TCRRecipeGenerator extends TCRRecipeProvider implements IConditionBuilder {
    public TCRRecipeGenerator(PackOutput output) {
        super(output);
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TCRItems.DRAGON_FLUTE.get(), 1)
                .pattern(" AA")
                .pattern("AAA")
                .pattern("BA ")
                .define('A', ModItems.KOBOLETON_BONE.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), 1)
                .requires(TCRItems.WITHER_SOUL_STONE.get())
                .requires(Items.GHAST_TEAR)
                .unlockedBy(getHasName(TCRItems.WITHER_SOUL_STONE.get()), has(TCRItems.WITHER_SOUL_STONE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), 1)
                .requires(TCRItemTags.CATACLYSM_HUMANOID_BOSS_DROP)
                .requires(TCRItemTags.CATACLYSM_HUMANOID_BOSS_DROP)
                .requires(TCRItemTags.CATACLYSM_HUMANOID_BOSS_DROP)
                .requires(TCRItemTags.CATACLYSM_HUMANOID_BOSS_DROP)
                .unlockedBy(getHasName(TCRItems.WITHER_SOUL_STONE.get()), has(TCRItems.WITHER_SOUL_STONE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TCRItems.PROOF_OF_ADVENTURE_PLUS.get(), 1)
                .requires(TCRItems.ABYSS_FRAGMENT.get())
                .requires(TCRItems.STORM_FRAGMENT.get())
                .requires(TCRItems.FLAME_FRAGMENT.get())
                .requires(TCRItems.MECH_FRAGMENT.get())
                .requires(TCRItems.SOUL_FRAGMENT.get())
                .requires(TCRItems.DESERT_FRAGMENT.get())
                .requires(TCRItems.ENDER_FRAGMENT.get())
                .requires(TCRItems.NETHERITE_FRAGMENT.get())
                .requires(TCRItems.DIVINE_FRAGMENT.get())
                .unlockedBy(getHasName(TCRItems.WITHER_SOUL_STONE.get()), has(TCRItems.WITHER_SOUL_STONE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TCRItems.BOSS_RUSH_MANAGER.get(), 1)
                .requires(TCRItems.ANCIENT_ORACLE_FRAGMENT.get())
                .requires(TCRItems.ANCIENT_ORACLE_FRAGMENT.get())
                .unlockedBy(getHasName(TCRItems.WITHER_SOUL_STONE.get()), has(TCRItems.WITHER_SOUL_STONE.get()))
                .save(consumer);

        //紫金匕
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TCRItems.BLOOD_LOTUS.get()),
                        Ingredient.of(EpicFightItems.NETHERITE_DAGGER.get()),
                        Ingredient.of(ModItems.BLACK_STEEL_INGOT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.EMBERFANG.get()
                ).unlocks("has_blood_lotus", has(TCRItems.BLOOD_LOTUS.get()))
                .save(consumer, modLoc("emberfang_smithing"));

        // 紫金斧 Magmaheart 改手写，读config不让datagen
//        SmithingTransformRecipeBuilder.smithing(
//                        Ingredient.of(TCRItems.BLOOD_LOTUS.get()),
//                        Ingredient.of(Items.NETHERITE_AXE),
//                        Ingredient.of(ItemHandler.WROUGHT_AXE.get()),
//                        RecipeCategory.COMBAT,
//                        TCRItems.MAGMAHEART.get()
//                ).unlocks("has_blood_lotus", has(TCRItems.BLOOD_LOTUS.get()))
//                .save(consumer, modLoc("magmaheart_smithing"));

        // 紫金长刀 Cinderwyrm
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TCRItems.BLOOD_LOTUS.get()),
                        Ingredient.of(EpicFightItems.NETHERITE_TACHI.get()),
                        Ingredient.of(Items.AMETHYST_SHARD),
                        RecipeCategory.COMBAT,
                        TCRItems.CINDERWYRM.get()
                ).unlocks("has_blood_lotus", has(TCRItems.BLOOD_LOTUS.get()))
                .save(consumer, modLoc("cinderwyrm_smithing"));

        // 紫金雁翎 Purging Swallow 改手写，读config不让datagen
//        SmithingTransformRecipeBuilder.smithing(
//                        Ingredient.of(TCRItems.BLOOD_LOTUS.get()),
//                        Ingredient.of(EpicFightItems.UCHIGATANA.get()),
//                        Ingredient.of(ItemHandler.SOL_VISAGE.get()),
//                        RecipeCategory.COMBAT,
//                        TCRItems.PURGING_SWALLOW.get()
//                ).unlocks("has_blood_lotus", has(TCRItems.BLOOD_LOTUS.get()))
//                .save(consumer, modLoc("purging_swallow_smithing"));

        // 紫金戟 Ashen Crescent
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TCRItems.BLOOD_LOTUS.get()),
                        Ingredient.of(EpicFightItems.NETHERITE_SPEAR.get()),
                        Ingredient.of(BMDItems.VOID_THORN.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.ASHEN_CRESCENT.get()
                ).unlocks("has_blood_lotus", has(TCRItems.BLOOD_LOTUS.get()))
                .save(consumer, modLoc("ashen_crescent_smithing"));

        // 玉曜 Lux Jadae
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TCRItems.NINE_HEAVEN_DARKSTEEL.get()),
                        Ingredient.of(Items.NETHERITE_SWORD),
                        Ingredient.of(AetherBlocks.GOLDEN_OAK_LEAVES.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.LUX_JADAE.get()
                ).unlocks("has_nine_heaven_darksteel", has(TCRItems.NINE_HEAVEN_DARKSTEEL.get()))
                .save(consumer, modLoc("lux_jadae_smithing"));

        // 玉霜 Glacis Jadae  改手写，读config不让datagen
//        SmithingTransformRecipeBuilder.smithing(
//                        Ingredient.of(TCRItems.NINE_HEAVEN_DARKSTEEL.get()),
//                        Ingredient.of(EpicFightItems.NETHERITE_LONGSWORD.get()),
//                        Ingredient.of(ItemHandler.ICE_CRYSTAL.get()),
//                        RecipeCategory.COMBAT,
//                        TCRItems.GLACIS_JADAE.get()
//                ).unlocks("has_nine_heaven_darksteel", has(TCRItems.NINE_HEAVEN_DARKSTEEL.get()))
//                .save(consumer, modLoc("glacis_jadae_smithing"));

        // 玉岳 Mons Jadae  改手写，读config不让datagen
//        SmithingTransformRecipeBuilder.smithing(
//                        Ingredient.of(TCRItems.NINE_HEAVEN_DARKSTEEL.get()),
//                        Ingredient.of(EpicFightItems.NETHERITE_GREATSWORD.get()),
//                        Ingredient.of(ItemHandler.WROUGHT_HELMET.get()),
//                        RecipeCategory.COMBAT,
//                        TCRItems.MONS_JADAE.get()
//                ).unlocks("has_nine_heaven_darksteel", has(TCRItems.NINE_HEAVEN_DARKSTEEL.get()))
//                .save(consumer, modLoc("mons_jadae_smithing"));

        // 玉虹 Iris Jadae
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TCRItems.NINE_HEAVEN_DARKSTEEL.get()),
                        Ingredient.of(EpicFightItems.NETHERITE_SPEAR.get()),
                        Ingredient.of(Items.DIAMOND),
                        RecipeCategory.COMBAT,
                        TCRItems.IRIS_JADAE.get()
                ).unlocks("has_nine_heaven_darksteel", has(TCRItems.NINE_HEAVEN_DARKSTEEL.get()))
                .save(consumer, modLoc("iris_jadae_smithing"));

        // 魔纹匕首：紫金短刃 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.EMBERFANG.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_DAGGER.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_dagger_smithing"));

        // 魔纹斧：紫金斧 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.MAGMAHEART.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_AXE.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_axe_smithing"));

        // 魔纹太刀：紫金长刀 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.CINDERWYRM.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_TACHI.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_tachi_smithing"));

        // 魔纹打刀：紫金雁翎 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.PURGING_SWALLOW.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_KATANA.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_katana_smithing"));

        // 魔纹戟：紫金戟 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.ASHEN_CRESCENT.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_HALBERD.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_halberd_smithing"));

        // 魔纹剑：玉曜 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.LUX_JADAE.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_SWORD.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_sword_smithing"));

        // 魔纹长剑：玉霜 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.GLACIS_JADAE.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_LONGSWORD.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_longsword_smithing"));

        // 魔纹大剑：玉岳 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.MONS_JADAE.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_GREATSWORD.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_greatsword_smithing"));

        // 魔纹枪：玉虹 + 下界之星模板 + 神谕残卷
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(TCRItems.IRIS_JADAE.get()),
                        Ingredient.of(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()),
                        RecipeCategory.COMBAT,
                        TCRItems.MAGIC_SPEAR.get()
                ).unlocks("has_nether_star", has(Items.NETHER_STAR))
                .save(consumer, modLoc("magic_spear_smithing"));

    }

    private ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, path);
    }

}
