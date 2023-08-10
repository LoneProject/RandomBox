package org.lone64.randombox.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.lone64.randombox.RandomBox;
import org.lone64.randombox.object.box.Box;
import org.lone64.randombox.util.Util;
import org.lone64.randombox.util.box.BoxUtil;
import org.lone64.randombox.util.nms.ItemNmsUtil;

import java.util.Random;

public class BoxOpenListener implements Listener {

    @EventHandler
    public void onOpen(PlayerInteractEvent event) {
        if (event.getHand() == null || event.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.AIR)) return;

        ItemNmsUtil itemNms = new ItemNmsUtil(item);
        if (itemNms.asTag("BOX_NAME") == null) return;

        event.setUseInteractedBlock(Event.Result.DENY);
        if (!Util.hasAvaliableSlot(player, 1)) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 2);
            player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&7인벤토리가 꽉 찼습니다!"));
            return;
        }

        Box box = BoxUtil.load().getBox(itemNms.asTag("BOX_NAME"));
        int i = new Random().nextInt(box.getItems().size());
        ItemStack stack = box.getItems().get(i);
        player.getInventory().addItem(stack);

        player.getInventory().setItemInMainHand(Util.getUsed(item, 1));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
        player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e{랜덤박스}&f(을)를 사용하셨습니다!")
                .replace("{랜덤박스}", itemNms.asTag("BOX_NAME")));
    }

}
