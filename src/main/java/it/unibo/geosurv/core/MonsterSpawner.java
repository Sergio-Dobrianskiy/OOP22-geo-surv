package it.unibo.geosurv.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import it.unibo.geosurv.utility.Pair;
import it.unibo.geosurv.entity.Monster;
import it.unibo.geosurv.entity.Triangle;
import it.unibo.geosurv.utility.Func;

public class MonsterSpawner extends GameObject {

    private final static int SPAWN_INTERVAL = 500; // specifies the time interval (in milliseconds) between monster
                                                   // spawns 3 secs
    private final static int MAX_MONSTERS = 50; // specifies the maximum number of monsters that can be spawned at any
                                                // given time
    private static int numMonsters = 0;
    private static long lastSpawnTime = 0L;
    private static Random random = new Random();
    private Handler handler;
    private static GameObject tempPlayer;
    static Game game;
    float x, y;

    public MonsterSpawner(float x, float y, ID id, Handler h, Game game2) {
        super(x, y, id);
        this.handler = h;
    }

    public void spawnMonsters() {

        // look for the the player
        tempPlayer = Func.findPlayer(this.handler);
        // System.out.println(tempPlayer);

        // Check if it's time to spawn a new monster
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) { // TODO: adapt magic number 1000
            if (currentTime - lastSpawnTime >= SPAWN_INTERVAL && numMonsters < MAX_MONSTERS) {
                // TODO: select monster based on time from begin of game

                // TODO: con x = 350 => Exception in thread "Thread-0"
                // java.lang.IllegalArgumentException:
                // bound must be greater than origin at
                // java.base/jdk.internal.util.random.RandomSupport.checkRange(Unknown Source)
                Pair<Float, Float> pair = Func.randomPoint(420.0f, 500.0f);
                GameObject tempPlayer = handler.getPlayer();
                x = tempPlayer.getX() + pair.getX();
                y = tempPlayer.getY() + pair.getY();
                this.handler.addObject(new Triangle(x, y, ID.Monster, this.handler, game));
                // Monster monster = new Triangle(10, 10, ID.Monster, this.handler, game);

                numMonsters++;
                lastSpawnTime = currentTime;
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

    @Override
    public void render(Graphics g) {
        // throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }

}
