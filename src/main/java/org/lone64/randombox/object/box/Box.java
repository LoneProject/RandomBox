package org.lone64.randombox.object.box;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public interface Box {

    void setUsers(List<UUID> users);

    void addUser(UUID user);

    void setItems(List<ItemStack> items);

    void addItem(ItemStack item);

    List<UUID> getUsers();

    List<ItemStack> getItems();

}
