package org.lone64.randombox.util;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Util {

    public static String getColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String getNumberFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(num);
    }

    public static String getDoubleFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(num);
    }

    public static String getGenerateString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            if (i % 4 == 0 && i > 0) {
                result.append("-");
            } else if (i % 2 == 0) {
                int index = random.nextInt(alphabet.length());
                char randomChar = alphabet.charAt(index);
                result.append(randomChar);
            } else {
                int index = random.nextInt(numbers.length());
                char randomNum = numbers.charAt(index);
                result.append(randomNum);
            }
        }

        return result.toString();
    }

    public static String getName(UUID uuid) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        return player.getName();
    }

    public static String calculateTime(long seconds) {
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24L);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);

        if (hours > 0 && day == 0)
            return hours + "시 " + minute + "분 " + second + "초";
        if (hours >= 0 && day > 0)
            return day + "일 " + hours + "시 " + minute + "분 " + second + "초";

        return minute + "분 " + second + "초";
    }

    public static String getLocationToString(Location location, String split) {
        World w = location.getWorld();
        int x = location.getBlockX(), y = location.getBlockY(), z = location.getBlockZ();
        return w.getName() + split + x + split + y + split + z;
    }

    public static Location getStringToLocation(String loc, String split) {
        String[] l = loc.split(split);
        World w = Bukkit.getWorld(l[0]);
        int x = Integer.parseInt(l[1]), y = Integer.parseInt(l[2]), z = Integer.parseInt(l[3]);
        return new Location(w, x, y, z);
    }

    public static String getLocationToString(Location location) {
        World w = location.getWorld();
        double x = location.getX(), y = location.getY(), z = location.getZ();
        return w.getName() + ":" + x + ":" + y + ":" + z;
    }

    public static Location getStringToLocation(String loc) {
        String[] l = loc.split(":");
        World w = Bukkit.getWorld(l[0]);
        double x = Double.parseDouble(l[1]), y = Double.parseDouble(l[2]), z = Double.parseDouble(l[3]);
        return new Location(w, x, y, z);
    }

    public static ItemStack getUsed(ItemStack is, int amount) {
        if (is.getAmount() == 1) {
            return null;
        } else {
            is.setAmount(is.getAmount() - amount);
            return is;
        }
    }

    public static ItemStack getItem(ItemStack is, int amount) {
        if (is.getAmount() == 1) {
            return null;
        } else {
            is.setAmount(amount);
            return is;
        }
    }

    public static byte[] encode(Object obj) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); BukkitObjectOutputStream boos = new BukkitObjectOutputStream(bos)) {
            boos.writeObject(obj);
            return bos.toByteArray();
        } catch (Exception ex) { return null; }
    }

    public static Object decode(byte[] bytes) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); BukkitObjectInputStream bois = new BukkitObjectInputStream(bis)) {
            return bois.readObject();
        } catch (Exception ex) { return null; }
    }

    public static boolean hasAvaliableSlot(Player player, int size) {
        return (36 - (int) Arrays.stream(player.getInventory().getContents()).filter(Objects::nonNull).count() > size);
    }

}
