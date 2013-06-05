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
     * Language string for "Exit" in the menu.
     */
    public String getMenuExitName();

    int getMenuExitMnemonic();

    /**
     * Get the language string for "Half marathon".
     */
    public String getHalfMarathonName();

    /**
     * Get the language string for "Full marathon".
     */
    public String getFullMarathonName();
}
