package com.muhammaddaffa.playerprofiles.inventory.items;

import java.util.List;

public record GUIItem(
        String type,
        String material,
        int amount,
        String name,
        List<Integer> slots,
        boolean glowing,
        boolean hideAttributes,
        boolean usePermission,
        String permission,
        List<String> lore,
        List<String> leftCommands,
        List<String> rightCommands,
        int customModelData,
        boolean onlyOwner,
        boolean onlyVisitor
) {

    @Override
    public String type() {
        if (type == null) {
            return "DUMMY";
        }
        return type;
    }

    @Override
    public String material() {
        if (material == null) {
            return "BARRIER";
        }
        return material;
    }

    @Override
    public String name() {
        if (name == null) {
            return "&cInvalid name! Specify it on the config!";
        }
        return name;
    }

    @Override
    public String permission() {
        if (permission == null) {
            return "invalid.permission";
        }
        return permission;
    }

}
