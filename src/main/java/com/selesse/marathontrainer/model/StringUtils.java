package com.selesse.marathontrainer.model;

public class StringUtils {
    /**
     * First-letter capitalizes a string.
     *
     * <pre> "HELLO" -> "Hello" or "hi it's me" -> "Hi It's Me" </pre>
     */
    public static String capitalizeAll(String string) {
        String[] tokens = string.split(" ");

        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(token.substring(0, 1).toUpperCase());
            stringBuilder.append(token.substring(1).toLowerCase());
            stringBuilder.append(" ");
        }
        // delete the trailing space, if it's there
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
