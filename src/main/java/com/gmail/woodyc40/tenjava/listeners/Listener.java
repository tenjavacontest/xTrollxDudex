/**
 * Name: Listener.java
 * Created: 7 December 2013
 *
 * @version 1.0.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava.listeners;

import com.gmail.woodyc40.tenjava.Game;
import com.gmail.woodyc40.tenjava.managers.GameManager;
import com.gmail.woodyc40.tenjava.managers.MessageManager;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.SpawnEgg;

import java.util.UUID;

public class Listener implements org.bukkit.event.Listener {

    /////////////////////////GAMELISTENER//////////////////////////
    @EventHandler
    public void onMobSend(PlayerInteractEvent e) {
        Material mat = e.getPlayer().getItemInHand().getType();

        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK) && mat.equals(Material.BLAZE_ROD) && GameManager.getInstance().isCreating(e.getPlayer())) {
            GameManager.getInstance().getArena(e.getPlayer()).setSpawn(e.getClickedBlock().getLocation());
            e.getPlayer().sendMessage(MessageManager.getInstance().getPrefix() + "Spawn set. This arena is done.");
            GameManager.getInstance().notCreating(e.getPlayer());
        } else if(e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            Game g = GameManager.getInstance().getArena(e.getPlayer());
            if(GameManager.getInstance().getArena(e.getPlayer()) != null) {
                if(mat.equals(Material.MONSTER_EGG)) {
                    SpawnEgg se = (SpawnEgg) e.getPlayer().getItemInHand().getData();
                    EntityType ent = se.getSpawnedType();

                    spawn(e.getPlayer(), ent);
                } else if(mat.equals(Material.IRON_BLOCK)) {
                    g.getSpawn().clone().add(0, 1, 0).getBlock().setType(Material.IRON_BLOCK);
                } else if(mat.equals(Material.BLAZE_POWDER)) {
                    for(int i = 0; i <= 4; i++) {
                        g.getWorld().spawn(g.getSpawn(), Blaze.class);
                    }
                } else if(mat.equals(Material.GOLD_INGOT)) {
                    int i = GameManager.getInstance().getPlayerData(e.getPlayer()).getSpawnRate() * 4;
                    int in = 0;
                    for(UUID id : g.getCom().uuids) {
                        for(Entity entity : g.getWorld().getEntities()) {
                            if(entity.getUniqueId().equals(id)) {
                                entity.getLocation().clone().subtract(0, 1, 0).getBlock().setType(Material.AIR);
                                entity.getLocation().clone().subtract(0, 2, 0).getBlock().setType(Material.AIR);
                            }
                        }
                        in++;
                        if(in == i) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public Entity spawn(Player p, EntityType et) {
        Entity e = null;
        for(int i = 0; i <= GameManager.getInstance().getPlayerData(p).getSpawnRate(); i++) {
            e = p.getWorld().spawnEntity(GameManager.getInstance().getArena(p).getSpawn(), et);//TODO set targets
            GameManager.getInstance().getArena(p).uuids.add(e.getUniqueId());
        }
        GameManager.getInstance().getArena(p).spawn(GameManager.getInstance().getPlayerData(p).getSpawnRate(), e);
        return e;
    }

}
