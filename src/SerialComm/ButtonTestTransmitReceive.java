package SerialComm;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTestTransmitReceive {
  public static JFrame frame;
  public static JLabel label;
  public static JButton button;
  public static SerialPort port;

  public static void main(String[] args) {

    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setLayout(null);


    label = new JLabel();
    label.setText("Data");
    label.setBounds(0, 0, 100, 100);

    button = new JButton();
    button.setBounds(100,0,100,100);
    button.setText("RESET");
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String command = "r\n"; // Kirim sinyal ke ESP32
        port.writeBytes(command.getBytes(), command.length());
      }
    });

    port = SerialPort.getCommPort("COM8");
    port.setBaudRate(9600);

    if (port.openPort()) {
      System.out.println("Port Terbuka");
    } else {
      System.err.println("Gagal membuka port");
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
        SwingUtilities.invokeLater(() -> {
          label.setText("Data: " + pesan);
        });
      }
    });

    frame.add(label);
    frame.add(button);
    frame.setVisible(true);
  }
}
