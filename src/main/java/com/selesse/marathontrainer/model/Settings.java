package com.selesse.marathontrainer.model;

import com.selesse.marathontrainer.resource.language.Language;
import com.selesse.marathontrainer.training.MarathonType;

import java.io.Serializable;
import java.util.Date;

public class Settings implements Serializable {
    private MarathonType marathonType;
    private String trainingPlanPath;
    private Date marathonDate;
    private Language language;

    public Settings(Language language) {
        this.language = language;
    }

    public static String getSettingsDirectory() {
        return "training-plan";
    }

    public static String getSettingsLocation() {
        return getSettingsDirectory() + "/.settings";
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
