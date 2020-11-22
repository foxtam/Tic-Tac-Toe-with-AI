package tictactoe;

import tictactoe.players.EasyBot;
import tictactoe.players.Human;

public enum Sign {
    X('X', Human.INSTANCE), O('O', EasyBot.INSTANCE);

    private final char sign;
    private Player player;

    Sign(char sign, Player player) {
        this.sign = sign;
        this.player = player;
    }

    public char getSign() {
        return sign;
    }

    public Sign nextSign() {
        return this == X ? O : X;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
