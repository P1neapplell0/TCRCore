package com.p1nero.tcrcore.events;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.dodo.dodosmobs.entity.InternalAnimationMonster.IABossMonsters.Bone_Chimera_Entity;
import com.p1nero.cataclysm_dimension.CataclysmDimensionMod;
import com.p1nero.cataclysm_dimension.worldgen.CataclysmDimensions;
import com.p1nero.dialog_lib.events.ServerNpcEntityInteractEvent;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCREntityCapabilityProvider;
import com.p1nero.tcrcore.save_data.TCRDimSaveData;
import com.p1nero.tcrcore.utils.WorldUtils;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shelmarow.combat_evolution.api.event.OnExecutionStartEvent;
import org.merlin204.leonidas.item.LeonidasItems;
import org.merlin204.wraithon.util.PositionTeleporter;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID)
public class ForgeEvents {

    public static final Map<LivingEntity, ServerBossEvent> BOSS_BAR_MANAGER = new HashMap<>();

    public static final Map<ResourceKey<Level>, Queue<BlockPos>> BLOCK_POS_TO_DESTROY = new HashMap<>();

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        if (event.getName().equals(ResourceLocation.fromNamespaceAndPath("betteroceanmonuments", "chests/upper_side_chamber"))) {
            //固定头盔
            LootPool.Builder pool1 = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))       // rolls = 1
                    .add(LootItem.lootTableItem(LeonidasItems.THE_LOST_HELMET.get())
                            .setWeight(1));

            LootPool.Builder pool2 = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(20))      // rolls = 20
                    .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                            .setWeight(3)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))))
                    .add(LootItem.lootTableItem(Items.DIAMOND)
                            .setWeight(5)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                    .add(LootItem.lootTableItem(Items.NAUTILUS_SHELL)
                            .setWeight(2)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))))
                    .add(LootItem.lootTableItem(Items.SPONGE)
                            .setWeight(2)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))))
                    .add(LootItem.lootTableItem(Items.PRISMARINE_SHARD)
                            .setWeight(3)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 4))))
                    .add(LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS)
                            .setWeight(3)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))))
                    .add(LootItem.lootTableItem(Items.IRON_INGOT)
                            .setWeight(5)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(8, 24))))
                    .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                            .setWeight(4)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 16))));

            LootTable table = LootTable.lootTable()
                    .withPool(pool1)
                    .withPool(pool2)
                    .build();

            event.setTable(table);
        }
    }

    @SubscribeEvent
    public static void onDialogSend(ServerNpcEntityInteractEvent event) {
        if(event.getSelf() instanceof IronGolem ironGolem) {
            if(event.getInteractId() == 1) {
                ironGolem.setPlayerCreated(false);
                ironGolem.setTarget(event.getServerPlayer());
            }
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if(event.phase == TickEvent.Phase.START) {
            //处理灾变维度的重置的计时器，不能提早进入防止重置失败
            if(event.level instanceof ServerLevel serverLevel && CataclysmDimensions.LEVELS.contains(event.level.dimension())) {
                TCRDimSaveData dimSaveData = TCRDimSaveData.get(serverLevel);
                dimSaveData.tickResetting();
                if(dimSaveData.isResetting() || CataclysmDimensionMod.RESOURCE_LOCATION_INTEGER_MAP.getOrDefault(serverLevel.dimension().location(), 0) > 0) {
                    for (ServerPlayer serverPlayer : serverLevel.players()) {
                        serverPlayer.changeDimension(serverLevel.getServer().getLevel(TCRDimensions.SANCTUM_LEVEL_KEY), new PositionTeleporter(new BlockPos(WorldUtils.START_POS)));
                        serverPlayer.displayClientMessage(TCRCoreMod.getInfo("dim_demending", dimSaveData.getResetCooldown() / 20), false);
                        break;
                    }
                }
                if(serverLevel.players().isEmpty() && !dimSaveData.isResetting()
                        && CataclysmDimensionMod.RESOURCE_LOCATION_INTEGER_MAP.getOrDefault(serverLevel.dimension().location(), 0) > 0){
                    dimSaveData.setResetCooldown(300);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking startTracking) {
        BOSS_BAR_MANAGER.entrySet().removeIf(entry -> entry.getKey() == null || !entry.getKey().isAlive());
        BOSS_BAR_MANAGER.forEach(((living, serverBossEvent) -> {
            if(startTracking.getTarget().equals(living) && startTracking.getEntity() instanceof ServerPlayer serverPlayer) {
                serverBossEvent.addPlayer(serverPlayer);
            }
        }));
    }

    @SubscribeEvent
    public static void onStopTracking(PlayerEvent.StopTracking stopTracking) {
        BOSS_BAR_MANAGER.forEach(((living, serverBossEvent) -> {
            if(stopTracking.getTarget().equals(living) && stopTracking.getEntity() instanceof ServerPlayer serverPlayer) {
                serverBossEvent.removePlayer(serverPlayer);
            }
        }));
        BOSS_BAR_MANAGER.entrySet().removeIf(entry -> entry.getKey() == null || !entry.getKey().isAlive());
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            BOSS_BAR_MANAGER.forEach(((living, serverBossEvent) -> {
                if(living != null) {
                    if(living.isAlive()) {
                        serverBossEvent.setProgress(living.getHealth() / living.getMaxHealth());
                        if(living instanceof Bone_Chimera_Entity) {
                            serverBossEvent.setVisible(TCREntityCapabilityProvider.getTCREntityPatch(living).isFighting());
                        }
                    } else {
                        serverBossEvent.removeAllPlayers();
                    }
                } else {
                    serverBossEvent.removeAllPlayers();
                }
            }));
        }
    }

    @SubscribeEvent
    public static void onExecutionStart(OnExecutionStartEvent event) {
        LivingEntity livingEntity = event.getTarget().getOriginal();
        if (livingEntity.hasEffect(ModEffect.EFFECTSTUN.get())) {
            event.setCanceled(true);
        }
    }

}
