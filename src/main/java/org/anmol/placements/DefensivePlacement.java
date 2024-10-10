package org.anmol.placements;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;
import org.anmol.utils.Utils;

import java.util.Optional;

public class DefensivePlacement implements Placement {
    private static DefensivePlacement defensivePlacement;

    private DefensivePlacement() {

    }

    public static synchronized DefensivePlacement get() {
        defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement, DefensivePlacement::new);
        return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(defense(board, player));
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
    }

    private Cell defense(TicTacToeBoard board, Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

}
