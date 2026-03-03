package DesignPattern.Lampu;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

public class Lampu extends JLabel {
  private final FlatSVGIcon offIcon = new FlatSVGIcon("resource/off-vector.svg", 200, 200);
  private final FlatSVGIcon onIcon = new FlatSVGIcon("resource/on-vector.svg", 200, 200);

  public Lampu(){
    this.setBackground(new Color(0,0,0,0));
    this.setBounds(0,0,200,200);
    this.setIcon(new FlatSVGIcon(offIcon));
  }

  public void on(){
    this.setIcon(new FlatSVGIcon(onIcon));
  }

  public void off(){
    this.setIcon(new FlatSVGIcon(offIcon));
  }
}
