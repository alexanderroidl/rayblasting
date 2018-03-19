package core;

import java.awt.Color;
import java.awt.Dimension;

import engine.Level;

public class Config {
	public static final String name = "Rayblasting";
	public static final Window.WindowMode defaultWindowMode = Window.WindowMode.WINDOWED;
	public static final Dimension windowedSize = new Dimension(640, 480);
	
	public static final int targetFPS = 60;
	public static final Level firstLevel = Level.test;
	public static final double maxViewingDistance = 10;
	public static final String resourceFolder = "res/";
	
	public static final int defaultTextureSize = 64;
	public static final String textureFolder = resourceFolder + "/textures/";
	
	public static final int defaultSpriteSize = 64;
	public static final String spriteFolder = resourceFolder + "/sprites/";
	public static final Color defaultSpriteInvisibleColor = new Color(152, 0, 136);
}
