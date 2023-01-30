package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerField extends Field {
    public static Pattern shipPattern = Pattern.compile("^([A-J]+)(\\d{1,2}) ([A-J]+)(\\d{1,2})$");
    private final ArrayList<Ship> ships = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public int getCellsToHit() {
        return cellsToHit;
    }

    private int cellsToHit;

    public void setupShips() {
        printField();
        for (ShipTemplate template : ShipTemplate.values()) {
            setupShip(template);
            cellsToHit += template.getLength();
        }
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    private void setupShip(ShipTemplate shipTemplate) {
        System.out.printf("Enter the coordinates of the %s (%d cells): ",
                shipTemplate.getName(),
                shipTemplate.getLength());
        boolean isShipPlaced = false;
        while (!isShipPlaced) {
            String userInput = scanner.nextLine();
            Matcher shipPlacementMatcher = shipPattern.matcher(userInput);

            if (shipPlacementMatcher.find()) {
                ShipCoordinates inputCoordinates = new ShipCoordinates(
                        new Coordinates(ROWS.indexOf(shipPlacementMatcher.group(1)), Integer.parseInt(shipPlacementMatcher.group(2))),
                        new Coordinates(ROWS.indexOf(shipPlacementMatcher.group(3)), Integer.parseInt(shipPlacementMatcher.group(4)))
                );
                if (inputCoordinates.areValid(shipTemplate.getLength())) {
                    if (shipCanBePlaced(inputCoordinates)) {
                        Ship createdShip = new Ship(shipTemplate, inputCoordinates);
                        putShipOnTheField(inputCoordinates, shipTemplate.getName());
                        ships.add(createdShip);
                        isShipPlaced = true;
                        printField();
                    } else {
                        System.out.print("Error! Coordinates are occupied! Try again: ");
                    }
                } else {
                    System.out.printf("Error! Wrong length of the %s! Try again: ", shipTemplate.getName());
                }
            } else {
                System.out.printf("Error! Wrong length of the %s! Try again: ", shipTemplate.getName());
            }
        }
    }

    private boolean shipCanBePlaced(ShipCoordinates shipCoordinates) {
        int collisionLeftBorderIndex = shipCoordinates.getLeftEnd() - 1;
        int collisionRightBorderIndex = shipCoordinates.getRightEnd() + 1;
        int collisionBottomBorderIndex = shipCoordinates.getBottomEnd() - 1;
        int collisionTopBorderIndex = shipCoordinates.getTopEnd() + 1;


        boolean canBePlaced = true;
        for (int i = collisionBottomBorderIndex; i <= collisionTopBorderIndex; i++) {
            for (int j = collisionLeftBorderIndex; j <= collisionRightBorderIndex; j++) {
                if (i > 0 && i < SIZE && j > 0 && j < SIZE && !this.cells[i][j].equals("~")) {
                    canBePlaced = false;
                }
            }
        }
        return canBePlaced;
    }

    private void putShipOnTheField(ShipCoordinates shipCoordinates, String name) {
        ArrayList<int[]> sc = new ArrayList<>();
        for (int i = shipCoordinates.getBottomEnd(); i <= shipCoordinates.getTopEnd(); i++) {
            for (int j = shipCoordinates.getLeftEnd(); j <= shipCoordinates.getRightEnd(); j++) {
                this.cells[i][j] = "O";
                sc.add(new int[]{i, j});
            }
        }
    }

    public ShotResult takeAShot(Coordinates target) {
        ShotResult result = null;
        System.out.println(target);
        System.out.println(cells[target.getRow()][target.getColumn()]);
        if (cells[target.getRow()][target.getColumn()].equals("~") || cells[target.getRow()][target.getColumn()].equals("M")) {
            cells[target.getRow()][target.getColumn()] = "M";
            result = ShotResult.MISSED;
        } else if (cells[target.getRow()][target.getColumn()].equals("O")) {
            cells[target.getRow()][target.getColumn()] = "X";
            for (Ship ship : ships) {
                for (Coordinates coordinate : new ArrayList<>(ship.getNotHitCells())) {
                    if (coordinate.equals(target)) {
                        ship.getNotHitCells().remove(coordinate);
                        if (ship.getNotHitCells().size() == 0) {
                            result = ShotResult.SANK;
                        } else {
                            result = ShotResult.HIT;
                        }
                    }
                }
            }
        } else if (cells[target.getRow()][target.getColumn()].equals("X")) {
            result = ShotResult.HIT;

        }
        return result;
    }
}
