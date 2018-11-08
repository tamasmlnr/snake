package domain;

public class Bomb extends Piece {
  private int age;
  public Bomb(int x, int y) {
    super(x, y);
  }
  @Override
  public String getImage() {
    return "src/gui/bomb.png";
  }

}
