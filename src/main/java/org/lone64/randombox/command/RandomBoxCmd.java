package org.lone64.randombox.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.lone64.randombox.RandomBox;
import org.lone64.randombox.object.ItemUtil;
import org.lone64.randombox.object.inv.InvBoxEditor;
import org.lone64.randombox.object.inv.InvBoxes;
import org.lone64.randombox.util.Util;
import org.lone64.randombox.util.conf.Conf;
import org.lone64.randombox.util.box.BoxUtil;
import org.lone64.randombox.util.nms.ItemNmsUtil;

import java.util.Arrays;

public class RandomBoxCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;
        if (!player.hasPermission("randombox.admin")) {
            player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&7당신은 해당 명령어를 사용할 수 없습니다!"));
            return true;
        }

        if (args.length == 0) return true;

        String name;
        switch (args[0]) {
            default:
                player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&7해당 명령어는 존재하지 않는 명령어입니다!"));
                break;
            case "생성":
                if (args.length < 2) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e랜덤박스 이름&7을 입력하여 주세요!"));
                    return true;
                }

                name = args[1];
                if (BoxUtil.load().getBoxes().size() == 54) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&7랜덤박스 생성은 &e최대 54개&7입니다!"));
                    return true;
                } else if (BoxUtil.load().isBox(name)) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e{랜덤박스}&7(은)는 이미 존재하는 랜덤박스입니다!")
                            .replace("{랜덤박스}", name));
                    return true;
                }

                BoxUtil.load().addBox(name);
                player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e{랜덤박스}&f(을)를 생성하셨습니다!")
                        .replace("{랜덤박스}", name));
                break;
            case "삭제":
                if (args.length < 2) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e랜덤박스 이름&7을 입력하여 주세요!"));
                    return true;
                }

                name = args[1];
                if (!BoxUtil.load().isBox(name)) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e{랜덤박스}&7(은)는 존재하지 않는 랜덤박스입니다!")
                            .replace("{랜덤박스}", name));
                    return true;
                }

                BoxUtil.load().delBox(name);
                player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e{랜덤박스}&f(을)를 삭제하셨습니다!")
                        .replace("{랜덤박스}", name));
                break;
            case "수정":
                if (args.length < 2) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e랜덤박스 이름&7을 입력하여 주세요!"));
                    return true;
                }

                name = args[1];
                if (!BoxUtil.load().isBox(name)) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e{랜덤박스}&7(은)는 존재하지 않는 랜덤박스입니다!")
                            .replace("{랜덤박스}", name));
                    return true;
                }

                Conf.addBoxPlayer(player, name);
                new InvBoxEditor(player).load();
                break;
            case "지급":
                if (args.length < 2) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e랜덤박스 이름&7을 입력하여 주세요!"));
                    return true;
                }

                name = args[1];
                if (!BoxUtil.load().isBox(name)) {
                    player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e{랜덤박스}&7(은)는 존재하지 않는 랜덤박스입니다!")
                            .replace("{랜덤박스}", name));
                    return true;
                }

                ItemUtil item = new ItemUtil(Material.BARREL);
                item.setDisplayName("&6랜덤박스");
                item.setLore(Arrays.asList(
                        Util.getColor("&7우클릭하여 랜덤박스를 사용할 수 있습니다."),
                        Util.getColor("&7사용하면 랜덤으로 보상이 지급됩니다.")
                ));

                ItemNmsUtil itemNms = new ItemNmsUtil(item.getItemStack());
                itemNms.asTag("BOX_NAME", name);

                player.getInventory().addItem(itemNms.asItemStack());
                player.sendMessage(Util.getColor(RandomBox.getPrefix() + "&e{랜덤박스}&f(을)를 지급받았습니다!")
                        .replace("{랜덤박스}", name));
                break;
            case "목록":
                new InvBoxes(player).load();
                break;
        }
        return false;
    }

}
