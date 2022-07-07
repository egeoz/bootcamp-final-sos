package org.patika;

import java.io.IOException;
import java.util.Random;

public class CPU{
    private final Random random;
    private final Board board;
    private final int size;
    private final String playerName;
    private char character;

    // Picks an empty cell, checks for winning condition and then writes the results to the JSON file.
    public void pickCell() throws InvalidCharacterException, IOException {
        // Creates a random number between 0 and emptyCells.size() and then gets the corresponding value from the list.
        int val = board.getEmptyCellLocation(random.nextInt(board.getEmptyCellCount()));
        // Converts 1D array position to 2D array position = [x, y]
        int[] pos = Utils.vectorToLocation(val, size);
        board.setCell(pos[0], pos[1], character);
        board.drawBoard();

        boolean status = board.isWin(pos[0], pos[1]);
        // Checks for CPU victory, adds a new score, writes to JSON file and quits.
        if (status){
            System.out.printf("%sComputer wins!%s%n", Terminal.BOLD, Terminal.COLOR_DEFAULT);
            ScoreHandler.addScore(new Score(playerName, 1, 0));
            ScoreHandler.writeJSONFile();
            System.exit(0);
        // Checks for draw, adds new score, writes to JSON file and quits.
        } else if (!status && board.getEmptyCellCount() == 0){
            System.out.printf("%sDraw!%s%n", Terminal.BOLD, Terminal.COLOR_DEFAULT);
            ScoreHandler.addScore(new Score(playerName, 1, 0));
            ScoreHandler.writeJSONFile();
            System.exit(0);
        }
    }

    // Constructor.
    public CPU(int size, Board board, String playerName){
        random = new Random();
        this.size = size;
        this.board = board;
        this.playerName = playerName;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}
