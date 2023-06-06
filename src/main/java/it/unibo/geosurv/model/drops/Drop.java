package it.unibo.geosurv.model.drops;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.monsters.types.Ball;

/**
 * Class which manages the logics of dropping objects (life and experience).
 * 
 */
public class Drop implements IDrop<GameObject> {

    private static final int LIFE_PILLS_PROB = 50; // probability to get a life pill: 1/50 at monster death
    private static final int NEW_MONSTER_PROB = 10; // probability to get a new monster: 1/10 at monster death
    private final Monster m;
    private final Handler h;

    /**
     * Droppable object constructor.
     * 
     * @param m monster.
     * @param h handler.
     */
    public Drop(final Monster m, final Handler h) {
        this.m = m;
        this.h = h;
    }

    /**
     * drop implementation, which could allow for life, a new monster or experience.
     */
    @Override
    public GameObject drop() {
        if (this.shouldDropLife()) {
            return dropLife();
        } else if (this.shouldDropMonsterBall()) {
            return dropBall();
        } else {
            return dropExperience();
        }

    }

    /**
     * Evaluates if a life should be dropped (probability 1/50).
     * 
     * @return true if life should be dropped.
     */
    public boolean shouldDropLife() {
        // Generate a random number between 0 and 49
        final double num = Math.random();
        final int randomNumber = (int) (num * LIFE_PILLS_PROB);
        // Return true if the random number is 0 (probability of 1/50)

        return randomNumber == 0;
    }

    /**
     * @return life object.
     */
    public Life dropLife() {
        return new Life(this.m.getX(), this.m.getY(), this.h);
    }

    /**
     * @return experience object.
     */
    public Experience dropExperience() {
        return new Experience(this.m.getX(), this.m.getY(), this.m.getDefaultExperience(), this.h);
    }

    /**
     * Evaluates if a new monster should be dropped (probability 1/50).
     * 
     * @return true if life should be dropped.
     */
    public boolean shouldDropMonsterBall() {
        // Generate a random number between 0 and 10
        final double num = Math.random();
        final int randomNumber = (int) (num * NEW_MONSTER_PROB);
        // Return true if the random number is 0 (probability of 1/10)
        return randomNumber == 0;
    }

    /**
     * @return a new Ball monster.
     */
    public Ball dropBall() {
        final Ball b = new Ball(this.h);
        b.setX(this.m.getX());
        b.setY(this.m.getY());
        return b;
    }

}
