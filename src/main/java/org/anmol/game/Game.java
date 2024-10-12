package org.anmol.game;

import org.anmol.api.RuleEngine;
import org.anmol.boards.Board;

public class Game {
    Player winner;
    private final GameConfig gameConfig;
    private Board board;
    private final Integer lastMoveTimeInMillis;
    private final Integer maxTimePerPlayer;
    private final Integer maxTimePerMove;
    private final RuleEngine ruleEngine = new RuleEngine();

    public Game(Player winner, GameConfig gameConfig, Board board, Integer lastMoveTimeInMillis, Integer maxTimePerPlayer, Integer maxTimePerMove) {
        this.winner = winner;
        this.gameConfig = gameConfig;
        this.board = board;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.lastMoveTimeInMillis = lastMoveTimeInMillis;
        this.maxTimePerMove = maxTimePerMove;
    }

    public void move(Move move, int timestampInMillis) {
        if (winner == null) {
            int timeTakenSinceLastMove = timestampInMillis - lastMoveTimeInMillis;
            move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
            if (gameConfig.timed) {
                moveForTimedGame(move, timeTakenSinceLastMove);
            } else {
                board = board.move(move);
            }
            if (winner == null && ruleEngine.getState(board).isOver()) {
                winner = move.getPlayer();
            }
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        if (move.getPlayer().getTimeUsedInMillis() < maxTimePerPlayer
                && (gameConfig.timePerMove == null || timeTakenSinceLastMove < maxTimePerMove)) {
            board = board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }

    public Player getWinner() {
        return winner;
    }
}
