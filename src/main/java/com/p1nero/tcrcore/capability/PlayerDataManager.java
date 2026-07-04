package com.p1nero.tcrcore.capability;

import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.PersistentBoolDataSyncPacket;
import com.p1nero.tcrcore.network.packet.clientbound.PersistentDoubleDataSyncPacket;
import com.p1nero.tcrcore.network.packet.clientbound.PersistentStringDataSyncPacket;
import com.p1nero.tcrcore.utils.FTBTeamUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerDataManager {
    private final static Set<String> EXISTING_ID = new HashSet<>();
    public static DoubleData currentQuestId = new DoubleData("currentQuestId", 0);

    public static BoolData bossRushFinished = new BoolData("bossRushFinished", false);
    //通关标记
    public static BoolData gameCleared = new BoolData("gameCleared", false);

    public static BoolData aineTalked = new BoolData("aineTalked", false);
    public static BoolData ornnTalked = new BoolData("ornnTalked", false);
    public static BoolData chronosTalked = new BoolData("chronosTalked", false);
    public static BoolData ferryGirlTalked = new BoolData("ferryGirlTalked", false);
    public static BoolData ferryGirlGiftGet = new BoolData("ferryGirlGiftGet", false);
    public static BoolData finalBossKilled = new BoolData("finalBossKilled", false);
    public static BoolData tudigongGiftGet = new BoolData("tudigongGiftGet", false);
    public static BoolData canEnterNether = new BoolData("canEnterNether", false);
    public static BoolData canEnterAether = new BoolData("canEnterAether", false);
    public static BoolData swordSoaringUnlocked = new BoolData("sword_soaring_avoid_unlocked", false);
    public static BoolData fireAvoidUnlocked = new BoolData("fire_avoid_unlocked", false);
    public static BoolData waterAvoidUnlocked = new BoolData("water_avoid_unlocked", false);

    public static BoolData firstJoint = new BoolData("first_joint", false);
    public static BoolData wayStoneInteracted = new BoolData("way_stone_interacted", false);
    public static BoolData dodged = new BoolData("dodged", false);
    public static BoolData parried = new BoolData("parried", false);
    public static BoolData locked = new BoolData("locked", false);
    public static BoolData tutorial_passed = new BoolData("tutorial_passed", false);
    public static BoolData weapon_innate_used = new BoolData("weapon_innate_used", false);

    //是否获取过
    public static BoolData stormEyeGotten = new BoolData("storm_eye_gotten", false);
    public static BoolData flameEyeGotten = new BoolData("flame_eye_gotten", false);
    public static BoolData abyssEyeGotten = new BoolData("abyss_eye_gotten", false);
    public static BoolData cursedEyeGotten = new BoolData("cursed_eye_gotten", false);
    public static BoolData desertEyeGotten = new BoolData("desert_eye_gotten", false);
    public static BoolData mechEyeGotten = new BoolData("mech_eye_gotten", false);
    public static BoolData voidEyeGotten = new BoolData("void_eye_gotten", false);
    public static BoolData monstEyeGotten = new BoolData("monst_eye_gotten", false);

    //是否击败过里面的boss
    public static BoolData stormEyeKilled = new BoolData("storm_eye_killed", false);
    public static BoolData flameEyeKilled = new BoolData("flame_eye_killed", false);
    public static BoolData abyssEyeKilled = new BoolData("abyss_eye_killed", false);
    public static BoolData cursedEyeKilled = new BoolData("cursed_eye_killed", false);
    public static BoolData desertEyeKilled = new BoolData("desert_eye_killed", false);
    public static BoolData mechEyeKilled = new BoolData("mech_eye_killed", false);
    public static BoolData voidEyeKilled = new BoolData("void_eye_killed", false);
    public static BoolData monstEyeKilled = new BoolData("monst_eye_killed", false);

    //是否祈福过
    public static BoolData stormEyeBlessed = new BoolData("stormEyeBlessed", false);
    public static BoolData abyssEyeBlessed = new BoolData("abyssEyeBlessed", false);
    public static BoolData desertEyeBlessed = new BoolData("desertEyeBlessed", false);
    public static BoolData cursedEyeBlessed = new BoolData("cursedEyeBlessed", false);
    public static BoolData flameEyeBlessed = new BoolData("flameEyeBlessed", false);
    public static BoolData mechEyeBlessed = new BoolData("mechEyeBlessed", false);
    public static BoolData monstEyeBlessed = new BoolData("monstEyeBlessed", false);
    public static BoolData voidEyeBlessed = new BoolData("voidEyeBlessed", false);

    //是否点亮过祭坛
    public static BoolData stormEyeActivated = new BoolData("stormEyeActivated", false);
    public static BoolData abyssEyeActivated = new BoolData("abyssEyeActivated", false);
    public static BoolData desertEyeActivated = new BoolData("desertEyeActivated", false);
    public static BoolData cursedEyeActivated = new BoolData("cursedEyeActivated", false);
    public static BoolData flameEyeActivated = new BoolData("flameEyeActivated", false);
    public static BoolData mechEyeActivated = new BoolData("mechEyeActivated", false);
    public static BoolData monstEyeActivated = new BoolData("monstEyeActivated", false);
    public static BoolData voidEyeActivated = new BoolData("voidEyeActivated", false);

    //是否打死过灾变人型
    public static BoolData scyllaHumanoidKilled = new BoolData("scyllaHumanoidKilled", false);
    public static BoolData ignisHumanoidKilled = new BoolData("ignisHumanoidKilled", false);
    public static BoolData netheriteHumanoidKilled = new BoolData("netheriteHumanoidKilled", false);
    public static BoolData maledictusHumanoidKilled = new BoolData("maledictusHumanoidKilled", false);
    public static BoolData leviathanHumanoidKilled = new BoolData("leviathanHumanoidKilled", false);
    public static BoolData enderGuardianHumanoidKilled = new BoolData("enderGuardianHumanoidKilled", false);
    public static BoolData ancientRemnantHumanoidKilled = new BoolData("ancientRemnantHumanoidKilled", false);
    public static BoolData harbingerRemnantHumanoidKilled = new BoolData("harbingerRemnantHumanoidKilled", false);

    public static boolean isAllEyeKilled(Player player) {
        return stormEyeKilled.get(player) &&
                flameEyeKilled.get(player) &&
                abyssEyeKilled.get(player) &&
                cursedEyeKilled.get(player) &&
                desertEyeKilled.get(player) &&
                mechEyeKilled.get(player) &&
                voidEyeKilled.get(player) &&
                monstEyeKilled.get(player);
    }

    public static boolean isAllHumanoidKilled(Player player) {
        return scyllaHumanoidKilled.get(player) &&
                ignisHumanoidKilled.get(player) &&
                netheriteHumanoidKilled.get(player) &&
                maledictusHumanoidKilled.get(player) &&
                leviathanHumanoidKilled.get(player) &&
                enderGuardianHumanoidKilled.get(player) &&
                ancientRemnantHumanoidKilled.get(player) &&
                harbingerRemnantHumanoidKilled.get(player);
    }

    public static void putData(Player player, String key, double value) {
        getTCRPlayer(player).putDouble(key, value);
        //double就不要同步了。。目前只有选中的任务在用
//        if(player instanceof ServerPlayer serverPlayer) {
//            FTBTeamUtils.onlineTeamMembersDo(serverPlayer, member -> {
//                getTCRPlayer(member).putDouble(key, value);
//            });
//        }
    }

    public static void putData(Player player, String key, String value) {
        getTCRPlayer(player).putString(key, value);
//        if(player instanceof ServerPlayer serverPlayer) {
//            FTBTeamUtils.onlineTeamMembersDo(serverPlayer, member -> {
//                getTCRPlayer(member).putString(key, value);
//            });
//        }
    }

    public static void putData(Player player, String key, boolean value) {
        getTCRPlayer(player).putBoolean(key, value);
        //姑息做法。。。客户端还是不同步= =
        if(player instanceof ServerPlayer serverPlayer) {
            FTBTeamUtils.onlineTeamMembersDoIgnoreSelf(serverPlayer, member -> {
                getTCRPlayer(member).putBoolean(key, value);
            });
        }
    }

    public static boolean getBool(Player player, String key) {
        return getTCRPlayer(player).getBoolean(key);
    }

    public static double getDouble(Player player, String key) {
        return getTCRPlayer(player).getDouble(key);
    }

    public static String getString(Player player, String key) {
        return getTCRPlayer(player).getString(key);
    }

    public static TCRPlayer getTCRPlayer(Player player) {
        return TCRCapabilityProvider.getTCRPlayer(player);
    }


    public abstract static class Data<T> {

        protected String key;
        protected boolean isLocked = false;//增加一个锁，用于初始化数据用
        protected int id;

        public Data(String key) {
            if (EXISTING_ID.contains(key)) {
                throw new IllegalArgumentException(key + " is already exist!");
            }
            this.key = key;
            EXISTING_ID.add(key);
        }

        public String getKey() {
            return key;
        }

        public void init(Player player) {
            isLocked = getTCRPlayer(player).getBoolean(key + "isLocked");

        }

        public boolean isLocked(Player player) {
            return getTCRPlayer(player).getBoolean(key + "isLocked");
        }

        public boolean isLocked(CompoundTag playerData) {
            return playerData.getBoolean(key + "isLocked");
        }

        public void lock(Player player) {
            putData(player, key + "isLocked", true);
            isLocked = true;
        }

        public void unLock(Player player) {
            putData(player, key + "isLocked", false);
            LocalPlayer localPlayer = Minecraft.getInstance().player;
            isLocked = false;
        }

        public abstract T get(Player player);

        public abstract void put(Player player, T data);

    }

    public static class StringData extends Data<String> {

        protected boolean isLocked = false;//增加一个锁
        protected String defaultString;

        public StringData(String key, String defaultString) {
            super(key);
            this.defaultString = defaultString;
        }

        @Override
        public void init(Player player) {
            put(player, defaultString);
        }

        @Override
        public void put(Player player, String value) {
            if (!isLocked(player)) {
                putData(player, key, value);
                if (player instanceof ServerPlayer serverPlayer) {
                    PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PersistentStringDataSyncPacket(key, isLocked, value), serverPlayer);
                } else {
                    PacketRelay.sendToServer(TCRPacketHandler.INSTANCE, new PersistentStringDataSyncPacket(key, isLocked, value));
                }
            }
        }

        @Override
        public String get(Player player) {
            return getTCRPlayer(player).getString(key);
        }

        public String get(CompoundTag playerData) {
            return playerData.getString(key);
        }

    }

    public static class DoubleData extends Data<Double> {

        private final double defaultValue;

        public DoubleData(String key, double defaultValue) {
            super(key);
            this.defaultValue = defaultValue;
        }

        public void init(Player player) {
            isLocked = getTCRPlayer(player).getBoolean(key + "isLocked");
            put(player, defaultValue);
        }

        public void put(Player player, Integer value) {
            put(player, value.doubleValue());
        }

        @Override
        public void put(Player player, Double value) {
            if (!isLocked(player)) {
                putData(player, key, value);
                if (player instanceof ServerPlayer serverPlayer) {
                    PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PersistentDoubleDataSyncPacket(key, isLocked, value), serverPlayer);
                } else {
                    PacketRelay.sendToServer(TCRPacketHandler.INSTANCE, new PersistentDoubleDataSyncPacket(key, isLocked, value));
                }
            }
        }

        public int getInt(Player player) {
            return get(player).intValue();
        }

        @Override
        public Double get(Player player) {
            return getTCRPlayer(player).getDouble(key);
        }

        public double get(CompoundTag playerData) {
            return playerData.getDouble(key);
        }

    }

    public static class BoolData extends Data<Boolean> {

        boolean defaultBool;

        public BoolData(String key, boolean defaultBool) {
            super(key);
            this.defaultBool = defaultBool;
        }

        public void init(Player player) {
            isLocked = getTCRPlayer(player).getBoolean(key + "isLocked");
            put(player, defaultBool);
        }

        @Override
        public void put(Player player, Boolean value) {
            if (isLocked(player))
                return;
            putData(player, key, value);
            if (player instanceof ServerPlayer serverPlayer) {
                PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PersistentBoolDataSyncPacket(key, isLocked, value), serverPlayer);
            } else {
                PacketRelay.sendToServer(TCRPacketHandler.INSTANCE, new PersistentBoolDataSyncPacket(key, isLocked, value));
            }
        }

        public void markTrue(Player player) {
            this.put(player, true);
        }

        public void markFalse(Player player) {
            this.put(player, true);
        }

        @Override
        public Boolean get(Player player) {
            return getTCRPlayer(player).getBoolean(key);
        }

        public boolean get(CompoundTag playerData) {
            return playerData.getBoolean(key);
        }

    }

}
