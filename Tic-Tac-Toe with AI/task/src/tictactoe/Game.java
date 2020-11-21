package tictactoe;

public class Game {

    private final Field field;
    private GameState gameState = GameState.NOT_FINISHED;
    private Sign currentSign;

    public Game(int fieldSize) {
        this.field = new Field(fieldSize, Input.getCells());
        this.currentSign = field.countO() < field.countX() ? Sign.O : Sign.X;
        Output.println(field.toString());
    }

    public void play() {
//        while (gameState == GameState.NOT_FINISHED) {
        try {
            Point point = Input.getPoint();
            field.mark(currentSign, point);
            currentSign = currentSign.nextSign();
            gameState = calculateGameState();
            Output.println(field.toString());
            printGameStatus();
        } catch (OccupiedException e) {
            System.out.println("This cell is occupied! Choose another one!");
            play();
        } catch (InputIsNotANumbers e) {
            System.out.println("You should enter numbers!");
            play();
        } catch (CoordinatesOutOfBounds e) {
            System.out.printf("Coordinates should be from 1 to %d!%n", field.getSize());
            play();
        }
//        }
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
