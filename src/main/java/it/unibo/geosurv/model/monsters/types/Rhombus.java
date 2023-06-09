package it.unibo.geosurv.model.monsters.types;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Class for enemies which do not move.
 */
public class Rhombus extends Monster {

    private static final int DEFAULT_HEALTH = 2;
    private static final int DEFAULT_H_DIMENSION = 32;
    private static final int DEFAULT_W_DIMENSION = 32;
    private static int counter;
    private String name = "Rhombus-";

    /**
     * Rhombus constructor.
     * 
     * @param h handler
     */
    public Rhombus(final Handler h) {
        super(0, 0, h);
        Rhombus.counter++;
        this.name = name + Rhombus.counter;
        this.health = DEFAULT_HEALTH;
        this.power = 2;
        this.speed = 0;
        this.height = DEFAULT_H_DIMENSION;
        this.width = DEFAULT_W_DIMENSION;
        this.texture = Texture.RHOMBUS;
    }

    /**
     * do nothing on tick().
     */
    @Override
    public void tick() {
        /* do nothing on tick() because object remains where it's created */
    }

    @Override
    public void setBig() {
        // Rhombus cannot be bigger at the moment.
    }

}
