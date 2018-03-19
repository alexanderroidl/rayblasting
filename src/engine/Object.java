package engine;

import base.Vector2D;
import world.Entity;

public class Object extends Entity {
	public Object(Vector2D position, Sprite sprite) {
		super(Entity.Type.OBJECT, position, new Vector2D(), sprite);
	}
}
