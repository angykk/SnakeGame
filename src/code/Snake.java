package code;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Snake {
	private List<Point> snake;
	private BufferedImage head;
	private BufferedImage body;
	private BufferedImage tail;
	
	public Snake(int numX) {
		snake = new ArrayList<Point>();
		snake.add(new Point(numX / 2, numX / 2));
		snake.add(new Point(numX / 2 - 1, numX / 2));
		snake.add(new Point(numX / 2 - 2, numX / 2));
		snake.add(new Point(numX / 2 - 3, numX / 2));
		
		 try {
			 head = ImageIO.read(new File ("head.jpg"));
			 body = ImageIO.read(new File ("body.jpg"));
			 tail = ImageIO.read(new File ("tail.jpg"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	
	public BufferedImage getHead(){
		return head;
	}
	
	public BufferedImage getBody(){
		return body;
	}
	
	public BufferedImage getTail(){
		return tail;
	}
	
	
	public List<Point> getPoints() {
		return snake;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
