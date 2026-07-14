package com.p1nero.tcrcore.events;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.almostreliable.summoningrituals.Registration;
import com.almostreliable.summoningrituals.altar.AltarRenderer;
import com.ao.tcrmeshes.TCRMeshes;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.hm.efn.registries.EFNItem;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.block.TCRBlocks;
import com.p1nero.tcrcore.block.client.AltarBlockRenderer;
import com.p1nero.tcrcore.block.entity.TCRBlockEntities;
import com.p1nero.tcrcore.client.BookOfDragonCameraManager;
import com.p1nero.tcrcore.client.gui.BTSpawnerBlockIndicatorRenderer;
import com.p1nero.tcrcore.client.gui.BlockTooltipHandler;
import com.p1nero.tcrcore.client.gui.CustomQuestOverlayRenderer;
import com.p1nero.tcrcore.client.item_renderer.RenderDualBokken;
import com.p1nero.tcrcore.client.item_renderer.RenderTheIncinerator;
import com.p1nero.tcrcore.client.ponder.TCRPonderPlugin;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.entity.custom.aine_iris.AineIrisRenderer;
import com.p1nero.tcrcore.entity.custom.boss_rush.BossRushManagerRenderer;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_boss.FakeBossNpcRenderer;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_end_golem.FakeEndGolemRenderer;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_sky_golem.FakeSkyGolemRenderer;
import com.p1nero.tcrcore.entity.custom.ferry_girl.FerryGirlGeoRenderer;
import com.p1nero.tcrcore.entity.custom.chronos_sol.ChronosSolGeoRenderer;
import com.p1nero.tcrcore.entity.custom.mimic.PTCRMimicRenderer;
import com.p1nero.tcrcore.entity.custom.mimic.TCRMimicRenderer;
import com.p1nero.tcrcore.entity.custom.ornn.OrnnlGeoRenderer;
import com.p1nero.tcrcore.entity.custom.tutorial_golem.TutorialGolemRenderer;
import com.p1nero.tcrcore.entity.custom.tutorial_humanoid.TutorialHumanoidRenderer;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.violetmoon.quark.content.mobs.module.ForgottenModule;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.client.event.EpicFightClientHooks;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.api.client.model.Meshes;
import yesman.epicfight.client.mesh.HumanoidMesh;
import yesman.epicfight.client.renderer.patched.entity.PHumanoidRenderer;
import yesman.epicfight.client.renderer.patched.entity.PIronGolemRenderer;
import yesman.epicfight.client.renderer.patched.entity.PresetRenderer;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            checkFancyMenuLogo();

            EntityRenderers.register(TCREntities.CUSTOM_COLOR_ITEM.get(), ItemEntityRenderer::new);

            EntityRenderers.register(TCREntities.CHRONOS_SOL.get(), ChronosSolGeoRenderer::new);
            EntityRenderers.register(TCREntities.FERRY_GIRL.get(), FerryGirlGeoRenderer::new);
            EntityRenderers.register(TCREntities.ORNN.get(), OrnnlGeoRenderer::new);
            EntityRenderers.register(TCREntities.AINE.get(), AineIrisRenderer::new);
            EntityRenderers.register(TCREntities.FAKE_SKY_GOLEM.get(), FakeSkyGolemRenderer::new);
            EntityRenderers.register(TCREntities.FAKE_END_GOLEM.get(), FakeEndGolemRenderer::new);
            EntityRenderers.register(TCREntities.TUTORIAL_GOLEM.get(), TutorialGolemRenderer::new);
            EntityRenderers.register(TCREntities.TUTORIAL_HUMANOID.get(), TutorialHumanoidRenderer::new);
            EntityRenderers.register(TCREntities.BOSS_RUSH_MANAGER.get(), BossRushManagerRenderer::new);

            EntityRenderers.register(TCREntities.TCR_MIMIC.get(), TCRMimicRenderer::new);

            EntityRenderers.register(TCREntities.FAKE_MALEDICTUS_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.MALEDICTUS_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_IGNIS_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.IGNIS_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_NETHERITE_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.NETHERITE_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_SCYLLA_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.SCYLLA_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_ENDER_GUARDIAN_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.ENDER_GUARDIAN_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_HARBINGER_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.HARBINGER_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_LEVIATHAN_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.LEVIATHAN_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_ANCIENT_REMNANT_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.ANCIENT_REMAIN_HUMANOID_TEXTURE));

            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.ABYSS_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.THE_LEVIATHAN.get().getDescription().copy().withStyle(ChatFormatting.BLUE), ModItems.TIDAL_CLAWS.get().getDescription().copy().withStyle(ChatFormatting.GOLD)),
                    TCRCoreMod.getInfo("related_loot", ModEntities.CORALSSUS.get().getDescription(), EFNItem.NF_CLAW.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.CURSED_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.MALEDICTUS.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN), ModItems.SOUL_RENDER.get().getDescription().copy().withStyle(ChatFormatting.GOLD)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.DESERT_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.ANCIENT_REMNANT.get().getDescription().copy().withStyle(ChatFormatting.YELLOW), ModItems.WRATH_OF_THE_DESERT.get().getDescription().copy().withStyle(ChatFormatting.GOLD)),
                    TCRCoreMod.getInfo("related_loot", ModEntities.KOBOLEDIATOR.get().getDescription(), EFNItem.EXSILIUMGLADIUS.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.FLAME_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.IGNIS.get().getDescription().copy().withStyle(ChatFormatting.RED), ModItems.THE_INCINERATOR.get().getDescription().copy().withStyle(ChatFormatting.GOLD)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.STORM_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.SCYLLA.get().getDescription().copy().withStyle(ChatFormatting.AQUA), ModItems.CERAUNUS.get().getDescription().copy().withStyle(ChatFormatting.GOLD)));

            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.VOID_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.ENDER_GUARDIAN.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE), ModItems.GAUNTLET_OF_GUARD.get().getDescription().copy().withStyle(ChatFormatting.GOLD)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.MECH_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.THE_HARBINGER.get().getDescription().copy().withStyle(ChatFormatting.RED), "???"));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.MONST_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.NETHERITE_MONSTROSITY.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED), ModItems.INFERNAL_FORGE.get().getDescription().copy().withStyle(ChatFormatting.GOLD)));

            PonderIndex.addPlugin(new TCRPonderPlugin());

            //借大小来判断是否启动过
