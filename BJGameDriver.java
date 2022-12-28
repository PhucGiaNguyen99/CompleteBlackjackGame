import java.util.ArrayList;
import java.util.Scanner;

public class BJGameDriver {
    private ArrayList<Player> playersList;

    //private ArrayList<Player> nonBlackJackPlayersList;

    // Index to control the turn of players
    // Initialized to 1
    private int turnIndex;

    // Initialize the number of players

    private int numOfPlayers;
    private Deck deck;

    // Create an object for the dealer
    private Dealer dealer = new Dealer();
    private boolean isGameFinished;


    //private ArrayList<Player> winnersArrayList;


    /*
    // Control the whole game
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

    /* initialize game
    1. Initialize the players list with the given list. Then do with the size of the list.
    2. Initialize the deck.
    3. Generate object Player for Dealer. Then add dealer to the end of the ArrayList for players.
    4. Set false to the boolean isGameFinished.

     */


    // Method to initialize the players list
    // Given the array list of players, assign it to playersList and append the Player object of dealer
    public void initPlayers(ArrayList<Player> playersList) {
        this.playersList = playersList;

        // Generate Player object for the dealer
        Player dealer = new Player("Dealer", "911", true);

        // Add dealer to the end of the players list
        this.playersList.add(dealer);
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


    //*************************************************************************************
    // Request the input of the player for the move option
    private String getMove(Player player) {
        System.out.println("It's " + player.getName() + "'s turn.");
        System.out.println("Current total point: " + player.getTotalPointOfHand());
        System.out.println("Enter move: ");
        Scanner scanner = new Scanner(System.in);
        String move = scanner.next().toUpperCase();
        while (!move.equals("HIT") && !move.equals("STAND")) {
            System.out.println("Current total point: " + player.getTotalPointOfHand());
            System.out.println("Enter valid move(Hit/Stand): ");
            move = scanner.next().toUpperCase();
        }
        return move;
    }

    //*************************************************************************************
    // Shuffle the deck of cards
    private void shuffleDeck() {
        deck.shuffleDeck();
    }


    // For the case the deck is not empty initially
    private void initDeck() {
        deck = new Deck(false);
    }


    //*************************************************************************************
    // Check if the game is finished yet
    private boolean isGameFinished() {
        return isGameFinished;
    }

    //*************************************************************************************
    // Check whether the dealer is busted
    private boolean isDealerBusted() {
        return dealer.isDealerBusted();
    }

    //*************************************************************************************
    // Show final points of all players and the dealer
    private void showFinalPoints() {
        System.out.println();
        System.out.println("Dealer     " + dealer.calculateTotalDealerHand());
        for (Player player : playersList) {
            System.out.println("Player " + player.getName() + "     " + player.getTotalPointOfHand());
        }
    }


    // Determine the winners and return an arrayList of the winners for android dev use later
    public void initWinners() {

        // If the dealer is not busted, the winners are players having total point higher than the dealer and not busted
        if (!isDealerBusted()) {
            int dealerPoint = dealer.calculateTotalDealerHand();

            for (Player player : playersList) {

                // In case: Both the dealer and the player are not busted
                if (!player.isBusted()) {
                    int playerPoint = player.getTotalPointOfHand();

                    if (playerPoint > dealerPoint) {
                        player.setStatusWin();
                        //winnersArrayList.add(player);
                    } else if (playerPoint == dealerPoint) {
                        player.setStatusTie();
                    } else {
                        player.setStatusLose();
                    }

                }

                // In case: If the player is busted, the dealer wins
                else {
                    player.setStatus(-1);
                }
                // Print out the player result
                System.out.println(player.getResult());
            }
        }

        // Otherwise, all are not busted win
        else {
            for (Player player : playersList) {

                // If both the player and the dealer are busted, set status of the player to even
                if (!player.isBusted()) {
                    player.setStatus(1);
                }

                // Otherwise, set status to win
                else {
                    player.setStatus(0);
                }

                // Print out the player result
                System.out.println(player.getResult());
            }
        }
        // Terminate the game
        isGameFinished = true;
    }

    //CHECKED
    // Remove the first card of the deck
    private Card removeFirstCard() {
        return deck.removeFirstCard();
    }

    //CHECKED
    // Dealing one card for the player
    private void dealCardForPlayer(Player player) {
        player.addCardToHand(removeFirstCard());
    }


    // After splitting the card, check whether the dealer has Black Jack, then traverse all the players
    // If any also has Black Jack, then set the status to even, otherwise to lose.

    // If the dealer does not have Blackjack, Add non-Blackjack players to the list
    // and use such list after that

    //*************************************************************************************
    /*private void checkBlackJack() {

        // Show that the dealer has Black Jack
        if (dealer.isBlackJack()) {
            System.out.println("Dealer has Black Jack!!!!");
            System.out.println();
        }

        for (Player player : playersList) {

            // If the dealer has Black Jack
            if (dealer.isBlackJack()) {

                if (player.hasBlackJack()) {
                    System.out.println("Player having phone number: " + player.getPhoneNumber() + " has Black Jack!!");
                    player.setStatusTie();
                } else {
                    player.setStatusLose();
                }
                System.out.println(player.getResult());

                // Terminate the game
                isGameFinished = true;
            }

            // Otherwise, if the player has Black Jack, then set the status to win, and continue with the remaining players
            // If the dealer does not have Blackjack, Add non-Blackjack players to the list
            // and use such list after that

            else {
                if (player.hasBlackJack()) {
                    System.out.println("Player having phone number: " + player.getPhoneNumber() + " has Black Jack!!");
                    player.setStatusWin();
                }
                nonBlackJackPlayersList.add(player);
            }

        }
    }*/


    // To start the game, Deal 2 cards for each player and the dealer
    public void dealStartingCards() {
        for (Player player : playersList) {
            if (!player.isDealer()) {
                System.out.println("Dealing cards for: " + player.getName());
                dealCardForPlayer(player);
                dealCardForPlayer(player);

                System.out.println(player.getName() + "'s hand: ");
                player.printHand();
                System.out.println("TOTAL POINT: " + player.getTotalPointOfHand());
                System.out.println();
            }
        }
        //dealCardForDealer();
        //dealCardForDealer();
        //System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
        //System.out.println();
    }

    // Check whether dealer has soft 17 or not, if he does, he would continue drawing
    // Dealer has soft 17 when there is any ace in the hand and the total equals to 17
    private boolean dealerHasSoft17() {
        return dealer.containAce() && dealer.calculateTotalDealerHand() == 17;
    }


    // For the case: The dealer does not have Black Jack

    // Dealer must hit while his hand is 16 or under
    // And check whether he has soft 17 or not
    private void dealerPlays() {
        while (dealer.calculateTotalDealerHand() <= 16) {
            System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
            System.out.println("Dealer continues to hit.");

            //dealCardForDealer();

            System.out.println();
        }

        // Check whether dealer has soft 17 or not, if he does, he would continue drawing
        // Dealer has soft 17 when there is any ace in the hand and the total equals to 17
        while (dealerHasSoft17()) {
            System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
            System.out.println("Dealer continues to hit.");
            //dealCardForDealer();
            System.out.println();
        }

        System.out.println("Dealer's TOTAL POINT: " + dealer.calculateTotalDealerHand());
        System.out.println();
    }


    // Manage the move of the players
    private void processMove(String phoneNumber, String move) {

        // Process move for PLAYER first

        // split it into a separate method to check whether the player is in correct turn or not
        // by checking the player with CORRECT PLAYER BY TURN
        // Determine the current player based on the turn index
        Player currentPlayer = playersList.get(turnIndex);

        if (phoneNumber.equals(currentPlayer.getPhoneNumber())) {

            // Notify the current player name and total point
            System.out.println("It's " + currentPlayer.getName() + "s turn.");

            // Check if the player has Black Jack, if he does, set his status to wi
            int currentTotalPoint = currentPlayer.getTotalPointOfHand();
            System.out.println("Current TOTAL POINT: " + currentTotalPoint);
            System.out.println();


            // If the player wants to stand, then skip to the next player in the list
            if (move.toUpperCase().equals("STAND")) {
                turnIndex++;
                return;
            }

            while (move.toUpperCase().equals("HIT")) {

                // Check if the player is busted or not, if the player is busted, skip to the next player
                if (currentPlayer.isBusted()) {
                    System.out.println("You've been busted already!");
                    turnIndex++;
                    return;
                }

                // If the player chooses to hit and he/she is not busted yet, then deal card for him/her and process the move
                dealCardForPlayer(currentPlayer);
                System.out.println(currentPlayer.getName() + "'s card hand: ");
                currentPlayer.printHand();
                System.out.println("TOTAL POINT after the move: " + currentPlayer.getTotalPointOfHand());
                System.out.println();

                System.out.println("Current total point: " + currentPlayer.getTotalPointOfHand());
                System.out.println("STAND or HIT ?");
                Scanner scanner = new Scanner(System.in);
                move = scanner.next();

                // If the player wants to stand, then skip to the next player in the list
                if (move.toUpperCase().equals("STAND")) {
                    turnIndex++;
                    return;
                }
            }

            // Process move for the dealer after dealing with all the players
            if (turnIndex == numOfPlayers - 1) {
                dealerPlays();
                System.out.println("Black Jack game is over!!!!");
                System.out.println(" WHO IS WINNER????");
                isGameFinished = true;
            }
        } else {
            // If the phone number is not matched to the current player's one
            System.out.println(" It's not turn of the player having phone number " + phoneNumber + "!!!!");
        }
    }


    //      CHECKED
    // Present name and total point of the player
    private void showPoint() {
        System.out.println("Player\t\t\t\t\t" + "Points");
        for (Player player : playersList) {
            System.out.println(player.getName() + "\t\t\t\t\t" + player.getTotalPointOfHand());
        }
    }


}


