package com.selesse.marathontrainer.training;

import com.selesse.marathontrainer.model.Weekday;

import java.util.ArrayList;
import java.util.List;

public class TrainingWeek {
    private List<TrainingActivity> activityList;

    public TrainingWeek() {
        activityList = new ArrayList<TrainingActivity>(7);
    }

    public void setActivity(Weekday weekday, TrainingActivity activity) {
        int weekIndex = weekday.ordinal();

        if (weekIndex >= 0 && weekIndex < activityList.size()) {
            if (activityList.get(weekIndex) != null) {
                activityList.remove(weekIndex);
            }
        }

        activityList.add(weekIndex, activity);
    }

    public TrainingActivity getActivity(Weekday weekday) {
        return activityList.get(weekday.ordinal());
    }

}
