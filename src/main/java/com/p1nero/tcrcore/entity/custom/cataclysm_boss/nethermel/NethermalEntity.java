package com.p1nero.tcrcore.entity.custom.cataclysm_boss.nethermel;

import com.p1nero.tcrcore.entity.custom.cataclysm_boss.BaseBossEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class NethermalEntity extends BaseBossEntity {

    public NethermalEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

}
