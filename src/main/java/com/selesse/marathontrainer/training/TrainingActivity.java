package com.selesse.marathontrainer.training;

import com.selesse.marathontrainer.resource.language.LanguageResource;

public class TrainingActivity {
    private double quantity;
    private int numberOfTimes;
    private TrainingActivityType trainingActivityType;

    public TrainingActivity(TrainingActivityType activityType) {
        quantity = -1;
        numberOfTimes = 1;
        this.trainingActivityType = activityType;
    }

    public TrainingActivity(TrainingActivityType activityType, int quantity) {
        this(activityType);
        this.quantity = quantity;
    }

    public TrainingActivity(TrainingActivityType activityTypee, int quantity, int numberOfTimes) {
        this(activityTypee, quantity);
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

    @Override
    public String toString() {
        return "TrainingActivity{" + trainingActivityType.toString().toLowerCase() +
                ", quantity=" + quantity +
                ", numberOfTimes=" + numberOfTimes +
                '}';
    }

    public String getPrintFriendlyString(LanguageResource resources) {
        String baseString = resources.printFriendlyString(trainingActivityType);
        switch (trainingActivityType) {
            case REST:
            case NOT_TRAINING_YET:
            case MARATHON_TODAY:
            case MARATHON_ALREADY_HAPPENED:
            case UNKNOWN:
                return baseString;
            case SPEED:
                return baseString + ": " + numberOfTimes + " x " + quantity + " KM";
            default:
                return baseString + ": " + quantity + " KM";
        }
    }
}
