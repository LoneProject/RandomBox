package org.lone64.randombox.util.conf;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Conf {

    private static final Map<Player, String> boxMap = new HashMap<>();

    public static void addBoxPlayer(Player player, String name) {
        boxMap.put(player, name);
    }

    public static void delBoxPlayer(Player player) {
        boxMap.remove(player);
    }

    public static String getBoxPlayer(Player player) {
        return boxMap.get(player);
    }

    public static boolean isBoxPlayer(Player player) {
        return boxMap.get(player) != null;
    }

}
