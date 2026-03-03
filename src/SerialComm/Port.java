package SerialComm;

import com.fazecast.jSerialComm.*;

public class Port {
  SerialPort comPort = SerialPort.getCommPort("COM8");

  Port() {
    openPort();
    addListener();

    System.out.println("Aplikasi sedang berjalan...");
  }

  public void openPort(){
    if (comPort.openPort()) {
      System.out.println("Gerbang dibuka! Silakan mulai mengobrol.");
    } else {
      System.out.println("Gagal! Mungkin port sedang dipakai aplikasi lain (seperti Serial Monitor Arduino IDE).");
    }
  }

  public void addListener(){
    comPort.addDataListener (new SerialPortMessageListener() {
      @Override
      public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
      }

      @Override
      public boolean delimiterIndicatesEndOfMessage() {
        return true;
      }

      @Override
      public byte[] getMessageDelimiter() {
        return new byte[]{(byte) 0x0A};
      }

      @Override
      public void serialEvent(SerialPortEvent event) {
        byte[] deliveredMessage = event.getReceivedData();
        System.out.println("Pesan: " + new String(deliveredMessage).trim());
      }
    });
  }
}