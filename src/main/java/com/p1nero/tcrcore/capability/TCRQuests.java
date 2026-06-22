package com.p1nero.tcrcore.capability;

import com.brass_amber.ba_bt.init.BTEntityType;
import com.brass_amber.ba_bt.init.BTItems;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.alexthe668.domesticationinnovation.server.item.DIItemRegistry;
import com.hm.efn.gameasset.EFNSkills;
import com.hm.efn.registries.EFNItem;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRQuestManager.Quest;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.gameassets.TCRSkills;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.WorldUtils;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import com.yesman.epicskills.registry.entry.EpicSkillsItems;
import com.yungnickyoung.minecraft.ribbits.module.EntityTypeModule;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.merlin204.leonidas.entity.LeonidasEntities;
import org.merlin204.leonidas.item.LeonidasItems;
import yesman.epicfight.world.item.EpicFightItems;
import yesman.epicfight.world.item.SkillBookItem;

public class TCRQuests {

    public static final ResourceLocation SIDE_QUEST_1 = ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/gui/side_quest_1.png");
    public static final ResourceLocation SIDE_QUEST_2 = ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/gui/side_quest_2.png");

    //通用的一些任务
    //等共鸣石充能
    public static Quest WAIT_RESONANCE_STONE_CHARGE;
    //放置祭坛之上
    public static Quest PUT_DESERT_EYE_ON_ALTAR;
    public static Quest PUT_ABYSS_EYE_ON_ALTAR;
    public static Quest PUT_CURSED_EYE_ON_ALTAR;
    public static Quest PUT_FLAME_EYE_ON_ALTAR;
    public static Quest PUT_MECH_EYE_ON_ALTAR;
    public static Quest PUT_STORM_EYE_ON_ALTAR;
    public static Quest PUT_VOID_EYE_ON_ALTAR;
    public static Quest PUT_MONST_EYE_ON_ALTAR;

    public static Quest KILL_DESERT_EYE;
    public static Quest KILL_ABYSS_EYE;
    public static Quest KILL_CURSED_EYE;
    public static Quest KILL_FLAME_EYE;
    public static Quest KILL_MECH_EYE;
    public static Quest KILL_STORM_EYE;
    public static Quest KILL_VOID_EYE;
    public static Quest KILL_MONST_EYE;

    //去女神像处祈福
    public static Quest BLESS_ON_THE_GODNESS_STATUE;
    //向安了解幻境
    public static Quest TALK_TO_AINE_CLOUDLAND;

    //序章
    public static Quest TALK_TO_AINE_0;
    public static Quest TALK_TO_CHRONOS_0;
    public static Quest TALK_TO_FERRY_GIRL_0;
    public static Quest TALK_TO_ORNN_0;

    //养龙支线
    public static Quest TAME_DRAGON;
    public static Quest TAME_DRAGON_BACK_TO_FERRY_GIRL;

    //前往获取大地之眼
    public static Quest USE_LAND_RESONANCE_STONE;
    public static Quest GET_DESERT_EYE;
    public static Quest TALK_TO_CHRONOS_1;
    //奇美拉支线
    public static Quest BONE_CHIMERA_QUEST;
    //百兵图给ornn
    public static Quest TALK_TO_ORNN_1;

    //获取海洋眼
    public static Quest TALK_TO_CHRONOS_2;
    //前往主世界-海洋眼
    public static Quest GO_TO_OVERWORLD_OCEAN;
    public static Quest USE_OCEAN_RESONANCE_STONE;
    public static Quest GET_OCEAN_EYE;
    public static Quest TALK_TO_CHRONOS_3;
    //呱呱支线
    public static Quest RIBBITS_QUEST;
    public static Quest GIVE_AMETHYST_BLOCK_TO_RIBBITS;

    //主线·灵魂之章
    public static Quest TALK_TO_AINE_ECHO;
    public static Quest TALK_TO_CHRONOS_4;
    public static Quest GO_TO_OVERWORLD_CURSED;
    public static Quest USE_CURSED_RESONANCE_STONE;
    public static Quest FIND_HELMET_IN_OCEAN_MONUMENT;
    public static Quest GET_CURSED_EYE;
    public static Quest TALK_TO_CHRONOS_5;

