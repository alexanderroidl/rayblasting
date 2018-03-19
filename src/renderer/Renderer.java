package renderer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import core.RayCast;
import engine.Player;

public class Renderer {
	public final Dimension windowSize;

	private BufferedImage image;
	int[] pixels;
	
	private Player viewer;
	
	public Renderer(Dimension windowSize) {
		image = new BufferedImage(windowSize.width, windowSize.height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		this.windowSize = windowSize;
	}
	
	public Player getViewer() {
		return viewer;
	}
	
	public void run(Graphics2D g2d, Player viewer) {
		for(int p = 0; p < pixels.length; p++) pixels[p] = 0;
		//for(int i = 0; i < widths.length; i++)
		
		this.viewer = viewer;
		
		// Cast rays
		
		for(int x = 0; x < windowSize.width; x++) {
			// Add ray to list
			RayCast ray = new RayCast(this, x, viewer.getPosition());

			Wall.render(this, ray);
			
			FloorCeiling.render(this, ray);
			Sprites.render(this, ray);
		}
		
		// Draw calculation
		
		g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
	}
}
