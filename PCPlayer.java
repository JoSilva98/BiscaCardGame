package academy.mindswap.Extras.Bisca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PCPlayer {
    private final ArrayList<String> CARDS = new ArrayList<>();
    private final String NAME;
    private int score;
    private boolean playsFirst;
    private String playedCard;

    public PCPlayer(String name) {
        this.NAME = name;
        this.playsFirst = false;
        this.playedCard = null;
    }

    public void drawCards(String card) {
        this.CARDS.add(card);
    }

    public ArrayList<String> getCARDS() {
        return CARDS;
    }

    public String getNAME() {
        return NAME;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int increase) {
        this.score += increase;
    }

    public boolean playsFirst() {
        return playsFirst;
    }

    public void setPlaysFirst(boolean playsFirst) {
        this.playsFirst = playsFirst;
    }

    public String getPlayedCard() {
        return playedCard;
    }

    public void playCardPC() {
        this.playedCard = Helpers.randomizeCard(this.CARDS);
    }

    public void playCardPC(String playerCard, String trump) {
        this.playedCard = null;
        ArrayList<String> sameSuitsCards = new ArrayList<>();
        ArrayList<String> trumpSuitCards = new ArrayList<>();

        for (String card : this.CARDS) {
            if (card.charAt(1) == playerCard.charAt(1)) sameSuitsCards.add(card);
            if (card.charAt(1) == trump.charAt(1)) trumpSuitCards.add(card);
        }
        this.playedCard = pcChooseCard(playerCard, sameSuitsCards, trumpSuitCards);
    }

    private String pcChooseCard(String playerCard, ArrayList<String> sameSuitsCards, ArrayList<String> trumpSuitCards) {
        String cardToPlay;
        if (sameSuitsCards.size() != 0) {
            for (String card : sameSuitsCards) {
                switch (card.charAt(0)) {
                    case 'A', '7' -> {
                        this.CARDS.remove(card);
                        return card;
                    }
                }
            }
            cardToPlay = sameSuitsCards.get((int) (Math.random() * sameSuitsCards.size()));
            this.CARDS.remove(cardToPlay);
            return cardToPlay;
        } else if (trumpSuitCards.size() != 0) {
            switch (playerCard.charAt(0)) {
                case 'A', '7' -> {
                    cardToPlay = trumpSuitCards.get((int) (Math.random() * trumpSuitCards.size()));
                    this.CARDS.remove(cardToPlay);
                    return cardToPlay;
                }
            }
        }
        return Helpers.randomizeCard(this.CARDS);
    }

    public void playCardPlayer() {
        this.playedCard = null;
        System.out.print("Play a card: ");
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String playedCard = bufferedReader.readLine().toUpperCase();

            if (this.CARDS.contains(playedCard)) {
                this.CARDS.remove(playedCard);
                this.playedCard = playedCard;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playCardPlayer(String pcCard) {
        this.playedCard = null;
        System.out.print("Play a card: ");
        int count = 0;
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String playedCard = bufferedReader.readLine().toUpperCase();

            for (String card : this.CARDS) {
                if (card.charAt(1) == pcCard.charAt(1)) count++;
            }

            for (String card : this.CARDS) {
                if (count > 0 && playedCard.charAt(1) == pcCard.charAt(1) && card.equals(playedCard)) {
                    this.playedCard = card;
                    this.CARDS.remove(card);
                    return;
                } else if (count == 0 && playedCard.equals(card)) {
                    this.playedCard = card;
                    this.CARDS.remove(card);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
