package academy.mindswap.Extras.Bisca;

import java.util.ArrayList;

public class Deck {
    private final ArrayList<String> CARDS;
    private int numberOfCards;

    public Deck() {
        this.CARDS = initializeDeck();
        this.numberOfCards = this.CARDS.size();
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    private ArrayList<String> initializeDeck() {
        String[] suits = new String[]{"S", "H", "C", "D"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "Q", "J", "K"};

        ArrayList<String> deck = new ArrayList<>();
        for (String value : values) {
            for (String suit : suits) {
                deck.add(value + suit);
            }
        }
        return deck;
    }

    public String dealCards() {
        this.numberOfCards--;
        return Helpers.randomizeCard(this.CARDS);
    }
}
