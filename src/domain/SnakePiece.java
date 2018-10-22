package domain;

public class SnakePiece extends Piece {

  public static final String HEADLEFT="src/gui/head_left.png";
  public static final String HEADRIGHT="src/gui/head_right.png";
  public static final String HEADUP="src/gui/head_up.png";
  public static final String HEADDOWN="src/gui/head_down.png";
  public static final String BODYHORIZONTAL="src/gui/body_horizontal.png";
  public static final String BODYVERTICAL="src/gui/body_vertical.png";
  public SnakePiece(int x, int y) {
    super(x, y);
    setImage("src/gui/snake_tile.png");
  }
}
