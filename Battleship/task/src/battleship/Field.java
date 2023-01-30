package battleship;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Field {
    public static final int SIZE = 11;
    public static final List<String> ROWS = Arrays.asList(" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
    protected String[][] cells = new String[SIZE][SIZE];

    Field() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0 && j > 0) {
                    cells[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    cells[i][j] = ROWS.get(i);
                } else {
                    cells[i][j] = "~";
                }
            }
        }
    }

    public void printField() {
        for (String[] row : this.cells) {
            System.out.println(String.join(" ", row));
        }
    }
}
