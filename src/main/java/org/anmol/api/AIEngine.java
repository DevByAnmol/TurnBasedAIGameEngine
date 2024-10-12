package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.boards.Board;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;

public class AIEngine {

    StrategyFactory strategyFactory = new StrategyFactory();

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard board1) {
            Cell suggestion = strategyFactory.getStrategy(board1, player).getOptimalMove(board1, player);
            if (suggestion != null) return new Move(suggestion, player);
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
