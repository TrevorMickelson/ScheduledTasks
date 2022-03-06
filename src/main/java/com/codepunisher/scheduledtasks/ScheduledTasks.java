package com.codepunisher.scheduledtasks;

import com.codepunisher.scheduledtasks.handlers.ConfigManager;
import com.codepunisher.scheduledtasks.placeholders.PlaceHolderAPI;
import com.codepunisher.scheduledtasks.util.SchedulerStorage;
import org.bukkit.plugin.java.JavaPlugin;

public class ScheduledTasks extends JavaPlugin {
    private static ScheduledTasks scheduledTasks;
    private ConfigManager configManager;
    private final SchedulerStorage schedulerStorage = new SchedulerStorage();

    @Override
    public void onEnable() {
        scheduledTasks = this;

        // Saving/loading config
        saveDefaultConfig();

        // Reading/loading values from config
        configManager = new ConfigManager();
        configManager.initConfigTasks();

        registerPlaceholderAPI();
    }

    @Override
    public void onDisable() {

    }

    public void registerPlaceholderAPI() {
        if (ScheduledTasks.getInstance().getServer().getPluginManager().isPluginEnabled("PlaceholderAPI"))
            new PlaceHolderAPI().register();
    }

    public static ScheduledTasks getInstance() { return scheduledTasks; }
    public ConfigManager getConfigManager() { return configManager; }
    public SchedulerStorage getSchedulerStorage() { return schedulerStorage; }
}
