package JNativeHookTest;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class GlobalInputExample implements NativeKeyListener {
  public static void main(String[] args) {
    try {
      // Registrasi Hook
      GlobalScreen.registerNativeHook();
    } catch (NativeHookException ex) {
      System.err.println("Gagal meregistrasi hook.");
      System.exit(1);
    }

    // Tambahkan listener ke class ini
    GlobalScreen.addNativeKeyListener(new GlobalInputExample());
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent e) {
    System.out.println("Tombol Ditekan: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    System.out.println("Kodenya: " + e.getKeyCode());

    // Contoh: Jika tekan ESC, aplikasi berhenti
    if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
      try {
        GlobalScreen.unregisterNativeHook();
      } catch (NativeHookException e1) {
        e1.printStackTrace();
      }
    }
  }
}