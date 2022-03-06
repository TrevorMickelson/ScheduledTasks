package com.codepunisher.scheduledtasks.handlers;

import com.codepunisher.scheduledtasks.ScheduledTasks;
import com.codepunisher.scheduledtasks.models.ConfigTask;
import com.codepunisher.scheduledtasks.models.ConfigTime;
import org.bukkit.configuration.file.FileConfiguration;

import java.time.ZoneId;
import java.util.*;

public class ConfigManager {
    private static final String TASK_SECTION = "ScheduledTasks";

    private final FileConfiguration config = ScheduledTasks.getInstance().getConfig();
    private final ZoneId timeZone = ZoneId.of(Objects.requireNonNull(config.getString("TimeZone")));

    public ZoneId getTimeZone() { return timeZone; }

    public void initConfigTasks() {
        getTaskSection().forEach((key) -> {
            String path = getTaskPath(key);
            List<String> times = config.getStringList(path + "times");
            List<Integer> preBroadcastTimes = config.getIntegerList(path + "prebroadcasttimes");
            String broadcastMessage = config.getString(path + "prebroadcastmessage");
            List<String> executeCommands = config.getStringList(path + "execute");

            ConfigTime configTime = new ConfigTime(times);
            ConfigTask configTask = new ConfigTask(preBroadcastTimes, broadcastMessage, executeCommands);

            new Scheduler(key, configTime, configTask);
        });
    }

    private String getTaskPath(String taskName) {
        return TASK_SECTION + "." + taskName + ".";
    }

    private Set<String> getTaskSection() {
        return Objects.requireNonNull(config.getConfigurationSection(TASK_SECTION)).getKeys(false);
    }
}
