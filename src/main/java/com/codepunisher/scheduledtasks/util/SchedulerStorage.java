package com.codepunisher.scheduledtasks.util;

import com.codepunisher.scheduledtasks.handlers.Scheduler;

import java.util.HashMap;
import java.util.Map;

public class SchedulerStorage {
    private final Map<String, Scheduler> scheduledTasks = new HashMap<>();

    public void addScheduler(String id, Scheduler scheduler) { scheduledTasks.put(id, scheduler); }

    public Scheduler getScheduler(String id) { return scheduledTasks.get(id); }
}
