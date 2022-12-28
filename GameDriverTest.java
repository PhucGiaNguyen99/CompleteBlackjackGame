import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameDriverTest {

    @Test
    void initPlayers() {

    }

    @Test
    void getNamesPlayersList() {
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

        BJGameDriver gameDriver = new BJGameDriver();
        gameDriver.initPlayers(players);
        assertEquals("Tam Kiet Phat Tori Phuc Dealer ", gameDriver.getNamesPlayersList());
    }
}