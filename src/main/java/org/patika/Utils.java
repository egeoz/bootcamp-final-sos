package org.patika;

// Shared utility methods.
public class Utils {
    // Converts 2D array position to 1D array position.
    public static int locationToVector(int x, int y, int size) {
        return ((y % size * size) + x % size);
    }

    // Converts 1D array position to 2D array position.
    public static int[] vectorToLocation(int pos, int size) {
        return new int[]{pos % size, pos / size};
    }
}
