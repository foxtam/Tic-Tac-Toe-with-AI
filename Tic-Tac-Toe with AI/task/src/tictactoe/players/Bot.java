package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;

public abstract class Bot extends Player {

    private final String levelAsString;

    public Bot(char sign, String levelAsString) {
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
        for (int i = 1; i <= field.size(); i++) {
            for (int j = 1; j <= field.size(); j++) {
                if (field.isCellEmpty(new Point(i, j))) {
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
