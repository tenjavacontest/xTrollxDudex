/**
 * Name: Listener.java
 * Created: 7 December 2013
 *
 * @version 1.0.0
 * @author AgentTroll
 */
package com.gmail.woodyc40.tenjava.listeners;

import com.gmail.woodyc40.tenjava.Game;
import com.gmail.woodyc40.tenjava.TenJava;
import com.gmail.woodyc40.tenjava.managers.GameManager;
import com.gmail.woodyc40.tenjava.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.material.SpawnEgg;

import java.util.*;

public class Listener implements org.bukkit.event.Listener {

    public final List<Player> list = new ArrayList<>();

    /////////////////////////GAMELISTENER//////////////////////////
    @EventHandler
    public void onMobSend(PlayerInteractEvent e) {
        if(GameManager.getInstance().isInGame(e.getPlayer()) {
            return;
        }
        e.setCancelled(true);
        Material mat = e.getPlayer().getItemInHand().getType();

        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK) && mat.equals(Material.BLAZE_ROD) && GameManager.getInstance().isCreating(e.getPlayer())) {
            GameManager.getInstance().getArena(e.getPlayer()).setSpawn(e.getClickedBlock().getLocation());
            e.getPlayer().sendMessage(MessageManager.getInstance().getPrefix() + "Spawn set. This arena is done.");

            TenJava.getInstance().getConfig().set("Arenas." + GameManager.getInstance().getArena(e.getPlayer()).getId() + ".spawn", GameManager.getInstance().serializeLoc(e.getClickedBlock().getLocation()));
            TenJava.getInstance().saveConfig();

            GameManager.getInstance().notCreating(e.getPlayer());
        } else if(e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            if(list.contains(e.getPlayer()) {
                e.getPlayer().sendMessage(MessageManager.getInstance().getError() + "This is on cool down");
                return;
            }

            Game g = GameManager.getInstance().getArena(e.getPlayer());
            if(GameManager.getInstance().getArena(e.getPlayer()) != null) {
                if(mat.equals(Material.MONSTER_EGG)) {
                    SpawnEgg se = (SpawnEgg) e.getPlayer().getItemInHand().getData();
                    EntityType ent = se.getSpawnedType();

                    spawn(e.getPlayer(), ent);
                    start(e.getPlayer(), 5);
                } else if(mat.equals(Material.IRON_BLOCK)) {
                    g.getSpawn().clone().add(0, 1, 0).getBlock().setType(Material.IRON_BLOCK);
                    start(e.getPlayer(), 30);
                } else if(mat.equals(Material.BLAZE_POWDER)) {
                    for(int i = 0; i <= 4; i++) {
                        g.getWorld().spawn(g.getSpawn(), Blaze.class);
                    }
                    start(e.getPlayer(), 30);
                } else if(mat.equals(Material.GOLD_INGOT)) {
                    int i = GameManager.getInstance().getPlayerData(e.getPlayer()).getSpawnRate() * 4;
                    int in = 0;
                    for(UUID id : g.getCom().uuids) {
                        for(Entity entity : g.getWorld().getEntities()) {
                            if(entity.getUniqueId().equals(id)) {
                                final Location l = entity.getLocation().clone();
                                final Material[] mats = new Material[2];
                                mats[0] = l.subtract(0, 1, 0).getBlock().getType();
                                mats[1] = l.subtract(0, 1, 0).getBlock().getType();

                                l.subtract(0, 1, 0).getBlock().setType(Material.AIR);
                                l.subtract(0, 2, 0).getBlock().setType(Material.AIR);

                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TenJava.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        l.subtract(0, 1, 0).getBlock().setType(mats[0]);
                                        l.subtract(0, 2, 0).getBlock().setType(mats[1]);
                                    }
                                }, 5*20L);
                            }
                        }
                        in++;
                        if(in == i) {
                            break;
                        }
                    }
                }
                start(e.getPlayer(), 30);
            }
            if(mat.equals(Material.WOOD_DOOR)) {
                GameManager.getInstance().removePlayer(e.getPlayer());
            }
            if(mat.equals(Material.STONE_BUTTON)) {
                Bukkit.getServer().dispatchCommand(e.getPlayer(), "mw stats");
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Game g = GameManager.getInstance().getArena(e.getEntity().getWorld());
        if(g != null) {
            assert !g.uuids.isEmpty();
            assert e.getEntity().getKiller() != null;
            if(g.uuids.contains(e.getEntity().getKiller().getUniqueId())) {
                g.kills++;
                GameManager.getInstance().getPlayerData(g.getPlayer()).increment(250);
                if(g.kills == 250) {
                    GameManager.getInstance().removePlayer(g.getPlayer());
                    GameManager.getInstance().getPlayerData(g.getPlayer()).increment();
                    g.getPlayer().sendMessage(MessageManager.getInstance().getPrefix() + "You have won the game, congratz");
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent e) {
        GameManager.getInstance().addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        e.setCancelled(true);
        TNTPrimed tnt = e.getEntity().getWorld().spawn(e.getEntity().getLocation(), TNTPrimed.class);
        tnt.setFireTicks(0);
        tnt.setYield(0);
        tnt.setIsIncendiary(true);
    }

    public Entity spawn(Player p, EntityType et) {
        Entity e = null;
        for(int i = 0; i <= GameManager.getInstance().getPlayerData(p).getSpawnRate(); i++) {
            e = p.getWorld().spawnEntity(GameManager.getInstance().getArena(p).getSpawn(), et);
            for(Entity entity : e.getNearbyEntities(100, 100, 100)) {
                if(GameManager.getInstance().getArena(p).getCom().uuids.contains(entity.getUniqueId())) {
                    ((Creature)e).setTarget((LivingEntity)entity);
                    break;
                }
            }
            GameManager.getInstance().getArena(p).uuids.add(e.getUniqueId());
        }
        GameManager.getInstance().getArena(p).spawn(GameManager.getInstance().getPlayerData(p).getSpawnRate(), e);
        return e;
    }

    public void start(final Player p, final int seconds) {
        list.add(p);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TenJava.getInstance(), new Runnable() {
            @Override
            public void run() {
                list.remove(p);
            }
        }, seconds*20L);
    }

}
