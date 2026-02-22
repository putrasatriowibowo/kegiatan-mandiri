package Leaderboard;

class Player implements Comparable<Player> {
  String name;
  int score;

  public Player(String name, int score) {
    this.name = name;
    this.score = score;
  }

  @Override
  public int compareTo(Player other) {
    // Mengurutkan dari skor terbesar ke terkecil
    return Integer.compare(other.score, this.score);
  }
}