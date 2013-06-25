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

    /**
     * Return a language-specific, print-friendly representation of a {@link TrainingActivity}.
     */
    public String toLanguageString(LanguageResource resources) {
        String baseString = resources.printFriendlyString(trainingActivityType);
        switch (trainingActivityType) {
            case REST:
            case NOT_TRAINING_YET:
            case MARATHON_TODAY:
            case MARATHON_ALREADY_HAPPENED:
            case UNKNOWN:
                return baseString;
            case SPEED:
                return numberOfTimes + " x " + quantity + " KM " + baseString;
            default:
                return quantity + " KM " + baseString;
        }
    }
}
