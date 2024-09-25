package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Board;
import org.anmol.game.GameState;

import java.util.function.BiFunction;

public class RuleEngine {

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard board1) {
            String firstCharacter = "-";

            GameState rowWin = isVictory((i, j) -> board1.getSymbol(i, j));
            if (rowWin != null) return rowWin;

            GameState colWin = isVictory((i, j) -> board1.getSymbol(j, i));
            if (colWin != null) return colWin;

            firstCharacter = board1.getSymbol(0, 0);
            boolean diagonalComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getSymbol(i, i))) {
                    diagonalComplete = false;
                    break;
                }
            }

            if (diagonalComplete) {
                return new GameState(true, firstCharacter);
            }

            firstCharacter = board1.getSymbol(0, 2);
            boolean reverseDiagonalComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getSymbol(i, 2 - i))) {
                    reverseDiagonalComplete = false;
                    break;
                }
            }
            if (reverseDiagonalComplete) {
                return new GameState(true, firstCharacter);
            }


            int countOfFilledCells = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getSymbol(i, j) != null) countOfFilledCells++;
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            } else {
                return new GameState(false, "-");
            }
        } else {
            return new GameState(false, "-");
        }
    }

    private GameState isVictory(BiFunction<Integer, Integer, String> next) {
        for (int i = 0; i < 3; i++) {
            boolean possibleStreak = true;

            for (int j = 1; j < 3; j++) {
                if (next.apply(i, j) == null || !next.apply(i, 0).equals(next.apply(i, j))) {
                    possibleStreak = false;
                    break;
                }
            }

            if (possibleStreak) {
                return new GameState(true, next.apply(i, 0));
            }
        }
        return null;
    }
}
