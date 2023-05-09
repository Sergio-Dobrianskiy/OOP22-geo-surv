package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.triangle.Rect;
import it.unibo.geosurv.model.monsters.triangle.Triangle;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

public class GenerateMonsterImpl implements GenerateMonster {

    private float x;
    private float y;
    private Handler handler;
    private Game game;

    GameObject tempPlayer = Game.returnHandler().getPlayer();

    @Override
    public Monster generateMonster(String monsterName, boolean isBig) {
        Pair<Float, Float> randomPosition = Func.randomPoint(500.0f, 600.0f);
        x = tempPlayer.getX() + randomPosition.getX();
        y = tempPlayer.getY() + randomPosition.getY();

        if (monsterName == "Triangle") {
            return new Triangle(x, y, handler, game, isBig);

        } else if (monsterName == "Rect") {
            return new Rect(x, y, handler, game, isBig);

        } else {
            throw new UnsupportedOperationException("Unimplemented Monster: " + monsterName);
        }
    }

}
