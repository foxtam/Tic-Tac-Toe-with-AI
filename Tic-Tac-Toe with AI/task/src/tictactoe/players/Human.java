package tictactoe.players;

import tictactoe.Field;
import tictactoe.Sign;
import tictactoe.io.Input;
import tictactoe.Point;

public class Human extends Player {

    Human(char sign) {
        super(sign);
    }

    @Override
    public Point getPointToMark(Field field) {
        return Input.getPoint();
    }

    @Override
    public String getPlayerMessage() {
        return "Enter the coordinates: ";
    }
}
