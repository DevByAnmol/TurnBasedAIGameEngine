package org.anmol.strategy;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Cell;
import org.anmol.game.Player;

public class BasicStrategy extends Strategy {
    @Override
    public Cell getOptimalMove(TicTacToeBoard board, Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    return Cell.getCell(i, j);
                }
            }
        }
        return null;
    }
}
