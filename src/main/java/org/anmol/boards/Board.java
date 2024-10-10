package org.anmol.boards;

import org.anmol.game.Move;

public interface Board {
    void move(Move move);

    Board getCopy();
}
