package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;
import tictactoe.Sign;

public abstract class Player {

    private final Sign sign;

    protected Player(Sign sign) {
        this.sign = sign;
    }

    public static Player fromStringWithSign(String player, Sign sign) {
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

    public Sign getSign() {
        return sign;
    }

    public abstract Point getPointToMark(Field field);

    public abstract String getPlayerMessage();
}
