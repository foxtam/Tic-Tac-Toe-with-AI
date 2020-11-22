package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;

public interface Player {
    static Player fromString(String player) {
        switch (player) {
            case "user":
                return Human.INSTANCE;
            case "easy":
                return EasyBot.INSTANCE;
            default:
                throw new IllegalArgumentException();
        }
    }

    Point getPointFrom(Field field);
}
