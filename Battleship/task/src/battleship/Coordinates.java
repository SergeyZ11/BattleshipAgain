package battleship;

public class Coordinates {
    private Integer row;
    private Integer column;
    Coordinates(Integer r1, Integer c1) {
        this.row = r1;
        this.column = c1;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public String toString() {
        return String.format("%d %d", row, column);
    }

    public boolean equals(Coordinates c) {
        return this.row.equals(c.getRow()) && this.column.equals(c.getColumn());
    }
}
