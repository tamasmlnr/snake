package gui;

import domain.Piece;
import game.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JComponent implements KeyListener {

  private SnakeGame snakeGame;
  private List<Piece> pieces = new ArrayList();
  public static Integer delay = 600;
  public static Integer tempDelay = 600;
  private Timer timer;

  public Board() {
    snakeGame = new SnakeGame();
    pieces = snakeGame.getGameObjects();
    setPreferredSize(new Dimension(SnakeGame.MAX_WIDTH * 50, SnakeGame.MAX_HEIGHT * 50));
    setVisible(true);
    contineInDirection();
  }


  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    for(int i=0;i<=5;i++){
      for (int j=0;j<=5;j++){
        PositionedImage tile = new PositionedImage("src/gui/map.png", i*5, j*5);
        tile.draw(graphics);
      }
    }
   for (Piece piece : pieces) {
      PositionedImage image = new PositionedImage(piece.getImage(), piece.getX(), piece.getY());
      image.draw(graphics);
      if (!snakeGame.isAlive) {
        PositionedImage gO = new PositionedImage("src/gui/gameover.png", 1, 6);
        gO.draw(graphics);
      }

      refreshPieces();

    }
  }

  public void refreshPieces() {
    pieces = snakeGame.getGameObjects();
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Snake!");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      snakeGame.turnUp();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      snakeGame.turnDown();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      snakeGame.turnLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      snakeGame.turnRight();
    }
    repaint();
    if (tempDelay > delay) {
      timer.cancel();
      contineInDirection();
    }
  }


  public void buttonPressed(String button) {
    if (button.equals("down")) {
      snakeGame.turnDown();
    }
    else if (button.equals("up")) {
      snakeGame.turnUp();
    }
    else if (button.equals("left")) {
      snakeGame.turnLeft();
    }
    else if (button.equals("right")) {
      snakeGame.turnRight();
    }
    repaint();
    if (tempDelay > delay) {
      timer.cancel();
      contineInDirection();
    }
  }


  public void contineInDirection() {
    java.util.Timer t = new Timer();
    timer = t;
    t.schedule(new TimerTask() {
      @Override
      public void run() {
        snakeGame.continueInDirection();
        repaint();
      }
    }, delay, delay);
    tempDelay = delay;
  }

  public void cancelTimer() {
    timer.cancel();
  }

}