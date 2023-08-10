package org.lone64.randombox.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.lone64.randombox.util.Util;

public class InvClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(Util.getColor("&8랜덤박스 목록"))) {
            ItemStack item = event.getCurrentItem();
            if (item == null) return;

            event.setCancelled(true);
        }
    }

}
