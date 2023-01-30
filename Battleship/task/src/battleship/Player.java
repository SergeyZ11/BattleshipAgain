package battleship;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Player {

    public static Pattern shotPattern = Pattern.compile("^([A-J]+)(\\d{1,2})$");
    private final String name;
    private Scanner scanner = new Scanner(System.in);

    private GameState won;
    private GameState pass;

    public String getName() {
        return name;
    }

    private final PlayerField ownField;
    private final EnemyField enemyField;

    Player(String name, GameState won, GameState pass) {
        System.out.println(name + ", place your ships on the game field");
        this.name = name;
        this.won = won;
        this.pass = pass;
        ownField = new PlayerField();
        ownField.setupShips();
        enemyField = new EnemyField();
    }

    public ShotResult takeAShot(Coordinates target) {
        return ownField.takeAShot(target);
    }

    public GameState makeAShot(Player targetPlayer) {
        enemyField.printField();
        System.out.println("----------------------");
        ownField.printField();
        System.out.println(this.name + ", it's your turn: ");

        boolean shotIsValid = false;
        ShotResult shotResult = null;
        Coordinates shot = null;
        GameState turnResult = null;

        while (!shotIsValid) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().trim();
            Matcher shotMatcher = shotPattern.matcher(userInput);
            if (shotMatcher.find()) {
                shot = new Coordinates(Field.ROWS.indexOf(shotMatcher.group(1)), Integer.parseInt(shotMatcher.group(2)));
                if (shot.getRow() > 0 && shot.getRow() < Field.SIZE && shot.getColumn() > 0 && shot.getColumn() < Field.SIZE) {
                    shotIsValid = true;
                    shotResult = targetPlayer.takeAShot(shot);
                    System.out.println(shotResult.getMessage());
                    if (shotResult.equals(ShotResult.HIT) || shotResult.equals(ShotResult.SANK)) {
                        if (enemyField.cellWasHitBefore(shot)) {
                            enemyField.cellsHit++;
                        }
                        enemyField.markHit(shot);
                    } else if (shotResult.equals(ShotResult.MISSED)) {
                        enemyField.markMiss(shot);
                    } else {
                        System.out.println("Error! You entered the wrong coordinates! Try again: ");
                    }
                } else {
                    System.out.println("Error! You entered the wrong coordinates! Try again: ");
                }
            }

            if (enemyField.cellsHit == targetPlayer.ownField.getCellsToHit()) {
                turnResult = this.won;
            } else {
                System.out.println("Press Enter and pass the move to another player");
                scanner.nextLine();
                turnResult = this.pass;
            }
        }
        return turnResult;
    }
}