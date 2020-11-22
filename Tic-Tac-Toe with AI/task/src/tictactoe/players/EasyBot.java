package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;

public class EasyBot implements Player {

    public static final EasyBot INSTANCE = new EasyBot();

    private EasyBot() {
    }

    @Override
    public Point getPointFrom(Field field) {
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
