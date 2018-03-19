package engine;

import java.awt.Color;
import java.awt.Point;

public class Level {
	public final String name;
	
	public final int[][] grid;
	
	public final int[][] objects;
	
	public final int ceilingTextureIndex, floorTextureIndex;
	
	public final Point start;
	
	private double viewDistance;
	
	public final Color fog = new Color(0, 0, 0);
	
	public Level(String name, int[][] grid, int[][] objects, Point start, double viewDistance, int ceilingTextureIndex, int floorTextureIndex) {
		this.name = name;
		this.grid = grid;
		this.objects = objects;
		
		this.start = start;
		
		this.viewDistance = viewDistance;
		
		this.ceilingTextureIndex = ceilingTextureIndex;
		this.floorTextureIndex = floorTextureIndex;
	}
	
	public Level(String name, int[][] grid, Point start, double viewDistance, int ceilingTextureIndex, int floorTextureIndex) {
		this(name, grid, new int[][] {}, start, viewDistance, ceilingTextureIndex, floorTextureIndex);
	}

	public double getViewDistance() { return viewDistance; }

	public void setViewDistance(double viewDistance) { this.viewDistance = viewDistance; }
	
	public static Level test = new Level("Testworld",
		new int[][] {
			{3,8,3,3,3,12,12,3,3,3,3,3},
			{3,0,0,0,3,0,0,3,0,0,0,3},
			{3,0,0,0,0,0,0,3,0,0,0,3},
			{3,0,3,3,3,0,0,3,0,3,3,3},
			{3,0,3,0,0,0,0,0,0,0,0,12},
			{12,0,0,0,0,10,0,0,0,0,0,12},
			{12,0,0,0,0,0,10,0,0,3,0,12},
			{3,0,3,0,0,0,0,0,3,3,0,3},
			{3,0,3,3,3,0,0,3,3,3,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,3},
			{3,3,3,3,3,12,12,3,3,3,3,3}
		}, new int[][] {
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,4,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,5,0,0,0,0,0,3,0},
			{0,0,0,0,0,0,0,0,0,0,0,0}
		},
		new Point(5,4), 8, 13, 11);
}
