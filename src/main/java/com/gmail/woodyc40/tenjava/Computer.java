package com.gmail.woodyc40.tenjava;

import com.gmail.woodyc40.tenjava.managers.GameManager;
import com.gmail.woodyc40.tenjava.managers.MessageManager;
import net.minecraft.server.v1_7_R1.EntityInsentient;
import net.minecraft.server.v1_7_R1.Navigation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Computer {

    public List<UUID> uuids = new ArrayList<>();

    Game game = null;
    public Computer(Game g) {
        game = g;
    }

    public void spawn(int in, EntityType et) {
        if(new Random().nextBoolean()) {
            for(int x = 0; x <= in * new Random().nextInt(6); x++) {
                final Entity ent = game.getWorld().spawnEntity(game.getSpawn(), et);
                uuids.add(ent.getUniqueId());

                Navigation nav = ((EntityInsentient)ent).getNavigation();
                nav.a(game.getSpawn().getX(), game.getSpawn().getY(), game.getSpawn().getZ(), 0.3F);
                Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(TenJava.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        if(ent.isValid() && !ent.isDead()) {
                            if(ent.getLocation().equals(game.getSpawn())) {
                                game.kills--;
                                game.getPlayer().sendMessage(MessageManager.getInstance().getPrefix() + "Your spawn just lost morale.");
                                if(game.kills <= 0) {
                                    game.getPlayer().sendMessage(MessageManager.getInstance().getError() + "You have lost the game!");
                                    GameManager.getInstance().removePlayer(game.getPlayer());
                                }
                            }
                        }
                    }
                }, 0L, 60*20L);
            }
        }
    }

}
