package TextFieldsTest;

import javax.swing.*;

public class MyTextFields extends JFrame {

  JFrame frame;
  JTextField textField;

  MyTextFields(String title){
    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setResizable(false);

    textField = new JTextField();
    frame.add(textField);

    frame.setVisible(true);
  }
}
