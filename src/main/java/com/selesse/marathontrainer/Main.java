package com.selesse.marathontrainer;

/**
 * Entry point to the application.
 */
public class Main {
    public static void main(String[] args) {
        Thread appThread = new Thread(new MarathonTrainer());
        appThread.start();
    }
}
