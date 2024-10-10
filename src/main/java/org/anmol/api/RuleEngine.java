package org.anmol.api;

import org.anmol.boards.Board;
import org.anmol.boards.CellBoard;
import org.anmol.boards.TicTacToeBoard;
import org.anmol.game.*;
import org.anmol.placements.DefensivePlacement;
import org.anmol.placements.OffensivePlacement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.anmol.boards.TicTacToeBoard.Symbol;
import static org.anmol.boards.TicTacToeBoard.getRules;

public class RuleEngine {
    Map<String, RuleSet<TicTacToeBoard>> ruleMap = new HashMap<>();

    public RuleEngine() {
        ruleMap.put(TicTacToeBoard.class.getName(), getRules());
    }

    public GameInfo getInfo(CellBoard board) {
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            GameState gameState = getState(ticTacToeBoard);
            for (Symbol symbol : Symbol.values()) {
                Player player = new Player(symbol.marker());
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTacToeBoard.getSymbol(i, j) == null) {
                            TicTacToeBoard b = ticTacToeBoard.move(new Move(new Cell(i, j), player));
                            // force opponent to make a defensive move
                            // we can still win after that move
                            DefensivePlacement defense = DefensivePlacement.get();
                            Optional<Cell> defensiveCell = defense.place(b, player.flip());
                            if (defensiveCell.isPresent()) {
                                b = ticTacToeBoard.move(new Move(defensiveCell.get(), player));
                                OffensivePlacement offense = OffensivePlacement.get();
                                Optional<Cell> offensiveCell = offense.place(b, player);
                                if (offensiveCell.isPresent()) {
                                    return new GameInfoBuilder()
                                            .isOver(gameState.isOver())
                                            .winner(gameState.getWinner())
                                            .hasFork(true)
                                            .forkCell(new Cell(i, j))
                                            .player(player.flip())
                                            .build();
                                }
                            }
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard board1) {
            for (Rule r : ruleMap.get(TicTacToeBoard.class.getName())) {
                GameState gameState = r.condition.apply(board1);
                if (gameState.isOver()) {
                    return gameState;
                }
            }
            return new GameState(false, "-");
        } else {
            throw new IllegalArgumentException();
        }
    }


}

