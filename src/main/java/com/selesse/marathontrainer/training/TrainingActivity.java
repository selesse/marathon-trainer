package com.selesse.marathontrainer.training;

public class TrainingActivity {
    private double quantity;
    private int numberOfTimes;
    private TrainingActivityType trainingActivityType;

    public TrainingActivity(String activityName) {
        quantity = -1;
        numberOfTimes = 1;
        try {
            trainingActivityType = TrainingActivityType.valueOf(activityName.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            trainingActivityType = TrainingActivityType.UNKNOWN;
        }
    }

    public TrainingActivity(String activityName, int quantity) {
        this(activityName);
        this.quantity = quantity;
    }

    public TrainingActivity(String activityName, int quantity, int numberOfTimes) {
        this(activityName, quantity);
        this.numberOfTimes = numberOfTimes;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getNumberOfTimes() {
        return numberOfTimes;
    }

    public void setNumberOfTimes(int numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    public TrainingActivityType getTrainingActivityType() {
        return trainingActivityType;
    }

    public void setTrainingActivityType(TrainingActivityType trainingActivityType) {
        this.trainingActivityType = trainingActivityType;
    }
}
