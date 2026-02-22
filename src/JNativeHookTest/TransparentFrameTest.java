package JNativeHookTest;

import javax.swing.*;
import java.awt.*;

public class TransparentFrameTest {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Test");
    frame.getContentPane().setBackground(new Color(0, 0, 0, 0));
    frame.setSize(500,500);
    frame.setUndecorated(true);
    frame.setOpacity(0);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
