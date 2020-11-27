package tictactoe.players;

import tictactoe.Field;
import tictactoe.Game;
import tictactoe.Point;

public abstract class Player {

    private final char sign;
    protected Field field;
    protected Game game;

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
            case "hard":
                return new HardBot(sign);
            default:
                throw new IllegalArgumentException();
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.field = game.getField();
        this.game = game;
    }

    public char getSign() {
        return sign;
    }

    public abstract Point getPointToMark();

    public abstract String getPlayerMessage();
}
