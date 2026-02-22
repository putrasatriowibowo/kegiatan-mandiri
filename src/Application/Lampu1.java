package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Lampu1 extends JFrame implements KeyListener {

  JLabel label;
  ImageIcon offIcon;
  ImageIcon onIcon;

  Lampu1() {
    offIcon = new ImageIcon("src/Off.png");
    onIcon = new ImageIcon("src/On.png");

    Image offImage = offIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    Image onImage = onIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

    offIcon = new ImageIcon(offImage);
    onIcon = new ImageIcon(onImage);

    label = new JLabel(offIcon);

    this.add(label);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400,400);

    this.addKeyListener(this);
    this.setFocusable(true);

    this.setVisible(true);
  }

  @Override
  public void keyTyped(KeyEvent e) { }

  @Override
  public void keyPressed(KeyEvent e) { }

  @Override
  public void keyReleased(KeyEvent e) {

    switch (e.getKeyChar()){
      case '0':
        label.setIcon(offIcon);
        break;
      case '1':
        label.setIcon(onIcon);
        break;
    }
  }

  public static void main(String[] args) {
    new Lampu1();
  }
}