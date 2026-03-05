package DesignPattern;

import Sound.SampleSimpel;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class All {
  public static JFrame frame1;
  public static JLabel lampu1;

  public static JFrame frame2;
  public static JLabel lampu2;

  public static JFrame frame3;
  public static JLabel lampu3;

  public static JFrame frame4;
  public static JButton button1;

  public static FlatSVGIcon onIcon = new FlatSVGIcon("resource/on-vector.svg", 200, 200);
  public static FlatSVGIcon offIcon = new FlatSVGIcon("resource/off-vector.svg", 200, 200);

  public static SerialPort comPort;

  public static Clip clip;

  public static String message;

  public static void main(String[] args) throws Throwable {
    URL url = SampleSimpel.class.getResource("../resource/buzzer.wav");
    AudioInputStream stream = null;

    stream = AudioSystem.getAudioInputStream(url);
    clip = AudioSystem.getClip();
    clip.open(stream);

    comPort = SerialPort.getCommPort("COM8");
    comPort.setBaudRate(9600);

    if (comPort.openPort()) {
      System.out.println("Gerbang dibuka!");
      comPort.setDTR();
      comPort.clearRTS();
      Thread.sleep(100);
      comPort.clearDTR();
      comPort.setRTS();

      System.out.println("Mereset ESP32...");
      Thread.sleep(2000);

      comPort.flushIOBuffers();
    } else {
      System.out.println("Gagal membuka port. Pastikan kabel tercolok dan Serial Monitor OFF.");
      return;
    }

    comPort.addDataListener(new SerialPortMessageListener() {
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
        byte[] data = event.getReceivedData();
        message = new String(data).trim();
        System.out.println("Pesan masuk: " + message);

        switch (message) {
          case "1":
            SwingUtilities.invokeLater(() -> {
              lampu1.setIcon(onIcon);
              clip.setFramePosition(0);
              clip.start();
            });
            break;
          case "2":
            SwingUtilities.invokeLater(() -> {
              lampu2.setIcon(onIcon);
              clip.setFramePosition(0);
              clip.start();
            });
            break;
          case "3":
            SwingUtilities.invokeLater(() -> {
              lampu3.setIcon(onIcon);
              clip.setFramePosition(0);
              clip.start();
            });
            break;
          case "4":
            SwingUtilities.invokeLater(() -> {
              lampu3.setIcon(onIcon);
              clip.setFramePosition(0);
              clip.start();
            });
            break;
          case "5":
            SwingUtilities.invokeLater(() -> {
              lampu3.setIcon(onIcon);
              clip.setFramePosition(0);
              clip.start();
            });
            break;
        }
      }
    });

    frame1 = new JFrame();
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.setResizable(false);
    frame1.setLocation(0, 0);
    frame1.setUndecorated(true);
    frame1.setBackground(new Color(0, 0, 0, 0));

    frame2 = new JFrame();
    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame2.setResizable(false);
    frame2.setLocation(200, 0);
    frame2.setUndecorated(true);
    frame2.setBackground(new Color(0, 0, 0, 0));

    frame3 = new JFrame();
    frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame3.setResizable(false);
    frame3.setLocation(400, 0);
    frame3.setUndecorated(true);
    frame3.setBackground(new Color(0, 0, 0, 0));

    frame4 = new JFrame();
    frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame4.setResizable(false);
    frame4.setLocation(600, 0);

    lampu1 = new JLabel();
    lampu1.setBackground(new Color(0, 0, 0, 0));
    lampu1.setBounds(0, 0, 200, 200);
    lampu1.setIcon(offIcon);

    lampu2 = new JLabel();
    lampu2.setBackground(new Color(0, 0, 0, 0));
    lampu2.setBounds(0, 0, 200, 200);
    lampu2.setIcon(offIcon);

    lampu3 = new JLabel();
    lampu3.setBackground(new Color(0, 0, 0, 0));
    lampu3.setBounds(0, 0, 200, 200);
    lampu3.setIcon(offIcon);

    button1 = new JButton("RESET");
    button1.addActionListener(e -> {
      String perintah = "r\n";
      comPort.writeBytes(perintah.getBytes(), perintah.length());
      SwingUtilities.invokeLater(() -> {
        lampu1.setIcon(offIcon);
        lampu2.setIcon(offIcon);
        lampu3.setIcon(offIcon);
      });
    });

    frame1.add(lampu1);
    frame2.add(lampu2);
    frame3.add(lampu3);
    frame4.add(button1);
    frame1.pack();
    frame2.pack();
    frame3.pack();
    frame4.pack();
    frame1.setVisible(true);
    frame2.setVisible(true);
    frame3.setVisible(true);
    frame4.setVisible(true);
  }
}
