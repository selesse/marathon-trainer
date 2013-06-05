package com.selesse.marathontrainer.resource.files;

import com.selesse.marathontrainer.model.Weekday;
import com.selesse.marathontrainer.training.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TrainerFileLoader {
    public static TrainingPlan loadTrainingPlan(MarathonType marathonType, String trainingFilePath) {
        TrainingPlan trainingPlan = new TrainingPlan(marathonType);

        File trainingPlanFile = new File(trainingFilePath);

        try {
            Scanner fileScanner = new Scanner(trainingPlanFile);

            parseAndSetupReferenceSpeeds(fileScanner, trainingPlan);
            parseAndSetupTrainingWeeks(fileScanner, trainingPlan);
        }
        catch (FileNotFoundException e) {
            // TODO handle
        }

        return trainingPlan;
    }

    private static void parseAndSetupTrainingWeeks(Scanner fileScanner, TrainingPlan trainingPlan) {
        while (fileScanner.hasNext()) {
            TrainingWeek trainingWeek = new TrainingWeek();

            String nextLine = fileScanner.nextLine();

            String weekPlan[] = nextLine.split("\\|");

            if (weekPlan.length == 7) {
                for (int i = 0; i < weekPlan.length; i++) {
                    weekPlan[i] = weekPlan[i].trim();
                    trainingWeek.setActivity(Weekday.values()[i], parseActivity(weekPlan[i]));
                }
            }
            else {
                // TODO handle gracefully
            }
            trainingPlan.addTrainingWeek(trainingWeek);
        }
    }

    private static void parseAndSetupReferenceSpeeds(Scanner fileScanner, TrainingPlan trainingPlan) {
        while (fileScanner.hasNext()) {
            String nextLine = fileScanner.nextLine();

            if (nextLine.equals("")) {
                break;
            }

            String[] tokens = nextLine.split(" ");
            if (tokens.length == 2) {
                String activityType = tokens[0].toUpperCase();
                TrainingActivityType trainingActivityType;

                try {
                    trainingActivityType = TrainingActivityType.valueOf(activityType);
                }
                catch (IllegalFormatException e) {
                    trainingActivityType = TrainingActivityType.UNKNOWN;
                }

                trainingPlan.addActivity(trainingActivityType, tokens[1]);
            }
        }
    }

    private static TrainingActivity parseActivity(String parseString) {
        String[] tokens = parseString.split(" ");

        TrainingActivity activity = new TrainingActivity(tokens[0]);

        if (tokens.length > 1) {
            activity.setQuantity(Double.parseDouble(tokens[1]));
        }
        if (tokens.length > 2) {
            activity.setNumberOfTimes(Integer.parseInt(tokens[2]));
        }

        return activity;
    }
}
