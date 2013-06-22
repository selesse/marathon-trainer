package com.selesse.marathontrainer.resource.language;

import com.selesse.marathontrainer.training.TrainingActivityType;

import java.awt.event.KeyEvent;

public class FrenchLanguageResource implements LanguageResource {
    @Override
    public String getProgramName() {
        return "Entraîneur Marathon";
    }

    @Override
    public String getMenuFileName() {
        return "Fichier";
    }

    @Override
    public int getMenuFileMnemonic() {
        return KeyEvent.VK_F;
    }

    @Override
    public String getMenuExitName() {
        return "Quitter";
    }

    @Override
    public int getMenuExitMnemonic() {
        return KeyEvent.VK_Q;
    }

    @Override
    public String getHalfMarathonName() {
        return "Semi-marathon";
    }

    @Override
    public String getFullMarathonName() {
        return "Marathon";
    }

    @Override
    public String getMenuSettingsName() {
        return "Parametres";
    }

    @Override
    public int getMenuSettingsMnemonic() {
        return KeyEvent.VK_P;
    }

    @Override
    public String getMenuLanguageName() {
        return "Langue";
    }

    @Override
    public int getMenuLanguageMnemonic() {
        return KeyEvent.VK_L;
    }

    @Override
    public String getLanguageChooserTitle() {
        return "Langue";
    }

    @Override
    public String getLanguageChooserText() {
        return String.format("Choisissez la langue pour %s:", getProgramName());
    }

    @Override
    public String getLanguageName() {
        return "Francais";
    }

    @Override
    public String getLoadErrorMessage() {
        return "Il ya eu une erreur de chargement de vos paramètres. Essayez de supprimer le fichier et recommencez.";
    }

    @Override
    public String getLoadErrorTitle() {
        return "Oops!";
    }

    @Override
    public String getNewMarathonName() {
        return "Nouveau marathon";
    }

    @Override
    public int getNewMarathonMnemonic() {
        return KeyEvent.VK_N;
    }

    @Override
    public String getWhenMarathonText() {
        return "Quelle est la date de ton marathon?";
    }

    @Override
    public String getFinishedText() {
        return "Fini";
    }

    @Override
    public String getFileChooserText() {
        return "Choisir plan d'entrainement";
    }

    @Override
    public String getBadMarathonTypeMessage() {
        return "Choisissez un type de marathon (semi ou complet)";
    }

    @Override
    public String getBadMarathonTypeTitle() {
        return "Erreur";
    }

    @Override
    public String getBadMarathonDateMessage() {
        return "Choisissez une date apres aujourd'hui";
    }

    @Override
    public String getBadMarathonDateTitle() {
        return "Erreur";
    }

    @Override
    public Language getLanguage() {
        return Language.FRENCH;
    }

    @Override
    public String getStartHintText() {
        return "Pour commencer, clique sur \"Fichier\" puis \"Nouveau marathon\".";
    }

    @Override
    public String getBadTrainingFileMessage() {
        return "Choisissez un fichier (plan d'entrainement) qui existe.";
    }

    @Override
    public String getBadTrainingFileTitle() {
        return "Erreur";
    }

    @Override
    public String printFriendlyString(TrainingActivityType activityType) {
        switch (activityType) {
            case NOT_TRAINING_YET:
                return "L'entrainement n'a pas commence.";
            case REST:
                return "Repos";
            case MARATHON_ALREADY_HAPPENED:
                return "Le marathon a deja eu lieu.";
            case LONG:
                return "Course longue";
            case CHALLENGE:
                return "Course défie";
            case FARTLEK:
                return "Fartlek";
            case HILLS:
                return "Cote";
            case TEMPO:
                return "Tempo";
            case SPEED:
                return "Vitesse";
            case REGULAR:
                return "Course regulier";
            case MARATHON_TODAY:
                return "Marathon aujourd'hui! Bonne chance!";
            case UNKNOWN:
            default:
                return "???";
        }
    }
}