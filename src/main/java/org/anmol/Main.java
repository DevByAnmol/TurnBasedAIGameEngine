package org.anmol;

import org.anmol.api.AIEngine;
import org.anmol.api.GameEngine;
import org.anmol.api.RuleEngine;
import org.anmol.boards.Board;
import org.anmol.commands.implementations.EmailCommand;
import org.anmol.commands.implementations.SMSCommand;
import org.anmol.events.Event;
import org.anmol.events.EventBus;
import org.anmol.events.Subscriber;
import org.anmol.game.Cell;
import org.anmol.game.Move;
import org.anmol.game.Player;
import org.anmol.services.EmailService;
import org.anmol.services.SMSService;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        Board board = gameEngine.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();
        EventBus eventBus = new EventBus();
        eventBus.subscribe(new Subscriber(event -> emailService.send(new EmailCommand(event))));
        eventBus.subscribe(new Subscriber(event -> smsService.send(new SMSCommand(event))));

        Player player = new Player("X");
        Player computer = new Player("O");

        if (player.getUser().activeAfter(10, TimeUnit.DAYS)) {
            eventBus.publish(new Event(player.getUser(), "Congratulations", "", "ACTIVITY"));
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
            eventBus.publish(new Event(player.getUser(), "Congratulations", "", "WIN"));
        }

        System.out.println("Game Result : " + ruleEngine.getState(board).getWinner());
        System.out.println(board);
    }
}
