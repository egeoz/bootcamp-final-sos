package org.patika;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final char[][] board;
    private final List<Integer> emptyCells;
    private final int size;

    // Constructor.
    public Board(int size){
        this.size = size;
        board = new char[size][size];

        // To optimize the cell picking process for the CPU,
        // an ArrayList that keeps the position of each empty cell is created.
        emptyCells = new ArrayList<>();
        for (int i = 0; i < size * size; i++){
            emptyCells.add(i);
        }
    }

    // Returns the length of the emptyCells ArrayList. This value is used as an upper bound for CPU's RNG.
    public int getEmptyCellCount() {
        return emptyCells.size();
    }

    // Returns the value of the cell picked by CPU's RNG, and removes it from the list of empty cells.
    public int getEmptyCellLocation(int pos) {
        int i = emptyCells.get(pos);
        emptyCells.remove(pos);
        return i;
    }

    // Checks if the given coordinates are within the bounds of the board and then returns the character at board[x][y]
    public char getCell(int x, int y){
        if (x >= board.length && y >= board.length) throw new ArrayIndexOutOfBoundsException();
        return board[x][y];
    }

    // Checks for the winning condition for the row, column and axis for the last placed character.
    public boolean isWin(int x, int y){
        StringBuilder line = new StringBuilder();
        // Checks for horizontal match.
        for (char c : board[x]){
            line.append(c);
        }
        if (line.toString().contains("SOS")) return true;

        line = new StringBuilder();
        // Checks for vertical match.
        for (int i = 0; i < size; i++){
            line.append(board[i][y]);
        }
        if (line.toString().contains("SOS")) return true;

        line = new StringBuilder();
        // Checks for a match in the board's primary diagonal.
        for (int i = 0; i < size; i++){
            line.append(board[i][i]);
        }
        if (line.toString().contains("SOS")) return true;

        line = new StringBuilder();
        // Checks for a match in the board's secondary diagonal.
        for (int i = size - 1; i >= 0; i--){
            line.append(board[size - 1 - i][i]);
        }
        return line.toString().contains("SOS"); // Else, returns false.
    }

    // Checks whether the specified coordinates are within the bound, if the character parameter is S or O and finally, places c if the cell is empty.
    public void setCell(int x, int y, char c) throws InvalidCharacterException {
        if (x >= board.length && y >= board.length) throw new ArrayIndexOutOfBoundsException();
        if (!(c == 'S' || c == 'O')) throw new InvalidCharacterException(c);
        if (board[x][y] == '\0') {
            board[x][y] = c;
            emptyCells.remove((Integer) Utils.locationToVector(x, y, size));
        }
    }

    // Draws the board on the terminal.
    public void drawBoard(){
        System.out.printf("%n%n%s%s%s%s%n%n",Terminal.BOLD, Terminal.COLOR_GREEN, Terminal.getSeparator(size * 2), Terminal.COLOR_DEFAULT);
        StringBuilder output = new StringBuilder(" ");
        // Add column numbers to the output.
        for (int x = 0; x < size; x++){
            output.append(" | " + Terminal.BOLD + Terminal.COLOR_GREEN).append(x).append(Terminal.COLOR_DEFAULT);
        }
        output.append(" |\n" + Terminal.COLOR_DEFAULT).append(Terminal.getSeparator(size)).append("\n");
        // Add row numbers and cells to the output.
        for (int x = 0; x < size; x++){
            output.append(Terminal.BOLD + Terminal.COLOR_GREEN).append(x).append(" ").append(Terminal.COLOR_DEFAULT);
            for (int y = 0; y < size; y++){
                output.append(Terminal.getCellString(board[x][y]));
            }
            output.append("|\n").append(Terminal.getSeparator(size)).append("\n");
        }
        // Print the board.
        System.out.print(output);
    }
}
