package SerialComm;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.Scanner;

public class SimpleSerial {
  public static void main(String[] args) throws IOException {
    SerialPort port = SerialPort.getCommPort("COM8");
    port.setBaudRate(9600);

    if (port.openPort()) {
      System.out.println("Port Berhasil Dibuka!");
    } else {
      System.out.println("Gagal Membuka Port.");
      return;
    }
//      port.getOutputStream().write('r');
//      port.getOutputStream().flush();

    while (true) {
      Scanner pambaca = new Scanner(port.getInputStream());
      if (pambaca.hasNextLine()) {
        System.out.println("Respon Arduino: " + pambaca.nextLine());
      }
//      port.closePort();
    }
  }
}