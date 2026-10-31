import java.util.Arrays;

class Solution {
    private int m, n;
    private final int MOD = 1_000_000_007;
    private int[][][] go;
    private int[] memo; // Fixed: Changed from int[][] to int[]

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length; // Fixed: safely indexing grid[0] for columns
        
        if (grid[m-1][n-1] == 1) return 0;

        go = new int[m][n][2];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Rightward move ray-trace
                int r = i, c = j + 1, dir = 0; 
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) { dir = 1; r += 1; } 
                    else { dir = 0; c += 1; }
                }
                go[i][j][0] = (r >= 0 && r < m && c >= 0 && c < n) ? (r * n + c) : -1;

                // Downward move ray-trace
                r = i + 1; c = j; dir = 1;
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) { dir = 1; r += 1; } 
                    else { dir = 0; c += 1; }
                }
                go[i][j][1] = (r >= 0 && r < m && c >= 0 && c < n) ? (r * n + c) : -1;
            }
        }

        memo = new int[m * n];
        Arrays.fill(memo, -1);

        if (grid[0][0] == 1) return 0;

        return dfs(0, 0);
    }

    private int dfs(int r, int c) {
        if (r == m - 1 && c == n - 1) return 1;
        
        int cellId = r * n + c;
        if (memo[cellId] != -1) return memo[cellId];

        long totalPaths = 0;

        // Try moving Right
        int targetRight = go[r][c][0];
        if (targetRight != -1) {
            totalPaths = (totalPaths + dfs(targetRight / n, targetRight % n)) % MOD;
        }

        // Try moving Down
        int targetDown = go[r][c][1];
        if (targetDown != -1) {
            totalPaths = (totalPaths + dfs(targetDown / n, targetDown % n)) % MOD;
        }

        memo[cellId] = (int) totalPaths;
        return memo[cellId];
    }
}
