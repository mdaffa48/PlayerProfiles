package me.aglerr.playerprofiles.commands.abstraction;

import me.aglerr.playerprofiles.PlayerProfiles;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class SubCommand {

    @Nullable
    public abstract String getPermission();

    @NotNull
    public abstract List<String> parseTabCompletion(PlayerProfiles plugin, CommandSender sender, String[] args);

    public abstract void execute(PlayerProfiles plugin, CommandSender sender, String[] args);

}
