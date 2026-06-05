package com.p1nero.tcrcore.item;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.block.TCRBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TCRItemTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TCRCoreMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WEAPONS = TABS.register("weapons",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tcr.weapons"))
                    .icon(() -> new ItemStack(TCRItems.GLACIS_JADAE.get()))
                    .withTabsAfter(CreativeModeTabs.BUILDING_BLOCKS)
                    .displayItems((params, output) -> {
                        output.accept(TCRItems.EMBERFANG.get());
                        output.accept(TCRItems.MAGMAHEART.get());
                        output.accept(TCRItems.CINDERWYRM.get());
                        output.accept(TCRItems.PURGING_SWALLOW.get());
                        output.accept(TCRItems.ASHEN_CRESCENT.get());
                        output.accept(TCRItems.LUX_JADAE.get());
                        output.accept(TCRItems.GLACIS_JADAE.get());
                        output.accept(TCRItems.MONS_JADAE.get());
                        output.accept(TCRItems.IRIS_JADAE.get());
                        output.accept(TCRItems.MAGIC_DAGGER.get());
                        output.accept(TCRItems.MAGIC_AXE.get());
                        output.accept(TCRItems.MAGIC_TACHI.get());
                        output.accept(TCRItems.MAGIC_KATANA.get());
                        output.accept(TCRItems.MAGIC_HALBERD.get());
                        output.accept(TCRItems.MAGIC_SWORD.get());
                        output.accept(TCRItems.MAGIC_LONGSWORD.get());
                        output.accept(TCRItems.MAGIC_GREATSWORD.get());
                        output.accept(TCRItems.MAGIC_SPEAR.get());
                    }).build());


    public static final RegistryObject<CreativeModeTab> ITEMS = TABS.register("items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tcr.items"))
                    .icon(() -> new ItemStack(TCRItems.ANCIENT_ORACLE_FRAGMENT.get()))
                    .withTabsAfter(WEAPONS.getId())
                    .displayItems((params, output) -> {
                        output.accept(TCRBlocks.CURSED_ALTAR_BLOCK.get());
                        output.accept(TCRBlocks.ABYSS_ALTAR_BLOCK.get());
                        output.accept(TCRBlocks.DESERT_ALTAR_BLOCK.get());
                        output.accept(TCRBlocks.FLAME_ALTAR_BLOCK.get());
                        output.accept(TCRBlocks.STORM_ALTAR_BLOCK.get());
                        output.accept(TCRBlocks.VOID_ALTAR_BLOCK.get());
                        output.accept(TCRBlocks.MECH_ALTAR_BLOCK.get());
                        output.accept(TCRBlocks.MONST_ALTAR_BLOCK.get());
                        output.accept(TCRItems.ANCIENT_ORACLE_FRAGMENT.get());
                        output.accept(TCRItems.ARTIFACT_TICKET.get());
                        output.accept(TCRItems.RARE_ARTIFACT_TICKET.get());
                        output.accept(TCRItems.VOID_CORE.get());
                        output.accept(TCRItems.ABYSS_CORE.get());
                        output.accept(TCRItems.PROOF_OF_ADVENTURE.get());
                        output.accept(TCRItems.PROOF_OF_ADVENTURE_PLUS.get());
                        output.accept(TCRItems.PROOF_OF_BOSS_RUSH.get());
                        output.accept(TCRItems.DRAGON_FLUTE.get());
                        output.accept(TCRItems.CORE_FLINT.get());
                        output.accept(TCRItems.LAND_RESONANCE_STONE.get());
                        output.accept(TCRItems.OCEAN_RESONANCE_STONE.get());
                        output.accept(TCRItems.CURSED_RESONANCE_STONE.get());
                        output.accept(TCRItems.CORE_RESONANCE_STONE.get());
                        output.accept(TCRItems.NETHER_RESONANCE_STONE.get());
                        output.accept(TCRItems.SKY_RESONANCE_STONE.get());
                        output.accept(TCRItems.END_RESONANCE_STONE.get());
                        output.accept(TCRItems.MYSTERIOUS_WEAPONS.get());
                        output.accept(TCRItems.NECROMANCY_SCROLL.get());
                        output.accept(TCRItems.MAGIC_BOTTLE.get());
                        output.accept(TCRItems.WITHER_SOUL_STONE.get());
                        output.accept(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get());
                        output.accept(TCRItems.BOSS_RUSH_MANAGER.get());
                        output.accept(TCRItems.DIVINE_FRAGMENT.get());
                        output.accept(TCRItems.ABYSS_FRAGMENT.get());
                        output.accept(TCRItems.DESERT_FRAGMENT.get());
                        output.accept(TCRItems.ENDER_FRAGMENT.get());
                        output.accept(TCRItems.STORM_FRAGMENT.get());
                        output.accept(TCRItems.SOUL_FRAGMENT.get());
                        output.accept(TCRItems.FLAME_FRAGMENT.get());
                        output.accept(TCRItems.MECH_FRAGMENT.get());
                        output.accept(TCRItems.NETHERITE_FRAGMENT.get());
                        output.accept(TCRItems.STONE_OF_REINCARNATION.get());
                        output.accept(TCRItems.RESET_SKILL_STONE.get());
                        output.accept(TCRItems.RETRACEMENT_STONE.get());
                        output.accept(TCRItems.BLOOD_LOTUS.get());
                        output.accept(TCRItems.NINE_HEAVEN_DARKSTEEL.get());
                    }).build());
}
