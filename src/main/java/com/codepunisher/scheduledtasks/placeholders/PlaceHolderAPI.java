package com.codepunisher.scheduledtasks.placeholders;

import com.codepunisher.scheduledtasks.util.SchedulerUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceHolderAPI extends PlaceholderExpansion  {
    @Override @NotNull
    public String getIdentifier() { return "scheduler"; }

    @Override @NotNull
    public String getAuthor() { return "CodePunisher"; }

    @Override @NotNull
    public String getVersion() { return "1.0"; }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        return SchedulerUtil.getTimeUntilNextEvent(identifier);
    }
}
