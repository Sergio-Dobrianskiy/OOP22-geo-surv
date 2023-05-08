package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.drops.Experience;

/** Interface for generic evil */
public abstract class Monster extends GameObject {

    private int DEFAULT_EXPERIENCE = 1;
    private int health;
    protected static int monstersCounter;

    protected Monster(float x, float y) {
        super(x, y, ID.Monster);
        monstersCounter++;
    }

    /**
     * @return an integer showing how much health entity has left
     */
    public int getHealth() {
        return this.health;
    };

    /**
     * Check if Entity is alive
     * 
     * @return boolen value
     */
    public boolean isDead() {
        return this.getHealth() < 0 ? true : false;
    };

    /**
     * At death, Entity drop experience pill
     * 
     * @return Experience pill
     */
    public Experience dropExperience() {
        return new Experience(this.x, this.y, DEFAULT_EXPERIENCE);
    };

    /**
     * Entity's been hit by player weapon
     * 
     * @param weapon whih hits the entity
     */
    public void hit(int weaponDamage) {
        health -= weaponDamage;
        if (this.isDead()) {
            // System.out.println(this + " is dead");
            this.die();
        }
    };

    /**
     * Attach target/Player
     * fixed monsters do not move..
     */
    public abstract void reachTarget();

    /**
     * Entity dies and it is removed
     */
    public void die() {
        Handler h = Game.returnHandler();
        h.addObject(this.dropExperience());
        monstersCounter--;
        h.removeObject(this); // monster is removed from Monsters set/list

    };

    public static int getMonstersCounter() {
        return monstersCounter;
    }

}
