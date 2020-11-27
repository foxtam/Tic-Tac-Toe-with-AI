package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;
import tictactoe.Sign;

public class HardBot extends Bot {

    public HardBot(char sign) {
        super(sign, "hard");
    }

    @Override
    public Point getPointToMark() {
        int bestScore = Integer.MIN_VALUE;
        Point bestPoint = null;
        for (int i = 1; i <= field.size(); i++) {
            for (int j = 1; j <= field.size(); j++) {
                if (field.getCellSign(i, j) == Field.emptyCell) {
                    final Point point = new Point(i, j);
                    field.setCellSign(point, getSign());
                    int score = calculateMinimaxScore(field, Sign.nextSign(getSign()));
                    field.setHardCellSign(point, Field.emptyCell);
                    if (score > bestScore) {
                        bestPoint = point;
                        bestScore = score;
                    }
                }
            }
        }
        return bestPoint;
    }

    private int calculateMinimaxScore(Field field, char sign) {
        if (game.isSignInARow(getSign())) {
            return 1;
        } else if (game.isSignInARow(Sign.nextSign(getSign()))) {
            return -1;
        } else if (!field.hasEmptyCell()) {
            return 0;
        } else {
            return getScore(field, sign);
        }
    }

    private int getScore(Field field, char sign) {
        int score = 0;
        for (int i = 1; i <= field.size(); i++) {
            for (int j = 1; j <= field.size(); j++) {
                if (field.getCellSign(i, j) == Field.emptyCell) {
                    field.setCellSign(i, j, sign);
                    score += calculateMinimaxScore(field, Sign.nextSign(sign));
                    field.setHardCellSign(i, j, Field.emptyCell);
                }
            }
        }
        return score;
    }
}
