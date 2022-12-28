package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

public class Snake1 {
	private List<Point> snake;
	  
	public Snake1(int numX, int numY) {
	    snake = new ArrayList<Point>();
		 // add three cells to the snake's body
		 snake.add(new Point(numX / 2, numY / 2));
		 snake.add(new Point(numX / 2 - 1, numY / 2));
		 snake.add(new Point(numX / 2 - 2, numY / 2));
	}
	
//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//	}

	public static void main(String[] args) {
		

	}
	
	public List<Point> getPoints() {
		return snake;
	}

	public void setColor(Graphics g, int cellSize) {
	    for (Point p : snake  ) {
	        g.fillRect(p.x * cellSize, p.y * cellSize, cellSize, cellSize);
	      }
	}
}
