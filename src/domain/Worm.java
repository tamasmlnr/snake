package domain;

import java.util.List;

public class Worm {
  private int originalX;
  private int originalY;
  private Direction direction;
  private List<Piece> pieces;

  public Worm(int originalX, int originalY, Direction direction) {
    this.originalX = originalX;
    this.originalY = originalY;
    this.direction = direction;
  }

  public int getOriginalX() {
    return originalX;
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

  public List<Piece> getPieces() {
    return pieces;
  }

  public void setPieces(List<Piece> pieces) {
    this.pieces = pieces;
  }
}
