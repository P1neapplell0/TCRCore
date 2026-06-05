package com.p1nero.tcrcore.entity.custom.boss_rush;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

public class BossRushManagerRenderer extends EntityRenderer<BossRushManagerEntity> {

    public BossRushManagerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.0F;
    }

    @Override
    public void render(BossRushManagerEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(BossRushManagerEntity pEntity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
