package game;

import domain.*;
import gui.Board;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static domain.Direction.*;

public class SnakeGame {
  public static final int MAX_WIDTH = 15;
  public static final int MAX_HEIGHT = 15;
  private Snake snake;
  private List<Piece> gameObjects;
  private Apple apple;
  public Boolean isAlive;
  private int score;
  public static int totalTurns;
  private List<Bomb> bombs;
  private Fly fly;

  public SnakeGame() {
    isAlive = true;
    snake = new Snake();
    apple = new Apple(generateRandomCoordinate());
    snake.getHead()
        .setImage(SnakePiece.HEADRIGHT);
    bombs = new ArrayList<>();
    fly = new Fly((new Coordinate((MAX_WIDTH+1)*50,((MAX_HEIGHT+1)*50))));
  }

  private void triggerTimedEvents() {
    totalTurns++;
    if (totalTurns % 50 == 0 && bombs.size() < 20) {
      bombs.add(new Bomb(generateRandomCoordinate()));
    }
    if (totalTurns%100==0&&totalTurns!=0) {
      spawnFly();
    }
    if(totalTurns%110==0){
      fly.setX((MAX_WIDTH+1)*50);
      fly.setY((MAX_HEIGHT+1)*50);
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

  public static Coordinate generateRandomCoordinate() {
    return new Coordinate(ThreadLocalRandom.current()
        .nextInt(2, MAX_HEIGHT + 1),
        ThreadLocalRandom.current()
            .nextInt(2, MAX_HEIGHT + 1));
  }


  public void turnUp() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection() != DOWN) {
      snake.setDirection(UP);
      snake.getHead()
          .setImage(SnakePiece.HEADUP);
    }
  }

  public void turnDown() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection() != UP) {
      snake.setDirection(DOWN);
      snake.getHead()
          .setImage(SnakePiece.HEADDOWN);
    }
  }

  public void turnLeft() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection() != RIGHT) {
      snake.setDirection(LEFT);

      snake.getHead()
          .setImage(SnakePiece.HEADLEFT);
    }
  }

  public void turnRight() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection() != LEFT) {
      snake.setDirection(RIGHT);

      snake.getHead()
          .setImage(SnakePiece.HEADRIGHT);
    }
  }

  public void continueInDirection() {
    if (isAlive) {
      Direction direction = snake.getDirection();
      if (direction == RIGHT) moveRight();
      if (direction == LEFT) moveLeft();
      if (direction == UP) moveUp();
      if (direction == DOWN) moveDown();

      checkIfSnakeEatsApple();
      checkIfSnakeCollides();
      triggerTimedEvents();
    }
  }

  private void checkIfSnakeCollides() {
    if (snake.getHead()
        .getX() > MAX_HEIGHT ||
        snake.getHead()
            .getX() < 1 ||
        snake.getHead()
            .getY() < 1 ||
        snake.getHead()
            .getY() > MAX_WIDTH) {
      gameOver();
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
      if (collidesWithFly()){
        fly.setX((MAX_WIDTH+1)*50);
        fly.setY((MAX_HEIGHT+1)*50);
        score+=100;
      }
    }
  }

  private boolean collidesWithFly() {
  if (fly.getX()==snake.getHead().getX()&&
        fly.getY()==snake.getHead().getY()){
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

  private void checkIfSnakeEatsApple() {
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

  public void moveLeft() {
    shiftPieces();
    snake.getPieces()
        .get(0)
        .setX(snake.getPieces()
            .get(0)
            .getX() - 1);
  }

  public void moveUp() {
    shiftPieces();
    snake.getPieces()
        .get(0)
        .setY(snake.getPieces()
            .get(0)
            .getY() - 1);
  }

  public void moveRight() {
    shiftPieces();
    snake.getPieces()
        .get(0)
        .setX(snake.getPieces()
            .get(0)
            .getX() + 1);
  }


  public void moveDown() {
    shiftPieces();
    snake.getPieces()
        .get(0)
        .setY(snake.getPieces()
            .get(0)
            .getY() + 1);
  }

  public void shiftPieces() {
    for (int i = snake.getPieces()
        .size() - 1; i > 0; i--) {
      snake.getPieces()
          .get(i)
          .setX(snake.getPieces()
              .get(i - 1)
              .getX());
      snake.getPieces()
          .get(i)
          .setY(snake.getPieces()
              .get(i - 1)
              .getY());
    }
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
}
