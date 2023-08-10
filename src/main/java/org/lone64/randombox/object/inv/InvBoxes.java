package org.lone64.randombox.object.inv;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.lone64.randombox.object.InvUtil;
import org.lone64.randombox.object.ItemUtil;
import org.lone64.randombox.object.box.Box;
import org.lone64.randombox.util.Util;
import org.lone64.randombox.util.box.BoxUtil;

import java.util.Arrays;

public class InvBoxes extends InvUtil {

    public InvBoxes(Player player) {
        super(player, "&8랜덤박스 목록", 54);

        for (String name : BoxUtil.load().getBoxes()) {
            Box box = BoxUtil.load().getBox(name);
            this.addItem(new ItemUtil(Material.BARREL).setDisplayName("&6&l│ &e" + name)
                    .setLore(Util.getColor("&6&l└ &f보상 갯수: &e" + Util.getNumberFormat(box.getItems().size()) + "&e개")).getItemStack());
        }
    }

}
