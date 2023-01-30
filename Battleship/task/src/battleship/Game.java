package battleship;

import java.util.Scanner;

public class Game {
    private GameState gameState = GameState.PLAYER_ONE_TURN;
    private final Player playerOne;
    private final Player playerTwo;

    Game() {
        playerOne = new Player("Player 1", GameState.PLAYER_ONE_WINS, GameState.PLAYER_TWO_TURN);
        playerTwo = new Player("Player 2", GameState.PLAYER_TWO_WINS, GameState.PLAYER_ONE_TURN);
        start();
    }

    public void start() {
        System.out.println("The game starts!");
        do {
            if (gameState.equals(GameState.PLAYER_ONE_TURN)) {
                gameState = playerOne.makeAShot(playerTwo);
            } else {
                gameState = playerTwo.makeAShot(playerOne);
            }
        } while (!(gameState.equals(GameState.PLAYER_ONE_WINS) || gameState.equals(GameState.PLAYER_TWO_WINS)));

        if (gameState.equals(GameState.PLAYER_ONE_WINS)) {
            System.out.printf("%s sank the last ship and won. Congratulations!", playerOne.getName());
        } else {
            System.out.printf("%s sank the last ship and won. Congratulations!", playerTwo.getName());
        }

    }
}
