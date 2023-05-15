package it.unibo.geosurv.view.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;

public class TextureRender{
	
	private static final boolean HIT_BOXES = false;
	private Handler handler;
	
	public TextureRender(Handler handler) {
		this.handler = handler;
	}
	
	public void render(Graphics g) {
		ArrayList<GameObject> gameObjects = handler.getObjects();
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject to = gameObjects.get(i);
			int xx = getRenderX(to);
			int yy = getRenderY(to);
			if (HIT_BOXES == true) this.renderHitBoxes(g, Color.white, to);
			g.drawImage(to.getTexture().extractTexture(), xx , yy, null);
		}
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
	
//	protected void drawRect(Graphics g, Color color) {
//		g.setColor(color);
//        g.fillRect((int) (x - (this.width / 2)), (int) (y - (this.height / 2)), width, height);
//
//	}
	
//	protected void drawOval(Graphics g, Color color) {
//		g.setColor(color);
//        g.fillOval(getRenderX(), getRenderY(), width, height);
//	}
	
//	protected void renderImage(Graphics g, BufferedImage sprite) {
//        g.drawImage(sprite, (int) (x - (this.width / 2)), (int) (y - (this.height / 2)), null);
//
//	}
}
