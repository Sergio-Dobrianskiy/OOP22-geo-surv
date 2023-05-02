package it.unibo.geosurv.core;

import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.geosurv.weapons.Weapon;

public class Monster extends GameObject implements Entity {

    public Monster(float x, float y, ID id) {
        super(x, y, id);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Entity born() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'born'");
    }

    @Override
    public int getHealth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHealth'");
    }

    @Override
    public boolean isDead() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDead'");
    }

    @Override
    public Experience dropExperience() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dropExperience'");
    }

    @Override
    public int takeHit(Weapon weapon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'takeHit'");
    }

    @Override
    public void die() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'die'");
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }

    /**
     * Allow monster to try to reach the target/player
     */
    void reachTarget() {
    };
}
