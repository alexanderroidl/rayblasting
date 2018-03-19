package factories;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import base.Factory;
import core.Config;
import engine.Sprite;

public class SpriteFactory extends Factory<Sprite> {
	public SpriteFactory(String spriteCollectionLocation, Point gridSize, int spriteSize, Color invisibleColor) {
		Sprite sprite;
		
		int size = spriteSize;
		String fileLocation = Config.spriteFolder + spriteCollectionLocation;
		
		for(int y = 0; y < gridSize.y; y ++) {
			for(int x = 0; x < gridSize.x; x ++) {
				sprite = new Sprite(fileLocation, x * size, y * size, size, invisibleColor);
				add(sprite);
			}
		}
	}
	
	public SpriteFactory(String[] spriteLocations, int spriteSize, Color invisibleColor) {
		for(String fileLocation : spriteLocations) {
			add(new Sprite(fileLocation, spriteSize, invisibleColor));
		}
	}
	
	public Sprite[] byIndexes(int[] indexes) {
		ArrayList<Sprite> foundSprites = new ArrayList<Sprite>();
		
		for(int spriteIndex : indexes)
			foundSprites.add(get(spriteIndex));
		
		Sprite[] spriteArray = new Sprite[] {};
		
		return foundSprites.toArray(spriteArray);
	}
}
