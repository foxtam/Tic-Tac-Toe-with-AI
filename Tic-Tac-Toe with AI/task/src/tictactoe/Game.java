package tictactoe;

import tictactoe.exceptions.CoordinatesOutOfBounds;
import tictactoe.exceptions.InputIsNotANumbers;
import tictactoe.exceptions.OccupiedException;
import tictactoe.io.Output;
import tictactoe.players.Player;
import tictactoe.players.PlayersQueue;

public class Game {

    private final Field field;
    private final PlayersQueue playersQueue;
    private GameState gameState = GameState.NOT_FINISHED;
    private Player currentPlayer;

    public Game(int fieldSize, Player... players) {
        this.field = new Field(fieldSize);
        this.playersQueue = new PlayersQueue(players);
        this.currentPlayer = playersQueue.next();
        Output.println(field.toString());
    }

    public void play() {
        while (gameState == GameState.NOT_FINISHED) {
            try {
                System.out.println(currentPlayer.getPlayerMessage());
                Point point = currentPlayer.getPointToMark(field);
                field.mark(currentPlayer.getSign(), point);
                currentPlayer = playersQueue.next();
                gameState = calculateGameState();
                Output.println(field.toString());
            } catch (OccupiedException e) {
                System.out.println("This cell is occupied! Choose another one!");
            } catch (InputIsNotANumbers e) {
                System.out.println("You should enter numbers!");
            } catch (CoordinatesOutOfBounds e) {
                System.out.printf("Coordinates should be from 1 to %d!%n", field.getSize());
            }
        }
        printGameStatus();
    }

    private GameState calculateGameState() {
        if (field.isOInARow()) {
            return GameState.O_WINS;
        } else if (field.isXInARow()) {
            return GameState.X_WINS;
        } else if (field.hasEmptyCell()) {
            return GameState.NOT_FINISHED;
        } else {
            return GameState.DRAW;
        }
    }

    private void printGameStatus() {
        switch (gameState) {
            case X_WINS:
                Output.println("X wins");
                break;
            case O_WINS:
                Output.println("O wins");
                break;
            case DRAW:
                Output.println("Draw");
                break;
            case NOT_FINISHED:
                Output.println("Game not finished");
        }
    }
}
