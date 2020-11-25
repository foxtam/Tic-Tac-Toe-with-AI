package tictactoe;

public class Sign {
    public static final char X = 'X';
    public static final char O = 'O';

    public static char nextSign(char sign) {
        return sign == X ? O : X;
    }
}
