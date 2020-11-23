package tictactoe.inputsettings;


import tictactoe.Sign;
import tictactoe.exceptions.BadParametersException;
import tictactoe.players.Player;

public interface GameCommand {

    static GameCommand getGameCommandFrom(String fullCommand) {
        try {
            String[] parts = fullCommand.split("\\s+");
            if (parts[0].equals(StartGame.stringCommand)) {
                return new StartGame(
                        Player.fromStringWithSign(parts[1], Sign.X),
                        Player.fromStringWithSign(parts[2], Sign.O));
            } else if (parts[0].equals(ExitGame.stringCommand)) {
                return new ExitGame();
            } else {
                throw new BadParametersException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new BadParametersException();
        }
    }
}
