package com.p1nero.tcrcore.capability;

import com.brass_amber.ba_bt.entity.hostile.golem.EndGolem;
import com.github.dodo.dodosmobs.entity.InternalAnimationMonster.IABossMonsters.Bone_Chimera_Entity;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID)
public class TCREntityCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final TCREntityPatch EMPTY = new TCREntityPatch();

    public static Capability<TCREntityPatch> TCR_ENTITY = CapabilityManager.get(new CapabilityToken<>() {});

    private TCREntityPatch TCREntityPatch = null;

    private final LazyOptional<TCREntityPatch> optional = LazyOptional.of(this::createTCREntityPatch);

    private TCREntityPatch createTCREntityPatch() {
        if(this.TCREntityPatch == null){
            this.TCREntityPatch = new TCREntityPatch();
        }

        return this.TCREntityPatch;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if(capability == TCR_ENTITY){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createTCREntityPatch().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        createTCREntityPatch().loadNBTData(tag);
    }

    @SubscribeEvent
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Bone_Chimera_Entity || event.getObject() instanceof EndGolem) {
            if(!event.getObject().getCapability(TCREntityCapabilityProvider.TCR_ENTITY).isPresent()){
                event.addCapability(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "tcr_entity"), new TCREntityCapabilityProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(TCREntityPatch.class);
    }

    public static TCREntityPatch getTCREntityPatch(Entity entity) {
        if(entity == null) {
            return EMPTY;
        }
        return entity.getCapability(TCREntityCapabilityProvider.TCR_ENTITY).orElse(EMPTY);
    }

}
