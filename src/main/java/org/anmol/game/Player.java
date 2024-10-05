package org.anmol.game;

public class Player {
    private int timeUsedInMillis;
    private User id;
    private String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public Player flip() {
        return new Player(symbol.equals("X") ? "O" : "X");
    }

    public void setTimeTaken(int timeInMillis) {
        timeUsedInMillis += timeInMillis;
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }
}
