package domain;

public class Apple extends Piece {

  private Coordinate coordinate;
  private String image;

  public Apple(Coordinate coordinate) {
    super(coordinate);
  }

  @Override
  public String getImage() {
    return "src/gui/apple.png";
  }

}
