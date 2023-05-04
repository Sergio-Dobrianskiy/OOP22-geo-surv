package it.unibo.geosurv.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import it.unibo.geosurv.entity.Monster;
import it.unibo.geosurv.entity.Triangle;
import it.unibo.geosurv.utility.Func;

public class MonsterSpawner extends GameObject {

    private final static int SPAWN_INTERVAL = 1000; // specifies the time interval (in milliseconds) between monster
                                                    // spawns 3 secs
    private final static int MAX_MONSTERS = 10; // specifies the maximum number of monsters that can be spawned at any
                                                // given time
    private static int numMonsters = 0;
    private static long lastSpawnTime = 0L;
    private static Random random = new Random();
    private Handler handler;
    private static GameObject tempPlayer;
    static Game game;

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
        if (currentTime - lastSpawnTime >= SPAWN_INTERVAL && numMonsters < MAX_MONSTERS) {
            // TODO: select monster based on time from begin of game
            // int monsterType = ...
            // Monster monster = null;

            // sure NOT WORKING
            Monster monster = new Triangle(10, 10, ID.Monster, this.handler, game);
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
                System.out.println("M created: " + getNumMonsters() + " " + monster.toString());
                // Spawn the monster
                // Calculate a random point that is a distance of 100 pixels away from the
                // player in a random direction
                // float angle = (float) (random.nextFloat() * 2 * Math.PI);
                // float distance = 10.0f;
                // float x = tempPlayer.getX() + distance * (float) Math.cos(angle);
                // float y = tempPlayer.getY() + distance * (float) Math.sin(angle);
                // System.out.println("x: " + x + ", y: " + y);
                //// Check if the random point is outside of the camera view
                //// if (!isPointInCameraView(x, y, Camera cam, float xx, float yy )) {
                //// Spawn the monster at the random point
                //// monster.setPosition(x, y);
                //// Add the monster to the game world
                monster.setX(x);
                monster.setY(y);
                this.handler.addObject(monster);
                numMonsters++;
                lastSpawnTime = currentTime;
                // }
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
