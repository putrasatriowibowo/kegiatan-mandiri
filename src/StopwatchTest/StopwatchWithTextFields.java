package StopwatchTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchWithTextFields implements ActionListener {

  JFrame frame = new JFrame();
  JButton startButton = new JButton();
  JButton resetButton = new JButton();
  JLabel timeLabel = new JLabel();
  int elapsedTime = 0;
  int milliseconds = 0;
  int seconds = 0;
  int minutes = 0;
  boolean started = false;
  String milliseconds_string = String.format("%02d", milliseconds);
  String seconds_string = String.format("%02d", seconds);
  String minutes_string = String.format("%02d", minutes);

  JTextField textField = new JTextField();

  Timer timer = new Timer(10, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      elapsedTime -= 10;
      refresh();

      if (elapsedTime <= 0) {
        reset();
      }
    }
  });

  StopwatchWithTextFields(){
    timeLabel.setText(minutes_string + ":" + seconds_string + "." + milliseconds_string);
    timeLabel.setBounds(100, 100, 200, 100);
    timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
    timeLabel.setBorder(BorderFactory.createBevelBorder(1));
    timeLabel.setOpaque(true);
    timeLabel.setHorizontalAlignment(JTextField.CENTER);

    startButton.setText("START");
    startButton.setBounds(100, 200, 100, 50);
    startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
    startButton.setFocusable(false);
    startButton.addActionListener(this);

    resetButton.setText("RESET");
    resetButton.setBounds(200, 200, 100, 50);
    resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
    resetButton.setFocusable(false);
    resetButton.addActionListener(this);

    textField.addActionListener(this);
    textField.setBounds(250,300,200,100);

    frame.add(timeLabel);
    frame.add(startButton);
    frame.add(resetButton);
    frame.add(textField);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setLayout(null);
    frame.setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startButton) {
      if (started == false) {
        started = true;
        startButton.setText("STOP");
        start();
      } else{
        started = false;
        startButton.setText("START");
        stop();
      }
    }
    if (e.getSource() == resetButton) {
      reset();
    }
    if (e.getSource() == textField) {
      elapsedTime = Integer.parseInt(textField.getText()) * 1000;
      refresh();
      textField.setText("");
    }
  }

  public void start(){
    timer.start();
  }

  public void stop(){
    timer.stop();
  }

  public void reset (){
    started = false;
    startButton.setText("START");
    timer.stop();
    elapsedTime = 0;
    milliseconds = 0;
    seconds = 0;
    minutes = 0;
    refresh();
  }

  public void refresh(){
    milliseconds = (elapsedTime/10) % 100;
    seconds = (elapsedTime/1000) % 60;
    minutes = (elapsedTime/60_000) % 60;
    milliseconds_string = String.format("%02d", milliseconds);
    seconds_string = String.format("%02d", seconds);
    minutes_string = String.format("%02d", minutes);
    timeLabel.setText(minutes_string + ":" + seconds_string + "." + milliseconds_string);
  }
}
