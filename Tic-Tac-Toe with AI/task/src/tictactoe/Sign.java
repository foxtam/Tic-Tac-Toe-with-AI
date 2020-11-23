package tictactoe;

import tictactoe.players.EasyBot;
import tictactoe.players.Human;
import tictactoe.players.Player;

public enum Sign {
    X('X'), O('O');

    private final char sign;

    Sign(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }

    public Sign nextSign() {
        return this == X ? O : X;
    }
}
