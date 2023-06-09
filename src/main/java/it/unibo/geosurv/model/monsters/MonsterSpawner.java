package it.unibo.geosurv.model.monsters;

import java.util.stream.Stream;
import it.unibo.geosurv.control.ITickingObject;
import it.unibo.geosurv.model.Handler;

/**
 * Class to manage spawn of monsters.
 */
public class MonsterSpawner implements ITickingObject {

    private static final int TIME_LEVEL_1 = 20;
    private static final int TIME_LEVEL_2 = 40;
    private static final int TIME_LEVEL_3 = 60;
    private final Handler handler;
    private static int spawnRate = 1; // in monsters per second
    private final long begin;
    private final GenerateMonsterT tm;
    private final GenerateMonsterR rm;
    private final GenerateMonsterRh rhm;
    private long lastSpawnTime;
    private int currentSecond;
    private long currentTime;

    /**
     * Inizialize monster creation factories.
     * 
     * @param h handler
     */
    public MonsterSpawner(final Handler h) {
        this.handler = h;
        this.tm = new GenerateMonsterT();
        this.rm = new GenerateMonsterR();
        this.rhm = new GenerateMonsterRh();
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
        // System.out.println(diff() + " :: " + this.begin + " :: " + (currentSecond -
        // this.begin / 1000));
        elapsedTime = currentTime - lastSpawnTime;
        if (diff() > TIME_LEVEL_1) {
            spawnRate = 2;
        }
        if (elapsedTime >= 1000 / spawnRate) {
            final Monster x = generateMonsters();
            handler.addObject(x);
            lastSpawnTime = currentTime;

            generateFixedPositionMonsters();
        }
        if (diff() > TIME_LEVEL_3) {
            spawnRate = 3;
            // if (elapsedTime >= 1000 / SPAWN_RATE) {
            flood();
            // }
        }
    }

    @Override
    public final void tick() {
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

        if ((diff()) < TIME_LEVEL_1) {
            m = tm.createMonster(this.handler);
        } else if (diff() == TIME_LEVEL_1) {
            m = tm.toBig().createMonster(this.handler);
            // m.setBig();
        } else if (diff() < TIME_LEVEL_2) {
            m = rm.createMonster(this.handler);
        } else if (diff() == TIME_LEVEL_2) {
            m = rm.toBig().createMonster(this.handler);
            // m.setBig();
        } else {
            m = rm.createMonster(this.handler);
        }

        return m;
    }

    /**
     * monsters created all through the game.
     */
    private void generateFixedPositionMonsters() {
        final Monster rh = rhm.toBig().createMonster(this.handler);
        handler.addObject(rh);
    }

    /**
     * massive flood of monsters.
     */
    private void flood() {

        Stream.generate(() -> rm.toBig().createMonster(this.handler))
                .limit(1)
                .forEach(m -> handler.addObject(m));

    }

    /**
     * @return time in seconds from the begin of game
     */
    final int diff() {
        return (int) (currentSecond - this.begin);
    }

}
