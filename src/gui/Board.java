package gui;

import domain.Apple;
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

  public Board() {
    snakeGame = new SnakeGame();
    pieces = snakeGame.getGameObjects();
    setPreferredSize(new Dimension(500, 500));
    setVisible(true);
    contineInDirection();
  }


  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    for (Piece piece : pieces) {
      PositionedImage image = new PositionedImage(piece.getImage(), piece.getX(), piece.getY());
      image.draw(graphics);
    }


  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Snake!");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    frame.addKeyListener(board);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  // But actually we can use just this one for our goals here
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
  }

  public void contineInDirection() {
    java.util.Timer t = new Timer();
    t.schedule(new TimerTask() {
      @Override
      public void run() {
        snakeGame.continueInDirection();
        repaint();
      }
    }, 0, 1000);
  }
}