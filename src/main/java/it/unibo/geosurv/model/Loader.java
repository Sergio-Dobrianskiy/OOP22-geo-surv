package it.unibo.geosurv.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import it.unibo.geosurv.control.weapons.AutoGun;
import it.unibo.geosurv.control.weapons.ExplosionGun;
import it.unibo.geosurv.control.weapons.LaserGun;
import it.unibo.geosurv.control.weapons.SatelliteGun;
import it.unibo.geosurv.control.weapons.Weapon;
import it.unibo.geosurv.model.block.Block;
import it.unibo.geosurv.model.block.BlockFactory;
import it.unibo.geosurv.model.block.BlockType;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.view.graphics.Camera;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents the game assets Loader class.
 */
public class Loader {

    /**
     * default game window height.
     */
    public static final int GAME_GRID_HEIGHT = 32;
    /**
     * default game window width.
     */
    public static final int GAME_GRID_WIDTH = 32;
    /**
     * x coordinate for player starting position.
     */
    private final int playerStartingX = 150;
    /**
     * y coordinate for player starting position.
     */
    private final int playerStartingY = 300;
    /**
     * maximum value for RGB.
     */
    private final int maxRGB = 255;

    private Handler handler;

    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public Loader(final Handler handler) {
        this.handler = handler;
    }

    /**
     * loads main game elements.
     * 
     */
    public void loadAll() {
        handler.addPlayer(new MainPlayer(playerStartingX, playerStartingY, handler)); // TODO: remove?
        this.loadTextures();
        this.loadGuns();
        loadLevel(Texture.SMALL_MAP.extractTexture());
        // loadLevel(Texture.TEST_MAP.getTexture());
        // loadLevel(Texture.BIG_MAP_2.getTexture());

    }

    /**
     * loads the game camera.
     * 
     * @return Camera 
     */
    public Camera loadCamera() {
        return new Camera(handler.getPlayer().getX(), handler.getPlayer().getY(), handler);
    }

    /**
     * Loads game textures.
     */
    private void loadTextures() {
        for (final Texture texture : Texture.values()) {
            try {
                texture.load();
            } catch (IOException e) {
                System.out.println("Error while loading texture " + texture.getPath());
                System.out.println(e);
                System.exit(1);
            }
        }
    }

    /**
     * Loads game Weapons/Guns.
     */
    private void loadGuns() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        Weapon autogun = (Weapon) handler.addTickingObject(new AutoGun(this.handler));
        Weapon satelliteGun = (Weapon) handler.addTickingObject(new SatelliteGun(this.handler));
        Weapon explosionGun = (Weapon) handler.addTickingObject(new ExplosionGun(this.handler));
        Weapon laserGun = (Weapon) handler.addTickingObject(new LaserGun(this.handler));
        autogun.levelUp();              // each game starts with level 1 AutoGun
        weapons.add(autogun);
        weapons.add(satelliteGun);
        weapons.add(explosionGun);
        weapons.add(laserGun);
        handler.getPlayer().setWeapons(weapons);
    }

    /**
     * Load the game world.
     *
     * @param image that models the game world
     */
    private void loadLevel(final BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BlockFactory blockFactory = new BlockFactory();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                // int red = (pixel >> 16) & 0xff;
                // green not used at the moment
                // int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (blue == maxRGB) {
                    final Optional<Block> block = blockFactory.createBlock(BlockType.Wall, xx * GAME_GRID_WIDTH, yy * GAME_GRID_HEIGHT);
                    if (block.isEmpty()) {
                        System.out.println("Error creating Wall!");
                    }
                    handler.addObject(block.get());
                }

                // if (red == 255) {
                // // handler.addPlayer(new MainPlayer(xx * GAME_GRID_WIDTH, yy *
                // GAME_GRID_HEIGHT,
                // // ID.Player, handler));
                // }
            }
        }
    }

}
