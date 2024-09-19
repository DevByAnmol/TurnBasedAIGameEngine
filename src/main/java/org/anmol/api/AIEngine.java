package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.Board;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;

public class AIEngine {

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard board1) {
            Move suggestion;
            if (isStarting(board1, 3)) {
                suggestion = getBasicMove(player, board1);
            } else {
                suggestion = getSmartMove(player, board1);
            }
            if (suggestion != null) return suggestion;
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Move getSmartMove(Player player, TicTacToeBoard board) {
        RuleEngine ruleEngine = new RuleEngine();

        //Victorious Moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.getCopy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move;
                    }
                }
            }
        }

        // Defensive Moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.getCopy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        return getBasicMove(player, board);
    }

    private Move getBasicMove(Player player, TicTacToeBoard board1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), player);
                }
            }
        }
        return null;
    }

    private boolean isStarting(TicTacToeBoard board, int threshold) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }

        return count < threshold;
    }
}
