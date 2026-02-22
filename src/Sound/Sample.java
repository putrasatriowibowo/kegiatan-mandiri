package Sound;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.Scanner;

public class Sample {

  private Clip clip;

  public Sample() {
    loadSound("../resource/buzzer.wav"); // Pastikan file ada di src/main/resources/click.wav
  }

  private void loadSound(String path) {
    try {
      // Gunakan URL agar aman dan stabil
      URL url = getClass().getResource(path);
      if (url == null) {
        System.err.println("Error: File " + path + " tidak ditemukan di resources!");
        return;
      }

      AudioInputStream ais = AudioSystem.getAudioInputStream(url);
      clip = AudioSystem.getClip();
      clip.open(ais);

      System.out.println("Suara berhasil dimuat!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void play() {
    if (clip != null) {
      // KUNCI LOW LATENCY:
      clip.setFramePosition(0); // Kembalikan 'jarum' ke awal secara instan
      clip.start();             // Mainkan suara
    }
  }

  public static void main(String[] args) {
    Sample player = new Sample();

    // Simulasi trigger suara lewat konsol
    Scanner scanner = new Scanner(System.in);
    System.out.println("Tekan ENTER untuk bunyi (Ketik 'exit' untuk berhenti):");

    while (true) {
      String input = scanner.nextLine();
      if (input.equalsIgnoreCase("exit")) break;

      player.play();
      System.out.println("Suara dimainkan!");
    }

    scanner.close();
    System.exit(0);
  }
}