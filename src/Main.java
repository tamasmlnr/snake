import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import gui.Board;

import javax.swing.*;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Snake");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    frame.addKeyListener(board);
    ControllerManager controllers = new ControllerManager();
    controllers.initSDLGamepad();
    while(true) {
      ControllerState currState = controllers.getState(0);

      if(!currState.isConnected || currState.b) {
        break;
      }

      if(currState.dpadRight) {
        board.buttonPressed("right");
      }

      if(currState.dpadLeft) {
        board.buttonPressed("left");
      }

      if(currState.dpadUp) {
        board.buttonPressed("up");
      }

      if(currState.dpadDown) {
        board.buttonPressed("down");
      }

    }

  }


}
