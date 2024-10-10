package org.anmol.api;

import org.anmol.boards.TicTacToeBoard;
import org.anmol.boards.Board;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;
import org.anmol.placements.OffensivePlacement;
import org.anmol.placements.Placement;

import java.util.Optional;

public class AIEngine {

    RuleEngine ruleEngine = new RuleEngine();

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard board1) {
            Cell suggestion;
            int threshold = 3;
            if (countMoves(board1) < threshold) {
                suggestion = getBasicMove(board1);
            } else if (countMoves(board1) < threshold + 1) {
                suggestion = getCellToPlay(player, board1);
            } else {
                suggestion = getOptimalMove(player, board1);
            }
            if (suggestion != null) return new Move(suggestion, player);
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Cell getOptimalMove(Player player, TicTacToeBoard board) {
        Placement placement = OffensivePlacement.get();
        while (placement.next() != null) {
            Optional<Cell> place = placement.place(board, player);
            if (place.isPresent()) {
                return place.get();
            }
            placement = placement.next();
        }
        return null;
    }

    private Cell getCellToPlay(Player player, TicTacToeBoard board) {
        //Victorious Moves
        Cell best = offense(player, board);
        if (best != null) return best;

        // Defensive Moves
        best = defense(player, board);
        if (best != null) return best;

        return getBasicMove(board);
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    private Cell defense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private Cell getBasicMove(TicTacToeBoard board1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i, j) == null) {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }

    private int countMoves(TicTacToeBoard board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }

        return count;
    }
}
