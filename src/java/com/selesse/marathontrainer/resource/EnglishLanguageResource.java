package com.selesse.marathontrainer.resource;

import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 31/05/13
 * Time: 4:46 PM
 */
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
