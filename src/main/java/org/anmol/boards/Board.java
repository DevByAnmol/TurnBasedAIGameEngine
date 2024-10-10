package org.anmol.boards;

import org.anmol.game.Move;

public interface Board {
    Board move(Move move);

    Board getCopy();
}
