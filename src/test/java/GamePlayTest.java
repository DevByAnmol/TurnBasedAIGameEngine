import org.anmol.api.GameEngine;
import org.anmol.api.RuleEngine;
import org.anmol.boards.Board;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @BeforeEach
    public void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {2, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColumnWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] firstPlayerMoves = new int[][]{{0, 1}, {1, 1}, {2, 1}};
        int[][] secondPlayerMoves = new int[][]{{1, 0}, {1, 2}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] firstPlayerMoves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][]{{1, 0}, {2, 1}, {1, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForReverseDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] firstPlayerMoves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{1, 0}, {2, 1}, {1, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForSecondPlayerWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {
            Player firstPlayer = new Player("X");
            int row = firstPlayerMoves[next][0];
            int col = firstPlayerMoves[next][1];

            Move firstPlayerMove = new Move(new Cell(row, col), firstPlayer);
            gameEngine.move(board, firstPlayerMove);

            Player secondPlayer = new Player("O");
            if (!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondPlayerMove = new Move(new Cell(sRow, sCol), secondPlayer);
                gameEngine.move(board, secondPlayerMove);
            }

            next++;
        }
    }
}
