package domain;

public class Fly extends Piece {

  public Fly(Coordinate coordinate) {
    super(coordinate);
  }

  @Override
  public String getImage() {
    return "src/gui/fly.png";
  }

}
