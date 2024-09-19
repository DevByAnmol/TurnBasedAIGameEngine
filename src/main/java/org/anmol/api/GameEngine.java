package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.*;

public class GameEngine {

    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Move move) {
        if (board instanceof TicTacToeBoard board1) {
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }


}