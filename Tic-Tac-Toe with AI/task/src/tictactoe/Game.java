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
        for (Player player : players) {
            player.setGame(this);
        }
        this.playersQueue = new PlayersQueue(players);
        this.currentPlayer = playersQueue.next();
        Output.println(field.toString());
    }

    public Field getField() {
        return field;
    }

    public void play() {
        while (gameState == GameState.NOT_FINISHED) {
            try {
                playerTurn();
            } catch (OccupiedException e) {
                System.out.println("This cell is occupied! Choose another one!");
            } catch (InputIsNotANumbers e) {
                System.out.println("You should enter numbers!");
            } catch (CoordinatesOutOfBounds e) {
                System.out.printf("Coordinates should be from 1 to %d!%n", field.size());
            }
        }
        printGameStatus();
    }

    public void playerTurn() {
        Output.println(currentPlayer.getPlayerMessage());
        Point point = currentPlayer.getPointToMark();
        field.setCellSign(point, currentPlayer.getSign());
        currentPlayer = playersQueue.next();
        gameState = calculateGameState();
        Output.println(field.toString());
    }

    public GameState calculateGameState() {
        if (isOInARow()) {
            return GameState.O_WINS;
        } else if (isXInARow()) {
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

    public boolean isXInARow() {
        return isSignInARow(Sign.X);
    }

    public boolean isSignInARow(char sign) {
        if (checkInARowRows(sign)) return true;
        if (checkInARowColumns(sign)) return true;
        if (checkInARowMainDiagonal(sign)) return true;
        return checkInARowOtherDiagonal(sign);
    }

    private boolean checkInARowRows(char sign) {
        for (int i = 1; i <= field.size(); i++) {
            if (field.countSignsInRow(i, sign) == field.size()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInARowColumns(char sign) {
        for (int j = 1; j <= field.size(); j++) {
            if (field.countSignsInColumn(j, sign) == field.size()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInARowMainDiagonal(char sign) {
        return field.countSignsMainDiagonal(sign) == field.size();
    }

    private boolean checkInARowOtherDiagonal(char sign) {
        return field.countSignsOtherDiagonal(sign) == field.size();
    }

    public boolean isOInARow() {
        return isSignInARow(Sign.O);
    }
}
