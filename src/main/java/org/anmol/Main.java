package org.anmol;

import org.anmol.api.AIEngine;
import org.anmol.commands.builder.SMSCommandBuilder;
import org.anmol.events.Event;
import org.anmol.services.EmailService;
import org.anmol.api.GameEngine;
import org.anmol.api.RuleEngine;
import org.anmol.boards.Board;
import org.anmol.commands.builder.EmailCommandBuilder;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;
import org.anmol.services.SMSService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        Board board = gameEngine.start("TicTacToe");
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();
        Scanner scanner = new Scanner(System.in);

        Player player = new Player("X");
        Player computer = new Player("O");

        if (player.getUser().activeAfter(10, TimeUnit.DAYS)) {
            emailService.send(new EmailCommandBuilder()
                    .user(player.getUser())
                    .message("We are glad you are back!")
                    .build()
            );
            smsService.send(new SMSCommandBuilder()
                    .user(player.getUser())
                    .message("We are glad you are back!")
                    .build());
        }

        // make moves in a loop
        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move!");
            System.out.println(board);

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move playerMove = new Move(Cell.getCell(row, col), player);
            gameEngine.move(board, playerMove);

            System.out.println(board);

            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }


        if (ruleEngine.getState(board).getWinner().equals(player.getSymbol())) {
            emailService.send(new EmailCommandBuilder()
                    .user(player.getUser())
                    .message("Congratulations on the win!")
                    .build());
            smsService.send(new SMSCommandBuilder()
                    .user(player.getUser())
                    .message("Congratulations on the win!")
                    .build());
        }

        System.out.println("Game Result : " + ruleEngine.getState(board).getWinner());
        System.out.println(board);
    }
}
