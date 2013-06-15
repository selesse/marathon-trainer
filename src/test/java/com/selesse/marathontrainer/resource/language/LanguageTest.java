package com.selesse.marathontrainer.resource.language;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class LanguageTest {
    @Test
    public void testEnglishLocaleDetection() {
        Language language = Language.getLanguageFromLocaleString("English");

        assertEquals(Language.ENGLISH, language);
    }

    @Test
    public void testFrenchLocaleDetection() {
        Language language = Language.getLanguageFromLocaleString("francais");

        assertEquals(Language.FRENCH, language);
    }

    @Test
    public void testFrenchLocaleDetectionAccent() {
        Language language = Language.getLanguageFromLocaleString("Français");

        assertEquals(Language.FRENCH, language);
    }

    @Test
    public void testUnknownLanguageDefaultsToEnglish() {
        Language language = Language.getLanguageFromLocaleString("jibjab");

        assertEquals(Language.ENGLISH, language);
    }
}
