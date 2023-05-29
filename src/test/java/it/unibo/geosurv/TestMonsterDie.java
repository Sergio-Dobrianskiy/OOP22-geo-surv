package it.unibo.geosurv;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.drops.Life;
import it.unibo.geosurv.model.monsters.GenerateMonsterT;
import it.unibo.geosurv.model.monsters.Monster;

import it.unibo.geosurv.model.player.MainPlayer;

/**
 * Test for {@link Monster} creation.
 */
public class TestMonsterDie {

    @Test
    void testMonstersDie() {

        Handler h = new Handler();
        h.addPlayer(new MainPlayer(0, 0, h));
        ArrayList<Monster> list = new ArrayList<>();
        GenerateMonsterT tFact = new GenerateMonsterT();
        for (int j = 0; j < 100; j++) {
            Monster m = tFact.createMonster(h);
            h.addObject(m);
            list.add(m);
        }

        // check if there are only monster and player
        h.getGameObjects().stream()
                .forEach((i) -> {
                    assertTrue(isInPlayerAndMonster(i));
                });

        // All monsters die, so now there should be only player, experience and
        // eventually life
        list.forEach((i) -> i.die());

        // check if there are only Life/Experience and player
        h.getGameObjects().stream()
                .forEach((i) -> {
                    assertTrue(isInPlayerAndExperienceOrLife(i));
                });
        list.forEach((i) -> i.removeMonster(i));
        list.removeAll(list);
        // m.removeMonster(m);
    }

    private boolean isInPlayerAndExperienceOrLife(GameObject i) {
        return i.getClass() == Experience.class
                || i.getClass() == Life.class
                || i.getClass() == MainPlayer.class;
    }

    private boolean isInPlayerAndMonster(GameObject i) {
        return i.getClass().getSuperclass() == Monster.class || i.getClass() == MainPlayer.class;
    }

}
