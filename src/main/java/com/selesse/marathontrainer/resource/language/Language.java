package com.selesse.marathontrainer.resource.language;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public enum Language {
    ENGLISH, FRENCH;

    public static String[] getSupportedLanguages() {
        return new String[] { "English", "Francais" };
    }

    public static Language getLanguageFromLocaleString(String localeString) {
        localeString = removeAccents(localeString).toLowerCase();

        if (localeString.equals("english")) {
            return ENGLISH;
        }
        if (localeString.equals("francais")) {
            return FRENCH;
        }
        return ENGLISH;
    }

    private static String removeAccents(String string) {
        String nfdNormalizedString = Normalizer.normalize(string, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public Locale getLocale() {
        switch (this) {
            case ENGLISH:
                return Locale.ENGLISH;
            case FRENCH:
                return Locale.FRENCH;
            default:
                return Locale.ENGLISH;
        }
    }
}
