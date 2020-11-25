package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;
import tictactoe.Sign;

public abstract class Bot extends Player {

    private final String levelAsString;

    public Bot(Sign sign, String levelAsString) {
        super(sign);
        this.levelAsString = levelAsString;
    }

    @Override
    public String getPlayerMessage() {
        return "Making move level \"" + levelAsString + "\"";
    }

    protected Point getRandomPointToMark(Field field) {
        Point randomPoint = null;
        int count = 0;
        for (int i = 1; i <= field.getSize(); i++) {
            for (int j = 1; j <= field.getSize(); j++) {
                if (field.isEmptyCell(new Point(i, j))) {
                    count++;
                    if (Math.random() < 1.0 / count) {
                        randomPoint = new Point(i, j);
                    }
                }
            }
        }
        assert count > 0;
        assert randomPoint != null;
        return randomPoint;
    }
}
