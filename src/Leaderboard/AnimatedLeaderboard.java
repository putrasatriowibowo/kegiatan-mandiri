package Leaderboard;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AnimatedLeaderboard extends JFrame {
  private JTable table;
  private DefaultTableModel model;
  private ArrayList<Player> players;
  // Menyimpan baris mana yang sedang "berubah" peringkatnya
  private Map<String, Color> rowColors = new HashMap<>();

  public AnimatedLeaderboard() {
    setTitle("Animated Leaderboard");
    setSize(500, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    players = new ArrayList<>();
    players.add(new Player("Player 1", 500));
    players.add(new Player("Player 2", 450));
    players.add(new Player("Player 3", 400));

    model = new DefaultTableModel(new String[]{"Rank", "Name", "Score"}, 0);
    table = new JTable(model);

    // Custom Renderer untuk animasi warna
    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
        Component comp = super.getTableCellRendererComponent(t, v, s, f, r, c);
        String name = (String) t.getValueAt(r, 1);

        // Jika player sedang berubah posisi, beri warna highlight
        if (rowColors.containsKey(name)) {
          comp.setBackground(rowColors.get(name));
        } else {
          comp.setBackground(Color.WHITE);
        }
        return comp;
      }
    });

    add(new JScrollPane(table), BorderLayout.CENTER);

    JButton btnUpdate = new JButton("Boost Player 3 (+200 Score)");
    btnUpdate.addActionListener(e -> animateUpdate("Player 3", 200));
    add(btnUpdate, BorderLayout.SOUTH);

    refreshTable();
  }

  private void animateUpdate(String targetName, int bonus) {
    for (Player p : players) {
      if (p.name.equals(targetName)) {
        p.score += bonus;
        // Beri warna kuning terang sebagai tanda perubahan
        rowColors.put(p.name, Color.YELLOW);
      }
    }

    Collections.sort(players);
    refreshTable();

    // Timer untuk efek Fade-Out (Animasi Visual)
    Timer timer = new Timer(50, null);
    timer.addActionListener(e -> {
      Color current = rowColors.get(targetName);
      if (current != null) {
        // Kurangi intensitas warna perlahan (ke putih)
        int r = Math.min(255, current.getRed() + 5);
        int g = Math.min(255, current.getGreen() + 5);
        int b = Math.min(255, current.getBlue() + 5);

        if (r >= 255 && g >= 255 && b >= 255) {
          rowColors.remove(targetName);
          timer.stop();
        } else {
          rowColors.put(targetName, new Color(r, g, b));
        }
        table.repaint();
      }
    });
    timer.start();
  }

  private void refreshTable() {
    model.setRowCount(0);
    for (int i = 0; i < players.size(); i++) {
      Player p = players.get(i);
      model.addRow(new Object[]{i + 1, p.name, p.score});
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new AnimatedLeaderboard().setVisible(true));
  }
}