package it.unibo.geosurv.core;

public class Camera {

    private float x, y;
    private MainPlayer tempPlayer;

    public Camera(float x, float y, Handler handler) {
        this.x = x;
        this.y = y;
        this.tempPlayer = handler.getPlayer();
    }

    public void tick() {
        x += ((this.tempPlayer.getX() - x) - 476) * 0.05f;  // magic numbers
        y += ((this.tempPlayer.getY() - y) - 256) * 0.05f;

        if (x <= 0) {
            x = 0;
        }

        if (x >= 1045) {
            x = 1045;
        }

        if (y <= 0) {
            y = 0;
        }

        if (y >= 1500) {
            y = 1500;
        }
//	    if (tempPlayer != null) {
//    	} else {
//    		this.tempPlayer = handler.getPlayer();
//    	}
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
