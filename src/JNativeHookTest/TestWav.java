package JNativeHookTest;

import javax.sound.sampled.*;
import java.io.File;

public class TestWav {
  public static void main(String[] args) {
    try {
      File file = new File("src/resource/buzzer.wav"); // Pastikan file ada di folder proyek
      AudioInputStream ais = AudioSystem.getAudioInputStream(file);

      // Cek format di konsol untuk memastikan
      System.out.println("Format: " + ais.getFormat().toString());

      Clip clip = AudioSystem.getClip();
      clip.open(ais);
      clip.start();

      System.out.println("Memutar...");

      // WAJIB: Tahan program selama file diputar (misal 10 detik)
      // Tanpa ini, program selesai dan suara mati seketika
      Thread.sleep(clip.getMicrosecondLength() / 1000);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}