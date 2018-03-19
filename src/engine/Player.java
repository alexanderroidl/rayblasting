package engine;

import java.awt.Color;
import java.awt.Point;

import base.Vector2D;
import core.Config;
import world.Entity;

public class Player extends Entity {
	private Vector2D cameraPlane;
	
	public Vector2D getCameraPlane() { return cameraPlane; }

	public Player(double x, double y) {
		super(
			Entity.Type.PLAYER,
			new Vector2D(x, y), new Vector2D(1, 0),
			new Sprite(Config.spriteFolder + "objects/BluePot.bmp", 64, new Color(0,0,0)));
		
		position = new Vector2D(x, y);
		rotation = new Vector2D(1, 0);
		cameraPlane = new Vector2D(0, 0.66);
	}
	
	public Player(Point point) {
		this(point.x, point.y);
	}
	
	public void rotate(double rad) {
		rotation = rotation.rotate(rad);
		cameraPlane = cameraPlane.rotate(rad);
	}
	
	public void rotate(Movement.Direction rotDirection) {
		int multiplier = 0;
		
		switch(rotDirection) {
			case LEFT: multiplier = -1; break;
			case RIGHT: multiplier = 1; break;
			default: break;
		}
		rotate(Movement.rotateSpeed * multiplier);
	}
	
	public void rotateDeg(double deg) {
		rotation = rotation.rotateDeg(deg);
	}

	public boolean move(Movement.Direction moveDirection) {
		Vector2D move = Movement.getVector(moveDirection, rotation);
		Vector2D target = position.add(move);
		
		if(Game.getLevel().grid[(int) target.x][(int) target.y] == 0) {
			position = position.add(move);
			return true;
		}
		return false;
	}
	
	public Point getMapPoint() {
		return new Point((int)position.x, (int)position.y);
	}
}
