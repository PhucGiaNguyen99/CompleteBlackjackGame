public class Player {
    private boolean isDealer;
    private String name;
    private String phoneNumber;

    // A card hand to save the hand of the player
    private Hand hand;

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
        this.hand = new Hand();
        this.status = STATUS_UNDETERMINED;
    }

    public int getStatus() {
        return status;
    }

    public String getStringStatus() {
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

    public boolean isStatusWin() {
        return status == STATUS_WIN;
    }

    public boolean isStatusLose() {
        return status == STATUS_LOSE;
    }

    public boolean isStatusUndetermined() {
        return status == STATUS_UNDETERMINED;
    }

    public boolean isStatusTie() {
        return status == STATUS_TIE;
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
        return getName() + ": " + this.getStringStatus();
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

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    // Return string of hand
    public String getStringHand() {
        return hand.getStringHand();
    }

    // Get the total point of the player hand
    public int getTotalPointOfHand() {
        return hand.calculateTotalPoint();
    }

    // Print out the player's hand
    public void printHand() {
        System.out.println(getName() + "'s hand: ");
        hand.printHand();
    }

    // Return string of name and phone number of the player
    public String getStringPlayerNameAndPhoneNumber() {
        return "Name: " + name + ".  " + "Phone number: " + phoneNumber;
    }

    // Deal one card for the player
    // by adding this card to the player's hand
    public void addCardToHand(Card card) {
        this.getHand().addCard(card);
    }

    // Check if the player busted or not
    public boolean isBusted() {
        return hand.isBusted();
    }

    // Check if the player has Blackjack
    public boolean hasBlackJack() {
        return hand.hasBlackjack();
    }

    // Return String hand and total point of player's hand
    public String getStringHandAndTotalPoint() {
        return hand.getStringHandAndTotalPoint();
    }

    // Check if player's hand has an Ace
    public boolean playerHasAce() {
        return hand.hasAce();
    }

    // Empty player's hand
    public void resetHand() {
        hand.resetHand();
    }

    //public boolean hasAce() {
    //  return playerHand.containAce();
    //}
}
