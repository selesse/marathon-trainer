package com.selesse.marathontrainer.resource.language;

import java.awt.event.KeyEvent;

public class FrenchLanguageResource implements LanguageResource {
    @Override
    public String getProgramName() {
        return "Entraineur de Marathon";
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
}