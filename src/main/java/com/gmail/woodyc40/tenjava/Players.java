package com.gmail.woodyc40.tenjava;

import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Trolldood3 aka xTrollxDudex aka AgentTroll
 */
public class Players {

    public String name;
    int mobSpawn;
    int wins;

    public Players(Player p, int num, int i) {
        name = p.getName();
        mobSpawn = num;
        wins = i;
    }

    public int getSpawnRate() {
        return mobSpawn;
    }

}
