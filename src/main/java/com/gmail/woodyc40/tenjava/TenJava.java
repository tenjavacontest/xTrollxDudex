/**
 * Name: TenJava.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava;

import com.gmail.woodyc40.tenjava.command.CommandHandler;
import com.gmail.woodyc40.tenjava.command.subcommands.*;
import com.gmail.woodyc40.tenjava.listeners.Listener;
import com.gmail.woodyc40.tenjava.managers.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
    public static TenJava instance;

    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Listener(), this);

        GameManager.getInstance().loadGames();

        getCommand("mw").setExecutor(new CommandHandler());
        CommandHandler.register("create", new Create());
        CommandHandler.register("stats", new Score());
        CommandHandler.register("join", new Join());
    }

    public void onDisable() {
        saveConfig();
    }

    public static TenJava getInstance() {
        return instance;
    }

}
