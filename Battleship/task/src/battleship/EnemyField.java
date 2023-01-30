package battleship;

public class EnemyField extends Field {
    public int cellsHit;

    EnemyField() {
        super();
        cellsHit = 0;
    }

    public void markHit(Coordinates hit) {
        this.cells[hit.getRow()][hit.getColumn()] = "X";
    }

    public void markMiss(Coordinates hit) {
        this.cells[hit.getRow()][hit.getColumn()] = "M";
    }

    public boolean cellWasHitBefore(Coordinates cell) {
        return this.cells[cell.getRow()][cell.getColumn()].equals("~");
    }

}
