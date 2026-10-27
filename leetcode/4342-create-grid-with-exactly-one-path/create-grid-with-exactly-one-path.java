class Solution {
    public String[] createGrid(int m, int n) {
        StringBuilder[] grid = new StringBuilder[m];
        
        // Initialize all rows with obstacles '#'
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('#');
            }
            grid[i] = sb;
        }
        
        // Create the single valid path:
        // 1. Move all the way right along the first row (row 0)
        for (int j = 0; j < n; j++) {
            grid[0].setCharAt(j, '.');
        }
        
        // 2. Move all the way down along the last column (column n - 1)
        for (int i = 0; i < m; i++) {
            grid[i].setCharAt(n - 1, '.');
        }
        
        // Convert StringBuilder array to String array
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = grid[i].toString();
        }
        
        return result;
    }
}