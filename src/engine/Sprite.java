package engine;

import java.awt.Color;

public class Sprite extends Bitmap {
	public final int size;
	
	private Color invisibleColor;
	
	public Sprite(String location, int x, int y, int size, Color invisibleColor) {
		super(location, x, y, size, size);
		
		this.size = size;
		this.invisibleColor = invisibleColor;
	}
	
	public Sprite(String location, int size, Color invisibleColor) {
		super(location, size);
		
		this.size = size;
		this.invisibleColor = invisibleColor;
	}
	
	public int getInvisibleColor() {
		return invisibleColor.getRGB();
	}
}
