package org.lone64.randombox.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.lone64.randombox.util.box.BoxUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomBoxTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String arg, String[] args) {
        List<String> tabs = new ArrayList<>();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("randombox.admin")) {
                if (args.length == 1) {
                    tabs.addAll(Arrays.asList("생성", "삭제", "수정", "지급", "목록"));
                } else if (args.length == 2) {
                    if (args[0].equals("생성")) {
                        tabs.add("[ 랜덤박스 이름 ]");
                    } else if (args[0].equals("삭제") || args[0].equals("수정") || args[0].equals("지급")) {
                        tabs.addAll(BoxUtil.load().getBoxes());
                    }
                }
            }
        }
        return tabs;
    }

}
