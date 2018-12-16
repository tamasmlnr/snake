package game;

import domain.Snake;
import domain.SnakePiece;

import static domain.Direction.*;
import static domain.Direction.LEFT;
import static domain.Direction.RIGHT;

public class MovementControl {

  private Snake snake;

  public MovementControl(Snake snake) {
  this.snake=snake;
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

}
