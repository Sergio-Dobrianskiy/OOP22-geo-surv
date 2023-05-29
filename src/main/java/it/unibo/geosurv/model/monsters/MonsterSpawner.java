package it.unibo.geosurv.model.monsters;

import java.util.stream.Stream;

import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.model.Game;
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
    private static int SPAWN_RATE = 1; // in monsters per second
    private long lastSpawnTime;
    private int currentSecond;
    private long currentTime;
    GenerateMonsterT tm = new GenerateMonsterT();
    GenerateMonsterR rm = new GenerateMonsterR();
    private final long begin = Game.getStartTime();

    public MonsterSpawner(final Handler h) {
        this.handler = h;
    }

    public void spawnMonsters() {
        long elapsedTime;
        currentSecond = (int) ((currentTime / 1000)); // update current second
        currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - lastSpawnTime;
        if (currentSecond - begin / 1000 > 11) {
            SPAWN_RATE = 2;
        }
        if (elapsedTime >= 1000 / SPAWN_RATE) {
            final MonsterImpl x = generateMonsters();
            handler.addObject(x);
            lastSpawnTime = currentTime;

            generateFixedPositionMonsters();
        }
        if (currentSecond - begin / 1000 > 40) {
            SPAWN_RATE = 3;
            if (elapsedTime >= 1000 / SPAWN_RATE) {
                flood();
            }
        }
    }

    public static int getNumMonsters() {
        return numMonsters;
    }

    @Override
    public void tick() {
        spawnMonsters();
    }

    private MonsterImpl generateMonsters() {

        MonsterImpl m;

        if ((currentSecond - begin / 1000) < 15) {
            m = tm.createMonster(this.handler);
            // System.out.println("T");
        } else if (currentSecond - begin / 1000 == 15) {
            m = tm.createMonster(this.handler);
            m.setBig(true);
            // System.out.println("T: big");
        } else if (currentSecond - begin / 1000 < 25) {
            m = rm.createMonster(this.handler);
            // System.out.println("R");
        } else if (currentSecond - begin / 1000 == 25) {
            m = rm.createMonster(this.handler);
            m.setBig(true);
            // System.out.println("R: big");
        } else {
            m = rm.createMonster(this.handler);
        }
        return m;
    }

    /** monsters created all through the game */
    private void generateFixedPositionMonsters() {
        final GenerateMonsterRh rhm = new GenerateMonsterRh();
        final MonsterImpl rh = rhm.createMonster(this.handler);
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
