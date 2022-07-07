package org.patika;

public class Score {
    private int score;
    private int gameCount;
    private String name;

    // Constructor.
    public Score(String name, int gameCount, int score){
        this.name = name;
        this.gameCount = gameCount;
        this.score = score;
    }

    public Score(){}

    // Getters
    public String getName() {
        return name;
    }

    public int getGameCount() {
        return gameCount;
    }

    public int getScore() {
        return score;
    }

    // Setters
    public void setScore(int score) {
        this.score = score;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    // Converts the objects attributes to a printable format, used for the high-scores list.
    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%n",this.name, this.gameCount, this.score);
    }
}
