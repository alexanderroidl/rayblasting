package core;

import java.awt.Dimension;
import java.awt.Point;

import base.Vector2D;
import engine.Game;
import engine.Player;
import renderer.Renderer;

public class RayCast {
	public final int x;
	
	private final Vector2D position;
	private final Vector2D direction;
	
	private boolean sideHit;
	private double perpWallDistance;
	private double wallX;
	private Point step;
	
	private int lineHeight;
	private int drawStart;
	private int drawEnd;
	
	private Point targetPoint;
	
	public RayCast(Renderer renderer, int x, Vector2D position) {
		Dimension screen = renderer.windowSize;
		Player viewer = renderer.getViewer();
		double cameraX = x * 2 / (double) screen.width - 1;
		
		this.x = x;
		
		this.position = position;
		this.direction = viewer.getRotation().add(viewer.getCameraPlane().multiply(cameraX));
		
		Cast(screen);
	}
	
	public Vector2D getPosition() { return position; }
	
	public Vector2D getDirection() { return direction; }
	
	public boolean isSideHit() { return sideHit; }
	
	public double getPerpWallDistance() { return perpWallDistance; }
	
	public Point getStep() { return step; }

	public int getDrawStart() { return drawStart; }

	public int getDrawEnd() { return drawEnd; }
	
	public Point getTargetPoint() { return targetPoint; }
	
	public double getWallX() { return wallX; }
	
	public void setWallX(double wallX) { this.wallX = wallX; }
	
	public int getLineHeight() { return lineHeight; }
	
	private void setLineHeight(int screenHeight, double perpWallDistance) {
		lineHeight = (int)(screenHeight / perpWallDistance);
		
		drawStart = screenHeight / 2 -lineHeight / 2;
		if(drawStart < 0) drawStart = 0;
		
		drawEnd =  screenHeight / 2 + lineHeight / 2;
		if(drawEnd >= screenHeight) drawEnd = screenHeight - 1;
	}
	
	public void Cast(Dimension screen) {
		Point mapPoint = position.getPoint();
		
		Vector2D deltaDist = new Vector2D() {
			{
				x = Math.sqrt(1 + Math.pow(direction.y, 2) / Math.pow(direction.x, 2));
				y = Math.sqrt(1 + Math.pow(direction.x, 2) / Math.pow(direction.y, 2));
			}
		};
		
		Point aStep = new Point();
		Vector2D slideDist = new Vector2D();
		
		// Calculate step and initial sideDist
		
		if(direction.x < 0) {
			aStep.x = -1;
			slideDist.x = (position.x - mapPoint.x) * deltaDist.x;
		} else {
			aStep.x = 1;
			slideDist.x = (mapPoint.x + 1 - position.x) * deltaDist.x;
		}
		
		if(direction.y < 0) {
			aStep.y = -1;
			slideDist.y = (position.y - mapPoint.y) * deltaDist.y;
		} else {
			aStep.y = 1;
			slideDist.y = (mapPoint.y + 1 - position.y) * deltaDist.y;
		}
		
		// Perform DDA
		
		boolean hit = false;
		boolean side = false;
		
		while(!hit) {
			if(slideDist.x < slideDist.y) {
				slideDist.x += deltaDist.x;
				mapPoint.x += aStep.x;
				side = false;
			} else {
				slideDist.y += deltaDist.y;
				mapPoint.y += aStep.y;
				side = true;
			}
			
			if(Game.getLevel().grid[mapPoint.x][mapPoint.y] > 0)
				hit = true;
		}
		
		// Calculate distance projected on camera direction
		
		double perpWallDist;
		
		if(!side) perpWallDist = (mapPoint.x - position.x + (1 - aStep.x) / 2) / direction.x;
		else perpWallDist = (mapPoint.y - position.y + (1 - aStep.y) / 2) / direction.y;
		
		// Set ray up
		
		setLineHeight(screen.height, perpWallDist);
		
		this.step = aStep;
		this.targetPoint = mapPoint;
		this.sideHit = side;
		this.perpWallDistance = perpWallDist;
	}
}
