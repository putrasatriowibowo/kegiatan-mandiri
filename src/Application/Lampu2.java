package Application;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Lampu2 extends JFrame implements KeyListener {

  Lampu2(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 500);
    this.setLayout(null);



    this.addKeyListener(this);
    this.setVisible(true);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println(e.getKeyChar());
  }
}
