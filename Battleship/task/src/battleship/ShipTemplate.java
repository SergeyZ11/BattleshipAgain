package battleship;

public enum ShipTemplate {
    AIRCRAFT_CARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private final int length;
    private final String name;

    ShipTemplate(int shipLength, String name) {
        this.name = name;
        this.length = shipLength;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}
