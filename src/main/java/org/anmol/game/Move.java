package org.anmol.game;

public class Move {
    private static Cell cell;

    public Move(Cell cell) {
        Move.cell = cell;
    }

    public static Cell getCell() {
        return cell;
    }
}

