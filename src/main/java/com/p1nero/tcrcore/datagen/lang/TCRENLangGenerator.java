package com.p1nero.tcrcore.datagen.lang;

import com.brass_amber.ba_bt.init.BTEntityType;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.dodo.dodosmobs.init.ModEntities;
import com.hm.efn.registries.EFNItem;
import com.p1nero.p1nero_ec.effect.PECEffects;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.block.TCRBlocks;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.client.TCRKeyMappings;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.FirstEnterCloudlandScreenHandler;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.ResetGameProgressScreenHandler;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.StartScreenHandler;
import com.p1nero.tcrcore.effect.TCREffects;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.worldgen.TCRBiomes;
import com.yesman.epicskills.registry.entry.EpicSkillsItems;
import com.yungnickyoung.minecraft.ribbits.module.EntityTypeModule;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.sonmok14.fromtheshadows.server.utils.registry.ItemRegistry;

public class TCRENLangGenerator extends TCRLangProvider {
    public TCRENLangGenerator(PackOutput output) {
        super(output, "en_us");
    }

    @Override
    protected void addTranslations() {

        this.add("skill_tree.tcrcore.magic", "Magic Skills");
        this.add("unlock_tip.epicskills.acrobat.spider_techniques", " Slaught 50 Spider");
        this.add("unlock_tip.epicskills.acrobat.natural_sprinter", " Slaught 2 Lycanth and sprint 10 km");
        this.add("unlock_tip.epicskills.acrobat.aqua_maneuvre", " Slaught 3 Elder Guadian and swim 3 km");

        this.add("unlock_tip.epicskills.prodigy.shooting_style", " Slaught 2 Enderman");
        this.add("unlock_tip.epicskills.prodigy.punishment_kick", " Slaught 25 Enderman");

        this.add("unlock_tip.epicskills.prodigy.arrow_tenacity", " Slaught 5 skeleton");
        this.add("unlock_tip.epicskills.prodigy.dodge_master", " Slaught 50 skeleton");

        this.add("unlock_tip.epicskills.prodigy.ender_step", " Slaught 50 Enderman");
        this.add("unlock_tip.epicskills.prodigy.ender_obscuris", " Slaught 250 Enderman");
        this.add("unlock_tip.epicskills.prodigy.time_travel", " Slaught 1000 Enderman");

        this.add("unlock_tip.epicskills.prodigy.buster_parade", " Slaught 10 Hollow");
        this.add("unlock_tip.epicskills.prodigy.perfect_bulwark", " Slaught 50 guardian");
        this.add("unlock_tip.epicskills.prodigy.avatar_of_might", " Slaught 50 Hollow");

        this.add("unlock_tip.epicskills.prodigy.precise_roll", " Sprint 4 km");
        this.add("unlock_tip.epicskills.prodigy.bull_charge", " Slaught 20 Ravager");

        this.add("unlock_tip.epicskills.prodigy.shulker_cloak", " Slaught 36 shulker");
        this.add("unlock_tip.epicskills.prodigy.soul_protection", " Slaught 60 Wither Skeleton");
        this.add("unlock_tip.epicskills.prodigy.vampirize", " Slaught 20 Evil Skeleton");
        this.add("unlock_tip.epicskills.prodigy.dopamine", " Slaught 27 Lycanth");
        this.add("unlock_tip.epicskills.prodigy.lunatic_vivacity", " Slaught 9 Ghast");
        this.add("unlock_tip.epicskills.prodigy.voodoo_magic", " Slaught 10 Evoker");
        this.add("unlock_tip.epicskills.prodigy.manipulator", " Slaught 6 Saulomnk");

        this.add("unlock_tip.epicskills.prodigy.all_eyes_on_you", " Slaught 100 Evil Skeleton");
        this.add("unlock_tip.epicskills.prodigy.all_eyes_on_me", " Slaught 100 Saulomnk");

        this.add("unlock_tip.epicskills.prodigy.inner_growth", " Elimate the Ender Dragon");
        this.add("unlock_tip.epicskills.prodigy.shadow_step", " Elimate the Wither");

        this.add("unlock_tip.epicskills.prodigy.critical_knowledge", " Slaught 50 Creeper and 150 mobs");
        this.add("unlock_tip.epicskills.prodigy.dancing_blade", " Slaught 5 Witches and 150 mobs");

        this.add("travelerstitles.tcrcore.sanctum", "Realm of Dreams");
        this.add("travelerstitles.tcrcore.real", "The Unreal Reality");
        this.add("travelerstitles.minecraft.overworld", "Overworld");
        this.add("travelerstitles.aether.the_aether", "The Aether");
        this.add("travelerstitles.minecraft.the_nether", "The Nether");
        this.add("travelerstitles.minecraft.the_nether.color", "750909");
        this.add("travelerstitles.minecraft.the_end", "The End");
        this.add("travelerstitles.minecraft.the_end.color", "4f219e");
        this.add("travelerstitles.pbf1.sanctum_of_the_battle1", "Infinite Samsara");

        this.addBiome(TCRBiomes.AIR, "");
        this.addBiome(TCRBiomes.REAL, "");

        this.addQuest(TCRQuests.WAIT_RESONANCE_STONE_CHARGE, "Interlude", "Wait for [%s] to charge", "[%s]'s energy has been depleted, and recharging will take some time. Before it finishes charging, go do something else!");
        this.addQuest(TCRQuests.PUT_DESERT_EYE_ON_ALTAR, "Interlude", "Place [%s] on the altar", "[%s] has been retrieved. Hurry and return it to the altar in the Sanctum corridor!");
        this.addQuest(TCRQuests.PUT_ABYSS_EYE_ON_ALTAR, "Interlude", "Place [%s] on the altar", "[%s] has been retrieved. Hurry and return it to the altar in the Sanctum corridor!");
        this.addQuest(TCRQuests.PUT_CURSED_EYE_ON_ALTAR, "Interlude", "Place [%s] on the altar", "[%s] has been retrieved. Hurry and return it to the altar in the Sanctum corridor!");
        this.addQuest(TCRQuests.PUT_FLAME_EYE_ON_ALTAR, "Interlude", "Place [%s] on the altar", "[%s] has been retrieved. Hurry and return it to the altar in the Sanctum corridor!");
        this.addQuest(TCRQuests.PUT_MECH_EYE_ON_ALTAR, "Interlude", "Place [%s] on the altar", "[%s] has been retrieved. Hurry and return it to the altar in the Sanctum corridor!");
        this.addQuest(TCRQuests.PUT_STORM_EYE_ON_ALTAR, "Interlude", "Place [%s] on the altar", "[%s] has been retrieved. Hurry and return it to the altar in the Sanctum corridor!");
        this.addQuest(TCRQuests.PUT_VOID_EYE_ON_ALTAR, "Interlude", "Place [%s] on the altar", "[%s] has been retrieved. Hurry and return it to the altar in the Sanctum corridor!");
        this.addQuest(TCRQuests.PUT_MONST_EYE_ON_ALTAR, "Interlude", "Place [%s] on the altar", "[%s] has been retrieved. Hurry and return it to the altar in the Sanctum corridor!");
        this.addQuest(TCRQuests.BLESS_ON_THE_GODNESS_STATUE, "Interlude", "Pray at the goddess statue", "[The Eye of God] has been retrieved. By using the goddess statue in the garden, we can resonate with the Eye of God and gain power from the Angel.");

        this.addQuest(TCRQuests.TALK_TO_AINE_CLOUDLAND, "Interlude", "Talk to %s", "When you touched the altar, you were drawn into a mysterious world. Perhaps we should call it the Cloudland. Go talk to %s; maybe she knows what this place is.");

        // Prologue
        this.addQuest(TCRQuests.TALK_TO_AINE_0, "Prologue", "Talk to %s", "You agreed to come to this world with %s, but when you came to your senses, %s was nowhere to be found. Hurry and look for her in the Sanctum! You vaguely remember her saying she had a new outfit to give you.");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_0, "Prologue", "Talk to ?", "You agreed to come to this world with %s, but when you came to your senses, %s was nowhere to be found. What is this place? Try talking to the dignified woman in the Sanctum corridor!");
        this.addQuest(TCRQuests.TALK_TO_FERRY_GIRL_0, "Prologue", "Go to [%s]", "You’ve heard the story of this world. When you’re ready, head to the dock to find %s! She will open the path to [%s] for us! She also seems to have some treasure to give you.");
        this.addQuest(TCRQuests.TALK_TO_ORNN_0, "Prologue", "Talk to %s", "On %s's suggestion, go to the armory to equip yourself with %s! Proper equipment will ensure a smooth adventure!");

        // Dragon Taming Side Quest
        this.addQuest(TCRQuests.TAME_DRAGON, "Dragon-Taming Chapter", "Raise the dragon to adulthood", "%s gave you a dragon. Now, follow the method described in [%s] to raise it to adulthood! You can feed your dragon any foods!\n\n§a[Quest Reward]: §f[%s] [%s]");
        this.addQuest(TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL, "Dragon-Taming Chapter", "Talk to %s", "With your careful care, the dragon has grown to adulthood. %s mentioned earlier that she would give us a treasure once the dragon is grown. Hurry back to %s to see!\n\n§a[Quest Reward]: §f[%s] [%s]");