    //铁魔法开启
    public static Quest TALK_TO_AINE_MAGIC;
    public static Quest TRY_TO_LEARN_MAGIC;
    public static Quest TALK_TO_AINE_MAGIC_2;

    //主线·炉心傀儡
    public static Quest TALK_TO_CHRONOS_6;
    public static Quest GO_TO_OVERWORLD_CORE;
    public static Quest USE_CORE_RESONANCE_STONE;
    public static Quest GET_FLAME_EYE;
    public static Quest TALK_TO_CHRONOS_7;
    //间章，和安闲聊
    public static Quest TALK_TO_AINE_1;

    //主线·地狱傀儡
    public static Quest TALK_TO_CHRONOS_8;
    public static Quest GO_TO_NETHER;
    public static Quest USE_NETHER_RESONANCE_STONE;
    public static Quest GET_NETHER_MONOLITH_KEY_1;//制作铃铛，召唤boss，铁魔法城堡
    public static Quest GET_NETHER_MONOLITH_KEY_2;//肘下界堡垒
    public static Quest GET_MONST_EYE;
    public static Quest TALK_TO_CHRONOS_9;

    //主线·毁灭之章
    public static Quest GET_WITHER_EYE;
    public static Quest TALK_TO_CHRONOS_10;
    public static Quest TALK_TO_AINE_SAMSARA;
    public static Quest GO_TO_SAMSARA;

    //主线·天域之章
    public static Quest TALK_TO_CHRONOS_11;
    public static Quest GO_TO_AETHER;
    public static Quest USE_AETHER_RESONANCE_STONE;
    public static Quest GET_AETHER_MONOLITH_KEY_1;//肘女武神殿
    public static Quest GET_AETHER_MONOLITH_KEY_2;//肘烈阳殿
    public static Quest GET_STORM_EYE;
    public static Quest TALK_TO_SKY_GOLEM;
    public static Quest TALK_TO_CHRONOS_12;
    public static Quest TALK_TO_AINE_2;

    //主线·末地之章
    public static Quest GO_TO_OVERWORLD_END;
    public static Quest USE_END_RESONANCE_STONE;
    public static Quest GO_TO_THE_END;
    public static Quest GET_VOID_EYE;

    public static Quest TALK_TO_ORNN_YAMATO;

    //大结局
    public static Quest TALK_TO_CHRONOS_END;
    public static Quest KILL_MAD_CHRONOS;

    //后日谈
    public static Quest TALK_TO_AINE_GAME_CLEAR;

    //前往冒险家协会
    public static Quest TALK_TO_BOUNTIFUL_VILLAGER;

