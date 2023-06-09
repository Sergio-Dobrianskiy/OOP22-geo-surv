package it.unibo.geosurv.model;

import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import it.unibo.geosurv.control.ITickingObject;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Abstract class for every game object in the game.
 */
public abstract class GameObject implements ITickingObject, IGameObject {
    /**
     * gameObject x coordinate.
     */
    private float x;
    /**
     * gameObject y coordinate.
     */
    private float y;

    /**
     * gameObject x velocity.
     */
    protected float velX;
    /**
     * gameObject y velocity.
     */
    protected float velY;
    /**
     * gameObject dimensions.
     */
    protected int height;
    protected int width;
    /**
     * gameObject texture.
     */
    protected Texture texture = Texture.MISSING_TEXTURE;
    /**
     * gameObject id.
     */
    protected ID id;

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

    /**
     * set object's id.
     */
    @Override
    public void setId(final ID id) {
        this.id = id;
    }

    /**
     * this object's behavior.
     */
    @Override
    public abstract void tick();

    /**
     * get object's x coordinate.
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * set object's x coordinate.
     */
    @Override
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * get object's y coordinate.
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * set object's y coordinate.
     */
    @Override
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * get object's velocity x vector.
     */
    @Override
    public float getVelX() {
        return velX;
    }

    /**
     * set object's velocity x vector.
     */
    @Override
    public void setVelX(final float velX) {
        this.velX = velX;
    }

    /**
     * get object's velocity y vector.
     */
    @Override
    public float getVelY() {
        return velY;
    }

    /**
     * set object's velocity y vector.
     */
    @Override
    public void setVelY(final float velY) {
        this.velY = velY;
    }

    /**
     * get object's height.
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * get object's width.
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * set object's height.
     */
    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * set object's width.
     */
    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * return x coordinate for object's sprite.
     */
    @Override
    public int getRenderX() {
        return (int) x - this.width / 2;
    }

    /**
     * return y coordinate for object's sprite.
     */
    @Override
    public int getRenderY() {
        return (int) y - this.height / 2;
    }

    /**
     * return object's texture.
     */
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

    /**
     * handle collision with another object.
     */
    public abstract void collide();
}
