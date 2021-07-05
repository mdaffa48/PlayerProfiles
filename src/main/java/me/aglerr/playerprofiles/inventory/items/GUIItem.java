package me.aglerr.playerprofiles.inventory.items;

import java.util.List;

public class GUIItem {

    private final String type;
    private final String material;
    private final int amount;
    private final String name;
    private final List<Integer> slots;
    private final boolean glowing;
    private final boolean hideAttributes;
    private final boolean usePermission;
    private final String permission;
    private final List<String> lore;
    private final List<String> leftCommands;
    private final List<String> rightCommands;
    private final int customModelData;
    private final boolean onlyOwner;
    private final boolean onlyVisitor;

    public GUIItem(String type, String material, int amount, String name, List<Integer> slots, boolean glowing, boolean hideAttributes, boolean usePermission, String permission, List<String> lore, List<String> leftCommands, List<String> rightCommands, int customModelData, boolean onlyOwner, boolean onlyVisitor) {
        this.type = type;
        this.material = material;
        this.amount = amount;
        this.name = name;
        this.slots = slots;
        this.glowing = glowing;
        this.hideAttributes = hideAttributes;
        this.usePermission = usePermission;
        this.permission = permission;
        this.lore = lore;
        this.leftCommands = leftCommands;
        this.rightCommands = rightCommands;
        this.customModelData = customModelData;
        this.onlyOwner = onlyOwner;
        this.onlyVisitor = onlyVisitor;
    }

    public String getType(){
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public boolean isGlowing() {
        return glowing;
    }

    public boolean isHideAttributes() {
        return hideAttributes;
    }

    public boolean isUsePermission() {
        return usePermission;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<String> getLeftCommands() {
        return leftCommands;
    }

    public List<String> getRightCommands() {
        return rightCommands;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public boolean isOnlyOwner() {
        return onlyOwner;
    }

    public boolean isOnlyVisitor() {
        return onlyVisitor;
    }
}
