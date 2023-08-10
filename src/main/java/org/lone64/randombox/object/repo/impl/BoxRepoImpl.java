package org.lone64.randombox.object.repo.impl;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.lone64.randombox.object.Config;
import org.lone64.randombox.object.box.Box;
import org.lone64.randombox.object.box.impl.BoxImpl;
import org.lone64.randombox.object.repo.BoxRepo;
import org.lone64.randombox.util.Util;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class BoxRepoImpl implements BoxRepo {

    private final Map<String, Box> boxMap = new HashMap<>();

    @Override
    public void init() {
        final Config main = new Config("");
        if (!main.isExists()) main.setFolder();
        final Config config = new Config("boxes");
        if (!config.isExists()) config.setFolder();
        this.loadAll();
    }

    @Override
    public void saveAll() {
        for (Map.Entry<String, Box> entry : this.boxMap.entrySet()) {
            String name = entry.getKey();
            Box box = entry.getValue();

            final Config config = new Config("boxes/" + name + ".yml");
            config.setObject("name", name);
            if (box.getItems() != null && !box.getItems().isEmpty())
                config.setObject("items", box.getItems().stream().map(Util::encode).collect(Collectors.toList()));
            if (box.getUsers() != null && !box.getUsers().isEmpty())
                config.setObject("users", box.getUsers().stream().map(Util::encode).collect(Collectors.toList()));
        }

        for (File file : new Config("boxes").getFiles()) {
            String name = file.getName().replace(".yml", "");

            final Config config = new Config("boxes/" + file.getName());
            if (!this.isBox(name))
                config.setDelete();
        }
    }

    @Override
    public void loadAll() {
        this.boxMap.clear();
        for (File file : new Config("boxes").getFiles()) {
            final Config config = new Config("boxes/" + file.getName());
            String name = file.getName().replace(".yml", "");

            Box box = new BoxImpl();
            if (config.isContains("users"))
                box.setUsers(config.getList("users").stream().map(byte[].class::cast).map(Util::decode).map(UUID.class::cast).collect(Collectors.toList()));
            if (config.isContains("items"))
                box.setItems(config.getList("items").stream().map(byte[].class::cast).map(Util::decode).map(ItemStack.class::cast).collect(Collectors.toList()));
            this.boxMap.put(name, box);
        }
    }

    @Override
    public void setBox(String name, Box box) {
        this.boxMap.put(name, box);
    }

    @Override
    public void addBox(String name) {
        Box box = new BoxImpl();
        this.boxMap.put(name, box);
    }

    @Override
    public void delBox(String name) {
        this.boxMap.remove(name);
    }

    @Override
    public void addBoxUser(String name, Player player) {
        Box box = this.getBox(name);
        box.addUser(player.getUniqueId());
        this.boxMap.put(name, box);
    }

    @Override
    public void setBoxItems(String name, List<ItemStack> items) {
        Box box = this.getBox(name);
        box.setItems(items);
        this.boxMap.put(name, box);
    }

    @Override
    public Box getBox(String name) {
        return this.boxMap.get(name);
    }

    @Override
    public boolean isBox(String name) {
        return this.boxMap.get(name) != null;
    }

    @Override
    public List<String> getBoxes() {
        return new ArrayList<>(this.boxMap.keySet());
    }

}
