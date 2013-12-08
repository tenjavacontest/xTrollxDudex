package com.gmail.woodyc40.tenjava;

import java.util.ArrayList;
import java.util.List;
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
                Entity ent = game.getWorld().spawnEntity(game.getSpawn(), et);
                uuids.add(ent.getUniqueId()); //TODO Set target at spawn, spawn away from the spawn
            }
        }
    }

}
