package domain;

public class SnakePiece extends Piece {

  public static final String HEADLEFT = "src/gui/head_left.png";
  public static final String HEADRIGHT = "src/gui/head_right.png";
  public static final String HEADUP = "src/gui/head_up.png";
  public static final String HEADDOWN = "src/gui/head_down.png";
  public static final String BODY = "src/gui/snake_tile.png";

  public SnakePiece(Coordinate coordinate) {
    super(coordinate);
    setImage(BODY);
  }
}
