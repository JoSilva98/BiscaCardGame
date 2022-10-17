package academy.mindswap.Extras.Bisca;

import java.util.ArrayList;

public class Helpers {

    public static String randomizeCard(ArrayList<String> cards) {
        int randomIndex = (int) (Math.random() * cards.size());
        String randomCard = cards.get(randomIndex);
        cards.remove(randomIndex);
        return randomCard;
    }

    public static int getCardPoints(String card) {
        return switch (card.charAt(0)) {
            case 'Q' -> 2;
            case 'J' -> 3;
            case 'K' -> 4;
            case '7' -> 10;
            case 'A' -> 11;
            default -> 0;
        };
    }
}
