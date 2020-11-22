package tictactoe.io;

import tictactoe.Point;
import tictactoe.exceptions.InputIsNotANumbers;

import java.util.Scanner;

public class Input {
    public static final Scanner scanner = new Scanner(System.in);

    public static String getCells() {
        System.out.print("Enter cells: ");
        return scanner.nextLine();
    }

    public static Point getPoint() {
        String[] xy = scanner.nextLine().split("\\s+");
        try {
            return new Point(Integer.parseInt(xy[1]), Integer.parseInt(xy[0]));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InputIsNotANumbers();
        }
    }
}
