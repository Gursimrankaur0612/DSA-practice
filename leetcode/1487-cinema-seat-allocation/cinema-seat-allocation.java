import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();

        // Build a bitmask for each row with reserved seats (only seats 2-9 matter)
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << (col - 1)));
            }
        }

        // Start by assuming all n rows can hold 2 groups each
        int maxGroups = n * 2;

        // Bitmasks for the 3 possible 4-seat blocks
        int leftBlock   = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4); // seats 2, 3, 4, 5
        int rightBlock  = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8); // seats 6, 7, 8, 9
        int middleBlock = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6); // seats 4, 5, 6, 7

        for (int mask : rowMasks.values()) {
            boolean canLeft = (mask & leftBlock) == 0;
            boolean canRight = (mask & rightBlock) == 0;

            if (canLeft && canRight) {
                // Both blocks fit; no reduction needed
                continue;
            } else if (canLeft || canRight || (mask & middleBlock) == 0) {
                // Either left, right, or middle fits (1 group instead of 2)
                maxGroups -= 1;
            } else {
                // No groups fit in this row (0 groups instead of 2)
                maxGroups -= 2;
            }
        }

        return maxGroups;
    }
}