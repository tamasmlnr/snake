package domain;

public class Bomb extends Piece {

  private String imagePath;

  public Bomb(Coordinate coordinate) {
    super(coordinate);
    this.imagePath = "src/gui/bomb.png";
  }

  public Bomb(int x, int y) {
    super(x, y);
    setImage("src/gui/bomb.png");
  }

  @Override
  public String getImage() {
    return imagePath;
  }

  public void explode() {
    this.imagePath = "src/gui/explosion.png";
  }
}
