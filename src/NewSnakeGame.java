import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class NewSnakeGame extends Canvas implements Runnable {
  private int width = 1000;
  private int height = 700;
  private int SnakeX, SnakeY;
  private int AppleX, AppleY;

  private Thread thread;
  int fps = 30;
  boolean Running;

  private BufferStrategy bs;


  public NewSnakeGame() {
    JFrame frame = new JFrame("Snake Game");
    this.setSize(width, height);
    frame.add(this);
    frame.pack();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    Running = false;

    SnakeX = 300;
    SnakeY = 300;
    AppleX = 800;
    AppleY = 300;
  }

  public void draw() {
    bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();
    g.setColor(new Color(90, 153, 4));
    g.fillRect(0,0, width, height);

    drawSnake(g, SnakeX, SnakeY);
    drawApple(g, AppleX, AppleY);

    g.dispose();
    bs.show();
  }

  private void drawSnake(Graphics g, int x, int y) {
    g.setColor(new Color(88, 182, 7));
    g.fillRect(0+x, 0+y, 25, 25);
  }

  private void drawApple(Graphics g, int x, int y) {
    g.setColor(new Color(246, 32, 32));
    g.fillRect(0+x,5+y,15,15);
  }

  public void update() {
    SnakeX += 5;
  }

  public static void main(String [] args) {
    NewSnakeGame Snake = new NewSnakeGame();
    Snake.start();
  }

  public synchronized void start() {
    thread = new Thread(this, "GameThread");
    Running = true;
    thread.start();
  }




  @Override
  public void run() {
    while(Running) {
      sleep(30);
      update();
      draw();

    }
  }



  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
