package org.anmol.placements;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;
import org.anmol.utils.Utils;

import java.util.Optional;

public class OffensivePlacement implements Placement {
    private static OffensivePlacement offensivePlacement;

    private OffensivePlacement() {
    }

    public static synchronized OffensivePlacement get() {
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, OffensivePlacement::new);
        return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(offense(board, player));
    }

    @Override
    public Placement next() {
        return DefensivePlacement.get();
    }

    private Cell offense(TicTacToeBoard board, Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(Cell.getCell(i, j), player);
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }
}
