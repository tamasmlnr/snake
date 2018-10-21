package game;

import domain.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static domain.Direction.*;

public class SnakeGame {
  public static final int MAX_WIDTH = 10;
  public static final int MAX_HEIGHT = 10;
  private Snake snake;
  private List<Piece> gameObjects;

  public SnakeGame() {
    snake = new Snake(1, 1, RIGHT);
  }


  public List<Piece> getGameObjects() {
    List<Piece> pieces = new ArrayList<>();
    pieces.add(new Apple(generateRandomCoordinate(), generateRandomCoordinate()));
    for (SnakePiece piece : snake.getPieces()) {
      pieces.add(piece);
    }
    return pieces;
  }

  public static int generateRandomCoordinate() {
    return ThreadLocalRandom.current()
        .nextInt(2, 10 + 1);
  }


  public void turnUp() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection()!=DOWN)
    snake.setDirection(UP);
  }

  public void turnDown() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection()!=UP)
    snake.setDirection(DOWN);
  }

  public void turnLeft() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection()!=RIGHT)
    snake.setDirection(LEFT);
  }

  public void turnRight() {
    snake.setOriginalDirection(snake.getDirection());
    snake.setDirection(RIGHT);
  }

  public void continueInDirection() {
    Direction direction = snake.getDirection();
    if (direction == RIGHT) moveRight();
    if (direction == LEFT) moveLeft();
    if (direction == UP) moveUp();
    if (direction == DOWN) moveDown();
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
}