//            if(ClientConfig.miningPreferredItems != null && ClientConfig.combatPreferredItems != null) {
//                if(ClientConfig.miningPreferredItems.size() < 10) {
//                    ForgeRegistries.ITEMS.getValues().forEach(item -> {
//                        if (!ClientConfig.combatPreferredItems.contains(item)) {
//                            ClientConfig.miningPreferredItems.add(item);
//                        }
//                    });
//                }
//            } else {
//                TCRCoreMod.LOGGER.error("[TCRCore]: Preferred items is null!!!");
//            }

            EpicFightClientHooks.Camera.ACTIVATE_TPS_CAMERA.registerEvent(BookOfDragonCameraManager::onEpicFightCameraSetup);

            ClientForgeEvents.initSamsaraTip();
        });
    }

    @SubscribeEvent
    public static void onRenderPatched(PatchedRenderersEvent.Add event) {
        EntityRendererProvider.Context context = event.getContext();
        event.addPatchedEntityRenderer(TCREntities.TUTORIAL_GOLEM.get(), (entityType) -> new PIronGolemRenderer(context, entityType).initLayerLast(context, entityType));
        event.addPatchedEntityRenderer(TCREntities.TUTORIAL_HUMANOID.get(), (entityType) -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType){
            @Override
            public AssetAccessor<HumanoidMesh> getDefaultMesh() {
                if(Minecraft.getInstance().player == null) {
                    return Meshes.BIPED;
                }
                return Minecraft.getInstance().player.getModelName().equals("slim") ? Meshes.ALEX : Meshes.BIPED;
            }
        }.initLayerLast(context, entityType));
        event.addPatchedEntityRenderer(EntityType.DROWNED, (entityType -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType)));
        //雷霆写法
        event.addPatchedEntityRenderer(EntityRegistry.SUMMONED_SKELETON.get(), (entityType -> {
            LivingEntityRenderer renderer = ((LivingEntityRenderer) context.getEntityRenderDispatcher().renderers.get(EntityType.SKELETON));
            return new PresetRenderer(context, entityType, renderer, ((AssetAccessor) Meshes.SKELETON));
        }));
        event.addPatchedEntityRenderer(EntityRegistry.SUMMONED_ZOMBIE.get(), (entityType -> {
            LivingEntityRenderer renderer = ((LivingEntityRenderer) context.getEntityRenderDispatcher().renderers.get(EntityType.ZOMBIE));
            return new PresetRenderer(context, entityType, renderer, ((AssetAccessor) Meshes.BIPED_OLD_TEX));
        }));
        event.addPatchedEntityRenderer(ForgottenModule.forgottenType, (entityType -> new PHumanoidRenderer<>(Meshes.SKELETON, context, entityType)));
        event.addPatchedEntityRenderer(TCRBossEntities.CITADEL_KEEPER.get(), (entityType -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType)));
        event.addPatchedEntityRenderer(AetherEntityTypes.VALKYRIE.get(), (entityType -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_SKY_GOLEM.get(), (entityType -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType)));
        event.addPatchedEntityRenderer(TCREntities.FAKE_END_GOLEM.get(), (entityType -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.TCR_MIMIC.get(), (entityType -> new PTCRMimicRenderer(Meshes.BIPED_OLD_TEX, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_MALEDICTUS_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.MALEDICTUS_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_IGNIS_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.IGNIS_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_NETHERITE_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.NETHERITE_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_SCYLLA_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.SCYLLA_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_ENDER_GUARDIAN_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.ENDER_GUARDIAN_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_HARBINGER_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.HARBINGER_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_LEVIATHAN_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.LEVIATHAN_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_ANCIENT_REMNANT_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.ANCIENT_REMAIN_HUMANOID, context, entityType)));
    }

    @SubscribeEvent
    public static void onRendererSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TCRBlockEntities.ABYSS_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.DESERT_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.STORM_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.FLAME_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.CURSED_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.MONST_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.VOID_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.MECH_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);

        event.registerBlockEntityRenderer(Registration.ALTAR_ENTITY.get(), AltarRenderer::new);
    }

    @SubscribeEvent
    public static void registerItemRenderer(PatchedRenderersEvent.RegisterItemRenderer event) {
        event.addItemRenderer(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "dual_bokken"), RenderDualBokken::new);
        event.addItemRenderer(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "incinerator"), RenderTheIncinerator::new);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("tcr_quest", new CustomQuestOverlayRenderer());
        event.registerAboveAll("tcr_bt_spawner_indicator", new BTSpawnerBlockIndicatorRenderer());
    }

    /**
     * 检测 fancymenu 的 logo32x.png 是否存在且完整，防止材质包被篡改
     */
    private static void checkFancyMenuLogo() {
        // 只在开发环境跳过检查
        if (!FMLLoader.isProduction()) {
            return;
        }
        File gameDir = Minecraft.getInstance().gameDirectory;
        File logoFile = new File(gameDir, "config/fancymenu/assets/logo32x.png");
        String expectedHash = "e0b1a04e6c1473eb3e9c6275aecc738fde9ab42e3047fe48515781a8526eb8d3";

        if (!logoFile.exists()) {
            throw new IllegalStateException(
                    "[The Casket of Reveries] Required file missing: config/fancymenu/assets/logo32x.png\n" +
                            "Please reinstall the modpack."
            );
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(Files.readAllBytes(logoFile.toPath()));
            String fileHash = bytesToHex(hash);

            if (!expectedHash.equals(fileHash)) {
                throw new IllegalStateException(
                        "[The Casket of Reveries] File integrity check failed: config/fancymenu/assets/logo32x.png\n" +
                                "Expected: " + expectedHash + "\n" +
                                "Got: " + fileHash + "\n" +
                                "Please restore the original file from the modpack."
                );
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException("[The Casket of Reveries] Failed to verify logo32x.png integrity", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
