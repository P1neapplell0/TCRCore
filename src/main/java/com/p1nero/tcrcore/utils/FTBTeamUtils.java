package com.p1nero.tcrcore.utils;

import com.p1nero.dpr.DodgeParryRewardMod;
import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.p1nero_ec.capability.PECDataManager;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import com.p1nero.tcrcore.capability.TCRPlayer;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.gameassets.TCRSkills;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.RefreshClientQuestsPacket;
import com.yesman.epicskills.registry.entry.EpicSkillsSkillTrees;
import com.yesman.epicskills.skilltree.SkillTree;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.p1nero.ss.gameassets.skills.FlyingSkills;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * 用于FTBTeams的一些操作
 */
public class FTBTeamUtils {

    @Nullable
    public static ServerPlayer getTeamLeader(ServerPlayer player) {
        Team team = getTeam(player);
        if(team == null || !team.isPartyTeam()) {
            return null;
        }
        UUID ownerId = team.getOwner();
        return (ServerPlayer) player.serverLevel().getPlayerByUUID(ownerId);
    }

    @Nullable
    public static Team getTeam(ServerPlayer player) {
        return FTBTeamsAPI.api().getManager().getTeamForPlayer(player).filter(Team::isPartyTeam).orElse(null);
    }

    public static void onlineTeamMembersDoWithSelf(ServerPlayer player, Consumer<ServerPlayer> consumer) {
        onlineTeamMembersDo(player, consumer, false);
    }

    public static void onlineTeamMembersDoIgnoreSelf(ServerPlayer player, Consumer<ServerPlayer> consumer) {
        onlineTeamMembersDo(player, consumer, true);
    }

    public static void onlineTeamMembersDo(ServerPlayer player, Consumer<ServerPlayer> consumer, boolean ignoreSelf) {
        if(!ignoreSelf) {
            consumer.accept(player);//防止没团队？虽然一个人默认就一个团队，但是以防万一
        }
        FTBTeamsAPI.api().getManager().getTeamForPlayer(player).filter(Team::isPartyTeam).ifPresent(team -> {
            team.getOnlineMembers().forEach(member -> {
                if(member == player) {
                    return;
                }
                consumer.accept(member);
            });
        });
    }

    /**
     * 同步团队进度给新人，前提是默认团队所有人进度都一样= =
     */

    public static boolean syncDataFromTeam(ServerPlayer serverPlayer) {
        return syncDataFromTeam(serverPlayer, getTeam(serverPlayer));
    }

    public static boolean syncDataFromTeam(ServerPlayer newComer, Team team) {
        if(newComer == null || team == null) {
            return false;
        }
        TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(newComer);
        if(!team.getOnlineMembers().isEmpty()) {
            ServerPlayer oldPlayer = null;
            for (ServerPlayer onlineMember : team.getOnlineMembers()) {
                if(onlineMember != newComer) {
                    oldPlayer = onlineMember;
                    break;
                }
            }
            if(oldPlayer == null) {
                return false;
            }

            //处理后入队的没解锁某技能的问题
            if(PlayerDataManager.fireAvoidUnlocked.get(oldPlayer) && !PlayerDataManager.fireAvoidUnlocked.get(newComer)) {
                EFUtils.unlockSkillOnSkillTree(newComer, ResourceLocation.fromNamespaceAndPath(DodgeParryRewardMod.MOD_ID, "passive"), TCRSkills.FIRE_AVOID);
            }
            if(!PlayerDataManager.fireAvoidUnlocked.get(oldPlayer) && PlayerDataManager.fireAvoidUnlocked.get(newComer)) {
                onlineTeamMembersDoWithSelf(oldPlayer, member -> {
                    EFUtils.unlockSkillOnSkillTree(member, ResourceLocation.fromNamespaceAndPath(DodgeParryRewardMod.MOD_ID, "passive"), TCRSkills.FIRE_AVOID);
                });
            }
            ResourceKey<SkillTree> waterTree = ResourceKey.create(SkillTree.SKILL_TREE_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(DodgeParryRewardMod.MOD_ID, "passive"));
            if(PlayerDataManager.waterAvoidUnlocked.get(oldPlayer) && !PlayerDataManager.waterAvoidUnlocked.get(newComer)) {
                EFUtils.unlockSkillOnSkillTree(newComer, waterTree, TCRSkills.WATER_AVOID);
            }
            if(!PlayerDataManager.waterAvoidUnlocked.get(oldPlayer) && PlayerDataManager.waterAvoidUnlocked.get(newComer)) {
                onlineTeamMembersDoWithSelf(oldPlayer, member -> {
                    EFUtils.unlockSkillOnSkillTree(member, waterTree, TCRSkills.WATER_AVOID);
                });
            }
            if(PlayerDataManager.swordSoaringUnlocked.get(oldPlayer) && !PlayerDataManager.swordSoaringUnlocked.get(newComer)) {
                EFUtils.unlockSkillOnSkillTree(newComer, EpicSkillsSkillTrees.BATTLEBORN, FlyingSkills.SWORD_SOARING_ELYTRA_MASTER);
                EFUtils.unlockSkillOnSkillTree(newComer, EpicSkillsSkillTrees.BATTLEBORN, FlyingSkills.SWORD_SOARING_MASTER);
            }
            if(!PlayerDataManager.swordSoaringUnlocked.get(oldPlayer) && PlayerDataManager.swordSoaringUnlocked.get(newComer)) {
                onlineTeamMembersDoWithSelf(oldPlayer, member -> {
                    EFUtils.unlockSkillOnSkillTree(member, EpicSkillsSkillTrees.BATTLEBORN, FlyingSkills.SWORD_SOARING_ELYTRA_MASTER);
                    EFUtils.unlockSkillOnSkillTree(member, EpicSkillsSkillTrees.BATTLEBORN, FlyingSkills.SWORD_SOARING_MASTER);
                });
            }

            //播报给被覆盖的
            ServerPlayer toBroadcast = newComer;
            if(tcrPlayer.copyFromFTBTeamMember(oldPlayer)) {
                toBroadcast = oldPlayer;
                oldPlayer = newComer;
            }

            //PEC的覆盖
            PECDataManager.getPECPlayer(newComer).copyFrom(PECDataManager.getPECPlayer(oldPlayer));

            toBroadcast.displayClientMessage(TCRCoreMod.getInfo("team_progress_synced").withStyle(ChatFormatting.GREEN), false);
            TCRQuestManager.ensureQuest(toBroadcast);
            tcrPlayer.syncToClient(toBroadcast);
            PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new RefreshClientQuestsPacket(), toBroadcast);
            return true;
        }
        return false;
    }

    /**
     * 把进度同步给团队其他人，比如用于祈福后
     */
    public static void syncDataToTeamMembers(ServerPlayer serverPlayer) {
        TCRPlayer self = TCRCapabilityProvider.getTCRPlayer(serverPlayer);
        FTBTeamsAPI.api().getManager().getTeamForPlayer(serverPlayer).ifPresent(team -> {
            team.getOnlineMembers().forEach(member -> {
                if(member != serverPlayer) {
                    TCRPlayer memberData = TCRCapabilityProvider.getTCRPlayer(member);
                    memberData.copyFromFTBTeamMember(self);
                    memberData.syncToClient(member);
                }
            });
        });
    }

}
