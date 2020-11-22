package tictactoe;

import tictactoe.exceptions.WrongGameSettingsInput;
import tictactoe.inputsettings.ExitGame;
import tictactoe.inputsettings.GameCommand;
import tictactoe.inputsettings.StartGame;
import tictactoe.io.Input;

public class Main {

    public static void main(String[] args) {
        while (true) {
            try {
                GameCommand command = inputUserCommand();
                if (command instanceof ExitGame) {
                    break;
                } else if (command instanceof StartGame) {
                    StartGame startCommand = (StartGame) command;
                    Sign.X.setPlayer(startCommand.getXPlayer());
                    Sign.O.setPlayer(startCommand.getOPlayer());
                    new Game(3).play();
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (WrongGameSettingsInput ignored) {
                System.out.println("Bad parameters!");
            }
        }
    }

    private static GameCommand inputUserCommand() {
        System.out.print("Input command: ");
        String input = Input.scanner.nextLine();
        return GameCommand.getGameCommandFrom(input);
    }
}
