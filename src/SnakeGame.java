import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class SnakeGame extends Canvas implements Runnable {
  private int width = 1000;
  private int height = 700;
  private int AppleX, AppleY;

  private Thread thread;
  int fps = 5;
  private boolean isRunning = false;

  private BufferStrategy bs;

  public SnakeGame() {
    JFrame frame = new JFrame("Snake Game");
    this.setSize(width, height);
    frame.add(this);
    frame.pack();


    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    AppleX = 500;
    AppleY = 350;

  }

  public void update() {
    AppleX += 12.5;
  }

  public void draw() {
    bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0,0, width, height);
    update();
    DrawApple(g, AppleX, AppleY);
    g.dispose();
    bs.show();




  }

  public void DrawApple(Graphics g, int x, int y) {
    g.setColor(new Color(165, 42, 42 ));
    g.fillRect(0+x,0+y, 25, 25);
  }


  public static void main(String [] args) {
  SnakeGame Snake = new SnakeGame();
  Snake.start();
  }

  public synchronized void start() {
    thread = new Thread(this, "Thread1");
    isRunning = true;
    thread.start();
  }

  public synchronized void stop() {
    isRunning = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }






  @Override
  public void run() {
    double deltaT = 1000.0/fps;
    long lastTime = System.currentTimeMillis();
    while (isRunning) {
      long now = System.currentTimeMillis();
      if (now-lastTime >= deltaT) {
        update();
        draw();
        lastTime = now;
      }

    }
    stop();
  }
}
