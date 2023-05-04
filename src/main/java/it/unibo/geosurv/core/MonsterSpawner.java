package it.unibo.geosurv.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import it.unibo.geosurv.entity.Monster;
import it.unibo.geosurv.entity.Triangle;
import it.unibo.geosurv.utility.Func;

public class MonsterSpawner extends GameObject {

    private final static int SPAWN_INTERVAL = 3000; // specifies the time interval (in milliseconds) between monster
                                                    // spawns 3 secs
    private final static int MAX_MONSTERS = 10; // specifies the maximum number of monsters that can be spawned at any
                                                // given time
    private static int numMonsters = 0;
    private static long lastSpawnTime = 0L;
    private static Random random = new Random();
    private static Handler handler;
    private static GameObject tempPlayer;
    static Game game;

    private MonsterSpawner(float x, float y, ID id) {
        super(x, y, id);
        // TODO Auto-generated constructor stub
    }

    public static void spawnMonsters() {

        // look for the the player
        tempPlayer = Func.findPlayer(handler);

        // Check if it's time to spawn a new monster
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime >= SPAWN_INTERVAL && numMonsters < MAX_MONSTERS) {
            // TODO: select monster based on time from begin of game
            // int monsterType = ...
            // Monster monster = null;

            // sure NOT WORKING
            Monster monster = new Triangle(20, 50, ID.Monster, handler, game);

            // switch (monsterType) {
            // case 0:
            // // monster = new Triangle();
            // break;
            // case 1:
            // // monster = new Square();
            // break;
            // case 2:
            // // monster = new Polygon();
            // break;
            // default:
            // // Should never happen
            // System.err.println("Unknown monster type: " + monsterType);
            // break;
            // }
            if (monster != null) {
                // Spawn the monster
                // Calculate a random point that is a distance of 100 pixels away from the
                // player in a random direction
                float angle = (float) (random.nextFloat() * 2 * Math.PI);
                float distance = 100.0f;
                float x = Func.findPlayer(handler).getX() + distance * (float) Math.cos(angle);
                float y = Func.findPlayer(handler).getY() + distance * (float) Math.sin(angle);

                // Check if the random point is outside of the camera view
                // if (!isPointInCameraView(x, y, Camera cam, float xx, float yy )) {
                // Spawn the monster at the random point
                // monster.setPosition(x, y);
                // Add the monster to the game world
                // ...
                numMonsters++;
                lastSpawnTime = currentTime;
                // }
            }
        }
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }

}
