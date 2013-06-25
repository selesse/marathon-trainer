package com.selesse.marathontrainer.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void testCapitalizeProperly() {
        String sampleString = "SPEED", sampleString2 = "hElLO";

        assertEquals("Speed", StringUtils.capitalizeAll(sampleString));
        assertEquals("Hello", StringUtils.capitalizeAll(sampleString2));
    }

    @Test
    public void testCapitalizeProperlyWithSpaces() {
        String sampleString = "this is an uncapitalized sentence";
        assertEquals("This Is An Uncapitalized Sentence", StringUtils.capitalizeAll(sampleString));
    }
}
