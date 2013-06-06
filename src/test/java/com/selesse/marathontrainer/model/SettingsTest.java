package com.selesse.marathontrainer.model;

import com.selesse.marathontrainer.training.MarathonType;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class SettingsTest {
    private File settingsFile;

    @Before
    public void setUpSettingsFile() {
        try {
            settingsFile = File.createTempFile("marathon-trainer", "sample");
            settingsFile.deleteOnExit();

            Settings settings = new Settings(MarathonType.HALF, settingsFile.getAbsolutePath());

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(settingsFile));
            oos.writeObject(settings);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            // failed?
        }
    }

    @Test
    public void testSuccessfulLoadSettings() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(settingsFile));

            Settings settings = (Settings) ois.readObject();

            assertTrue(settings.getMarathonType() == MarathonType.HALF);
            assertEquals(settingsFile.getAbsolutePath(), settings.getTrainingPlanPath());
        } catch (Exception e) {
            // failed?
            fail(e.toString());
        }
    }

    @Test
    public void testFailedLoadSettings() {
        try {
            settingsFile = File.createTempFile("marathon-trainer", "badsettings");
            settingsFile.deleteOnExit();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(settingsFile));

            Settings settings = (Settings) ois.readObject();

            fail("Should have thrown exception due to bogus settings file.");
        }
        catch (EOFException e) {
            // this is good, we've passed the test
        }
        catch (Exception e) {
            fail("Threw wrong type of exception: " + e);
        }
    }
}
