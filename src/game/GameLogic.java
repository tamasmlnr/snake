package game;

import domain.*;
import gui.Board;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static domain.Direction.*;

public class GameLogic {
  public static final int MAX_WIDTH = 15;
  public static final int MAX_HEIGHT = 15;
  private Snake snake;
  private List<Piece> gameObjects;
  private Apple apple;
  public static Boolean isAlive;
  private int score;
  private int highscore;
  public static int totalTurns;
  private List<Bomb> bombs;
  private Fly fly;

  public GameLogic() {
    bombs = new ArrayList<>();
    isAlive = true;
    snake = new Snake();
    snake.getHead()
        .setImage(SnakePiece.HEADRIGHT);
    fly = new Fly((new Coordinate((MAX_WIDTH + 1) * 50, ((MAX_HEIGHT + 1) * 50))));
    apple = new Apple(new Coordinate(10, 10));
    highscore = readHighScore();
  }

  private int readHighScore() {
    Path file = Paths.get("highscore.txt");
    File f = new File("highscore.txt");
    if (f.exists() && !f.isDirectory()) {
      try {
        List<String> lines = Files.readAllLines(file);
        if (lines == null && lines.size() >= 0) {
          highscore = Integer.parseInt(lines.get(0));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return 0;
  }

  void triggerTimedEvents() {
    totalTurns++;
    if (totalTurns % 50 == 0 && bombs.size() < 20) {
      bombs.add(new Bomb(generateRandomCoordinate()));
    }
    if (totalTurns % 100 == 0 && totalTurns != 0) {
      spawnFly();
    }
    if (totalTurns % 110 == 0) {
      fly.setX((MAX_WIDTH + 1) * 50);
      fly.setY((MAX_HEIGHT + 1) * 50);
    }
  }

  public List<Piece> getGameObjects() {
    List<Piece> pieces = new ArrayList<>();
    pieces.add(apple);
    for (SnakePiece piece : snake.getPieces()) {
      pieces.add(piece);
    }
    for (Bomb bomb : bombs) {
      pieces.add(bomb);
    }
    pieces.add(fly);
    gameObjects = pieces;
    return pieces;
  }

  public Coordinate generateRandomCoordinate() {
    Coordinate newCoordinate = new Coordinate(ThreadLocalRandom.current()
        .nextInt(2, MAX_HEIGHT + 1),
        ThreadLocalRandom.current()
            .nextInt(2, MAX_HEIGHT + 1));
    return checkIfCoordinateIsTaken(newCoordinate);
  }

  private Coordinate checkIfCoordinateIsTaken(Coordinate newCoordinate) {
    for (Coordinate coordinate : getAllTakenCoordinates()) {
      if (coordinate.getX() == newCoordinate.getX() && coordinate.getY() == newCoordinate.getY()) {
        return generateRandomCoordinate();
      }
    }
    return newCoordinate;
  }

  public List<Coordinate> getAllTakenCoordinates() {
    List<Coordinate> allCoordinates = new ArrayList<>();
    getGameObjects().stream()
        .forEach(piece -> allCoordinates.add(piece.getCoordinate()));
    return allCoordinates;
  }

  public void checkIfSnakeCollides() {
    if (snake.getHead()
        .getX() > MAX_HEIGHT ||
        snake.getHead()
            .getX() < 1 ||
        snake.getHead()
            .getY() < 1 ||
        snake.getHead()
            .getY() > MAX_WIDTH) {
      gameOver();
      saveHighScore(score);
    }
    for (int i = 1; i < snake.getPieces()
        .size(); i++) {
      if (snake.getHead()
          .runsInto(snake.getPieces()
              .get(i))) {
        gameOver();
      }
      if (collidesWithBomb()) {
        gameOver();
      }
      if (collidesWithFly()) {
        fly.setX((MAX_WIDTH + 1) * 50);
        fly.setY((MAX_HEIGHT + 1) * 50);
        score += 100;
      }
    }
  }

  private void saveHighScore(int score) {
    List<String> lines = Arrays.asList("" + score);
    Path file = Paths.get("highscore.txt");
    try {
      Files.write(file, lines, Charset.forName("UTF-8"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean collidesWithFly() {
    if (fly.getX() == snake.getHead()
        .getX() &&
        fly.getY() == snake.getHead()
            .getY()) {
      return true;
    }
    return false;
  }

  private boolean collidesWithBomb() {
    for (Bomb bomb : bombs) {
      if (snake.getHead()
          .runsInto(bomb)) {
        return true;
      }
    }
    return false;
  }

  private void gameOver() {
    File file = new File("src/assets/aww.wav");
    play(file);
    isAlive = false;
    for (Bomb bomb : bombs) {
      bomb.explode();
    }
  }

  void checkIfSnakeEatsApple() {
    if (getApple().runsInto(snake.getHead())) {
      grow();
      score += 10;
      for (SnakePiece piece : snake.getPieces()) {
        while (apple.runsInto(piece)) {
          apple.setCoordinate(generateRandomCoordinate());
          break;
        }
      }
      if (Board.delay > 81) {
        Board.delay -= 20;
      }
    }
  }


  private void grow() {
    if (snake.getDirection() == LEFT) {
      snake.getPieces()
          .add(new SnakePiece(new Coordinate(snake.getLastPiece()
              .getX() + 1, snake.getLastPiece()
              .getY())));
    }

    if (snake.getDirection() == RIGHT) {
      snake.getPieces()
          .add(new SnakePiece(new Coordinate(snake.getLastPiece()
              .getX() - 1, snake.getLastPiece()
              .getY())));
    }

    if (snake.getDirection() == UP) {
      snake.getPieces()
          .add(new SnakePiece(new Coordinate(snake.getLastPiece()
              .getX(), snake.getLastPiece()
              .getY() + 1)));
    }

    if (snake.getDirection() == DOWN) {
      snake.getPieces()
          .add(new SnakePiece(new Coordinate(snake.getLastPiece()
              .getX(), snake.getLastPiece()
              .getY() - 1)));
    }
  }

  private void spawnFly() {
    fly.setCoordinate(generateRandomCoordinate());
  }

  public Apple getApple() {
    return (Apple) gameObjects.get(0);
  }


  public void play(File file) {
    try {
      final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

      clip.addLineListener(new LineListener() {
        @Override
        public void update(LineEvent event) {
          if (event.getType() == LineEvent.Type.STOP)
            clip.close();
        }
      });

      clip.open(AudioSystem.getAudioInputStream(file));
      clip.start();
    } catch (Exception exc) {
      exc.printStackTrace(System.out);
    }
  }

  public int getScore() {
    return score;
  }

  public Snake getSnake() {
    return snake;
  }
}
