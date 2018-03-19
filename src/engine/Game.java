package engine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import base.Vector2D;
import base.Cipher;
import controllers.FileController;
import core.Config;
import core.Main;
import factories.EntityFactory;
import factories.SpriteFactory;
import factories.TextureFactory;
import renderer.Renderer;
import world.Entity;
import world.Entity.Type;

public class Game {
	private static TextureFactory textureFactory;
	private static SpriteFactory spriteFactory;
	private static EntityFactory entityFactory;
	
	private static Level level;
	
	private Renderer renderer;
	private Player player;
	
	public Game(Level level) {
		textureFactory = new TextureFactory("first_level.png", new Point(8, 3), 64);
		spriteFactory = new SpriteFactory("static.png", new Point(8, 2), 64, Config.defaultSpriteInvisibleColor);
		entityFactory = new EntityFactory();
		
		Game.level = level;
		
		FileController.writeFile("map", Cipher.encode("023", 43));
		
		player = new Player(level.start);
		entityFactory.add(player);
		
		// Create object entities
		for(int y = 0; y < level.objects.length; y++) {
			for(int x = 0; x < level.objects[y].length; x++) {
				int spriteId = level.objects[x][y];
				if(spriteId < 1) continue;
				
				entityFactory.create(Type.OBJECT, new Vector2D(x, y), spriteId-1);
			}
		}
	}
	
	public void createRenderer(Dimension windowSize) {
		renderer = new Renderer(windowSize);
	}
	
	public void update() {
		if(Main.keyDown(KeyEvent.VK_W)) {
			player.move(Movement.Direction.FORWARDS);
		}
		
		if(Main.keyDown(KeyEvent.VK_A)) {
			player.rotate(Movement.Direction.LEFT);
		}
		
		if(Main.keyDown(KeyEvent.VK_D)) {
			player.rotate(Movement.Direction.RIGHT);
		}
		
		if(Main.keyDown(KeyEvent.VK_S)) {
			player.move(Movement.Direction.BACKWARDS);
		}
	}
	
	public void draw(Graphics2D g2d) {
		renderer.run(g2d, player);

		int spriteSize = renderer.windowSize.width / 5;
		
	    Image hero = Toolkit.getDefaultToolkit().getImage("res/wlds.png");
	    
	    g2d.drawImage(hero,
    		renderer.windowSize.width/2 - spriteSize/2, renderer.windowSize.height - spriteSize,
			spriteSize, spriteSize, null);
	    
	    g2d.setColor(Color.white);
	    g2d.drawString(player.getPosition() + "", 60, 20);
	}
	
	public static Level getLevel() {
		return level;
	}
	
	public static TextureFactory getTextureFactory() {
		return textureFactory;
	}
	
	public static ArrayList<Texture> getTextures() {
		return textureFactory.getCollection();
	}
	
	public static SpriteFactory getSpriteFactory() {
		return spriteFactory;
	}
	
	public static ArrayList<Sprite> getSprites() {
		return spriteFactory.getCollection();
	}

	public static EntityFactory getEntityFactory() {
		return entityFactory;
	}
	
	public static ArrayList<Entity> getEntities() {
		return entityFactory.getCollection();
	}
	
	public static ArrayList<Entity> getEntitiesByType(Entity.Type type) {
		ArrayList<Entity>
			fullList = entityFactory.getCollection(),
			typeList = new ArrayList<Entity>();
		
		for(Entity entity : fullList) {
			if(entity.getType() == type)
				typeList.add(entity);
		}
		return typeList;
	}
}
