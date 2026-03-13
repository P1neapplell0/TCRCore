package com.p1nero.tcrcore.datagen.sound;

import com.p1nero.tcrcore.client.sound.TCRSounds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TCRSoundGenerator extends TCRSoundProvider {

    public TCRSoundGenerator(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public void registerSounds() {
        generateNewSoundWithSubtitle(TCRSounds.WIN_BGM, "bgm/win_bgm", 1);
        generateNewSoundWithSubtitle(TCRSounds.CLAP, "common/clap", 1);

        generateNewSoundWithSubtitle(TCRSounds.DRUMBEAT_1, "drumbeat1", 1);
        generateNewSoundWithSubtitle(TCRSounds.DRUMBEAT_2, "drumbeat2", 1);
        generateNewSoundWithSubtitle(TCRSounds.DRUMBEAT_3, "drumbeat3", 1);
    }
}
