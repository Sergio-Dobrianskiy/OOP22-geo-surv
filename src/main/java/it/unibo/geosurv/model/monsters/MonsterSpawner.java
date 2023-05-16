package it.unibo.geosurv.model.monsters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.stream.Stream;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.monsters.triangle.Rect;
import it.unibo.geosurv.model.monsters.triangle.Rhombus;
import it.unibo.geosurv.model.monsters.triangle.Triangle;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

public class MonsterSpawner extends GameObject {

    private final static int SPAWN_INTERVAL = 500; // specifies the time interval (in milliseconds) between monster
                                                   // spawns (500 = 2 monster each sec)
    private final static int MAX_T_MONSTERS = 5; // 50 // specifies the maximum number of monsters that can be spawned
                                                 // at any given time
    private final static int MAX_R_MONSTERS = 100;
    private static int numMonsters = 0;
    // private static long lastSpawnTime = 0L;
    // private static Random random = new Random();
    private Handler handler;
    private static GameObject tempPlayer;
    static Game game;
    // private float x;
    // private float y;

    private static int SPAWN_RATE = 1; // in monsters per second
    private long lastSpawnTime = 0;
    private int currentSecond;
    private long currentTime;
    private long elapsedTime;
    private long begin = Game.getStartTime();

    public MonsterSpawner(float x, float y, Handler h, Game game2) {
        super(x, y, ID.Spawner);
        this.handler = h;
        tempPlayer = handler.getPlayer();
        // oppure
        // tempPlayer = Game.returnHandler().getPlayer();

    }

    public void spawnMonsters() {
        currentSecond = (int) ((currentTime / 1000)); // update current minute
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

            gm();
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

    // @Override
    // public void render(Graphics g) {
    // // throw new UnsupportedOperationException("Unimplemented method 'render'");
    // }

    @Override
    public Rectangle getShape() {
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }

    private Monster generateMonsterT(boolean isBig) {
        // System.out.println("--> generateMonsterT()");
        return new Triangle(tempPlayer.getX() + Func.randomPoint(420.0f, 500.0f).getX(),
                tempPlayer.getY() + Func.randomPoint(420.0f, 500.0f).getY(), isBig);
    }

    private Monster generateMonsterR(boolean isBig) {
        // System.out.println("--> generateMonsterR()");
        Pair<Float, Float> randomPosition = Func.randomPoint(500.0f, 600.0f);
        x = tempPlayer.getX() + randomPosition.getX();
        y = tempPlayer.getY() + randomPosition.getY();
        return new Rect(x, y, isBig);
    }

    private Monster generateMonsters() {
        Monster m = null;
        if ((currentSecond - begin / 1000) < 10) {
            m = generateMonsterT(false);
        } else if (currentSecond - begin / 1000 == 10) {
            m = generateMonsterT(true);
        } else if (currentSecond - begin / 1000 < 20) {
            m = generateMonsterR(false);
        } else if (currentSecond - begin / 1000 == 20) {
            m = generateMonsterR(true);
        } else {
            m = generateMonsterT(false);
        }
        // System.out.println("Creted Monster: " + m.toString());
        return m;
    }

    private void gm() {

        GenerateMonsterImpl gm = new GenerateMonsterImpl();
        // Monster t = gm.generateMonster("Triangle", true);
        // handler.addObject(t);
        // Monster r = gm.generateMonster("Rect", false);
        // handler.addObject(r);
        Monster rh = gm.generateMonster("Rhombus", false);
        handler.addObject(rh);
    }

    private void flood() {
        Stream.generate(() -> new Rect(tempPlayer.getX() + Func.randomPoint(500.0f, 600.0f).getX(),
                tempPlayer.getY() + Func.randomPoint(500.0f, 600.0f).getY(), true))
                .limit(1)
                .forEach(m -> handler.addObject(m));

        // Stream.generate(() -> new Rhombus(tempPlayer.getX() +
        // Func.randomPoint(280.0f, 300.0f).getX(),
        // tempPlayer.getY() + Func.randomPoint(280.0f, 300.0f).getY(), this.handler,
        // game, true))
        // .limit(1)
        // .forEach(m -> handler.addObject(m));

    }

}
