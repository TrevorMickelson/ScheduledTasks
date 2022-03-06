package com.codepunisher.scheduledtasks.models;

import com.codepunisher.scheduledtasks.ScheduledTasks;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles the "times"
 * section from the config
 *
 * it converts them into a
 * list of ZoneDateTimes
 */
public class ConfigTime {
    private final ZoneId zoneId = ScheduledTasks.getInstance().getConfigManager().getTimeZone();
    private final Map<ZonedDateTime, String> zonedDateTimes = new HashMap<>();

    // The next available time
    private ZonedDateTime nextDateTime = null;

    // List string of times from config
    private final List<String> times = new ArrayList<>();

    public ConfigTime(List<String> times) {
        this.times.addAll(times);
        scheduleEvent();
    }

    public void scheduleEvent() {
        zonedDateTimes.clear();

        for (String time : times) {
            int hour = getHourFromTime(time);
            int minute = getMinuteFromTime(time);
            ZonedDateTime zonedDateTime = LocalDate.now().atTime(hour, minute, 0).atZone(zoneId);
            updateScheduledDay(zonedDateTime, time);
        }

        updateNextDateTime();
    }

    public long getTimeUntilEvent(ZonedDateTime dateTime) {
        return dateTime.toInstant().toEpochMilli() - System.currentTimeMillis();
    }

    public long getTimeUntilNextEvent() {
        if (nextDateTime == null)
            return 0;

        return getTimeUntilEvent(nextDateTime);
    }

    public ZonedDateTime getNextDateTime() { return nextDateTime; }

    /**
     * Updating the "Next Date Time"
     * this is ran after the previous
     * scheduled tasks is executed
     */
    public void updateNextDateTime() {
        long min = Long.MAX_VALUE;

        for (ZonedDateTime dateTime : zonedDateTimes.keySet()) {
            long timeUntilEvent = getTimeUntilEvent(dateTime);

            if (timeUntilEvent < min) {
                min = timeUntilEvent;
                nextDateTime = dateTime;
            }
        }
    }

    private int getHourFromTime(String input) {
        int time = Integer.parseInt(input.substring(0, input.indexOf(":")));
        int timeIncrease = time == 12 ? 0 : time;

        boolean isAM = input.substring(6, 8).toUpperCase().equals("AM");

        if (isAM)
            return LocalTime.MIDNIGHT.plusHours(timeIncrease).getHour();

        return LocalTime.NOON.plusHours(timeIncrease).getHour();
    }

    private int getMinuteFromTime(String input) {
        input = input.substring(input.lastIndexOf(":") + 1);
        input = input.substring(0, 2);
        return Integer.parseInt(input);
    }

    private void updateScheduledDay(ZonedDateTime zonedDateTime, String input) {
        String dayValue = input.substring(9).toUpperCase();
        boolean isDaily = dayValue.equals("DAILY");
        DayOfWeek today = LocalDate.now(zoneId).getDayOfWeek();

        zonedDateTime = zonedDateTime.with(isDaily ? today : DayOfWeek.valueOf(dayValue));
        boolean timeIsInPast = getTimeUntilEvent(zonedDateTime) <= 0;

        if (timeIsInPast) {
            // If the time is in the past
            // that means I need to schedule it
            // to the correct future
            if (isDaily) {
                zonedDateTime = zonedDateTime.plusDays(1);
            } else {
                zonedDateTime = zonedDateTime.plusWeeks(1);
            }
        }

        zonedDateTimes.put(zonedDateTime, input);
    }
}
