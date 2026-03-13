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

public class TCRZHLangGenerator extends TCRLangProvider {
    public TCRZHLangGenerator(PackOutput output) {
        super(output, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        this.add("skill_tree.tcrcore.magic", "魔法技能");
        this.add("unlock_tip.epicskills.acrobat.spider_techniques", " 击杀50只蜘蛛");
        this.add("unlock_tip.epicskills.acrobat.natural_sprinter", " 击杀2只狼人并冲刺10公里");
        this.add("unlock_tip.epicskills.acrobat.aqua_maneuvre", " 击杀3只远古守卫者并游泳3公里");

        this.add("unlock_tip.epicskills.prodigy.shooting_style", " 击杀2只末影人");
        this.add("unlock_tip.epicskills.prodigy.punishment_kick", " 击杀25只末影人");

        this.add("unlock_tip.epicskills.prodigy.arrow_tenacity", " 击杀5只骷髅");
        this.add("unlock_tip.epicskills.prodigy.dodge_master", " 击杀50只骷髅");

        this.add("unlock_tip.epicskills.prodigy.ender_step", " 击杀50只末影人");
        this.add("unlock_tip.epicskills.prodigy.ender_obscuris", " 击杀250只末影人");
        this.add("unlock_tip.epicskills.prodigy.time_travel", " 击杀1000只末影人");

        this.add("unlock_tip.epicskills.prodigy.buster_parade", " 击杀10只 活尸");
        this.add("unlock_tip.epicskills.prodigy.perfect_bulwark", " 击杀50只守卫者");
        this.add("unlock_tip.epicskills.prodigy.avatar_of_might", " 击杀50只 活尸");

        this.add("unlock_tip.epicskills.prodigy.precise_roll", " 冲刺4公里");
        this.add("unlock_tip.epicskills.prodigy.bull_charge", " 击杀40只劫掠兽");

        this.add("unlock_tip.epicskills.prodigy.shulker_cloak", " 击杀36只潜影贝");
        this.add("unlock_tip.epicskills.prodigy.soul_protection", " 击杀60只凋灵骷髅");
        this.add("unlock_tip.epicskills.prodigy.vampirize", " 击杀20只邪恶骷髅");
        this.add("unlock_tip.epicskills.prodigy.dopamine", " 击杀27只狼人");
        this.add("unlock_tip.epicskills.prodigy.lunatic_vivacity", " 击杀9只恶魂");
        this.add("unlock_tip.epicskills.prodigy.voodoo_magic", " 击杀10只唤魔者");
        this.add("unlock_tip.epicskills.prodigy.manipulator", " 击杀6只 索罗蒙僧");

        this.add("unlock_tip.epicskills.prodigy.all_eyes_on_you", " 击杀100只邪恶骷髅");
        this.add("unlock_tip.epicskills.prodigy.all_eyes_on_me", " 击杀100只 索罗蒙僧");

        this.add("unlock_tip.epicskills.prodigy.inner_growth", " 击败末影龙");
        this.add("unlock_tip.epicskills.prodigy.shadow_step", " 击败凋灵");

        this.add("unlock_tip.epicskills.prodigy.critical_knowledge", " 击杀50只苦力怕和150只怪物");
        this.add("unlock_tip.epicskills.prodigy.dancing_blade", " 击杀5只女巫和150只怪物");

        this.add("travelerstitles.tcrcore.sanctum", "梦之领域");
        this.add("travelerstitles.tcrcore.real", "永远抵达不到的现实");
        this.add("travelerstitles.minecraft.overworld", "记忆中的主世界");
        this.add("travelerstitles.aether.the_aether", "记忆中的天界");
        this.add("travelerstitles.minecraft.the_nether", "记忆中的下界");
        this.add("travelerstitles.minecraft.the_nether.color", "750909");
        this.add("travelerstitles.minecraft.the_end", "记忆中的终界");
        this.add("travelerstitles.minecraft.the_end.color", "4f219e");
        this.add("travelerstitles.pbf1.sanctum_of_the_battle1", "轮回绝境");

        this.addBiome(TCRBiomes.AIR, "");
        this.addBiome(TCRBiomes.REAL, "");

        this.addQuest(TCRQuests.WAIT_RESONANCE_STONE_CHARGE, "间章", "等待[%s]充能", "[%s]的能量已经耗尽，重新充能需要一段时间。在充能完成之前先去做点别的事吧！");
        this.addQuest(TCRQuests.PUT_DESERT_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_ABYSS_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_CURSED_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_FLAME_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_MECH_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_STORM_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_VOID_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_MONST_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.BLESS_ON_THE_GODNESS_STATUE, "间章", "在女神像处祈福", "[神之眼]已经寻回，借助花园里的女神像，我们可以与神之眼共鸣，获得来自使徒们的力量。");

        this.addQuest(TCRQuests.TALK_TO_AINE_CLOUDLAND, "间章", "和%s对话", "当你接触祭坛后被卷入了一个神秘的世界，或许我们应该称之为幻境（Cloudland）。去找%s聊聊吧，或许她知道这究竟是什么地方。");

        //序章
        this.addQuest(TCRQuests.TALK_TO_AINE_0, "序章", "和%s对话", "和%s说好了一起来到这个世界，但是当你回过神来后%s已经找不着人影了，快去圣殿里找找她吧！你依稀记得她好像说有什么新时装要给你。");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_0, "序章", "和？对话", "和%s说好了一起来到这个世界，但是当你回过神来后%s已经找不着人影了。这里到底是什么地方？和圣殿长廊中那位端庄的女人交流看看吧！");
        this.addQuest(TCRQuests.TALK_TO_FERRY_GIRL_0, "序章", "前往[%s]", "你已经听说了这个世界的故事，准备好了就前往码头寻找 %s 吧！她将为我们打开前往[%s]的路！她似乎还有什么宝贝要送给你。");
        this.addQuest(TCRQuests.TALK_TO_ORNN_0, "序章", "和%s对话", "在%s的建议下，先去武库找%s武装一下我们自己吧！充分的武装才能保证我们顺利的冒险！");

        //驯龙支线
        this.addQuest(TCRQuests.TAME_DRAGON, "驯龙之章", "将龙养大", "%s送给了你一条龙，现在，按[%s]上说的办法，将它养成年吧！任何食物都可以喂养你的龙！龙，可是帝王之征！\n\n§a[任务奖励]: §f[%s] [%s]");
        this.addQuest(TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL, "驯龙之章", "和%s对话", "经过你精心呵护，龙已长大成年。%s之前说过，在龙养大后她有宝具要赠与我们。快回去找%s看看吧！\n\n§a[任务奖励]: §f[%s] [%s]");

        //主线·沙漠之眼
        this.addQuest(TCRQuests.USE_LAND_RESONANCE_STONE, "大地之章", "使用[%s]", "你终于来到了传说中的[%s]。在这里将会遇到什么样的冒险呢？快使用[%s]吧！它将指引我们寻回第一颗眼睛。");
        this.addQuest(TCRQuests.GET_DESERT_EYE, "大地之章", "寻回[%s]", "[%s]为我们标记了[%s]所散落的位置，快出发去寻回[%s]吧！\n\n§a[提示]:探索高塔以寻找召唤boss的钥匙！注意，[%s§a]可能藏匿于方块之中！\n\n§4[注意]：若获取后无法完成任务，请尝试关闭可能自动拾取物品的插件，并扔出去后重新拾取！");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_1, "大地之章", "和%s对话", "[%s]已经寻回，快回主城找%s汇报吧！她将告诉我们下一步该做什么。");
        //奇美拉支线
        this.addQuest(TCRQuests.BONE_CHIMERA_QUEST, "神兵之章", "前往[%s]", "[%s]似乎为我们标记了一个另一个地点，说不定有什么奇遇，快去看看吧！\n\n§a[任务奖励]: §f[%s§f]");
        this.addQuest(TCRQuests.TALK_TO_ORNN_1, "神兵之章", "和%s对话", "从[%s]身上获得了[%s]。上面到底记载了什么秘密？带回主城找%s看看吧！");

