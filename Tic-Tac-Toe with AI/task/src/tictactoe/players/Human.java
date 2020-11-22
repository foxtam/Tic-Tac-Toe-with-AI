package tictactoe.players;

import tictactoe.Field;
import tictactoe.io.Input;
import tictactoe.Point;

public class Human implements Player {

    public static final Human INSTANCE = new Human();

    private Human() {
    }

    @Override
    public Point getPointFrom(Field field) {
        System.out.print("Enter the coordinates: ");
        return Input.getPoint();
    }
}
