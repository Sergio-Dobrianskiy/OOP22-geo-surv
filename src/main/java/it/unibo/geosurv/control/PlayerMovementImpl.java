package it.unibo.geosurv.control;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.MainPlayer;

public class PlayerMovementImpl implements PlayerMovement {
	
	private Handler handler;
	private MainPlayer player;
	
	public PlayerMovementImpl(final Handler handler) {
		this.handler = handler;
		this.player = handler.getPlayer();
	}

	@Override
	 public void movePlayer() {
		if (player == null) {				// TODO: rework
			player = handler.getPlayer();
			System.out.println("null");
			return;
		}
		
        if (handler.isUp()) {
        	player.setVelY(- MainPlayer.PLAYER_SPEED);
        } else if (!handler.isDown()) {
        	player.setVelY(0);
        }

        if (handler.isDown()) {
        	player.setVelY(MainPlayer.PLAYER_SPEED);
        } else if (!handler.isUp()) {
        	player.setVelY(0);
        }

        if (handler.isRight()) {
        	player.setVelX(MainPlayer.PLAYER_SPEED);
        } else if (!handler.isLeft()) {
        	player.setVelX(0);
        }

        if (handler.isLeft()) {
        	player.setVelX(- MainPlayer.PLAYER_SPEED);
        } else if (!handler.isRight()) {
        	player.setVelX(0);
        }
    }
	
	@Override
	public void stopMovements() {
//		player.setX(player.getX() + player.getVelX() * -1);
//        player.setY(player.getY() + player.getVelY() * -1);
    }

}
