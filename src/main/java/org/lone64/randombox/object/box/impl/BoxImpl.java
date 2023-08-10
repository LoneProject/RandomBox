package org.lone64.randombox.object.box.impl;

import org.bukkit.inventory.ItemStack;
import org.lone64.randombox.object.box.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BoxImpl implements Box {

    private List<UUID> users;
    private List<ItemStack> items;

    public BoxImpl() {
        this.users = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    @Override
    public void setUsers(List<UUID> users) {
        this.users = users;
    }

    @Override
    public void addUser(UUID user) {
        this.users.add(user);
    }

    @Override
    public void setItems(List<ItemStack> items) {
        this.items = items;
    }

    @Override
    public void addItem(ItemStack item) {
        this.items.add(item);
    }

    @Override
    public List<UUID> getUsers() {
        return users;
    }

    @Override
    public List<ItemStack> getItems() {
        return items;
    }

}
