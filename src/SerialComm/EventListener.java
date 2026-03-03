package SerialComm;

import com.fazecast.jSerialComm.*;


public class EventListener {
  public static SerialPort comPort;

  public static void main(String[] args) {
    init();
    open();
    addListener();

    System.out.println("Aplikasi siap mendengarkan...");

  }

  public static void init(){
    comPort = SerialPort.getCommPort("COM8");
    comPort.setBaudRate(9600);
    //    comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 1000, 1000);
  }

  public static void open(){
    if (comPort.openPort()) {
      System.out.println("Gerbang dibuka!");
      try {
        reset();
      } catch (Exception e) { e.printStackTrace(); }

    } else {
      System.out.println("Gagal membuka port. Pastikan kabel tercolok dan Serial Monitor OFF.");
      return;
    }
  }

  public static void reset() throws InterruptedException {
    comPort.setDTR();
    comPort.clearRTS();
    Thread.sleep(100);
    comPort.clearDTR();
    comPort.setRTS();

    System.out.println("Mereset ESP32...");
    Thread.sleep(2000);

    comPort.flushIOBuffers();
  }

  public static void addListener(){
    comPort.addDataListener(new SerialPortMessageListener() {
      @Override
      public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
      @Override
      public boolean delimiterIndicatesEndOfMessage() { return true; }
      @Override
      public byte[] getMessageDelimiter() { return new byte[] { (byte) 0x0A }; }

      @Override
      public void serialEvent(SerialPortEvent event) {
        byte[] data = event.getReceivedData();
        String pesan = new String(data).trim();
        if (!pesan.isEmpty()) {
          System.out.println("Pesan masuk: " + pesan);
        }
      }
    });
  }
}