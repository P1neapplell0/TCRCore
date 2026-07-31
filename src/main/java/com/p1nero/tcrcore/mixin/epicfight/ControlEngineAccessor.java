package com.p1nero.tcrcore.mixin.epicfight;

import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import yesman.epicfight.client.events.engine.ControlEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;

@Mixin(ControlEngine.class)
public interface ControlEngineAccessor {

    @Accessor(value = "player", remap = false)
    void tcr$setPlayer(@Nullable LocalPlayer player);

    @Accessor(value = "playerPatch", remap = false)
    void tcr$setPlayerPatch(@Nullable LocalPlayerPatch playerPatch);
}
