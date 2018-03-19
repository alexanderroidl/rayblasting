package renderer;

import java.awt.Color;

import base.ColorHelper;
import base.Vector3D;

public class Shader {
	public static int multiplyFog(int rgb, Color fog, double distance, double viewDistance) {
		double multiplier = distance / viewDistance;
		if(multiplier > 1) multiplier = 1;
    	
    	Vector3D v = ColorHelper.ToVector3D(rgb);
    	Vector3D vFog = ColorHelper.ToVector3D(fog).multiply(multiplier);
    	v = v.multiply(1 - multiplier).add(vFog);
    	
    	return ColorHelper.ToInt(v);
	}
	
	public static int darken(int rgb) {
		return (rgb >> 1) & 8355711;
	}
}
