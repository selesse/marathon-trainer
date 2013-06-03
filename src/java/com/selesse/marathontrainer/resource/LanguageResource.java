package com.selesse.marathontrainer.resource;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 31/05/13
 * Time: 4:42 PM
 */
public interface LanguageResource {
    public String getProgramName();

    public String getMenuFileName();
    int getMenuFileMnemonic();

    public String getMenuExitName();
    int getMenuExitMnemonic();

    public String getHalfMarathonName();
    public String getFullMarathonName();
}
