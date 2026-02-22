package StopwatchTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTimer {

  private JLabel label;

  private int elapsedTime = 0;

  private int milliseconds = 0;
  private int seconds = 0;
  private int minutes = 0;

  private String milliseconds_string = String.format("%02d", milliseconds);
  private String seconds_string = String.format("%02d", seconds);
  private String minutes_string = String.format("%02d", minutes);

  private boolean started = false;

  private long startTime;
  private int initialTime;

  Timer timer = new Timer(10, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      long now = System.currentTimeMillis();
      long durationSinceStart = now - startTime;

      elapsedTime = initialTime - (int) durationSinceStart;

      refresh();
//      printTime();

      if (elapsedTime <= 0) {
        reset();
      }
    }
  });

  MyTimer(){

  }
  MyTimer(JLabel label){
    this.label = label;
  }

  public void start(){
    started = true;
    startTime = System.currentTimeMillis();
    initialTime = elapsedTime;
    timer.start();
  }

  public void stop(){
    started = false;
    timer.stop();
  }

  public void reset(){
    started = false;
    timer.stop();
    elapsedTime = 0;
    milliseconds = 0;
    seconds = 0;
    minutes = 0;
    refresh();
  }

  public void refresh() {
    milliseconds = (elapsedTime % 1000);
    seconds = (elapsedTime / 1000) % 60;
    minutes = (elapsedTime / 60_000) % 60;
    milliseconds_string = String.format("%02d", milliseconds / 10);
    seconds_string = String.format("%02d", seconds);
    minutes_string = String.format("%02d", minutes);
    label.setText(minutes_string + ":" + seconds_string + "." + milliseconds_string);
  }

  public void printTime(){
    System.out.println("Minute: " + minutes_string + ", Seconds: " + seconds_string + ", millis: " + milliseconds_string);
  }

//  public static void main(String[] args) throws InterruptedException {
//    MainTimer bigTimer = new MainTimer();
//
//    bigTimer.elapsedTime = 15_000;
//
//    bigTimer.start();
//    Thread.sleep(5000);
//    bigTimer.stop();
//    Thread.sleep(3000);
//    bigTimer.start();
//    Thread.sleep(4000);
//    bigTimer.stop();
//    bigTimer.reset();
//    bigTimer.printTime();
//
//    Thread.sleep(60_000);
//  }

  public void setLabel(JLabel label) {
    this.label = label;
  }

  public int getInitialTime() {
    return initialTime;
  }

  public void setInitialTime(int initialTime) {
    this.initialTime = initialTime;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public boolean isStarted() {
    return started;
  }

  public void setStarted(boolean started) {
    this.started = started;
  }

  public String getMinutes_string() {
    return minutes_string;
  }

  public void setMinutes_string(String minutes_string) {
    this.minutes_string = minutes_string;
  }

  public String getSeconds_string() {
    return seconds_string;
  }

  public void setSeconds_string(String seconds_string) {
    this.seconds_string = seconds_string;
  }

  public String getMilliseconds_string() {
    return milliseconds_string;
  }

  public void setMilliseconds_string(String milliseconds_string) {
    this.milliseconds_string = milliseconds_string;
  }

  public int getMinutes() {
    return minutes;
  }

  public void setMinutes(int minutes) {
    this.minutes = minutes;
    updateElapsedTime();
  }

  public void addMinutes(int minutes) {
    this.minutes += minutes;
    updateElapsedTime();
  }

  public void subMinutes(int minutes) {
    this.minutes -= minutes;
    updateElapsedTime();
  }

  public int getSeconds() {
    return seconds;
  }

  public void setSeconds(int seconds) {
    this.seconds = seconds;
    updateElapsedTime();
  }

  public void addSeconds(int seconds) {
    this.seconds += seconds;
    updateElapsedTime();
  }

  public void subSeconds(int seconds) {
    this.seconds -= seconds;
    updateElapsedTime();
  }

  public int getMilliseconds() {
    return milliseconds;
  }

  public void setMilliseconds(int milliseconds) {
    this.milliseconds = milliseconds;
    updateElapsedTime();
  }

  public void addMilliseconds(int milliseconds) {
    this.milliseconds += MyTimer.this.milliseconds;
    updateElapsedTime();
  }

  public void subMilliseconds(int milliseconds) {
    this.milliseconds -= MyTimer.this.milliseconds;
    updateElapsedTime();
  }

  public int getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(int elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  public void updateElapsedTime(){
    this.elapsedTime = (minutes * 60_000) + (seconds * 1_000) + milliseconds;
    refresh();
  }

  public Timer getTimer() {
    return timer;
  }

  public void setTimer(Timer timer) {
    this.timer = timer;
  }
}
