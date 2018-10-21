package domain;

public class Piece {

  private int x;
  private int y;
  private String image;

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
    if (piece.getX() == this.x && piece.getY() == this.y) {
      return true;
    }
    return false;
  }

  public String toString() {
    return "(" + x + "," + y + ")";
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
