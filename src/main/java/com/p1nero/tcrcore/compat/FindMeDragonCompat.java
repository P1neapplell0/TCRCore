package com.p1nero.tcrcore.compat;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.magister.bookofdragons.entity.state.AirMotion;
import net.magister.bookofdragons.entity.state.AirStance;
import net.magister.bookofdragons.entity.state.TransportMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public final class FindMeDragonCompat {

    private static final int LAUNCH_LOCK_TICKS = 20;
    private static final Set<UUID> DIVING_RESCUES = ConcurrentHashMap.newKeySet();

    private FindMeDragonCompat() {
    }

    public static boolean canFly(Entity entity) {
        return entity instanceof DragonBase dragon && dragon.canFly();
    }

    public static void forceFlight(LivingEntity living) {
        if (!(living instanceof DragonBase dragon) || !dragon.canFly() || dragon.level().isClientSide) {
            return;
        }

        dragon.setTransportMode(TransportMode.AIRBORNE);
        // setTransportMode is a no-op when the enum already matches, so restore these explicitly.
        dragon.setNoGravity(true);
        dragon.setOnGround(false);
        dragon.fallDistance = 0.0F;
        dragon.setServerLaunchCooldown(LAUNCH_LOCK_TICKS);
        dragon.getStateContext().setTransportMode(TransportMode.AIRBORNE);
    }

    public static void forceCinematicFlightPose(LivingEntity living) {
        if (DIVING_RESCUES.contains(living.getUUID())) {
            forceDive(living);
        } else {
            forceFlight(living);
        }
    }

    public static void beginDiveRescue(LivingEntity living) {
        if (canFly(living) && !living.level().isClientSide) {
            DIVING_RESCUES.add(living.getUUID());
            forceDive(living);
        }
    }

    public static void clearDiveRescue(LivingEntity living) {
        DIVING_RESCUES.remove(living.getUUID());
    }

    public static void finishCinematicFlight(LivingEntity living) {
        clearDiveRescue(living);
        if (!(living instanceof DragonBase dragon) || !dragon.canFly() || dragon.level().isClientSide) {
            return;
        }

        forceFlight(dragon);
        dragon.setAirStance(AirStance.FLYING);
        dragon.setAirMotion(AirMotion.GLIDING);
        dragon.setIsDiveBreaking(false);
        dragon.getStateContext().setAirStance(AirStance.FLYING);
        dragon.getStateContext().setAirMotion(AirMotion.GLIDING);
        dragon.getStateContext().setDiveBreaking(false);
    }

    public static void forceDive(LivingEntity living) {
        if (!(living instanceof DragonBase dragon) || !dragon.canFly() || dragon.level().isClientSide) {
            return;
        }

        forceFlight(dragon);
        dragon.setAirStance(AirStance.FLYING);
        dragon.setAirMotion(AirMotion.DIVING);
        dragon.setIsDiveBreaking(false);
        dragon.getStateContext().setAirStance(AirStance.FLYING);
        dragon.getStateContext().setAirMotion(AirMotion.DIVING);
        dragon.getStateContext().setDiveBreaking(false);
    }
}
