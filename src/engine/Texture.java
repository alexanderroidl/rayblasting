package engine;

public class Texture extends Bitmap {
	public final int size;
	
	public Texture(String location, int x, int y, int size) {
		super(location, x, y, size, size);
		this.size = size;
	}
	
	public Texture(String location, int size) {
		super(location, size);
		this.size = size;
	}
}
