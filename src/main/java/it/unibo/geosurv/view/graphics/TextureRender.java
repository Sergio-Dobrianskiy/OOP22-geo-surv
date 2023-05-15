package it.unibo.geosurv.view.graphics;

import java.awt.Graphics;
import java.util.ArrayList;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;

public class TextureRender{
	
	private Handler handler;
	
	public TextureRender(Handler handler) {
		this.handler = handler;
	}
	
	public void render(Graphics g) {
		ArrayList<GameObject> gameObjects = handler.getObjects();
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject to = gameObjects.get(i);
//			to.render(g);
			int xx = to.getRenderX();
			int yy = to.getRenderY();
			
			g.drawImage(to.getTexture().extractTexture(), xx , yy, null);
		}
	}
}
