package tictactoe;

public enum Sign {
    X('X'), O('O');

    public final char sign;

    Sign(char sign) {
        this.sign = sign;
    }

    public Sign nextSign() {
        return this == X ? O : X;
    }
}
