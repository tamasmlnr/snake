package game;

import domain.Direction;
import domain.Snake;
import domain.SnakePiece;

import static domain.Direction.*;
import static domain.Direction.LEFT;
import static domain.Direction.RIGHT;
import static game.GameLogic.isAlive;

public class MovementControl {

  private Snake snake;
  private GameLogic gameLogic;

  public MovementControl(GameLogic gameLogic) {
    this.gameLogic = gameLogic;
    this.snake = gameLogic.getSnake();
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

  public void turnUp() {
    if (snake.getDirection() != DOWN) {
      snake.setDirection(UP);
      snake.getHead()
          .setImage(SnakePiece.HEADUP);
    }
  }

  public void turnDown() {
    if (snake.getDirection() != UP) {
      snake.setDirection(DOWN);
      snake.getHead()
          .setImage(SnakePiece.HEADDOWN);
    }
  }

  public void turnLeft() {
    if (snake.getDirection() != RIGHT) {
      snake.setDirection(LEFT);

      snake.getHead()
          .setImage(SnakePiece.HEADLEFT);
    }
  }

  public void turnRight() {
    if (snake.getDirection() != LEFT) {
      snake.setDirection(RIGHT);

      snake.getHead()
          .setImage(SnakePiece.HEADRIGHT);
    }
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

  public void continueInDirection() {
    if (isAlive) {
      Direction direction = snake.getDirection();
      if (direction == RIGHT) moveRight();
      if (direction == LEFT) moveLeft();
      if (direction == UP) moveUp();
      if (direction == DOWN) moveDown();

      gameLogic.checkIfSnakeEatsApple();
      gameLogic.checkIfSnakeCollides();
      gameLogic.triggerTimedEvents();
    }
  }
}
