package code;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Apple{
	
	private Point apple;
	private BufferedImage image;
	
	public Apple(int cell) {
		apple = new Point(rng(cell),rng(cell));
		
		 try {
			 image = ImageIO.read(new File ("apple.jpeg"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	
	public BufferedImage getApple() {
		return image;
	}
	public Point getPoints() {
		return apple;
	}
	
	public static int rng(int max) {
		int random = (int)(Math.random() * (max + 1));
		return random;
	}

	public static void main(String[] args) {
	
	}

}
