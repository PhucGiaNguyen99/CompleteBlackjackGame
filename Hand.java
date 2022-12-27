import java.util.ArrayList;

public class Hand {
    public static final int BLACKJACK_VALUE = 1000;
    public static final int BUSTED_VALUE = -1000;

    // Using ArrayList for hand to store cards
    private ArrayList<Card> hand;

    private int status;

    // Constructor: Initialize the ArrayList for the hand and the status to regular
    public Hand() {
        hand = new ArrayList<>();
    }

    // Check if the player is busted. Busted if the total point of hand is larger than 21
    public boolean isBusted() {
        return this.calculateTotalPoint() > 21;
    }

    // Check if the player has Blackjack. Blackjack if the player has 2 cards and the total point is 21
    public boolean hasBlackjack() {
        return (hand.size() == 2 && this.calculateTotalPoint() == 21);
    }

    // Check the current status of hand. Return 1000 if the hand is Blackjack, -1000 if the hand is busted, or return the total point of the hand
    public int getStatusValue() {
        if (hasBlackjack()) {
            return BLACKJACK_VALUE;
        } else if (isBusted()) {
            return BUSTED_VALUE;
        }
        return calculateTotalPoint();
    }

    // Return the string status of the hand
    public String getStatusString() {
        switch (getStatusValue()) {
            case 1000:
                return "Blackjack";
            case -1000:
                return "Busted";
            default:
                return "" + calculateTotalPoint();
        }
    }


    /*Calculate the total point of hand.
    Calculate the sum of Aces cards and the sum of non-Ace cards.
    1. Traversing the hand to calculate the number of Aces and the sum of non-Ace cards.
    2. Only one Ace is counted as 11 and all remaining Aces are counted as 1.
    3. Calculate the current total of hand. If hand is busted, all Aces are counted as 1 only.
     */
    public int calculateTotalPoint() {

        // Initialize non-Ace sum, Aces sum and number of Aces to 0
        int nonAcesSum = 0, acesSum = 0, numOfAces = 0;

        // Calculate the number of Aces and the sum of non-Ace
        for (Card card : hand) {
            if (card.getCardValue() == 1) {
                numOfAces++;
            } else {
                nonAcesSum += card.getCardValue();
            }
        }

        // Only one Ace is counted as 11 and all remaining Aces are counted as 1 only
        if (numOfAces > 0) {
            acesSum = 11 + (numOfAces - 1);
        }

        // If the hand is busted, then all Aces are counted as 1 only
        if (acesSum + nonAcesSum > 21) {
            acesSum = numOfAces;
        }
        return (acesSum + nonAcesSum);
    }

    // Present card hand of the player
    public void presentHand() {
        System.out.println("Card hand of the player: ");
        for (int cardIndex = 0; cardIndex < hand.size(); cardIndex++) {
            System.out.println(hand.get(cardIndex).printCard());
        }
    }

    // Add new card to the player's hand
    public void dealCard(Card card) {
        hand.add(card);
    }


    // Compare two cards by comparing their status values. Return 1 if the current card is larger, -1 if it is lower than or 0 if it is equal
    public int compareTo(Hand otherHand) {
        return this.getStatusValue() == otherHand.getStatusValue() ? 0 : this.getStatusValue() > otherHand.getStatusValue() ? 1 : -1;
    }

    // Check whether card hand including any ace
    public boolean containsAce() {
        for (Card card : hand) {
            if (card.getCardValue() == 1) {
                return true;
            }
        }
        return false;
    }


}
