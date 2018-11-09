package domain;

public class Fly extends Piece {

  private int age=0;

  public Fly(int x, int y) {
    super(x, y);
  }

  @Override
  public String getImage() {
    return "src/gui/fly.png";
  }

}
