package SerialComm;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.Scanner;

public class SimpleSerial {
  public static void main(String[] args) throws IOException {
    // 1. Inisialisasi Port (Ganti "COM3" sesuai port Arduino Anda)
    SerialPort port = SerialPort.getCommPort("COM3");
    port.setBaudRate(9600);

    if (port.openPort()) {
      System.out.println("Port Berhasil Dibuka!");
    } else {
      System.out.println("Gagal Membuka Port.");
      return;
    }

    // 2. Kirim perintah '1' ke Arduino
    port.getOutputStream().write('1');
    port.getOutputStream().flush();

    // 3. Baca balasan dari Arduino
    Scanner pambaca = new Scanner(port.getInputStream());
    if (pambaca.hasNextLine()) {
      System.out.println("Respon Arduino: " + pambaca.nextLine());
    }

    port.closePort(); // Tutup koneksi
  }
}