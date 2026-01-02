package com.p1nero.tcrcore.mixin;

import com.github.L_Ender.cataclysm.client.model.item.Incinerator_Model;
import com.github.L_Ender.cataclysm.client.render.CMItemstackRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.item.TCRItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CMItemstackRenderer.class)
public class CMItemStackRendererMixin {

    @Shadow(remap = false)
    @Final
    private static Incinerator_Model THE_INCINERATOR_MODEL;

    @Unique
    private static final ResourceLocation THE_INCINERATOR_TEXTURE = ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/item/the_incinerator_soul.png");

    @Inject(method = "renderByItem", at = @At("HEAD"))
    private void tcr$render(ItemStack itemStackIn, ItemDisplayContext transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn, CallbackInfo ci){
        if (itemStackIn.getItem() == TCRItems.THE_INCINERATOR_SOUL.get()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5F, 0.5F, 0.5F);
            matrixStackIn.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(THE_INCINERATOR_TEXTURE), false, itemStackIn.hasFoil());
            THE_INCINERATOR_MODEL.renderToBuffer(matrixStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
    }
}
