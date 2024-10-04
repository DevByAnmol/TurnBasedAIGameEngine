package org.anmol.game;

public class GameInfo {
    private boolean hasFork;
    private String winner;
    private boolean isOver;
    private Player player;
    private int numberOfMoves;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, int numberOfMoves) {
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.numberOfMoves = numberOfMoves;
    }
}
