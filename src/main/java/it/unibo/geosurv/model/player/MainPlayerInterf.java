package it.unibo.geosurv.model.player;

import java.awt.Rectangle;

import it.unibo.geosurv.model.MonstersObserver;

public interface MainPlayerInterf {
    
    void tick();
    
    Rectangle getShape();
    
    int getExperience();
    
    void setExperience(int experience);
    
    int getLife();
    
    void setLife(int life);
    
    void addObserver(MonstersObserver observer);
    
    void removeObserver(MonstersObserver observer);
    
    void hit(int damage);
}
