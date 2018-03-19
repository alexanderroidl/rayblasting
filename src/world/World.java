package world;

import base.Vector2D;
import engine.Game;
import engine.Texture;

public class World {
	private int[][] map;

	public World(int[][] map) {
		this.map = map;
	}
	
	public Vector2D snap(int x, int y) {
		return new Vector2D(x * map[0].length / map[0].length, x * map[0].length / map[0].length);
	}
	
	public Vector2D snap(Vector2D position) {
		return snap((int) position.x, (int) position.y);
	}
	
	public void setBlockTexture(Vector2D position, Texture texture) {
		map[(int) position.x][(int) position.y] = Game.getTextureFactory().indexOf(texture);
	}
	
	public int[][] getMap() {
		return map;
	}
}
