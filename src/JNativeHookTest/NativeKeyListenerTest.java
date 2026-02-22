package JNativeHookTest;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import javax.swing.*;
import java.awt.*;

public class NativeKeyListenerTest extends JFrame implements NativeKeyListener {

  JFrame frame1;
  JFrame frame2;
  JLabel label1;
  JLabel label2;
  ImageIcon off = new ImageIcon("src/resource/Off.png");
  ImageIcon on = new ImageIcon("src/resource/On.png");

  NativeKeyListenerTest() {
    try {
      // Registrasi Hook
      GlobalScreen.registerNativeHook();
    } catch (NativeHookException ex) {
      System.err.println("Gagal meregistrasi hook.");
      System.exit(1);
    }

    // Tambahkan listener ke class ini
    GlobalScreen.addNativeKeyListener(this);

    frame1 = new JFrame("Lampu1");
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.setSize(200, 200);
    frame1.setResizable(false);
    frame1.setLayout(new BorderLayout());

    frame2 = new JFrame("Lampu2");
    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame2.setSize(200, 200);
    frame2.setResizable(false);
    frame2.setLayout(new BorderLayout());

    label1 = new JLabel();
    label1.setIcon(off);
    frame1.add(label1, BorderLayout.CENTER);

    label2 = new JLabel();
    label2.setIcon(off);
    frame2.add(label2, BorderLayout.CENTER);

    frame1.setVisible(true);
    frame2.setVisible(true);
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent e) {
    System.out.println("Tombol Ditekan: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    System.out.println("Kodenya: " + e.getKeyCode());
//    label1.setText(String.valueOf(e.getKeyCode()));
//    label2.setText(String.valueOf(e.getKeyCode()));

    switch (e.getKeyCode()) {
      case 2:
        label1.setIcon(on);
        break;
      case 3:
        label2.setIcon(on);
        break;
      case 11:
        label1.setIcon(off);
        label2.setIcon(off);
        break;
    }

//    // Contoh: Jika tekan ESC, aplikasi berhenti
//    if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
//      try {
//        GlobalScreen.unregisterNativeHook();
//      } catch (NativeHookException e1) {
//        e1.printStackTrace();
//      }
//    }
  }
}