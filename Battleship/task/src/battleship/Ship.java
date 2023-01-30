package battleship;

import java.util.ArrayList;

public class Ship {
    private int length;
    private String name;
    //private final ArrayList<Coordinates> occupiedCells;
    private final ArrayList<Coordinates> notHitCells;

    Ship(ShipTemplate template, ShipCoordinates coordinates) {
        this.name = template.getName();
        this.length = template.getLength();

        //occupiedCells = new ArrayList<>(calculateOccupiedCells(coordinates));
        notHitCells = new ArrayList<>(calculateOccupiedCells(coordinates));

    }

    private ArrayList<Coordinates> calculateOccupiedCells(ShipCoordinates shipCoordinates) {
        ArrayList<Coordinates> occupiedCells = new ArrayList<>();
        for (int i = shipCoordinates.getBottomEnd(); i <= shipCoordinates.getTopEnd(); i++) {
            for (int j = shipCoordinates.getLeftEnd(); j <= shipCoordinates.getRightEnd(); j++) {
                occupiedCells.add(new Coordinates(i, j));
            }
        }
        return occupiedCells;
    }

    public ArrayList<Coordinates> getNotHitCells() {
        return notHitCells;
    }
}
