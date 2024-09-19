package org.anmol.game;

public class Player {
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
}
