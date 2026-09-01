import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) Arrays.fill(row, -1);
        
        int k = 0;
        int startR = 0, startC = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterId[r][c] = k++;
                }
            }
        }

        if (k == 0) return 0;
        int targetMask = (1 << k) - 1;

        // bestEnergy[r][c][mask] stores max energy achieved at (r, c) with mask
        int[][][] bestEnergy = new int[m][n][1 << k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        // Format: {row, col, mask, energy, moves}
        queue.offer(new int[]{startR, startC, 0, energy, 0});
        bestEnergy[startR][startC][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], mask = cur[2], e = cur[3], moves = cur[4];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (classroom[nr].charAt(nc) == 'X') continue;

                int ne = e - 1;
                if (ne < 0) continue;

                int nmask = mask;
                char cell = classroom[nr].charAt(nc);
                if (cell == 'R') {
                    ne = energy;
                } else if (cell == 'L') {
                    nmask |= (1 << litterId[nr][nc]);
                }

                if (nmask == targetMask) {
                    return moves + 1;
                }

                if (ne <= bestEnergy[nr][nc][nmask]) continue;
                bestEnergy[nr][nc][nmask] = ne;

                queue.offer(new int[]{nr, nc, nmask, ne, moves + 1});
            }
        }

        return -1;
    }
}