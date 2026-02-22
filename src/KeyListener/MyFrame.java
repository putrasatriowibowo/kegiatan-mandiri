package KeyListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame {

  JTextField textField;

  MyFrame(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 500);
    this.setLayout(null);


    textField = new JTextField();
    textField.setBounds(0,0,200,100);
    textField.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(textField.getText());
        textField.setText("");
      }
    });
    this.add(textField);

    this.setVisible(true);
  }
}
