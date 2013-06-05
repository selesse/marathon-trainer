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

    @Override
    public String getMenuSettingsName() {
        return "Settings";
    }

    @Override
    public int getMenuSettingsMnemonic() {
        return KeyEvent.VK_S;
    }

    @Override
    public String getMenuLanguageName() {
        return "Language";
    }

    @Override
    public int getMenuLanguageMnemonic() {
        return KeyEvent.VK_L;
    }

    @Override
    public String getLanguageChooserTitle() {
        return "Language";
    }

    @Override
    public String getLanguageChooserText() {
        return String.format("Please choose the language you'd like to run %s in:", getProgramName());
    }

    @Override
    public String[] getSupportedLanguages() {
        return new String[] { "English", "French" };
    }

    @Override
    public String getLanguageName() {
        return "English";
    }

}
