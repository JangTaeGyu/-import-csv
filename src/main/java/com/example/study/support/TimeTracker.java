package com.example.study.support;

public class TimeTracker {
    public static <T> void track(Runnable action) {
        long startTime = System.currentTimeMillis();

        action.run();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Duration: " + duration + " ms");
    }
}
