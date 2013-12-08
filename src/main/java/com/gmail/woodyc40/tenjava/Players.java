package com.gmail.woodyc40.tenjava;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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

    public void increment(int... kills) {
        wins++;
        if(wins % 4 == 0) {
            mobSpawn++;
        }
        if(kills == null) {
            return;
        }
        this.kills = this.kills + kills[0];
    }

    public String getScore() {
        return ChatColor.GOLD + "Your Mob Spawn Rate: " + ChatColor.DARK_BLUE + mobSpawn + "\n"
            + ChatColor.GOLD + "Your wins: " + ChatColor.DARK_BLUE + wins + "\n"
            + ChatColor.GOLD + "Your total kills: " + ChatColor.DARK_BLUE + kills;
    }

}
