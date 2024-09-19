package org.anmol.game;

public interface Board {
    void move(Move move);

    Board getCopy();
}
