package com.codepunisher.scheduledtasks.util;

import com.codepunisher.scheduledtasks.ScheduledTasks;
import com.codepunisher.scheduledtasks.handlers.Scheduler;

public class SchedulerUtil {
    /**
     * This method formats the time
     * until a specific time/date
     *
     * Makes it human readable for in-game
     */
    public static String formatTime(long millis) {
        long totalSeconds = millis / 1000;

        // Setting up times
        long days = totalSeconds / 86400;
        long hours = (totalSeconds / 3600) - (days * 24);
        long minutes = (totalSeconds / 60) - (days * 1440) - (hours * 60);
        long seconds = totalSeconds - (days * 86400) - (hours * 3600) - (minutes * 60);

        // Adding values to string

        // Returning string
        return (days <= 0 ? "" : days + "d ") + (hours <= 0 ? "" : hours + "h ") +
               (minutes <= 0 ? "" : minutes + "m ") + (seconds <= 0 ? "" : seconds + "s");
    }

    public static String getTimeUntilNextEvent(String id) {
        Scheduler scheduler = ScheduledTasks.getInstance().getSchedulerStorage().getScheduler(id);
        return formatTime(scheduler.getConfigTime().getTimeUntilNextEvent());
    }
}
