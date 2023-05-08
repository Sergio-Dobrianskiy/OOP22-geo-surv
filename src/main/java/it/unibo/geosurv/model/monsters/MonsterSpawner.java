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
import it.unibo.geosurv.model.monsters.triangle.Triangle;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

public class MonsterSpawner extends GameObject {

    private final static int SPAWN_INTERVAL = 500; // specifies the time interval (in milliseconds) between monster
                                                   // spawns (500 = 2 monster each sec)
    private final static int MAX_T_MONSTERS = 5; // 50 // specifies the maximum number of monsters that can be spawned
                                                 // at any
                                                 // given time
    private final static int MAX_R_MONSTERS = 100;
    private final static int IILEV = 30;
    private static int numMonsters = 0;
    private static long lastSpawnTime = 0L;
    private static Random random = new Random();
    private Handler handler;
    private static GameObject tempPlayer;
    static Game game;
    float x, y;

    public MonsterSpawner(float x, float y, Handler h, Game game2) {
        super(x, y, ID.Spawner);
        this.handler = h;
        tempPlayer = handler.getPlayer();
        // sopra o sotto
        // tempPlayer = Game.returnHandler().getPlayer();
    }

    public void spawnMonsters() {

        // Check if it's time to spawn a new monster
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastSpawnTime >= SPAWN_INTERVAL && numMonsters < MAX_T_MONSTERS) {
            // TODO: select monster based on time from begin of game

            // Pair<Float, Float> pair = Func.randomPoint(420.0f, 500.0f);
            //
            // x = tempPlayer.getX() + pair.getX();
            // y = tempPlayer.getY() + pair.getY();
            Stream.generate(() -> new Triangle(tempPlayer.getX() + Func.randomPoint(420.0f, 500.0f).getX(),
                    tempPlayer.getY() + Func.randomPoint(420.0f, 500.0f).getY(), this.handler, game, false)).limit(1)
                    .forEach(i -> handler.addObject(i)); // 1->50
            // 5->250

            numMonsters++;
            lastSpawnTime = currentTime;
        }

        // if ((currentTime - lastSpawnTime) >= (SPAWN_INTERVAL * IILEV) && numMonsters
        // < MAX_R_MONSTERS) {
        //
        // Stream.generate(() -> new Rect(tempPlayer.getX() + Func.randomPoint(500.0f,
        // 600.0f).getX(),
        // tempPlayer.getY() + Func.randomPoint(500.0f, 600.0f).getY(), this.handler,
        // game, false)).limit(50)
        // .forEach(i -> handler.addObject(i));
        // numMonsters++;
        // lastSpawnTime = currentTime;
        // }

    }

    public static int getNumMonsters() {
        return numMonsters;
    }

    @Override
    public void tick() {
        spawnMonsters();
    }

    @Override
    public void render(Graphics g) {
        // throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public Rectangle getShape() {
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }

}
