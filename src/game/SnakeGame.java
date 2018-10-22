package game;

import domain.*;
import gui.Board;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static domain.Direction.*;

public class SnakeGame {
  public static final int MAX_WIDTH = 15;
  public static final int MAX_HEIGHT = 15;
  private Snake snake;
  private List<Piece> gameObjects;
  private Apple apple;
  public Boolean isAlive;

  public SnakeGame() {
    isAlive = true;
    snake = new Snake();
    apple = new Apple(generateRandomCoordinate(), generateRandomCoordinate());
  }

  public List<Piece> getGameObjects() {
    List<Piece> pieces = new ArrayList<>();
    pieces.add(apple);
    for (SnakePiece piece : snake.getPieces()) {
      pieces.add(piece);
    }
    gameObjects = pieces;
    return pieces;
  }

  public static int generateRandomCoordinate() {
    return ThreadLocalRandom.current()
        .nextInt(2, 10 + 1);
  }


  public void turnUp() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection() != DOWN)
      snake.setDirection(UP);
//    snake.getHead().setImage(SnakePiece.HEADUP);
  }

  public void turnDown() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection() != UP)
      snake.setDirection(DOWN);
//    snake.getHead().setImage(SnakePiece.HEADDOWN);
  }

  public void turnLeft() {
    snake.setOriginalDirection(snake.getDirection());
    if (snake.getDirection() != RIGHT)
      snake.setDirection(LEFT);

//    snake.getHead().setImage(SnakePiece.HEADLEFT);
  }

  public void turnRight() {
    snake.setOriginalDirection(snake.getDirection());
    snake.setDirection(RIGHT);

//    snake.getHead().setImage(SnakePiece.HEADRIGHT);
  }

  public void continueInDirection() {
    if (isAlive) {
      Direction direction = snake.getDirection();
      if (direction == RIGHT) moveRight();
      if (direction == LEFT) moveLeft();
      if (direction == UP) moveUp();
      if (direction == DOWN) moveDown();

      checkIfSnakeEatsApple();
      checkIfSnakeCollides();
    }
  }

  private void checkIfSnakeCollides() {
    if (snake.getHead()
        .getX() > MAX_HEIGHT ||
        snake.getHead()
            .getX() < 1 ||
        snake.getHead()
            .getY() < 1 ||
        snake.getHead()
            .getY() > MAX_WIDTH) {
      gameOver();
    }
    for (int i = 1; i < snake.getPieces()
        .size(); i++) {
      if (snake.getHead()
          .runsInto(snake.getPieces()
              .get(i))) {
        gameOver();
      }
    }
  }

  private void gameOver() {
    File file = new File("src/assets/aww.wav");
    play(file);
    isAlive = false;
  }

  private void checkIfSnakeEatsApple() {
    if (getApple().runsInto(snake.getHead())) {
      grow();
      for(SnakePiece piece: snake.getPieces()){
        while (apple.runsInto(piece)){
          apple.setX(generateRandomCoordinate());
          apple.setY(generateRandomCoordinate());
          break;
        }
      }
      if (Board.delay > 81) {
        Board.delay -= 30;
      }
    }
  }


  private void grow() {
    if (snake.getDirection() == LEFT) {
      snake.getPieces()
          .add(new SnakePiece(snake.getLastPiece()
              .getX() + 1, snake.getLastPiece()
              .getY()));
    }

    if (snake.getDirection() == RIGHT) {
      snake.getPieces()
          .add(new SnakePiece(snake.getLastPiece()
              .getX() - 1, snake.getLastPiece()
              .getY()));
    }

    if (snake.getDirection() == UP) {
      snake.getPieces()
          .add(new SnakePiece(snake.getLastPiece()
              .getX(), snake.getLastPiece()
              .getY() + 1));
    }

    if (snake.getDirection() == DOWN) {
      snake.getPieces()
          .add(new SnakePiece(snake.getLastPiece()
              .getX(), snake.getLastPiece()
              .getY() - 1));
    }
  }

  public void moveLeft() {
    shiftPieces();
    snake.getPieces()
        .get(0)
        .setX(snake.getPieces()
            .get(0)
            .getX() - 1);
  }

  public void moveUp() {
    shiftPieces();
    snake.getPieces()
        .get(0)
        .setY(snake.getPieces()
            .get(0)
            .getY() - 1);
  }

  public void moveRight() {
    shiftPieces();
    snake.getPieces()
        .get(0)
        .setX(snake.getPieces()
            .get(0)
            .getX() + 1);
  }


  public void moveDown() {
    shiftPieces();
    snake.getPieces()
        .get(0)
        .setY(snake.getPieces()
            .get(0)
            .getY() + 1);
  }

  public void shiftPieces() {
    for (int i = snake.getPieces()
        .size() - 1; i > 0; i--) {
      int tempX=snake.getPieces().get(i).getX();
      int tempY=snake.getPieces().get(i).getY();
      snake.getPieces()
          .get(i)
          .setX(snake.getPieces()
              .get(i - 1)
              .getX());
      snake.getPieces()
          .get(i)
          .setY(snake.getPieces()
              .get(i - 1)
              .getY());
//    if (snake.getPieces().get(i).getX()!=tempX){
//      snake.getPieces().get(i).setImage(SnakePiece.BODYHORIZONTAL);
//    }
//    if (snake.getPieces().get(i).getY()!=tempY){
//        snake.getPieces().get(i).setImage(SnakePiece.BODYVERTICAL);
//      }changeCorners();
      }

  }

