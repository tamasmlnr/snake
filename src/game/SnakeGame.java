package game;

import domain.Apple;
import domain.Piece;
import domain.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static domain.Direction.UP;

public class SnakeGame {
  private Snake snake;
  private List<Piece> gameObjects;

  public SnakeGame() {
  }

  public List<Piece> getGameObjects() {
    List<Piece> pieces = new ArrayList<>();
    pieces.add(new Apple(5, 5));
    return pieces;
  }

  public static int generateRandomCoordinate() {
    return ThreadLocalRandom.current()
        .nextInt(1, 10 + 1);
  }


}
