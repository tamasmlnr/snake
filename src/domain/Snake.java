package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.Direction.RIGHT;

public class Snake {
  private Direction direction;
  private Direction originalDirection;
  private List<SnakePiece> pieces;

  public Snake() {
    this.direction = RIGHT;
    pieces = new ArrayList<>();
    pieces.add(new SnakePiece(new Coordinate(4, 1)));
    pieces.add(new SnakePiece(new Coordinate(3, 1)));
    pieces.add(new SnakePiece(new Coordinate(2, 1)));
    pieces.add(new SnakePiece(new Coordinate(1, 1)));
  }

  public Direction getOriginalDirection() {
    return originalDirection;
  }

  public void setOriginalDirection(Direction originalDirection) {
    this.originalDirection = originalDirection;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public List<SnakePiece> getPieces() {
    return pieces;
  }

  public SnakePiece getHead() {
    return pieces.get(0);
  }

  public SnakePiece getLastPiece() {
    return pieces.get(pieces.size() - 1);
  }

}
