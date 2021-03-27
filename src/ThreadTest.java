public class ThreadTest {

  public static void main(String[] args) {
    Runnable runnable = () -> {
      while(true) {
        sleep(1000);
        System.out.println("Running");
      }
    };

    Thread thread = new Thread(runnable);
    thread.setDaemon(true);
    thread.start();
    System.out.println("Second thread starts now.");
    sleep(3100);
    System.out.println("Ending both threads soon.");
    sleep(400);
    System.out.println("Threads ended.");

  }

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }



}
