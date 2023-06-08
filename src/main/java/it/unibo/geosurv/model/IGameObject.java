package it.unibo.geosurv.model;

import java.awt.geom.RectangularShape;

import it.unibo.geosurv.view.graphics.Texture;


/**
 * interface for GameObjects.
 */
public interface IGameObject {

    /**
     * returns GameObjects' ID.
     * 
     * @return GameObjects' ID
     */
    ID getId();

    /**
     * set object's id.
     * @param id object's id
     */
    void setId(ID id);

    /**
     * get object's x coordinate.
     * @return object's x coordinate
     */
    float getX();

    /**
     * set object's x coordinate.
     * @param x object's x coordinate
     */
    void setX(float x);

    /**
     * get object's y coordinate.
     * @return object's y coordinate
     */
    float getY();

    /**
     * set object's y coordinate.
     * @param y object's y coordinate
     */
    void setY(float y);

    /**
     * get object's velocity x vector.
     * @return object's velocity x vector
     */
    float getVelX();

    /**
     * set object's velocity x vector.
     * @param velX object's velocity x vector
     */
    void setVelX(float velX);

    /**
     * get object's velocity y vector.
     * @return object's velocity y vector
     */
    float getVelY();

    /**
     * set object's velocity y vector.
     * @param velY object's velocity y vector
     */
    void setVelY(float velY);

    /**
     * get object's height.
     * @return object's width
     */
    int getHeight();

    /**
     * get object's width.
     * @return object's width
     */
    int getWidth();

    /**
     * set object's height.
     * @param height object's width
     */
    void setHeight(int height);

    /**
     * set object's width.
     * @param width object's width
     */
    void setWidth(int width);

    /**
     * return x coordinate for object's sprite.
     * @return object sprite y coordinate
     */
    int getRenderX();

    /**
     * return y coordinate for object's sprite.
     * @return object sprite y coordinate
     */
    int getRenderY();

    /**
     * return GameObject's hitbox (that is a Rectangle by default).
     * 
     * @return RectangularShape centered on the GameObject
     */
    RectangularShape getShape();

    /**
     * return object's texture.
     * @return object's texture
     */
    Texture getTexture();
}
