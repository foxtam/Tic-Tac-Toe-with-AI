package tictactoe;

public enum Sign {
    X('X', PlayerType.HUMAN), O('O', PlayerType.BOT);

    public final char sign;
    public final PlayerType playerType;

    Sign(char sign, PlayerType playerType) {
        this.sign = sign;
        this.playerType = playerType;
    }

    public Sign nextSign() {
        return this == X ? O : X;
    }
}
