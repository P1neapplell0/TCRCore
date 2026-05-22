package com.p1nero.tcrcore;

import artifacts.registry.ModItems;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.bobmowzie.mowziesmobs.server.entity.EntityHandler;
import com.brass_amber.ba_bt.init.BTEntityType;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.mojang.logging.LogUtils;
import com.p1nero.p1nero_ec.PEpicCataclysmMod;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.block.TCRBlocks;
import com.p1nero.tcrcore.block.entity.TCRBlockEntities;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.client.sound.TCRSounds;
import com.p1nero.tcrcore.effect.TCREffects;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.events.LivingEntityEventListeners;
import com.p1nero.tcrcore.events.PlayerEventListeners;
import com.p1nero.tcrcore.gameassets.TCRSkillSlots;
import com.p1nero.tcrcore.gamerule.TCRGameRules;
import com.p1nero.tcrcore.item.TCRItemTabs;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.utils.ItemUtils;
import com.p1nero.tcrcore.utils.WorldUtils;
import com.p1nero.tcrcore.worldgen.TCRStructures;
import com.wintercogs.beyonddimensions.common.init.BDItems;
import dev.ftb.mods.ftbteams.api.event.TeamEvent;
import net.genzyuro.uniqueaccessories.registry.UAItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import yesman.epicfight.skill.SkillSlot;

import java.util.List;
import java.util.Locale;

@Mod(TCRCoreMod.MOD_ID)
public class TCRCoreMod {

    public static final String MOD_ID = "tcrcore";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static boolean coolMod = false;
    private static boolean worldEditLoaded;
    private static boolean xaeroMapLoaded;
    private static boolean journeyMapLoaded;
    private static boolean epicParCoolLoaded;

