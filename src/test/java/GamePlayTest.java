import org.anmol.api.AIEngine;
import org.anmol.api.GameEngine;
import org.anmol.api.RuleEngine;
import org.anmol.game.Board;
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
    AIEngine aiEngine;

    @BeforeEach
    public void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        aiEngine = new AIEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColumnWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] moves = new int[][]{{0, 1}, {1, 1}, {2, 1}};
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] moves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForReverseDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] moves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEngine.start("TicTacToe");

        // make moves
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        playGame(board, moves);
        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] moves) {
        int row, col;
        int next=0;
        while (!ruleEngine.getState(board).isOver()) {
            Player player = new Player("X");
            row = moves[next][0];
            col = moves[next][1];

            Move playerMove = new Move(new Cell(row, col), player);
            gameEngine.move(board, playerMove);

            Player computer = new Player("O");
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }

            next++;
        }
    }
}
