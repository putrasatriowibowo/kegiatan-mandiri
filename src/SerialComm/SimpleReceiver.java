package SerialComm;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class SimpleReceiver {
  public static void main(String[] args) {
    SerialPort port = SerialPort.getCommPort("COM8");
    port.setBaudRate(9600);

    if (port.openPort()) {
      System.out.println("Port Terbuka. Java sekarang SIAGA menerima data...");
    } else {
      System.err.println("Gagal membuka port.");
      return;
    }

    port.addDataListener(new SerialPortDataListener() {
      @Override
      public int getListeningEvents() {
        // Beritahu Java untuk bereaksi hanya saat "Data Tersedia"
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
      }

      @Override
      public void serialEvent(SerialPortEvent event) {
        // Jika ada data masuk...
        byte[] buffer = new byte[event.getSerialPort().bytesAvailable()];
        int bytesRead = event.getSerialPort().readBytes(buffer, buffer.length);

        String pesan = new String(buffer, 0, bytesRead);
        System.out.print(pesan);
      }
    });

    // 4. Biarkan program tetap hidup (Main thread tidak boleh mati)
    // Tanpa loop kosong ini, program Java akan langsung selesai/exit.
    while (true) {
      try { Thread.sleep(100); } catch (InterruptedException e) {}
    }
  }
}