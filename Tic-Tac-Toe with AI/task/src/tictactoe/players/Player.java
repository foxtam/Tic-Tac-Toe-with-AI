package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;
import tictactoe.Sign;

public abstract class Player {

    private final char sign;

    protected Player(char sign) {
        this.sign = sign;
    }

    public static Player fromStringWithSign(String player, char sign) {
        switch (player) {
            case "user":
                return new Human(sign);
            case "easy":
                return new EasyBot(sign);
            case "medium":
                return new MediumBot(sign);
            default:
                throw new IllegalArgumentException();
        }
    }

    public char getSign() {
        return sign;
    }

    public abstract Point getPointToMark(Field field);

    public abstract String getPlayerMessage();
}