//  private void changeCorners() {
//    System.out.println("head" +snake.getPieces().get(0).getY()+" "+snake.getPieces().get(0).getX());
//    System.out.println("first" +snake.getPieces().get(1).getY()+" "+snake.getPieces().get(1).getX());
//    System.out.println("second" +snake.getPieces().get(2).getY()+" "+snake.getPieces().get(2).getX());
//    System.out.println("____");
//    if (snake.getHead().getY()>snake.getPieces().get(1).getY()&&
//        snake.getPieces().get(2).getX()>snake.getPieces().get(1).getX()
//    ){snake.getPieces().get(1).setImage(SnakePiece.UPLEFT);
//    }
//
//
//    for (int i=2;i<snake.getPieces().size()-1;i++){
//    if (snake.getPieces().get(i).getY()<snake.getPieces().get(i-1).getY()&&
//        snake.getPieces().get(i).getX()>snake.getPieces().get(i+1).getX()
//    ){
//        snake.getPieces().get(i).setImage(SnakePiece.UPLEFT);
//    }
//
//      else if (snake.getPieces().get(i).getY()>snake.getPieces().get(i-1).getY()&&
//          snake.getPieces().get(i).getX()<snake.getPieces().get(i+1).getX()
//      ){
//        snake.getPieces().get(i).setImage(SnakePiece.DOWNRIGHT);
//      }
//
//      else if (snake.getPieces().get(i).getY()<snake.getPieces().get(i-1).getY()&&
//          snake.getPieces().get(i).getX()<snake.getPieces().get(i+1).getX()
//      ){
//        snake.getPieces().get(i).setImage(SnakePiece.UPRIGHT);
//      }
//
//      else if (snake.getPieces().get(i).getY()>snake.getPieces().get(i-1).getY()&&
//          snake.getPieces().get(i).getX()>snake.getPieces().get(i+1).getX()
//      ){
//        snake.getPieces().get(i).setImage(SnakePiece.DOWNLEFT);
//      }
//  }}

  public Apple getApple() {
    return (Apple) gameObjects.get(0);
  }


  public void play(File file) {
    try {
      final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

      clip.addLineListener(new LineListener() {
        @Override
        public void update(LineEvent event) {
          if (event.getType() == LineEvent.Type.STOP)
            clip.close();
        }
      });

      clip.open(AudioSystem.getAudioInputStream(file));
      clip.start();
    } catch (Exception exc) {
      exc.printStackTrace(System.out);
    }
  }

}
