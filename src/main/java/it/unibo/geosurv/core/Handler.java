package it.unibo.geosurv.core;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

		LinkedList<GameObject> object = new LinkedList<GameObject>();
		
		
		public void tick() {
			for(int i=0; i< object.size(); i++) {
				GameObject tempObject = object.get(i);
				
				tempObject.tick();
				
			}
		}
		
		
		public void render(Graphics g) {
			for(int i=0; i< object.size(); i++) {
				GameObject tempObject = object.get(i);
				
				tempObject.render(g);
				
			}
		}
		
		public void addObject(GameObject tempObject) {
			object.add(tempObject);
		}
		
		public void removeObject(GameObject tempObject) {
			object.remove(tempObject);
		}
}