/**
 * Name: InfoManager.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava.managers;

import com.gmail.woodyc40.tenjava.Game;
import com.gmail.woodyc40.tenjava.Players;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    private int arenas;
    private Plugin plugin;
    private List<Players> playerData = new ArrayList<>();
    private static List<Game> games = new ArrayList<>();
    private static Map<String, Game> creators = new HashMap<>();
    private static final GameManager gm = new GameManager();


    protected GameManager() {}

    public GameManager(Plugin p) {
        plugin = p;
    }

    public static GameManager getInstance() {
        return gm;
    }

    public Game createArena(Player creator) {
        arenas++;

        Game g = new Game(arenas);
        g.setWorld(creator.getWorld());
        g.p = creator.getName();
        games.add(g);

        creators.put(creator.getName(), g);

        plugin.getConfig().set("Arenas." + arenas, creator.getWorld().getName());

        creator.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
        creator.sendMessage(MessageManager.getInstance().getPrefix() + "Click the spawn");

        return g;
    }

    public Game getArena(Player p) {
        for(Game g : games) {
            if(g.getPlayer() == p) {
                return g;
            }
        }
        return null;
    }

    public boolean exists(World w) {
        if(games.isEmpty()) {
            return false;
        }
        for(Game g : games) {
            if(g.getWorld().getName().equals(w.getName())) {
                return true;
            }
        }
        return false;
    }

    public void addPlayer(Player ply) {
        for(Players p : playerData) {
            if(p.name.equals(ply.getName())) {
                return;
            }
        }
        if(ply.hasPlayedBefore()) {
            return;
        }
        playerData.add(new Players(ply, 2, 0));
    }

    public Players getPlayerData(Player pl) {
        for(Players p : playerData) {
            if(p.name.equals(pl.getName())) {
                return p;
            }
        }
        return null;
    }

    public boolean isCreating(Player p) {
        return creators.containsKey(p.getName());
    }

    public void notCreating(Player p) {
        creators.remove(p.getName());
        getArena(p).p = "";
    }

}
