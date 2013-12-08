package com.gmail.woodyc40.tenjava.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Trolldood3 aka xTrollxDudex aka AgentTroll
 */
public class MessageManager {

    private static final MessageManager mm = new MessageManager();

    protected MessageManager(){}

    public static MessageManager getInstance() {
        return mm;
    }

    public String getError() {
        return ChatColor.GREEN + "[" + ChatColor.DARK_AQUA + "MobWar" + ChatColor.GREEN + "]" + ChatColor.RED;
    }

    public String getPrefix() {
        return ChatColor.GREEN + "[" + ChatColor.DARK_AQUA + "MobWar" + ChatColor.GREEN + "]" + ChatColor.BLUE;
    }

    public void sendHelp(Player p, int page, String... help) {
        p.sendMessage(ChatColor.GREEN + "----------" + getPrefix() + ChatColor.GREEN + "----------");
        p.sendMessage(ChatColor.GOLD + "Page: " + ChatColor.DARK_BLUE + page + ChatColor.GOLD + "/1");
        for(String s : help) {
            String[] split = s.split(": ");

            p.sendMessage(ChatColor.LIGHT_PURPLE + split[0] + ChatColor.GRAY + ": " + ChatColor.BLUE + split[1]);
        }
        p.sendMessage(ChatColor.GREEN + "----------[" + ChatColor.DARK_AQUA + "End of help" + ChatColor.GREEN + "]----------");
    }

}
