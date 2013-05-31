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
    public String getMenuExitName();
    int getMenuFileMnemonic();
    int getMenuExitMnemonic();
}
