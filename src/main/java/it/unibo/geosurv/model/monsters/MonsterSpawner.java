package it.unibo.geosurv.model.monsters;

import java.util.stream.Stream;

import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.model.Handler;

public class MonsterSpawner implements TickingObject {

    // private final static int SPAWN_INTERVAL = 500; // specifies the time interval
    // (in milliseconds) between monster
    // // spawns (500 = 2 monster each sec)
    // private final static int MAX_T_MONSTERS = 5; // 50 // specifies the maximum
    // number of monsters that can be spawned
    // // at any given time
    // private final static int MAX_R_MONSTERS = 100;
    private final Handler handler;
    private static int numMonsters;
    // private static GameObject tempPlayer;
    // static Game game;
    private static int spawnRate = 1; // in monsters per second
    private long lastSpawnTime;
    private int currentSecond;
    private long currentTime;
    private long begin;
    private final GenerateMonsterT tm;
    private final GenerateMonsterR rm;

    public MonsterSpawner(final Handler h) {
        this.handler = h;
        this.tm = new GenerateMonsterT();
        this.rm = new GenerateMonsterR();
        this.begin = System.currentTimeMillis() / 1000;

    }

    public void spawnMonsters() {
        long elapsedTime;

        currentTime = System.currentTimeMillis();
        currentSecond = (int) ((currentTime / 1000)); // update current second
        int diff = (int) (currentSecond - this.begin);
        System.out.println(diff + " :: " + this.begin + " :: " + (currentSecond - this.begin / 1000));
        elapsedTime = currentTime - lastSpawnTime;
        if (diff > 11) {
            spawnRate = 2;
        }
        if (elapsedTime >= 1000 / spawnRate) {
            final Monster x = generateMonsters();
            handler.addObject(x);
            lastSpawnTime = currentTime;

            generateFixedPositionMonsters();
        }
        if (diff > 40) {
            spawnRate = 3;
            // if (elapsedTime >= 1000 / SPAWN_RATE) {
            flood();
            // }
        }
    }

    public static int getNumMonsters() {
        return numMonsters;
    }

    @Override
    public void tick() {
        spawnMonsters();
    }

    private Monster generateMonsters() {

        currentSecond = (int) ((currentTime / 1000)); // update current second
        int diff = (int) (currentSecond - this.begin);
        Monster m;

        if ((diff) < 15) {
            m = tm.createMonster(this.handler);

        } else if (diff == 15) {
            m = tm.createMonster(this.handler);
            m.setBig(true);
        } else if (diff < 25) {
            m = rm.createMonster(this.handler);
        } else if (diff == 25) {
            m = rm.createMonster(this.handler);
            m.setBig(true);
        } else {
            m = rm.createMonster(this.handler);
        }
        return m;
    }

    /** monsters created all through the game */
    private void generateFixedPositionMonsters() {
        final GenerateMonsterRh rhm = new GenerateMonsterRh();
        final Monster rh = rhm.createMonster(this.handler);
        handler.addObject(rh);
    }

    private void flood() {

        Stream.generate(() -> rm.createMonster(this.handler))
                .limit(1)
                .forEach(m -> {
                    m.setBig(true);
                    handler.addObject(m);
                });

    }

}
