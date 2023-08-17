package com.muhammaddaffa.playerprofiles.manager.customgui;

import com.muhammaddaffa.playerprofiles.inventory.items.GUIItem;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record CustomGUI(
        String fileName,
        @NotNull String title,
        int size,
        FileConfiguration config,
        List<GUIItem> items
) {

}
