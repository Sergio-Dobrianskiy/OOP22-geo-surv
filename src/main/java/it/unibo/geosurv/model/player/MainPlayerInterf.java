package it.unibo.geosurv.model.player;

import it.unibo.geosurv.model.ObserverEntity;

public interface MainPlayerInterf {

    void tick();

    int getExperience();

    void setExperience(int experience);

    int getLife();

    void setLife(int life);

    void addObserver(ObserverEntity observer);

    void removeObserver(ObserverEntity observer);

    void hit(int damage);
}
