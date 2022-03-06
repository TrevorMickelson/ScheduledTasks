package com.codepunisher.scheduledtasks.handlers;

import com.codepunisher.scheduledtasks.ScheduledTasks;
import com.codepunisher.scheduledtasks.models.ConfigTask;
import com.codepunisher.scheduledtasks.models.ConfigTime;
import com.codepunisher.scheduledtasks.util.SchedulerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Scheduler implements Runnable {
    private final ConfigTime configTime;
    private final ConfigTask configTask;

    public Scheduler(String id, ConfigTime configTime, ConfigTask configTask) {
        this.configTime = configTime;
        this.configTask = configTask;
        Bukkit.getServer().getScheduler().runTaskTimer(ScheduledTasks.getInstance(), this, 0L, 20L);
        ScheduledTasks.getInstance().getSchedulerStorage().addScheduler(id, this);
    }

    public ConfigTime getConfigTime() { return configTime; }
    public ConfigTask getConfigTask() { return configTask; }

    @Override
    public void run() {
        if (configTime.getNextDateTime() == null)
            return;

        long timeUntilEvent = configTime.getTimeUntilNextEvent();
        boolean canStartEvent = timeUntilEvent <= 0;

        if (canStartEvent) {
            configTask.executeCommands();
            configTime.scheduleEvent();
        } else {
            long timeInSeconds = timeUntilEvent / 1000;

            configTask.getBroadcastTimes().forEach((time) -> {
                if (timeInSeconds == time) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    configTask.getBroadcastMessage()).replace("%time%", SchedulerUtil.formatTime(timeUntilEvent)));
                }
            });
        }
    }
}
