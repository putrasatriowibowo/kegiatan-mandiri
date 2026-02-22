package JNativeHookTest;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SharpedImage {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Sharp Image Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    try {
      // 1. Baca file dari luar (Ganti dengan path file kamu)
      File fileLuar = new File("src/resource/on500.png");
      BufferedImage imgAsli = ImageIO.read(fileLuar);

      // 2. Tentukan ukuran kecil yang diinginkan
      int targetWidth = 100;
      int targetHeight = 100;

      // 3. Buat kanvas kosong baru ukuran kecil
      BufferedImage hasilTajam = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

      // 4. Gunakan Graphics2D untuk "melukis" ulang gambar asli ke kanvas kecil
      Graphics2D g2 = hasilTajam.createGraphics();

      // --- INI KUNCI KETAJAMANNYA ---
      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
      g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      // ------------------------------

      g2.drawImage(imgAsli, 0, 0, targetWidth, targetHeight, null);
      g2.dispose();

      // 5. Tampilkan di JLabel
      JLabel label = new JLabel(new ImageIcon(hasilTajam));
      frame.add(label);

    } catch (Exception e) {
      e.printStackTrace();
    }

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}