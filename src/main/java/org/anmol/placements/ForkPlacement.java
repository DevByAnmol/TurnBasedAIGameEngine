package org.anmol.placements;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Cell;
import org.anmol.game.GameInfo;
import org.anmol.game.Player;
import org.anmol.utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placement {
    private static ForkPlacement forkPlacement;

    private ForkPlacement() {

    }

    public static synchronized ForkPlacement get() {
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, ForkPlacement::new);
        return forkPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if (gameInfo.hasAFork()) {
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return CenterPlacement.get();
    }
}
