package domain;

public class Piece {

  private Coordinate coordinate;
  private String image;

  public Piece(Coordinate coordinate) {
    this.coordinate=coordinate;
  }

  public int getX() {
    return coordinate.getX();
  }

  public void setX(int x) {
    coordinate.setX(x);
  }

  public int getY() {
    return coordinate.getY();
  }

  public void setY(int y) {
    coordinate.setY(y);
  }

  public boolean runsInto(Piece piece) {
    if (piece.getX() == this.getX() && piece.getY() == this.getY()) {
      return true;
    }
    return false;
  }

  public String toString() {
    return "(" + getX() + "," + getY() + ")";
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
