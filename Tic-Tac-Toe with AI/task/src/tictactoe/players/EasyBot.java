package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;
import tictactoe.Sign;

public class EasyBot extends Player {

    EasyBot(Sign sign) {
        super(sign);
    }

    @Override
    public Point getPointToMark(Field field) {
        System.out.println("Making move level \"easy\"");
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
