package org.lone64.randombox.object;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.lone64.randombox.util.Util;

import java.util.List;

public class InvUtil {

    private Player player;
    private Inventory inventory;

    public InvUtil(Player player, String title, int slot) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, slot, Util.getColor(title));
    }

    public void load() {
        this.player.openInventory(this.inventory);
    }

    public void setItem(int slot, ItemStack item) {
        this.inventory.setItem(slot, item);
    }

    public void setContents(ItemStack... items) {
        this.inventory.setContents(items);
    }

    public void setContents(List<ItemStack> items) {
        for (ItemStack item : items) {
            this.addItem(item);
        }
    }

    public void setItem(int slot, Material type) {
        this.inventory.setItem(slot, new ItemStack(type));
    }

    public void addItem(ItemStack item) {
        this.inventory.addItem(item);
    }

    public void addItem(Material type) {
        this.inventory.addItem(new ItemStack(type));
    }

}