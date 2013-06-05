package com.selesse.marathontrainer.resource.language;

import java.awt.event.KeyEvent;

public class FrenchLanguageResource implements LanguageResource {
    @Override
    public String getProgramName() {
        return "Entraîneur Marathon";
    }

    @Override
    public String getMenuFileName() {
        return "Fichier";
    }

    @Override
    public int getMenuFileMnemonic() {
        return KeyEvent.VK_F;
    }

    @Override
    public String getMenuExitName() {
        return "Quitter";
    }

    @Override
    public int getMenuExitMnemonic() {
        return KeyEvent.VK_Q;
    }

    @Override
    public String getHalfMarathonName() {
        return "Semi-marathon";
    }

    @Override
    public String getFullMarathonName() {
        return "Marathon";
    }

    @Override
    public String getMenuSettingsName() {
        return "Parametres";
    }

    @Override
    public int getMenuSettingsMnemonic() {
        return KeyEvent.VK_P;
    }

    @Override
    public String getMenuLanguageName() {
        return "Langue";
    }

    @Override
    public int getMenuLanguageMnemonic() {
        return KeyEvent.VK_L;
    }

    @Override
    public String getLanguageChooserTitle() {
        return "Langue";
    }

    @Override
    public String getLanguageChooserText() {
        return String.format("Choisissez la langue pour %s:", getProgramName());
    }

    @Override
    public String[] getSupportedLanguages() {
        return new String[] { "Francais", "Anglais" };
    }

    @Override
    public String getLanguageName() {
        return "Francais";
    }
}