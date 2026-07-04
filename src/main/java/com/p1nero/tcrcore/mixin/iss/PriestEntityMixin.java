package com.p1nero.tcrcore.mixin.iss;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.SupportMob;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.NeutralWizard;
import io.redspace.ironsspellbooks.entity.mobs.goals.HomeOwner;
import io.redspace.ironsspellbooks.entity.mobs.wizards.IMerchantWizard;
import io.redspace.ironsspellbooks.entity.mobs.wizards.priest.PriestEntity;
import io.redspace.ironsspellbooks.item.FurledMapItem;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

/**
 * 删除村民圣经交易
 */
@Mixin(PriestEntity.class)
public abstract class PriestEntityMixin extends NeutralWizard implements VillagerDataHolder, SupportMob, HomeOwner, IMerchantWizard {

    protected PriestEntityMixin(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Shadow(remap = false) @Nullable protected MerchantOffers offers;

    @Shadow(remap = false) public abstract void setLastRestockGameTime(long time);

    @Inject(method = {"getOffers", "m_6616_"}, at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$getOffers(CallbackInfoReturnable<MerchantOffers> cir) {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 24), ItemStack.EMPTY, FurledMapItem.of(IronsSpellbooks.id("evoker_fort"), FurledMapItem.OVERWORLD, Component.translatable("item.irons_spellbooks.evoker_fort_battle_plans")), 0, 1, 5, 10.0F));
            this.offers.add(new MerchantOffer(new ItemStack(ItemRegistry.GREATER_HEALING_POTION.get()), new ItemStack(Items.EMERALD, 18), 3, 0, 0.2F));
            this.offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 6), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HEALING), 2, 0, 0.2F));
            this.offers.removeIf(Objects::isNull);
            this.setLastRestockGameTime(this.level().getGameTime());
        }
        this.offers.removeIf((merchantOffer -> merchantOffer.getResult().is(ItemRegistry.VILLAGER_SPELL_BOOK.get())));

        cir.setReturnValue(this.offers);
    }

}
