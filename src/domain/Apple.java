package domain;

public class Apple extends Piece {

  public Apple(Coordinate coordinate) {
    super(coordinate);
  }

  @Override
  public String getImage() {
    return "src/gui/apple.png";
  }

}
