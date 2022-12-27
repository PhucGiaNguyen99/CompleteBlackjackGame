public class Player {
    private boolean isDealer;
    private String name;
    private String phoneNumber;

    // A card hand to save the hand of the player
    private Hand playerHand;

    // Status to determine whether the user is losing, winning or even to the opponent
    private int status;

    // constants for player status
    public static final int STATUS_WIN = 1;
    public static final int STATUS_TIE = 0;
    public static final int STATUS_LOSE = -1;
    public static final int STATUS_UNDETERMINED = 13;

    public Player(String name, String phoneNumber, boolean isDealer) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isDealer = isDealer;
        this.playerHand = new Hand();
        this.status = STATUS_UNDETERMINED;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusString() {
        switch (getStatus()) {
            case -1:
                return "Lose";
            case 0:
                return "Tie";
            case 1:
                return "Win";
        }
        return "Undetermined";
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatusTie() {
        setStatus(STATUS_TIE);
    }

    public void setStatusWin() {
        setStatus(STATUS_WIN);
    }

    public void setStatusLose() {
        setStatus(STATUS_LOSE);
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    // Return name and status of the player
    public String getResult() {
        return "Player: " + this.getName() + ", Status: " + this.getStatus();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }


    // Get the total point of the player hand
    public int getTotalPointOfHand() {
        return playerHand.calculateTotalPoint();
    }

    // Print out the hand
    public void printHand() {
        playerHand.presentHand();
    }

    // Return string of name and phone number of the player
    public String toString() {
        return "Name: " + name + ".  " + "Phone number: " + phoneNumber;
    }

    // Deal one card for the player
    // by adding this card to the player's hand
    public void dealCardForPlayer(Card card) {
        this.getPlayerHand().dealCard(card);
    }

    // Check if the player busted or not
    public boolean isBusted() {
        return playerHand.isBusted();
    }

    // Check if the player has Blackjack
    public boolean hasBlackJack() {
        return playerHand.hasBlackkack();
    }

    //public boolean hasAce() {
    //  return playerHand.containAce();
    //}
}
