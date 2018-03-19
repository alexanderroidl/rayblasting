package base;
import java.awt.Dimension;
import java.awt.Point;
import java.text.DecimalFormat;

public class Vector2D {
	public double x, y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D() {
		this(0, 0);
	}
	
	public Vector2D(Dimension d) {
		this(d.width, d.height);
	}
	
	public Vector2D add(Vector2D v) {
		return new Vector2D(x + v.x, y + v.y);
	}
	
	public Vector2D subtract(Vector2D v) {
		return new Vector2D(x - v.x, y - v.y);
	}
	
	public Vector2D multiply(Vector2D v) {
		return new Vector2D(x * v.x, y * v.y);
	}
	
	public Vector2D multiply(double scalar) {
		return new Vector2D(x * scalar, y * scalar);
	}
	
	public Vector2D divide(Vector2D v) {
		return multiply(new Vector2D(1/v.x, 1/v.y));
	}
	
	public Vector2D divide(double scalar) {
		return multiply(1/scalar);
	}
	
	public Vector2D rotate(double n)
	{
	    double rx = x * Math.cos(n) - y * Math.sin(n);
	    double ry = x * Math.sin(n) + y * Math.cos(n);
	    
	    return new Vector2D(rx, ry);
	}
	
	public Vector2D rotateDeg(double n) {
		return rotate(Math.toRadians(n));
	}
	
	public double distanceTo(Vector2D v) {
        return Math.sqrt(Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2));
	}
	
	public Point getPoint() {
		return new Point((int)x, (int)y);
	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("#.####");
		return "(" + df.format(x) + "; " + df.format(y) + ")";
	}
}
