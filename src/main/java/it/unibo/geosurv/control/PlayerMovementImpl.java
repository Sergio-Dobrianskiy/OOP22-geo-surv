package it.unibo.geosurv.control;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.MainPlayer;

/**
 * manages player movement.
 */
public class PlayerMovementImpl implements PlayerMovement {

    private final Handler handler;
    private MainPlayer player;

    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public PlayerMovementImpl(final Handler handler) {
        this.handler = handler;
        this.player = handler.getPlayer();
    }

    /**
     * manages player movement.
     */
    @Override
     public void movePlayer() {
        if (player == null) {                // TODO: rework
            player = handler.getPlayer();
            return;
        }

        if (handler.isUp()) {
            player.setVelY(-player.getSpeed());
        } else if (!handler.isDown()) {
            player.setVelY(0);
        }

        if (handler.isDown()) {
            player.setVelY(player.getSpeed());
        } else if (!handler.isUp()) {
            player.setVelY(0);
        }

        if (handler.isRight()) {
            player.setVelX(player.getSpeed());
        } else if (!handler.isLeft()) {
            player.setVelX(0);
        }

        if (handler.isLeft()) {
            player.setVelX(-player.getSpeed());
        } else if (!handler.isRight()) {
            player.setVelX(0);
        }
    }

    @Override
    public void stopMovements() {
//        player.setX(player.getX() + player.getVelX() * -1);
//        player.setY(player.getY() + player.getVelY() * -1);
    }

}
