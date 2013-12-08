/**
 * Name: Game.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    public Location spawn;
    public String p;
    public List<UUID> uuids = new ArrayList<>();

    private int id = 0;
    public  boolean game = false;
    private World w;
    private Computer com = new Computer(this);
    public int kills = 0;

    public Game(int i) {
        id = i;
    }

    public void spawn(int in, Entity e) {
        if(in % 2 == 0) 
            com.spawn(in, e.getType());
    }

    public int getId() {
        return id;
    }

    public World getWorld() {
        return w;
    }

    public void setWorld(World w) {
        this.w = w;
    }

    public Player getPlayer() {
        return Bukkit.getServer().getPlayerExact(p);
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public boolean isInGame() {
        return game;
    }

    public Computer getCom() {
        return com;
    }

    public String getScore() {
        return ChatColor.GOLD + "Your in-game kills: " + ChatColor.DARK_BLUE + kills;
    }

}
