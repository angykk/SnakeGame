package code;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

  private static final long serialVersionUID = 1L;

  // size of the window
  private static final int WINDOW_WIDTH = 500;
  private static final int WINDOW_HEIGHT = 500;

  // size of each cell in the grid
  private static final int CELL_SIZE = 20;

  // number of cells in the grid
  private static final int NUM_CELLS_X = WINDOW_WIDTH / CELL_SIZE;
  private static final int NUM_CELLS_Y = WINDOW_HEIGHT / CELL_SIZE;

  // directions the snake can move
  private static final int UP = 0;
  private static final int DOWN = 1;
  private static final int LEFT = 2;
  private static final int RIGHT = 3;

  // the snake's current direction
  private int direction;

  // the snake's body
  private Snake1 snake;

  // the food
  private Point food;

  // the timer used to animate the game
  private Timer timer;

  // the random number generator
  private Random rand;

  public SnakeGame() {
    // set the size of the panel
    setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

    // initialize the snake's body and direction
    snake = new Snake1(NUM_CELLS_X,NUM_CELLS_Y);
    direction = RIGHT;

    // create the food
    rand = new Random();
    food = new Point(rand.nextInt(NUM_CELLS_X), rand.nextInt(NUM_CELLS_Y));

    // create the timer and start it
    timer = new Timer(100, this);
    timer.start();

    // add the key listener
    addKeyListener(this);
    setFocusable(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // draw the grid
    g.setColor(Color.BLACK);
    for (int i = 0; i < NUM_CELLS_X; i++) {
      g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, WINDOW_HEIGHT);
    }
    for (int i = 0; i < NUM_CELLS_Y; i++) {
      g.drawLine(0, i * CELL_SIZE, WINDOW_WIDTH, i * CELL_SIZE);
    }

    // draw the snake
    g.setColor(Color.GREEN);
    snake.setColor(g, CELL_SIZE);

      // draw the food
      g.setColor(Color.RED);
      g.fillRect(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      // move the snake
      moveSnake();

      // check if the snake has collided with itself or the wall
      if (hasCollided()) {
        timer.stop();
      }

      // check if the snake has eaten the food
      if (snake.getPoints().get(0).equals(food)) {
        // add a new cell to the snake's body
        snake.getPoints().add(new Point(snake.getPoints().get(snake.getPoints().size() - 1)));

        // create a new piece of food
        food.setLocation(rand.nextInt(NUM_CELLS_X), rand.nextInt(NUM_CELLS_Y));
      }

      // repaint the panel
      repaint();
    }

    private void moveSnake() {
      // move the body
      for (int i = snake.getPoints().size() - 1; i > 0; i--) {
        snake.getPoints().get(i).setLocation(snake.getPoints().get(i - 1));
      }

      // move the head
      Point head = snake.getPoints().get(0);
      if (direction == UP) {
        head.y--;
      } else if (direction == DOWN) {
        head.y++;
      } else if (direction == LEFT) {
        head.x--;
      } else if (direction == RIGHT) {
        head.x++;
      }
    }

    private boolean hasCollided() {
      Point head = snake.getPoints().get(0);

      // check for collision with the wall
      if (head.x < 0 || head.x >= NUM_CELLS_X || head.y < 0 || head.y >= NUM_CELLS_Y) {
        return true;
      }

      // check for collision with the snake's body
      for (int i = 1; i < snake.getPoints().size(); i++) {
        if (head.equals(snake.getPoints().get(i))) {
          return true;
        }
      }

      return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_UP && direction != DOWN) {
        direction = UP;
      } else if (key == KeyEvent.VK_DOWN && direction != UP) {
        direction = DOWN;
      } else if (key == KeyEvent.VK_LEFT && direction != RIGHT) {
        direction = LEFT;
      } else if (key == KeyEvent.VK_RIGHT && direction != LEFT) {
        direction = RIGHT;
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new SnakeGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    }

