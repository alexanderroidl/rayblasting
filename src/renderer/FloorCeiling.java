package renderer;

import java.awt.Dimension;
import java.awt.Point;

import base.Vector2D;
import core.RayCast;
import engine.Game;
import engine.Level;
import engine.Texture;
import factories.TextureFactory;

class FloorCeiling {
	public static void render(Renderer renderer, RayCast ray) {
	      //FLOOR CASTING
		
	      Vector2D floorWall = new Vector2D();
	      
	      Vector2D rayPos = ray.getPosition(),
	    		  rayDir = ray.getDirection();
	      
	      boolean side = ray.isSideHit();
	      Point target = ray.getTargetPoint();
	      double wallX = ray.getWallX();
	      
	      Dimension screen = renderer.windowSize;

	      // Four different wall directions possible
	      
	      if(!side && rayDir.x > 0)
	      {
	    	  floorWall = new Vector2D(target.x, target.y + wallX);
	      }
	      else if(!side && rayDir.x < 0)
	      {
	    	  floorWall = new Vector2D(target.x + 1.0, target.y + wallX);
	      }
	      else if(side && rayDir.y > 0)
	      {
	    	  floorWall = new Vector2D(target.x + wallX, target.y);
	      }
	      else
	      {
	    	  floorWall = new Vector2D(target.x + wallX, target.y + 1.0);
	      }

	      double distWall, distPlayer, currentDist;

	      distWall = ray.getPerpWallDistance();
	      distPlayer = 0.0;
	      
	      int drawEnd = ray.getDrawEnd();

	      //if (drawEnd < 0) drawEnd = screen.height; // Becomes < 0 when the integer overflows

	      // Draw the floor from drawEnd to the bottom of the screen
	      
	      Level level = Game.getLevel();
	      
	      TextureFactory textures = Game.getTextureFactory();
	      
	      Texture floorTexture = textures.get(level.floorTextureIndex),
	    		  ceilingTexture = textures.get(level.ceilingTextureIndex);
	      
	      for(int y = drawEnd + 1; y < screen.height; y++)
	      {
	        currentDist = screen.height / (2.0 * y - screen.height);

	        @SuppressWarnings("unused")
			double weight = (currentDist - distPlayer) / (distWall - distPlayer);
	        
	        Vector2D currentFloor = new Vector2D(
	        		weight * floorWall.x + (1.0 - weight) * rayPos.x,
	        		weight * floorWall.y + (1.0 - weight) * rayPos.y
	    		);

	        @SuppressWarnings("serial")
			Point floorTex = new Point() {
	        	{
	        		x = (int)(currentFloor.x * floorTexture.size) % floorTexture.size;
	        		y = (int)(currentFloor.y * floorTexture.size) % floorTexture.size;
	        	}
	        };
	        
	        int iCeiling = floorTex.x + floorTex.y * floorTexture.size;
	        int iFloor = floorTex.x + floorTex.y * floorTexture.size;
	        
	        //floor
	        int floorPixel = Shader.darken(floorTexture.getPixels()[iFloor]);
	        floorPixel = Shader.multiplyFog(floorPixel, level.fog, currentDist, level.getViewDistance());
	        renderer.pixels[ray.x + y * screen.width] = floorPixel;
	        
	        // Ceiling (symmetrical!)
	        int ceilingPixel = ceilingTexture.getPixels()[iCeiling];
	        ceilingPixel = Shader.multiplyFog(ceilingPixel, level.fog, currentDist, level.getViewDistance());
	        renderer.pixels[ray.x + (screen.height - y) * screen.width] = ceilingPixel;
	      }
	}
}
