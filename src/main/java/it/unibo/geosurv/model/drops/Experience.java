package it.unibo.geosurv.model.drops;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

public class Experience extends GameObject {

    /** more experience make player go to new levels */
    private int experience;
    private static int experienceCounter = 0;
    private GameObject tempPlayer = Game.returnHandler().getPlayer();
    private Handler handler;

    public Experience(float x, float y, int exp) {
        super(x, y, ID.Experience);
        this.experience = exp;
        experienceCounter++; // TODO: decrease the counter as Player gather experiences pills
        handler = Game.returnHandler();
    }

    @Override
    public void tick() {
        if (this.getShape().getBounds2D().intersects(tempPlayer.getShape().getBounds2D())) {
//            System.out.println("pick up experience");
            // TODO: player should increase experience
            handler.getPlayer().setExperience(this.getExperience());
            // maybe these few line have to go in a method of player class
            handler.removeObject(this);
        }
    }

//    @Override
//    public void render(Graphics g) {
//        g.setColor(Color.PINK);
//        g.fillOval((int) x, (int) y, 8, 8);
//    }

    @Override
    public Rectangle getShape() {
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
