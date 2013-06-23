package com.selesse.marathontrainer.model;

public enum Weekday {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private final int arrayValue;

    private Weekday(int arrayValue) {
        this.arrayValue = arrayValue;
    }

    public int toArrayValue() {
        return arrayValue;
    }
}
