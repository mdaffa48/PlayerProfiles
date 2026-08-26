package com.muhammaddaffa.playerprofiles.commands.abstraction;

import com.muhammaddaffa.playerprofiles.PlayerProfiles;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class SubCommand {

    @Nullable
    public abstract String getPermission();

    @Nullable
    public abstract List<String> parseTabCompletion(PlayerProfiles plugin, CommandSender sender, String[] args);

    public abstract void execute(PlayerProfiles plugin, CommandSender sender, String[] args);

}
