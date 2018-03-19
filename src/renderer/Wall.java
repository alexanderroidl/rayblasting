package renderer;

import java.awt.Dimension;
import java.awt.Point;

import base.Vector2D;
import core.RayCast;
import engine.Game;
import engine.Level;
import engine.Texture;

class Wall {
	public static void render(Renderer renderer, RayCast ray) {
		Dimension screen = renderer.windowSize;

		Vector2D rayPos = ray.getPosition();
		Vector2D rayDir = ray.getDirection();

		Point map = ray.getTargetPoint();
		
		Level level = Game.getLevel();

		// TEXTURES
		
	    int texNum = level.grid[map.x][map.y] - 1;
	    Texture texture = Game.getTextureFactory().get(texNum + 1);
	    
	    double wallX; // Exact position of where wall was hit
	    
	    if(ray.isSideHit()) {
	    	wallX = (rayPos.x + ((map.y - rayPos.y + (1 - ray.getStep().y) / 2) / rayDir.y) * rayDir.x);
	    } else {
	    	wallX = (rayPos.y + ((map.x - rayPos.x + (1 - ray.getStep().x) / 2) / rayDir.x) * rayDir.y);
	    }
	    wallX -= Math.floor(wallX);
	    
	    // X coordinate on the texture
	    
	    int texX = (int)(wallX * texture.size);
	    if((!ray.isSideHit() && rayDir.x > 0) || (ray.isSideHit() && rayDir.y < 0))
			texX = texture.size - texX - 1;
	    
	    Point tex = new Point(texX, 0);
	    
	    // Calculate y coordinate on texture
	    
	    int lineHeight = ray.getLineHeight();
	    
	    // Fog
	    //double fog = Game.getLevel().getFogMultiplier(ray.getPerpWallDistance());
	    
	    for(int y = ray.getDrawStart(); y < ray.getDrawEnd(); y++) {
	    	tex.y = ((y * 2 - screen.height + lineHeight) << 6) / lineHeight / 2;
	    	
	    	int color = texture.getPixels()[texX + tex.y * texture.size];
	    	
	    	if(ray.isSideHit()) // Make sides darker
	    		color = Shader.darken(color);
	    	
	    	// Fog
	    	
	    	color = Shader.multiplyFog(color, level.fog, ray.getPerpWallDistance(), level.getViewDistance());

	    	renderer.pixels[ray.x + y* screen.width] = color;
	    }
	    
	    // Save wall X
	    ray.setWallX(wallX);
	}
}
