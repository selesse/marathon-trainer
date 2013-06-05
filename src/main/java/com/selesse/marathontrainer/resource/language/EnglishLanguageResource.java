package com.selesse.marathontrainer.resource.language;

import java.awt.event.KeyEvent;

public class EnglishLanguageResource implements LanguageResource {
    @Override
    public String getProgramName() {
        return "Marathon Trainer";
    }

    @Override
    public String getMenuFileName() {
        return "File";
    }

    @Override
    public int getMenuFileMnemonic() {
        return KeyEvent.VK_F;
    }

    @Override
    public String getMenuExitName() {
        return "Exit";
    }

    @Override
    public int getMenuExitMnemonic() {
        return KeyEvent.VK_X;
    }

    @Override
    public String getHalfMarathonName() {
        return "Half marathon";
    }

    @Override
    public String getFullMarathonName() {
        return "Full marathon";
    }
}
