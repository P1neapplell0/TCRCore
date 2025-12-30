package com.p1nero.tcrcore.entity.custom.cataclysm_boss.ignis;

import com.p1nero.tcrcore.entity.custom.cataclysm_boss.BaseBossEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class IgnisEntity extends BaseBossEntity {

    public IgnisEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

}
