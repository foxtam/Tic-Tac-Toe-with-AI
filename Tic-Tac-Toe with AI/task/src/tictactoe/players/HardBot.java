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
        return calculateMinimaxScore(field, getSign()).point;
    }

    private PointScorePair calculateMinimaxScore(Field field, char sign) {
        if (game.isSignInARow(getSign())) {
            return new PointScorePair(null, 1);
        } else if (game.isSignInARow(Sign.nextSign(getSign()))) {
            return new PointScorePair(null, -1);
        } else if (!field.hasEmptyCell()) {
            return new PointScorePair(null, 0);
        } else {
            return getScore(field, sign);
        }
    }

    private PointScorePair getScore(Field field, char sign) {
        int bestScore = Integer.MIN_VALUE;
        Point bestPoint = null;

        for (int i = 1; i <= field.size(); i++) {
            for (int j = 1; j <= field.size(); j++) {
                if (field.getCellSign(i, j) == Field.emptyCell) {
                    field.setCellSign(i, j, sign);
                    PointScorePair pointScore = calculateMinimaxScore(field, Sign.nextSign(sign));
                    if (pointScore.score > bestScore) {
                        bestScore = pointScore.score;
                        bestPoint = new Point(i, j);
                    }
                    field.setHardCellSign(i, j, Field.emptyCell);
                }
            }
        }
        return new PointScorePair(bestPoint, bestScore);
    }
}

class PointScorePair {
    public final Point point;
    public final int score;

    public PointScorePair(Point point, int score) {
        this.point = point;
        this.score = score;
    }
}