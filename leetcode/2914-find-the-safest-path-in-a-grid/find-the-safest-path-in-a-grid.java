import java.util.*;

class Solution {
    // Direction vectors for moving up, down, left, and right
    private final int[] rowDirs = {-1, 1, 0, 0};
    private final int[] colDirs = {0, 0, -1, 1};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // Edge case: If start or end contains a thief, the safeness factor is 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        // step 1: Multi-source BFS to calculate minimum distance to any thief
        int[][] safenessDir = new int[n][n];
        for (int[] row : safenessDir) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> bfsQueue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    bfsQueue.offer(new int[]{r, c});
                    safenessDir[r][c] = 0;
                }
            }
        }

        while (!bfsQueue.isEmpty()) {
            int[] curr = bfsQueue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + rowDirs[i];
                int nc = c + colDirs[i];

                if (isValid(nr, nc, n) && safenessDir[nr][nc] == -1) {
                    safenessDir[nr][nc] = safenessDir[r][c] + 1;
                    bfsQueue.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Max-Heap (Dijkstra-like) to find the path maximizing safeness factor
        // Elements in PQ: [safeness_factor, row, col]
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] visited = new boolean[n][n];

        maxHeap.offer(new int[]{safenessDir[0][0], 0, 0});
        visited[0][0] = true;

        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int currentSafeness = curr[0];
            int r = curr[1];
            int c = curr[2];

            // Reached destination destination cell
            if (r == n - 1 && c == n - 1) {
                return currentSafeness;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + rowDirs[i];
                int nc = c + colDirs[i];

                if (isValid(nr, nc, n) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // The safeness of the path to the neighbor is limited by the minimum encountered so far
                    int nextSafeness = Math.min(currentSafeness, safenessDir[nr][nc]);
                    maxHeap.offer(new int[]{nextSafeness, nr, nc});
                }
            }
        }

        return 0;
    }

    private boolean isValid(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}