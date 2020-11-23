package tictactoe;

import tictactoe.exceptions.BadParametersException;
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
                    new Game(3, startCommand.getXPlayer(), startCommand.getOPlayer()).play();
                } else {
                    throw new IllegalStateException();
                }
            } catch (BadParametersException e) {
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
