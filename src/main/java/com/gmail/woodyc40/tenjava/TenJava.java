/**
 * Name: TenJava.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava;

import com.gmail.woodyc40.tenjava.command.CommandHandler;
import com.gmail.woodyc40.tenjava.listeners.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Listener(), this);

        getCommand("mw").setExecutor(new CommandHandler());
    }

    public void onDisable() {

    }

}
