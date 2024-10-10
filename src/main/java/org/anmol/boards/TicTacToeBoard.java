package org.anmol.boards;

import org.anmol.api.Rule;
import org.anmol.api.RuleSet;
import org.anmol.game.Cell;
import org.anmol.game.GameState;
import org.anmol.game.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements CellBoard {

    String[][] cells = new String[3][3];

    History history = new History();

    public static RuleSet<TicTacToeBoard> getRules() {
        RuleSet<TicTacToeBoard> rules = new RuleSet<>();
        rules.add(new Rule(board -> outerTraversals(board::getSymbol)));
        rules.add(new Rule(board -> outerTraversals((i, j) -> board.getSymbol(j, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        rules.add(new Rule(board -> {
            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(i, j) != null) countOfFilledCells++;
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            }
            return new GameState(false, "-");
        }));
        return rules;
    }

    private static GameState outerTraversals(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState traversal = traverse(j -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    private static GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }

        if (possibleStreak) {
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }

    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] == null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.append(cells[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public TicTacToeBoard move(Move move) {
        TicTacToeBoard board = getCopy();
        board.setCell(move.getCell(), move.getPlayer().getSymbol());
        history.add(new Representation(this));
        return board;
    }

    @Override
    public TicTacToeBoard getCopy() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, ticTacToeBoard.cells[i], 0, 3);
        }
        ticTacToeBoard.history = history;
        return ticTacToeBoard;
    }

    public enum Symbol {
        X("X"), O("O");

        final String marker;

        Symbol(String marker) {
            this.marker = marker;
        }

        public String marker() {
            return marker;
        }
    }
}

class History {
    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIndex) {
        moveIndex = moveIndex - 1;
        int initialSize = boards.size();
        for (int i = 0; i < initialSize - (moveIndex + 1); i++) {
            boards.remove(boards.size() - 1);
        }
        return boards.get(moveIndex);
    }

    public Representation undo() {
        boards.remove(boards.size() - 1);
        return boards.get(boards.size() - 1);
    }

    public void add(Representation representation) {
        boards.add(representation);
    }
}

class Representation {
    String representation;

    public Representation(TicTacToeBoard board) {
        representation = board.toString();
    }

}
