package org.anmol;

import org.anmol.api.GameEngine;
import org.anmol.game.Board;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");

        Scanner scanner = new Scanner(System.in);

        // make moves in a loop

        while (!gameEngine.isComplete(board).isOver()) {
            System.out.println("Make your move!");
            System.out.println(board);

            Player player = new Player("X");

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move playerMove = new Move(new Cell(row, col));
            gameEngine.move(board, player, playerMove);

            System.out.println(board);

            Player computer = new Player("O");
            if (!gameEngine.isComplete(board).isOver()) {
                Move computerMove = gameEngine.suggestMove(computer, board);
                gameEngine.move(board, computer, computerMove);
            }
        }

        System.out.println("Game Result : " + gameEngine.isComplete(board).getWinner());
        System.out.println(board);
    }
}
