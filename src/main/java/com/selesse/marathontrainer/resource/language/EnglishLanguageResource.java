package com.selesse.marathontrainer.resource.language;

import com.selesse.marathontrainer.model.StringUtil;
import com.selesse.marathontrainer.training.TrainingActivityType;

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
    public Language getLanguage() {
        return Language.ENGLISH;
    }

    @Override
    public String getLanguageName() {
        return "English";
    }

    @Override
    public String getLoadErrorMessage() {
        return "Sorry, your settings file could not be loaded. Try deleting it and starting over.";
    }

    @Override
    public String getLoadErrorTitle() {
        return "Whoops!";
    }

    @Override
    public String getNewMarathonName() {
        return "New Marathon";
    }

    @Override
    public int getNewMarathonMnemonic() {
        return KeyEvent.VK_N;
    }

    @Override
    public String getWhenMarathonText() {
        return "When is your marathon?";
    }

    @Override
    public String getFileChooserText() {
        return "Choose training plan file";
    }

    @Override
    public String getFinishedText() {
        return "Finish";
    }

    @Override
    public String getBadMarathonTypeMessage() {
        return "Please pick a marathon type (half or full).";
    }

    @Override
    public String getBadMarathonTypeTitle() {
        return "Bad Marathon Type";
    }

    @Override
    public String getBadMarathonDateMessage() {
        return "Please pick a date that's after today.";
    }

    @Override
    public String getBadMarathonDateTitle() {
        return "Bad Date";
    }

    @Override
    public String getStartHintText() {
        return "To start, click on \"File\", then \"New Marathon\".";
    }

    @Override
    public String getBadFileTypeMessage() {
        return "That is not a valid training plan file.";
    }

    @Override
    public String getBadFileTypeTitle() {
        return "Bad file type";
    }

    @Override
    public String getBadTrainingFileMessage() {
        return "Please pick a valid training plan file.";
    }

    @Override
    public String getBadTrainingFileTitle() {
        return "Bad File";
    }

    @Override
    public String printFriendlyString(TrainingActivityType activityType) {
        switch (activityType) {
            case NOT_TRAINING_YET:
                return "Not training yet";
            case REST:
                return "Rest";
            case MARATHON_ALREADY_HAPPENED:
                return "Marathon already happened";
            default:
                return StringUtil.capitalizeAllCaps(activityType.toString());
        }
    }
}
