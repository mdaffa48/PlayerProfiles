package com.muhammaddaffa.playerprofiles.commands;

import com.muhammaddaffa.playerprofiles.ConfigValue;
import com.muhammaddaffa.playerprofiles.PlayerProfiles;
import com.muhammaddaffa.playerprofiles.configs.ConfigManager;
import me.aglerr.mclibs.libs.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ToggleCommand implements CommandExecutor, TabCompleter {

    private static final String COMMAND_NAME = "toggleprofile";

    private final PlayerProfiles plugin;
    public ToggleCommand(PlayerProfiles plugin){
        this.plugin = plugin;
    }

    public void registerThisCommand(){
        plugin.getCommand(COMMAND_NAME).setExecutor(this);
        plugin.getCommand(COMMAND_NAME).setTabCompleter(this);
        // Get the file configuration of config.yml
        // Trying to register the aliases from the config
        try{
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            ((CommandMap) bukkitCommandMap.get(Bukkit.getServer())).register(COMMAND_NAME, plugin.getCommand(COMMAND_NAME));

            bukkitCommandMap.setAccessible(false);
        } catch (Exception ex){
            Common.log("&cFailed to register /toggleprofile command");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        // Check if the args length is 0 (/lockprofile)
        if(args.length == 0){
            // Permission: playerprofiles.lockprofile
            if(!(sender.hasPermission("playerprofiles.toggleprofile"))){
                sender.sendMessage(Common.color(ConfigValue.NO_PERMISSION
                        .replace("{prefix}", ConfigValue.PREFIX)
                        .replace("{permission}", "playerprofiles.toggleprofile")));
                return true;
            }
            // If the sender is not a player (the sender is console)
            if(!(sender instanceof Player)){
                // Send console a usage message and return the code
                sender.sendMessage(Common.color("&cUsage: /toggleprofile (player)"));
                return true;
            }
            // After the check above, we can get the Player object from the sender
            Player player = (Player) sender;
            // Check if player is locked
            if (plugin.getProfileManager().getOrCreate(player).isLocked()) {
                player.sendMessage(Common.color(ConfigValue.UNLOCK_PROFILE
                        .replace("{prefix}", ConfigValue.PREFIX)));
                // Actually lock the player's profile
                plugin.getProfileManager().unlockProfile(player);
            } else {
                // Send a lock profile message to player
                player.sendMessage(Common.color(ConfigValue.LOCK_PROFILE
                        .replace("{prefix}", ConfigValue.PREFIX)));
                // Actually lock the player's profile
                plugin.getProfileManager().lockProfile(player);
            }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>();
    }

}
