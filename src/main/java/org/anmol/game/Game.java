package org.anmol.game;

import org.anmol.boards.Board;

public class Game {
    Player winner;
    private GameConfig gameConfig;
    private Board board;
    private Integer lastMoveTimeInMillis;
    private Integer maxTimePerPlayer;
    private Integer maxTimePerMove;

    public Game(Player winner, GameConfig gameConfig, Board board, Integer maxTimePerPlayer, Integer lastMoveTimeInMillis, Integer maxTimePerMove) {
        this.winner = winner;
        this.gameConfig = gameConfig;
        this.board = board;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.lastMoveTimeInMillis = lastMoveTimeInMillis;
        this.maxTimePerMove = maxTimePerMove;
    }

    public void move(Move move, int timestampInMillis) {
        int timeTakenSinceLastMove = timestampInMillis - lastMoveTimeInMillis;
        move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
        if (gameConfig.timed) {
            moveForTimedGame(move, timeTakenSinceLastMove);
        } else {
            board.move(move);
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        int currentTime, endTime;
        if (gameConfig.timePerMove != null) {
            currentTime = timeTakenSinceLastMove;
            endTime = maxTimePerMove;
        } else {
            currentTime = move.getPlayer().getTimeUsedInMillis();
            endTime = maxTimePerPlayer;
        }
        if (currentTime < endTime) {
            board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }

    public void setConfig(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }
}
