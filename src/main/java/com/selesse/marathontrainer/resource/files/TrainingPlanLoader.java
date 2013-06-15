package com.selesse.marathontrainer.resource.files;

import com.selesse.marathontrainer.model.Weekday;
import com.selesse.marathontrainer.training.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class TrainingPlanLoader {
    public static TrainingPlan loadPlan(MarathonType marathonType, String trainingFilePath)
            throws FileNotFoundException, InvalidTrainingFileException {
        TrainingPlan trainingPlan = new TrainingPlan(marathonType);

        File trainingPlanFile = new File(trainingFilePath);

        Scanner fileScanner = new Scanner(trainingPlanFile);

        parseAndSetupReferenceSpeeds(fileScanner, trainingPlan);
        parseAndSetupTrainingWeeks(fileScanner, trainingPlan);

        if (trainingPlan.getTrainingWeekList().size() == 0) {
            throw new InvalidTrainingFileException();
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
            if (!trainingWeek.isEmpty()) {
                trainingPlan.addTrainingWeek(trainingWeek);
            }
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

        TrainingActivityType trainingActivityType = TrainingActivityType.UNKNOWN;
        try {
            trainingActivityType = TrainingActivityType.valueOf(tokens[0].toUpperCase());
        }
        catch (Exception e) {
            // that's okay, we'll revert to unknown
        }

        TrainingActivity activity = new TrainingActivity(trainingActivityType);

        if (tokens.length > 1) {
            activity.setQuantity(Double.parseDouble(tokens[1]));
        }
        if (tokens.length > 2) {
            activity.setNumberOfTimes(Integer.parseInt(tokens[2]));
        }

        return activity;
    }
}
