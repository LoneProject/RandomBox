package org.lone64.randombox.object.repo;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.lone64.randombox.object.box.Box;

import java.util.List;

public interface BoxRepo {

    void init();

    void saveAll();

    void loadAll();

    void setBox(String name, Box box);

    void addBox(String name);

    void delBox(String name);

    void addBoxUser(String name, Player player);

    void setBoxItems(String name, List<ItemStack> items);

    Box getBox(String name);

    boolean isBox(String name);

    List<String> getBoxes();

}