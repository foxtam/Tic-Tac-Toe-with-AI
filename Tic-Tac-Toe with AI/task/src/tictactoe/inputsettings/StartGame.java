package tictactoe.inputsettings;

import tictactoe.players.Player;

public class StartGame implements GameCommand {
    public static final String stringCommand = "start";

    private final Player xPlayer;
    private final Player oPlayer;

    public StartGame(Player xPlayer, Player oPlayer) {
        this.xPlayer = xPlayer;
        this.oPlayer = oPlayer;
    }

    public Player getXPlayer() {
        return xPlayer;
    }

    public Player getOPlayer() {
        return oPlayer;
    }
}
