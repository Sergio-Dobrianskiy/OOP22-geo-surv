package it.unibo.geosurv.model;

import java.awt.geom.RectangularShape;

import it.unibo.geosurv.view.graphics.Texture;

public interface IGameObject {

	ID getId();

	void setId(ID id);

	void tick();

	float getX();

	void setX(float x);

	float getY();

	void setY(float y);

	float getVelX();

	void setVelX(float velX);

	float getVelY();

	void setVelY(float velY);

	int getHeight();

	int getWidth();

	void setHeight(int height);

	void setWidth(int width);

	int getRenderX();

	int getRenderY();

	RectangularShape getShape();

	Texture getTexture();

}