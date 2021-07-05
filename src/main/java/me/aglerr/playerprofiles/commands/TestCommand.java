package me.aglerr.playerprofiles.commands;

import me.aglerr.playerprofiles.PlayerProfiles;
import me.aglerr.playerprofiles.enums.InventoryType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {

    private final PlayerProfiles plugin;
    public TestCommand(PlayerProfiles plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        plugin.getInventoryManager().openInventory(InventoryType.PROFILE, player, player);

        return false;
    }

}
