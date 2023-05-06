package it.unibo.geosurv.model.monsters.triangle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.utility.Func;

public class Triangle extends Monster {

    private Handler handler;
    private Game game;
    private float speed = 1.5f;
    private static int counter = 0;
    private String name = "Triangle-";
    private GameObject tempPlayer = Game.returnHandler().getPlayer();

    /**
     * Triangle constructor
     */
    public Triangle(float x, float y, Handler handler, Game game) {
        super(x, y);
        // this.speed = speed;
        this.handler = handler;
        this.game = game;
        Triangle.counter++;
        this.name = name + Triangle.counter;
    };

    @Override
    public void tick() {
        reachTarget();
//        if (this.getShape().intersects(tempPlayer.getShape())) {
//            this.die();
//        }
//         LinkedList<GameObject> tmpObjects = handler.getObjects();
//         for (int i = 0; i < tmpObjects.size(); i++) {
//	         GameObject tempObject = tmpObjects.get(i);
//	         
//
//	         if (tempObject.getId() == ID.Bullet) { 
//		         if (this.getShape().intersects(tempObject.getShape())) {
//		        	 // TODO: add bullet.getDamage()
////		         System.out.println(this.toString() + " " + this.name + " object is removed");
////		         System.out.println("Position: [" + this.x + "," + this.y + "]");
//		         }
//	         }
//         }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 20, 20);
    }

    @Override
    public Rectangle getShape() {
        return new Rectangle((int) x, (int) y, 20, 20);
    }

    @Override
    public void reachTarget() {
        x += velX;
        y += velY;
        tempPlayer = Func.findPlayer(handler);

        int mx = (int) this.tempPlayer.getX();
        int my = (int) this.tempPlayer.getY();

        float angle = (float) Math.atan2(my - this.getY() + 8, mx - this.getX() + 4);

        this.velX = (float) ((this.speed) * Math.cos(angle));
        this.velY = (float) ((this.speed) * Math.sin(angle));
        // System.out.println("T trying to reach the target");
    }

}
