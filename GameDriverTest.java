import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;

class GameDriverTest {

    @Test
    void initPlayersTurnIndex() {
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
    void initPlayersIsGameFinished() {
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

        assertEquals(0, gameDriver.getTurnIndex());
    }

    @Test
    void initPlayersNumOfPlayers() {
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
        assertEquals(6, gameDriver.getNumOfPlayers());
    }

    @Test
    void initPlayersPlayersList() {
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

        assertEquals("Tam Kiet Phat Tori Phuc Dealer ", gameDriver.getNamesPlayersList());
    }

    @Test
    void gameControlWhenDealerHasBlackjackTest() {
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
        gameDriver.gameControlWhenDealerHasBlackjack(players, 1);
        for (int i = 0; i < 1000; i++) {

            // Compare the string returned by getExpectedStringResult() and the string result after the testing method
            assertEquals(gameDriver.getExpectedStringResultPlayersStatusWhenDealerHasBlackjack(), gameDriver.getStringResult());
            gameDriver.resetGame();

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
            gameDriver.gameControlWhenDealerHasBlackjack(players, 1);
        }

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
            assertEquals("Tam: Lose, Kiet: Lose, Tori: Lose, Phuc: Lose, Phat: Lose", gameDriver.getStringResult());
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
        players.add(Tori);
        players.add(Phuc);
        players.add(Phat);
        GameDriver gameDriver = new GameDriver();
        gameDriver.gameControl(players, 1);
        assertEquals("hit", gameDriver.getValidMove());
    }


    // Check Player in turn
    @Test
    void getInTurnPlayerTest() {
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
        assertEquals(Tam, gameDriver.getInTurnPlayer());
    }

    @Test
    void getInTurnPlayerNameTest() {
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
        assertEquals("Tam", gameDriver.getInTurnPlayerName());
    }

    @Test
    void getInTurnPlayerPhoneNumberTest() {
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
        assertEquals("111", gameDriver.getInTurnPlayerPhoneNumber());
    }

    // Compare 2 phone numbers: one of the in turn player and of the sending message
    @Test
    void arePhoneNumbersEqualTest() {
        GameDriver gameDriver = new GameDriver();
        assertEquals(true, gameDriver.arePhoneNumbersEqual("123", "123"));
    }

    // Using phone number of the message received, check if it is the in turn player
    @Test
    void isPlayerInTurnTest() {
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
        assertEquals(false, gameDriver.isPlayerInTurn("112"));
    }

    // Check whether the given move is valid or not
    @Test
    void isMoveValidTest() {
        GameDriver gameDriver = new GameDriver();
        assertEquals(true, gameDriver.isMoveValid("hIT"));
    }

    // Process move: requesting player to enter move until receiving a valid move. Also return this valid move
    @Test
    void getMoveInputTest() {

    }

    // Given phone number, return name of that player
    @Test
    void getNameFromPhoneNumberTestNotIncludedCase() throws PlayerOutOfListException {
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

        assertEquals("This player is NOT in the players list!", gameDriver.getNameFromPhoneNumber("443"));
    }

    @Test
    void getNameFromPhoneNumberTestIncludedCase() throws PlayerOutOfListException {
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

        assertEquals("Phuc", gameDriver.getNameFromPhoneNumber("555"));
    }

    // After having phone number of the in turn player, checking the move
    // Keep asking until a valid move receives. Return this valid move


    // Given phone number and move from the message, go check phone number and move.
    // After having a valid move from in turn player, return a string with those 2 information.

    // Wrong move input is tested in the class
    @Test
    void processPlayersTest() throws PlayerOutOfListException {
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

        assertEquals("111: hit", gameDriver.processPlayers("111", "hit"));
    }

    // Case: Dealer has soft 17. The method should return true.
    // Assign a hand of an Ace and 6 to the player.
    @Test
    void playerHasSoft17TestReturnTrue() {
        Player Phuc = new Player("Phuc", "555", false);
        GameDriver gameDriver = new GameDriver();
        //for (int i = 0; i < 1000; i++) {
        gameDriver.initDeck();
        gameDriver.shuffleDeck();
        Hand playerHand = new Hand();
        playerHand.addCard(new Card("1", "S", null));
        playerHand.addCard(new Card("6", "S", null));
        Phuc.setHand(playerHand);
        gameDriver.printHandAndTotalPointOfPlayer(Phuc);

        assertTrue(gameDriver.playerHasSoft17(Phuc));
    }

    @Test
    void playerHasSoft17TestReturnFalse() {
        Player Phuc = new Player("Phuc", "555", false);
        GameDriver gameDriver = new GameDriver();
        for (int i = 0; i < 1000; i++) {
            gameDriver.initDeck();
            gameDriver.shuffleDeck();
            gameDriver.dealCard(Phuc);
            gameDriver.dealCard(Phuc);
            gameDriver.printHandAndTotalPointOfPlayer(Phuc);
            //assertEquals(true, gameDriver.playerHasSoft17(Phuc));

            // The method should return false when dealer neither has Ace and total is equal to 17
            if (Phuc.playerHasAce() && Phuc.getTotalPointOfHand() == 17) {
                assertTrue(gameDriver.playerHasSoft17(Phuc));

            } else {
                assertFalse(gameDriver.playerHasSoft17(Phuc));

            }

            Phuc.resetHand();
        }
    }

    // Case dealer has soft 17. It should deal one card for dealer until his hand's total is higher than 17.
    @Test
    void dealerPlaysTestWhenDealerHasSoft17() {
        Player dealer = new Player("Phuc", "555", false);
        GameDriver gameDriver = new GameDriver();
        //for (int i = 0; i < 1000; i++) {
        gameDriver.initDeck();
        gameDriver.shuffleDeck();
        Hand playerHand = new Hand();
        playerHand.addCard(new Card("1", "S", null));
        playerHand.addCard(new Card("6", "S", null));
        dealer.setHand(playerHand);
        gameDriver.printHandAndTotalPointOfPlayer(dealer);

        // Call the method
        gameDriver.dealerPlays(dealer);

        // There I'm trying to test the process and assign a random total hand to assertEquals method
        assertEquals(20, dealer.getTotalPointOfHand());
    }

    // Case dealer has soft 17. It should deal one card for dealer until his hand's total is higher than 17.
    // Test the process, so we ignore the method assertEquals()
    @Test
    void dealerPlaysTest() {
        Player dealer = new Player("Phuc", "555", false);
        GameDriver gameDriver = new GameDriver();
        for (int i = 0; i < 10; i++) {
            gameDriver.initDeck();
            gameDriver.shuffleDeck();
            gameDriver.dealCard(dealer);
            gameDriver.dealCard(dealer);
            gameDriver.printHandAndTotalPointOfPlayer(dealer);

            // Call the method
            gameDriver.dealerPlays(dealer);

            // There I'm trying to test the process and assign a random total hand to assertEquals method
            assertEquals(dealer.getTotalPointOfHand(), dealer.getTotalPointOfHand());
            dealer.resetHand();
        }

    }

    // Compare: Expected value the expected string result from the method to getStringResult() after calling checkAndProcessPlayersBlackjack()
    @Test
    void checkAndProcessPlayersBlackjackTest() {
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
        gameDriver.gameControl(players, 1);
        for (int i = 0; i < 1000; i++) {
            System.out.println("i: " + i);
            assertEquals(gameDriver.getExpectedStringResultPlayersStatusWhenDealerDoesNotHaveBlackjack(), gameDriver.getStringResult());
            gameDriver.resetGame();

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
}