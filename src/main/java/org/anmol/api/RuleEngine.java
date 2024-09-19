package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Board;
import org.anmol.game.GameState;

public class RuleEngine {

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard board1) {
            String firstCharacter = "-";

            boolean rowComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getSymbol(i, 0);
                rowComplete = firstCharacter != null;
                if (firstCharacter != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getSymbol(i, j))) {
                            rowComplete = false;
                            break;
                        }
                    }
                }
                if (rowComplete) {
                    break;
                }
            }

            if (rowComplete) {
                return new GameState(true, firstCharacter);
            }

            boolean colComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getSymbol(0, i);
                colComplete = firstCharacter != null;
                if (firstCharacter != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getSymbol(j, i))) {
                            colComplete = false;
                            break;
                        }
                    }
                }
                if (colComplete) {
                    break;
                }
            }

            if (colComplete) {
                return new GameState(true, firstCharacter);
            }

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
}
