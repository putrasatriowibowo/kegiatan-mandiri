package Leaderboard.Class;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.util.jar.JarEntry;

public class NameLabel extends JLabel {
  FlatSVGIcon background;

  NameLabel(String path, int width, int heigth) {
    FlatSVGIcon backgroundLabel = new FlatSVGIcon(path);

    JLabel nameLabel = new JLabel();
    nameLabel.setBounds(50, 50, width, heigth);
    nameLabel.setIcon(backgroundLabel);
  }

  NameLabel(String path, int width, int heigth, float scale) {
    FlatSVGIcon backgroundLabel = new FlatSVGIcon(path);
    FlatSVGIcon backgroundLabelSmall = backgroundLabel.derive(scale);

    JLabel nameLabel = new JLabel();
    nameLabel.setBounds(50, 50, width, heigth);
    nameLabel.setIcon(backgroundLabel);
  }

}
