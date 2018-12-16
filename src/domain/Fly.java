package domain;

public class Fly extends Piece {

  public Fly(Coordinate coordinate) {
    super(coordinate);
  }

  public Fly(int x, int y) {
    super(x, y);
    setImage("src/gui/fly.png");
  }

  @Override
  public String getImage() {
    return "src/gui/fly.png";
  }

}
