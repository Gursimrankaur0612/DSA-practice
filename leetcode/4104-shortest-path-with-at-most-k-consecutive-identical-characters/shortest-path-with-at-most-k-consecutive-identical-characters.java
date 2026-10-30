import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Corrected: Treat the graph as directed
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }
        
        pq.offer(new long[]{0, 0, 1});
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (d > dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return (int) d;
            }

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                int nextCount = (labels.charAt(v) == labels.charAt(u)) ? count + 1 : 1;

                if (nextCount <= k) {
                    if (d + weight < dist[v][nextCount]) {
                        dist[v][nextCount] = d + weight;
                        pq.offer(new long[]{d + weight, v, nextCount});
                    }
                }
            }
        }

        return -1;
    }
}
