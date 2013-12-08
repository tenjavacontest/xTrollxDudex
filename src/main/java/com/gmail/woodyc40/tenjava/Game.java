/**
 * Name: Game.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Game {

    public Location spawn;
    public String p;

    private int id = 0;
    private boolean game = false;
    private World w;
    private Computer com = new Computer(this);
    private kills = 0;

    public Game(int i) {
        id = i;
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
