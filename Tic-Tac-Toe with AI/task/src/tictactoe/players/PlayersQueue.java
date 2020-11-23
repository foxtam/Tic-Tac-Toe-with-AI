package tictactoe.players;

import java.util.Arrays;

public class PlayersQueue {
    private final Player[] players;
    private int nextPlayerIndex = 0;

    public PlayersQueue(Player[] players) {
        this.players = Arrays.copyOf(players, players.length);
        if (players.length < 2) {
            throw new IllegalArgumentException();
        }
    }

    public Player next() {
        if(nextPlayerIndex == players.length) {
            nextPlayerIndex = 0;
        }
        return players[nextPlayerIndex++];
    }
}
