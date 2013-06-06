package com.selesse.marathontrainer.resource.files;

import com.selesse.marathontrainer.model.Weekday;
import com.selesse.marathontrainer.training.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * This test suite covers the {@link TrainerFileLoader} class's ability to correctly load
 * training plans via the file format.
 */
public class TrainerFileLoaderTest {
    private TrainingPlan trainingPlan;

    @Before
    public void setUpSampleFile() {
        List<String> fileSampleContents = new ArrayList<String>();

        fileSampleContents.add("long 6:30-7:30");
        fileSampleContents.add("tempo 5:55");
        fileSampleContents.add("regular 6:00");
        fileSampleContents.add("speed 5:41");
        fileSampleContents.add("");
        fileSampleContents.add("   rest   | tempo 4 | speed 2.6 3 |    rest   |     rest     |  rest  | rest");
        fileSampleContents.add("regular 3 | tempo 4 | speed 2.6 3 |    rest   |     rest     | long 3 | long 2");
        fileSampleContents.add("tempo 3   | hills 1 | fartlek 12  | regular 9 | challenge 16 | long 2 | rest");

        try {
            File tempFile = File.createTempFile("marathon-trainer", "test");
            tempFile.deleteOnExit();

            PrintWriter out = new PrintWriter(tempFile);

            for (String line : fileSampleContents) {
                out.println(line);
            }

            out.close();

            trainingPlan = TrainerFileLoader.loadTrainingPlan(MarathonType.HALF, tempFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            // failed?
        } catch (IOException e) {
            // failed?
        }
    }

    @Test
    public void testActivityReferenceSpeedParsedCorrectly() {
        assertEquals(null, trainingPlan.getActivityReferenceSpeed(TrainingActivityType.UNKNOWN));
        assertEquals("6:30-7:30", trainingPlan.getActivityReferenceSpeed(TrainingActivityType.LONG));
        assertEquals("5:55", trainingPlan.getActivityReferenceSpeed(TrainingActivityType.TEMPO));
        assertEquals("5:41", trainingPlan.getActivityReferenceSpeed(TrainingActivityType.SPEED));
    }

    @Test
    public void testWeeklyActivityParsedNumberOfWeeksCorrectly() {
        assertEquals(3, trainingPlan.getTrainingWeekList().size());
    }

    @Test
    public void testWeeklyActivityParsedRestPeriodCorrectly() {
        TrainingWeek week1 = trainingPlan.getTrainingWeek(1);
        TrainingActivity restActivity = week1.getActivity(Weekday.SUNDAY);

        assertTrue(restActivity.getTrainingActivityType() == TrainingActivityType.REST);
    }

    @Test
    public void testWeekOneActivityParsedActivityTypesCorrectly() {
        TrainingWeek week1 = trainingPlan.getTrainingWeek(1);
        TrainingActivity sundayActivity = week1.getActivity(Weekday.SUNDAY);
        TrainingActivity mondayActivity = week1.getActivity(Weekday.MONDAY);
        TrainingActivity tuesdayActivity = week1.getActivity(Weekday.TUESDAY);
        TrainingActivity wednesdayActivity = week1.getActivity(Weekday.WEDNESDAY);
        TrainingActivity thursdayActivity = week1.getActivity(Weekday.THURSDAY);
        TrainingActivity fridayActivity = week1.getActivity(Weekday.FRIDAY);
        TrainingActivity saturdayActivity = week1.getActivity(Weekday.SATURDAY);

        assertTrue(sundayActivity.getTrainingActivityType() == TrainingActivityType.REST);
        assertTrue(mondayActivity.getTrainingActivityType() == TrainingActivityType.TEMPO);
        assertTrue(tuesdayActivity.getTrainingActivityType() == TrainingActivityType.SPEED);
        assertTrue(wednesdayActivity.getTrainingActivityType() == TrainingActivityType.REST);
        assertTrue(thursdayActivity.getTrainingActivityType() == TrainingActivityType.REST);
        assertTrue(fridayActivity.getTrainingActivityType() == TrainingActivityType.REST);
        assertTrue(saturdayActivity.getTrainingActivityType() == TrainingActivityType.REST);
    }

    @Test
    public void testWeekTwoActivityParsedActivityTypesCorrectly() {
        TrainingWeek week2 = trainingPlan.getTrainingWeek(2);
        TrainingActivity sundayActivity = week2.getActivity(Weekday.SUNDAY);
        TrainingActivity mondayActivity = week2.getActivity(Weekday.MONDAY);
        TrainingActivity tuesdayActivity = week2.getActivity(Weekday.TUESDAY);
        TrainingActivity wednesdayActivity = week2.getActivity(Weekday.WEDNESDAY);
        TrainingActivity thursdayActivity = week2.getActivity(Weekday.THURSDAY);
        TrainingActivity fridayActivity = week2.getActivity(Weekday.FRIDAY);
        TrainingActivity saturdayActivity = week2.getActivity(Weekday.SATURDAY);

        assertTrue(sundayActivity.getTrainingActivityType() == TrainingActivityType.REGULAR);
        assertTrue(mondayActivity.getTrainingActivityType() == TrainingActivityType.TEMPO);
        assertTrue(tuesdayActivity.getTrainingActivityType() == TrainingActivityType.SPEED);
        assertTrue(wednesdayActivity.getTrainingActivityType() == TrainingActivityType.REST);
        assertTrue(thursdayActivity.getTrainingActivityType() == TrainingActivityType.REST);
        assertTrue(fridayActivity.getTrainingActivityType() == TrainingActivityType.LONG);
        assertTrue(saturdayActivity.getTrainingActivityType() == TrainingActivityType.LONG);
    }

    @Test
    public void testWeekThreeActivityParsedActivityTypesCorrectly() {
        TrainingWeek week3 = trainingPlan.getTrainingWeek(3);
        TrainingActivity sundayActivity = week3.getActivity(Weekday.SUNDAY);
        TrainingActivity mondayActivity = week3.getActivity(Weekday.MONDAY);
        TrainingActivity tuesdayActivity = week3.getActivity(Weekday.TUESDAY);
        TrainingActivity wednesdayActivity = week3.getActivity(Weekday.WEDNESDAY);
        TrainingActivity thursdayActivity = week3.getActivity(Weekday.THURSDAY);
        TrainingActivity fridayActivity = week3.getActivity(Weekday.FRIDAY);
        TrainingActivity saturdayActivity = week3.getActivity(Weekday.SATURDAY);

        assertTrue(sundayActivity.getTrainingActivityType() == TrainingActivityType.TEMPO);
        assertTrue(mondayActivity.getTrainingActivityType() == TrainingActivityType.HILLS);
        assertTrue(tuesdayActivity.getTrainingActivityType() == TrainingActivityType.FARTLEK);
        assertTrue(wednesdayActivity.getTrainingActivityType() == TrainingActivityType.REGULAR);
        assertTrue(thursdayActivity.getTrainingActivityType() == TrainingActivityType.CHALLENGE);
        assertTrue(fridayActivity.getTrainingActivityType() == TrainingActivityType.LONG);
        assertTrue(saturdayActivity.getTrainingActivityType() == TrainingActivityType.REST);
    }

    @Test
    public void testTempoQuantitiesParsedCorrectly() {
        TrainingWeek week3 = trainingPlan.getTrainingWeek(3);
        TrainingActivity trainingActivity = week3.getActivity(Weekday.SUNDAY);

        assertEquals(3, (int) trainingActivity.getQuantity());
        assertEquals(1, trainingActivity.getNumberOfTimes());
    }

    @Test
    public void testHillsQuantitiesParsedCorrectly() {
        TrainingWeek week3 = trainingPlan.getTrainingWeek(3);
        TrainingActivity trainingActivity = week3.getActivity(Weekday.MONDAY);

        assertEquals(1, (int) trainingActivity.getQuantity());
        assertEquals(1, trainingActivity.getNumberOfTimes());
    }

    @Test
    public void testFartlekQuantitiesParsedCorrectly() {
        TrainingWeek week3 = trainingPlan.getTrainingWeek(3);
        TrainingActivity trainingActivity = week3.getActivity(Weekday.TUESDAY);

        assertEquals(12, (int) trainingActivity.getQuantity());
        assertEquals(1, trainingActivity.getNumberOfTimes());
    }

    @Test
    public void testRegularQuantitiesParsedCorrectly() {
        TrainingWeek week3 = trainingPlan.getTrainingWeek(3);
        TrainingActivity trainingActivity = week3.getActivity(Weekday.WEDNESDAY);

        assertEquals(9, (int) trainingActivity.getQuantity());
        assertEquals(1, trainingActivity.getNumberOfTimes());
    }

    @Test
    public void testChallengeQuantitiesParsedCorrectly() {
        TrainingWeek week3 = trainingPlan.getTrainingWeek(3);
        TrainingActivity trainingActivity = week3.getActivity(Weekday.THURSDAY);

        assertEquals(16, (int) trainingActivity.getQuantity());
        assertEquals(1, trainingActivity.getNumberOfTimes());
    }

    @Test
    public void testLongQuantitiesParsedCorrectly() {
        TrainingWeek week3 = trainingPlan.getTrainingWeek(3);
        TrainingActivity trainingActivity = week3.getActivity(Weekday.FRIDAY);

        assertEquals(2, (int) trainingActivity.getQuantity());
        assertEquals(1, trainingActivity.getNumberOfTimes());
    }

    @Test
    public void testSpeedQuantitiesParsedCorrectly() {
        TrainingWeek week2 = trainingPlan.getTrainingWeek(2);
        TrainingActivity trainingActivity = week2.getActivity(Weekday.TUESDAY);

        assertEquals(2.6, trainingActivity.getQuantity(), 0.005);
        assertEquals(3, trainingActivity.getNumberOfTimes());
    }
}
