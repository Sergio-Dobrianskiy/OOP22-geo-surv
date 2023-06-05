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
    // private static GameObject tempPlayer;
    // static Game game;
    private static int spawnRate = 1; // in monsters per second
    private final long begin;
    private final GenerateMonsterT tm;
    private final GenerateMonsterR rm;
    private long lastSpawnTime;
    private int currentSecond;
    private long currentTime;

    public MonsterSpawner(final Handler h) {
        this.handler = h;
        this.tm = new GenerateMonsterT();
        this.rm = new GenerateMonsterR();
        this.begin = System.currentTimeMillis() / 1000;

    }

    /**
     * Spawn monsters all around the player
     * Creation is time based.
     */
    public void spawnMonsters() {
        long elapsedTime;

        currentTime = System.currentTimeMillis();
        currentSecond = (int) ((currentTime / 1000)); // update current second
        // final int diff = (int) (currentSecond - this.begin);
        //System.out.println(diff + " :: " + this.begin + " :: " + (currentSecond - this.begin / 1000));
        elapsedTime = currentTime - lastSpawnTime;
        if (diff() > 21) {
            spawnRate = 2;
        }
        if (elapsedTime >= 1000 / spawnRate) {
            final Monster x = generateMonsters();
            handler.addObject(x);
            lastSpawnTime = currentTime;

            generateFixedPositionMonsters();
        }
        if (diff() > 60) {
            spawnRate = 3;
            // if (elapsedTime >= 1000 / SPAWN_RATE) {
            flood();
            // }
        }
    }

    @Override
    public void tick() {
        spawnMonsters();
    }

    /**
     * create different type of monsters based on time.
     * 
     * @return monster.
     */
    private Monster generateMonsters() {

        currentSecond = (int) ((currentTime / 1000)); // update current second
        // final int diff = (int) (currentSecond - this.begin);
        Monster m;

        if ((diff()) < 20) {
            m = tm.createMonster(this.handler);

        } else if (diff() == 20) {
            m = tm.createMonster(this.handler);
            m.setIsBig(true);
        } else if (diff() < 40) {
            m = rm.createMonster(this.handler);
        } else if (diff() == 40) {
            m = rm.createMonster(this.handler);
            m.setIsBig(true);
        } else {
            m = rm.createMonster(this.handler);
        }
        return m;
    }

    /**
     * monsters created all through the game
     */
    private void generateFixedPositionMonsters() {
        final GenerateMonsterRh rhm = new GenerateMonsterRh();
        final Monster rh = rhm.createMonster(this.handler);
        handler.addObject(rh);
    }

    /**
     * massive flood of monsters.
     */
    private void flood() {

        Stream.generate(() -> rm.createMonster(this.handler))
                .limit(1)
                .forEach(m -> {
                    m.setIsBig(true);
                    handler.addObject(m);
                });

    }

    /**
     * @return time in seconds from the begin of game
     */
    final int diff() {
        return (int) (currentSecond - this.begin);
    }

}
