package com.selesse.marathontrainer.model;

import com.selesse.marathontrainer.resource.language.Language;
import com.selesse.marathontrainer.resource.language.LanguageResource;
import com.selesse.marathontrainer.training.MarathonType;

import java.io.Serializable;
import java.util.Date;

public class Settings implements Serializable {
    private MarathonType marathonType;
    private String trainingPlanPath;
    private Date marathonDate;
    private Language language;

    public Settings(String language) {
        this.language = Language.valueOf(language.toUpperCase());
    }

    public static String getSettingsLocation() {
        return "training-plan/settings";
    }

    public MarathonType getMarathonType() {
        return marathonType;
    }

    public void setMarathonType(MarathonType marathonType) {
        this.marathonType = marathonType;
    }

    public String getTrainingPlanPath() {
        return trainingPlanPath;
    }

    public void setTrainingPlanPath(String trainingPlanPath) {
        this.trainingPlanPath = trainingPlanPath;
    }

    public void setMarathonDate(Date marathonDate) {
        this.marathonDate = marathonDate;
    }

    public Date getMarathonDate() {
        return marathonDate;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }
}