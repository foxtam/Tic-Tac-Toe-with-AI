package tictactoe;

import tictactoe.exceptions.CoordinatesOutOfBounds;
import tictactoe.exceptions.OccupiedException;

public class Field {

    private static final char emptyCell = '_';
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

    private char getCell(Point point) {
        return field[size - point.row][point.column - 1];
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

    public int getSize() {
        return size;
    }

    public void mark(Sign sign, Point point) {
        point = new Point(size - point.row, point.column - 1);
        checkOutOfBounds(point);
        checkOccupiedCell(point);
        field[point.row][point.column] = sign.getSign();
    }

    private void checkOutOfBounds(Point point) {
        if (Math.min(point.row, point.column) < 0 || Math.max(point.row, point.column) >= size) {
            throw new CoordinatesOutOfBounds();
        }
    }

    private void checkOccupiedCell(Point point) {
        if (field[point.row][point.column] != emptyCell) {
            throw new OccupiedException();
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

    public boolean isOInARow() {
        return isSignInARow(Sign.O);
    }

    private boolean isSignInARow(Sign sign) {
        if (checkInARowRows(sign)) return true;
        if (checkInARowColumns(sign)) return true;
        if (checkInARowMainDiagonal(sign)) return true;
        return checkInARowOtherDiagonal(sign);
    }

    private boolean checkInARowRows(Sign sign) {
        outer:
        for (char[] chars : field) {
            for (char aChar : chars) {
                if (aChar != sign.getSign()) {
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkInARowColumns(Sign sign) {
        outer:
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (field[i][j] != sign.getSign()) {
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkInARowMainDiagonal(Sign sign) {
        for (int i = 0; i < size; i++) {
            if (field[i][i] != sign.getSign()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkInARowOtherDiagonal(Sign sign) {
        for (int i = 0; i < size; i++) {
            if (field[i][size - i - 1] != sign.getSign()) {
                return false;
            }
        }
        return true;
    }

    public boolean isXInARow() {
        return isSignInARow(Sign.X);
    }

    public int countX() {
        return countSign(Sign.X);
    }

    private int countSign(Sign sign) {
        int count = 0;
        for (char[] chars : field) {
            for (char aChar : chars) {
                if (aChar == sign.getSign()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countO() {
        return countSign(Sign.O);
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

    public boolean isEmptyCell(Point point) {
        return getCell(point) == emptyCell;
    }
}
