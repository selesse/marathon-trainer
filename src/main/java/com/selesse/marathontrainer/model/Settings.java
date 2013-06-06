package com.selesse.marathontrainer.model;

import com.selesse.marathontrainer.training.MarathonType;

import java.io.Serializable;

public class Settings implements Serializable {
    private MarathonType marathonType;
    private String trainingPlanPath;

    public static String getSettingsLocation() {
        return "training-plan/settings";
    }

    public Settings() {}

    public Settings(MarathonType marathonType, String trainingPlanPath) {
        this.marathonType = marathonType;
        this.trainingPlanPath = trainingPlanPath;
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
}
