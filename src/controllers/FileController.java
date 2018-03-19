package controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileController {
	public static boolean writeFile(String fileName, String content) {
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(content);
		    writer.close();
		    
		    return true;
		} catch (IOException e) {}
		
		return false;
	}
	
    public static BufferedImage loadImage(String fileName) {
        try {
            BufferedImage img;
            File f = new File(fileName);
            img = ImageIO.read(f);
            return img;
            
        } catch (IOException e) {
            System.out.println("\nImage couldn't be loaded. Will terminate.");
            System.out.println("(Path: \"" + fileName + "\")");
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
