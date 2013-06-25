package com.selesse.marathontrainer.resource.language;

import com.selesse.marathontrainer.training.TrainingActivityType;

/**
 * It's advised *not* to perform localization through inheritance. Let's find out why.
 */
public interface LanguageResource {
    /**
     * The name of this program.
     */
    String getProgramName();

    /**
     * The {@link Language} for a particular Language resource.
     */
    Language getLanguage();

    /**
     * The string for "File" in the menu.
     */
    String getMenuFileName();
    int getMenuFileMnemonic();

    /**
     * The string for "Exit" in the "File" submenu.
     */
    String getMenuExitName();
    int getMenuExitMnemonic();

    /**
     * The string for "Half marathon".
     */
    String getHalfMarathonName();

    /**
     * The string for "Full marathon".
     */
    String getFullMarathonName();

    /**
     * The string for "Settings" in the menu.
     */
    String getMenuSettingsName();
    int getMenuSettingsMnemonic();

    /**
     * The string for "Languages" in the settings submenu.
     */
    String getMenuLanguageName();
    int getMenuLanguageMnemonic();

    /**
     * The title of the dialog that pops up when you're in the "Languages" modal dialog.
     */
    String getLanguageChooserText();

    /**
     * The text of the dialog that pops up when you're in the "Languages" modal dialog.
     */
    String getLanguageChooserTitle();

    /**
     * The string for the load error message.
     */
    String getLoadErrorMessage();

    /**
     * Title window of the load error message.
     */
    String getLoadErrorTitle();

    /**
     * The string for "New Marathon".
     */
    String getNewMarathonName();
    int getNewMarathonMnemonic();

    /**
     * The string for "Choose the training plan file".
     */
    String getFileChooserText();

    /**
     * The string asking when the marathon is.
     */
    String getWhenMarathonText();

    /**
     * The string for the "Finish" button in the New Marathon dialog.
     */
    String getFinishedText();

    /**
     * The error message for an unselected radio button.
     */
    String getBadMarathonTypeMessage();

    /**
     * The title for the unselected radio button error message.
     */
    String getBadMarathonTypeTitle();

    /**
     * The string saying that the marathon needs to be after today.
     */
    String getBadMarathonDateMessage();

    /**
     * The title for the dialog saying marathon needs to be after today.
     */
    String getBadMarathonDateTitle();

    /**
     * The string saying "that's a bad training file".
     */
    String getBadTrainingFileMessage();

    /**
     * The title for the dialog saying "that's a bad training file".
     */
    String getBadTrainingFileTitle();

    /**
     * The string giving first run instructions, i.e. "File -> New Marathon".
     */
    String getStartHintText();

    /**
     * The print friendly string for a given activity type (i.e. "Regular", "Speed").
     */
    String printFriendlyString(TrainingActivityType activityType);

    /**
     * The string for "Today".
     */
    String getTodayString();

    /**
     * The string for "Next day".
     */
    String getNextDayString();

    /**
     * The string for "Days until marathon".
     */
    String getDaysUntilMarathonString();
}
