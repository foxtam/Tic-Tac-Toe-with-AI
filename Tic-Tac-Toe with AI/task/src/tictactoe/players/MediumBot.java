package tictactoe.players;

import tictactoe.Field;
import tictactoe.Point;
import tictactoe.Sign;

import java.util.Optional;

public class MediumBot extends Bot {
    public MediumBot(char sign) {
        super(sign, "medium");
    }

    @Override
    public Point getPointToMark() {
        Optional<Point> point = checkInARowSign(field, getSign());
        if (point.isPresent()) {
            return point.get();
        }

        point = checkInARowSign(field, Sign.nextSign(getSign()));
        if (point.isPresent()) {
            return point.get();
        }

        return getRandomPointToMark(field);
    }

    public Optional<Point> checkInARowSign(Field field, char sign) {
        for (int i = 1; i <= field.size(); i++) {
            if (hasOneEmptyCellInRow(field, i, sign)) {
                Optional<Integer> column = field.getEmptyCellColumnIndexInRow(i);
                if (column.isPresent()) {
                    return Optional.of(new Point(i, column.get()));
                }
            }
        }

        for (int j = 1; j <= field.size(); j++) {
            if (hasOneEmptyCellInColumn(field, j, sign)) {
                Optional<Integer> row = field.getEmptyCellRowIndexInColumn(j);
                if (row.isPresent()) {
                    return Optional.of(new Point(row.get(), j));
                }
            }
        }

        if (hasOneEmptyCellInMainDiagonal(field, sign)) {
            Optional<Point> point = field.getEmptyCellPointMainDiagonal();
            if (point.isPresent()) {
                return point;
            }
        }

        if (hasOneEmptyCellInOtherDiagonal(field, sign)) {
            Optional<Point> point = field.getEmptyCellPointOtherDiagonal();
            if (point.isPresent()) {
                return point;
            }
        }

        return Optional.empty();
    }

    public boolean hasOneEmptyCellInRow(Field field, int i, char sign) {
        return field.countSignsInRow(i, sign) == 2 && field.countSignsInRow(i, Field.emptyCell) == 1;
    }

    public boolean hasOneEmptyCellInColumn(Field field, int j, char sign) {
        return field.countSignsInColumn(j, sign) == 2 && field.countSignsInColumn(j, Field.emptyCell) == 1;
    }

    private boolean hasOneEmptyCellInMainDiagonal(Field field, char sign) {
        return field.countSignsMainDiagonal(sign) == 2 && field.countSignsMainDiagonal(Field.emptyCell) == 1;
    }

    private boolean hasOneEmptyCellInOtherDiagonal(Field field, char sign) {
        return field.countSignsOtherDiagonal(sign) == 2 && field.countSignsOtherDiagonal(Field.emptyCell) == 1;
    }
}
