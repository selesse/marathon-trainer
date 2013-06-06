package com.selesse.marathontrainer.resource.language;

public class LanguageResourceFactory {
    public static LanguageResource createResource(String languageString) {
        try {
            Language language = Language.valueOf(languageString.toUpperCase());
            switch (language) {
                case ENGLISH:
                    return new EnglishLanguageResource();
                case FRENCH:
                    return new FrenchLanguageResource();
            }
        }
        catch (Exception e) {
            return new EnglishLanguageResource();
        }
        return new EnglishLanguageResource();
    }

}