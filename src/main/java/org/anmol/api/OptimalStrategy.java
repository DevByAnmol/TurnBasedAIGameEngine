package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Cell;
import org.anmol.game.Player;
import org.anmol.placements.OffensivePlacement;
import org.anmol.placements.Placement;

import java.util.Optional;

public class OptimalStrategy extends Strategy {
    @Override
    public Cell getOptimalMove(TicTacToeBoard board, Player player) {
        Placement placement = OffensivePlacement.get();
        while (placement.next() != null) {
            Optional<Cell> place = placement.place(board, player);
            if (place.isPresent()) {
                return place.get();
            }
            placement = placement.next();
        }
        return null;
    }
}
