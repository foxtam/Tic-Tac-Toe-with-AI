package tictactoe.players;

import tictactoe.Point;

public class EasyBot extends Bot {

    EasyBot(char sign) {
        super(sign, "easy");
    }

    @Override
    public Point getPointToMark() {
        return getRandomPointToMark(field);
    }
}
