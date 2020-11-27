package tictactoe.players;

import tictactoe.io.Input;
import tictactoe.Point;

public class Human extends Player {

    Human(char sign) {
        super(sign);
    }

    @Override
    public Point getPointToMark() {
        return Input.getPoint();
    }

    @Override
    public String getPlayerMessage() {
        return "Enter the coordinates: ";
    }
}
