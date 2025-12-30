package com.p1nero.tcrcore.entity.custom.cataclysm_boss.scylla;

import com.p1nero.tcrcore.entity.custom.cataclysm_boss.BaseBossEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class ScyllaEntity extends BaseBossEntity {

    public ScyllaEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

}
