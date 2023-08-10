package org.lone64.randombox;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.randombox.command.RandomBoxCmd;
import org.lone64.randombox.command.RandomBoxTab;
import org.lone64.randombox.listener.BoxOpenListener;
import org.lone64.randombox.listener.InvClickListener;
import org.lone64.randombox.listener.InvCloseListener;
import org.lone64.randombox.object.repo.BoxRepo;
import org.lone64.randombox.object.repo.impl.BoxRepoImpl;

@Getter
public class RandomBox extends JavaPlugin {

    private static RandomBox instance;
    private static String prefix;

    private BoxRepo boxRepo;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "&6[랜덤박스] &r&f";

        // LOAD RANDOMBOXES
        this.boxRepo = new BoxRepoImpl();
        this.boxRepo.init();

        // LOAD COMMANDS
        this.getCommand("랜덤박스").setExecutor(new RandomBoxCmd());
        this.getCommand("랜덤박스").setTabCompleter(new RandomBoxTab());

        // LOAD LISTENERS
        this.getServer().getPluginManager().registerEvents(new BoxOpenListener(), this);
        this.getServer().getPluginManager().registerEvents(new InvClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new InvCloseListener(), this);
    }

    @Override
    public void onDisable() {
        if (this.boxRepo != null) this.boxRepo.saveAll();
    }

    public static RandomBox get() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

}
