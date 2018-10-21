package domain;

public class Piece {

  private int x;
  private int y;

  public Piece(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean runsInto(Piece piece) {
    if (piece.getX() == this.getX() && piece.getY() == this.getY()) {
      return true;
    }
    return false;
  }

  public String toString() {
    return "(" + x + "," + y + ")";
  }

}
