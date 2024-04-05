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
	private int mCellx;//number of cells in x axis
	private int mCelly;//number of cells in y axis
	private int mboardStrip;//length of the strip above panel
	private int mcellSize;//length of cells
	
	public Apple(int cellx,int celly, List<Point> snake, int boardStrip, int cellSize) {
		mCellx = cellx - 1;
		mCelly = celly - 1;
		mboardStrip = boardStrip;
		mcellSize = cellSize;
		reposition(snake);
		
		 try { //https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html
			image = ImageIO.read(new File ("apple.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getApple() {//apple image
		return image;
	}
	public Point getPoints() {//position of apple
		return apple;
	}
	
	public void reposition(List<Point> snake) {//generating location for apple
		Point position;
		boolean equals = false;
		while (true) {
			position = new Point(rng(mCellx, 0), rng(mCelly + mboardStrip/mcellSize, mboardStrip/mcellSize)); 
			for (int x = 0; x < snake.size(); x ++) {
				if(position.equals(snake.get(x))){
					equals = true;
					break;
				}
			}
			if (!equals) {
				apple = new Point(position);
				break;
			}
		}
	}
	
	public static int rng(int max, int min) {
		int random = (int)(Math.random() * (max - min + 1) + min);
		return random;
	}

	public static void main(String[] args) {
	
	}

}
