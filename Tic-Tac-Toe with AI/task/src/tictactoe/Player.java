package tictactoe;

import tictactoe.players.EasyBot;
import tictactoe.players.Human;

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
