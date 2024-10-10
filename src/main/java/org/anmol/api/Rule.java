package org.anmol.api;

import org.anmol.boards.Board;
import org.anmol.boards.CellBoard;
import org.anmol.game.GameState;

import java.util.function.Function;

public class Rule {
    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }
}
