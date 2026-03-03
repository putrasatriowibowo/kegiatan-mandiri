package Leaderboard;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;

public class Leaderboard {

  static int labelWidth = 550;
  static int labelHeigth = 75;
  static int frameWidth = 700;
  static int frameHeigth = 700;
  static float scaled = 0.8f;


  public static void main(String[] args) {

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
    frame.setSize(frameWidth, frameHeigth);

    FlatSVGIcon backgroundLabel = new FlatSVGIcon("resource/leaderboard.svg", labelWidth, labelHeigth);
    FlatSVGIcon backgroundLabelSmall = backgroundLabel.derive(scaled);

    JLabel nameLabel = new JLabel();
    nameLabel.setBounds(50, 50, labelWidth, labelHeigth);
    nameLabel.setIcon(backgroundLabel);

    JLabel nameLabelSmall = new JLabel();
    nameLabelSmall.setBounds(50, 150, (int) (labelWidth*scaled), (int) (labelHeigth*scaled));
    nameLabelSmall.setIcon(backgroundLabelSmall);

    frame.add(nameLabel);
    frame.add(nameLabelSmall);
    frame.setVisible(true);
  }
}
