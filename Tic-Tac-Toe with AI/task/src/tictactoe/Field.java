package tictactoe;

import tictactoe.exceptions.CoordinatesOutOfBounds;
import tictactoe.exceptions.OccupiedException;

import java.util.Optional;

public class Field {

    public static final char emptyCell = '_';
    private final char[][] field;
    private final int size;

    public Field(int size) {
        this(size, String.valueOf(emptyCell).repeat(size * size));
    }

    public Field(int size, String cells) {
        cells = cells.toUpperCase();
        checkSize(size, cells);
        checkCellsChars(cells);
        this.size = size;
        field = new char[size][size];
        fillField(cells);
    }

    private void checkSize(int size, String cells) {
        if (size * size != cells.length()) {
            throw new IllegalArgumentException();
        }
    }

    private void checkCellsChars(String cells) {
        for (char ch : cells.toCharArray()) {
            if ("XO_".indexOf(ch) == -1) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void fillField(String cells) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = cells.charAt(i * size + j);
            }
        }
    }

    public boolean hasEmptyCell() {
        for (char[] chars : field) {
            for (char aChar : chars) {
                if (aChar == emptyCell) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        int length = 9;
        StringBuilder builder = new StringBuilder("-".repeat(length));
        for (char[] chars : field) {
            builder.append("\n| ");
            for (char aChar : chars) {
                if (aChar == emptyCell) {
                    aChar = ' ';
                }
                builder.append(aChar).append(" ");
            }
            builder.append("|");
        }
        builder.append('\n').append("-".repeat(length));
        return builder.toString();
    }

    public boolean isCellEmpty(Point point) {
        return getCellSign(point) == emptyCell;
    }

    public char getCellSign(Point point) {
        point = new Point(size - point.row, point.column - 1);
        checkOutOfBounds(point);
        return field[point.row][point.column];
    }

    private void checkOutOfBounds(Point point) {
        if (Math.min(point.row, point.column) < 0 || Math.max(point.row, point.column) >= size) {
            throw new CoordinatesOutOfBounds();
        }
    }

    public void setCellSign(int row, int column, char sign) {
        setCellSign(new Point(row, column), sign);
    }

    public void setHardCellSign(int row, int column, char sign) {
        setHardCellSign(new Point(row, column), sign);
    }

    public void setCellSign(Point point, char sign) {
        point = new Point(size - point.row, point.column - 1);
        checkOutOfBounds(point);
        checkOccupiedCell(point);
        field[point.row][point.column] = sign;
    }

    public void setHardCellSign(Point point, char sign) {
        point = new Point(size - point.row, point.column - 1);
        checkOutOfBounds(point);
        field[point.row][point.column] = sign;
    }

    private void checkOccupiedCell(Point point) {
        if (field[point.row][point.column] != emptyCell) {
            throw new OccupiedException();
        }
    }

    public int countSignsInRow(int row, char sign) {
        int count = 0;
        for (int j = 1; j <= size(); j++) {
            if (getCellSign(row, j) == sign) {
                count++;
            }
        }
        return count;
    }

    public int size() {
        return size;
    }

    public char getCellSign(int row, int column) {
        return getCellSign(new Point(row, column));
    }

    public int countSignsInColumn(int column, char sign) {
        int count = 0;
        for (int i = 1; i <= size(); i++) {
            if (getCellSign(i, column) == sign) {
                count++;
            }
        }
        return count;
    }

    public int countSignsMainDiagonal(char sign) {
        int count = 0;
        for (int i = 1; i <= size(); i++) {
            if (getCellSign(i, i) == sign) {
                count++;
            }
        }
        return count;
    }

    public int countSignsOtherDiagonal(char sign) {
        int count = 0;
        for (int i = 1; i <= size(); i++) {
            if (getCellSign(i, size() - i + 1) == sign) {
                count++;
            }
        }
        return count;
    }

    public Optional<Integer> getEmptyCellColumnIndexInRow(int i) {
        for (int j = 1; j <= size; j++) {
            if (getCellSign(i, j) == emptyCell) {
                return Optional.of(j);
            }
        }
        return Optional.empty();
    }

    public Optional<Integer> getEmptyCellRowIndexInColumn(int j) {
        for (int i = 1; i <= size; i++) {
            if (getCellSign(i, j) == emptyCell) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public Optional<Point> getEmptyCellPointMainDiagonal() {
        for (int i = 1; i <= size; i++) {
            if(getCellSign(i, i) == emptyCell) {
                return Optional.of(new Point(i, i));
            }
        }
        return Optional.empty();
    }

    public Optional<Point> getEmptyCellPointOtherDiagonal() {
        for (int i = 1; i <= size; i++) {
            if(getCellSign(i, size - i + 1) == emptyCell) {
                return Optional.of(new Point(i, size - i + 1));
            }
        }
        return Optional.empty();
    }
}
