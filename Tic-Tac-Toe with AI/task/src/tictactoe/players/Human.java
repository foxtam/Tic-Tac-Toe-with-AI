package tictactoe.players;

import tictactoe.Field;
import tictactoe.Sign;
import tictactoe.io.Input;
import tictactoe.Point;

public class Human extends Player {

    Human(Sign sign) {
        super(sign);
    }

    @Override
    public Point getPointToMark(Field field) {
        System.out.print("Enter the coordinates: ");
        return Input.getPoint();
    }
}
