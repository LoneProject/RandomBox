package org.lone64.randombox.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.lone64.randombox.object.box.Box;
import org.lone64.randombox.util.Util;
import org.lone64.randombox.util.box.BoxUtil;
import org.lone64.randombox.util.conf.Conf;

import java.util.ArrayList;

public class InvCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equals(Util.getColor("&8랜덤박스 수정"))) {
            if (!Conf.isBoxPlayer(player)) return;

            Box box = BoxUtil.load().getBox(Conf.getBoxPlayer(player));
            if (!box.getItems().isEmpty()) box.setItems(new ArrayList<>());
            for (ItemStack item : event.getInventory().getContents()) {
                if (item == null || item.getType().equals(Material.AIR)) continue;
                box.addItem(item);
            }
            BoxUtil.load().setBox(Conf.getBoxPlayer(player), box);
            Conf.delBoxPlayer(player);
        }
    }

}
