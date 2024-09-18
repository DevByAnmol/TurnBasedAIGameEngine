package org.anmol.boards;

import org.anmol.game.Board;
import org.anmol.game.Cell;

public class TicTacToeBoard extends Board {

    String[][] cells = new String[3][3];

    public String getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }
}
