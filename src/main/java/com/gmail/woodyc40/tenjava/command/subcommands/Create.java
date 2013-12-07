/**
 * Name: Create.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava.command.subcommands;

import com.gmail.woodyc40.tenjava.command.SubCommand;
import com.gmail.woodyc40.tenjava.managers.GameManager;
import com.gmail.woodyc40.tenjava.managers.MessageManager;
import org.bukkit.entity.Player;

public class Create implements SubCommand {

    public void execute(Player p, String cmd, String[] args) {
        if(cmd.equalsIgnoreCase("create")) {
            p.sendMessage(MessageManager.getInstance().getPrefix() + "Creating new arena");

            if(GameManager.getInstance().exists(p.getWorld())) {
                p.sendMessage(MessageManager.getInstance().getError() + "Can't create arenas on the same world");
                return;
            }
            GameManager.getInstance().createArena(p);
        }
    }
}
