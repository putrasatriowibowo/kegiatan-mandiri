package StopwatchTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButton extends JButton {
  MyButton(String text){
    this.setText(text);
    this.setFont(new Font("Times New Roman", Font.PLAIN, 10));
    this.setFocusable(false);
  }
}
