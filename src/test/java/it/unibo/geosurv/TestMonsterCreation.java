package it.unibo.geosurv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.GenerateMonsterR;
import it.unibo.geosurv.model.monsters.GenerateMonsterRh;
import it.unibo.geosurv.model.monsters.GenerateMonsterT;
import it.unibo.geosurv.model.monsters.MonsterImpl;
import it.unibo.geosurv.model.monsters.types.Rect;
import it.unibo.geosurv.model.monsters.types.Rhombus;
import it.unibo.geosurv.model.monsters.types.Triangle;
import it.unibo.geosurv.model.player.MainPlayer;

/**
 * Test for {@link MonsterImpl} creation.
 */
class TestMonsterCreation {

    @Test
    void createTMonsters() {

        Handler h = new Handler();
        h.addPlayer(new MainPlayer(0, 0, h));

        GenerateMonsterT tFact = new GenerateMonsterT();
        MonsterImpl t = tFact.createMonster(h);

        assertEquals(Triangle.class, t.getClass());
        t.die();
    }

    @Test
    void createRMonsters() {

        Handler h = new Handler();
        h.addPlayer(new MainPlayer(0, 0, h));

        GenerateMonsterR rFact = new GenerateMonsterR();
        MonsterImpl r = rFact.createMonster(h);

        assertEquals(Rect.class, r.getClass());
        r.die();
    }

    @Test
    void createRhMonsters() {

        Handler h = new Handler();
        h.addPlayer(new MainPlayer(0, 0, h));

        GenerateMonsterRh rhFact = new GenerateMonsterRh();
        MonsterImpl rh = rhFact.createMonster(h);

        assertEquals(Rhombus.class, rh.getClass());
        rh.die();
    }

    @Test
    void createDifferentTypeOfMonsters() {

        Handler h = new Handler();
        h.addPlayer(new MainPlayer(0, 0, h));
        int counter = 0;
        GenerateMonsterT tFact = new GenerateMonsterT();
        GenerateMonsterR rFact = new GenerateMonsterR();
        GenerateMonsterRh rhFact = new GenerateMonsterRh();
        MonsterImpl t1 = tFact.createMonster(h); // Nr. 1
        counter++;
        MonsterImpl t2 = tFact.createMonster(h); // Nr. 2
        counter++;
        MonsterImpl r1 = rFact.createMonster(h); // Nr. 3
        counter++;
        MonsterImpl rh1 = rhFact.createMonster(h); // Nr. 4
        counter++;

        assertEquals(counter, MonsterImpl.getMonstersCounter());

        t1.die();
        t2.die();
        r1.die();
        rh1.die();
    }

}
