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
        Player Tori = new Player("Tori", "444", false);
        Player Phuc = new Player("Phuc", "555", false);
        Player Phat = new Player("Phat", "333", false);

        ArrayList<Player> players = new ArrayList<>();
        players.add(Tam);
        players.add(Kiet);
        players.add(Phat);
        players.add(Tori);
        players.add(Phuc);

        GameDriver gameDriver = new GameDriver();
        gameDriver.initPlayers(players);
        assertEquals("Tam Kiet Phat Tori Phuc Dealer ", gameDriver.getNamesPlayersList());
    }

    @Test
    void getResultTest() {

    }


    @Test
    void initDeck() {
    }

    @Test
    void shuffleDeck() {
    }

    @Test
    void initGame() {
    }

    @Test
    void dealStartingCards() {
    }

    @Test
    void dealerHasBlackjack() {
    }

    // If dealer has Blackjack
    // If there's no player has Blackjack, it should return that all players lose. Otherwise, only that player/ those players win.
    @Test
    void whenDealerHasBlackjack() {
        // init game
        Player Tam = new Player("Tam", "111", false);
        Player Kiet = new Player("Kiet", "222", false);
        Player Tori = new Player("Tori", "444", false);
        Player Phuc = new Player("Phuc", "555", false);
        Player Phat = new Player("Phat", "333", false);

        ArrayList<Player> players = new ArrayList<>();
        players.add(Tam);
        players.add(Kiet);
        players.add(Tori);
        players.add(Phuc);
        players.add(Phat);
        GameDriver gameDriver = new GameDriver();
        gameDriver.initGame(players, 1);
        for (int i = 0; i < 1000; i++) {
            System.out.println("AT THE TIME " + i + 1 + ": ");
            // Test method getResult()
            assertEquals("Tam: Lose, Kiet: Lose, Tori: Lose, Phuc: Lose, Phat: Lose", gameDriver.getResult());
            //gameDriver.resetGame();

            Tam = new Player("Tam", "111", false);
            Kiet = new Player("Kiet", "222", false);
            Tori = new Player("Tori", "444", false);
            Phuc = new Player("Phuc", "555", false);
            Phat = new Player("Phat", "333", false);
            players = new ArrayList<>();
            players.add(Tam);
            players.add(Kiet);
            players.add(Tori);
            players.add(Phuc);
            players.add(Phat);
            gameDriver = new GameDriver();
            gameDriver.initGame(players, 1);
        }
    }

    /*
    Dealing Starting cards...

Dealing cards to Tam...
Tam's hand:
2- D
A- D
Dealing cards to Kiet...
Kiet's hand:
7- H
2- S
Dealing cards to Tori...
Tori's hand:
A- H
Q- H
Dealing cards to Phuc...
Phuc's hand:
9- D
10- S
Dealing cards to Phat...
Phat's hand:
4- D
8- H
Dealing cards to Dealer...
AT THE TIME 01:

org.opentest4j.AssertionFailedError:
Expected :Tam: Lose, Kiet: Lose, Tori: Lose, Phuc: Lose, Phat: Lose
Actual   :Tam: Lose, Kiet: Lose, Tori: Tie, Phuc: Lose, Phat: Lose

     */

    @Test
    void checkPlayersBlackjack() {
    }


    @Test
    void setBlackjackHandToDealer() {
    }

    @Test
    void isMoveValidReturnTrueTest() {
        GameDriver gameDriver = new GameDriver();
        assertEquals(true, gameDriver.isMoveValid("HIt"));
    }

    @Test
    void isMoveValidReturnTrue2Test() {
        GameDriver gameDriver = new GameDriver();
        assertEquals(true, gameDriver.isMoveValid("HIT"));
    }

    @Test
    void isMoveValidReturnTrue3Test() {
        GameDriver gameDriver = new GameDriver();
        assertEquals(true, gameDriver.isMoveValid("hit"));
    }

    @Test
    void isMoveValidReturnFalseTest() {
        GameDriver gameDriver = new GameDriver();
        assertEquals(false, gameDriver.isMoveValid("HItt"));
    }

    @Test
    void isMoveValidReturnFalse2Test() {
        GameDriver gameDriver = new GameDriver();
        assertEquals(false, gameDriver.isMoveValid("raw"));
    }

    @Test
    void getValidMoveTest() {
        Player Tam = new Player("Tam", "111", false);
        Player Kiet = new Player("Kiet", "222", false);
        Player Tori = new Player("Tori", "444", false);
        Player Phuc = new Player("Phuc", "555", false);
        Player Phat = new Player("Phat", "333", false);

        ArrayList<Player> players = new ArrayList<>();
        players.add(Tam);
        players.add(Kiet);
        players.add(Phat);
        players.add(Tori);
        players.add(Phuc);

        GameDriver gameDriver = new GameDriver();
        gameDriver.initGame(players, 1);
        assertEquals("hit", gameDriver.getValidMove());
    }
}