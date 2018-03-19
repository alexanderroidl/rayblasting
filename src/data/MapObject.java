package data;

//import base.Vector2D;
//import engine.Game;
//import engine.Object;
//import factories.EntityFactory;
//import world.Entity;

public class MapObject {
//	Vector2D playerPos = new Vector2D(start.x, start.y);
//	EntityFactory entityFactory = Game.getEntityFactory();
//	
//	for (Object object : objects) {
//		
//		//entityFactory.create(Entity.Type.OBJECT, new Vector2D(9, 8), 2);
//		entityFactory.add(new Object(new Vector2D(9, 8), spriteFactory.get(2)));
//		
//		for(Entity entity : getEntities()) {
//			if(!(entity instanceof Object)) continue;
//			Object object = (Object) entity;
//			System.out.println("Position: " + object.getPosition());
//		}
//	    
//		System.out.println("Distance to " + spriteID + " is " + playerPos.distanceTo(position));
//	}
	public int spriteId;
	public int x;
	public int y;
	
	public MapObject(int spriteId, int x, int y) {
		this.spriteId = spriteId;
		this.x = x;
		this.y = y;
	}
}
