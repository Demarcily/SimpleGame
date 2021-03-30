import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class NewSnakeGame extends Canvas implements Runnable {
  private int width = 1000;
  private int height = 700;
  private int SnakeX, SnakeY;
  private int SnakeVX, SnakeVY;
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
    this.addKeyListener(new KL());
    frame.setVisible(true);

    Running = false;

    SnakeX = 300;
    SnakeY = 300;
    AppleX = 800;
    AppleY = 300;
    SnakeVX = 0;
    SnakeVY = 0;
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
    SnakeX += SnakeVX;
    SnakeY += SnakeVY;
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

  private class KL implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'a') {
          SnakeVY = 0;
          SnakeVX = -5;
        }

        if (keyEvent.getKeyChar() == 'd') {
          SnakeVY = 0;
          SnakeVX = 5;
        }
        if (keyEvent.getKeyChar() == 'w') {
          SnakeVX = 0;
          SnakeVY = -5;
        }
        if (keyEvent.getKeyChar() == 's') {
          SnakeVX = 0;
          SnakeVY = +5;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
  }


}
