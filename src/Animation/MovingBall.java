package Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingBall extends JPanel implements ActionListener {
  private int x = 0;          // Posisi horizontal
  private int y = 100;        // Posisi vertikal
  private int velX = 2;       // Kecepatan (pixel per frame)
  private Timer timer;

  public MovingBall() {
    // Set timer untuk berjalan setiap 10ms (sekitar 100 FPS)
    timer = new Timer(10, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Menghaluskan tepi gambar (Anti-aliasing)
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Gambar bola
    g2d.setColor(Color.RED);
    g2d.fillOval(x, y, 50, 50);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Logika Update: Gerakkan bola ke kanan
    if (x < getWidth()) {
      x += velX;
    } else {
      x = -50; // Reset ke kiri jika keluar layar
    }

    // Memicu pemanggilan paintComponent
    repaint();
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Animasi Swing Sederhana");
    frame.add(new MovingBall());
    frame.setSize(600, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}