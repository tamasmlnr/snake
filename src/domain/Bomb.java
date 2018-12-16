package domain;

import game.SnakeGame;

public class Bomb extends Piece {
  private int age;
  private String imagePath;
  public Bomb(Coordinate coordinate) {
    super(coordinate);
    this.imagePath="src/gui/bomb.png";
  }
  @Override
  public String getImage() {
    return imagePath;
  }

  public void explode() {
    this.imagePath = "src/gui/explosion.png";
  }
}
