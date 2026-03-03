package DesignPattern.LampuApplication;

import DesignPattern.FrameLampu.FrameLampu;
import DesignPattern.SerialComm.SerialComm;
import Sound.SampleSimpel;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class LampuApplication {
  public static SerialPort comPort;
  public static String message = "";

  public static Clip clip;

  public static void main(String[] args) {
    FrameLampu frame1 = new FrameLampu(0, 0);
    FrameLampu frame2 = new FrameLampu(200, 0);
    JFrame frame3 = new JFrame();
    JButton btnOn = new JButton("Nyalakan LED");
    btnOn.addActionListener(e -> {
      String perintah = "r";
      comPort.writeBytes(perintah.getBytes(), perintah.length());
    });

    URL url = SampleSimpel.class.getResource("../resource/buzzer.wav");
    AudioInputStream stream = null;

    try {
      stream = AudioSystem.getAudioInputStream(url);
      clip = AudioSystem.getClip();
      clip.open(stream);
    } catch (
      UnsupportedAudioFileException e) {
      throw new RuntimeException(e);
    } catch (
      IOException e) {
      throw new RuntimeException(e);
    } catch (
      LineUnavailableException e) {
      throw new RuntimeException(e);
    }

    comPort = SerialPort.getCommPort("COM8");
    comPort.setBaudRate(9600);

    if (comPort.openPort()) {
      System.out.println("Gerbang dibuka!");
      try {
        comPort.setDTR();
        comPort.clearRTS();
        Thread.sleep(100);
        comPort.clearDTR();
        comPort.setRTS();

        System.out.println("Mereset ESP32...");
        Thread.sleep(2000);

        comPort.flushIOBuffers();
      } catch (Exception e) {
        e.printStackTrace();
      }

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
              frame1.lampu.on();
              clip.setFramePosition(0);
              clip.start();
            });
            break;
          case "2":
            SwingUtilities.invokeLater(() -> {
              frame2.lampu.on();
              clip.setFramePosition(0);
              clip.start();
            });
            break;
            case "5":
            SwingUtilities.invokeLater(() -> {
              frame1.lampu.off();
              frame2.lampu.off();
            });
            break;
        }
      }
    });

    frame3.setVisible(true);
  }
}
