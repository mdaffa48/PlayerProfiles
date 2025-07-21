package com.muhammaddaffa.playerprofiles.commands.subcommands;

import com.muhammaddaffa.playerprofiles.ConfigValue;
import com.muhammaddaffa.playerprofiles.PlayerProfiles;
import com.muhammaddaffa.playerprofiles.commands.abstraction.SubCommand;
import com.muhammaddaffa.mdlib.utils.Common;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand extends SubCommand {

    @Override
    public @Nullable String getPermission() {
        return "playerprofiles.admin";
    }

    @Override
    public @NotNull List<String> parseTabCompletion(PlayerProfiles plugin, CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public void execute(PlayerProfiles plugin, CommandSender sender, String[] args) {
        // Call the reload method from the PlayerProfiles class
        plugin.reloadAllThing();
        // Send the message to the command sender
        sender.sendMessage(Common.color(ConfigValue.RELOAD
                .replace("{prefix}", ConfigValue.PREFIX)));
    }

}
