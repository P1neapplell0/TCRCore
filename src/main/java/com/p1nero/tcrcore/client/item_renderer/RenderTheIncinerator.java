package com.p1nero.tcrcore.client.item_renderer;

import com.google.gson.JsonElement;
import com.mojang.blaze3d.vertex.PoseStack;
import com.p1nero.tcrcore.effect.TCREffects;
import com.p1nero.tcrcore.item.TCRItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.renderer.patched.item.RenderItemBase;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class RenderTheIncinerator extends RenderItemBase {
    private final ItemStack blue;
    public RenderTheIncinerator(JsonElement jsonElement) {
        super(jsonElement);
        blue = TCRItems.THE_INCINERATOR_SOUL.get().getDefaultInstance();
    }

    @Override
    public void renderItemInHand(ItemStack stack, LivingEntityPatch<?> entityPatch, InteractionHand hand, OpenMatrix4f[] poses, MultiBufferSource buffer, PoseStack poseStack, int packedLight, float partialTicks) {
        ItemStack toUse = entityPatch.getOriginal().hasEffect(TCREffects.SOUL_INCINERATOR.get()) ? blue : stack;
        super.renderItemInHand(toUse, entityPatch, hand, poses, buffer, poseStack, packedLight, partialTicks);
    }
}
