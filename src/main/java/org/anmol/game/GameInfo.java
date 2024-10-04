package org.anmol.game;

public class GameInfo {
    private boolean hasFork;
    private String winner;
    private boolean isOver;
    private Player player;

    public GameInfo(GameState gameState, boolean hasFork, Player player) {
        this.isOver = gameState.isOver();
        this.winner = gameState.getWinner();
        this.hasFork = hasFork;
        this.player = player;
    }
}
