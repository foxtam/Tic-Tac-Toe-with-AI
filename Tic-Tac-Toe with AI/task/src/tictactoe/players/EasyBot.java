package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;
import tictactoe.Sign;

public class EasyBot extends Bot {

    EasyBot(char sign) {
        super(sign, "easy");
    }

    @Override
    public Point getPointToMark(Field field) {
        return getRandomPointToMark(field);
    }
}
