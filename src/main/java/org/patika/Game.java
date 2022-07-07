package org.patika;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private char playerChar;
    private char computerChar;
    private int size;
    private boolean playerTurn;
    private final Board board;
    private final CPU cpu;
    private final Player player;

    // Checks if the entered board is a valid integer and if it is within the interval of [3, 7].
    private boolean isValidSize(String input) {
        size = 0;
        if (input == null) return false;

        try {
            // Try parsing input to see if it triggers NumberFormatException.
            size = Integer.parseInt(input);
            return size >= 3 && size <= 7;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Picks player character based on a coin flip
    private void pickPlayerCharacter(){
        if ((new Random()).nextInt(2) == 1){
            playerChar = 'S';
            playerTurn = true;
            computerChar = 'O';
        } else {
            playerChar = 'O';
            playerTurn = false;
            computerChar = 'S';
        }
    }

    // Prints current player score and calls corresponding pickCell method depending on the turn.
    private void controls() throws InvalidCharacterException, IOException {
        while (board.getEmptyCellCount() > 0) {
            System.out.printf("%sSCORE: %s%d%n", Terminal.BOLD, Terminal.COLOR_DEFAULT, player.getPoints());
            System.out.println(playerTurn ? Terminal.BOLD + "Player's turn (" + playerChar + ") " + Terminal.COLOR_DEFAULT: Terminal.BOLD + "Computer's turn (" + computerChar + ")" + Terminal.COLOR_DEFAULT);
            while (playerTurn) {
                player.pickCell();
                playerTurn = false;
            }
            while (!playerTurn) {
                cpu.pickCell();
                playerTurn = true;
            }
        }
    }

    // Constructor.
    public Game(String name) {
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            System.out.print("Enter board size (minimum 3, maximum 7): ");
            input = sc.nextLine();
        } while (!isValidSize(input));

        this.board = new Board(size);
        this.cpu = new CPU(size, board, name);
        this.player = new Player(size, board, name);
    }

    // Starts the game.
    public void start() throws InvalidCharacterException, IOException {
        pickPlayerCharacter();
        cpu.setCharacter(computerChar);
        player.setCharacter(playerChar);
        board.drawBoard();
        controls();

        try {
            ScoreHandler.writeJSONFile();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
