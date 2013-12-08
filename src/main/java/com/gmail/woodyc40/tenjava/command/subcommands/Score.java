/**
 * Name: Score.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava.command.subcommands;

import com.gmail.woodyc40.tenjava.command.SubCommand;
import com.gmail.woodyc40.tenjava.managers.GameManager;
import org.bukkit.entity.Player;

public class Score implements SubCommand {

    public void execute(Player p, String cmd, String[] args) {
        if(cmd.equalsIgnoreCase("stats")) {
            p.sendMessage(GameManager.getInstance().getPlayerData(p).getScore());

            if(GameManager.getInstance().getArena(p) != null) {
                p.sendMessage(GameManager.getInstance().getArena(p).getScore());
            }
        }
    }

}