    public static void init() {

        WAIT_RESONANCE_STONE_CHARGE = TCRQuestManager.create("wait_resonance_stone_charge")
                .shortDescParam(TCRItems.RESONANCE_STONE.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription());

        KILL_DESERT_EYE = TCRQuestManager.create("kill_desert_eye")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), ModItems.SANDSTORM_IN_A_BOTTLE.get())
                .shortDescParam(ModEntities.ANCIENT_REMNANT.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .descParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW),
                        ModEntities.ANCIENT_REMNANT.get().getDescription().copy().withStyle(ChatFormatting.YELLOW),
                        ModItems.NECKLACE_OF_THE_DESERT.get().getDescription().copy().withStyle(ChatFormatting.YELLOW),
                        ModEntities.ANCIENT_REMNANT.get().getDescription().copy().withStyle(ChatFormatting.YELLOW));
        KILL_MECH_EYE = TCRQuestManager.create("kill_mech_eye")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), ModItems.WITHERITE_INGOT.get())
                .shortDescParam(ModEntities.THE_HARBINGER.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .descParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        ModEntities.THE_HARBINGER.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        Items.NETHER_STAR.getDescription().copy().withStyle(ChatFormatting.GOLD),
                        ModEntities.THE_HARBINGER.get().getDescription().copy().withStyle(ChatFormatting.GOLD));
        KILL_MONST_EYE = TCRQuestManager.create("kill_monst_eye")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), ModItems.INFERNAL_FORGE.get())
                .shortDescParam(ModEntities.NETHERITE_MONSTROSITY.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        ModEntities.NETHERITE_MONSTROSITY.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED));
        KILL_STORM_EYE = TCRQuestManager.create("kill_storm_eye")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), ModItems.ESSENCE_OF_THE_STORM.get(), ModItems.LACRIMA.get())
                .shortDescParam(ModEntities.SCYLLA.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModEntities.SCYLLA.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.CERAUNUS.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModEntities.SCYLLA.get().getDescription().copy().withStyle(ChatFormatting.AQUA));
        KILL_ABYSS_EYE = TCRQuestManager.create("kill_abyss_eye")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), ModItems.TIDAL_CLAWS.get())
                .shortDescParam(ModEntities.THE_LEVIATHAN.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .descParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        ModEntities.THE_LEVIATHAN.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        ModItems.ABYSSAL_SACRIFICE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        ModEntities.THE_LEVIATHAN.get().getDescription().copy().withStyle(ChatFormatting.BLUE));
        KILL_CURSED_EYE = TCRQuestManager.create("kill_cursed_eye")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), ModItems.CURSIUM_INGOT.get())
                .shortDescParam(ModEntities.MALEDICTUS.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        ModEntities.MALEDICTUS.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        ModItems.STRANGE_KEY.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        ModEntities.MALEDICTUS.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN));
        KILL_FLAME_EYE = TCRQuestManager.create("kill_flame_eye")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), ModItems.IGNITIUM_INGOT.get())
                .shortDescParam(ModEntities.IGNIS.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModEntities.IGNIS.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.BURNING_ASHES.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModEntities.IGNIS.get().getDescription().copy().withStyle(ChatFormatting.RED));
        KILL_VOID_EYE = TCRQuestManager.create("kill_void_eye")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), ModItems.GAUNTLET_OF_GUARD.get())
                .shortDescParam(ModEntities.ENDER_GUARDIAN.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .descParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE),
                        ModEntities.ENDER_GUARDIAN.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE),
                        ModEntities.ENDER_GUARDIAN.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE));

        PUT_DESERT_EYE_ON_ALTAR = TCRQuestManager.create("put_desert_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .descParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .withTrackingPos(new BlockPos(WorldUtils.DESERT_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_ABYSS_EYE_ON_ALTAR = TCRQuestManager.create("put_abyss_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .descParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .withTrackingPos(new BlockPos(WorldUtils.ABYSS_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_CURSED_EYE_ON_ALTAR = TCRQuestManager.create("put_cursed_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .withTrackingPos(new BlockPos(WorldUtils.CURSED_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_FLAME_EYE_ON_ALTAR = TCRQuestManager.create("put_flame_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .withTrackingPos(new BlockPos(WorldUtils.FLAME_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_MECH_EYE_ON_ALTAR = TCRQuestManager.create("put_mech_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .descParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .withTrackingPos(new BlockPos(WorldUtils.MECH_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_STORM_EYE_ON_ALTAR = TCRQuestManager.create("put_storm_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .withTrackingPos(new BlockPos(WorldUtils.STORM_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_VOID_EYE_ON_ALTAR = TCRQuestManager.create("put_void_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .descParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .withTrackingPos(new BlockPos(WorldUtils.VOID_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_MONST_EYE_ON_ALTAR = TCRQuestManager.create("put_monst_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .withTrackingPos(new BlockPos(WorldUtils.MONST_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        BLESS_ON_THE_GODNESS_STATUE = TCRQuestManager.create("bless_on_the_godness_statue")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .withTrackingPos(new BlockPos(WorldUtils.GODNESS_STATUE_POS), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_CLOUDLAND = TCRQuestManager.create("talk_to_aine_cloudland")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_0 = TCRQuestManager.create("talk_to_aine_0")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription(), TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_CHRONOS_0 = TCRQuestManager.create("talk_to_col_0")
                .descParam(TCREntities.AINE.get().getDescription(), TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_FERRY_GIRL_0 = TCRQuestManager.create("talk_to_ferry_girl_0")
                .shortDescParam(WorldUtils.OVERWORLD_NAME)
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(), WorldUtils.OVERWORLD_NAME)
                .withTrackingPos(new BlockPos(WorldUtils.FERRY_GIRL_POS.above(1)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_ORNN_0 = TCRQuestManager.create("talk_to_ornn_0")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TAME_DRAGON = TCRQuestManager.create("tame_dragon")
                .withIcon(SIDE_QUEST_1)
                .withRewards(Items.SADDLE)
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(),
                        net.magister.bookofdragons.item.ModItems.BOOK_OF_DRAGONS.get().getDescription(),
                        Component.translatable("item.domesticationinnovation.collar_tag"),
                        Items.SADDLE.getDescription());

        TAME_DRAGON_BACK_TO_FERRY_GIRL = TCRQuestManager.create("tame_dragon_back_to_ferry_girl")
                .shortDescParam(TCREntities.FERRY_GIRL.get().getDescription())
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(), TCREntities.FERRY_GIRL.get().getDescription(), Items.SADDLE.getDescription(), DIItemRegistry.COLLAR_TAG.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withRewards(Items.SADDLE)
                .withTrackingPos(new BlockPos(WorldUtils.FERRY_GIRL_POS.above(1)), TCRDimensions.SANCTUM_LEVEL_KEY);

        USE_LAND_RESONANCE_STONE = TCRQuestManager.create("use_land_resonance_stone")
                .shortDescParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription())
                .descParam(WorldUtils.OVERWORLD_NAME, TCRItems.LAND_RESONANCE_STONE.get().getDescription());

        GET_DESERT_EYE = TCRQuestManager.create("get_desert_eye")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .shortDescParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription(),
                        ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW),
                        ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW),
                        Items.SPAWNER.getDescription());

        BONE_CHIMERA_QUEST = TCRQuestManager.create("bone_chimera_quest")
                .withIcon(SIDE_QUEST_1)
                .withRewards(TCRItems.MYSTERIOUS_WEAPONS.get())
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription(), TCRItems.MYSTERIOUS_WEAPONS.get().getDescription())
                .shortDescParam(WorldUtils.getStructureName(WorldUtils.BONE_CHIMERA_STRUCTURE));

        TALK_TO_ORNN_1 = TCRQuestManager.create("talk_to_ornn_1")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(com.github.dodo.dodosmobs.init.ModEntities.BONE_CHIMERA.get().getDescription(), TCRItems.MYSTERIOUS_WEAPONS.get().getDescription(),TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_1 = TCRQuestManager.create("talk_to_chronos_1")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_2 = TCRQuestManager.create("talk_to_chronos_2")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_OCEAN = TCRQuestManager.create("go_to_overworld_ocean")
                .shortDescParam(WorldUtils.OVERWORLD_NAME)
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), WorldUtils.OVERWORLD_NAME,
                        ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE));

        USE_OCEAN_RESONANCE_STONE = TCRQuestManager.create("use_ocean_resonance_stone")
                .shortDescParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription())
                .descParam(WorldUtils.OVERWORLD_NAME, TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(),
                        ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE));

        GET_OCEAN_EYE = TCRQuestManager.create("get_ocean_eye")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .shortDescParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .descParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(),
                        ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        Items.SPAWNER.getDescription());

        TALK_TO_CHRONOS_3 = TCRQuestManager.create("talk_to_chronos_3")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        TCREntities.CHRONOS_SOL.get().getDescription(),
                        ItemRegistry.ICY_FANG.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        ItemStack waterAvoid = new ItemStack(EpicFightItems.SKILLBOOK.get());
        SkillBookItem.setContainingSkill(TCRSkills.WATER_AVOID, waterAvoid);

        RIBBITS_QUEST = TCRQuestManager.create("ribbits_quest")
                .withIcon(SIDE_QUEST_1)
                .withRewards(waterAvoid, artifacts.registry.ModItems.CHARM_OF_SINKING.get().getDefaultInstance())
                .shortDescParam(WorldUtils.getStructureName(WorldUtils.RIBBIT_VILLAGE))
                .descParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(),
                        Component.translatable(TCRSkills.WATER_AVOID.getTranslationKey()),
                        artifacts.registry.ModItems.CHARM_OF_SINKING.get().getDescription());
        GIVE_AMETHYST_BLOCK_TO_RIBBITS = TCRQuestManager.create("give_amethyst_block_to_ribbits")
                .withIcon(SIDE_QUEST_1)
                .withRewards(waterAvoid, artifacts.registry.ModItems.CHARM_OF_SINKING.get().getDefaultInstance())
                .shortDescParam(Items.AMETHYST_BLOCK.getDescription(), EntityTypeModule.RIBBIT.get().getDescription())
                .descParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        EntityTypeModule.RIBBIT.get().getDescription(),
                        Items.AMETHYST_BLOCK.getDescription(),
                        Component.translatable(TCRSkills.WATER_AVOID.getTranslationKey()),
                        artifacts.registry.ModItems.CHARM_OF_SINKING.get().getDescription());

        TALK_TO_AINE_ECHO = TCRQuestManager.create("talk_to_aine_echo")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(),
                        ItemRegistry.ICY_FANG.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_4 = TCRQuestManager.create("talk_to_chronos_4")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription(),
                        ItemRegistry.ICY_FANG.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_CURSED = TCRQuestManager.create("go_to_overworld_cursed")
                .shortDescParam(WorldUtils.OVERWORLD_NAME)
                .descParam(ItemRegistry.ICY_FANG.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        WorldUtils.OVERWORLD_NAME,
                        TCRItems.CURSED_RESONANCE_STONE.get().getDescription());

        USE_CURSED_RESONANCE_STONE = TCRQuestManager.create("use_cursed_resonance_stone")
                .shortDescParam(TCRItems.CURSED_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(ItemRegistry.ICY_FANG.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        WorldUtils.OVERWORLD_NAME,
                        TCRItems.CURSED_RESONANCE_STONE.get().getDescription());

        FIND_HELMET_IN_OCEAN_MONUMENT = TCRQuestManager.create("find_helmet_in_ocean_monument")
                .shortDescParam(LeonidasItems.THE_LOST_HELMET.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .descParam(TCRItems.CURSED_RESONANCE_STONE.get().getDescription(),
                        LeonidasEntities.LEONIDAS.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        WorldUtils.getStructureName(WorldUtils.OCEAN_MONUMENT),
                        WorldUtils.getStructureName(WorldUtils.OCEAN_MONUMENT));

        GET_CURSED_EYE = TCRQuestManager.create("get_cursed_eye")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .shortDescParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(TCRItems.CURSED_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        LeonidasEntities.LEONIDAS.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN));

        TALK_TO_CHRONOS_5 = TCRQuestManager.create("talk_to_chronos_5")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_MAGIC = TCRQuestManager.create("talk_to_aine_magic")
                .withRewards(TCRItems.MAGIC_BOTTLE.get())
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(LeonidasEntities.LEONIDAS.get().getDescription(),
                        TCRItems.NECROMANCY_SCROLL.get().getDescription(),
                        TCREntities.AINE.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TRY_TO_LEARN_MAGIC = TCRQuestManager.create("try_to_learn_magic")
                .withIcon(SIDE_QUEST_1)
                .withRewards(TCRItems.MAGIC_BOTTLE.get())
                .descParam(TCRItems.NECROMANCY_SCROLL.get().getDescription(), TCREntities.AINE.get().getDescription(),
                        TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_MAGIC_2 = TCRQuestManager.create("talk_to_aine_magic_2")
                .withRewards(TCRItems.MAGIC_BOTTLE.get())
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_6 = TCRQuestManager.create("talk_to_chronos_6")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_CORE = TCRQuestManager.create("go_to_overworld_core")
                .shortDescParam(WorldUtils.OVERWORLD_NAME)
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), WorldUtils.OVERWORLD_NAME,
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        USE_CORE_RESONANCE_STONE = TCRQuestManager.create("use_core_resonance_stone")
                .shortDescParam(TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(WorldUtils.OVERWORLD_NAME, TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        GET_FLAME_EYE = TCRQuestManager.create("get_flame_eye")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .shortDescParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        TALK_TO_CHRONOS_7 = TCRQuestManager.create("talk_to_chronos_7")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_1 = TCRQuestManager.create("talk_to_aine_1")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_8 = TCRQuestManager.create("talk_to_chronos_8")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_NETHER = TCRQuestManager.create("go_to_nether")
                .shortDescParam(WorldUtils.NETHER_NAME)
                .descParam(WorldUtils.OVERWORLD_NAME, WorldUtils.OVERWORLD_NAME, TCREntities.CHRONOS_SOL.get().getDescription(), TCRItems.CORE_FLINT.get().getDescription(), Blocks.NETHER_PORTAL.getName());

        USE_NETHER_RESONANCE_STONE = TCRQuestManager.create("use_nether_resonance_stone")
                .shortDescParam(TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(WorldUtils.NETHER_NAME, TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED));

        GET_NETHER_MONOLITH_KEY_1 = TCRQuestManager.create("get_nether_monolith_key_1")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), TCRItems.BLOOD_LOTUS.get())
                .shortDescParam(ItemRegistry.CINDEROUS_SOULCALLER.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        WorldUtils.getStructureName(WorldUtils.NETHER_KEY_1),
                        ItemRegistry.CINDEROUS_SOULCALLER.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        TCRBossEntities.GOLDEN_EXECUTOR.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.GOLD));

        GET_NETHER_MONOLITH_KEY_2 = TCRQuestManager.create("get_nether_monolith_key_2")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), TCRItems.BLOOD_LOTUS.get())
                .shortDescParam(BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        WorldUtils.getStructureName(WorldUtils.NETHER_KEY_2),
                        WorldUtils.getStructureName(WorldUtils.NETHER_KEY_2),
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        TCRBossEntities.EVENING_GHOST.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.GOLD));

        GET_MONST_EYE = TCRQuestManager.create("get_monst_eye")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .shortDescParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        TCRSkills.FIRE_AVOID.getDisplayName().copy().withStyle(ChatFormatting.GOLD));

        TALK_TO_CHRONOS_9 = TCRQuestManager.create("talk_to_chronos_9")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GET_WITHER_EYE = TCRQuestManager.create("get_wither_eye")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), TCRItems.WITHER_SOUL_STONE.get())
                .shortDescParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), EntityType.WITHER.getDescription().copy().withStyle(ChatFormatting.GOLD),
                        ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD));

        TALK_TO_CHRONOS_10 = TCRQuestManager.create("talk_to_chronos_10")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_SAMSARA = TCRQuestManager.create("talk_to_aine_samsara")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), TCRItems.WITHER_SOUL_STONE.get().getDescription(),
                        Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1"),
                        Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1"),
                        TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_SAMSARA = TCRQuestManager.create("go_to_samsara")
                .withIcon(SIDE_QUEST_1)
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .shortDescParam(Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1").withStyle(ChatFormatting.RED))
                .descParam(Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1").withStyle(ChatFormatting.RED),
                        TCREntities.AINE.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        TCRItems.WITHER_SOUL_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN));

        TALK_TO_CHRONOS_11 = TCRQuestManager.create("talk_to_chronos_11")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_AETHER = TCRQuestManager.create("go_to_aether")
                .shortDescParam(WorldUtils.AETHER_NAME)
                .descParam(Blocks.WATER.getName().withStyle(ChatFormatting.BLUE), Blocks.GLOWSTONE.getName().withStyle(ChatFormatting.YELLOW));

        USE_AETHER_RESONANCE_STONE = TCRQuestManager.create("use_aether_resonance_stone")
                .shortDescParam(TCRItems.SKY_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(TCRItems.SKY_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA));

        GET_AETHER_MONOLITH_KEY_1 = TCRQuestManager.create("get_aether_monolith_key_1")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), TCRItems.NINE_HEAVEN_DARKSTEEL.get())
                .shortDescParam(BTItems.SKY_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(BTEntityType.SKY_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        BTItems.SKY_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        WorldUtils.getStructureName(WorldUtils.AETHER_KEY_1),
                        WorldUtils.getStructureName(WorldUtils.AETHER_KEY_1),
                        BTItems.SKY_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCRBossEntities.VALKYRIE.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTItems.SKY_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.AQUA));

        GET_AETHER_MONOLITH_KEY_2 = TCRQuestManager.create("get_aether_monolith_key_2")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), TCRItems.NINE_HEAVEN_DARKSTEEL.get())
                .shortDescParam(BTItems.SKY_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(BTEntityType.SKY_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        BTItems.SKY_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        WorldUtils.getStructureName(WorldUtils.AETHER_KEY_2),
                        WorldUtils.getStructureName(WorldUtils.AETHER_KEY_2),
                        BTItems.SKY_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCRBossEntities.GILDED_HUNTER.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTItems.SKY_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.AQUA));

        GET_STORM_EYE = TCRQuestManager.create("get_storm_eye")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .shortDescParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(TCRItems.SKY_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA));

        TALK_TO_SKY_GOLEM = TCRQuestManager.create("talk_to_sky_golem")
                .shortDescParam(BTEntityType.SKY_GOLEM.get().getDescription())
                .descParam(BTEntityType.SKY_GOLEM.get().getDescription(), BTEntityType.SKY_GOLEM.get().getDescription());

        TALK_TO_CHRONOS_12 = TCRQuestManager.create("talk_to_chronos_12")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_2 = TCRQuestManager.create("talk_to_aine_2")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_END = TCRQuestManager.create("go_to_overworld_end")
                .shortDescParam(WorldUtils.OVERWORLD_NAME)
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(),
                        WorldUtils.END_NAME,
                        WorldUtils.OVERWORLD_NAME,
                        WorldUtils.END_NAME,
                        WorldUtils.getStructureName(WorldUtils.STRONG_HOLD),
                        WorldUtils.END_NAME);

        USE_END_RESONANCE_STONE = TCRQuestManager.create("use_end_resonance_stone")
                .shortDescParam(TCRItems.END_RESONANCE_STONE.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(),
                        WorldUtils.END_NAME,
                        WorldUtils.OVERWORLD_NAME,
                        WorldUtils.END_NAME,
                        WorldUtils.getStructureName(WorldUtils.STRONG_HOLD),
                        WorldUtils.END_NAME);

        GO_TO_THE_END = TCRQuestManager.create("go_to_the_end")
                .shortDescParam(WorldUtils.END_NAME)
                .descParam(TCRItems.END_RESONANCE_STONE.get().getDescription(),
                        WorldUtils.getStructureName(WorldUtils.STRONG_HOLD),
                        WorldUtils.END_NAME);

        GET_VOID_EYE = TCRQuestManager.create("get_void_eye")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get(), EFNItem.YAMATO_DMC_IN_SHEATH.get())
                .shortDescParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .descParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE),
                        WorldUtils.END_NAME);

        TALK_TO_ORNN_YAMATO = TCRQuestManager.create("talk_to_ornn_yamato")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(EFNItem.YAMATO_DMC_IN_SHEATH.get().getDescription(), TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_END = TCRQuestManager.create("talk_to_chronos_end")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        KILL_MAD_CHRONOS = TCRQuestManager.create("kill_mad_chronos")
                .withTrackingPos(new BlockPos(WorldUtils.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_GAME_CLEAR = TCRQuestManager.create("talk_to_aine_game_clear")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtils.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_BOUNTIFUL_VILLAGER = TCRQuestManager.create("talk_to_bountiful_villager")
                .withRewards(EpicSkillsItems.ABILIITY_STONE.get())
                .descParam(WorldUtils.OVERWORLD_NAME)
                .withIcon(SIDE_QUEST_1);
    }
}