    public TCRCoreMod(FMLJavaModLoadingContext context) {
        SkillSlot.ENUM_MANAGER.registerEnumCls(TCRCoreMod.MOD_ID, TCRSkillSlots.class);
        IEventBus bus = context.getModEventBus();
        bus.addListener(this::commonSetup);
        bus.addListener(this::addPackFindersEvent);
        TCRSounds.REGISTRY.register(bus);
        TCREntities.REGISTRY.register(bus);
        TCRBlocks.REGISTRY.register(bus);
        TCRBlockEntities.REGISTRY.register(bus);
        TCRItems.REGISTRY.register(bus);
        TCRItemTabs.TABS.register(bus);
        TCREffects.REGISTRY.register(bus);
        TCRStructures.STRUCTURE_TYPES.register(bus);
        TCRStructures.STRUCTURE_PIECE.register(bus);
        TCRGameRules.register();
        context.registerConfig(ModConfig.Type.CLIENT, TCRClientConfig.SPEC);
        if(FMLEnvironment.dist.isDedicatedServer() && ModList.get().isLoaded("aaa_particles")) {
            throw new IllegalStateException("[The Casket of Reveries]: AAA Particles mod is detected on server side. Please remove it or use our official server pack on curseforge additional file otherwise it will crash.");
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            worldEditLoaded = ModList.get().isLoaded("worldedit");
            xaeroMapLoaded = ModList.get().isLoaded("xaerominimap");
            journeyMapLoaded = ModList.get().isLoaded("journeymap");
            epicParCoolLoaded = ModList.get().isLoaded("epicparcool");
            TCRPacketHandler.register();
            TCRQuestManager.init();
            List<String> coolModList = List.of("apotheosis");
            coolModList.forEach(s -> {
                if (ModList.get().isLoaded(s)) {
                    coolMod = true;
                }
            });
            PlayerEventListeners.illegalItems.add(UAItems.STARVED_WOLF_SKULL.get());
            PlayerEventListeners.illegalItems.add(artifacts.registry.ModItems.VAMPIRIC_GLOVE.get());
            PlayerEventListeners.illegalItems.add(UAItems.BURNING_SOUL.get());
            PlayerEventListeners.illegalItems.add(artifacts.registry.ModItems.SCARF_OF_INVISIBILITY.get());
            PlayerEventListeners.illegalItems.add(ModItems.CROSS_NECKLACE.get());//可能导致招架bug
            PlayerEventListeners.illegalItems.add(BDItems.NET_FEEDER_ITEM.get());
            if (ModList.get().isLoaded("create")) {
                Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("create:crafting_blueprint"));
                if (item != null) {
                    PlayerEventListeners.illegalItems.add(item);
                }
            }

            LivingEntityEventListeners.illegalEntityTypes.addAll(List.of(
                    BTEntityType.BT_CULTIST.get(),
                    AetherEntityTypes.FIRE_MINION.get(),
                    EntityHandler.NAGA.get(),
                    ModEntities.URCHINKIN.get(),
                    ModEntities.KOBOLETON.get(),
                    ModEntities.ELITE_DRAUGR.get(),
                    ModEntities.ROYAL_DRAUGR.get(),
                    ModEntities.DRAUGR.get(),
                    ModEntities.LIONFISH.get(),
                    ModEntities.DEEPLING_BRUTE.get(),
                    ModEntities.DEEPLING.get()
            ));

            ItemUtils.initAdditionalInfoItems();

            PEpicCataclysmMod.astrapeLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.SCYLLA_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.RED);
            PEpicCataclysmMod.ceraunusLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.SCYLLA_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.RED);

            PEpicCataclysmMod.dualAnnihilatorLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.MALEDICTUS_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN)).withStyle(ChatFormatting.RED);
            PEpicCataclysmMod.soulRenderLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.MALEDICTUS_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN)).withStyle(ChatFormatting.RED);

            PEpicCataclysmMod.gauntletOfGuardLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.DARK_PURPLE)).withStyle(ChatFormatting.RED);

            PEpicCataclysmMod.infernalForgeLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.NETHERITE_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.RED);

            PEpicCataclysmMod.tidalClawLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.LEVIATHAN_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE)).withStyle(ChatFormatting.RED);

            PEpicCataclysmMod.wrathOfTheDesertLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.RED);

            PEpicCataclysmMod.theIncineratorLock = TCRCoreMod.getInfo("pec_weapon_lock", WorldUtils.SAMSARA_NAME,
                    TCRBossEntities.IGNIS_HUMANOID.get().getDescription().copy().withStyle(ChatFormatting.RED)).withStyle(ChatFormatting.RED);

            TeamEvent.PLAYER_JOINED_PARTY.register(PlayerEventListeners::onPlayerTeamChanged);
            TeamEvent.CREATED.register(PlayerEventListeners::onTeamCreated);
        });
    }

    public static boolean hasCoolMod() {
        return coolMod;
    }

    private void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            String name = "i18n";
            var resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("packs/" + name);
            var pack = Pack.readMetaAndCreate(name, TCRCoreMod.getInfo("i18n_pack"), true,
                    (path) -> new PathPackResources(path, resourcePath, false), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
        }

        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            String name = "zh_cn_patch";
            var resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("packs/" + name);
            var pack = Pack.readMetaAndCreate(name, Component.literal("Chinese Patch"), true,
                    (path) -> new PathPackResources(path, resourcePath, false), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
        }

        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            String name = "tcr_assets";
            var resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("packs/" + name);
            var pack = Pack.readMetaAndCreate(name, Component.literal("The Casket of Reveries Assets - Override"), true,
                    (path) -> new PathPackResources(path, resourcePath, false), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
        }

        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            String name = "custom_models";
            var resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("packs/" + name);
            var pack = Pack.readMetaAndCreate(name, TCRCoreMod.getInfo("more_beautiful_models_and_trail"), false,
                    (path) -> new PathPackResources(path, resourcePath, false), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
        }

        //idas为可选
        if (event.getPackType() == PackType.SERVER_DATA && ModList.get().isLoaded("idas")) {
            String name = "idas_modify";
            var resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("packs/" + name);
            var pack = Pack.readMetaAndCreate(name, Component.literal("The Casket of Reveries Data - IDAS Override"), true,
                    (path) -> new PathPackResources(path, resourcePath, false), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.WORLD);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
        }

        if (event.getPackType() == PackType.SERVER_DATA) {
            String name = "tcr_data";
            var resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("packs/" + name);
            var pack = Pack.readMetaAndCreate(name, Component.literal("The Casket of Reveries Data - Override"), true,
                    (path) -> new PathPackResources(path, resourcePath, false), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.WORLD);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
        }

        if (event.getPackType() == PackType.SERVER_DATA) {
            String name = "structure_set_control";
            var resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("packs/" + name);
            var pack = Pack.readMetaAndCreate(name, Component.literal("The Casket of Reveries Structure Sets - Override"), true,
                    (path) -> new PathPackResources(path, resourcePath, false), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.WORLD);
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));

        }
    }

    public static boolean isWorldEditLoad() {
        return worldEditLoaded;
    }

    /**
     * 做成联动，低配机需移除
     */
    public static boolean isXaeroMapLoaded() {
        return xaeroMapLoaded;
    }

    public static boolean isJourneyMapLoaded() {
        return journeyMapLoaded;
    }

    public static boolean isEpicParCoolLoaded() {
        return epicParCoolLoaded;
    }

    public static MutableComponent getInfo(String key) {
        return Component.translatable("info.tcr." + key);
    }

    public static String getInfoKey(String key) {
        return "info.tcr." + key;
    }

    public static MutableComponent getInfo(String key, Object... objects) {
        return Component.translatable("info.tcr." + key, objects);
    }

    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name.toLowerCase(Locale.ROOT));
    }

}
