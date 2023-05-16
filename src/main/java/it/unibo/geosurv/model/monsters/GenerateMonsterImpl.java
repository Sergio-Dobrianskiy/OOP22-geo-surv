package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Rect;
import it.unibo.geosurv.model.monsters.types.Rhombus;
import it.unibo.geosurv.model.monsters.types.Triangle;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

public class GenerateMonsterImpl implements GenerateMonster {

    private static final float minDistance = 500.0f;
    private static final float maxDistance = 600.0f;
    private Handler handler;
    private Game game;
    private float x;
    private float y;

    GameObject tempPlayer = Game.returnHandler().getPlayer();

    @Override
    public Monster generateMonster(final String monsterName, final boolean isBig) {

        if ("Triangle".equals(monsterName)) {
            final Pair<Float, Float> randomPosition = Func.randomPoint(minDistance, maxDistance);
            x = tempPlayer.getX() + randomPosition.getX();
            y = tempPlayer.getY() + randomPosition.getY();
            return new Triangle(x, y, isBig);

        } else if ("Rect".equals(monsterName)) {
            final Pair<Float, Float> randomPosition = Func.randomPoint(minDistance, maxDistance);
            x = tempPlayer.getX() + randomPosition.getX();
            y = tempPlayer.getY() + randomPosition.getY();
            return new Rect(x, y, isBig);

        } else if ("Rhombus".equals(monsterName)) {
            final Pair<Float, Float> randomPosition = Func.randomPoint(100, 200);
            x = tempPlayer.getX() + randomPosition.getX();
            y = tempPlayer.getY() + randomPosition.getY();
            return new Rhombus(x, y);

        } else {
            throw new UnsupportedOperationException("Unimplemented Monster: " + monsterName);
        }
    }

}
