package factories;

import java.awt.Point;

import base.Factory;
import core.Config;
import engine.Texture;

public class TextureFactory extends Factory<Texture> {
	public TextureFactory(String textureCollectionLocation, Point gridSize, int textureSize) {
		Texture texture;
		
		String fileLocation = Config.textureFolder + textureCollectionLocation;
		int size = textureSize;
		
		for(int y = 0; y < gridSize.y; y ++) {
			for(int x = 0; x < gridSize.x; x ++) {
				texture = new Texture(fileLocation, x * size, y * size, size);
				add(texture);
			}
		}
	}
	
	public TextureFactory(String[] textureLocations, int textureSize) {
		for(String fileLocation : textureLocations) {
			add(new Texture(Config.textureFolder + fileLocation, textureSize));
		}
	}
}
