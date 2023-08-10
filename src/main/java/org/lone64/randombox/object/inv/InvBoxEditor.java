package org.lone64.randombox.object.inv;

import org.bukkit.entity.Player;
import org.lone64.randombox.object.InvUtil;
import org.lone64.randombox.object.box.Box;
import org.lone64.randombox.util.box.BoxUtil;
import org.lone64.randombox.util.conf.Conf;

public class InvBoxEditor extends InvUtil {

    public InvBoxEditor(Player player) {
        super(player, "&8랜덤박스 수정", 27);
        Box box = BoxUtil.load().getBox(Conf.getBoxPlayer(player));
        this.setContents(box.getItems());
    }

}
