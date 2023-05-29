package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.drops.Life;

public interface Monster {

    int getHealth();

    int getPower();

    boolean isDead();

    // unire le due in drop?? ed usare un unico method drop()
    Experience dropExperience();

    Life dropLife();

    boolean shouldDropLife();

    void hit(int weaponDamage);

    void reachTarget();

    void setBig(boolean b);

    void setStartingPosition(float minDistance, float maxDistance);

    void removeMonster(MonsterImpl monster);

    void die();
}
