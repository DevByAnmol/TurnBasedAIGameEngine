package org.anmol.placements;

import org.anmol.api.RuleEngine;
import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Cell;
import org.anmol.game.Player;

import java.util.Optional;

public interface Placement {
    RuleEngine ruleEngine= new RuleEngine();

    Optional<Cell> place(TicTacToeBoard board, Player player);

    Placement next();
}
