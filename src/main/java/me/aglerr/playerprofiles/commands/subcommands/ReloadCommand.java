package me.aglerr.playerprofiles.commands.subcommands;

import me.aglerr.mclibs.libs.Common;
import me.aglerr.playerprofiles.ConfigValue;
import me.aglerr.playerprofiles.PlayerProfiles;
import me.aglerr.playerprofiles.commands.abstraction.SubCommand;
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
