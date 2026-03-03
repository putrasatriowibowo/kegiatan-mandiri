package SerialComm;

import com.fazecast.jSerialComm.SerialPort;
import java.util.Scanner;

public class TerimaSederhana {
  public static void main(String[] args) {
    // 1. Pilih Port (Ganti sesuai port ESP32-mu)
    SerialPort port = SerialPort.getCommPort("COM8");
    port.setBaudRate(9600);

    if (port.openPort()) {
      System.out.println("Siap menerima data dari ESP32...");
    } else {
      System.out.println("Gagal buka port!");
      return;
    }

    // 2. Thread Khusus untuk Mendengarkan (Background Task)
    new Thread(() -> {
      Scanner s = new Scanner(port.getInputStream());
      while (s.hasNextLine()) {
        String data = s.nextLine();
        System.out.println("Diterima: " + data);
      }
    }).start();
  }
}