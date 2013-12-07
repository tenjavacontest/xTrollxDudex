/**
 * Name: SubCommand.java
 * Created: 7 December 2013
 *
 * @version 1.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava.command;

import org.bukkit.entity.Player;

public interface SubCommand {

    /**
     * Method to execute command
     *
     * @param p The player that executed the command
     * @param cmd The command executed
     * @param args The arguments following the command
     */
    public void execute(Player p, String cmd, String[] args);

}
