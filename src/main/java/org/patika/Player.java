package org.patika;

import java.io.IOException;
import java.util.Scanner;

public class Player {
    private final Board board;
    private final int size;
    private final String name;
    private char character;
    private final Scanner sc;
    private int x;
    private int y;
    private int points;

    // Takes string from stdin and checks if it is a valid x coordinate.
    private void getX(){
        String sx = sc.nextLine();
        try {
            // Try parsing input to see if it triggers NumberFormatException.
            x = Integer.parseInt(sx);
        } catch (NumberFormatException e) {
            x = -1;
        }
    }

    // Takes string from stdin and checks if it is a valid y coordinate.
    private void getY(){
        String sy = sc.nextLine();
        try {
            // Try parsing input to see if it triggers NumberFormatException.
            y = Integer.parseInt(sy);
        } catch (NumberFormatException e) {
            y = -1;
        }
    }

    // Checks whether x and y are valid coordinates.
    private boolean isValidPos() {
        return (x > -1 && x < size) && (y > -1 && y < size) && (board.getCell(x, y) == '\0');
    }

    // Asks user for x and y coordinates, checks if they are valid, checks for winning condition and then writes the results to the JSON file.
    public void pickCell() throws InvalidCharacterException, IOException {
        do {
            System.out.print("Enter an empty row: ");
            getX();
            System.out.print("Enter an empty column: ");
            getY();
        } while (!isValidPos());
        board.setCell(x, y, character);
        board.drawBoard();

        boolean status = board.isWin(x, y);
        // Checks for player victory, adds a new score, writes to JSON file and quits.
        if (status){
            System.out.printf("%sPlayer wins!%s%n", Terminal.BOLD, Terminal.COLOR_DEFAULT);
            ScoreHandler.addScore(new Score(name, 1, points));
            ScoreHandler.writeJSONFile();
            System.exit(0);
        // Checks for draw, adds new score, writes to JSON file and quits.
        } else if (!status && board.getEmptyCellCount() == 0){
            System.out.println("Draw!");
            ScoreHandler.addScore(new Score(name, 1, 0));
            ScoreHandler.writeJSONFile();
            System.exit(0);
        }
        // A point is deducted for every turn.
        points--;
    }

    // Constructor.
    public Player(int size, Board board, String name){
        this.size = size;
        this.board = board;
        this.name = name;
        this.points = size * size;
        sc = new Scanner(System.in);
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getPoints() {
        return points;
    }
}
