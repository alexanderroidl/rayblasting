package world;

import base.Vector2D;
import engine.Sprite;

public class Entity {
	public static enum Type {
		NONE,
		PLAYER,
		NPC,
		OBJECT
	}
	protected Type type;
	
	protected Vector2D position;
	protected Vector2D rotation;
	
	protected double health;
	protected double armor;
	
	protected double velocity;
	
	protected boolean friendly;
	protected boolean immutable;
	
	protected Sprite[] sprites;
	protected int currentSprite;
	
	public Entity(
			Type type,
			Vector2D position, Vector2D rotation,
			Sprite[] sprites,
			boolean friendly, boolean immutable)
	{
		this.type = type;
		
		this.position = position;
		this.rotation = rotation;
		
		this.sprites = sprites;
		setCurrentSprite(0);
		
		health = 100;
		armor = 0;
		
		velocity = 1;
	}
	
	public Entity(Type type, Vector2D position, Vector2D rotation, Sprite sprite, boolean friendly, boolean immutable) {
		this(type, position, rotation, new Sprite[] { sprite }, friendly, immutable);
	}
	
	public Entity(Type type, Vector2D position, Vector2D rotation, Sprite[] sprites, boolean friendly) {
		this(type, position, rotation, sprites, friendly, false);
	}
	
	public Entity(Type type, Vector2D position, Vector2D rotation, Sprite sprite, boolean friendly) {
		this(type, position, rotation, new Sprite[] { sprite }, false);
	}
	
	public Entity(Type type, Vector2D position, Vector2D rotation, Sprite[] sprites) {
		this(type, position, rotation, sprites, false);
	}
	
	public Entity(Type type, Vector2D position, Vector2D rotation, Sprite sprite) {
		this(type, position, rotation, new Sprite[] { sprite });
	}
	
	public void setPosition(Vector2D position) {
		this.position = position;
	}
	
	public Vector2D getPosition() {
		return position;
	}
	
	public boolean isFriendly() {
		return friendly;
	}

	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}

	public boolean isImmutable() {
		return immutable;
	}

	public void setImmutable(boolean immutable) {
		this.immutable = immutable;
	}

	public Type getType() {
		return type;
	}

	public Sprite getCurrentSprite() {
		return sprites[currentSprite];
	}
	
	public void setCurrentSprite(int index) {
		currentSprite = index;
	}
	
	public void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}

	public Vector2D getRotation() {
		return rotation;
	}

	public void setRotation(Vector2D rotation) {
		this.rotation = rotation;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getArmor() {
		return armor;
	}

	public void setArmor(double armor) {
		this.armor = armor;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
}
