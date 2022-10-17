package academy.mindswap.Extras.Bisca;

public class Main {
    public static void main(String[] args) {
        PCPlayer player = new PCPlayer("Player");
        Game game = new Game(player);

        game.play();
    }
}
