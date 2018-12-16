package domain;

public class Fly extends Piece {

  private int age = 0;

  public Fly(Coordinate coordinate) {
    super(coordinate);
  }

  @Override
  public String getImage() {
    return "src/gui/fly.png";
  }

}
