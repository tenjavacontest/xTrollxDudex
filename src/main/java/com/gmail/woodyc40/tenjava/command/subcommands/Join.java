/**
 * Name: Join.java
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

public class Join implements SubCommand {

    public void execute(Player p, String cmd, String[] args) {
        if(cmd.equalsIgnoreCase("join") && args.length >= 1) {
            int in = 0;
            try {
                in = Integer.parseInt(args[0]);
            } catch(NumberFormatException x) {
                p.sendMessage(MessageManager.getInstance().getError() + "You can't join that arena");
            }
            GameManager.getInstance().addPlayer(p, in);
        }
    }

}
