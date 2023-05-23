package it.unibo.geosurv.model.monsters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.stream.Stream;

import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.monsters.types.Rect;
import it.unibo.geosurv.model.monsters.types.Rhombus;
import it.unibo.geosurv.model.monsters.types.Triangle;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

public class MonsterSpawner implements TickingObject {

    private final static int SPAWN_INTERVAL = 500; // specifies the time interval (in milliseconds) between monster
                                                   // spawns (500 = 2 monster each sec)
    private final static int MAX_T_MONSTERS = 5; // 50 // specifies the maximum number of monsters that can be spawned
                                                 // at any given time
    private final static int MAX_R_MONSTERS = 100;
    private static int numMonsters = 0;
    private Handler handler;
    private static GameObject tempPlayer;
    static Game game;
    private static int SPAWN_RATE = 1; // in monsters per second
    private long lastSpawnTime = 0;
    private int currentSecond;
    private long currentTime;
    private long elapsedTime;
    GenerateMonsterT tm = new GenerateMonsterT();
    GenerateMonsterR rm = new GenerateMonsterR();
    private long begin = Game.getStartTime();

    public MonsterSpawner(float x, float y, Handler h) {
        this.handler = h;
        tempPlayer = handler.getPlayer();

    }

    public void spawnMonsters() {
        currentSecond = (int) ((currentTime / 1000)); // update current second
        currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - lastSpawnTime;
        if (currentSecond - begin / 1000 > 11) {
            SPAWN_RATE = 2;
        }
        if (elapsedTime >= 1000 / SPAWN_RATE) {
            // System.out.println(
            // "DIFF: " + (currentSecond - begin / 1000) + " currentSecond: " +
            // currentSecond
            // + " currentTime: " + currentTime
            // + " elapsedTime: " + elapsedTime);

            // Stream.generate(() -> generateMonsters())
            // .forEach(m -> handler.addObject(m));
            Monster x = generateMonsters();
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
    }//

    public static int getNumMonsters() {
        return numMonsters;
    }

    @Override
    public void tick() {
        spawnMonsters();
    }

    private Monster generateMonsters() {

        Monster m = null;

        if ((currentSecond - begin / 1000) < 15) {
            m = tm.createMonster();
            // System.out.println("T");
        } else if (currentSecond - begin / 1000 == 15) {
            m = tm.createMonster();
            m.setBig(true);
            // System.out.println("T: big");
        } else if (currentSecond - begin / 1000 < 25) {
            m = rm.createMonster();
            // System.out.println("R");
        } else if (currentSecond - begin / 1000 == 25) {
            m = rm.createMonster();
            m.setBig(true);
            // System.out.println("R: big");
        } else {
            m = rm.createMonster();
        }
        return m;
    }

    /** monsters created all through the game */
    private void generateFixedPositionMonsters() {
        GenerateMonsterRh rhm = new GenerateMonsterRh();
        Monster rh = rhm.createMonster();
        handler.addObject(rh);
    }

    private void flood() {

        Stream.generate(() -> rm.createMonster())
                .limit(1)
                .forEach(m -> {
                    m.setBig(true);
                    handler.addObject(m);
                });

        // Stream.generate(() -> new Rect())
        // .limit(1)
        // .forEach(m -> {
        // // m.setStartingPosition(x, y);
        // m.setBig(true);
        // handler.addObject(m);
        // });

        // Stream.generate(() -> new Rhombus(tempPlayer.getX() +
        // Func.randomPoint(280.0f, 300.0f).getX(),
        // tempPlayer.getY() + Func.randomPoint(280.0f, 300.0f).getY(), this.handler,
        // game, true))
        // .limit(1)
        // .forEach(m -> handler.addObject(m));

    }

}
