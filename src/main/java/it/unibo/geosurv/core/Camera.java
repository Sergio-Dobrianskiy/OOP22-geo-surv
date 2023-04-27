package it.unibo.geosurv.core;

public class Camera {

    private int x, y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object) {

        x += ((object.getX() - x) - 1000/4); //* 0.05f;
        y += ((object.getY() - y) - 563/4); //* 0.05f;

        if(x <= 0) {
            x = 0;
        }

        if (x >= 540) {
            x = 540;
        }

        if (y <= 0) {
            y = 0;
        }

        if(y >= 1000) {
            y = 1000;
        }

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    
}
