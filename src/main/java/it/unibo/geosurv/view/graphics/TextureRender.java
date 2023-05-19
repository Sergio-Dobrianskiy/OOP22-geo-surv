package it.unibo.geosurv.view.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.player.MainPlayer;

import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.view.graphics.Camera;

public class TextureRender {

	private static final boolean HIT_BOXES = false;
	private Handler handler;
	private static final int FRAMES_IN_BUFFER = 3;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;

	public TextureRender(Handler handler) {
		this.handler = handler;

	}

	public void renderView(Graphics g) {
		ArrayList<GameObject> gameObjects = handler.getObjects();
		MainPlayer player = handler.getPlayer();
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject to = gameObjects.get(i);
			int xx = getRenderX(to);
			int yy = getRenderY(to);
			g.drawImage(to.getTexture().extractTexture(), xx, yy, to.getWidth(), to.getHeight(), null);
		}
		renderUI(g, player);
	}

	public void renderUI(final Graphics g, final MainPlayer player) {
		int x = (int) player.getX();
		int y = (int) player.getY();
		g.setColor(Color.white);
		g.drawString("Life: " + player.getLife(), (int) x + player.getWidth(), (int) y);
		g.drawString("Exp: " + (int) player.getExpPercentage() + "%", (int) x + player.getWidth(), (int) y + 20);
		g.drawString("Curr: " + player.getExperience(), (int) x + player.getWidth(), (int) y + 40);
		g.drawString("Max: " + player.getMaxExperience(), (int) x + player.getWidth(), (int) y + 60);
		g.drawString("Lvl: " + player.getLevel(), (int) x + player.getWidth(), (int) y + 80);

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

	public void showDebug(Graphics g) {
		ArrayList<GameObject> gameObjects = handler.getObjects();

		g.setColor(Color.RED);
		// g.drawString("FPS: " + this.fps, 850, 50);
		// g.drawString("Objects: " + this.objectsCounter, 850, 65);
		g.drawString("Experience: " + Experience.getExperienceCounter(), 850, 80);
		g.drawString("Monsters: " + Monster.getMonstersCounter(), 850, 95);
		g.drawString("Player Exp: " + handler.getPlayer().getExperience(), 850, 110);
		g.drawString("Player Life: " + handler.getPlayer().getLife(), 850, 125);
		// g.drawString("Time: " + (((int) ((System.currentTimeMillis() / 1000))) -
		// startTime / 1000), 850, 140);

		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject to = gameObjects.get(i);
			int xx = getRenderX(to);
			int yy = getRenderY(to);

			if (to.getId() != ID.Block && to.getId() != ID.Bullet) {
				g.drawString(to.toString(), xx + 10, yy + 10);
				this.renderHitBoxes(g, Color.red, to);
			}

		}
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
