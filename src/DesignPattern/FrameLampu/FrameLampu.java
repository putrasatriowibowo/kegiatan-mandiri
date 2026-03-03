package DesignPattern.FrameLampu;


import DesignPattern.Lampu.Lampu;

import javax.swing.*;
import java.awt.*;

public class FrameLampu extends JFrame {

  public Lampu lampu = new Lampu();

  public FrameLampu (int offset1, int offset2){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setLocation(offset1, offset2);
    this.setUndecorated(true);
    this.setBackground(new Color(0,0,0, 0));


    this.add(lampu);
    this.pack();
    this.setVisible(true);
  }
}
