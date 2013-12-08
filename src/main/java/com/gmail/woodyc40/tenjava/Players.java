package com.gmail.woodyc40.tenjava;

import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Trolldood3 aka xTrollxDudex aka AgentTroll
 */
public class Players {

    public String name = "";
    int mobSpawn = 0;
    int wins = 0;
    int kills = 0;

    public Players(Player p, int num, int i) {
        name = p.getName();
        mobSpawn = num;
        wins = i;
    }

    public int getSpawnRate() {
        return mobSpawn;
    }

    public void increment(int kills) {
        wins++;
        if(wins % 4 == 0) {
            mobSpawn++;
        }
        this.kills =+ kills;
    }

    public String getScore() {
        return ChatColor.GOLD + "Your Mob Spawn Rate: " + ChatColor.DARK_BLUE + mobSpawn + "\n" 
            + ChatColor.GOLD + "Your wins: " + ChatColor.DARK_BLUE + wins + "\n"
            + ChatColor.GOLD + "Your total kills: " + ChatColor.DARK_BLUE + kills;
    }

}
