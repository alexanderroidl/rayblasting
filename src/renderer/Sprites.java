package renderer;

import core.RayCast;
import engine.Game;
import engine.Sprite;
import factories.SpriteFactory;
//import world.Entity;
//import world.Entity.Type;

class Sprites {
	public static void render(Renderer renderer, RayCast ray) {
		SpriteFactory sprites = Game.getSpriteFactory();
		Sprite sprite = sprites.get(5);
		int pixel;
		
		//if(renderer.getViewer().getPosition().distanceTo(v))
		
		if(ray.x < sprite.size) {
			for(int y = 0; y < sprite.size; y++) {
				pixel = sprite.getPixels()[ray.x + y * sprite.size];
				
//				renderer.getViewer().getPosition().distanceTo(v)
//				
//				Game.getEntitiesByType(Type.OBJECT).sort(new Comparator<Entity>() {
//				    @Override
//				    public int compare(Entity lhs, Entity rhs) {
//				        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//				    	double dl = renderer.getViewer()
//				        return lhs.customInt > rhs.customInt ? -1 : (lhs.customInt < rhs.customInt) ? 1 : 0;
//				    }
//				});

				if(pixel != sprite.getInvisibleColor()) {
					renderer.pixels[ray.x + y * renderer.windowSize.width] = pixel;
				}
			}
		}
	}
}
