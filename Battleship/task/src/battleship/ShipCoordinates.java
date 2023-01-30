package battleship;

public class ShipCoordinates {
    private final int bottomEnd;
    private final int topEnd;
    private final int leftEnd;
    private final int rightEnd;
    private final boolean isShapeValid;
    private final int length;

    ShipCoordinates(Coordinates start, Coordinates end) {
        this.bottomEnd = Math.min(start.getRow(), end.getRow());
        this.topEnd = Math.max(start.getRow(), end.getRow());
        this.leftEnd = Math.min(start.getColumn(), end.getColumn());
        this.rightEnd = Math.max(start.getColumn(), end.getColumn());
        this.isShapeValid = bottomEnd == topEnd ^ leftEnd == rightEnd;
        this.length = leftEnd == rightEnd ? topEnd - bottomEnd + 1 : rightEnd - leftEnd + 1;
    }

    public int getBottomEnd() {
        return bottomEnd;
    }

    public int getTopEnd() {
        return topEnd;
    }

    public int getLeftEnd() {
        return leftEnd;
    }

    public int getRightEnd() {
        return rightEnd;
    }

    public boolean areValid(int templateLength) {
        return isShapeValid && this.length == templateLength;
    }

    public int getLength() {
        return length;
    }
}
