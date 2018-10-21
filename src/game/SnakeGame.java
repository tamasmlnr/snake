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
    snake.setDirection(UP);
  }

  public void turnDown() {
    snake.setDirection(DOWN);
  }

  public void turnLeft() {
    snake.setDirection(LEFT);
  }

  public void turnRight() {
    snake.setDirection(RIGHT);
  }

  public void continueInDirection() {
    Direction direction=snake.getDirection();
    if(direction==RIGHT) snake.moveRight();
    if(direction==LEFT) snake.moveLeft();
    if(direction==UP) snake.moveUp();
    if(direction==DOWN) snake.moveDown();
  }
}