        //主线·深渊之眼
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_2, "海洋之章", "和%s对话", "经过漫长的等待，%s已完成充能。快回去找%s吧！她在圣殿里等你。");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_OCEAN, "海洋之章", "前往[%s]", "[%s]已完成充能，该前往[%s]使用它了！它将为我们指引[%s]的位置！");
        this.addQuest(TCRQuests.USE_OCEAN_RESONANCE_STONE, "海洋之章", "使用[%s]", "你已经抵达了[%s]，快使用[%s]吧！它将为我们指引[%s]的位置！到底有什么样的冒险在等着我们呢？");
        this.addQuest(TCRQuests.GET_OCEAN_EYE, "海洋之章", "寻回[%s]", "[%s]为我们标记了[%s]所散落的位置，快出发去寻回[%s]吧！\n\n§a[提示]:[%s§a]可能藏匿于方块之中！ \n\n§4[注意]：若获取后无法完成任务，请尝试关闭可能自动拾取物品的插件，并扔出去后重新拾取！");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_3, "海洋之章", "和%s对话", "[%s]已经寻回，快回主城找%s汇报吧！她将告诉我们下一步该做什么。\n\n本次我们还取回了[%s]，这里面究竟蕴藏了什么秘密？顺便问问吧！");

        this.addQuest(TCRQuests.RIBBITS_QUEST, "海洋之章", "探索[%s]", "[%s]似乎为我们标记了一个另一个地点，说不定有什么奇遇，快去看看吧！\n\n§a[任务奖励]: §f[%s] [%s]\n\n§6[推荐优先完成]");
        this.addQuest(TCRQuests.GIVE_AMETHYST_BLOCK_TO_RIBBITS, "海洋之章", "将[%s]交给%s", "看来想要打探更多关于[%s]的线索，需要与%s们做个交易才行。收集好12个[%s]就回去找它们吧！\n\n§a[任务奖励]: §f[%s] [%s]\n\n§6[推荐优先完成]");

        //主线·诅咒之眼
        this.addQuest(TCRQuests.TALK_TO_AINE_ECHO, "灵魂之章", "和%s对话", "[%s]也不知道[%s]的来历，去找%s问问吧！或许智库中有所记载，借助魔力或许可以解读其中的秘密。");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_4, "灵魂之章", "和%s对话", "%s已经将[%s]解读完毕了，现在请把消息告诉%s吧！");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_CURSED, "灵魂之章", "前往[%s]", "你意外地从[%s]中获得了[%s]散落的位置，快前去[%s]寻回它吧！[%s]将为我们指引它的位置。");
        this.addQuest(TCRQuests.USE_CURSED_RESONANCE_STONE, "灵魂之章", "使用[%s]", "你意外地从[%s]中获得了[%s]散落的位置，快前去[%s]寻回它吧！[%s]将为我们指引它的位置。");
        this.addQuest(TCRQuests.GET_CURSED_EYE, "灵魂之章", "寻回[%s]", "[%s]为我们标记了[%s]所散落的位置，快出发去寻回[%s]吧！\n\n§a[提示]:§f探索以寻找召唤[%s]的线索！");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_5, "灵魂之章", "和%s对话", "[%s]已经寻回，快回主城找%s汇报吧！她将告诉我们下一步该做什么。");

        //铁魔法支线
        this.addQuest(TCRQuests.TALK_TO_AINE_MAGIC, "魔法之章", "和%s对话", "[%s]掉落了神秘的%s，或许掌握魔法的%s能为我们解读上面的秘密！");
        this.addQuest(TCRQuests.TRY_TO_LEARN_MAGIC, "魔法之章", "尝试淬灵", "[%s]为我们揭示了这个世界的魔法，现在，按照%s所说的，尝试将卷轴里的法术淬灵在武器上吧！\n\n§a[提示]：%s处解锁了新的对话选项！");
        this.addQuest(TCRQuests.TALK_TO_AINE_MAGIC_2, "魔法之章", "和%s对话", "你已经学会了将法术淬灵在武器上啦，快去和%s聊聊吧！她将教会你如何运用好魔法！");

        //主线·烈焰之眼
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_6, "烈焰之章", "和%s对话", "经过漫长的等待，%s已完成充能。快回去找%s吧！她在圣殿里等你。");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_CORE, "烈焰之章", "前往[%s]", "[%s]已完成充能，该前往[%s]使用它了！它将为我们指引[%s]的位置！");
        this.addQuest(TCRQuests.USE_CORE_RESONANCE_STONE, "烈焰之章", "使用[%s]", "你已经抵达了[%s]，快使用[%s]吧！它将为我们指引[%s]的位置！到底有什么样的冒险在等着我们呢？");
        this.addQuest(TCRQuests.GET_FLAME_EYE, "烈焰之章", "寻回[%s]", "[%s]为我们标记了[%s]所散落的位置，快出发去寻回[%s]吧！\n\n§a[提示]:§f[%s]或藏匿于地下高塔之中，你需要有破开黑曜石屏障的力量！");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_7, "烈焰之章", "和%s对话", "[%s]已经寻回，快回主城找%s汇报吧！她将告诉我们下一步该做什么。");

        this.addQuest(TCRQuests.TALK_TO_AINE_1, "间章", "和%s聊聊", "收集神之眼的旅途至今已归还一半祭坛了，和%s聊聊吧！");

        //主线·地狱之章
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_8, "地狱之章", "和%s对话", "经过漫长的等待，%s已完成充能。快回去找%s吧！她在圣殿里等你。");
        this.addQuest(TCRQuests.GO_TO_NETHER, "地狱之章", "前往[%s]", "散落在[%s]的神之眼皆已寻回，共鸣石或许无法在[%s]发挥作用。按%s所说，使用[%s]点燃黑曜石框架打开%s吧！");
        this.addQuest(TCRQuests.USE_NETHER_RESONANCE_STONE, "地狱之章", "使用[%s]", "你已经抵达[%s]，快使用[%s]吧！它将为我们指引[%s]的位置！到底有什么样的冒险在等着我们呢？");
        this.addQuest(TCRQuests.GET_MONST_EYE, "地狱之章", "寻回[%s]", "[%s]为我们标记了[%s]所散落的位置，快出发去寻回[%s]吧！ \n\n§a[提示]：建议装备[%s]");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_9, "地狱之章", "和%s对话", "[%s]已经寻回，快回主城找%s汇报吧！她将告诉我们下一步该做什么。");

        //主线·毁灭之章
        this.addQuest(TCRQuests.GET_WITHER_EYE, "毁灭之章", "寻回[%s]", "海船墓地回响的研究有进展了！我们必须启动古老的召唤仪式，将死亡使徒唤回！快按%s所说的，召唤[%s]，夺回[%s]吧！");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_10, "毁灭之章", "和%s对话", "[%s]已经寻回，快回主城找%s汇报吧！她将告诉我们下一步该做什么。");
        this.addQuest(TCRQuests.TALK_TO_AINE_SAMSARA, "间章", "和%s对话", "%s告诉我们，[%s]是打开[%s]的钥匙。[%s]究竟有什么秘密？快去找%s问问吧！");
        this.addQuest(TCRQuests.GO_TO_SAMSARA, "轮回之章", "前往[%s]", "[%s]里到底藏着什么秘密？快按%s所说的方法，激活[%s]看看吧！");

        //主线·天域之章
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_11, "天域之章", "和%s对话", "经过漫长的等待，%s已完成充能。快回去找%s吧！她在圣殿里等你。");
        this.addQuest(TCRQuests.GO_TO_AETHER, "天域之章", "前往[%s]", "以生命之源（%s）浇灌%s所筑之框，即可开启天堂之门！");
        this.addQuest(TCRQuests.USE_AETHER_RESONANCE_STONE, "天域之章", "使用[%s]", "收集神之眼的旅途至今，仅剩天域使徒（Sky Angel），和虚无使徒（Void Angel）的神之眼尚未归还...");
        this.addQuest(TCRQuests.GET_STORM_EYE, "天域之章", "寻回[%s]", "[%s]为我们标记了[%s]所散落的位置，快出发去寻回[%s]吧！");
        this.addQuest(TCRQuests.TALK_TO_SKY_GOLEM, "天域之章", "和%s对话", "%s似乎...没有完全消散，祂似乎恢复了神智，和%s交流看看吧！");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_12, "天域之章", "和%s对话", "[%s]已经寻回，并且了解了自己的故事...快回主城找%s汇报吧！她将告诉我们下一步该做什么。");
        this.addQuest(TCRQuests.TALK_TO_AINE_2, "天域之章", "和%s对话", "[%s]已经寻回，并且了解了自己的故事...和%s聊聊吧。");
        //主线·终末之章
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_END, "终末之章", "前往[%s]", "%s说，最后一颗神之眼，就位于[%s]。我们需要在[%s]，找到通往[%s]的[%s]，才能开启前往[%s]的大门！");
        this.addQuest(TCRQuests.USE_END_RESONANCE_STONE, "终末之章", "使用[%s]", "我们需要在[%s]，找到通往[%s]的[%s]，才能开启前往[%s]的大门！共鸣石将为我们指引[%s]的位置。");
        this.addQuest(TCRQuests.GO_TO_THE_END, "终末之章", "前往[%s]", "[%s]为我们指引了[%s]的位置，接下来，想办法前往[%s]吧！");
        this.addQuest(TCRQuests.GET_VOID_EYE, "终末之章", "寻回[%s]", "[%s]就在[%s]，拼尽全力夺回它！");
        this.addQuest(TCRQuests.TALK_TO_ORNN_YAMATO, "神兵之章", "和%s对话", "你意外地获得了[%s]，但它的力量似乎还未完全释放。快找%s聊聊吧！或许有什么办法可以让它恢复力量！");

        this.addQuest(TCRQuests.TALK_TO_CHRONOS_END, "终章", "和%s对话", "[%s]已经寻回...你终于集齐了8颗神之眼，是时候准备再创世仪式了。");
        this.addQuest(TCRQuests.KILL_MAD_CHRONOS, "终章", "击败[？？？]", "出现巨大的魔物，拼尽全力战胜祂！");

        this.addQuest(TCRQuests.TALK_TO_AINE_GAME_CLEAR, "后日谈", "和%s对话", "你，真的读懂[远梦之棺（The Casket of Reveries）]了吗？");

        this.addEffect(TCREffects.INVULNERABLE, "无敌");
        this.addEffect(TCREffects.SOUL_INCINERATOR, "灵魂火");
        this.addEffect(PECEffects.SOUL_INCINERATOR, "灵魂火");

        this.add("epicfight.skill_slot.passive4", "被动4");
        this.add("epicfight.skill_slot.passive5", "被动5");

        StartScreenHandler.onGenerateZH(this);
        FirstEnterCloudlandScreenHandler.onGenerateZH(this);
        ResetGameProgressScreenHandler.onGenerateZH(this);

        this.add("item.domesticationinnovation.collar_tag.tcr_info", "可进行特殊附魔，并将附魔应用于宠物身上。");
        this.add("block.domesticationinnovation.pet_bed_white.tcr_info", "可使宠物在宠物床复活。");
        this.addTCRItemInfo(net.blay09.mods.waystones.item.ModItems.warpStone, "点击物品栏中的传送卷轴按钮以进行传送。");
        this.addTCRItemInfo(ItemRegistry.BOTTLE_OF_BLOOD.get(), "使用§d[尼赫莫斯]§r掉落物§c[晶化血髓]§r酿造获取");
        this.addTCRItemInfo(EFNItem.DEEPDARK_HEART.get(), "击败§2[监守者]§r或§2[可妮莉亚船长]§r获取");
        this.addTCRItemInfo(ModItems.CORAL_CHUNK.get(), "于§d利维坦幻境§r击败§a[珊瑚巨像]§r获取");
        this.addTCRItemInfo(com.github.dodo.dodosmobs.init.ModItems.CHIERA_CLAW.get(), "击败§e[骸骨奇美拉]§r获取");
        this.addTCRItemInfo(ModItems.CHITIN_CLAW.get(), "于§3斯库拉幻境§r击败§3巨钳守卫§r获取");
        this.addTCRItemInfo(Items.DRAGON_EGG, "于§d末地§r击败§d[末影龙]§r获取");
        this.addTCRItemInfo(EpicSkillsItems.ABILIITY_STONE.get(), "右键使用以获取技能点");

        this.add("itemGroup.tcr.items", "远梦之棺 —— 核心 物品");
        this.add("key.categories." + TCRCoreMod.MOD_ID, "远梦之棺 —— 核心");
        this.addKeyMapping(TCRKeyMappings.RIPTIDE, "激流");
        this.addKeyMapping(TCRKeyMappings.SHOW_QUESTS, "隐藏/显示指引");
        this.addKeyMapping(TCRKeyMappings.EXIT_SPECTATOR, "退出观察者模式");

        this.add("skill_tree.sword_soaring.unlock_tip", "推进主线任务习得");
        this.add("unlock_tip.tcrcore.battleborn.water_avoid1", "使用§d[紫水晶块]§f向§6[呱呱]§f交易习得");
        this.add("unlock_tip.tcrcore.battleborn.fire_avoid", "推进主线任务习得");
        this.add("unlock_tip.tcrcore.get_vatansever", "获得§d「卫疆刃」§f后解锁");
        this.addSkill("water_avoid", "避水咒", "可在水下自由呼吸！");
        this.addSkill("fire_avoid", "避火咒", "免疫火焰伤害！");
        this.addSkill("perfect_dodge", "闪避特效", "完美闪避时将有帅气的动作！");

        this.add(TCRItems.RESET_SKILL_STONE.get(), "重置技能石");
        this.addItemUsageInfo(TCRItems.RESET_SKILL_STONE.get(), "使用后将重置技能树中的所有技能并返还点数，十分的珍贵！");
        this.add(TCRItems.PROOF_OF_ADVENTURE_PLUS.get(), "真·冒险之证");
        this.addItemUsageInfo(TCRItems.PROOF_OF_ADVENTURE_PLUS.get(), "至高无上的荣耀！");
        this.add(TCRItems.DIVINE_FRAGMENT.get(), "神性碎片");
        this.addItemUsageInfo(TCRItems.DIVINE_FRAGMENT.get(), "上面记载了神的意志");
        this.add(TCRItems.ABYSS_FRAGMENT.get(), "渊海葬歌的回响");
        this.add(TCRItems.DESERT_FRAGMENT.get(), "遗忘之沙的回响");
        this.add(TCRItems.ENDER_FRAGMENT.get(), "终末之诗的残片");
        this.add(TCRItems.MECH_FRAGMENT.get(), "永动齿轮的残骸");
        this.add(TCRItems.NETHERITE_FRAGMENT.get(), "熔心淬火的余烬");
        this.add(TCRItems.FLAME_FRAGMENT.get(), "初火残响");
        this.add(TCRItems.STORM_FRAGMENT.get(), "雷渊怒吼的碎片");
        this.add(TCRItems.SOUL_FRAGMENT.get(), "咒骨囚魂的遗尘");
        this.add(TCRItems.STONE_OF_REINCARNATION.get(), "轮回之石");
        this.addItemUsageInfo(TCRItems.STONE_OF_REINCARNATION.get(), "使用后将回到旅途的起点。");
        this.add(TCRItems.WITHER_SOUL_STONE.get(), "凋灵魂石");
        this.addItemUsageInfo(TCRItems.WITHER_SOUL_STONE.get(), "它现在似乎失去了魔力，只是一块独特的石头。想办法激活它吧！");
        this.add(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), "凋灵魂石");
        this.addItemUsageInfo(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), "可打开前往[%s]的大门。");
        this.add(TCRItems.MAGIC_BOTTLE.get(), "魔力之瓶");
        this.addItemUsageInfo(TCRItems.MAGIC_BOTTLE.get(), "使用后可恢复一定比例的魔力。当用尽后，需在主城中使用以补充魔力。");
        this.add(TCRItems.MYSTERIOUS_WEAPONS.get(), "百兵图");
        this.addItemUsageInfo(TCRItems.MYSTERIOUS_WEAPONS.get(), "上面似乎记载了世间百般兵器，拿给了解的人看看吧。");
        this.add(TCRItems.NECROMANCY_SCROLL.get(), "死灵法卷");
        this.addItemUsageInfo(TCRItems.NECROMANCY_SCROLL.get(), "上面似乎记载了魔法的秘密，拿给了解的人看看吧。");
        this.add(TCRItems.DRAGON_FLUTE.get(), "龙之笛");
        this.addItemUsageInfo(TCRItems.DRAGON_FLUTE.get(), "右键可收服龙，再次右键可释放龙。");
        this.add(TCRItems.RESONANCE_STONE.get(), "共鸣石");
        this.add(TCRItems.LAND_RESONANCE_STONE.get(), "大地共鸣石");
        this.add(TCRItems.OCEAN_RESONANCE_STONE.get(), "海洋共鸣石");
        this.add(TCRItems.CURSED_RESONANCE_STONE.get(), "诅咒共鸣石");
        this.add(TCRItems.CORE_RESONANCE_STONE.get(), "炉心共鸣石");
        this.add(TCRItems.NETHER_RESONANCE_STONE.get(), "地狱共鸣石");
        this.add(TCRItems.SKY_RESONANCE_STONE.get(), "天域共鸣石");
        this.add(TCRItems.END_RESONANCE_STONE.get(), "终界共鸣石");
        this.add(TCRItems.CORE_FLINT.get(), "炉心火石");
        this.addItemUsageInfo(TCRItems.CORE_FLINT.get(), "在黑曜石框架上使用，可打开地狱之门。");
        this.add(TCRItems.PROOF_OF_ADVENTURE.get(), "冒险之证");
        this.addItemUsageInfo(TCRItems.PROOF_OF_ADVENTURE.get(), "此证乃是用所有败于你剑下的强敌之名铸就。你的冒险已抵达终点，你的勇气已化为传奇。");
        this.add(TCRItems.DUAL_BOKKEN.get(), "双持木棍");
        this.addItemUsageInfo(TCRItems.DUAL_BOKKEN.get(), "我或许能力不足，但绝不缺乏奉献精神，难道你缺少奉献精神吗？");
        this.add(TCRItems.VOID_CORE.get(), "虚空精华");
        this.addItemUsageInfo(TCRItems.VOID_CORE.get(), "击败[末影守卫]掉落");
        this.add(TCRItems.ABYSS_CORE.get(), "深渊精华");
        this.addItemUsageInfo(TCRItems.ABYSS_CORE.get(), "击败[利维坦]掉落");
        this.add(TCRItems.ARTIFACT_TICKET.get(), "饰品精华");
        this.addItemUsageInfo(TCRItems.ARTIFACT_TICKET.get(), "通过任务书某些任务获取。可在§d[圣殿港口]§r的§3[摆渡人]§r处提炼饰品");
        this.add(TCRItems.RARE_ARTIFACT_TICKET.get(), "稀有饰品精华");
        this.addItemUsageInfo(TCRItems.RARE_ARTIFACT_TICKET.get(), "通过任务书某些任务获取。可在§d[圣殿港口]§r的§3[摆渡人]§r处提炼稀有饰品");
        this.add(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "神谕残卷");
        this.addItemUsageInfo(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "§c§kI'm Your...");

        this.addInfo("talk_to_start", "对话以开始战斗");
        this.addInfo("temp_dragon_flute", "[临时龙之笛]：使用后将消耗！");
        this.addInfo("dragon_die_back", "您的龙已死亡，未检测到宠物床，已转化为龙之笛。");
        this.addInfo("only_work_on_dragon", "只能对龙类使用！");
        this.addInfo("creative_may_lost_progress", "警告！创造模式下击败boss将无法推进游戏进度！");
        this.addInfo("exit_spectator_in_pbf1", "按[%s]以退出观察者模式并回到主城");
        this.addInfo("cataclysm_humanoid_drop_desc", "于[%s]击败[%s]掉落");
        this.addInfo("can_not_use_scroll_directly","请将法术附魔在武器上以使用！！");
        this.addInfo("quest_updated","当前追踪的任务已更新！");
        this.addInfo("i18n_pack", "国际化翻译包，感谢所有译者！");
        this.addInfo("can_not_dodge", "Boss攻击将无视闪避！");
        this.addInfo("can_not_guard", "Boss攻击将无视防御！");
        this.addInfo("pec_weapon_lock", "武器技能已锁定！在[%s]击败[%s]以解锁！");
        this.addInfo("resonance_stone_usage", "可与使徒封印的位置共鸣。");
        this.addInfo("resonance_search_failed", "错误：无法共鸣！请重启游戏后再次尝试！请保留latest.log并向作者报告此错误！[%s]");
        this.addInfo("yamato_skill_lock", "[%s]已锁定，需对应附魔书以解锁！");
        this.addInfo("congratulation", "恭喜！");
        this.addInfo("open_backpack_tutorial", "按下 [%s] 以打开随身空间");
        this.addInfo("unlock_new_ftb_page_title", "§6新图鉴解锁！");
        this.addInfo("unlock_new_ftb_page_subtitle", "§a按[%s§a]查看");
        this.addInfo("resonance_stone_working", "[%s]共鸣中...请耐心等待...");
        this.addInfo("containing_dragon", "物种：[%s]");
        this.addInfo("dragon_owner", "主人：[%s]");
        this.addInfo("quest_map_mark", "任务点");
        this.addInfo("map_pos_marked_press_to_open", "已标记地点，按 [%s] 查看地图");
        this.addInfo("press_to_open_skill_tree", "按下 %s 以打开技能树");
        this.addInfo("press_to_show_quest_ui", "按 %s 键查看任务列表");
        this.addInfo("please_use_custom_flint_and_steel", "请使用[%s]开启地狱传送门");
        this.addInfo("exit_quest_screen", "退出");
        this.addInfo("start_tracking_quest", "开始追踪");
        this.addInfo("cancel_tracking_quest", "停止追踪");
        this.addInfo("no_quest", "暂无任务");
        this.addInfo("tracking_quest", " [☆追踪中]");
        this.addInfo("require_item_to_wake", "需要[%s]以唤醒...");
        this.addInfo("weapon_no_interact", "武器禁止交互！请使用其他物品或按[%s]切换非战斗模式");
        this.addInfo("tudigong_gift", "[见面礼]");
        this.addInfo("tudigong_gift_get", "§6[土地公]§f: 老夫不中用矣，此宝具赠予你罢！");
        this.addInfo("need_to_unlock_waystone", "仍有传送石碑未激活！");
        this.addInfo("nether_unlock", "地狱之门已解锁！");
        this.addInfo("end_unlock", "末地之门已解锁！");
        this.addInfo("dim_max_4_players", "§6幻境最多容纳4人！");
        this.addInfo("dim_max_players", "§6已达人数上限");
        this.addInfo("can_not_enter_before_finish", "§6当前没有进入幻镜的命运。");
        this.addInfo("can_not_do_this_too_early", "§6当前没有做此事的命运。");
        this.addInfo("after_heal_stop_attack", "§6停止攻击以取消傀儡仇恨");
        this.addInfo("cloud_follow_me", "§6[仙气]：§f嗨伙计，跟着我！");
        this.addInfo("shift_to_pic", "潜行时攻击以收回");
        this.addInfo("no_place_to_ship", "空间不足，无法摆放船只！");
        this.addInfo("boss_killed_ready_return", "§6幻境英灵已击败！已解锁方块交互！");
        this.addInfo("click_to_return", "§a[点击返回主城]");
        this.addInfo("cs_warning", "§c§l警告！Compute Shader未启用！建议在史诗战斗设置中开启以获得更流畅的体验！");
        this.addInfo("wraithon_start_tip", "§d[Wraithon]: §6外来之人，你的旅途到此结束！");
        this.addInfo("wraithon_end_tip", "§d[Wraithon]: §6这...不可能...");
        this.addInfo("dim_block_no_interact", "§cBoss未击败！暂时无法与幻境方块交互！");
        this.addInfo("altar_dim_info", "幻境信息：");
        this.addInfo("related_loot", "魔物： [%s] | 相关战利品：[%s]");
        this.add(TCRBlocks.CURSED_ALTAR_BLOCK.get(), "诅咒祭坛");
        this.add(TCRBlocks.ABYSS_ALTAR_BLOCK.get(), "深渊祭坛");
        this.add(TCRBlocks.STORM_ALTAR_BLOCK.get(), "风暴祭坛");
        this.add(TCRBlocks.FLAME_ALTAR_BLOCK.get(), "烈焰祭坛");
        this.add(TCRBlocks.DESERT_ALTAR_BLOCK.get(), "沙漠祭坛");
        this.add(TCRBlocks.MONST_ALTAR_BLOCK.get(), "恶兽祭坛");
        this.add(TCRBlocks.VOID_ALTAR_BLOCK.get(), "虚空祭坛");
        this.add(TCRBlocks.MECH_ALTAR_BLOCK.get(), "机械祭坛");

        this.addInfo("nothing_happen_after_bless", "§d无事发生...看来该[神之眼]已经祈福过了");
        this.addInfo("attack_to_restart", "§c攻击Boss以再次发起挑战");
        this.addInfo("captain_start_heal", "§c可妮莉亚船长开始回血！增大伤害击败她！");
        this.addInfo("illegal_item_tip", "§c检测到非法物品！");
        this.addInfo("illegal_item_tip2", "§6当前没有使用此物品的命运。");

        this.addInfo("dim_demending", "§6幻境重铸中...请等待[%d§6]秒");
        this.addInfo("to_be_continue2", "[P1nero]: §6感谢游玩！更多Boss持续制作中，未完待续！");

        this.addInfo("second_after_boss_die_left", "将在 %d 秒后返回主世界");

        this.addInfo("press_to_open_battle_mode", "§c请按[%s]开启战斗模式!§r");
        this.addInfo("unlock_new_dim_girl", "§6摆渡人处已解锁新选项!§r");
        this.addInfo("unlock_new_dim", "§c[地狱]§d[末地]§6已解锁!§r");
        this.addInfo("iron_golem_name", "天空岛之守卫");

        this.addInfo("get_mimic_invite", "[%s]: 异界之人，我果然没看错你！这封§6[%s§6]§f，你收下罢！");
        this.addInfo("kill_arterius", "[%s]: 异界之人，果然有几分本事！看来预言是对的！这几块[%s§f]，赠予你罢！");

        this.addInfo("finish_all_eye", "§d众祭坛已点亮！§r");
        this.addInfo("time_to_altar", "失散火种已寻，该回去点亮祭坛了...");
        this.addInfo("time_to_ask_godness_statue", "§d*可在女神像处祈求祝福");
        this.addInfo("time_to_end", "所有祭坛已点亮，该找守卫者举行仪式了...");

        this.addInfo("reset_when_no_player", "当幻境内没有玩家存在时，离开幻境过久，幻境将会重置！");
        this.addInfo("on_full_set", "套装效果");
        this.addInfo("unlock_new_ftb_page", "解锁了新的任务界面，请打开§6[任务书]§r查看");
        this.addInfo("unlock_new_skill_page", "解锁了新的技能书界面！");
        this.addInfo("unlock_new_skill", "解锁了[%s]! ");
        this.addInfo("unlock_new_skill_sub", "按§6[%s]§r查看解锁的新技能");
        this.addInfo("hit_barrier", "前面的区域，以后再来探索吧！");

        this.addInfo("death_info", "§6敌人过于强大时，可以尝试搭配不同技能组合！");
        this.addInfo("enter_dimension_tip", "右键祭坛核心以进入英灵幻境");
        this.addInfo("use_true_eye_tip", "请使用 [%s] 右键祭坛核心");

        this.addInfo("add_item_tip", "获得新物品：%s × %d！");
        this.addInfo("skill_point_lack", "释放该技能需 %d 技能点");
        this.addInfo("press_to_open_portal_screen2", "点击物品栏中的§6[卷轴]§r以回到曾经点亮过的石碑！");
        this.addInfo("press_to_show_progress", "按下§6[L]键§f以查看指引！");
        this.addInfo("press_to_skill_tree", "经验充足，按下§6[K]键§f以进行技能加点！");
        this.addInfo("lock_tutorial", "按下§6[%s§6]§r以锁定目标");
        this.addInfo("lock_tutorial_sub", "§c晃动鼠标以切换锁定目标！再次按下以解除锁定！");
        this.addInfo("riptide_tutorial", "在水中按下§6[%s§6]键§f以释放§b激流");
        this.addInfo("dodge_tutorial", "按下§6[%s§6]§f以释放闪避技能");
        this.addInfo("weapon_innate_tutorial", "按下§6[%s§6]键§f以释放武器技能");
        this.addInfo("weapon_innate_charge_tutorial", "§6[完美闪避]§c或§6[完美招架]§c可以对部分武器进行充能！");
        this.addInfo("perfect_dodge_tutorial", "§c抓住时机闪避以释放完美闪避！");
        this.addInfo("hurt_damage", "造成[ %s ]点伤害！");
        this.addInfo("parry_tutorial", "按下§6[%s§6]§f以进行格挡");
        this.addInfo("perfect_parry_tutorial", "§c抓住时机格挡以触发完美招架！");
        this.addInfo("you_pass", "§6你过关！！");

        this.addInfo("press_to_open_map", "按下§6[M]键§f以查看地图");

        this.addInfo("godness_statue_pos", "女神像");
        this.addInfo("eye_pos_mark", "[%s]之所在：[%s]");

        this.addAdvancement(TCRCoreMod.MOD_ID, "远梦之棺", "梦开始的地方");
        this.addAdvancement("unlock_weapon_armor_book", "百兵图", "开启了武器图鉴和盔甲图鉴");
        this.addAdvancement("unlock_magic_and_boss", "死灵法卷", "开启了法术图鉴和生物图鉴");
        this.addAdvancement("unlock_epic_boss", "始源决斗场", "开启了新的篇章");

        this.add(TCREntities.CHRONOS_SOL.get(), "羲轮｜Chronos Sol");
        this.add(TCREntities.FERRY_GIRL.get(), "摆渡人");
        this.add(TCREntities.AINE.get(), "安");
        this.add(TCREntities.ORNN.get(), "老奥恩");
        this.add(TCREntities.TUTORIAL_GOLEM.get(), "训练傀儡");
        this.add(TCREntities.TCR_MIMIC.get(), "我？");

        this.add(TCRBossEntities.LEVIATHAN_HUMANOID.get(), "沧溟|Thalassa-Mare");
        this.add(TCRBossEntities.HARBINGER_HUMANOID.get(), "归寂|Letum-Quietus");
        this.add(TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get(), "湮无|Nihil-Vacuum");
        this.add(TCRBossEntities.IGNIS_HUMANOID.get(), "烬煌|Ignis-Ardens");
        this.add(TCRBossEntities.IGNIS_SHIELD.get(), "烬煌盾");
        this.add(TCRBossEntities.SCYLLA_HUMANOID.get(), "穹霄|Caelum-Altum");
        this.add(TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get(), "坤岳|Terra-Montis");
        this.add(TCRBossEntities.MALEDICTUS_HUMANOID.get(), "魂兮|Anima-Essentia");
        this.add(TCRBossEntities.NETHERITE_HUMANOID.get(), "狱渊|Infernus-Abyssus");


        this.addDialogAnswer(EntityType.IRON_GOLEM, 0, "人类，准备好接受试炼了？");
        this.addDialogOption(EntityType.IRON_GOLEM, 0, "确定");
        this.addDialogOption(EntityType.IRON_GOLEM, 1, "再等等");
        this.addDialogAnswer(EntityType.VILLAGER, -2, "曼波？");
        this.addDialogAnswer(EntityType.VILLAGER, -1, "！！！");
        this.addDialogAnswer(EntityType.VILLAGER, 0, "曼波，曼波，哦嘛吉利，曼波~");
        this.addDialogAnswer(EntityType.VILLAGER, 1, "砸布砸布~");
        this.addDialogAnswer(EntityType.VILLAGER, 2, "瓦一夏~曼波~");
        this.addDialogAnswer(EntityType.VILLAGER, 3, "南北绿豆~阿西噶阿西~");
        this.addDialogAnswer(EntityType.VILLAGER, 4, "哈基米南北绿豆~阿西噶阿西~");
        this.addDialogAnswer(EntityType.VILLAGER, 5, "叮咚鸡~叮咚鸡~");
        this.addDialogAnswer(EntityType.VILLAGER, 6, "有哒有哒~");
        this.addDialogAnswer(EntityType.VILLAGER, 7, "阿西噶哈雅酷那路~ wow~");
        this.addDialogOption(EntityType.VILLAGER, -3, "[或许可以试试绿宝石？]");
        this.addDialogOption(EntityType.VILLAGER, -2, "[这位村民对该物品并没有兴趣...]");
        this.addDialogOption(EntityType.VILLAGER, -1, "[收下]");
        this.addDialogOption(EntityType.VILLAGER, 0, "[？？？]");
        this.addDialogOption(EntityType.VILLAGER, 1, "[看来，当地的居民被侵蚀的不轻！]");
        this.addDialogOption(EntityType.VILLAGER, 2, "[叽里咕噜说什么呢？]");
        this.addDialogOption(EntityType.VILLAGER, 3, "[为什么和村民就语言不通了...]");

        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -4, "§a收下[%s§a]");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -3, "返回");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -2, "结束对话");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 0, "阁下，冒险可还顺利？");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 0, "关于这个世界");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 1, "千年以前，“我们”的世界受到未知的天外冲击，黑潮降临世间。面对无穷无尽的黑潮，“我们”无能为力。受黑潮影响，“我们”的记忆逐渐缺失...祂们一个接一个地被黑潮所吞噬，祂们的灵魂被封印在世界各地之中。当“我们”意识到“我们”的记忆正在如潮水般逐渐消退之时，“我们”用魔力写下了死海文书，用以提醒吾等，终有一位救世主自天外归来，将“我们”的力量统合，实现再创世，将黑潮击溃！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 2, "吾受黑潮的侵蚀程度最轻，祂们用尽最后的力气，将吾与脚下这座主城封入原始之海，以减缓黑潮之侵蚀，等待救世主归来。至于救世主是何人，为何有救世的力量，“我们”已无从得知。但在黑潮面前，“我们”只能相信这份古老的记忆，相信再创世的到来...");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 1, "关于%s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 3, "阁下说的可是在此处落脚的魔女阁下？她看起来和阁下一样掌握着不属于这个世界的力量，吾相信预言，吾相信阁下与魔女阁下能拯救圣殿，这里的一切都将为二位敞开！");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 2, "关于%s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 4, "自黑潮入侵以来，她在港口驻守千年，是此间与世界唯一的联系。她是“我们”所铸造的人偶，随着“我们”记忆的消退，她的力量也所剩无几了。");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 3, "关于%s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 5, "祂是来自主世界的半神，掌管锻造与工艺。使徒们所用之神兵百胄皆出自祂之手。");

        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 6, "预言中的救世主啊，吾已在此恭候多时！唯有阁下可寻回众神之眼，恢复世界昔日之荣光！");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 4, "你是何人？");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 7, "吾乃世界十大使徒之一，名为 %s，掌管岁月。");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 5, "使徒？");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 8, "创世之初，圣殿便诞生了十位使徒，掌管世间万物，吾便是其中的一位。“我们”常共聚一堂，商议政要。若有需要“我们”出面之事，“我们”会选则“我们”之中的一位前去。");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 6, "§a关于接下来的行动");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 9, "阁下需寻回祂们的锚定之物——神之眼，将众使徒的灵魂归位到祭坛之上，届时，吾再献上自己的灵魂，便可实现死海文书所记载的再创世！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 10, "原始之海屏蔽了外界的气息，吾在此处无从得知祂们的位置。但借此§6共鸣石§f可再外界寻得神之眼散落的位置。我将这枚共鸣石给予阁下，待阁下到了主世界，共鸣石将引领你寻找使徒所在之处。切记，每个阶段仅能使用一次共鸣石！待阁下寻回神之眼后，吾方可利用神之眼铸造新的共鸣石。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 11, "阁下可以先去§6[武库]§f找 %s 取一样趁手的武器。准备好了就去港口寻找 %s 吧，她将带你前往旅程的起点。");

        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 12, "阁下，寻找[%s]的旅途可还顺利？");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 7, "§a我已寻回[%s§a]");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 13, "阁下不愧是预言中的救世主！请阁下将神之眼归位到祭坛上吧！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 14, "神之眼也可在女神像处祈福，神之眼所蕴含的使徒（Angel）的部分力量将通过神像享与阁下。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 15, "共鸣石充能仍需要点时间，还请阁下等待！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 16, "共鸣石已完成充能，出发吧，阁下！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 17, "辛苦阁下了！待吾为共鸣石充能，阁下请稍作休息！");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 8, "§a关于[%s§a]");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 18, "[%s]？吾找不到相关记忆，还请阁下去请教魔女阁下，借助她的力量在智库中搜寻看看吧！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 19, "阁下，共鸣石尚未完成充能。还请阁下稍安勿躁。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 20, "这是...%s？！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 21, "既然如此，这份记忆便可以让共鸣石提前恢复魔力，吾这就将回响注入共鸣石之中，阁下务必把祂的火种收回！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 22, "请阁下将神之眼归位到祭坛上吧！没想到掌管冥界的使徒（Angel），终将自己踏入冥界...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 23, "请阁下将神之眼归位到祭坛上吧！你在灰烬里埋藏复活的密码，将锈蚀的锁熔成流淌的金河…");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 24, "阁下，散落在主世界（Overworld）的神之眼皆已寻回，共鸣石或许无法在主世界发挥作用。若共鸣石没有回应，或许该去其它维度试试。");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 9, "其它维度？");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 25, "创世之初，世界分为了四个维度，分别是主世界（The Overworld），地狱（The Nether），天域（The Aether），和终界（The End），分别由各自的使徒所掌管。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 26, "黑潮入侵后，祂们的灵魂也永远留在了那里。吾用[%s]的力量为阁下打造了[%s]。用[%s]点燃黑曜石框架，即可打开地狱之门！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 27, "阁下要是准备好了，就前往地狱使用共鸣石吧！在这之前，阁下请接受赐福，以免受火焰之灾！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 28, "阁下来得正好！魔女阁下对海船墓地回响的研究有进展了！我们必须启动古老的召唤仪式，将死亡使徒(Death Angel)唤回！");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 10, "死亡使徒？");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 29, "掌管死亡的使徒——%s，从海船墓地中灵魂使徒留下的信息来看，它的灵魂正囚禁在冥河之中！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 30, "我们必须举行召唤仪式，将祂从冥河中解救出来！");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 11, "召唤仪式？");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 31, "阁下可曾在地狱见到[%s]？将[%s]以T形排列，再在上方摆上三个[%s]，即可召唤！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 32, "[%s]？此乃死亡使徒体内力量汇集而成的精华，可以打开[%s]的大门！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 33, "但吾已无力为阁下开启[%s]，或许需要借助魔女阁下的力量，才能激发其中的魔力，打开轮回之门。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 34, "在共鸣石完成充能之前，阁下先去找%s看看[%s]吧！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 35, "咳咳，阁下，接下来，咳咳...");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 12, "%s，你怎么了？");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 13, "你看起来很糟糕");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 36, "咳咳，神之眼回到祭坛之后，我感觉我的力量逐渐在消退...或许黑潮的诅咒快轮到我了吧...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 37, "归还神之眼之旅至今，仅剩天域使徒（Sky Angel），和虚无使徒（Void Angel）的神之眼尚未归还...随着[死亡使徒之眼]归还，天堂之门也随之打开。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 38, "传言生灵死后，善者入天堂，恶者下地狱，善恶不分者遁入虚无，而死亡使徒与灵魂使徒（Soul Angel），则掌管冥河，安排亡灵们的去向。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 39, "黑潮过后，阴阳平衡被打乱，亡灵也不断涌入主世界，无法轮回。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 40, "出发吧，趁我的力量还未完全消退，§6以生命之源浇灌萤石所筑之框，即可开启天堂之门！§f待阁下步入天域（The Aether），再以共鸣石寻[%s]之所在吧。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 41, "天域凶险，吾将赐予阁下§6[御剑(Sword Soaring)]§f的祝福！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 42, "咳咳，阁下，你来了，咳咳...寻找[%s]的旅途可还顺利？");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 14, "%s，");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 15, "我已知晓我是谁");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 43, "...（你将%s发生的事告诉了%s）");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 44, "原来如此...原来死海文书中的救世主，乃是纯净使徒...唯有纯净能扫去一切黑暗...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 45, "难怪吾总觉得，阁下与我们有着同样的气息，且能穿行于三界。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 46, "咳，眼下就差最后一步...就可以启动仪式了...虚无使徒的神之眼，就在终界...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 47, "我已无力为共鸣石充能，这共鸣石的能量，仅足以为阁下寻找[%s]...剩下的路，还请阁下保重！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 48, "......");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 16, "%s？");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 17, "%s！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 49, "Purus Absolutus..Purus Absolutus...!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 50, "");

        this.addDialogOption(TCREntities.AINE.get(), -4, "§6法术淬灵");
        this.addDialogOption(TCREntities.AINE.get(), -3, "§6法术交易");
        this.addDialogOption(TCREntities.AINE.get(), -2, "结束对话");
        this.addDialogOption(TCREntities.AINE.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.AINE.get(), -2, "要学习哪个流派（School）呢？");
        this.addDialogAnswer(TCREntities.AINE.get(), -1, "%s, 你来了！今天要做些什么呢？");
        this.addDialogAnswer(TCREntities.AINE.get(), 0, "%s, 你来了！我正在阅读这个世界的智库");
        this.addDialogOption(TCREntities.AINE.get(), 0, "关于%s");
        this.addDialogAnswer(TCREntities.AINE.get(), 1, "你已经和那个女人打过招呼啦？她应该就是这里的老大了，看来要解决这里的问题，我们暂时只能按她的话做了。");
        this.addDialogOption(TCREntities.AINE.get(), 1, "关于这个世界");
        this.addDialogAnswer(TCREntities.AINE.get(), 2, "你已经听过那女人讲的故事了吧？但是真是假，还得真正的踏上旅程才知道。来之前可说好了，要靠你自己的力量找回失去的记忆！加油，我会一直在你身边，做你坚强的后盾！");
        this.addDialogOption(TCREntities.AINE.get(), 2, "§a关于时装");
        this.addDialogAnswer(TCREntities.AINE.get(), 3, "看！我这身新衣服！漂亮吧？来到这个世界，也应该换一套新的行头才行！给，你也快换上吧！");
        this.addDialogOption(TCREntities.AINE.get(), 3, "§a领取");
        this.addDialogAnswer(TCREntities.AINE.get(), 4, "%s，有事找我？");
        this.addDialogOption(TCREntities.AINE.get(), 5, "§a关于幻境");
        this.addDialogAnswer(TCREntities.AINE.get(), 5, "幻境（Cloudland）？我找找...");
        this.addDialogAnswer(TCREntities.AINE.get(), 6, "嗯...根据智库记载的资料，和我的经验来看，你所接触祭坛后进入的幻境，应该是使徒（Angel）们原先的精神世界，受到黑潮侵蚀后所融合的结果。");
        this.addDialogOption(TCREntities.AINE.get(), 6, "精神世界？");
        this.addDialogAnswer(TCREntities.AINE.get(), 7, "嗯，或许在幻境深处可以找到使徒（Angel）们的映射，并借此获取它们的力量！");
        this.addDialogAnswer(TCREntities.AINE.get(), 8, "在幻境中探索看看吧！说不定会有不小的收获，也许还可以获取宝贵的忆质材料！");
        this.addDialogOption(TCREntities.AINE.get(), 7, "§a关于[%s§a]");
        this.addDialogAnswer(TCREntities.AINE.get(), 9, "这是...特殊的忆质呢，似乎记载了一个团体的记忆，而不是某个生命的记忆。让我们看看其中有什么秘密吧！");
        this.addDialogAnswer(TCREntities.AINE.get(), 10, "！！");
        this.addDialogAnswer(TCREntities.AINE.get(), 11, "上面记载了可妮莉亚船长(Captain Cornelia)的冒险故事，并且从记忆来看，灵魂使徒(Soul Angel) %s 被黑潮侵蚀后，就附身在可妮莉亚船长(Captain Cornelia)身上！快把这份解读的记忆给%s看看吧！");
        this.addDialogOption(TCREntities.AINE.get(), 8, "%s? %s?");
        this.addDialogAnswer(TCREntities.AINE.get(), 12, "灵魂之使徒，%s，掌管亡者之世界。可妮莉亚船长(Captain Cornelia)的船队则是世界闻名的远洋船队，受到黑潮的诅咒，和[%s]一起永远困在了那片寒冰迷宫！");
        this.addDialogAnswer(TCREntities.AINE.get(), 13, "[%s]？让我看看，又是一份特殊的记忆呢...");
        this.addDialogAnswer(TCREntities.AINE.get(), 14, "哼哼，看来掌管冥界的灵魂使徒，还掌握了生灵法术！");
        this.addDialogAnswer(TCREntities.AINE.get(), 15, "[%s]上带给了我们两个信息，§6其一是，它为我们描绘了世界上大部分受到黑潮侵蚀的魔物！");
        this.addDialogAnswer(TCREntities.AINE.get(), 16, "§6其二是，它上面记载了这个世界上的魔法！哼哼，这里的魔法和我所学的魔法有异曲同工之妙呢！");
        this.addDialogAnswer(TCREntities.AINE.get(), 17, "卷轴描绘了法术源质及其对应的法术，§6将图鉴所描述的法术源质带给我，我将为你转化成对应的法术卷轴");
        this.addDialogAnswer(TCREntities.AINE.get(), 18, "§6将武器交给我，我将把卷轴所记载的法术附魔在武器上！§f我这里刚好有本卷轴，快去试试吧！");
        this.addDialogAnswer(TCREntities.AINE.get(), 19, "%s, 看来你已经学会如何为武器附魔啦！现在本姑娘就教教你如何释放法术！§6好好看，好好学，我只教一遍！");
        this.addDialogAnswer(TCREntities.AINE.get(), 20, "持有附魔的武器时，按下[%s]即可释放对应的法术。怎么样，很简单吧？");
        this.addDialogAnswer(TCREntities.AINE.get(), 21, "使用法术会消耗法力值，法力值耗尽则无法使用法术。§6注意，法力值只能在主城进行回复！ 离开了主城我便无法为你注入魔力。§f不过，还有一件事...");
        this.addDialogAnswer(TCREntities.AINE.get(), 22, "还有一件事！以防你离开了主城无法回复魔力，本姑娘特意为你准备了[%s]！有了[%s]，你就可以随时随地补充魔力值啦！");
        this.addDialogOption(TCREntities.AINE.get(), 9, "§6我已学会");
        this.addDialogOption(TCREntities.AINE.get(), 10, "§a再说一遍");
        this.addDialogAnswer(TCREntities.AINE.get(), 23, "不过，[%s]的使用次数是有限的！当你用尽后，只要在主城里使用，它就会自动回满！踏上魔法的道路吧！救——世——主！");
        this.addDialogAnswer(TCREntities.AINE.get(), 24, "%s，最近冒险还顺利吗？");
        this.addDialogOption(TCREntities.AINE.get(), 11, "轻轻松松！");
        this.addDialogOption(TCREntities.AINE.get(), 12, "累死我了！");
        this.addDialogAnswer(TCREntities.AINE.get(), 25, "本姑娘在这儿研究资料也累坏了，要不是来之前说好了由你自己完成，不然我也想和你一起去冒险呢。");
        this.addDialogOption(TCREntities.AINE.get(), 13, "%s...");
        this.addDialogAnswer(TCREntities.AINE.get(), 26, "嗯？");
        this.addDialogOption(TCREntities.AINE.get(), 14, "我觉得不对劲");
        this.addDialogAnswer(TCREntities.AINE.get(), 27, "怎么啦？");
        this.addDialogOption(TCREntities.AINE.get(), 15, "祂们似乎都认识我");
        this.addDialogAnswer(TCREntities.AINE.get(), 28, "说什么胡话，怎么会有这种事？我看是你最近冒险太累出幻觉了吧，你也该注意多多休息！");
        this.addDialogOption(TCREntities.AINE.get(), 16, "我是认真的");
        this.addDialogOption(TCREntities.AINE.get(), 17, "%s？");
        this.addDialogAnswer(TCREntities.AINE.get(), 29, "好啦好啦，等我们归还了所有的神之眼，一切问题一定都能得到解答的！");
        this.addDialogAnswer(TCREntities.AINE.get(), 30, "嗯...智库里说，注入[%s]，就可以激活它，打开[%s]的传送门！");
        this.addDialogAnswer(TCREntities.AINE.get(), 31, "§6[%s§6]是介于冥界和主世界之间的地带，在[%s§6]内，我们可以借助召唤祭坛，觐见往昔的使徒");
        this.addDialogAnswer(TCREntities.AINE.get(), 32, "或许我们可以进去瞧瞧，说不定可以从记忆中获取祂们的力量！");
        this.addDialogAnswer(TCREntities.AINE.get(), 33, "%s，你看起来似乎有什么心事，在%s发生了什么？");
        this.addDialogOption(TCREntities.AINE.get(), 18, "%s，其实我...");
        this.addDialogAnswer(TCREntities.AINE.get(), 34, "...（你将%s发生的事告诉了%s）");
        this.addDialogAnswer(TCREntities.AINE.get(), 35, "原来，这就是为什么你之前说，祂们似乎都认识你...");
        this.addDialogAnswer(TCREntities.AINE.get(), 36, "不知不觉，只剩下[%s]还未归还了...加油，！");
        this.addDialogAnswer(TCREntities.AINE.get(), 37, "%s，你来了！或者说，应该称呼你为...祂们怎么称乎你的来着？Purus！");
        this.addDialogAnswer(TCREntities.AINE.get(), 38, "治疗结束，有什么想问的尽管问吧！我都会为你解答的！");
        this.addDialogOption(TCREntities.AINE.get(), 19, "关于你");
        this.addDialogOption(TCREntities.AINE.get(), 20, "关于使徒(Angel)");
        this.addDialogOption(TCREntities.AINE.get(), 21, "关于黑潮的真相");
        this.addDialogOption(TCREntities.AINE.get(), 22, "关于未来");
        this.addDialogOption(TCREntities.AINE.get(), 23, "§a我没有想问的了");
        this.addDialogAnswer(TCREntities.AINE.get(), 39, "我是你的主治医师，负责治疗你的精神分裂症。至于我为什么长这副样子，因为我们在你的精神世界，这里的一切都是你想象的样子。");
        this.addDialogAnswer(TCREntities.AINE.get(), 40, "无需担心，你已经亲手杀死了它们，它们永远死在了你的梦中，它们不会再来了。");
        this.addDialogAnswer(TCREntities.AINE.get(), 41, "黑潮实际上是Risperdal的药物作用，我们通过药物控制住各个人格的活动。而你，作为最友善的一个人格，最纯洁的一个人格，我们选择了你，作为突破口。其他人格太难缠了，因此我们只能在你掌控身体的时候，对你使用药物，镇压其他人格。");
        this.addDialogAnswer(TCREntities.AINE.get(), 42, "药物在你的精神世界里就表现为“黑潮”，它替我们束缚住了其他人格。但是，在你的世界里，只有你能够对他们产生实质的影响。于是我们编造了再创世的谎言，引导你一步步地抹除它们。");
        this.addDialogAnswer(TCREntities.AINE.get(), 43, "今后，你可以像正常人一样生活了！当然，只要你想回到这里，随时可以告诉我！");
        this.addDialogAnswer(TCREntities.AINE.get(), 44, "那么，我们一起出去吧！");
        this.addDialogOption(TCREntities.AINE.get(), 24, "去哪里？");
        this.addDialogAnswer(TCREntities.AINE.get(), 45, "回到现实！");
        this.addDialogOption(TCREntities.AINE.get(), 25, "...");
        this.addDialogAnswer(TCREntities.AINE.get(), 46, "怎么啦，%s？");
        this.addDialogAnswer(TCREntities.AINE.get(), 47, "...");
        this.addDialogOption(TCREntities.AINE.get(), 26, "不，我想留在这里");
        this.addDialogAnswer(TCREntities.AINE.get(), 48, "为什么？");
        this.addDialogOption(TCREntities.AINE.get(), 27, "我想...");
        this.addDialogOption(TCREntities.AINE.get(), 28, "永远...");
        this.addDialogOption(TCREntities.AINE.get(), 29 , "和他们在一起...");
        this.addDialogAnswer(TCREntities.AINE.get(), 49, "...我尊重你的选择。等你想回到现实了，随时可以来找我！");

        this.addDialogOption(TCREntities.ORNN.get(), -4, "§6高级锻造");
        this.addDialogOption(TCREntities.ORNN.get(), -3, "§6[解锁新交易选项]");
        this.addDialogOption(TCREntities.ORNN.get(), -2, "结束对话");
        this.addDialogOption(TCREntities.ORNN.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.ORNN.get(), -1, "火焰的温度、钢铁的韧性——这两样东西能解决世上大多数问题。主人，有何吩咐？");
        this.addDialogAnswer(TCREntities.ORNN.get(), 0, "火焰的温度、钢铁的韧性——这两样东西能解决世上大多数问题。");
        this.addDialogOption(TCREntities.ORNN.get(), 0, "你是何人？");
        this.addDialogAnswer(TCREntities.ORNN.get(), 1, "吾乃半神，主掌锻造与工艺，为圣殿铸神兵。黑潮来袭之前，本以为老夫所铸神兵天下无敌。现在想来，可笑，可笑。");
        this.addDialogOption(TCREntities.ORNN.get(), 1, "关于%s");
        this.addDialogAnswer(TCREntities.ORNN.get(), 2, "守望者大人实在令人捉摸不透，但遵从她的安排便是。");
        this.addDialogAnswer(TCREntities.ORNN.get(), 3, "那小姑娘倒比老夫自在，老夫再也没法回到故土了。");
        this.addDialogOption(TCREntities.ORNN.get(), 2, "§6锻造委托");
        this.addDialogOption(TCREntities.ORNN.get(), 3, "§a见面礼");
        this.addDialogAnswer(TCREntities.ORNN.get(), 4, "这是一些边角碎料所铸成的，你暂且拿去防身吧。");
        this.addDialogOption(TCREntities.ORNN.get(), 4, "%s");
        //拿百兵图
        this.addDialogOption(TCREntities.ORNN.get(), 5, "§6展示[%s§6]");
        this.addDialogAnswer(TCREntities.ORNN.get(), 5, "锻造之神在上！这百兵图……记载了世界上所有高阶武器及其铸造之法！甚至还存在我未曾了解的武器！");
        this.addDialogAnswer(TCREntities.ORNN.get(), 6, "想必此百兵图，乃是是黑潮降临前大地使徒Montis大人所铸。");
        this.addDialogAnswer(TCREntities.ORNN.get(), 7, "交给我吧，我将为你展现这幅精美的画卷！");
        this.addDialogOption(TCREntities.ORNN.get(), 6, "§6解锁图鉴");
        //阎魔刀交易
        this.addDialogAnswer(TCREntities.ORNN.get(), 8, "这是，传说中的%s！阁下竟寻回了如此宝具！");
        this.addDialogAnswer(TCREntities.ORNN.get(), 9, "阁下要我助你解除其中的封印？嗯...我确实有解除封印之法，待阁下找足了材料，再来找我吧！");

        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -3, "返回");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -2, "结束对话");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), -1, "你好，主人，有什么可以帮助您的吗？");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 0, "你好，阁下，有什么可以帮助您的吗？");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 0, "你是何人？");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 1, "吾乃圣殿摆渡人，受%s大人之令在此处护送阁下离开结界前往那方世界。吾也会接纳那方世界的亡魂，因此也留下了不少他们散落的饰品遗物。但需使用[%s]才能于原始之海中打捞。若是阁下有%s，也可交予我。");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 1, "关于%s");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 2, "守望者大人日夜为拯救圣殿而操劳，实在令人敬佩。");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 3, "%s锻造之术此间无人可与之比肩，吾也常常将一些珍奇饰品交由他修复。");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 2, "§6饰品提取");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 3, "§a前往主世界");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 4, "§6见面礼");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 4, "一份微薄的礼物，还望阁下笑纳。此灵宠孵化成年之后，可日行万里，希望对阁下主世界之旅有所帮助！§6当阁下将灵宠养大后，再来找我吧。");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 5, "§6选择[%s§6]");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 6, "§6我已将龙养大");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 5, "看来小家伙和阁下相处很愉快呢！这些礼物还请阁下收下！阁下可在图鉴中查看它们的用法。相信小家伙们可以给阁下的战斗带来更多的乐趣！");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 7, "收下");

        this.addDialogOption(ModEntities.BONE_CHIMERA, -1, "返回");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 0, "人类？竟能发现此地，可是共鸣石指引你前来此地？");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 0, "你为何囚禁于此？");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 1, "吾乃大地使徒之坐骑，高塔封印降临之时，大地使徒将吾传送至此，共鸣石得以寻吾之所在。");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 1, "释放灵魂");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 2, "此身携有不死诅咒，你若助我解脱，吾之尸骨可铸利器。准备好了吗？");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 2, "准备好了");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 3, "再等等");

        this.addDialogAnswer(BTEntityType.END_GOLEM, 0, "（此人有极强大的气场，他一言不发）");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 1, "（它还是在那里，不为所动）");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 2, "如果你想要，你得自己来拿。");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 3, "这规矩你早就懂的。");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 4, "我们之间打过多少次架了。");
        this.addDialogAnswer(BTEntityType.END_GOLEM, 5, "（邪魅一笑）");
        this.addDialogOption(BTEntityType.END_GOLEM, 0, "嘿，%s，你动不动就打开传送门的日子结束了！");
        this.addDialogOption(BTEntityType.END_GOLEM, 1, "（叹气）把[%s]给我");
        this.addDialogOption(BTEntityType.END_GOLEM, 2, "继续");
        this.addDialogOption(BTEntityType.END_GOLEM, 3, "我就知道你会这么说（掏出武器）");
        this.addDialogOption(BTEntityType.END_GOLEM, 4, "很难说，这是我从小唯一记得的事。");
        this.addDialogOption(BTEntityType.END_GOLEM, 5, "该做个了解了！一了百了！");

        this.addDialogOption(EntityTypeModule.RIBBIT.get(), -2, "离开");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), -1, "继续");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 0, "呱！人类！");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 0, "呱！");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 1, "咕咕嘎嘎！");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 2, "§6交易");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 3, "§6关于[%s§6]");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 4, "§6提交[%s§6]");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 5, "§6收下");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 1, "想了解更多的话，带好多好多紫水晶块来换呱！要12个呱！");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 2, "呱！成交呱！");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 3, "呱，打发叫花子呱！我要12个呱！");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 4, "我们原是海洋村庄的人类，受到黑潮诅咒而变成这副模样呱。");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 5, "海洋高塔十分危险呱，念在紫水晶块的份上，这些宝具你暂且带着防身呱！");

        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), -2, "返回");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 0, "Purus Absolutus！我想起来了，我全都想起来了！");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 0, "Purus Absolutus？");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 1, "究竟是什么意思？");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 1, "你不记得了吗？Purus Absolutus！");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 2, "我？");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 3, "我的名字？");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 2, "看来你也受黑潮侵蚀，失去了记忆...没错，你，Purus Absolutus，纯净之使徒！");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 4, "？？？");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 5, "这一切是怎么回事？");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 3, "吾将神性划入[%s]之中，以免受黑潮之侵蚀。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 4, "共鸣石指引你取回了[%s]，才能将我的记忆补全！Purus Absolutus，你当真什么都不记得了吗？");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 6, "抱歉");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 7, "我什么都不记得了");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 5, "这样啊...在帮助你找回记忆之前，请告诉我都发生了什么，你为何来到这里？");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 8, "...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 6, "...（你将之前发生的一切告诉了[%s]）");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 7, "原来如此...%s真的坚持到了最后一刻...没想到你最终竟会和魔女一同归来拯救世界。好吧，关于过去的一切，你想知道些什么，我尽可能地回答你。");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 9, "我到底是谁？");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 10, "黑潮到底是什么？");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 11, "§6我没有想问的了");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 8, "你名为Purus Absolutus，乃纯净之使徒，负责净化世间黑暗，与我们同为尘世十执政。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 9, "千年以前，恰好轮到你出面解决天外的事务，但在你离去后不久，黑潮便降临世界。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 10, "也许是失去了来自你的净化的神力，我们无力抵抗黑潮，只能任由黑潮侵蚀我们。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 11, "我不知道。黑潮刚刚降临时，我们对黑潮做了大量的研究，但毫无进展。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 12, "黑潮本是代号Risperdal的一种介质，我们无法具体感知其存在。但受它影响的生灵皆逐渐发黑而死，因此我们将其命名为黑潮。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 13, "我们只知道黑潮来自天外的世界，但黑潮的目标似乎很明确，总是针对以我们使徒为首的生命。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 14, "也许，由你来带领我们进行再创世，是我们文明延续的唯一希望了。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 15, "也就是说，现在需要代表我神性的神之眼，来协助完成仪式是吧。但神之眼的剥离，意味着我将放弃身为人的肉体。");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 16, "也罢，现在的我，黑潮缠身，早已不是我原初的身躯...我离开了神之眼的力量，便无法再维持人形...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 17, "...也许这就是我们的宿命吧。");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 12, "不要这样...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 18, "...明天见，Purus Absolutus！愿我们能在约定的新世界相遇！");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 13, "§4处决");

        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 0, "往日种种，你当真不记得了吗？");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 0, "§4处决");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 1, "往日种种...");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 2, "你说的可是往日...");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 1, "吾将神性铸成%s，才避免了黑潮之侵蚀，虽然吾之神躯早已成为腐朽的傀儡...");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 3, "继续");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 2, "方才你释放吾之神躯之时，我便取回了自己的部分力量。Purus，这把兵刃乃是当初你以净化之力炼成。");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 3, "可惜黑潮侵蚀，仅剩残胚。拿去吧，愿它能助你扫荡最后的黑潮！");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 4, "收下吧，[%s]，我将在世界之尽头等你！");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 4, "...");

    }
}
