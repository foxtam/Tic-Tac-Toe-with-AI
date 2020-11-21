package tictactoe;

class TicTakToeException extends RuntimeException {
}

class OccupiedException extends TicTakToeException {
}

class InputIsNotANumbers extends TicTakToeException {
}

class CoordinatesOutOfBounds extends TicTakToeException {
}