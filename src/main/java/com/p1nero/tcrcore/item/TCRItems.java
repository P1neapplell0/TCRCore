package com.p1nero.tcrcore.item;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.brass_amber.ba_bt.init.BTItems;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.items.The_Incinerator;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.compat.JourneyMapCompat;
import com.p1nero.tcrcore.item.custom.*;
import com.p1nero.tcrcore.utils.XaeroWaypointUtils;
import com.p1nero.tcrcore.utils.WorldUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xaero.hud.minimap.waypoint.WaypointColor;

import java.util.List;

public class TCRItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TCRCoreMod.MOD_ID);
    public static final RegistryObject<Item> ANCIENT_ORACLE_FRAGMENT = REGISTRY.register("ancient_oracle_fragment", () -> new OracleItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> ARTIFACT_TICKET = REGISTRY.register("artifact_ticket", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant(), true));
    public static final RegistryObject<Item> RARE_ARTIFACT_TICKET = REGISTRY.register("rare_artifact_ticket", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), true));
    public static final RegistryObject<Item> ABYSS_CORE = REGISTRY.register("abyss_core", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), true));
    public static final RegistryObject<Item> VOID_CORE = REGISTRY.register("void_core", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), true));
    public static final RegistryObject<Item> DUAL_BOKKEN = REGISTRY.register("dual_bokken", () -> new DualBokkenItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant()));
    public static final RegistryObject<Item> PROOF_OF_ADVENTURE = REGISTRY.register("proof_of_adventure", () -> new ProofOfAdventureItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> PROOF_OF_ADVENTURE_PLUS = REGISTRY.register("proof_of_adventure_plus", () -> new ProofOfAdventureItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> CORE_FLINT = REGISTRY.register("core_flint", () -> new CoreFlintItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> DRAGON_FLUTE = REGISTRY.register("dragon_flute", () -> new DragonFluteItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> ABYSS_FRAGMENT = REGISTRY.register("abyss_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.LEVIATHAN_HUMANOID::get));
    public static final RegistryObject<Item> DESERT_FRAGMENT = REGISTRY.register("desert_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.ANCIENT_REMNANT_HUMANOID::get));
    public static final RegistryObject<Item> ENDER_FRAGMENT = REGISTRY.register("ender_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.ENDER_GUARDIAN_HUMANOID::get));
    public static final RegistryObject<Item> MECH_FRAGMENT = REGISTRY.register("mech_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.HARBINGER_HUMANOID::get));
    public static final RegistryObject<Item> NETHERITE_FRAGMENT = REGISTRY.register("netherite_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.NETHERITE_HUMANOID::get));
    public static final RegistryObject<Item> FLAME_FRAGMENT = REGISTRY.register("flame_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.IGNIS_HUMANOID::get));
    public static final RegistryObject<Item> STORM_FRAGMENT = REGISTRY.register("storm_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.SCYLLA_HUMANOID::get));
    public static final RegistryObject<Item> SOUL_FRAGMENT = REGISTRY.register("soul_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.MALEDICTUS_HUMANOID::get));

    public static final RegistryObject<Item> RESONANCE_STONE = REGISTRY.register("resonance_stone", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> RESET_SKILL_STONE = REGISTRY.register("reset_skill_stone", () -> new ResetSkillStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> RETRACEMENT_STONE = REGISTRY.register("retracement_stone", () -> new RetracementStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> LAND_RESONANCE_STONE = REGISTRY.register("land_resonance_stone",
            () -> new LandResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtils.LAND_GOLEM), ResonanceStoneItem.SURFACE, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_LAND_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) -> {
                        if (pos != null) {
                            if (TCRCoreMod.isXaeroMapLoaded()) {
                                XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.DESERT_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.LAND_GOLEM)))), pos, WaypointColor.YELLOW);
                            }
                            if (TCRCoreMod.isJourneyMapLoaded()) {
                                JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.DESERT_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.LAND_GOLEM)))), pos, ChatFormatting.YELLOW);
                            }
                            TCRQuests.USE_LAND_RESONANCE_STONE.finish(serverPlayer, true);
                            TCRQuests.GET_DESERT_EYE.start(serverPlayer);
                        }
                    }))
    );

    public static final RegistryObject<Item> OCEAN_RESONANCE_STONE = REGISTRY.register("ocean_resonance_stone",
            () -> new OceanResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtils.OCEAN_GOLEM), 63, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_OCEAN_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if (TCRCoreMod.isXaeroMapLoaded()) {
                            XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.ABYSS_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.OCEAN_GOLEM)))), pos, WaypointColor.BLUE);
                        }
                        if (TCRCoreMod.isJourneyMapLoaded()) {
                            JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.ABYSS_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.OCEAN_GOLEM)))), pos, ChatFormatting.BLUE);
                        }
                        TCRQuests.USE_OCEAN_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_OCEAN_EYE.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> CURSED_RESONANCE_STONE = REGISTRY.register("cursed_resonance_stone",
            () -> new ResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtils.OCEAN_MONUMENT), 63, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_CURSED_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if (TCRCoreMod.isXaeroMapLoaded()) {
                            XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.CURSED_EYE.get().getDescription(), WorldUtils.getStructureName(WorldUtils.OCEAN_MONUMENT)), pos, WaypointColor.DARK_GREEN);
                        }
                        if (TCRCoreMod.isJourneyMapLoaded()) {
                            JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.CURSED_EYE.get().getDescription(), WorldUtils.getStructureName(WorldUtils.OCEAN_MONUMENT)), pos, ChatFormatting.DARK_GREEN);
                        }
                        TCRQuests.USE_CURSED_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_CURSED_EYE.start(serverPlayer);
                        TCRQuests.FIND_HELMET_IN_OCEAN_MONUMENT.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> CORE_RESONANCE_STONE = REGISTRY.register("core_resonance_stone",
            () -> new ResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtils.CORE_GOLEM), -56, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_CORE_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if (TCRCoreMod.isXaeroMapLoaded()) {
                            XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.FLAME_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.CORE_GOLEM)))), pos, WaypointColor.RED);
                        }
                        if (TCRCoreMod.isJourneyMapLoaded()) {
                            JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.FLAME_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.CORE_GOLEM)))), pos, ChatFormatting.RED);
                        }
                        TCRQuests.USE_CORE_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_FLAME_EYE.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> NETHER_RESONANCE_STONE = REGISTRY.register("nether_resonance_stone",
            () -> new MultiResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(),
                    //地狱傀儡位置
                    List.of(new MultiResonanceStoneItem.TargetProperties(ResourceLocation.parse(WorldUtils.NETHER_GOLEM), 35, Level.NETHER, (serverPlayer) ->
                            TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_NETHER_RESONANCE_STONE) || serverPlayer.isCreative(),
                            ((pos, serverPlayer) ->
                            {
                                if (TCRCoreMod.isXaeroMapLoaded()) {
                                    XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.MONSTROUS_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.NETHER_GOLEM)))), pos, WaypointColor.DARK_RED);
                                }
                                if (TCRCoreMod.isJourneyMapLoaded()) {
                                    JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.MONSTROUS_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.NETHER_GOLEM)))), pos, ChatFormatting.DARK_RED);
                                }
                            }))
                            , //地狱钥匙位置1魔法城堡
                            new MultiResonanceStoneItem.TargetProperties(ResourceLocation.parse(WorldUtils.NETHER_KEY_1), 35, Level.NETHER, (serverPlayer) ->
                            TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_NETHER_RESONANCE_STONE) || serverPlayer.isCreative(),
                            ((pos, serverPlayer) ->
                            {
                                if (TCRCoreMod.isXaeroMapLoaded()) {
                                    XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", BTItems.NETHER_MONOLITH_KEY.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.NETHER_KEY_1)))), pos, WaypointColor.DARK_RED);
                                }
                                if (TCRCoreMod.isJourneyMapLoaded()) {
                                    JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", BTItems.NETHER_MONOLITH_KEY.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.NETHER_KEY_1)))), pos, ChatFormatting.DARK_RED);
                                }
                                //地狱钥匙位置2地狱堡垒
                            })), new MultiResonanceStoneItem.TargetProperties(ResourceLocation.parse(WorldUtils.NETHER_KEY_2), 35, Level.NETHER, (serverPlayer) ->
                            TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_NETHER_RESONANCE_STONE) || serverPlayer.isCreative(),
                            ((pos, serverPlayer) ->
                            {
                                if (TCRCoreMod.isXaeroMapLoaded()) {
                                    XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", BTItems.NETHER_MONOLITH_KEY.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.NETHER_KEY_2)))), pos, WaypointColor.DARK_RED);
                                }
                                if (TCRCoreMod.isJourneyMapLoaded()) {
                                    JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", BTItems.NETHER_MONOLITH_KEY.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.NETHER_KEY_2)))), pos, ChatFormatting.DARK_RED);
                                }
                            }))
                    ),
                    (serverPlayer -> {
                        TCRQuests.USE_NETHER_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_MONST_EYE.start(serverPlayer);
                        TCRQuests.GET_NETHER_MONOLITH_KEY_2.start(serverPlayer);
                        TCRQuests.GET_NETHER_MONOLITH_KEY_1.start(serverPlayer);
                    })
            )
    );

    public static final RegistryObject<Item> SKY_RESONANCE_STONE = REGISTRY.register("sky_resonance_stone",
            () -> new MultiResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(),
                    //天域傀儡位置
                    List.of(new MultiResonanceStoneItem.TargetProperties(ResourceLocation.parse(WorldUtils.SKY_GOLEM), 160, AetherDimensions.AETHER_LEVEL, (serverPlayer) ->
                            TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_AETHER_RESONANCE_STONE) || serverPlayer.isCreative(),
                            ((pos, serverPlayer) ->
                            {
                                if (TCRCoreMod.isXaeroMapLoaded()) {
                                    XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.STORM_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.SKY_GOLEM)))), pos, WaypointColor.AQUA);
                                }
                                if (TCRCoreMod.isJourneyMapLoaded()) {
                                    JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.STORM_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.SKY_GOLEM)))), pos, ChatFormatting.AQUA);
                                }

                            }))
                            //天域钥匙位置1
                            , new MultiResonanceStoneItem.TargetProperties(ResourceLocation.parse(WorldUtils.AETHER_KEY_1), ResonanceStoneItem.SURFACE, AetherDimensions.AETHER_LEVEL, (serverPlayer) ->
                            TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_AETHER_RESONANCE_STONE) || serverPlayer.isCreative(),
                            ((pos, serverPlayer) ->
                            {
                                if (TCRCoreMod.isXaeroMapLoaded()) {
                                    XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", BTItems.SKY_MONOLITH_KEY.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.AETHER_KEY_1)))), pos, WaypointColor.AQUA);
                                }
                                if (TCRCoreMod.isJourneyMapLoaded()) {
                                    JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", BTItems.SKY_MONOLITH_KEY.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.AETHER_KEY_1)))), pos, ChatFormatting.AQUA);
                                }
                                //天域钥匙位置2
                            })), new MultiResonanceStoneItem.TargetProperties(ResourceLocation.parse(WorldUtils.AETHER_KEY_2), 100, AetherDimensions.AETHER_LEVEL, (serverPlayer) ->
                            TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_AETHER_RESONANCE_STONE) || serverPlayer.isCreative(),
                            ((pos, serverPlayer) ->
                            {
                                if (TCRCoreMod.isXaeroMapLoaded()) {
                                    XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", BTItems.SKY_MONOLITH_KEY.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.AETHER_KEY_2)))), pos, WaypointColor.AQUA);
                                }
                                if (TCRCoreMod.isJourneyMapLoaded()) {
                                    JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", BTItems.SKY_MONOLITH_KEY.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.AETHER_KEY_2)))), pos, ChatFormatting.AQUA);
                                }
                            }))
                    ),
                    (serverPlayer -> {
                        TCRQuests.USE_AETHER_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_STORM_EYE.start(serverPlayer);
                        TCRQuests.GET_AETHER_MONOLITH_KEY_2.start(serverPlayer);
                        TCRQuests.GET_AETHER_MONOLITH_KEY_1.start(serverPlayer);
                    })
            )
    );

    public static final RegistryObject<Item> END_RESONANCE_STONE = REGISTRY.register("end_resonance_stone",
            () -> new ResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtils.STRONG_HOLD), ResonanceStoneItem.SURFACE, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_END_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if (TCRCoreMod.isXaeroMapLoaded()) {
                            XaeroWaypointUtils.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", Blocks.END_PORTAL.getName(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.STRONG_HOLD)))), pos, WaypointColor.PURPLE);
                        }
                        if (TCRCoreMod.isJourneyMapLoaded()) {
                            JourneyMapCompat.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", Blocks.END_PORTAL.getName(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtils.STRONG_HOLD)))), pos, ChatFormatting.DARK_PURPLE);
                        }
                        TCRQuests.USE_END_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GO_TO_THE_END.start(serverPlayer);
                    }), false)
    );

    public static final RegistryObject<Item> STONE_OF_REINCARNATION = REGISTRY.register("stone_of_reincarnation", () -> new StoneOfReincarnationItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> MYSTERIOUS_WEAPONS = REGISTRY.register("mysterious_weapons", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), true));

    public static final RegistryObject<Item> NECROMANCY_SCROLL = REGISTRY.register("necromancy_scroll", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), true));

    public static final RegistryObject<Item> WITHER_SOUL_STONE = REGISTRY.register("wither_soul_stone", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), false));

    public static final RegistryObject<Item> WITHER_SOUL_STONE_ACTIVATED = REGISTRY.register("wither_soul_stone_activated", () -> new WitherSoulStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> DIVINE_FRAGMENT = REGISTRY.register("divine_fragment", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), true));

    public static final RegistryObject<Item> MAGIC_BOTTLE = REGISTRY.register("magic_bottle", () -> new BlueBottleItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> THE_INCINERATOR_SOUL = REGISTRY.register("the_incinerator_soul", () -> new The_Incinerator((new Item.Properties()).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    // 血戮莲华
    public static final RegistryObject<Item> BLOOD_LOTUS = REGISTRY.register("blood_lotus", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));

    // 九天玄铁
    public static final RegistryObject<Item> NINE_HEAVEN_DARKSTEEL = REGISTRY.register("nine_heaven_darksteel", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));

    // 地狱 · 紫金武器（炽焰熔岩风格）
    public static final RegistryObject<Item> EMBERFANG = REGISTRY.register("emberfang", () -> new NetherMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 紫金短刃（匕首模板）
    public static final RegistryObject<Item> MAGMAHEART = REGISTRY.register("magmaheart", () -> new NetherMagicAxeItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 紫金斧
    public static final RegistryObject<Item> CINDERWYRM = REGISTRY.register("cinderwyrm", () -> new NetherMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 紫金长刀（太刀模板）
    public static final RegistryObject<Item> PURGING_SWALLOW = REGISTRY.register("purging_swallow", () -> new NetherMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 紫金雁翎（打刀模板）
    public static final RegistryObject<Item> PURGING_SWALLOW_SHEATH = REGISTRY.register("purging_swallow_sheath", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> PURGING_SWALLOW_SHEATH_ALTER = REGISTRY.register("purging_swallow_sheath_alter", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ASHEN_CRESCENT = REGISTRY.register("ashen_crescent", () -> new NetherMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 紫金戟（关刀模板）

    // 天域 · 玉系武器（神圣光曜风格）
    public static final RegistryObject<Item> LUX_JADAE = REGISTRY.register("lux_jadae", () -> new AetherMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 玉曜（原名：白金剑）
    public static final RegistryObject<Item> GLACIS_JADAE = REGISTRY.register("glacis_jadae", () -> new AetherMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 玉霜（原名：白金长剑）
    public static final RegistryObject<Item> MONS_JADAE = REGISTRY.register("mons_jadae", () -> new AetherMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 玉岳（原名：白金大剑）
    public static final RegistryObject<Item> IRIS_JADAE = REGISTRY.register("iris_jadae", () -> new AetherMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC))); // 玉虹（原名：白金枪）

    public static final RegistryObject<Item> MAGIC_DAGGER = REGISTRY.register("magic_dagger",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_AXE = REGISTRY.register("magic_axe",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_TACHI = REGISTRY.register("magic_tachi",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_KATANA = REGISTRY.register("magic_katana",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_KATANA_SHEATH = REGISTRY.register("magic_katana_sheath", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_KATANA_SHEATH_ALTER = REGISTRY.register("magic_katana_sheath_alter", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MAGIC_HALBERD = REGISTRY.register("magic_halberd",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_SWORD = REGISTRY.register("magic_sword",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_LONGSWORD = REGISTRY.register("magic_longsword",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_GREATSWORD = REGISTRY.register("magic_greatsword",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MAGIC_SPEAR = REGISTRY.register("magic_spear",
            () -> new AllMagicSwordItem(Tiers.NETHERITE, 3, -2.4F, new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));

}
