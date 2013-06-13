package com.selesse.marathontrainer.resource.language;

public class LanguageResourceFactory {
    public static LanguageResource createResource(Language language) {
        switch (language) {
            case ENGLISH:
                return new EnglishLanguageResource();
            case FRENCH:
                return new FrenchLanguageResource();
            default:
                return new EnglishLanguageResource();
        }
    }
}