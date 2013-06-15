package com.selesse.marathontrainer.model;

public class StringUtil {
    /**
     * First-letter capitalizes a string that's in all caps.
     *
     * <pre> "HELLO" -> "Hello" </pre>
     */
    public static String capitalizeAllCaps(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
