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

public class Board extends JComponent implements KeyListener {

  private SnakeGame snakeGame;
  private List<Piece> pieces = new ArrayList();

  public Board() {
    pieces.add(new Apple(5, 5));
    setPreferredSize(new Dimension(500, 500));
    setVisible(true);
  }


  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    for (Piece piece : pieces) {
      System.out.println(piece);
      PositionedImage image = new PositionedImage(piece.getImage(), piece.getX(), piece.getY());
      image.draw(graphics);
    }


  }

  public static void main(String[] args) {
    // Here is how you set up a new window and adding our board to it
    JFrame frame = new JFrame("RPG Game");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    // Here is how you can add a key event listener
    // The board object will be notified when hitting any key
    // with the system calling one of the below 3 methods
    frame.addKeyListener(board);
    // Notice (at the top) that we can only do this
    // because this Board class (the type of the board object) is also a KeyListener
  }

  // To be a KeyListener the class needs to have these 3 methods in it
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

    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

    }
    repaint();
  }
}