/**
 * Name: CommandHandler.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava.command;

import com.gmail.woodyc40.tenjava.managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler implements CommandExecutor {

    static Map<String, SubCommand> cmds = new HashMap<>();

    public static void register(String cmd, SubCommand sc) {
        cmds.put(cmd, sc);
    }

    private static SubCommand getCommand(String cmd) {
        return cmds.containsKey(cmd) ? cmds.get(cmd) : null;
    }

    public static void execute(Player p, String[] args) {
        if(getCommand(args[0]) == null) {
            p.sendMessage(MessageManager.getInstance().getError() + "This command does not exist!");
            return;
        }
        String[] newArgs = new String[args.length - 1];
        for(int i = 1; i <= args.length; i++) {
            newArgs[i - 1] = args[i];
        }

        getCommand(args[0]).execute(p, args[0], newArgs);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("mw")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(MessageManager.getInstance().getError() + "You're not a player -_-");
                return true;
            }

            Player p = (Player) sender;
            if(args.length < 1) {
                MessageManager.getInstance().sendHelp(p, 1, "/mw create: Sets the current world to an arena",
                    "/mw stats: Shows your stats"
                    "/mw join [Arena ID]: Joins an arena");
                return true;
            }
            if(args.length >= 1) {
                execute(p, args);
                return true;
            }
        }

        return false;
    }

}
