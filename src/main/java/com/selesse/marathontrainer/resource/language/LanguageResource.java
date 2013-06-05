package com.selesse.marathontrainer.resource.language;

/**
 * It's advised *not* to perform localization through inheritance. Let's find out why.
 */
public interface LanguageResource {
    public String getProgramName();

    /**
     * Language string for "File" in the menu.
     */
    public String getMenuFileName();
    int getMenuFileMnemonic();

    /**
     * Language string for "Exit" in the "File" submenu.
     */
    public String getMenuExitName();
    int getMenuExitMnemonic();

    /**
     * Get the language string for "Half marathon" "File" submenu.
     */
    public String getHalfMarathonName();

    /**
     * Get the language string for "Full marathon" "File" submenu.
     */
    public String getFullMarathonName();

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

    String getLanguageName();
}
