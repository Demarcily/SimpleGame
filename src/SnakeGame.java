import javax.swing.*;
import java.awt.*;

public class SnakeGame extends Canvas implements Runnable {

  public SnakeGame() {
    JFrame frame = new JFrame("Snake Game");
    this.setSize(1000,700);
    frame.add(this);
    frame.pack();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }


  public static void main(String [] args) {
    new SnakeGame();
  }






  @Override
  public void run() {

  }
}
