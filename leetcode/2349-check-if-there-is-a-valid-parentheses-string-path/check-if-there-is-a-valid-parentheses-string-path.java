class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // A valid parentheses string must have an even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Starting with ')' or ending with '(' is invalid
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // Maximum possible open brackets in any valid path is (m + n) / 2
        memo = new Boolean[m][n][(m + n) / 2 + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {
        // Track running bracket balance
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid path: more ')' than '('
        if (balance < 0) {
            return false;
        }

        // If balance exceeds maximum allowed remaining steps, prune early
        if (balance > (m - 1 - r) + (n - 1 - c)) {
            return false;
        }

        // Base case: reached bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Return memoized result if already computed
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean found = false;

        // Move Down
        if (r + 1 < m) {
            found = dfs(grid, r + 1, c, balance);
        }

        // Move Right
        if (!found && c + 1 < n) {
            found = dfs(grid, r, c + 1, balance);
        }

        return memo[r][c][balance] = found;
    }
}