package it.unibo.geosurv.model.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import it.unibo.geosurv.control.weapons.Weapon;
import it.unibo.geosurv.control.weapons.WeaponFactory;
import it.unibo.geosurv.control.weapons.WeaponType;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.block.Block;
import it.unibo.geosurv.model.block.BlockFactory;
import it.unibo.geosurv.model.block.BlockType;
import it.unibo.geosurv.model.monsters.MonsterSpawner;
import it.unibo.geosurv.model.player.Player;
import it.unibo.geosurv.view.graphics.Camera;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents the game assets Loader class.
 */
public class Loader implements ILoader {

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

    private final Handler handler;

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
        handler.addPlayer(new Player(playerStartingX, playerStartingY, handler));
        this.loadTextures();
        this.loadGuns();
        loadLevel(Texture.SMALL_MAP.extractTexture());
        // loadLevel(Texture.TEST_MAP.getTexture());
        // loadLevel(Texture.BIG_MAP_2.getTexture());
        handler.addTickingObject(new MonsterSpawner(handler));

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
        final WeaponFactory weaponFactory = new WeaponFactory(this.handler);
        final ArrayList<Weapon> weapons = new ArrayList<>();

        Optional<Weapon> weapon = weaponFactory.createWeapon(WeaponType.AUTO_GUN, 1);
        if (weapon.isEmpty()) {
            System.out.println("Error creating AutoGun!");
        } else {
            weapons.add(weapon.get());
        }
        weapon = weaponFactory.createWeapon(WeaponType.SATELLITE_GUN, 0);
        if (weapon.isEmpty()) {
            System.out.println("Error creating SatelliteGun!");
        } else {
            weapons.add(weapon.get());
        }
        weapon = weaponFactory.createWeapon(WeaponType.EXPLOSION_GUN, 0);
        if (weapon.isEmpty()) {
            System.out.println("Error creating ExplosionGun!");
        } else {
            weapons.add(weapon.get());
        }
        weapon = weaponFactory.createWeapon(WeaponType.LASER_GUN, 0);
        if (weapon.isEmpty()) {
            System.out.println("Error creating LaserGun!");
        } else {
            weapons.add(weapon.get());
        }

        weapons.forEach(handler::addTickingObject);
        handler.getPlayer().setWeapons(weapons);
    }

    /**
     * Load the game world.
     *
     * @param image that models the game world
     */
    private void loadLevel(final BufferedImage image) {
        final int w = image.getWidth();
        final int h = image.getHeight();
        final BlockFactory blockFactory = new BlockFactory();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                final int pixel = image.getRGB(xx, yy);
                // int red = (pixel >> 16) & 0xff;
                // green not used at the moment
                // int green = (pixel >> 8) & 0xff;
                final int blue = (pixel) & 0xff;

                if (blue == maxRGB) {
                    final Optional<Block> block = blockFactory.createBlock(BlockType.WALL, xx * GAME_GRID_WIDTH, yy * GAME_GRID_HEIGHT);
                    if (block.isEmpty()) {
                        System.out.println("Error creating Wall!");
                    } else {
                        handler.addObject(block.get());
                    }
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
