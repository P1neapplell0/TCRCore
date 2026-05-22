package com.p1nero.tcrcore.mixin.ftbq;

import dev.ftb.mods.ftblibrary.icon.ItemIcon;
import dev.ftb.mods.ftblibrary.util.TooltipList;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.client.gui.quests.QuestScreen;
import dev.ftb.mods.ftbquests.quest.QuestObjectBase;
import net.minecraft.world.item.ItemStack;
import org.arc.epic_ponder.client.ponder.EFPPonderPlugin;
import org.arc.epic_ponder.compat.EFMSkillsPonderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.item.EpicFightItems;
import yesman.epicfight.world.item.SkillBookItem;

@Mixin(QuestScreen.class)
public class QuestScreenMixin {

    @Shadow(remap = false)
    int zoom;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void tcr$init(ClientQuestFile clientQuestFile, QuestScreen.PersistedData persistedData, CallbackInfo ci) {
        this.zoom = 5;
    }

    @Inject(method = "addInfoTooltip", at = @At("HEAD"), remap = false)
    private void tcr$addInfoTooltip(TooltipList list, QuestObjectBase object, CallbackInfo ci) {
        if(object.getIcon() instanceof ItemIcon icon) {
            ItemStack stack = icon.getStack();
            if(!EFMSkillsPonderManager.hasPonderScene(stack)) {
                return;
            }
            if(stack.is(EpicFightItems.SKILLBOOK.get())) {
                Skill skill = SkillBookItem.getContainSkill(stack);
                if(skill != null) {
                    if(EFMSkillsPonderManager.hasPonderScene(skill)) {
                        EFMSkillsPonderManager.setHoveredSkill(skill);
                        list.add(EFMSkillsPonderManager.getPonderTooltip());
                    }
                }
            } else {
                String id = EFPPonderPlugin.getWeaponPresetId(stack);
                if(id != null) {
                    EFMSkillsPonderManager.setHoveredWeapon(id);
                    EFMSkillsPonderManager.setHoveredItem(stack);
                    list.add(EFMSkillsPonderManager.getPonderTooltip());
                }
            }
        }
    }

}
