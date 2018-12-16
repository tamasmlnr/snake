package domain;

public class Apple extends Piece {

  public Apple(Coordinate coordinate) {
    super(coordinate);
  }

  public Apple(int x, int y) {
    super(x, y);
    setImage("src/gui/apple.png");
  }

  @Override
  public String getImage() {
    return "src/gui/apple.png";
  }

}
