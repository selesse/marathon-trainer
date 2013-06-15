package com.selesse.marathontrainer.training;

import com.selesse.marathontrainer.resource.files.InvalidTrainingFileException;
import com.selesse.marathontrainer.resource.files.TrainingPlanLoaderTest;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class TrainingPlanTest {
    @Test
    public void testCanCorrectlyGetActivityBasedOnDates() throws IOException, InvalidTrainingFileException, ParseException {
        TrainingPlan trainingPlan = TrainingPlanLoaderTest.createMockTrainingPlan();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        // set it to a Sunday, then make sure the previous week was all correctly computed
        trainingPlan.setMarathonDate(formatter.parse("30/06/13"));

        Date sundayDate = formatter.parse("23/06/13");
        Date mondayDate = formatter.parse("24/06/13");
        Date tuesdayDate = formatter.parse("25/06/13");
        Date wednesdayDate = formatter.parse("26/06/13");
        Date thursdayDate = formatter.parse("27/06/13");
        Date fridayDate = formatter.parse("28/06/13");
        Date saturdayDate = formatter.parse("29/06/13");

        TrainingActivity sundayActivity = trainingPlan.getActivityForDate(sundayDate);
        TrainingActivity mondayActivity = trainingPlan.getActivityForDate(mondayDate);
        TrainingActivity tuesdayActivity = trainingPlan.getActivityForDate(tuesdayDate);
        TrainingActivity wednesdayActivity = trainingPlan.getActivityForDate(wednesdayDate);
        TrainingActivity thursdayActivity = trainingPlan.getActivityForDate(thursdayDate);
        TrainingActivity fridayActivity = trainingPlan.getActivityForDate(fridayDate);
        TrainingActivity saturdayActivity = trainingPlan.getActivityForDate(saturdayDate);

        // based on "tempo 3   | hills 1 | fartlek 12  | regular 9 | challenge 16 | long 2 | rest"
        // defined in TrainingPlanLoaderTest

        assertEquals(TrainingActivityType.TEMPO, sundayActivity.getTrainingActivityType());
        assertEquals(3, (int) sundayActivity.getQuantity());

        assertEquals(TrainingActivityType.HILLS, mondayActivity.getTrainingActivityType());
        assertEquals(1, (int) mondayActivity.getQuantity());

        assertEquals(TrainingActivityType.FARTLEK, tuesdayActivity.getTrainingActivityType());
        assertEquals(12, (int) tuesdayActivity.getQuantity());

        assertEquals(TrainingActivityType.REGULAR, wednesdayActivity.getTrainingActivityType());
        assertEquals(9, (int) wednesdayActivity.getQuantity());

        assertEquals(TrainingActivityType.CHALLENGE, thursdayActivity.getTrainingActivityType());
        assertEquals(16, (int) thursdayActivity.getQuantity());

        assertEquals(TrainingActivityType.LONG, fridayActivity.getTrainingActivityType());
        assertEquals(2, (int) fridayActivity.getQuantity());

        assertEquals(TrainingActivityType.REST, saturdayActivity.getTrainingActivityType());
    }
}
