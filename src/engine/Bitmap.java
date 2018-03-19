package engine;

import java.awt.image.BufferedImage;

import controllers.FileController;

public class Bitmap {
	protected int[] pixels;
	
	public Bitmap(String location, int x, int y, int width, int height) {
		this.pixels = new int[width * height];
		
		BufferedImage image = FileController.loadImage(location);
		
		image.getRGB(x, y, width, height, pixels, 0, width);
	}
	
	public Bitmap(String location, int size) {
		this(location, 0, 0, size, size);
	}
	
	public int[] getPixels() { return pixels; }

	public void setPixels(int[] pixels) { this.pixels = pixels; }
}
