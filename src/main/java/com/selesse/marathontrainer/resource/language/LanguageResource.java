package com.selesse.marathontrainer.resource.language;

/**
 * It's advised *not* to perform localization through inheritance. Let's find out why.
 */
public interface LanguageResource {
    String getProgramName();
    String getLanguageName();

    /**
     * Language string for "File" in the menu.
     */
    String getMenuFileName();
    int getMenuFileMnemonic();

    /**
     * Language string for "Exit" in the "File" submenu.
     */
    String getMenuExitName();
    int getMenuExitMnemonic();

    /**
     * Get the language string for "Half marathon" "File" submenu.
     */
    String getHalfMarathonName();

    /**
     * Get the language string for "Full marathon" "File" submenu.
     */
    String getFullMarathonName();

    /**
     * Get the language string for "Settings" in the menu.
     */
    String getMenuSettingsName();
    int getMenuSettingsMnemonic();

    /**
     * Get the language string for "Languages" in the settings submenu.
     */
    String getMenuLanguageName();
    int getMenuLanguageMnemonic();

    /**
     * An array of supported languages.
     */
    String[] getSupportedLanguages();

    /**
     * The title of the dialog that pops up when you're in the "Languages" modal dialog.
     */
    String getLanguageChooserText();

    /**
     * The text of the dialog that pops up when you're in the "Languages" modal dialog.
     */
    String getLanguageChooserTitle();

    /**
     * Message saying there was a load error.
     */
    String getLoadErrorMessage();

    /**
     * Title window of the load error message.
     */
    String getLoadErrorTitle();
}
