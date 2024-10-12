package org.anmol.strategy;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Cell;
import org.anmol.game.Player;

public abstract class Strategy {
    public abstract Cell getOptimalMove(TicTacToeBoard board, Player player);
}
