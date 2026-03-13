package com.p1nero.tcrcore.mixin;

import it.unimi.dsi.fastutil.longs.Long2BooleanMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({StructureCheck.class})
public class MixinStructureChecker {

    @Mutable
    @Shadow
    @Final
    private Long2ObjectMap<Object2IntMap<Structure>> loadedChunks;
    @Mutable
    @Shadow
    @Final
    private Map<Structure, Long2BooleanMap> featureChecks;
    @Unique
    private Object mapMutex = new Object();

    @Inject(
            method = {"<init>"},
            at = {@At("RETURN")}
    )
    private void onInit(CallbackInfo info) {
        this.mapMutex = new Object();
        this.loadedChunks = Long2ObjectMaps.synchronize(this.loadedChunks);
        this.featureChecks = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap(), this.mapMutex);
    }

    @Redirect(
            method = {"storeFullResults"},
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Collection;forEach(Ljava/util/function/Consumer;)V"
            )
    )
    private void redirectForEach(Collection<Long2BooleanMap> instance, Consumer<Long2BooleanMap> consumer) {
        synchronized(this.mapMutex) {
            Iterator<Long2BooleanMap> iterator = instance.iterator();

            while(iterator.hasNext()) {
                Long2BooleanMap next = iterator.next();
                consumer.accept(next);
                if (next.isEmpty()) {
                    iterator.remove();
                }
            }

        }
    }
}
