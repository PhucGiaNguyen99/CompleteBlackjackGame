import java.util.ArrayList;

public class GameDriver {
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
}
