package gui;

import domain.Piece;
import game.GameLogic;
import game.MovementControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JComponent implements KeyListener {

  private GameLogic gameLogic;
  private List<Piece> pieces = new ArrayList();
  public static Integer delay = 600;
  public static Integer tempDelay = 600;
  private Timer timer;
  private MovementControl movementControl;

  public Board() {
    gameLogic = new GameLogic();
    pieces = gameLogic.getGameObjects();
    setPreferredSize(new Dimension(GameLogic.MAX_WIDTH * 50, GameLogic.MAX_HEIGHT * 50));
    setVisible(true);
    contineInDirection();
    movementControl = new MovementControl(gameLogic);
  }


  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    for (int i = 0; i <= 5; i++) {
      for (int j = 0; j <= 5; j++) {
        PositionedImage tile = new PositionedImage("src/gui/map.png", i * 5, j * 5);
        tile.draw(graphics);
      }
    }
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, GameLogic.MAX_HEIGHT * 50 - 30, 100, 30);
    graphics.setColor(Color.WHITE);
    graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    graphics.drawString("Score: " + String.valueOf(gameLogic.getScore()), 0, (GameLogic.MAX_WIDTH) * 50 - 10);
    graphics.setColor(Color.BLACK);
    graphics.fillRect(GameLogic.MAX_WIDTH * 50 - 180, GameLogic.MAX_HEIGHT * 50 - 30, 180, 30);
    graphics.setColor(Color.WHITE);
    graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    graphics.drawString("Highscore: " + String.valueOf(gameLogic.getHighscore()), GameLogic.MAX_WIDTH * 50 - 180, (GameLogic.MAX_WIDTH) * 50 - 10);
    for (Piece piece : pieces) {
      PositionedImage image = new PositionedImage(piece.getImage(), piece.getX(), piece.getY());
      image.draw(graphics);
      if (!gameLogic.isAlive) {
        PositionedImage gO = new PositionedImage("src/gui/gameover.png", 1, 6);
        gO.draw(graphics);
      }

      refreshPieces();

    }
  }

  public void refreshPieces() {
    pieces = gameLogic.getGameObjects();
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
      movementControl.turnUp();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      movementControl.turnDown();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      movementControl.turnLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      movementControl.turnRight();
    }
    repaint();
    if (tempDelay > delay) {
      timer.cancel();
      contineInDirection();
    }
  }

  public void buttonPressed(String button) {
    if (button.equals("down")) {
      movementControl.turnDown();
    } else if (button.equals("up")) {
      movementControl.turnUp();
    } else if (button.equals("left")) {
      movementControl.turnLeft();
    } else if (button.equals("right")) {
      movementControl.turnRight();
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
        movementControl.continueInDirection();
        repaint();
      }
    }, delay, delay);
    tempDelay = delay;
  }

}