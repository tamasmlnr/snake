package domain;

public class SnakePiece extends Piece {
  public SnakePiece(int x, int y) {
    super(x, y);
  }

  @Override
  public String getImage() {
    return "src/gui/snake_tile.png";
  }

}
