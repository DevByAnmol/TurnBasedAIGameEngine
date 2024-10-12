package org.anmol.game;

import java.util.concurrent.TimeUnit;

public class Player {
    private int timeUsedInMillis;
    private User user;
    private String symbol;

    public Player(String symbol) {
        this.user = new User();
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

    public User getUser() {
        return user;
    }


}
