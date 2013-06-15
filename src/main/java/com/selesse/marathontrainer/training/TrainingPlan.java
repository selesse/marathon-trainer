package com.selesse.marathontrainer.training;

import com.selesse.marathontrainer.model.Weekday;

import java.util.*;

public class TrainingPlan {
    private MarathonType marathonType;
    private Map<TrainingActivityType, String> activityReferenceSpeed;
    private List<TrainingWeek> trainingWeekList;
    private Date marathonDate;

    public TrainingPlan(MarathonType marathonType) {
        this.marathonType = marathonType;
        this.activityReferenceSpeed = new HashMap<TrainingActivityType, String>();
        this.trainingWeekList = new ArrayList<TrainingWeek>(20);
    }

    public void setMarathonDate(Date marathonDate) {
        this.marathonDate = marathonDate;
    }

    public Date getMarathonDate() {
        return marathonDate;
    }

    public MarathonType getMarathonType() {
        return marathonType;
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

    public TrainingActivity getActivityForDate(Date date) {
        // Assume marathon date is on a Sunday, then find the difference in days between today and the marathon.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(marathonDate);
        // get to nearest Sunday
        calendar.add(Calendar.DAY_OF_WEEK, -(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        calendar.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        calendar.set(Calendar.MINUTE, 0);                 // set minute in hour
        calendar.set(Calendar.SECOND, 0);                 // set second in minute
        calendar.set(Calendar.MILLISECOND, 0);            // set millis in second

        // find difference in days (normalize to same time)
        int dayDifference = getDaysBetween(calendar.getTime(), date);

        if (dayDifference == 0) {
            return new TrainingActivity(TrainingActivityType.MARATHON_TODAY);
        }
        if (dayDifference < 0) {
            return new TrainingActivity(TrainingActivityType.MARATHON_ALREADY_HAPPENED);
        }

        int weeksAgo = dayDifference / 7;
        int weekDay = 7 - dayDifference % 7;

        int calculatedWeek = trainingWeekList.size() - weeksAgo - 1;

        if (weekDay == 7) {
            weekDay = 0;
            calculatedWeek++;
        }

        if (trainingWeekList.size() < weeksAgo || calculatedWeek < 0) {
            return new TrainingActivity(TrainingActivityType.NOT_TRAINING_YET);
        }

        TrainingWeek trainingWeek = trainingWeekList.get(calculatedWeek);

        return trainingWeek.getActivity(Weekday.values()[weekDay]);
    }

    private int getDaysBetween(Date firstDate, Date secondDate) {
        long millisecondDiff = firstDate.getTime() - secondDate.getTime();

        return (int) Math.ceil((millisecondDiff / (1000 * 60 * 60 * 24.0)));
    }
}
