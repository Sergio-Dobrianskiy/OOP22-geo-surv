package it.unibo.geosurv.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.geosurv.core.GameObject;
import it.unibo.geosurv.core.ID;

public class Experience extends GameObject {

    /** more experience make player go to new levels */
    private int experience;
    private static int experienceCounter = 0;

    public Experience(float x, float y, ID id, int exp) {
        super(x, y, ID.Experience);
        this.experience = exp;
        experienceCounter++; // TODO: decrease the counter as Player gather experiences pills
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillOval((int) x, (int) y, 8, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /**
     * @return int amount of experience in the pill
     */
    public int getExperience() {
        return experience;
    }

    public static int getExperienceCounter() {
        return experienceCounter;
    }
}
