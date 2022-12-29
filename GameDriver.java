import java.util.ArrayList;
import java.util.Scanner;

public class GameDriver {

    Scanner scanner = new Scanner(System.in);

    // Initialized in initPlayers()
    private ArrayList<Player> playersList;

    Player dealer;
    private int numOfPlayers;


    // Initialized in initDeck()
    private Deck deck;

    // Index to control the turn of players
    // Initialized in initGame
    private int turnIndex;
    private boolean isGameFinished;


    // Method to initialize the players list
    // Given the array list of players, assign it to playersList and append the Player object of dealer. Then initialize the number of players
    public void initPlayers(ArrayList<Player> playersList) {
        this.playersList = playersList;

        // Generate Player object for the dealer
        dealer = new Player("Dealer", "911", true);

        // Add dealer to the end of the players list
        this.playersList.add(dealer);

        // Initialize the number of players
        this.numOfPlayers = this.playersList.size();
    }

    // Method to test the initPlayers method above
    // Return a string of name of the players in the players list
    public String getNamesPlayersList() {
        String namesString = "";
        for (Player player : playersList) {
            namesString += player.getName() + " ";
        }
        return namesString;
    }


    // Initialize the deck
    public void initDeck() {
        this.deck = new Deck(false);
    }

    // Shuffle the deck
    public void shuffleDeck() {
        this.deck.shuffleDeck();
    }

    /* Control the whole game
    public void initGame(ArrayList<Player> playersList, int deckCount) {


        // Initialize the turn index to 0
        turnIndex = 0;
        numOfPlayers = playersList.size();

        // create a Player object for Dealer, whose name and phone number are empty, but the boolean isDealer set to true
        Player dealer = new Player("", "", true);

        // add dealer object to the playersList

        // Initialize the game status to unfinished
        isGameFinished = false;
        while (isGameFinished == false) {

            // Initialize the playersList list
            initPlayers(playersList);

            // Initialize the deck of cards
            initDeck();

            // Shuffle the deck of cards
            shuffleDeck();

            // Deal starting cards for the playersList
            dealStartingCards();

            // After splitting the card, check whether the dealer has Black Jack, then traverse all the playersList
            // If any also has Black Jack, then set the status to even, otherwise to lose.
            checkBlackJack();

            // Otherwise, traverse the player list to ask whether any wants to draw like regular
            // Traverse the playersList list and put the player into the process move
            for (Player player : this.playersList) {
                String playerMove = getMove(player);
                processMove(player.getPhoneNumber(), playerMove);
            }
            dealerPlays();
            showFinalPoints();
            initWinners();

        }
    }*/

    // Using deckCount later
    public void initGame(ArrayList<Player> playersList, int deckCount) {
        // Initialize turn index to 0
        this.turnIndex = 0;

        // Boolean to control the status of the game
        this.isGameFinished = false;

        initDeck();

        shuffleDeck();

        initPlayers(playersList);

        dealStartingCards();

        // Test when dealer has Blackjack
        //setBlackjackHandToDealer();

        //whenDealerHasBlackjack();

        //getResult();

    }

    /* Traverse the playersList to deal starting cards
    1. Print out the name of the player.
    2. Dealing 2 cards.
    3. Print out hands of all players, except the dealer.
    * */
    public void dealStartingCards() {
        System.out.println("Dealing Starting cards...\n");
        for (Player player : playersList) {
            System.out.println("Dealing cards to " + player.getName() + "...");
            deck.dealCard(player);
            deck.dealCard(player);
            if (!player.isDealer()) {
                player.printHand();
            }
        }
    }

    // Check whether dealer has Blackjack
    public boolean dealerHasBlackjack() {
        return dealer.hasBlackJack();
    }

