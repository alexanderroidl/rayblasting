package base;

import java.awt.Color;

public class ColorHelper {
	public static int ToInt(Color c) {
		//return ((c.getRed() & 0x0ff) << 16) | ((c.getGreen() & 0x0ff) << 8) | (c.getBlue() & 0x0ff);
		return c.getRGB();
	}
	
	public static int ToInt(int r, int g, int b) {
		return ToInt(new Color(r, g, b));
	}
	
	public static int ToInt(Vector3D v) {
		return ToInt((int) v.x, (int) v.y, (int) v.z);
	}
	
	public static Color ToColor(int rgb) {
		return new Color((rgb >> 16) & 0x0ff, (rgb >> 8) &0x0ff, rgb &0x0ff);
	}
	
	public static Vector3D ToVector3D(int r, int g, int b) {
		return new Vector3D(r, g, b);
	}
	
	public static Vector3D ToVector3D(Color c) {
		return new Vector3D(c.getRed(), c.getGreen(), c.getBlue());
	}
	
	public static Vector3D ToVector3D(int rgb) {
		return ToVector3D(ToColor(rgb));
	}
}
