package com.p1nero.tcrcore.entity.custom.cataclysm_boss.ignis;

import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class IgnisRenderer extends HumanoidMobRenderer<IgnisEntity, HumanoidModel<IgnisEntity>> {
    public IgnisRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull IgnisEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/entity/ignis.png");
    }
}