import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Player Tam = new Player("Tam", "111", false);
        Player Kiet = new Player("Kiet", "222", false);
        Player Phat = new Player("Phat", "333", false);
        Player Tori = new Player("Tori", "444", false);
        Player Phuc = new Player("Phuc", "555", false);
        ArrayList<Player> players = new ArrayList<>();
        players.add(Tam);
        players.add(Kiet);
        players.add(Phat);
        players.add(Tori);
        players.add(Phuc);

        GameDriver gameDriver = new GameDriver();
        gameDriver.initGame(players, 1);
    }
}
