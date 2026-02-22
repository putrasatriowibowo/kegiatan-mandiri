package StopwatchTest;

import javax.swing.*;
import java.awt.*;

public class MyTimerLabel extends JLabel {
  MyTimer timer = new MyTimer();

  MyTimerLabel() {
    this.setText(timer.getMinutes_string() + ":" + timer.getSeconds_string() + "." + timer.getMilliseconds_string());
    this.setFont(new Font("Verdana", Font.PLAIN, 35));
    this.setBorder(BorderFactory.createBevelBorder(1));
    this.setOpaque(true);
    this.setHorizontalAlignment(JTextField.CENTER);
  }
}
