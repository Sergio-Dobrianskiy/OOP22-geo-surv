package it.unibo.geosurv.model.weapons;

import java.util.LinkedList;

import it.unibo.geosurv.model.monsters.triangle.Triangle;

public class NotificationService {
	
	private LinkedList<Triangle> listeners;
	
	public NotificationService() {
		listeners = new LinkedList<>();
	}
	
	public void subscrive(Triangle triangle) {
		listeners.add(triangle);
	}

	public void unsubscribe(Triangle triangle) {
		listeners.remove(triangle);
	}
	
	public void notify2() {
		listeners.forEach(listener -> listener.update());
	}
}
