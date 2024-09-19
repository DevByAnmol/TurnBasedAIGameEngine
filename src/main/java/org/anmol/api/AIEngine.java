package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Board;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;

public class AIEngine {

    public Move suggestMove(Player computer, Board board) {
        if (board instanceof TicTacToeBoard board1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) == null) {
                        return new Move(new Cell(i, j), computer);
                    }
                }
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
