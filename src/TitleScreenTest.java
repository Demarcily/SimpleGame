import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class TitleScreenTest extends Canvas implements Runnable {
  int width = 1000;
  int height = 700;
  int fps = 5;

  boolean GameStarted = true;
  public Thread thread;

  private BufferStrategy bs;

  public TitleScreenTest() {
    JFrame frame = new JFrame();
    this.setSize(width, height);
    this.setBackground(Color.BLACK);
    frame.add(this);
    frame.pack();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }

  public void draw() {
    bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.GREEN);
    g.fillRect(0,0, width, height);
    g.setColor(Color.lightGray);
    g.fillRect(500-(width/4), 350-(width/4), width/2, height/2);
    g.dispose();
    bs.show();
  }



  public static void main (String [] args) {
    TitleScreenTest test = new TitleScreenTest();
    test.Start();
  }



  public synchronized void Start() {
    thread = new Thread(this, "ThreadGame");
    if (GameStarted) {
      thread.start();
    }

  }

  public synchronized void Stop() {

  }


  @Override
  public void run() {
    double deltaT = 1000.0/fps;
    long lastTime = System.currentTimeMillis();
    while (GameStarted) {
      long now = System.currentTimeMillis();
      if (now-lastTime >= deltaT) {
        draw();
        lastTime = now;
      }
    }
  }

}
