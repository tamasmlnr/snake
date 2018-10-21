package domain;

import game.SnakeGame;
import gui.PositionedImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Apple extends Piece {

  private int x;
  private int y;
  private String image;

  public Apple(int x, int y) {
    super(x, y);
  }

  @Override
  public String getImage() {
    return "src/gui/apple.png";
  }

}
