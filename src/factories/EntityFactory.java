package factories;

import java.util.ArrayList;

import base.Factory;
import base.Vector2D;
import engine.Game;
import engine.Sprite;
import world.Entity;

public class EntityFactory extends Factory<Entity> {
	public EntityFactory() {
	}
	
	public Entity create(Entity.Type type, Vector2D pos, Sprite[] sprites) {
		Entity entity = new Entity(type, pos, new Vector2D(), sprites);
		add(entity);
		return entity;
	}
	
	public Entity create(Entity.Type type, Vector2D pos, int[] sprites) {
		return create(type, pos, Game.getSpriteFactory().byIndexes(sprites));
	}
	
	public Entity create(Entity.Type type, Vector2D pos, int sprite) {
		return create(type, pos, new int[] { sprite });
	}
	
	@Override
	public ArrayList<Entity> getCollection() {
		ArrayList<Entity>
			completeList = super.getCollection(),
			correctList = new ArrayList<Entity>();
		
		for(Entity entity : completeList) {
			if(entity.getType() != Entity.Type.NONE)
				correctList.add(entity);
		}
		return correctList;
	}
}
