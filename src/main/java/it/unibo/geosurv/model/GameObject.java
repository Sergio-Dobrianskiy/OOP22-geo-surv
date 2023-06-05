package it.unibo.geosurv.model;

import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Abstract class for every game object in the game.
 */
public abstract class GameObject implements TickingObject, IGameObject {
    /**
     * gameObject coordinates.
     */
    private float x;
    private float y;

    /**
     * gameObject velocity.
     */
    protected float velX = 0, velY = 0;
    /**
     * gameObject dimensions.
     */
    protected int height = 0, width = 0;
    /**
     * gameObject texture.
     */
    protected Texture texture = Texture.MISSING_TEXTURE;
    /**
     * gameObject id.
     */
    protected ID id;
    // protected ICollisionBehavior collisionBehavior;

    /**
     * Constructor for this class.
     *
     * @param x  GameObject coordinate
     * @param y  GameObject coordinate
     * @param id GameObject id
     */
    public GameObject(final float x, final float y, final ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
        // this.collisionBehavior = new NoCollisionBehavior();
    }

    /**
     * returns GameObjects' ID.
     * 
     * @return GameObjects' ID
     */
    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(final ID id) {
        this.id = id;
    }

    @Override
    public abstract void tick();

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(final float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(final float y) {
        this.y = y;
    }

    @Override
    public float getVelX() {
        return velX;
    }

    @Override
    public void setVelX(final float velX) {
        this.velX = velX;
    }

    @Override
    public float getVelY() {
        return velY;
    }

    @Override
    public void setVelY(final float velY) {
        this.velY = velY;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public int getRenderX() {
        return (int) (x - (this.width / 2));
    }

    @Override
    public int getRenderY() {
        return (int) (y - (this.height / 2));
    }

    @Override
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * return GameObject's hitbox (that is a Rectangle by default).
     * 
     * @return RectangularShape centered on the GameObject
     */
    @Override
    public RectangularShape getShape() {
        return new Rectangle(this.getRenderX(), getRenderY(), this.width, this.height);
    }
    
    public abstract void collide();
}
