package org.patika;

public class Terminal {
    // ANSI Color codes for prettier output.
    public static final String COLOR_DEFAULT = "\u001B[0m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String HIGHLIGHT = "\u001B[107m";
    public static final String BOLD = "\u001B[1m";

    // Get the string for each cell and if it contains S or O, highlight it.
    public static String getCellString(char c){
        if (c == 'S' || c == 'O') return String.format("|%s%s %s %s", HIGHLIGHT, COLOR_GREEN, c, COLOR_DEFAULT);
        else return "|   ";
    }

    // Get the bottom separator for cells.
    public static String getSeparator(int size){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i <= size * 4 + 2; i++){
            output.append("-");
        }

        return output.toString();
    }

}