    // Case: Dealer has Blackjack.
    // Traverse the playersList, if player also has Blackjack, set player's status to tie. Otherwise, set his status to lose.
    public void whenDealerHasBlackjack() {
        // run for loop for players
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            if (currentPlayer.hasBlackJack()) {
                currentPlayer.setStatusTie();
            } else {
                currentPlayer.setStatusLose();
            }
        }
    }

    // Case: Dealer does NOT have Blackjack. Then go check whether there is any player has Blackjack. If yes, set status to win.
    public void checkPlayersBlackjack() {
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            if (currentPlayer.hasBlackJack()) {
                currentPlayer.setStatusWin();
            }
        }
    }

    // Return the name and status of all players
    public String getResult() {
        String result = "";
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            result += currentPlayer.getResult();
            if (i != playersList.size() - 2) {
                result += ", ";
            }
        }
        return result;
    }

    // Set Blackjack hand to dealer
    // This method is to test the case when dealer has Blackjack
    public void setBlackjackHandToDealer() {
        Card card1 = new Card("A", "H", null);
        Card card2 = new Card("Q", "H", null);
        Hand blackjackHand = new Hand();
        blackjackHand.addCard(card1);
        blackjackHand.addCard(card2);
        dealer.setHand(blackjackHand);
    }

    public void resetGame() {
        playersList = new ArrayList<>();
    }

    // Check Player in turn
    public Player getInTurnPlayer() {
        return playersList.get(turnIndex);
    }

    public String getInTurnPlayerName() {
        return getInTurnPlayer().getName();
    }

    public String getInTurnPlayerPhoneNumber() {
        return getInTurnPlayer().getPhoneNumber();
    }

    // Compare 2 phone numbers: one of the in turn player and of the sending message
    public boolean arePhoneNumbersEqual(String pNumber1, String pNumber2) {
        return pNumber1.equals(pNumber2);
    }

    // Using phone number of the message received, check if it is the in turn player
    public boolean isPlayerInTurn(String sendingPlayerPhoneNumber) {
        return arePhoneNumbersEqual(sendingPlayerPhoneNumber, getInTurnPlayerPhoneNumber());
    }

    // Check whether the given move is valid or not
    public boolean isMoveValid(String givenMove) {
        String moveInLowerCase = givenMove.toLowerCase();
        return moveInLowerCase.equals("stand") || moveInLowerCase.equals("hit");
    }

    // Process move: requesting player to enter move until receiving a valid move. Also return this valid move
    public String processMove() {
        String phoneNumber = getInTurnPlayerPhoneNumber();
        System.out.println("Hey " + getInTurnPlayerName() + ", ");
        System.out.println("Enter move(stand/hit): ");
        return scanner.next();

    }

    // Given phone number, return name of that player
    public String getNameFromPhoneNumber(String givenPhoneNumber) {
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            if (arePhoneNumbersEqual(currentPlayer.getPhoneNumber(), givenPhoneNumber)) {
                return currentPlayer.getName();
            }
        }
        return "This player is NOT in the players list!";
    }

    // Given phone number and move from the message, go check phone number and move.
    // After having a valid move from in turn player, return a string with those 2 information.
    public String processPlayers(String phoneNumber, String move) {
        // Terminate if the phone number is not from the in turn player
        // Sending a message back to this phone number
        if (!isPlayerInTurn(phoneNumber)) {
            System.out.println("It's NOT your turn, " + getNameFromPhoneNumber(phoneNumber));
            return "Wrong turn!";
        }
        return phoneNumber + ": " + getValidMove(phoneNumber, move);
    }

    // After having phone number of the in turn player, checking the move
    // Keep asking until a valid move receives. Return this valid move
    public String getValidMove(String phoneNumberInTurnPlayer, String inputMove) {
        while (!isMoveValid(inputMove)) {
            // Sending message to this phone number to request a valid move
            System.out.println("Hey " + getInTurnPlayerName() + ", it's NOT a valid move!");
            System.out.println("Enter move(stand/hit): ");
            inputMove = scanner.next();
        }
        return inputMove;
    }


}
