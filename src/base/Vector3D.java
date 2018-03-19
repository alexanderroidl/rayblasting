package base;

import java.text.DecimalFormat;

public class Vector3D {
	public double x, y, z;
	
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3D() {
		this(0, 0, 0);
	}
	
	public Vector3D add(Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}
	
	public Vector3D multiply(double scalar) {
		return new Vector3D(x * scalar, y * scalar, z * scalar);
	}
	
	public String toString() {
		DecimalFormat f = new DecimalFormat("#.####");
		return "(" + f.format(x) + "; " + f.format(y) + "; " + f.format(z) + ")";
	}
	
	public double distanceTo(Vector3D v) {
	    return Math.sqrt(Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2) + Math.pow(z - v.z, 2));
	}
}
