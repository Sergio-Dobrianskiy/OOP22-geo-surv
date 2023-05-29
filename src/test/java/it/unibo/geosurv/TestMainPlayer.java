package it.unibo.geosurv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.MainPlayer;

public class TestMainPlayer {

    private MainPlayer player;

    @BeforeEach
    public void setUp() {
        Handler handler = new Handler(); 
        player = new MainPlayer(0, 0, handler);
    }

    @Test
    public void testGetExperience() {
        int expectedExperience = 0;
        int actualExperience = player.getExperience();
        assertEquals(expectedExperience, actualExperience);
    }

    @Test
    public void testGetLevel() {
        int expectedLevel = 1;
        int actualLevel = player.getLevel();
        assertEquals(expectedLevel, actualLevel);
    }
    @Test
    public void testGetLife() {
        int expectedLife = 100;
        int actualLife = player.getLife();
        assertEquals(expectedLife, actualLife);
    }



    
}
