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

    // Control turn of player for requesting move and get move when dealer.
    // Used only in case when dealer does not have Blackjack
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
    }

    // Initialize the deck: Create an object for the deck when it's not an empty deck
    public void initDeck() {
        deck = new Deck(false);
    }

    // Initialize the players list: Initialize the players list, create an object for dealer and add dealer object to the list
    public void initPlayersList(ArrayList<Player> players) {
        playersList = players;
        dealer = new Player("Dealer", "", true);
        playersList.add(dealer);
    }

    // Initialize game: initialize the deck and players list, initialize the number of players, set turn index to 0 and set boolean isGameFinished to false
    public void initGame(ArrayList<Player> players, int deckCount) {
        initDeck();
        initPlayersList(players);
        // Initialize the number of players
        this.numOfPlayers = this.playersList.size();
        turnIndex = 0;
        isGameFinished = false;
    }

    // initGame
    // shuffle deck
    // deal starting cards for players and dealer
    // check whether the dealer has Blackjack. Split the flow of game
    // If dealer has Blackjack, continue the flow based on whenDealerHasBlackjack().
    // Otherwise, do the followings:
    // Traverse the players list, if any player has Blackjack, set his status to Win
    // Run while loop with condition: turnIndex< players list size -1,
    // Run for loop for player in playersList
    // If player's status is Win, increment turnIndex by 1 to skip that player.
    // If it's Undetermined, Notify Player in turn, Request phone number and move, call ProcessPlayers2
    public void gameControl(ArrayList<Player> players, int deckCount) throws PlayerOutOfListException {
        initGame(players, deckCount);
        shuffleDeck();
        dealStartingCards();
        boolean isDealerHasBlackjack = checkDealerHasBlackjack();
        if (isDealerHasBlackjack) {
            whenDealerHasBlackjack();
        } else {
            checkPlayersBlackjack();

            // while loop: turn index < players list size -1
            while (turnIndex < playersList.size() - 1) {
                // for loop: player in playersList
                for (Player currentPlayer : playersList) {
                    // if status is Win, skip to the next player
                    if (currentPlayer.isStatusWin()) {
                        turnIndex++;
                    }
                    // if status is Undetermined, keep the turn index, request phone number and move ( given move from received text). Put them into processPlayers2()
                    else {
                        // notify which player in turn
                        System.out.println(getNotificationPlayerInTurn());
                        // get input of phone number and move from player in turn
                        String phoneNumber = getInputPhoneNumber();
                        String move = getInputMove();

                        // put into processPlayerTurn()
                        processPlayerTurn(phoneNumber, move);
                    }


                }
            }

        }
    }

    // Based on turn index, return a string of which player in turn
    public String getNotificationPlayerInTurn() {
        return "It's " + getInTurnPlayerName() + "'s turn!\n Enter your phone number and move: ";
    }

    // Get Player input for phone number and move
    public String getInputPhoneNumber() {
        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.next();
        return phoneNumber;
    }

    public String getInputMove() {
        System.out.println("Enter move (Hit/Stand): ");
        String move = scanner.next();
        return move;
    }

    // When dealer does not have Blackjack, Check and process any player has Blackjack
    // If any player has Blackjack, set his status to Win
    public void checkAndProcessPlayersBlackjack() {
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            if (currentPlayer.hasBlackJack()) {
                currentPlayer.setStatusWin();
            }
        }
    }

    // Test: Return Expected String of players' results to compare in testing checkAndProcessPlayersHasBlackjack()
    public String getExpectedStringResultPlayersStatusWhenDealerDoesNotHaveBlackjack() {
        String result = "";
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            if (currentPlayer.hasBlackJack()) {
                result += currentPlayer.getName() + ": Win";
            } else {
                result += currentPlayer.getName() + ": Undetermined";
            }
            result += "\n";
        }
        return result;
    }

    // Test game control when dealer has Blackjack
    public void gameControlWhenDealerHasBlackjack(ArrayList<Player> players, int deckCount) {
        initGame(players, deckCount);
        shuffleDeck();
        dealStartingCards();
        setBlackjackHandToDealer();
        boolean isDealerHasBlackjack = checkDealerHasBlackjack();
        if (isDealerHasBlackjack) {
            whenDealerHasBlackjack();
        }
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

    public boolean checkIfGameFinished() {
        return this.isGameFinished;
    }

    public int getTurnIndex() {
        return this.turnIndex;
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

    /* Using deckCount later
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

    }*/

    // Deal one card to the given player
    public void dealCard(Player player) {
        deck.dealCard(player);
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
            dealCard(player);
            dealCard(player);

            if (!player.isDealer()) {
                player.printHand();
            }
        }
    }

    // Check whether dealer has Blackjack
    public boolean checkDealerHasBlackjack() {
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

    // Test: When dealer has Blackjack, create the Expected string result to compare with the string return
    // Traverse the players list, if dealer has Blackjack, concatenate "Name: Tie"
    // Otherwise, concatenate "Name: Lose"
    public String getExpectedStringResultPlayersStatusWhenDealerHasBlackjack() {
        String result = "";
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            if (currentPlayer.hasBlackJack()) {
                result += currentPlayer.getName() + ": Tie";
            } else {
                result += currentPlayer.getName() + ": Lose";
            }
            result += "\n";
        }
        return result;
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
    public String getStringResult() {
        String result = "";
        for (int i = 0; i < playersList.size() - 1; i++) {
            Player currentPlayer = playersList.get(i);
            result += currentPlayer.getResult();
            result += "\n";
        }
        return result;
    }

    // Print result
    public void printResult() {
        System.out.println(getStringResult());
    }

    public void printHandAndTotalPointOfPlayer(Player player) {
        player.printHand();
        System.out.println(player.getTotalPointOfHand());
    }

    // Set Blackjack hand to dealer
    // This method is to test the case when dealer has Blackjack
    public void setBlackjackHandToDealer() {
        Card card1 = new Card("1", "H", null);
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

    // Given phone number, return name of that player
    public String getNameFromPhoneNumber(String givenPhoneNumber) throws PlayerOutOfListException {
        try {
            for (int i = 0; i < playersList.size() - 1; i++) {
                Player currentPlayer = playersList.get(i);
                if (arePhoneNumbersEqual(currentPlayer.getPhoneNumber(), givenPhoneNumber)) {
                    return currentPlayer.getName();
                }
            }
            return "This player is NOT in the players list!";
        } catch (Exception e) {
            throw new PlayerOutOfListException("The player is not in the players list!");
        }
    }

    // If it's from correct in turn player, check the given move if it's valid or not. Keep requesting until receiving a valid move.
    public String getValidMove() {
        String inputMove = getInputMove();
        while (!isMoveValid(inputMove)) {
            // Sending message to this phone number to request a valid move
            System.out.println("Hey " + getInTurnPlayerName() + ", it's NOT a valid move!");
            inputMove = getInputMove();
        }
        return inputMove;
    }

    // Given phone number and move from the message, go check phone number and move.
    // After having a valid move from in turn player, return a string with those 2 information.
    public String processPlayers(String phoneNumber, String move) throws PlayerOutOfListException {
        // Terminate if the phone number is not from the in turn player
        // Sending a message back to this phone number
        if (!isPlayerInTurn(phoneNumber)) {
            System.out.println("It's NOT your turn, " + getNameFromPhoneNumber(phoneNumber));
            return "Wrong turn!";
        }
        return phoneNumber + ": " + getValidMove();
    }

    // Covering method for requesting a valid move from correct in turn player and process that move
    public void processPlayerTurn(String phoneNumber, String move) throws PlayerOutOfListException {
        // if not from the in turn player, terminate and send a message back to this phone number
        if (!isPlayerInTurn(phoneNumber)) {
            System.out.println("It's NOT your turn, " + getNameFromPhoneNumber(phoneNumber));
            return;
        }

        // if the given move is valid, process that move. Otherwise, getting a valid move by calling getValidMove()
        if (!isMoveValid(move)) {
            System.out.println("Hey " + getInTurnPlayerName() + ", your move is invalid!");
            move = getValidMove();
        }

        // if move is "hit", continue with whenPlayerHits() to notify the current hand and total point of this player. Otherwise, use whenPlayerStands() to notify and skip to the next player
        while (move.equals("hit")) {
            whenPlayerHits(getInTurnPlayer());
            move = getValidMove();
        }
        whenPlayerStands(getInTurnPlayer());


    }

    // Return the string of hand and total point of player's hand
    public String getStringHandAndTotalPoint(Player player) {
        return player.getStringHandAndTotalPoint();
    }

    // When player stands, print out hand and skip to the next player
    public void whenPlayerStands(Player player) {
        System.out.println(player.getStringHandAndTotalPoint() + "\n");
        turnIndex++;
    }

    // When player hits, print out hand, deal card and request next move
    public void whenPlayerHits(Player player) {
        System.out.println("Hand and Total point: ");
        System.out.println(player.getStringHandAndTotalPoint() + "\n");
        System.out.println(getInTurnPlayerName() + " wants to hit! ");
        dealCard(player);
        System.out.println("After hitting: ");
        System.out.println(player.getStringHandAndTotalPoint() + "\n");
    }


    // Check if dealer has soft 17. Dealer has soft 17 when he has an Ace and 6
    // TEST: Given a player with hand of 2 cards, test whether dealer has soft 17.
    public boolean playerHasSoft17(Player player) {
        return player.playerHasAce() && player.getTotalPointOfHand() == 17;
    }

    public boolean dealerHasSoft17() {
        return playerHasSoft17(dealer);
    }

    // After serving all players, it's the dealer's turn. Firstly, we check if the dealer has soft 17. If dealer does, dealer hits one card. Then deal card for dealer while his total is smaller than 16.
    // The parameter of object of Player because it'll be used to test
    public void dealerPlays(Player dealer) {

        // Test: use the method to check playerHasSoft17
        if (playerHasSoft17(dealer)) {
            System.out.println("Dealer has soft 17!");
            System.out.println(getStringHandAndTotalPoint(dealer));
            dealCard(dealer);
        }
        // Dealer continue hitting until his hand is equal to or higher than 17
        while (dealer.getTotalPointOfHand() <= 16) {
            System.out.println("Dealer hits.");
            dealCard(dealer);
            System.out.println(getStringHandAndTotalPoint(dealer));
        }

        // NOTE: IF DEALER HAS SOFT 17, HE MUST HIT ONE MORE CARD.
        //if (dealerHasSoft17()) {
        //  dealCard(dealer);
        //}

        // Print out dealer's hand and total point
        System.out.println("Finally:");
        System.out.println(dealer.getStringHandAndTotalPoint());
    }

    // After serving all players, it's the dealer's turn. Firstly, we check if the dealer has soft 17. If dealer does, dealer hits one card. Then deal card for dealer while his total is smaller than 16.


    public Scanner getScanner() {
        return scanner;
    }


    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setTurnIndex(int turnIndex) {
        this.turnIndex = turnIndex;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

    public static void main(String[] args) throws PlayerOutOfListException {
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

        GameDriver gameDriver;
        gameDriver = new GameDriver();

        // Test game control when dealer has Blackjack
        //gameDriver.gameControlWhenDealerHasBlackjack(players, 1);

        // Test getValidMove()
        //gameDriver.getValidMove();

        // Test processPlayers2
        gameDriver.gameControl(players, 1);
        gameDriver.processPlayerTurn("111", "hit");
    }
}
