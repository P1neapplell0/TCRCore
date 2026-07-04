package com.p1nero.tcrcore.client.ponder;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.item.TCRItems;
import net.createmod.catnip.platform.ForgeRegisteredObjectsHelper;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.arc.epic_ponder.client.ponder.EFPPonderPlugin;
import org.jetbrains.annotations.NotNull;

public class TCRPonderPlugin implements PonderPlugin {
    @Override
    public @NotNull String getModId() {
        return TCRCoreMod.MOD_ID;
    }

    @Override
    public void registerScenes(@NotNull PonderSceneRegistrationHelper<ResourceLocation> helper) {
        ForgeRegisteredObjectsHelper forgeRegisteredObjectsHelper = new ForgeRegisteredObjectsHelper();
        PonderSceneRegistrationHelper<Item> entryHelper = helper.withKeyFunction(forgeRegisteredObjectsHelper::getKeyOrThrow);
        entryHelper.forComponents(TCRItems.WITHER_SOUL_STONE.get(), TCRItems.WITHER_SOUL_STONE_ACTIVATED.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addHarbingerScene)
                .addStoryBoard("altar", TCRBossesPonderScene::addScyllaScene)
                .addStoryBoard("altar", TCRBossesPonderScene::addNetheriteScene)
                .addStoryBoard("altar", TCRBossesPonderScene::addMaledictusScene)
                .addStoryBoard("altar", TCRBossesPonderScene::addAncientRemainScene)
                .addStoryBoard("altar", TCRBossesPonderScene::addEnderGuardianScene)
                .addStoryBoard("altar", TCRBossesPonderScene::addIgnisScene)
                .addStoryBoard("altar", TCRBossesPonderScene::addLeviathanScene);
        entryHelper.forComponents(ModItems.ESSENCE_OF_THE_STORM.get(), ModItems.LACRIMA.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addScyllaScene);
        entryHelper.forComponents(ModItems.IGNITIUM_INGOT.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addIgnisScene);
        entryHelper.forComponents(ModItems.CURSIUM_INGOT.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addMaledictusScene);
        entryHelper.forComponents(ModItems.MONSTROUS_HORN.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addNetheriteScene);
        entryHelper.forComponents(ModItems.NECKLACE_OF_THE_DESERT.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addAncientRemainScene);
        entryHelper.forComponents(TCRItems.VOID_CORE.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addEnderGuardianScene);
        entryHelper.forComponents(ModItems.WITHERITE_INGOT.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addHarbingerScene);
        entryHelper.forComponents(TCRItems.ABYSS_CORE.get())
                .addStoryBoard("altar", TCRBossesPonderScene::addLeviathanScene);
        EFPPonderPlugin.registerWeapon(helper, "tcrcore:falchion",
                TCRWeaponScenes::showcaseFalchionBasicAttackCombo,
                TCRWeaponScenes::showcaseFalchionArt);
    }

}
