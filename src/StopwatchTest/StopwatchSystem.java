package StopwatchTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchSystem implements ActionListener {

  JFrame frame = new JFrame();

  MyTimerLabel timeLabel = new MyTimerLabel();
  MyTimerLabel miniTimeLabel = new MyTimerLabel();

  MyButton startTimerButton = new MyButton("START TIMER");
  MyButton stopTimerButton = new MyButton("STOP TIMER");
  MyButton startMiniTimerButton = new MyButton("START MINI TIMER");
  MyButton stopMiniTimerButton = new MyButton("STOP MINI TIMER");
  MyButton resetTimerButton = new MyButton("RESET TIMER");
  MyButton resetMiniTimerButton = new MyButton("RESET MINI TIMER");

  MyButton switchButton = new MyButton("SWITCH");
  MyButton submitButton = new MyButton("SUBMIT");

  JTextField millisecondsTimerTextFields = new JTextField();
  JTextField secondsTimerTextFields = new JTextField();
  JTextField minutesTimerTextFields = new JTextField();

  JTextField millisecondsMiniTimerTextFields = new JTextField();
  JTextField secondsMiniTimerTextFields = new JTextField();
  JTextField minutesMiniTimerTextFields = new JTextField();

  MyTimer timer = new MyTimer(timeLabel);
  MyTimer miniTimer = new MyTimer(miniTimeLabel);

  StopwatchSystem() {
    timeLabel.setBounds(0, 0, 300, 100);
    miniTimeLabel.setBounds(0, 100, 300, 50);

    minutesTimerTextFields.addActionListener(this);
    minutesTimerTextFields.setBounds(0, 150, 100, 50);
    secondsTimerTextFields.addActionListener(this);
    secondsTimerTextFields.setBounds(100, 150, 100, 50);
    millisecondsTimerTextFields.addActionListener(this);
    millisecondsTimerTextFields.setBounds(200, 150, 100, 50);

    minutesMiniTimerTextFields.addActionListener(this);
    minutesMiniTimerTextFields.setBounds(0, 200, 100, 50);
    secondsMiniTimerTextFields.addActionListener(this);
    secondsMiniTimerTextFields.setBounds(100, 200, 100, 50);
    millisecondsMiniTimerTextFields.addActionListener(this);
    millisecondsMiniTimerTextFields.setBounds(200, 200, 100, 50);

    submitButton.setBounds(0, 250, 150, 50);
    submitButton.addActionListener(this);

    startTimerButton.setBounds(0, 300, 100, 50);
    startTimerButton.addActionListener(this);
    stopTimerButton.setBounds(0, 350, 100, 50);
    stopTimerButton.addActionListener(this);
    resetTimerButton.setBounds(0, 400, 100, 50);
    resetTimerButton.addActionListener(this);

    startMiniTimerButton.setBounds(100, 300, 100, 50);
    startMiniTimerButton.addActionListener(this);
    stopMiniTimerButton.setBounds(100, 350, 100, 50);
    stopMiniTimerButton.addActionListener(this);
    resetMiniTimerButton.setBounds(100, 400, 100, 50);
    resetMiniTimerButton.addActionListener(this);

    switchButton.setBounds(200, 400, 100, 50);
    switchButton.addActionListener(this);


    frame.add(timeLabel);
    frame.add(miniTimeLabel);
    frame.add(startTimerButton);
    frame.add(stopTimerButton);
    frame.add(resetTimerButton);
    frame.add(startMiniTimerButton);
    frame.add(stopMiniTimerButton);
    frame.add(resetMiniTimerButton);
    frame.add(switchButton);
    frame.add(minutesTimerTextFields);
    frame.add(secondsTimerTextFields);
    frame.add(millisecondsTimerTextFields);
    frame.add(minutesMiniTimerTextFields);
    frame.add(secondsMiniTimerTextFields);
    frame.add(millisecondsMiniTimerTextFields);
    frame.add(submitButton);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setLayout(null);
    frame.setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startTimerButton) {
      timer.start();
    }
    if (e.getSource() == stopTimerButton) {
      timer.stop();
    }
    if (e.getSource() == resetTimerButton) {
      timer.reset();
    }
    if (e.getSource() == startMiniTimerButton) {
      miniTimer.start();
    }
    if (e.getSource() == stopMiniTimerButton) {
      miniTimer.stop();
    }
    if (e.getSource() == resetMiniTimerButton) {
      miniTimer.reset();
    }

    if (e.getSource() == switchButton) {
      if (!miniTimer.isStarted()) {
        timer.stop();
        miniTimer.start();
      } else {
        timer.start();
        miniTimer.stop();
      }
    }
    if (e.getSource() == submitButton) {
      try {
        inputMinutes(timer, minutesTimerTextFields);
        inputSeconds(timer, secondsTimerTextFields);
        inputMilliseconds(timer, millisecondsTimerTextFields);

        inputMinutes(miniTimer, minutesMiniTimerTextFields);
        inputSeconds(miniTimer, secondsMiniTimerTextFields);
        inputMilliseconds(miniTimer, millisecondsMiniTimerTextFields);

      } catch (NumberFormatException exception) {
        System.out.println("Hanya Angka");
      }
      minutesTimerTextFields.setText("");
      secondsTimerTextFields.setText("");
      millisecondsTimerTextFields.setText("");

      minutesMiniTimerTextFields.setText("");
      secondsMiniTimerTextFields.setText("");
      millisecondsMiniTimerTextFields.setText("");
    }
  }

  public void inputMinutes(MyTimer timer, JTextField textFileds) {
    if (!textFileds.getText().isEmpty()) {
      if (textFileds.getText().startsWith("+") || textFileds.getText().startsWith("-")) {
        timer.addMinutes(Integer.parseInt(textFileds.getText()));
      } else {
        timer.setMinutes(Integer.parseInt(textFileds.getText()));
      }
    }
  }

  public void inputSeconds(MyTimer timer, JTextField textFileds) {
    if (!textFileds.getText().isEmpty()) {
      if (textFileds.getText().startsWith("+") || textFileds.getText().startsWith("-")) {
        timer.addSeconds(Integer.parseInt(textFileds.getText()));
      } else {
        timer.setSeconds(Integer.parseInt(textFileds.getText()));
      }
    }
  }

  public void inputMilliseconds(MyTimer timer, JTextField textFileds) {
    if (!textFileds.getText().isEmpty()) {
      if (textFileds.getText().startsWith("+") || textFileds.getText().startsWith("-")) {
        timer.addMilliseconds(Integer.parseInt(textFileds.getText()));
      } else {
        timer.setMilliseconds(Integer.parseInt(textFileds.getText()));
      }
    }
  }
}