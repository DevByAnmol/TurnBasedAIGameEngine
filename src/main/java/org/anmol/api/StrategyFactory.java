package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Player;

public class StrategyFactory {

    private int countMoves(TicTacToeBoard board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public Strategy getStrategy(TicTacToeBoard board, Player player) {
        Strategy strategy = null;
        int threshold = 3;
        if (countMoves(board) < threshold) {
            strategy = new BasicStrategy();
        } else if (countMoves(board) < threshold + 1) {
            strategy = new SmartStrategy();
        } else if (player.getTimeUsedInMillis() > 100000) {
            strategy = new BasicStrategy();
        } else {
            strategy = new OptimalStrategy();
        }

        return strategy;
    }
}
