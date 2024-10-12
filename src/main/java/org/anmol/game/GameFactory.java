package org.anmol.game;

import org.anmol.boards.TicTacToeBoard;

public class GameFactory {
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer) {
        return new Game(null,
                new GameConfig(maxTimePerPlayer != null, maxTimePerPlayer),
                new TicTacToeBoard(),
                0,
                maxTimePerPlayer,
                maxTimePerMove);
    }

    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer, TicTacToeBoard board) {
        return new Game(null,
                new GameConfig(maxTimePerPlayer != null, maxTimePerPlayer),
                board,
                0,
                maxTimePerPlayer,
                maxTimePerMove);
    }
}
