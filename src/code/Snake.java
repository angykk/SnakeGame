package code;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

public class Snake {
	private List<Point> snake;
	
	public Snake(int snakeSize, int numX, int numY) {
		snake = new ArrayList<Point>();
		for (int i = 0; i < snakeSize; i++) {
			snake.add(new Point(numX / 2 - i, numY / 2));
		}
	}
	
	public Point getHeadPoint(){
		return snake.get(0); //position of head of snake
	}
	
	public List<Point> getPoints() {
		return snake; //return list of all points in snake
	}
	
	public int grow(boolean shouldGrow, Apple apple, int counter) {
		if (shouldGrow) {
			getPoints().add(new Point(0, 0));
			apple.reposition(getPoints());
			counter ++;
		}
		return counter;
	}
	
	public void movement(int direction) {

		// body movement
		for (int x = getPoints().size() - 1; x > 0; x--) { //iterating from the back of the snake, not including head
			Point currentPosition = getPoints().get(x); //starting from the last point - position updates to the point before
			Point nextPosition = getPoints().get(x - 1); 
			currentPosition.setLocation(nextPosition);
		}

		// head movement
		
		if (direction == 1) {//direction == right
			getHeadPoint().x++;
		}
		else if (direction == 2) { // direction == left
			getHeadPoint().x--;
		} else if (direction == 3) // direction == up
			getHeadPoint().y--;
		else { //direction == down
			getHeadPoint().y++;
		}
	}
	
	public boolean collide(int numCellx, int numCelly, int boardStrip, int cellSize) {
		boolean collided = false;
		if (getHeadPoint().x < 0 || getHeadPoint().x > numCellx ||getHeadPoint().y < boardStrip/cellSize || getHeadPoint().y > boardStrip/cellSize + numCelly) {
			collided = true;
			//checking if collided with boundaries of the game
		}
		for (int x = 1; x < getPoints().size(); x ++) { //checking if collided with itself
			if(getHeadPoint().equals(getPoints().get(x))) {
				collided = true;
			}
		}
		
		return collided;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
