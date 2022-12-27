public class Dealer {
    // Create a card hand for the dealer
    Hand dealerHand;

    public Dealer() {
        this.dealerHand = new Hand();
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(Hand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public boolean isDealerBusted() {
        return dealerHand.calculateTotalPoint() > 21;
    }

    // Add one card to the dealer hand
    public void addFront(Card card) {
        dealerHand.dealCard(card);
    }

    public int calculateTotalDealerHand() {
        return dealerHand.calculateTotalPoint();
    }

    public void printHand() {
        dealerHand.presentHand();
    }

    // Add one card to the player hand
    public void dealCardForDealer(Card card) {
        this.getDealerHand().dealCard(card);
    }

    public boolean isBusted() {
        return dealerHand.isBusted();
    }

    //public boolean isBlackJack() {
//        return dealerHand.isBlackjack();


    // Check if there is any Ace in the dealer hand
    public boolean containAce() {
        return dealerHand.containsAce();
    }
}
