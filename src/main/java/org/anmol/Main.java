package org.anmol;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public Board start() {
        return new Board();
    }

    public void move(Board board, Player player, Move move) {

    }

    public GameResult isComplete(Board board) {
        if (board instanceof TicTacToeBoard board1) {
            String firstCharacter = "-";

            boolean rowComplete = true;
            for (int i = 0; i < 3; i++) {
                rowComplete = true;
                firstCharacter = board1.cells[i][0];
                for (int j = 1; j < 3; j++) {
                    if (!board1.cells[i][j].equals(firstCharacter)) {
                        rowComplete = false;
                        break;
                    }
                }
                if (rowComplete) {
                    break;
                }
            }

            if (rowComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean colComplete = true;
            for (int i = 0; i < 3; i++) {
                colComplete = true;
                firstCharacter = board1.cells[0][i];
                for (int j = 1; j < 3; j++) {
                    if (!board1.cells[j][i].equals(firstCharacter)) {
                        colComplete = false;
                        break;
                    }
                }
                if (colComplete) {
                    break;
                }
            }

            if (colComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean diagonalComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.cells[0][0];
                if (!board1.cells[i][i].equals(firstCharacter)) {
                    diagonalComplete = false;
                    break;
                }
            }

            if (diagonalComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean reverseDiagonalComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.cells[0][2];
                if (!board1.cells[i][2 - i].equals(firstCharacter)) {
                    reverseDiagonalComplete = false;
                    break;
                }
            }
            if (reverseDiagonalComplete) {
                return new GameResult(true, firstCharacter);
            }


            int countOfFilledCells = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.cells[i][j] != null) countOfFilledCells++;
                }
            }

            if (countOfFilledCells == 9) {
                return new GameResult(true, "-");
            } else {
                return new GameResult(false, "-");
            }
        } else {
            return new GameResult(false, "-");
        }
    }
}