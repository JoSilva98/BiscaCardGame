package academy.mindswap.Extras.Bisca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private final Deck DECK;
    private String trump;
    private final PCPlayer PLAYER;
    private final PCPlayer PC;
    private int delay;

    public Game(PCPlayer player) {
        this.DECK = new Deck();
        this.PLAYER = player;
        this.PC = new PCPlayer("PC");
    }

    public void play() {
        if (chooseTextDelay() != null) return;

        for (int i = 0; i < 7; i++) {
            playersDraw(this.PLAYER, this.PC);
        }
        System.out.println("-----Both players draw 7 cards-----\n");
        this.trump = getCard();
        textDelay();
        chooseFirstToPlay();

        while (this.PLAYER.getCARDS().size() > 0) {
            textDelay();
            System.out.println("Cards left in the deck: " + this.DECK.getNumberOfCards());
            textDelay();
            System.out.println("Trump: " + this.trump + "\n");

            playCards();
            textDelay();
            System.out.println("Trump: " + this.trump + "\n");
            String playerCard = this.PLAYER.getPlayedCard();
            String pcCard = this.PC.getPlayedCard();

            int playerPoints = Helpers.getCardPoints(playerCard);
            int pcPoints = Helpers.getCardPoints(pcCard);

            checkRoundWinner(playerCard, pcCard, playerPoints, pcPoints);

            if (this.DECK.getNumberOfCards() > 0) {
                textDelay();
                System.out.println("\n-----Both players draw a card-----\n");
                if (this.PLAYER.playsFirst()) {
                    playersDraw(this.PLAYER, this.PC);
                } else {
                    playersDraw(this.PC, this.PLAYER);
                }
            }
        }
        textDelay();
        checkWinner();
    }

    public String getCard() {
        return this.DECK.dealCards();
    }

    private void playersDraw(PCPlayer winner, PCPlayer loser) {
        String winnerCard = getCard();
        if (this.DECK.getNumberOfCards() == 0) {
            winner.drawCards(winnerCard);
            textDelay();
            System.out.println(loser.getNAME() + " draws the trump");
            loser.getCARDS().add(this.trump);
        } else {
            winner.drawCards(winnerCard);
            loser.drawCards(getCard());
        }
    }

    private void chooseFirstToPlay() {
        int firstToPlay = (int) (Math.random() * 2);
        if (firstToPlay == 0) {
            System.out.println("-----" + this.PLAYER.getNAME() + " plays first-----\n");
            this.PLAYER.setPlaysFirst(true);
        } else {
            System.out.println("-----PC plays first-----\n");
            this.PC.setPlaysFirst(true);
        }
    }

    private void playCards() {
        String playerCard;
        String pcCard;

        if (this.PLAYER.playsFirst()) {
            textDelay();
            printPlayerCards();
            playerCard = approveCard();
            System.out.println("\n" + this.PLAYER.getNAME() + " played " + playerCard);

            this.PC.playCardPC(playerCard, this.trump);
            pcCard = this.PC.getPlayedCard();
            textDelay();
            System.out.println("PC played " + pcCard);
        } else {
            this.PC.playCardPC();
            pcCard = this.PC.getPlayedCard();
            textDelay();
            System.out.println("PC played " + pcCard + "\n");
            textDelay();
            printPlayerCards();
            playerCard = approveCard(pcCard);
            System.out.println("\nPC played " + pcCard);
            textDelay();
            System.out.println(this.PLAYER.getNAME() + " played " + playerCard);
        }
    }

    private void printPlayerCards() {
        System.out.println("Your cards:\n" + this.PLAYER.getCARDS() + "\n");
    }

    private String approveCard() {
        do {
            this.PLAYER.playCardPlayer();
        } while (this.PLAYER.getPlayedCard() == null);
        return this.PLAYER.getPlayedCard();
    }

    private String approveCard(String pcCard) {
        do {
            this.PLAYER.playCardPlayer(pcCard);
        }
        while (this.PLAYER.getPlayedCard() == null);
        return this.PLAYER.getPlayedCard();
    }

    private void checkRoundWinner(String playerCard, String pcCard, int playerPoints, int pcPoints) {
        int totalPoints = playerPoints + pcPoints;
        if (playerCard.charAt(1) == pcCard.charAt(1)) {
            if (playerPoints > pcPoints) {
                roundWinner(this.PLAYER, this.PC, totalPoints);
            } else if (playerPoints < pcPoints) {
                roundWinner(this.PC, this.PLAYER, totalPoints);
            } else {
                if (playerCard.charAt(0) > pcCard.charAt(0)) {
                    roundWinner(this.PLAYER, this.PC, totalPoints);
                } else {
                    roundWinner(this.PC, this.PLAYER, totalPoints);
                }
            }
        } else if (playerCard.charAt(1) == this.trump.charAt(1)) {
            roundWinner(this.PLAYER, this.PC, totalPoints);
        } else if (pcCard.charAt(1) == this.trump.charAt(1)) {
            roundWinner(this.PC, this.PLAYER, totalPoints);
        } else if (this.PLAYER.playsFirst()) {
            roundWinner(this.PLAYER, this.PC, totalPoints);
        } else {
            roundWinner(this.PC, this.PLAYER, totalPoints);
        }
    }

    private void roundWinner(PCPlayer winner, PCPlayer loser, int totalPoints) {
        winner.increaseScore(totalPoints);
        winner.setPlaysFirst(true);
        loser.setPlaysFirst(false);
        textDelay();
        System.out.println("-----" + winner.getNAME() + " won this round-----\n");
        textDelay();
        System.out.println(this.PLAYER.getNAME() + " score: " + this.PLAYER.getScore());
        textDelay();
        System.out.println(this.PC.getNAME() + " score: " + this.PC.getScore());
    }

    private void checkWinner() {
        if (this.PLAYER.getScore() > this.PC.getScore()) {
            System.out.println(this.PLAYER.getNAME() + " won the game with " + this.PLAYER.getScore() + " points!");
        } else if (this.PLAYER.getScore() < this.PC.getScore()) {
            System.out.println(this.PC.getNAME() + " won the game with " + this.PC.getScore() + " points!");
        } else {
            System.out.println("Draw!");
        }
    }

    private String chooseTextDelay() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.print("Enter the dialog delay (milliseconds): ");
                String checkMessage = bufferedReader.readLine();
                if (checkMessage.equalsIgnoreCase("q"))
                    return "Quit";

                this.delay = Integer.parseInt(checkMessage);
            }
            while (this.delay < 0 || this.delay > 2000);
        } catch (IOException | IllegalArgumentException e) {
            return chooseTextDelay();
        }
        return null;
    }

    private void textDelay() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
