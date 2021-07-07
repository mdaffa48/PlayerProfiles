package me.aglerr.playerprofiles.commands;

import me.aglerr.lazylibs.libs.Common;
import me.aglerr.playerprofiles.ConfigValue;
import me.aglerr.playerprofiles.PlayerProfiles;
import me.aglerr.playerprofiles.commands.abstraction.SubCommand;
import me.aglerr.playerprofiles.commands.subcommands.OpenGUICommand;
import me.aglerr.playerprofiles.commands.subcommands.ReloadCommand;
import me.aglerr.playerprofiles.configs.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

public class MainCommand implements CommandExecutor, TabCompleter {

    private static final String COMMAND_NAME = "playerprofiles";

    private final Map<String, SubCommand> subCommandMap = new HashMap<>();

    private final PlayerProfiles plugin;
    public MainCommand(PlayerProfiles plugin){
        this.plugin = plugin;

        // Reload command
        this.subCommandMap.put("reload", new ReloadCommand());
        // Open GUI command
        this.subCommandMap.put("opengui", new OpenGUICommand());
    }

    public void registerThisCommand(){
        plugin.getCommand(COMMAND_NAME).setExecutor(this);
        plugin.getCommand(COMMAND_NAME).setTabCompleter(this);
        // Get the file configuration of config.yml
        FileConfiguration config = ConfigManager.CONFIG.getConfig();
        // Get the aliases from the config
        List<String> aliases = config.getStringList("commandAliases.playerProfiles");
        // Add all aliases to the command
        plugin.getCommand(COMMAND_NAME).getAliases().addAll(aliases);
        // Trying to register the aliases from the config
        try{
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            ((CommandMap) bukkitCommandMap.get(Bukkit.getServer())).register(COMMAND_NAME, plugin.getCommand(COMMAND_NAME));

            bukkitCommandMap.setAccessible(false);
        } catch (Exception ex){
            Common.log(ChatColor.RED, "Failed to register /playerprofiles command");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        // Return if the args length is 0 and send help messages
        if(args.length == 0){
            this.sendHelpMessages(sender);
            return true;
        }

        // Trying to get subcommand from 'args[0]'
        SubCommand subCommand = this.subCommandMap.get(args[0].toLowerCase());

        // Return if there is no subcommand with 'args[0]' and send help messages
        if(subCommand == null) {
            this.sendHelpMessages(sender);
            return true;
        }

        // Check if sub command has permission
        if(subCommand.getPermission() != null){
            // Check if sender/player doesn't have permission for the subcommand
            if(!(sender.hasPermission(subCommand.getPermission()))){
                // Return and send messages
                sender.sendMessage(Common.color(ConfigValue.NO_PERMISSION
                        .replace("{prefix}", ConfigValue.PREFIX)
                        .replace("{permission}", subCommand.getPermission())));
                return true;
            }
        }

        // Execute the sub command
        subCommand.execute(plugin, sender, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if(args.length == 1){
            return Arrays.asList("reload");
        }

        if(args.length >= 2){
            SubCommand subCommand = this.subCommandMap.get(args[0].toLowerCase());
            if(subCommand == null) return null;

            if(subCommand.getPermission() == null){
                return subCommand.parseTabCompletion(plugin, sender, args);
            }
            if(sender.hasPermission(subCommand.getPermission())){
                return subCommand.parseTabCompletion(plugin, sender, args);
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    private void sendHelpMessages(CommandSender sender){
        ConfigValue.HELP_MESSAGES.forEach(message ->
                sender.sendMessage(Common.color(message)));
    }

}
