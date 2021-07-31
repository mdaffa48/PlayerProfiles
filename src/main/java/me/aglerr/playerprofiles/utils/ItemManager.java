package me.aglerr.playerprofiles.utils;

import me.aglerr.lazylibs.inventory.LazyInventory;
import me.aglerr.lazylibs.libs.Common;
import me.aglerr.lazylibs.libs.ItemBuilder;
import me.aglerr.lazylibs.libs.XMaterial;
import me.aglerr.playerprofiles.inventory.items.GUIItem;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class ItemManager {

    public static ItemStack createItem(GUIItem item, Player player, Player target){
        ItemBuilder builder;
        // Check if the item material contains ';'
        if(item.getMaterial().contains(";")){
            // That means this item is a base64 head item
            // First, we split the ';' to get the head value
            String[] split = item.getMaterial().split(";");
            // Get the head value
            String headValue = Utils.tryParsePAPI(split[1], player, target);
            // Now build the item using ItemBuilder
            builder = new ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                    .name(Utils.tryParsePAPI(item.getName(), player, target))
                    .lore(Utils.tryParsePAPI(item.getLore(), player, target))
                    .amount(item.getAmount() <= 0 ? 1 : item.getAmount())
                    .skull(headValue);
        } else {
            // If the item doesn't contains ';', that means the item is not a head
            // First of all, we check if the item is exist or valid
            Optional<XMaterial> optionalMaterial = XMaterial.matchXMaterial(item.getMaterial());
            // We check if the item is not valid
            if(!optionalMaterial.isPresent()){
                // If so, instead throwing an error, just return error item
                return errorItem(item);
            }
            // Finally create the item stack using the item builder
            builder = new ItemBuilder(optionalMaterial.get().parseItem())
                    .name(Utils.tryParsePAPI(item.getName(), player, target))
                    .lore(Utils.tryParsePAPI(item.getLore(), player, target))
                    .amount(item.getAmount() <= 0 ? 1 : item.getAmount());
        }
        // Add hide attributes item flag if it's enabled
        if(item.isHideAttributes()) builder.flags(ItemFlag.HIDE_ATTRIBUTES);
        // Add random enchant and hide enchant attributes if item set to glowing
        if(item.isGlowing()) builder.enchant(Enchantment.ARROW_DAMAGE).flags(ItemFlag.HIDE_ENCHANTS);
        // Check if the server is 1.14+ and try to add custom model data
        if(Utils.hasCustomModelData()) builder.customModelData(item.getCustomModelData());
        return builder.build();
    }

    public static ItemStack createGUIItem(GUIItem item, Player player, Player target){
        if(item.getType() == null)
            return ItemManager.createItem(item, player, target);

        if(item.getType().contains(";")){
            String[] split = item.getType().split(";");
            String identifier = split[0];
            // Inventory slot item
            if(identifier.equalsIgnoreCase("SLOTS")){
                int slot = Integer.parseInt(split[1]);
                ItemStack stack = target.getInventory().getItem(slot);
                return stack == null ? ItemManager.createItem(item, player, target) : stack;
            }
        }

        switch(item.getType()){
            case "HELMET":
                return createArmorItem(item, player, target, target.getInventory().getHelmet());
            case "CHESTPLATE":
                return createArmorItem(item, player, target, target.getInventory().getChestplate());
            case "LEGGINGS":
                return createArmorItem(item, player, target, target.getInventory().getLeggings());
            case "BOOTS":
                return createArmorItem(item, player, target, target.getInventory().getBoots());
            case "MAIN_HAND":
                return createArmorItem(item, player, target, target.getItemInHand());
            case "OFF_HAND":{
                if(Common.hasOffhand()){
                    return createArmorItem(item, player, target, target.getInventory().getItemInOffHand());
                }
                return new ItemStack(Material.AIR);
            }
            default:
                return ItemManager.createItem(item, player, target);
        }
    }

    public static void fillItem(LazyInventory inventory, FileConfiguration config){
        if(!config.getBoolean("fillItems.enabled")) return;
        // Get the Optional XMaterial
        Optional<XMaterial> optional = XMaterial.matchXMaterial(config.getString("fillItems.material"));
        // If the XMaterial isn't present, just return
        if(!optional.isPresent()) return;
        // Get the ItemStack if the XMaterial is present
        ItemStack stack = optional.get().parseItem();
        // Item builder boiss.
        ItemBuilder builder = new ItemBuilder(stack)
                .name(Utils.hex(config.getString("fillItems.name")))
                .lore(Utils.hex(config.getStringList("fillItems.lore")));
        // If the server has custom model data, apply the custom model data
        if(Utils.hasCustomModelData()){
            builder.customModelData(config.getInt("fillItems.customModelData"));
        }
        // Get the final item stack
        ItemStack finalStack = builder.build();
        // Loop through all inventory slots
        for (int i = 0; i < inventory.getInventory().getSize(); i++) {
            // Get the item stack from the slot
            ItemStack slotStack = inventory.getInventory().getItem(i);
            // Skip if the slot is not null and the type is not material AIR
            if(slotStack != null) continue;
            // If all going well, we set the slot to the fillter item stack
            inventory.setItem(i, finalStack);
        }
    }

    private static ItemStack createArmorItem(GUIItem item, Player player, Player target, ItemStack stack){
        if(stack == null || stack.getType() == Material.AIR) return createItem(item, player, target);
        return stack;
    }

    private static ItemStack errorItem(GUIItem item){
        return new ItemBuilder(XMaterial.BARRIER.parseItem())
                .name("&cInvalid Material!")
                .lore("&7Please check your configuration for item '{item}'".replace("{item}", item.getName()), " ", "&7Additional Information:", "&7Material: {material}".replace("{material}", item.getMaterial()))
                .build();
    }

}
