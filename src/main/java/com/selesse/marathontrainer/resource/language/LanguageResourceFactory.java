package com.selesse.marathontrainer.resource.language;

public class LanguageResourceFactory {
    public static LanguageResource createResource(String language) {
        language = language.toLowerCase();

        if (language.equals("french")) {
            return new FrenchLanguageResource();
        }
        else {
            return new EnglishLanguageResource();
        }
    }

}