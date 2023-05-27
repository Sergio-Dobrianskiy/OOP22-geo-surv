package it.unibo.geosurv.model.bullets;

import java.awt.geom.Ellipse2D;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents an Explosion Bullet.
 */
public class Explosion extends Bullet {

    /**
     * explosion's height.
     */
    private final int explosionHeight = 64;
    /**
     * explosion's width.
     */
    private final int explosionWidth = 64;
    /**
     * explosion's life span.
     */
    private final long explosionLifeSpan = 2000L;

    /**
     * Constructor for this class.
     *
     * @param x explosion coordinate
     * @param y explosion coordinate
     * @param handler game's Handler
     * @param damage explosion damage
     */
    public Explosion(final float x, final float y, final Handler handler, final int damage) {
        super(x, y, handler, damage);
        super.lifeSpan = explosionLifeSpan;
        this.height = explosionHeight;
        this.width = explosionWidth;
        this.texture = Texture.EXPLOSION;
    }

    /**
     * an explosion has a circular shape.
     * 
     * @return Ellipse2D centered on the GameObject
     */
    @Override
    public Ellipse2D getShape() {
        return new Ellipse2D.Float(getRenderX(), getRenderY(), this.width, this.height);
    }
}

