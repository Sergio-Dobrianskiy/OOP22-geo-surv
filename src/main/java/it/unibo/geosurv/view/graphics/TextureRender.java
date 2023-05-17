package it.unibo.geosurv.view.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import it.unibo.geosurv.debug.DebugManager;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.player.MainPlayer;

public class TextureRender {

	private static final boolean HIT_BOXES = false;
	private Handler handler;
	DebugManager debugManager = new DebugManager();

	public TextureRender(Handler handler) {
		this.handler = handler;
	}

	public void render(Graphics g) {
		ArrayList<GameObject> gameObjects = handler.getObjects();
		MainPlayer player = handler.getPlayer();
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject to = gameObjects.get(i);
			int xx = getRenderX(to);
			int yy = getRenderY(to);
			// Enable debug mode and set the debug code
			debugManager.setDebugFunction(() -> {
				if (to.getId() != ID.Block) {
					g.drawString(to.toString(), xx + 10, yy + 10);
					this.renderHitBoxes(g, Color.white, to);
				}
			});
			// debugManager.executeDebugCode();
			if (HIT_BOXES == true)
				this.renderHitBoxes(g, Color.white, to);
			g.drawImage(to.getTexture().extractTexture(), xx, yy, null);
		}
		renderUI(g, player);
	}

	public void renderUI(final Graphics g, final MainPlayer player) {
		int x = (int) player.getX();
		int y = (int) player.getY();
		g.setColor(Color.blue);
		g.drawString("Life: " + player.getLife() + " Exp: " + player.getExperience(), (int) x, (int) y - 5);

		// Draw bar progres for player's life
		int barWidth = 100;
		int barHeight = 10;
		int barX = (int) (x - barWidth / 2); // bar x coordinate
		int barY = (int) y - barHeight - 30;

		g.setColor(Color.RED);
		g.fillRect(barX, barY, barWidth, barHeight);

		float percentage = (float) player.getLife() / MainPlayer.MAX_LIFE;
		percentage = percentage > 0 ? percentage : 0; // prevents bar from overflowing
		int filledWidth = (int) (barWidth * percentage);

		g.setColor(Color.GREEN);
		g.fillRect(barX, barY, filledWidth, barHeight);

		g.setColor(Color.WHITE);
		g.drawRect(barX, barY, barWidth, barHeight);
	}

	public void renderHitBoxes(Graphics g, Color color, GameObject obj) {
		int xx = getRenderX(obj);
		int yy = getRenderY(obj);
		g.setColor(color);
		g.fillRect(xx, yy, obj.getWidth(), obj.getHeight());
	}

	public int getRenderX(GameObject obj) {
		return (int) (obj.getX() - (obj.getWidth() / 2));
	}

	public int getRenderY(GameObject obj) {
		return (int) (obj.getY() - (obj.getHeight() / 2));
	}

	// protected void drawRect(final Graphics g, final Color color, final GameObject
	// obj) {
	// g.setColor(color);
	// g.fillRect((int) (obj.getX() - (obj.getWidth() / 2)), (int) (obj.getY() -
	// (obj.getHeight() / 2)), obj.getWidth(), obj.getHeight());
	//
	// }

	// protected void drawOval(Graphics g, Color color) {
	// g.setColor(color);
	// g.fillOval(getRenderX(), getRenderY(), width, height);
	// }

	// protected void renderImage(Graphics g, BufferedImage sprite) {
	// g.drawImage(sprite, (int) (x - (this.width / 2)), (int) (y - (this.height /
	// 2)), null);
	//
	// }
}
