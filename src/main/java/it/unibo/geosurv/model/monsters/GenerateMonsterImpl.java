package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.triangle.Rect;
import it.unibo.geosurv.model.monsters.triangle.Rhombus;
import it.unibo.geosurv.model.monsters.triangle.Triangle;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

public class GenerateMonsterImpl implements GenerateMonster {

    private static final float MIN_DISTANCE = 500.0f;
    private static final float MAX_DISTANCE = 600.0f;

    private Handler handler;
    private Game game;

    GameObject tempPlayer = Game.returnHandler().getPlayer();

    @Override
    public Monster generateMonster(final String monsterName, final boolean isBig) {
        final Pair<Float, Float> randomPosition = Func.randomPoint(MIN_DISTANCE, MAX_DISTANCE);
        final float x = tempPlayer.getX() + randomPosition.getX();
        final float y = tempPlayer.getY() + randomPosition.getY();

        if ("Triangle".equals(monsterName)) {
            return new Triangle(x, y, handler, game, isBig);

        } else if ("Rect".equals(monsterName)) {
            return new Rect(x, y, handler, game, isBig);

        } else if ("Rhombus".equals(monsterName)) {
            return new Rhombus(x, y, handler, game, isBig);

        } else {
            throw new UnsupportedOperationException("Unimplemented Monster: " + monsterName);
        }
    }

}
