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
import it.unibo.geosurv.model.monsters.types.Ball;
import it.unibo.geosurv.model.player.Player;

/**
 * Test for {@link Monster} creation.
 */
class TestMonsterDie {

    @Test
    void testMonstersDie() {

        Handler h = new Handler();
        h.addPlayer(new Player(0, 0, h));
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
    }

    boolean isInPlayerAndExperienceOrLife(final GameObject go) {
        return go.getClass() == Experience.class
                || go.getClass() == Life.class
                || go.getClass() == Ball.class
                || go.getClass() == Player.class;
    }

    boolean isInPlayerAndMonster(final GameObject go) {
        return go.getClass().getSuperclass() == Monster.class || go.getClass() == Player.class;
    }

}
