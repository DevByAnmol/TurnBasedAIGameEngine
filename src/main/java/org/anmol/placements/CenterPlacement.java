package org.anmol.placements;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Cell;
import org.anmol.game.Player;
import org.anmol.utils.Utils;

import java.util.Optional;

public class CenterPlacement implements Placement {

    private static CenterPlacement centerPlacement;

    private CenterPlacement() {

    }

    public static synchronized CenterPlacement get() {
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement, CenterPlacement::new);
        return centerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell center = null;
        if (board.getSymbol(1, 1) == null) {
            center = Cell.getCell(1, 1);
        }
        return Optional.ofNullable(center);
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
