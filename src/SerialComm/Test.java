package SerialComm;

public class Test {
  public static void main(String[] args) {
    new Port();

    while(true) {
      try { Thread.sleep(1000); } catch (Exception e) {}
    }
  }
}
