package org.anmol;

import org.anmol.api.AIEngine;
import org.anmol.api.GameEngine;
import org.anmol.api.RuleEngine;
import org.anmol.boards.Board;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        Board board = gameEngine.start("TicTacToe");

        Scanner scanner = new Scanner(System.in);

        // make moves in a loop

        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move!");
            System.out.println(board);

            Player player = new Player("X");

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move playerMove = new Move(new Cell(row, col), player);
            gameEngine.move(board, playerMove);

            System.out.println(board);

            Player computer = new Player("O");
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }

        System.out.println("Game Result : " + ruleEngine.getState(board).getWinner());
        System.out.println(board);
    }
}
