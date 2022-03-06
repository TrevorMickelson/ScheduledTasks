package com.codepunisher.scheduledtasks.models;

import org.bukkit.Bukkit;

import java.util.List;

/**
 * Handles all config "tasks"
 *
 * what the players actually see
 */
public class ConfigTask {
    private final List<Integer> broadcastTimes;
    private final String broadcastMessage;
    private final List<String> executeCommands;

    public ConfigTask(List<Integer> broadcastTimes, String broadcastMessage, List<String> executeCommands) {
        this.broadcastTimes = broadcastTimes;
        this.broadcastMessage = broadcastMessage;
        this.executeCommands = executeCommands;
    }

    public List<Integer> getBroadcastTimes() { return broadcastTimes; }
    public String getBroadcastMessage() { return broadcastMessage; }

    public void executeCommands() {
        executeCommands.forEach((cmd) -> { Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd); });
    }
}
