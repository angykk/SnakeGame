package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener{
	
	final int boardx = 400;
	final int boardy = 400;
	
	final int cellSize = 20;
	final int numCell = boardx / cellSize;
	
	private Snake snake;
	private Apple apple;
	
	private Timer timer;
	
	final int right = 1;
	final int left = 2;
	final int up = 3;
	final int down = 4;
	private int direction = 1;
	
	AffineTransform headAT = new AffineTransform();
	
	public Board() {
		setPreferredSize(new Dimension(boardx,boardy));
		setBackground(Color.BLACK);
		setFocusable(true);
		
		snake = new Snake(numCell);
		//\\apple = new Apple(numCell);
		
		timer = new Timer(200,this);
		timer.start();
		
		addKeyListener(this);
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		
	    for (Point p : snake.getPoints()) {
	    	g2d.fillRect(p.x * cellSize, p.y * cellSize, cellSize, cellSize);
	    }
		
		for (int x = 0; x < snake.getPoints().size(); x ++) {
			 Point p = snake.getPoints().get(x);
			 int width = cellSize *(p.x);
			 int height = cellSize *(p.y);

			 if (x == 0) {
				 g2d.drawImage(snake.getHead(), cellSize *(p.x), cellSize *(p.y), cellSize,cellSize, this);	 
			 } else if (x == snake.getPoints().size() - 1) 
				 g2d.drawImage(snake.getTail(), cellSize *(p.x), cellSize *(p.y), cellSize,cellSize, this);
			 else {
//				 g2d.drawImage(snake.getBody(), cellSize *(p.x), cellSize *(p.y), cellSize,cellSize, this);
			 }
		}
		
		//Point p = apple.getPoints();
		//g.drawImage(apple.getApple(), cellSize *(p.x), cellSize *(p.y), cellSize,cellSize, this);
		
		//set colour of lines (R,G,B,OPACITY)
		g.setColor(new Color(255,255,255, 128));
		//horizontal lines
		for (int x = 0; x < numCell; x ++) {
			int yPosition = x * cellSize;
			g.drawLine(0,yPosition, boardx,yPosition);
		}
		//vertical lines
		for (int x = 0; x < numCell; x ++) {
			int xPosition = x * cellSize;
			g.drawLine(xPosition,0 ,xPosition,boardy);
		}
	}
	
	private void movement() {
		//body
		for (int x = snake.getPoints().size() - 1; x > 0; x --) {
			
			Point currentPosition = snake.getPoints().get(x);
			Point previousPosition = snake.getPoints().get(x-1);
//			position = snake.getPoints().get(x-1);
			currentPosition.setLocation(previousPosition);
		}
		
		//head
		Point head = snake.getPoints().get(0);
		if (direction == right) 
			head.x ++;
		
		else if(direction == left) {
			head.x --;
			//headAT.rotate(Math.PI, width, height);
		}
		else if (direction == up)
			head.y--;
		else
			head.y++;
			
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Snake Game");
		frame.add(new Board());
        frame.setSize(410,410);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		movement();
		repaint();
		
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_UP && direction != down) {
    	direction = up;
      } else if (key == KeyEvent.VK_DOWN && direction != up) {
        direction = down;
      } else if (key == KeyEvent.VK_LEFT && direction != right) {
    	 direction = left;
      } else if (key == KeyEvent.VK_RIGHT && direction != left) {
    	 direction = right;
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    
	

}

