package battleship;

public enum ShotResult{
    HIT("You hit a ship!"), MISSED("You missed!"), SANK("You sank the ship!");

    private String message;

    ShotResult(String m) {
        this.message = m;
    }

    public String getMessage() {
        return message;
    }
}
