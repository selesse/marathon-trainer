package com.selesse.marathontrainer.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingPlan {
    private MarathonType marathonType;
    private Map<TrainingActivityType, String> activityReferenceSpeed;
    private List<TrainingWeek> trainingWeekList;

    public TrainingPlan(MarathonType marathonType) {
        this.marathonType = marathonType;
        this.activityReferenceSpeed = new HashMap<TrainingActivityType, String>();
        this.trainingWeekList = new ArrayList<TrainingWeek>(20);
    }

    public void addActivity(TrainingActivityType activityType, String duration) {
        activityReferenceSpeed.put(activityType, duration);
    }

    public String getActivityReferenceSpeed(TrainingActivityType activityType) {
        return activityReferenceSpeed.get(activityType);
    }

    public void addTrainingWeek(TrainingWeek trainingWeek) {
        trainingWeekList.add(trainingWeek);
    }

    public List<TrainingWeek> getTrainingWeekList() {
        return trainingWeekList;
    }

    public TrainingWeek getTrainingWeek(int weekIndex) {
        if (weekIndex <= 0) {
            return null;
        }
        return trainingWeekList.get(weekIndex - 1);
    }
}
