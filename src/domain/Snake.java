package domain;

import java.util.ArrayList;
import java.util.List;

public class Snake {
  private int originalX;
  private int originalY;
  private Direction direction;
  private Direction originalDirection;
  private List<SnakePiece> pieces;

  public Snake(int originalX, int originalY, Direction direction) {
    this.originalX = originalX;
    this.originalY = originalY;
    this.direction = direction;
    pieces = new ArrayList<>();
    pieces.add(new SnakePiece(4, 1));
    pieces.add(new SnakePiece(3, 1));
    pieces.add(new SnakePiece(2, 1));
    pieces.add(new SnakePiece(1, 1));
  }

  public int getOriginalX() {
    return originalX;
  }

  public Direction getOriginalDirection() {
    return originalDirection;
  }

  public void setOriginalDirection(Direction originalDirection) {
    this.originalDirection = originalDirection;
  }

  public void setOriginalX(int originalX) {
    this.originalX = originalX;
  }

  public int getOriginalY() {
    return originalY;
  }

  public void setOriginalY(int originalY) {
    this.originalY = originalY;
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

  public void setPieces(List<SnakePiece> pieces) {
    this.pieces = pieces;
  }

}
