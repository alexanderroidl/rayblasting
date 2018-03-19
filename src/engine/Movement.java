package engine;

import base.Vector2D;

public class Movement {
	public final static double moveSpeed = 0.05;
	public final static double rotateSpeed = 0.0125;
	
	public static enum Direction {
		FORWARDS,
		BACKWARDS,
		LEFT,
		RIGHT
	}
	
	public static Vector2D getVector(Movement.Direction direction, Vector2D view) {
		Vector2D move = new Vector2D();

		switch(direction) {
			case FORWARDS: move = view.multiply(moveSpeed); break;
			case BACKWARDS: move = view.multiply(-moveSpeed); break;
			default: break;
		}
		return move;
	}
}