        // Main Quest · Desert Eye
        this.addQuest(TCRQuests.USE_LAND_RESONANCE_STONE, "Earth Chapter", "Use [%s]", "You’ve finally arrived at the legendary [%s]. What adventures await here? Hurry and use [%s]! It will guide us to retrieve the first eye.");
        this.addQuest(TCRQuests.GET_DESERT_EYE, "Earth Chapter", "Retrieve [%s]", "[%s] has marked the scattered locations of [%s]. Set out to retrieve [%s]!\n\n§a[Hint]: Explore the tower to find the key to summon the boss! Note that [%s§a] may be hidden within blocks!\n\n§4[Note]: If you cannot complete the quest after obtaining it, try disabling any plugins that might automatically pick up items, then drop and re-pick up the item!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_1, "Earth Chapter", "Talk to %s", "[%s] has been retrieved. Hurry back to the Sanctum to report to %s! She will tell us what to do next.");
        // Chimera Side Quest
        this.addQuest(TCRQuests.BONE_CHIMERA_QUEST, "Weapon Chapter", "Go to [%s]", "[%s] seems to have marked another location. Perhaps there’s an unexpected adventure there. Hurry and check it out!\n\n§a[Quest Reward]: §f[%s§f]");
        this.addQuest(TCRQuests.TALK_TO_ORNN_1, "Weapon Chapter", "Talk to %s", "You obtained [%s] from [%s]. What secrets does it hold? Take it back to the Sanctum and show it to %s!");

        // Main Quest · Abyss Eye
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_2, "Ocean Chapter", "Talk to %s", "After a long wait, %s has finished charging. Hurry back to %s! She’s waiting for you in the Sanctum.");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_OCEAN, "Ocean Chapter", "Go to [%s]", "[%s] has finished charging. It’s time to go to [%s] and use it! It will guide us to the location of [%s]!");
        this.addQuest(TCRQuests.USE_OCEAN_RESONANCE_STONE, "Ocean Chapter", "Use [%s]", "You’ve arrived at [%s]. Hurry and use [%s]! It will guide us to the location of [%s]! What adventures await us?");
        this.addQuest(TCRQuests.GET_OCEAN_EYE, "Ocean Chapter", "Retrieve [%s]", "[%s] has marked the scattered locations of [%s]. Set out to retrieve [%s]!\n\n§a[Hint]: [%s§a] may be hidden within blocks!\n\n§4[Note]: If you cannot complete the quest after obtaining it, try disabling any plugins that might automatically pick up items, then drop and re-pick up the item!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_3, "Ocean Chapter", "Talk to %s", "[%s] has been retrieved. Hurry back to the Sanctum to report to %s! She will tell us what to do next.\n\nThis time, we also retrieved [%s]. What secrets does it hold? Ask her while you’re at it!");

        this.addQuest(TCRQuests.RIBBITS_QUEST, "Ocean Chapter", "Explore [%s]", "[%s] seems to have marked another location. Perhaps there’s an unexpected adventure there. Hurry and check it out!\n\n§a[Quest Reward]: §f[%s] [%s]\n\n§6[Recommended to complete first]");
        this.addQuest(TCRQuests.GIVE_AMETHYST_BLOCK_TO_RIBBITS, "Ocean Chapter", "Give [%s] to %s", "It seems that to learn more about [%s], you’ll need to make a deal with the %s. Gather 12 [%s] and return to them!\n\n§a[Quest Reward]: §f[%s] [%s]\n\n§6[Recommended to complete first]");

        // Main Quest · Cursed Eye
        this.addQuest(TCRQuests.TALK_TO_AINE_ECHO, "Soul Chapter", "Talk to %s", "%s doesn’t know the origin of [%s] either. Go ask %s! Perhaps the archives hold records, and with magic, you can decipher its secrets.");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_4, "Soul Chapter", "Talk to %s", "%s has finished deciphering [%s]. Now, go tell %s the news!");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_CURSED, "Soul Chapter", "Go to [%s]", "You unexpectedly obtained the scattered location of [%s] from [%s]. Hurry to [%s] to retrieve it! [%s] will guide us to its location.");
        this.addQuest(TCRQuests.USE_CURSED_RESONANCE_STONE, "Soul Chapter", "Use [%s]", "You unexpectedly obtained the scattered location of [%s] from [%s]. Hurry to [%s] to retrieve it! [%s] will guide us to its location.");
        this.addQuest(TCRQuests.GET_CURSED_EYE, "Soul Chapter", "Retrieve [%s]", "[%s] has marked the scattered locations of [%s]. Set out to retrieve [%s]!\n\n§a[Hint]: §fExplore to find clues to summon [%s]!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_5, "Soul Chapter", "Talk to %s", "[%s] has been retrieved. Hurry back to the Sanctum to report to %s! She will tell us what to do next.");

        // Iron Magic Side Quest
        this.addQuest(TCRQuests.TALK_TO_AINE_MAGIC, "Magic Chapter", "Talk to %s", "[%s] dropped a mysterious %s. Perhaps %s, who understands magic, can decipher its secrets!");
        this.addQuest(TCRQuests.TRY_TO_LEARN_MAGIC, "Magic Chapter", "Try Spell Infusion", "[%s] has revealed the magic of this world. Now, follow %s's instructions and try infusing the spell from the scroll into your weapon!\n\n§a[Hint]: %s has new dialogue options!");
        this.addQuest(TCRQuests.TALK_TO_AINE_MAGIC_2, "Magic Chapter", "Talk to %s", "You’ve learned how to infuse spells into your weapon. Go talk to %s! She will teach you how to use magic effectively!");

        // Main Quest · Flame Eye
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_6, "Flame Chapter", "Talk to %s", "After a long wait, %s has finished charging. Hurry back to %s! She’s waiting for you in the Sanctum.");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_CORE, "Flame Chapter", "Go to [%s]", "[%s] has finished charging. It’s time to go to [%s] and use it! It will guide us to the location of [%s]!");
        this.addQuest(TCRQuests.USE_CORE_RESONANCE_STONE, "Flame Chapter", "Use [%s]", "You’ve arrived at [%s]. Hurry and use [%s]! It will guide us to the location of [%s]! What adventures await us?");
        this.addQuest(TCRQuests.GET_FLAME_EYE, "Flame Chapter", "Retrieve [%s]", "[%s] has marked the scattered locations of [%s]. Set out to retrieve [%s]!\n\n§a[Hint]: §f[%s] may be hidden in an underground tower. You’ll need the power to break through the obsidian barrier!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_7, "Flame Chapter", "Talk to %s", "[%s] has been retrieved. Hurry back to the Sanctum to report to %s! She will tell us what to do next.");

        this.addQuest(TCRQuests.TALK_TO_AINE_1, "Interlude", "Talk to %s", "You’ve retrieved half of the Eyes of God on your journey. Talk to %s!");

        // Main Quest · Nether Chapter
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_8, "Nether Chapter", "Talk to %s", "After a long wait, %s has finished charging. Hurry back to %s! She’s waiting for you in the Sanctum.");
        this.addQuest(TCRQuests.GO_TO_NETHER, "Nether Chapter", "Go to [%s]", "All the Eyes of God scattered in [%s] have been retrieved. The Resonance Stone may not work in [%s]. As %s said, use [%s] to ignite the obsidian frame and open %s!");
        this.addQuest(TCRQuests.USE_NETHER_RESONANCE_STONE, "Nether Chapter", "Use [%s]", "You’ve arrived at [%s]. Hurry and use [%s]! It will guide us to the location of [%s]! What adventures await us?");
        this.addQuest(TCRQuests.GET_MONST_EYE, "Nether Chapter", "Retrieve [%s]", "[%s] has marked the scattered locations of [%s]. Set out to retrieve [%s]!\n\n§a[Hint]: It is recommended to equip [%s]");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_9, "Nether Chapter", "Talk to %s", "[%s] has been retrieved. Hurry back to the Sanctum to report to %s! She will tell us what to do next.");

        // Main Quest · Destruction Chapter
        this.addQuest(TCRQuests.GET_WITHER_EYE, "Destruction Chapter", "Retrieve [%s]", "Research on the echoes of the Ship Graveyard has progressed! We must perform an ancient ritual to summon the Angel of Death! Hurry and follow %s's instructions to summon [%s] and retrieve [%s]!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_10, "Destruction Chapter", "Talk to %s", "[%s] has been retrieved. Hurry back to the Sanctum to report to %s! She will tell us what to do next.");
        this.addQuest(TCRQuests.TALK_TO_AINE_SAMSARA, "Interlude", "Talk to %s", "%s told us that [%s] is the key to opening [%s]. What secrets does [%s] hold? Hurry and ask %s!");
        this.addQuest(TCRQuests.GO_TO_SAMSARA, "Cycle Chapter", "Go to [%s]", "What secrets lie within [%s]? Hurry and follow %s's instructions to activate [%s]!");

        // Main Quest · Sky Chapter
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_11, "Sky Chapter", "Talk to %s", "After a long wait, %s has finished charging. Hurry back to %s! She’s waiting for you in the Sanctum.");
        this.addQuest(TCRQuests.GO_TO_AETHER, "Sky Chapter", "Go to [%s]", "Water the frame built by %s with the source of life (%s) to open the gates of heaven!");
        this.addQuest(TCRQuests.USE_AETHER_RESONANCE_STONE, "Sky Chapter", "Use [%s]", "On your journey to collect the Eyes of God, only the eyes of the Sky Angel and the Void Angel remain to be retrieved...");
        this.addQuest(TCRQuests.GET_STORM_EYE, "Sky Chapter", "Retrieve [%s]", "[%s] has marked the scattered locations of [%s]. Set out to retrieve [%s]!");
        this.addQuest(TCRQuests.TALK_TO_SKY_GOLEM, "Sky Chapter", "Talk to %s", "%s seems... not entirely dissipated. It appears to have regained consciousness. Try talking to %s!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_12, "Sky Chapter", "Talk to %s", "[%s] has been retrieved, and its story has been understood... Hurry back to the Sanctum to report to %s! She will tell us what to do next.");
        this.addQuest(TCRQuests.TALK_TO_AINE_2, "Sky Chapter", "Talk to %s", "[%s] has been retrieved, and its story has been understood... Talk to %s.");

        // Main Quest · Final Chapter
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_END, "Final Chapter", "Go to [%s]", "%s said that the final Eye of God is located in [%s]. We need to find the [%s] leading to [%s] in [%s] to open the gate to [%s]!");
        this.addQuest(TCRQuests.USE_END_RESONANCE_STONE, "Final Chapter", "Use [%s]", "We need to find the [%s] leading to [%s] in [%s] to open the gate to [%s]! The Resonance Stone will guide us to the location of [%s].");
        this.addQuest(TCRQuests.GO_TO_THE_END, "Final Chapter", "Go to [%s]", "[%s] has guided us to the location of [%s]. Now, find a way to reach [%s]!");
        this.addQuest(TCRQuests.GET_VOID_EYE, "Final Chapter", "Retrieve [%s]", "[%s] is in [%s]. Do your best to retrieve it!");
        this.addQuest(TCRQuests.TALK_TO_ORNN_YAMATO, "Weapon Chapter", "Talk to %s", "You unexpectedly obtained [%s], but its power seems not fully released. Hurry and talk to %s! Perhaps there’s a way to restore its power!");

        this.addQuest(TCRQuests.TALK_TO_CHRONOS_END, "Final Chapter", "Talk to %s", "[%s] has been retrieved... You’ve finally collected all 8 Eyes of God. It’s time to prepare for the re-creation ritual.");
        this.addQuest(TCRQuests.KILL_MAD_CHRONOS, "Final Chapter", "Defeat [???]", "A massive monster has appeared. Do your best to defeat Him!");

        this.addQuest(TCRQuests.TALK_TO_AINE_GAME_CLEAR, "After Story", "Talk to %s", "Have you truly understood [The Casket of Reveries]?");

        this.addEffect(TCREffects.INVULNERABLE, "Invulnerable");
        this.addEffect(TCREffects.SOUL_INCINERATOR, "Soul Fire");
        this.addEffect(PECEffects.SOUL_INCINERATOR, "Soul Fire");

        this.add("epicfight.skill_slot.passive4", "Passive 4");
        this.add("epicfight.skill_slot.passive5", "Passive 5");

        StartScreenHandler.onGenerateEN(this);
        FirstEnterCloudlandScreenHandler.onGenerateEN(this);
        ResetGameProgressScreenHandler.onGenerateEN(this);

        this.add("item.domesticationinnovation.collar_tag.tcr_info", "It allows for special enchantments to be applied to pets.");
        this.add("block.domesticationinnovation.pet_bed_white.tcr_info", "Allows pets to respawn on the pet bed.");
        this.addTCRItemInfo(net.blay09.mods.waystones.item.ModItems.warpStone, "Click the §6[Scroll]§r button in the inventory to teleport to activated waystones!");
        this.addTCRItemInfo(ItemRegistry.BOTTLE_OF_BLOOD.get(), "Brewed using §c[Crystallized Blood Marrow]§r, a drop from §d[Nehemoth]§r");
        this.addTCRItemInfo(EFNItem.DEEPDARK_HEART.get(), "Obtained by defeating the §2[Warden]§r or §2[Captain Cornelia]§r");
        this.addTCRItemInfo(ModItems.CORAL_CHUNK.get(), "Obtained by defeating the §a[Coral Colossus]§r in the §dcloudland of The Leviathan§r");
        this.addTCRItemInfo(com.github.dodo.dodosmobs.init.ModItems.CHIERA_CLAW.get(), "Obtained by defeating the §e[Bone Chimera]§r");
        this.addTCRItemInfo(ModItems.CHITIN_CLAW.get(), "Obtained by defeating the §3[Giant Claw Guard]§r in the §3cloudland of Scylla§r");
        this.addTCRItemInfo(Items.DRAGON_EGG, "Obtained by defeating the §d[Ender Dragon]§r in the §dEnd§r");
        this.addTCRItemInfo(EpicSkillsItems.ABILIITY_STONE.get(), "Right-click to use and gain skill points");

        this.add("itemGroup.tcr.items", "The Casket of Reveries — Core Items");
        this.add("key.categories." + TCRCoreMod.MOD_ID, "The Casket of Reveries — Core");
        this.addKeyMapping(TCRKeyMappings.RIPTIDE, "Riptide");
        this.addKeyMapping(TCRKeyMappings.SHOW_QUESTS, "Show/Hide Task");
        this.addKeyMapping(TCRKeyMappings.EXIT_SPECTATOR, "Exit Spectator Mode");

        this.add("skill_tree.sword_soaring.unlock_tip", "Unlocked by Advancing the main quest");
        this.add("unlock_tip.tcrcore.battleborn.water_avoid1", "Learned by trading with §6[Ribbit]§f using §d[Block of Amethyst]§f");
        this.add("unlock_tip.tcrcore.battleborn.fire_avoid", "Unlocked by Advancing the main quest");
        this.add("unlock_tip.tcrcore.get_vatansever", "Unlocks after obtaining the §d[Vatansever]§f");
        this.addSkill("water_avoid", "Water Avoidance Charm", "Allows breathing underwater!");
        this.addSkill("fire_avoid", "Fire Avoidance Charm", "Immunity to fire damage!");
        this.addSkill("perfect_dodge", "Dodge Effect", "Play a cool animation when perfect dodge!");

        this.add(TCRItems.RESET_SKILL_STONE.get(), "Reset Skill Stone");
        this.addItemUsageInfo(TCRItems.RESET_SKILL_STONE.get(), "After use, it will reset all skills in the skill tree and refund the points, very precious!");
        this.add(TCRItems.PROOF_OF_ADVENTURE_PLUS.get(), "True · Proof of Adventure");
        this.addItemUsageInfo(TCRItems.PROOF_OF_ADVENTURE_PLUS.get(), "The ultimate glory!");
        this.add(TCRItems.DIVINE_FRAGMENT.get(), "Divine Fragment");
        this.addItemUsageInfo(TCRItems.DIVINE_FRAGMENT.get(), "It records the will of the gods.");
        this.add(TCRItems.ABYSS_FRAGMENT.get(), "Echo of the Abyssal Dirge");
        this.add(TCRItems.DESERT_FRAGMENT.get(), "Echo of Forgotten Sands");
        this.add(TCRItems.ENDER_FRAGMENT.get(), "Shard of the Final Verse");
        this.add(TCRItems.MECH_FRAGMENT.get(), "Remains of the Perpetual Gear");
        this.add(TCRItems.NETHERITE_FRAGMENT.get(), "Embers of the Quenched Molten Heart");
        this.add(TCRItems.FLAME_FRAGMENT.get(), "Echo of the First Flame");
        this.add(TCRItems.STORM_FRAGMENT.get(), "Shard of the Thunderous Roar");
        this.add(TCRItems.SOUL_FRAGMENT.get(), "Dust of the Cursed Bone Prison");
        this.add(TCRItems.STONE_OF_REINCARNATION.get(), "Stone of Reincarnation");
        this.addItemUsageInfo(TCRItems.STONE_OF_REINCARNATION.get(), "After use, you will return to the beginning of the journey.");
        this.add(TCRItems.WITHER_SOUL_STONE.get(), "Wither Soul Stone");
        this.addItemUsageInfo(TCRItems.WITHER_SOUL_STONE.get(), "It seems to have lost its magic for now, just a unique stone. Figure out how to reawaken it!");
        this.add(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), "Wither Soul Stone");
        this.addItemUsageInfo(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), "Can open a gateway to the §6[Infinite Samsara]§r.");
        this.add(TCRItems.MAGIC_BOTTLE.get(), "Magic Bottle");
        this.addItemUsageInfo(TCRItems.MAGIC_BOTTLE.get(), "Restores a certain percentage of mana when used. Once depleted, must be used in the Sanctum to replenish MP.");
        this.add(TCRItems.MYSTERIOUS_WEAPONS.get(), "Mysterious Weapons Scroll");
        this.addItemUsageInfo(TCRItems.MYSTERIOUS_WEAPONS.get(), "It seems to record all manner of weapons from around the world. You should show it to someone who knows about such things.");
        this.add(TCRItems.NECROMANCY_SCROLL.get(), "Necromancy Scroll");
        this.addItemUsageInfo(TCRItems.NECROMANCY_SCROLL.get(), "It seems to hold the secrets of magic within. Show it to someone who understands it.");
        this.add(TCRItems.DRAGON_FLUTE.get(), "Dragon Flute");
        this.addItemUsageInfo(TCRItems.DRAGON_FLUTE.get(), "Right-click to capture a dragon; right-click again to release it.");
        this.add(TCRItems.RESONANCE_STONE.get(), "Resonance Stone");
        this.add(TCRItems.LAND_RESONANCE_STONE.get(), "Land Resonance Stone");
        this.add(TCRItems.OCEAN_RESONANCE_STONE.get(), "Ocean Resonance Stone");
        this.add(TCRItems.CURSED_RESONANCE_STONE.get(), "Cursed Resonance Stone");
        this.add(TCRItems.CORE_RESONANCE_STONE.get(), "Core Resonance Stone");
        this.add(TCRItems.NETHER_RESONANCE_STONE.get(), "Nether Resonance Stone");
        this.add(TCRItems.SKY_RESONANCE_STONE.get(), "Aether Resonance Stone");
        this.add(TCRItems.END_RESONANCE_STONE.get(), "End Resonance Stone");
        this.add(TCRItems.CORE_FLINT.get(), "Core Flint");
        this.addItemUsageInfo(TCRItems.CORE_FLINT.get(), "Use on an Obsidian Frame to open a Nether Portal.");
        this.add(TCRItems.PROOF_OF_ADVENTURE.get(), "Proof of Adventure");
        this.addItemUsageInfo(TCRItems.PROOF_OF_ADVENTURE.get(), "Forged from the names of all foes vanquished by your blade. Your journey has reached its end, and your courage is now legend.");
        this.add(TCRItems.DUAL_BOKKEN.get(), "Bokeen");
        this.addItemUsageInfo(TCRItems.DUAL_BOKKEN.get(), "I may have skill issue but I'm not lacking on dedication so do you lacking in dedication?");
        this.add(TCRItems.VOID_CORE.get(), "Void Essence");
        this.addItemUsageInfo(TCRItems.VOID_CORE.get(), "Dropped by the [Ender Guardian] when defeated");
        this.add(TCRItems.ABYSS_CORE.get(), "Abyss Essence");
        this.addItemUsageInfo(TCRItems.ABYSS_CORE.get(), "Dropped by [The Leviathan] when defeated");
        this.add(TCRItems.ARTIFACT_TICKET.get(), "Artifact Essence");
        this.addItemUsageInfo(TCRItems.ARTIFACT_TICKET.get(), "Obtained from certain quests in the quest book. Can be used to refine artifact with the §3[Ferry girl]§r at the §d[Saint Harbor]§r");
        this.add(TCRItems.RARE_ARTIFACT_TICKET.get(), "Golden Artifact Essence");
        this.addItemUsageInfo(TCRItems.RARE_ARTIFACT_TICKET.get(), "Obtained from certain quests in the quest book. Can be used to refine rare artifact with the §3[Ferry girl]§r at the §d[Saint Harbor]§r");
        this.add(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "Oracle Fragment");
        this.addItemUsageInfo(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "§c§kI'm Your...");

        this.addInfo("talk_to_start", "Talk to start fighting");
        this.addInfo("temp_dragon_flute", "[Temporary Dragon Flute]: It will be consumed after use!");
        this.addInfo("dragon_die_back", "Your dragon has died, no pet bed detected, and has beed saved to a new dragon flute");
        this.addInfo("only_work_on_dragon", "Only work on Dragon!");
        this.addInfo("creative_may_lost_progress", "Warning: Defeating the boss in Creative Mode will not progress the game!");
        this.addInfo("exit_spectator_in_pbf1", "Press [%s] to exit Spectator Mode and return to the Sanctum.");
        this.addInfo("cataclysm_humanoid_drop_desc", "Dropped at [%s] by defeating [%s]");
        this.addInfo("can_not_use_scroll_directly", "Please enchant the spell to a weapon to use it!!");
        this.addInfo("quest_updated","Current Quest Updated!");
        this.addInfo("i18n_pack", "I18n pack, thanks to all translators!");
        this.addInfo("can_not_dodge", "Boss attacks cannot be dodged!");
        this.addInfo("can_not_guard", "Boss attacks cannot be blocked!");
        this.addInfo("pec_weapon_lock", "Weapon skill locked! Defeat [%s] in [%s] to unlock!");
        this.addInfo("resonance_stone_usage", "Can resonate with the location of the Angel's seal");
        this.addInfo("resonance_search_failed", "[ERROR]：Failed to Resonate! Please retry after restarting the game! Please keep the latest.log and report to the author! [%s]");
        this.addInfo("yamato_skill_lock", "[%s] are locked. Requires special enchantment book to unlock!");
        this.addInfo("congratulation", "Congratulations!");
        this.addInfo("open_backpack_tutorial", "Press [%s] to view Backpack");
        this.addInfo("unlock_new_ftb_page_title", "§6New Chapter Unlocked!");
        this.addInfo("unlock_new_ftb_page_subtitle", "§aPress [%s§a] to view");
        this.addInfo("resonance_stone_working", "[%s] Resonating... Please wait patiently...");
        this.addInfo("containing_dragon", "Type: [%s]");
        this.addInfo("dragon_owner", "Owner: [%s]");
        this.addInfo("quest_map_mark", "Quest Pos");
        this.addInfo("map_pos_marked_press_to_open", "Labeled location, press [%s] to view the map.");
        this.addInfo("press_to_open_skill_tree", "Press %s to open Skill Tree");
        this.addInfo("press_to_show_quest_ui", "Press %s to view quests");
        this.addInfo("please_use_custom_flint_and_steel", "Please use [%s] to spawn portal");
        this.addInfo("exit_quest_screen", "Exit");
        this.addInfo("start_tracking_quest", "Select");
        this.addInfo("cancel_tracking_quest", "Stop");
        this.addInfo("no_quest", "No Quest");
        this.addInfo("tracking_quest", " [☆Tracking]");
        this.addInfo("require_item_to_wake", "Require [%s]...");
        this.addInfo("weapon_no_interact", "Cannot interact! Please press [%s] vanilla mode or other item.");
        this.addInfo("tudigong_gift", "[Gift]");
        this.addInfo("tudigong_gift_get", "§6[TuDi]§f: I have grown old and incapable, so Ill pass this treasure to you！");
        this.addInfo("need_to_unlock_waystone", "Some waystones remain inactive!");
        this.addInfo("nether_unlock", "Nether Unlocked!");
        this.addInfo("end_unlock", "End Unlocked!");
        this.addInfo("nothing_happen_after_bless", "§dNothing happened... The [Eye] has been used.");
        this.addInfo("dim_max_4_players", "§6Cloudland can only contain 4 players!");
        this.addInfo("dim_max_players", "§6Maximum capacity reached");
        this.addInfo("can_not_enter_before_finish", "§6You are not destined to enter this cloudland.");
        this.addInfo("can_not_do_this_too_early", "§6You are not destined to do this.");
        this.addInfo("captain_start_heal", "§cCornelia started healing! Increase your damage!");
        this.addInfo("illegal_item_tip", "§cIllegal Item!");
        this.addInfo("illegal_item_tip2", "§6Currently, you are not destined to use this item.");

        this.addInfo("shift_to_pic", "Attack when pressing Shift to pick-up");
        this.addInfo("no_place_to_ship", "No space for ship!");
        this.addInfo("boss_killed_ready_return", "§6Boss has been defeated! Block interaction unlocked!");
        this.addInfo("click_to_return", "§a[Click to return]");
        this.addInfo("cs_warning", "§c§l WARNING！Compute Shader is inactive now! You could enable it in Epic Fight config to get a better experience!");
        this.addInfo("wraithon_start_tip", "§d[Wraithon]: §6Outsider, your journey ends here!");
        this.addInfo("wraithon_end_tip", "§d[Wraithon]: §6This... is impossible...");
        this.addInfo("dim_block_no_interact", "§cBoss has not been defeated! Cannot interact with the cloudland blocks yet!");
        this.addInfo("altar_dim_info", "Cloudland Info:");
        this.addInfo("related_loot", "Monster: [%s] | Related Loot: [%s]");
        this.add(TCRBlocks.CURSED_ALTAR_BLOCK.get(), "Cursed Altar");
        this.add(TCRBlocks.ABYSS_ALTAR_BLOCK.get(), "Abyss Altar");
        this.add(TCRBlocks.STORM_ALTAR_BLOCK.get(), "Storm Altar");
        this.add(TCRBlocks.FLAME_ALTAR_BLOCK.get(), "Flame Altar");
        this.add(TCRBlocks.DESERT_ALTAR_BLOCK.get(), "Desert Altar");
        this.add(TCRBlocks.MONST_ALTAR_BLOCK.get(), "Monst Altar");
        this.add(TCRBlocks.VOID_ALTAR_BLOCK.get(), "Void Altar");
        this.add(TCRBlocks.MECH_ALTAR_BLOCK.get(), "Mech Altar");

        this.addInfo("attack_to_restart", "§cAttack to restart");
        this.addInfo("after_heal_stop_attack", "§6Stop attack to clear anggression.");
        this.addInfo("cloud_follow_me", "§6[Magic Cloud]: §fHi, follow me!");
        this.addInfo("dim_demending", "§6Rebuilding... Wait[%d§6]s");
        this.addInfo("to_be_continue2", "[P1nero]: §6Thank you for playing! More bosses are in the making, stay tuned for more!");
        this.addInfo("second_after_boss_die_left", "Returning to the Overworld in %d seconds");
        this.addInfo("press_to_open_battle_mode", "§cPress [%s] to enable Battle Mode!§r");
        this.addInfo("unlock_new_dim_girl", "§6New options unlocked at the Ferry girl!§r");
        this.addInfo("unlock_new_dim", "§c[Nether]§d[End]§6 unlocked!§r");
        this.addInfo("iron_golem_name", "Sky Island Guardian");

        this.addInfo("get_mimic_invite", "[%s]: Otherworldly one, I knew I was right about you! Take this §6[%s§6]§f!");
        this.addInfo("kill_arterius", "[%s]: Otherworlder, you are indeed formidable! It seems the prophecy is true! Then, I shall bestow these pieces of [%s] upon you!");

        this.addInfo("finish_all_eye", "§dAll altars are lit!§r");
        this.addInfo("time_to_altar", "The scattered embers have been found. It's time to return and light-up the altars...");
        this.addInfo("time_to_ask_godness_statue", "§d*This item can be used at the statue of the Goddess.");
        this.addInfo("time_to_end", "All altars are lit. It's time to find the The Sanctuary Keeper to perform the ritual...");

        this.addInfo("reset_when_no_player", "If no players remain in the Cloudland, leaving for too long will reset the Cloudland!");
        this.addInfo("on_full_set", "Full Set Effect");
        this.addInfo("unlock_new_ftb_page", "A new quest page has been unlocked. Please open the §6[Quest Book]§r to check");
        this.addInfo("unlock_new_skill_page", "A new skill book interface has been unlocked!");
        this.addInfo("unlock_new_skill", "Unlocked [%s]!");
        this.addInfo("unlock_new_skill_sub", "Press §6[%s]§r to check new skill");
        this.addInfo("hit_barrier", "This area is not available yet. Come back later!");

        this.addInfo("death_info", "§6When enemies are too powerful, try combining different skills!");
        this.addInfo("enter_dimension_tip", "Right-click the altar core to enter the Cloudland");
        this.addInfo("use_true_eye_tip", "Please use [%s] to right-click the altar core");

        this.addInfo("add_item_tip", "New item obtained: %s × %d!");
        this.addInfo("skill_point_lack", "This skill requires %d skill points to unlock");
        this.addInfo("press_to_open_portal_screen2", "Click the §6[Scroll]§r in the inventory to return to previously activated stones!");
        this.addInfo("press_to_show_progress", "Press §6[L]§f to view guidance!");
        this.addInfo("press_to_skill_tree", "Sufficient EXP available. Press §6[K]§f to allocate skill points!");
        this.addInfo("lock_tutorial", "§6[[%s§6]§r to lock on");
        this.addInfo("lock_tutorial_sub", "§cMove the mouse to switch targets!");
        this.addInfo("riptide_tutorial", "§6[[%s§6]§f to §bRiptide");
        this.addInfo("dodge_tutorial", "§6[[%s§6]§f to dodge");
        this.addInfo("weapon_innate_tutorial", "§6[[%s§6]§f to use your weapon's skill");
        this.addInfo("weapon_innate_charge_tutorial", "§6[Perfect Dodge]§c or §6[Perfect Parry]§c can charge certain weapons!");
        this.addInfo("perfect_dodge_tutorial", "§cDodge in time to Perfect Dodge!");
        this.addInfo("hurt_damage", "Dealt [ %s ] damage!");
        this.addInfo("parry_tutorial", "§6[[%s§6]§f to guard");
        this.addInfo("perfect_parry_tutorial", "§cBlock in time to Perfect Parry!");
        this.addInfo("you_pass", "§6You passed!!");

        this.addInfo("press_to_open_map", "§6[M]§f to view the map");

        this.addInfo("godness_statue_pos", "Goddess Statue");
        this.addInfo("eye_pos_mark", "Location of [%s]: [%s]");

        this.addAdvancement(TCRCoreMod.MOD_ID, "The Casket of Reveries", "Where the dream begins.");
        this.addAdvancement("unlock_weapon_armor_book", "Mysterious Weapons", "");
        this.addAdvancement("unlock_magic_and_boss", "Necromancy Scroll", "");
        this.addAdvancement("unlock_epic_boss", "Primal Arena", "");

        this.add(TCREntities.CHRONOS_SOL.get(), "Chronos Sol");
        this.add(TCREntities.FERRY_GIRL.get(), "Ferry girl");
        this.add(TCREntities.ORNN.get(), "Old Ornn");
        this.add(TCREntities.AINE.get(), "Aine");
        this.add(TCREntities.TUTORIAL_GOLEM.get(), "Training Golem");
        this.add(TCREntities.TCR_MIMIC.get(), "Me?");

        this.add(TCRBossEntities.LEVIATHAN_HUMANOID.get(), "Thalassa Mare");
        this.add(TCRBossEntities.HARBINGER_HUMANOID.get(), "Letum Quietus");
        this.add(TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get(), "Nihil Vacuum");
        this.add(TCRBossEntities.IGNIS_HUMANOID.get(), "Ignis Ardens");
        this.add(TCRBossEntities.IGNIS_SHIELD.get(), "Ignis Ardens Shield");
        this.add(TCRBossEntities.SCYLLA_HUMANOID.get(), "Caelum Altum");
        this.add(TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get(), "Terra Montis");
        this.add(TCRBossEntities.MALEDICTUS_HUMANOID.get(), "Anima Essentia");
        this.add(TCRBossEntities.NETHERITE_HUMANOID.get(), "Infernus Abyssus");


        this.addDialogAnswer(EntityType.IRON_GOLEM, 0, "Man, are you ready？");
        this.addDialogOption(EntityType.IRON_GOLEM, 0, "Yes");
        this.addDialogOption(EntityType.IRON_GOLEM, 1, "Wait");
        this.addDialogAnswer(EntityType.VILLAGER, -2, "Mambo?");
        this.addDialogAnswer(EntityType.VILLAGER, -1, "!!!");
        this.addDialogAnswer(EntityType.VILLAGER, 0, "Mambo, mambo, oh my gilly, mambo~");
        this.addDialogAnswer(EntityType.VILLAGER, 1, "Zabu zabu~");
        this.addDialogAnswer(EntityType.VILLAGER, 2, "Wa i sha~ Mambo~");
        this.addDialogAnswer(EntityType.VILLAGER, 3, "Nanbei ludou~ Axi ga axi~");
        this.addDialogAnswer(EntityType.VILLAGER, 4, "Hakimi nanbei ludou~ Axi ga axi~");
        this.addDialogAnswer(EntityType.VILLAGER, 5, "Ding dong ji~ Ding dong ji~");
        this.addDialogAnswer(EntityType.VILLAGER, 6, "You da you da~");
        this.addDialogAnswer(EntityType.VILLAGER, 7, "Axi ga hayaku naru~ wow~");
        this.addDialogOption(EntityType.VILLAGER, -3, "[Try Emeralds?]");
        this.addDialogOption(EntityType.VILLAGER, -2, "[This villager shows no interest...]");
        this.addDialogOption(EntityType.VILLAGER, -1, "[Accept]");
        this.addDialogOption(EntityType.VILLAGER, 0, "[???]");
        this.addDialogOption(EntityType.VILLAGER, 1, "[It seems the local residents are heavily corrupted!]");
        this.addDialogOption(EntityType.VILLAGER, 2, "[What are you mumbling about?]");
        this.addDialogOption(EntityType.VILLAGER, 3, "[Why can't I understand the villagers' language...]");

        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -4, "§aAccept [%s§a]");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -3, "Return");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -2, "End Conversation");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -1, "Continue");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 0, "Traveler, is your adventure going well?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 0, "About this world");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 1, "A thousand years ago, 'our' world was struck by an unknown calamity from beyond the heavens, and the Black Tide descended upon the realm. Facing the endless Black Tide, 'we' were powerless. Affected by the Black Tide, 'our' memories gradually faded... One by one, They were devoured by the Black Tide, and their souls were sealed away across the world. When 'we' realized that 'our' memories were receding like the tide, 'we' used magic to inscribe the Dead Sea Scrolls, to remind us that a savior would eventually return from beyond the heavens, unite 'our' power, achieve recreation, and vanquish the Black Tide!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 2, "I was the least eroded by the Black Tide. They used their remaining strength to seal me and this Sanctum beneath us into the Primordial Sea, to slow the Black Tide's erosion and await the savior's return. As for who the savior is, or why they possess the power to save us, 'we' have no way of knowing. But in the face of the Black Tide, 'we' can only believe in this ancient memory and believe in the arrival of recreation...");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 1, "About %s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 3, "Traveler, are you referring to the witch who has settled here? She seems to possess power from beyond this world, much like yourself. I believe in the prophecy; I believe that you and the witch can save the Sanctum. Everything here will be open to you both!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 2, "About %s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 4, "Since the Black Tide invasion, she has stood guard at the harbor for a thousand years, the only link between this place and the world. She is a puppet 'we' crafted. As 'our' memories faded, so too did her power dwindle.");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 3, "About %s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 5, "He is a demigod from the Overworld, master of forging and craftsmanship. All the divine weapons and armor used by the Angel were forged by his hand.");

        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 6, "Oh, prophesied savior, I have awaited your arrival! Only you can retrieve the Eyes of the Gods and restore the world's former glory!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 4, "Who are you?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 7, "I am one of the world's ten great Angel, named %s, and I govern time.");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 5, "Angel?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 8, "At the dawn of creation, ten Angel were born in the Sanctum, governing all things in the world. I am one of them. 'We' often gathered to discuss important matters. When a situation required 'our' intervention, 'we' would choose one among 'us' to go.");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 6, "§aAbout the Next Step");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 9, "Traveler, you must retrieve their anchors—the Eyes of the Gods—and return the souls of the Angel to the altars. Then, I shall offer my own soul, and we can achieve the recreation recorded in the Dead Sea Scrolls!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 10, "The Primordial Sea shields us from the outside world's aura. I cannot sense their locations from here. However, this §6Resonance Stone§f can help you find where the Eyes of the Gods are scattered in the outer world. I give this Resonance Stone to you. Once you reach the Overworld, it will guide you to the Angel' locations. Remember, the Resonance Stone can only be used once per stage! After you retrieve an Eye of God, I can use it to forge a new Resonance Stone.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 11, "Traveler, you might first go to the §6[Armory]§f to get a suitable weapon from %s. Once you're ready, head to the harbor to find %s. She will take you to the starting point of your journey.");

        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 12, "Traveler, has your journey to find [%s] been successful?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 7, "§aI have retrieved [%s§a]");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 13, "Truly, you are the prophesied savior! Please, place the Eye of God upon the altar!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 14, "You can also pray at the goddess statue with the Eye of God. A portion of the Angel's power contained within the Eye will be shared with you through the statue.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 15, "The Resonance Stone still needs time to recharge. Please wait, traveler!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 16, "The Resonance Stone has finished recharging. Set forth, traveler!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 17, "Well done, traveler! Allow me to recharge the Resonance Stone. Please rest awhile!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 8, "§aAbout [%s§a]");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 18, "[%s]? I cannot find any related memories. Please consult the witch and search the archives with her power!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 19, "Traveler, the Resonance Stone has not yet finished recharging. Please be patient.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 20, "This is... %s?!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 21, "In that case, this memory can restore the Resonance Stone's magic prematurely. I will infuse this echo into the Resonance Stone. You must retrieve its spark!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 22, "Please place the Eye of God upon the altar! The Angel governing the underworld has, in the end, stepped into the underworld themselves...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 23, "Please place the Eye of God upon the altar! You have buried the code of resurrection in the ashes, melting the rusted lock into a flowing golden river…");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 24, "Traveler, all the Eyes of God scattered in the Overworld have been retrieved. The Resonance Stone may no longer function there. If it doesn't respond, perhaps it's time to try other dimensions.");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 9, "Other dimensions?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 25, "At the dawn of creation, the world was divided into four dimensions: the Overworld, the Nether, the Aether, and the End. Each was governed by its respective Angel.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 26, "After the Black Tide invasion, their souls remained there forever. I used the power of [%s] to forge [%s] for you. Use [%s] to ignite the obsidian frame and open the gate to the Nether!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 27, "Traveler, when you are ready, head to the Nether and use the Resonance Stone! Before you go, accept my blessing to protect you from the flames!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 28, "Traveler, you've arrived just in time! The witch has made progress researching the echoes of the Ship Graveyard! We must perform the ancient ritual to summon the Death Angel back!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 10, "Death Angel?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 29, "The Angel governing death—%s. According to information left by the Soul Angel in the Ship Graveyard, their soul is imprisoned in the River Styx!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 30, "We must perform the summoning ritual to rescue them from the Styx!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 11, "Summoning ritual?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 31, "Traveler, did you see [%s] in the Nether? Arrange [%s] in a T-shape, then place three [%s] on top to summon them!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 32, "[%s]? This is the essence condensed from the Death Angel's power. It can open the gate to [%s]!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 33, "But I lack the power to open [%s] for you. Perhaps you will need the witch's help to activate its magic and open the gate to Samsara.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 34, "While the Resonance Stone is recharging, go find %s and examine [%s]!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 35, "Cough, traveler, next... cough...");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 12, "%s, what's wrong?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 13, "You look terrible");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 36, "Cough, ever since the Eyes returned to the altars, I feel my power waning... Perhaps the Black Tide's curse is finally reaching me...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 37, "On your journey to return the Eyes, only those of the Sky Angel and the Void Angel remain unreturned... With the return of the Death Angel's Eye, the gates of Heaven have opened.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 38, "Legend says that after death, the virtuous ascend to Heaven, the wicked descend to the Nether, and those neither good nor evil fade into the Void. The Death Angel and the Soul Angel govern the Styx, arranging the destinations of the departed.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 39, "After the Black Tide, the balance of yin and yang was broken. Endless souls pour into the Overworld, unable to enter the cycle of reincarnation.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 40, "Set forth, before my power fades completely. §6Water the frame built of glowstone with the source of life to open the gates of Heaven! §fOnce you enter the Aether, use the Resonance Stone to find the location of [%s].");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 41, "The Aether is perilous. I bestow upon you the blessing of §6[Sword Soaring]§f!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 42, "Cough, traveler, you've come. Cough... Was your journey to find [%s] successful?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 14, "%s,");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 15, "I know who I am");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 43, "...(You tell %s about what happened in %s)");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 44, "So that's it... So the savior in the Dead Sea Scrolls is the Pure Angel... Only purity can sweep away all darkness...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 45, "No wonder I always felt you shared our essence, traveler, and could traverse the three realms.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 46, "Cough, now only the final step remains... to begin the ritual... The Void Angel's Eye is in the End...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 47, "I lack the power to recharge the Resonance Stone. Its remaining energy is only enough to guide you to [%s]... For the rest of the journey, please take care, traveler!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 48, "......");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 16, "%s?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 17, "%s!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 49, "Purus Absolutus.. Purus Absolutus...!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 50, "");

        this.addDialogOption(TCREntities.AINE.get(), -4, "§6Spell Infusion");
        this.addDialogOption(TCREntities.AINE.get(), -3, "§6Spell Trading");
        this.addDialogOption(TCREntities.AINE.get(), -2, "End Conversation");
        this.addDialogOption(TCREntities.AINE.get(), -1, "Continue");
        this.addDialogAnswer(TCREntities.AINE.get(), -2, "Which school would you like to learn?");
        this.addDialogAnswer(TCREntities.AINE.get(), -1, "%s, you're here! What would you like to do today?");
        this.addDialogAnswer(TCREntities.AINE.get(), 0, "%s, you're here! I'm reading the archives of this world");
        this.addDialogOption(TCREntities.AINE.get(), 0, "About %s");
        this.addDialogAnswer(TCREntities.AINE.get(), 1, "You've already spoken with that woman? She seems to be the leader here. Looks like to solve the problems here, we'll have to follow her instructions for now.");
        this.addDialogOption(TCREntities.AINE.get(), 1, "About this world");
        this.addDialogAnswer(TCREntities.AINE.get(), 2, "You've heard that woman's story, right? But whether it's true or false, we'll only know once we truly start our journey. Remember what we agreed on before coming here: you have to rely on your own strength to recover your lost memories! Keep it up, I'll always be by your side, your strong support!");
        this.addDialogOption(TCREntities.AINE.get(), 2, "§aAbout the Outfit");
        this.addDialogAnswer(TCREntities.AINE.get(), 3, "Look! My new outfit! Pretty, right? Coming to this world, we should get a new set of clothes too! Here, you should change into yours as well!");
        this.addDialogOption(TCREntities.AINE.get(), 3, "§aAccept");
        this.addDialogAnswer(TCREntities.AINE.get(), 4, "%s, need something?");
        this.addDialogOption(TCREntities.AINE.get(), 5, "§aAbout the Cloudland");
        this.addDialogAnswer(TCREntities.AINE.get(), 5, "Cloudland? Let me check...");
        this.addDialogAnswer(TCREntities.AINE.get(), 6, "Hmm... Based on the information in the archives and my experience, the Cloudland you entered after touching the altar is likely the merged result of the Angel' original mental worlds, eroded by the Black Tide.");
        this.addDialogOption(TCREntities.AINE.get(), 6, "Mental worlds?");
        this.addDialogAnswer(TCREntities.AINE.get(), 7, "Yeah, maybe deep within the Cloudland, you can find reflections of the Angel and gain their power!");
        this.addDialogAnswer(TCREntities.AINE.get(), 8, "Try exploring the Cloudland! You might find something significant, maybe even valuable memory essence materials!");
        this.addDialogOption(TCREntities.AINE.get(), 7, "§aAbout [%s§a]");
        this.addDialogAnswer(TCREntities.AINE.get(), 9, "This is... special memory essence. It seems to record the memories of a group, not just a single life. Let's see what secrets it holds!");
        this.addDialogAnswer(TCREntities.AINE.get(), 10, "!!");
        this.addDialogAnswer(TCREntities.AINE.get(), 11, "It records the adventure story of Captain Cornelia. And from the memory, after the Soul Angel %s was eroded by the Black Tide, they possessed Captain Cornelia! Hurry and show this deciphered memory to %s!");
        this.addDialogOption(TCREntities.AINE.get(), 8, "%s? %s?");
        this.addDialogAnswer(TCREntities.AINE.get(), 12, "The Soul Angel, %s, governs the world of the dead. Captain Cornelia's fleet was a world-renowned ocean-going fleet. Cursed by the Black Tide, they were trapped forever in that icy labyrinth along with [%s]!");
        this.addDialogAnswer(TCREntities.AINE.get(), 13, "[%s]? Let me see, another special memory...");
        this.addDialogAnswer(TCREntities.AINE.get(), 14, "Heh heh, it seems the Soul Angel, who governs the underworld, also mastered the magic of the living!");
        this.addDialogAnswer(TCREntities.AINE.get(), 15, "[%s] gives us two pieces of information. §6First, it depicts most of the monsters in the world eroded by the Black Tide!");
        this.addDialogAnswer(TCREntities.AINE.get(), 16, "§6Second, it records the magic of this world! Heh, the magic here is quite similar to the magic I know!");
        this.addDialogAnswer(TCREntities.AINE.get(), 17, "The scroll depicts spell essences and their corresponding spells. §6Bring me the spell essence described in the compendium, and I will convert it into a corresponding spell scroll for you.");
        this.addDialogAnswer(TCREntities.AINE.get(), 18, "§6Give me your weapon, and I will enchant it with the spell recorded on the scroll! §fI happen to have a scroll here. Go try it out!");
        this.addDialogAnswer(TCREntities.AINE.get(), 19, "%s, looks like you've learned how to enchant weapons! Now, let me teach you how to cast spells! §6Watch closely, I'll only teach this once!");
        this.addDialogAnswer(TCREntities.AINE.get(), 20, "When holding an enchanted weapon, press [%s] to cast the corresponding spell. See? Simple, right?");
        this.addDialogAnswer(TCREntities.AINE.get(), 21, "Casting spells consumes mana. When mana is depleted, you cannot cast spells. §6Note, mana can only be recovered in the Sanctum! Once you leave the Sanctum, I cannot infuse you with magic. §fHowever, there's one more thing...");
        this.addDialogAnswer(TCREntities.AINE.get(), 22, "One more thing! In case you can't recover mana outside the Sanctum, I've specially prepared [%s] for you! With [%s], you can replenish mana anytime, anywhere!");
        this.addDialogOption(TCREntities.AINE.get(), 9, "§6I've learned");
        this.addDialogOption(TCREntities.AINE.get(), 10, "§aSay it again");
        this.addDialogAnswer(TCREntities.AINE.get(), 23, "However, [%s] has limited uses! When it's used up, just use it in the Sanctum, and it will automatically refill! Embark on the path of magic, Sa—vi—or!");
        this.addDialogAnswer(TCREntities.AINE.get(), 24, "%s, is your adventure going well recently?");
        this.addDialogOption(TCREntities.AINE.get(), 11, "Easy peasy!");
        this.addDialogOption(TCREntities.AINE.get(), 12, "I'm exhausted!");
        this.addDialogAnswer(TCREntities.AINE.get(), 25, "I'm worn out from researching materials here too. If it weren't agreed that you'd do it yourself, I'd love to go on an adventure with you.");
        this.addDialogOption(TCREntities.AINE.get(), 13, "%s...");
        this.addDialogAnswer(TCREntities.AINE.get(), 26, "Hmm?");
        this.addDialogOption(TCREntities.AINE.get(), 14, "Something feels off");
        this.addDialogAnswer(TCREntities.AINE.get(), 27, "What's wrong?");
        this.addDialogOption(TCREntities.AINE.get(), 15, "They all seem to know me");
        this.addDialogAnswer(TCREntities.AINE.get(), 28, "Nonsense, how could that be? I think you've just been adventuring too hard lately and are seeing things. You should rest more!");
        this.addDialogOption(TCREntities.AINE.get(), 16, "I'm serious");
        this.addDialogOption(TCREntities.AINE.get(), 17, "%s?");
        this.addDialogAnswer(TCREntities.AINE.get(), 29, "Alright, alright, once we return all the Eyes of God, all questions will surely be answered!");
        this.addDialogAnswer(TCREntities.AINE.get(), 30, "Hmm... The archives say that infusing [%s] can activate it and open a portal to [%s]!");
        this.addDialogAnswer(TCREntities.AINE.get(), 31, "§6[%s§6] is a realm between the underworld and the Overworld. Inside [%s§6], we can use summoning altars to meet the Angel of the past.");
        this.addDialogAnswer(TCREntities.AINE.get(), 32, "Maybe we can go in and take a look. Perhaps we can gain their power from the memories!");
        this.addDialogAnswer(TCREntities.AINE.get(), 33, "%s, you seem troubled. What happened in %s?");
        this.addDialogOption(TCREntities.AINE.get(), 18, "%s, actually I...");
        this.addDialogAnswer(TCREntities.AINE.get(), 34, "...(You tell %s about what happened in %s)");
        this.addDialogAnswer(TCREntities.AINE.get(), 35, "So that's why you said before that they all seem to know you...");
        this.addDialogAnswer(TCREntities.AINE.get(), 36, "Before you know it, only [%s] remains to be returned... Keep it up!");
        this.addDialogAnswer(TCREntities.AINE.get(), 37, "%s, you're here! Or should I call you... what did they call you? Purus!");
        this.addDialogAnswer(TCREntities.AINE.get(), 38, "Treatment is over. If you have any questions, feel free to ask! I'll answer them all!");
        this.addDialogOption(TCREntities.AINE.get(), 19, "About you");
        this.addDialogOption(TCREntities.AINE.get(), 20, "About the Angels");
        this.addDialogOption(TCREntities.AINE.get(), 21, "About the truth of the Black Tide");
        this.addDialogOption(TCREntities.AINE.get(), 22, "About the future");
        this.addDialogOption(TCREntities.AINE.get(), 23, "§aI have no more questions");
        this.addDialogAnswer(TCREntities.AINE.get(), 39, "I am your attending physician, responsible for treating your schizophrenia. As for why I look like this, it's because we're in your mental world. Everything here is as you imagine it.");
        this.addDialogAnswer(TCREntities.AINE.get(), 40, "No need to worry. You've already killed them with your own hands. They are dead forever in your dreams. They won't come back.");
        this.addDialogAnswer(TCREntities.AINE.get(), 41, "The Black Tide is actually the effect of the medication Risperdal. We use medication to control the activity of your various personalities. And you, as the kindest, purest personality, we chose you as our breakthrough point. The other personalities were too troublesome, so we could only administer the medication to suppress them when you were in control of the body.");
        this.addDialogAnswer(TCREntities.AINE.get(), 42, "In your mental world, the medication manifests as the 'Black Tide.' It helps us restrain the other personalities. However, in your world, only you can truly affect them. So we fabricated the lie of recreation to guide you, step by step, to eliminate them.");
        this.addDialogAnswer(TCREntities.AINE.get(), 43, "From now on, you can live like a normal person! Of course, whenever you want to return here, just tell me!");
        this.addDialogAnswer(TCREntities.AINE.get(), 44, "So, let's go out together!");
        this.addDialogOption(TCREntities.AINE.get(), 24, "Go where?");
        this.addDialogAnswer(TCREntities.AINE.get(), 45, "Back to reality!");
        this.addDialogOption(TCREntities.AINE.get(), 25, "...");
        this.addDialogAnswer(TCREntities.AINE.get(), 46, "What's wrong, %s?");
        this.addDialogAnswer(TCREntities.AINE.get(), 47, "...");
        this.addDialogOption(TCREntities.AINE.get(), 26, "No, I want to stay here");
        this.addDialogAnswer(TCREntities.AINE.get(), 48, "Why?");
        this.addDialogOption(TCREntities.AINE.get(), 27, "I want...");
        this.addDialogOption(TCREntities.AINE.get(), 28, "Forever...");
        this.addDialogOption(TCREntities.AINE.get(), 29 , "To be with them...");
        this.addDialogAnswer(TCREntities.AINE.get(), 49, "...I respect your choice. Whenever you want to return to reality, you can come find me!");

        this.addDialogOption(TCREntities.ORNN.get(), -4, "§6Advanced Forging");
        this.addDialogOption(TCREntities.ORNN.get(), -3, "§6[Unlock New Trade Options]");
        this.addDialogOption(TCREntities.ORNN.get(), -2, "End Conversation");
        this.addDialogOption(TCREntities.ORNN.get(), -1, "Continue");
        this.addDialogAnswer(TCREntities.ORNN.get(), -1, "The heat of fire, the toughness of steel—these two things can solve most problems in the world. Master, what is your command?");
        this.addDialogAnswer(TCREntities.ORNN.get(), 0, "The heat of fire, the toughness of steel—these two things can solve most problems in the world.");
        this.addDialogOption(TCREntities.ORNN.get(), 0, "Who are you?");
        this.addDialogAnswer(TCREntities.ORNN.get(), 1, "I am a demigod, master of forging and craftsmanship, forging divine weapons for the Sanctum. Before the Black Tide came, I thought the weapons I forged were unmatched in the world. Thinking back now... laughable, laughable.");
        this.addDialogOption(TCREntities.ORNN.get(), 1, "About %s");
        this.addDialogAnswer(TCREntities.ORNN.get(), 2, "The Warden is truly unfathomable, but it's best to follow her arrangements.");
        this.addDialogAnswer(TCREntities.ORNN.get(), 3, "That young lady is freer than I am. I can never return to my homeland.");
        this.addDialogOption(TCREntities.ORNN.get(), 2, "§6Forging Commission");
        this.addDialogOption(TCREntities.ORNN.get(), 3, "§aWelcome Gift");
        this.addDialogAnswer(TCREntities.ORNN.get(), 4, "These were forged from leftover scraps. Take them for self-defense for now.");
        this.addDialogOption(TCREntities.ORNN.get(), 4, "%s");
        // Show Armamentarium
        this.addDialogOption(TCREntities.ORNN.get(), 5, "§6Show [%s§6]");
        this.addDialogAnswer(TCREntities.ORNN.get(), 5, "God of Forging above! This Armamentarium... records all the high-tier weapons of the world and how to forge them! There are even weapons I've never known!");
        this.addDialogAnswer(TCREntities.ORNN.get(), 6, "This Armamentarium must have been forged by the Earth Angel Montis before the Black Tide descended.");
        this.addDialogAnswer(TCREntities.ORNN.get(), 7, "Leave it to me. I will unveil this exquisite tome for you!");
        this.addDialogOption(TCREntities.ORNN.get(), 6, "§6Unlock Compendium");
        // Yamato trade
        this.addDialogAnswer(TCREntities.ORNN.get(), 8, "This is, the legendary %s! You have found such a treasure, traveler!");
        this.addDialogAnswer(TCREntities.ORNN.get(), 9, "You want me to help you break the seal within? Hmm... I do know a way to unseal it. Once you've gathered the necessary materials, come back to me!");

        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -3, "Return");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -2, "End Conversation");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -1, "Continue");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), -1, "Hello, master. How may I assist you?");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 0, "Hello, traveler. How may I assist you?");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 0, "Who are you?");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 1, "I am the Ferry girl of the Sanctum, ordered by Lady %s to escort you out of the barrier and to that world. I also receive the souls of that world, thus I have collected many of their scattered trinkets and relics. However, you need [%s] to retrieve them from the Primordial Sea. If you have %s, you can also give them to me.");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 1, "About %s");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 2, "The Warden toils day and night to save the Sanctum. It is truly admirable.");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 3, "%s's forging skill is unmatched here. I often entrust him with repairing some rare trinkets.");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 2, "§6Extract Trinkets");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 3, "§aGo to Overworld");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 4, "§6Welcome Gift");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 4, "A small gift, I hope you'll accept it. Once this spirit hatches and matures, it can travel thousands of miles a day. I hope it helps you on your journey through the Overworld! §6Come back to me once you've raised it to adulthood.");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 5, "§6Choose [%s§6]");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 6, "§6I have raised the dragon");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 5, "It seems the little one is getting along well with you! Please accept these gifts! You can check their usage in the compendium. I believe they will bring more fun to your battles!");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 7, "Accept");

        this.addDialogOption(ModEntities.BONE_CHIMERA, -1, "Return");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 0, "Human? To find this place... did the Resonance Stone guide you here?");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 0, "Why are you imprisoned here?");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 1, "I am the mount of the Earth Angel. When the tower's seal descended, the Earth Angel transported me here so the Resonance Stone could find my location.");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 1, "Release Soul");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 2, "This body carries an undying curse. If you help me find release, my bones can be forged into a fine weapon. Are you ready?");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 2, "Ready");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 3, "Wait a while");

        this.addDialogAnswer(BTEntityType.END_GOLEM, 0, "(This man has an extremely powerful aura, he remains silent.)");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 1, "(It still stands there, unmoved.)");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 2, "If you want it, you'll have to take it.");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 3, "But you already knew that.");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 4, "How many times have we fought.");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 5, "(A sinister smile)");
        this.addDialogOption(BTEntityType.END_GOLEM, 0, "Hey, %s, the days you could easily open the portal are over!");
        this.addDialogOption(BTEntityType.END_GOLEM, 1, "(Sigh) Give me the [%s].");
        this.addDialogOption(BTEntityType.END_GOLEM, 2, "Continue.");
        this.addDialogOption(BTEntityType.END_GOLEM, 3, "I had a feeling you’d say that.");
        this.addDialogOption(BTEntityType.END_GOLEM, 4, "Hard to say. It's the only memory I have of us since we were kids.");
        this.addDialogOption(BTEntityType.END_GOLEM, 5, "Time to finish this! Once for!");

        this.addDialogOption(EntityTypeModule.RIBBIT.get(), -2, "Leave");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), -1, "Continue");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 0, "Croak! Human!");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 0, "Croak!");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 1, "Gurgle gaga!");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 2, "§6Trade");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 3, "§6About [%s§6]");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 4, "§6Submit [%s§6]");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 5, "§6Accept");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 1, "If you want to know more, bring lots and lots of amethyst blocks to trade, croak! Need 12, croak!");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 2, "Croak! Deal, croak!");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 3, "Croak, you call that a offering, croak! I need 12, croak!");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 4, "We were originally humans from the ocean village, cursed by the Black Tide into this form, croak.");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 5, "The ocean tower is very dangerous, croak. For the sake of the amethyst blocks, take these treasures for self-defense, croak!");

        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), -2, "Return");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), -1, "Continue");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 0, "Purus Absolutus! I remember, I remember everything!");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 0, "Purus Absolutus?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 1, "What does it mean?");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 1, "You don't remember? Purus Absolutus!");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 2, "Me?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 3, "My name?");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 2, "It seems you've also lost your memories to the Black Tide... That's right, you are Purus Absolutus, the Pure Angel!");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 4, "???");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 5, "What is all this?");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 3, "I placed my divinity into [%s] to protect it from the Black Tide's erosion.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 4, "The Resonance Stone guided you to retrieve [%s], which allowed my memories to be restored! Purus Absolutus, do you truly remember nothing?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 6, "Sorry");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 7, "I remember nothing");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 5, "I see... Before helping you regain your memories, tell me what happened. Why did you come here?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 8, "...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 6, "...(You tell [%s] about everything that happened before)");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 7, "So that's it... %s truly persisted until the very end... I never thought you would return with the witch to save the world. Very well. What do you wish to know about the past? I'll answer as best I can.");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 9, "Who am I, really?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 10, "What is the Black Tide, really?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 11, "§6I have no more questions");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 8, "Your name is Purus Absolutus. You are the Pure Angel, responsible for purifying the darkness of the world. Like us, you are one of the Ten Ruling Council of the World.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 9, "A thousand years ago, it was your turn to handle affairs beyond the heavens. But shortly after you left, the Black Tide descended upon the world.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 10, "Perhaps bereft of your purifying divine power, we were powerless against the Black Tide and could only let it erode us.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 11, "I don't know. When the Black Tide first fell, we conducted extensive research on it, but made no progress.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 12, "The Black Tide was originally a medium codenamed Risperdal. We couldn't perceive its existence specifically. But all living things affected by it gradually turned black and died, so we named it the Black Tide.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 13, "We only know the Black Tide came from a world beyond the heavens, but its target seemed very clear, always aiming for life forms led by us Angel.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 14, "Perhaps you leading us in recreation is the only hope for our civilization's survival.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 15, "So, you need the Eye of God, which represents my divinity, to assist in completing the ritual. But separating the Eye of God means I will abandon my mortal form.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 16, "Ah, well. Now, I am covered in the Black Tide, no longer my original body... Without the power of the Eye of God, I cannot maintain human form...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 17, "...Perhaps this is our destiny.");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 12, "Don't say that...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 18, "...See you tomorrow, Purus Absolutus! May we meet in the promised new world!");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 13, "§4Execute");

        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 0, "The past events... do you truly not remember?");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 0, "§4Execute");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 1, "The past events...");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 2, "You mean the past...");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 1, "I forged my divinity into %s to avoid the Black Tide's erosion, even though my divine body has long become a decayed puppet...");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 3, "Continue");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 2, "When you released my divine body just now, I regained part of my power. Purus, this blade was forged by you long ago using the power of purification.");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 3, "Pity the Black Tide erosion left only a rough embryo. Take it. May it help you sweep away the last of the Black Tide!");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 4, "Accept it, [%s]. I will wait for you at the world's end!");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 4, "...");

    }
}
