import java.security.SecureRandom;

public class Deck {
    int numOfCards = 0;
    Card deck;
    public static final String[] VALUES = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    public static final String[] SUITS = new String[]{"C", "D", "H", "S"};
    Card head = null;

    // Request whether the player want to create a blank or standard deck
    public Deck(boolean emptyDeck) {
        if (emptyDeck) {
            return;
        } else {
            generateStandardDeck();
        }
    }

    private void generateStandardDeck() {
        for (int indexValue = 0; indexValue < VALUES.length; indexValue++) {
            for (int indexSuit = 0; indexSuit < SUITS.length; indexSuit++) {
                Card card = new Card(VALUES[indexValue], SUITS[indexSuit], null);
                addFront(card);
            }
        }
    }

    // Add given card to the front of the deck
    private void addFront(Card newCard) {
        if (head == null) {
            head = newCard;
        } else {
            newCard.setLink(head);
            head = newCard;
        }
        numOfCards++;
    }

    /* Add card to the end of the deck
    private void addLast(Card newCard) {
        if (head == null) {
            head = newCard;
        } else {
            Card currentNode = head;
            while (currentNode.getLink() != null) {
                currentNode = currentNode.getLink();
            }
            currentNode.setLink(newCard);
        }
        numOfCards++;
    }*/

    // Remove a card from the deck at given position
    public Card removeAtIndex(int position) {
        if (head == null) {
            return null;
        }

        // Decrement the number of cards by 1
        numOfCards--;
        if (position == 0) {
            Card temp = head;
            head = head.getLink();
            temp.setLink(null);
            return temp;
        }

        // Traverse to the node before the wanted node
        Card temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.getLink();
        }
        // if the node before or its next is null, then return null
        if (temp == null || temp.getLink() == null) {
            return null;
        }

        Card nextOfNext = temp.getLink().getLink();
        Card removedNode = temp.getLink();
        removedNode.setLink(null);
        temp.setLink(nextOfNext);
        return removedNode;
    }

    // Remove the first card
    public Card removeFirstCard() {
        if (head == null) {
            return null;
        }
        Card firstCard = head;
        head = head.getLink();
        firstCard.setLink(null);
        numOfCards--;
        return firstCard;
    }


    // Shuffle the deck by removing a card at random position then add it back to the front of the deck
    public void shuffleDeck() {
        for (int i = 0; i < 1000; i++) {
            SecureRandom secureRandom = new SecureRandom();
            int randomPosition = Math.abs(secureRandom.nextInt()) % numOfCards;

            Card removedCard = removeAtIndex(randomPosition);
            addFront(removedCard);
        }
    }

    public void printDeck() {
        Card cursor = head;
        if (head == null) {
            return;
        }

        while (cursor != null) {

            System.out.println(cursor.printCard());
            cursor = cursor.getLink();
        }
    }

}
