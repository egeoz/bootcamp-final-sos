package org.patika;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Returns the formatted menu string.
    private static String getMenu(){
        return String.format("%n%s%sSOS Game%n%s%s1%s - Start%n%s2%s - Show high scores%nPress any other key to exit.%n%s> %s",
                            Terminal.BOLD, Terminal.COLOR_GREEN, Terminal.COLOR_DEFAULT, Terminal.BOLD, Terminal.COLOR_DEFAULT, Terminal.BOLD, Terminal.COLOR_DEFAULT, Terminal.BOLD, Terminal.COLOR_DEFAULT);
    }

    public static void main(String[] args) throws InvalidCharacterException, IOException {
        Scanner sc = new Scanner(System.in);
        int menuChoice = 0;

        ScoreHandler.init();

        while (true) {
            System.out.print(getMenu());

            // Get menu option, quit if it is not an integer.
            try {
                menuChoice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.exit(0);
            }

            sc.nextLine();

            switch (menuChoice) {
                case 1: // Start the game
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    Game game = new Game(name);
                    game.start();
                    break;
                case 2: // Print all high-scores
                    System.out.printf("%n%s%s%-15s%-15s%-15s%s%n%s%n%s", Terminal.COLOR_GREEN, Terminal.BOLD, "User name", "Game count", "Points", Terminal.COLOR_DEFAULT, Terminal.getSeparator(9), ScoreHandler.getAll());
                    break;
                default: // Quit if menuChoice is any other integer.
                    sc.close();
                    System.exit(0);
            }
        }
    }
}
