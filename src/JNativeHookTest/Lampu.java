package JNativeHookTest;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class Lampu extends JFrame {
  JFrame frame;
  JLabel label;
  ImageIcon off;
  ImageIcon on;
  FlatSVGIcon offVector;
  FlatSVGIcon onVector;

  Lampu(String title, int offset1, int offset2){
    System.setProperty("awt.useSystemAAFontSettings", "on");
    System.setProperty("swing.aatext", "true");

    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocation(offset1, offset2);
    frame.setUndecorated(true);
    frame.setBackground(new Color(0,0,0, 0));

    off = new ImageIcon(new ImageIcon("src/resource/off-400-400.png").getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
    on = new ImageIcon(new ImageIcon("src/resource/on-400-400.png").getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
    offVector = new FlatSVGIcon("resource/off-vector.svg", 200, 200);
    onVector = new FlatSVGIcon("resource/on-vector.svg", 200, 200);

    label = new JLabel();
    label.setIcon(offVector);
    label.setBackground(new Color(0,0,0,0));
    frame.add(label);

    frame.pack();
    frame.setVisible(true);
  }

  public void on(){
    label.setIcon(onVector);
    frame.repaint();
  }

  public void off(){
    label.setIcon(offVector);
    frame.repaint();
  }
}
