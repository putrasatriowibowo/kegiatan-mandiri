package JNativeHookTest;

import Sound.SampleSimpel;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class LampuKeyListener implements NativeKeyListener {
  Lampu lampu1 = new Lampu("Lampu1", 0, 0);
  Lampu lampu2 = new Lampu("Lampu2", 200, 0);

  Clip clip;

  LampuKeyListener() {
    //SOUND==================================================================
    URL url = SampleSimpel.class.getResource("../resource/buzzer.wav");
    AudioInputStream stream = null;
    try {
      stream = AudioSystem.getAudioInputStream(url);
      clip = AudioSystem.getClip();
      clip.open(stream);
    } catch (UnsupportedAudioFileException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (LineUnavailableException e) {
      throw new RuntimeException(e);
    }

    //JNativeHook==================================================================
    try {
      GlobalScreen.registerNativeHook();
    } catch (
      NativeHookException ex) {
      System.err.println("Gagal meregistrasi hook.");
      System.exit(1);
    }
    GlobalScreen.addNativeKeyListener(this);
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent e) {
    System.out.println(e.getKeyCode());
    switch (e.getKeyCode()) {
      case 2:
        lampu1.on();
        clip.setFramePosition(0);
        clip.start();
        break;
      case 3:
        lampu2.on();
        clip.setFramePosition(0);
        clip.start();
        break;
      case 11:
        lampu1.off();
        lampu2.off();
        break;
    }
  }
}
