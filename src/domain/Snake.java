package domain;

import java.util.ArrayList;
import java.util.List;

public class Snake {
  private int originalX;
  private int originalY;
  private Direction direction;
  private List<SnakePiece> pieces;

  public Snake(int originalX, int originalY, Direction direction) {
    this.originalX = originalX;
    this.originalY = originalY;
    this.direction = direction;
    pieces = new ArrayList<>();
    pieces.add(new SnakePiece(3, 1));
    pieces.add(new SnakePiece(2, 1));
    pieces.add(new SnakePiece(1, 1));
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

  public List<SnakePiece> getPieces() {
    return pieces;
  }

  public void setPieces(List<SnakePiece> pieces) {
    this.pieces = pieces;
  }

  public void moveLeft() {
    pieces.get(0)
        .setX(pieces.get(0)
            .getX() - 1);
    for (int i = 1; i < pieces.size() - 1; i++) {
      pieces.get(i)
          .setX(pieces.get(i + 1)
              .getX() - 1);
    }
  }

  public void moveUp() {
    for (SnakePiece piece : pieces) {
      piece.setY(piece.getY() - 1);
    }
  }

  public void moveRight() {
    for (SnakePiece piece : pieces) {
      piece.setX(piece.getX() + 1);
    }
  }

  public void moveDown() {
    for (int i = 1; i < pieces.size()-1; i++) {
      pieces.get(i)
          .setX(pieces.get(i - 1)
              .getX());
      pieces.get(i).setY(pieces.get(i - 1)
          .getY());
    }
    pieces.get(0)
        .setY(pieces.get(0)
            .getY() + 1);
  }


}
