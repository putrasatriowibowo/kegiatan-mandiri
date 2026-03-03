package Belajar.JSerrialComm;

import com.fazecast.jSerialComm.SerialPort;
import java.util.Scanner;

public class Ringkas {
  public static void main(String[] args) {
    SerialPort port = SerialPort.getCommPort("COM8"); // Sesuaikan port
    port.setBaudRate(9600);
    port.openPort();

    // Thread Membaca (Dari ESP32 ke Konsol Java)
    new Thread(() -> {
      Scanner s = new Scanner(port.getInputStream());
      while (s.hasNextLine()) System.out.println("ESP32: " + s.nextLine());
    }).start();

    // Thread Menulis (Dari Keyboard Java ke ESP32)
    Scanner input = new Scanner(System.in);
    System.out.println("Ketik 1 (ON) atau 0 (OFF):");
    while (true) {
      String out = input.next();
      port.writeBytes(out.getBytes(), 1);
    }
  }
}