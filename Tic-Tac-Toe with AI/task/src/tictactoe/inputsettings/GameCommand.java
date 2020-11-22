package tictactoe.inputsettings;


import tictactoe.Player;
import tictactoe.exceptions.WrongGameSettingsInput;

public interface GameCommand {

    static GameCommand getGameCommandFrom(String fullCommand) {
        try {
            String[] parts = fullCommand.split("\\s+");
            if (parts[0].equals(StartGame.stringCommand)) {
                return new StartGame(Player.fromString(parts[1]), Player.fromString(parts[2]));
            } else if (parts[0].equals(ExitGame.stringCommand)) {
                return new ExitGame();
            } else {
                throw new WrongGameSettingsInput();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WrongGameSettingsInput();
        }
    }
}
