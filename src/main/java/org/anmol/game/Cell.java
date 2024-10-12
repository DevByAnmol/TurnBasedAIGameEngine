package org.anmol.game;

public class Cell {
    static Cell[][] cells = new Cell[][]{
            {new Cell(0, 0), new Cell(0, 1), new Cell(0, 2)},
            {new Cell(1, 0), new Cell(1, 1), new Cell(1, 2)},
            {new Cell(2, 0), new Cell(2, 1), new Cell(2, 2)}
    };

    int row, col;

    private Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Cell getCell(int row, int col) {
        if (cells[row][col] == null) {
            cells[row][col] = new Cell(row, col);
        }
        return cells[row][col];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
