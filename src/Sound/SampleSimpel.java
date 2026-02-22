package Sound;

import javax.sound.sampled.*;
import java.net.URL;

public class SampleSimpel {
  public static void main(String[] args) throws Exception {
    // 1. Ambil file dari folder resources
    URL url = SampleSimpel.class.getResource("../resource/buzzer.wav");

    // 2. Buka stream audio
    AudioInputStream stream = AudioSystem.getAudioInputStream(url);

    // 3. Muat ke dalam Clip (RAM)
    Clip clip = AudioSystem.getClip();
    clip.open(stream);

    // 4. Mainkan
    clip.start();

    // Biarkan program tetap jalan sampai suara selesai (khusus aplikasi konsol)
    System.out.println(clip.getMicrosecondLength());
    Thread.sleep(1000);
    Thread.sleep(clip.getMicrosecondLength()/1000);
  }
}