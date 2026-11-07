import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for available servers: ordered by weight, then index
        // Stores int[]: {weight, index}
        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Min-heap for busy servers: ordered by freeTime
        // Stores long[]: {freeTime, weight, index}
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            available.offer(new int[] { servers[i], i });
        }

        long currTime = 0;

        for (int j = 0; j < m; j++) {
            currTime = Math.max(currTime, j);

            // If no servers are available, jump time to when the next server frees up
            if (available.isEmpty() && !busy.isEmpty()) {
                currTime = Math.max(currTime, busy.peek()[0]);
            }

            // Release all servers that finish at or before currTime
            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                long[] freed = busy.poll();
                available.offer(new int[] { (int) freed[1], (int) freed[2] });
            }

            // Assign task j to the best available server
            int[] server = available.poll();
            ans[j] = server[1];
            busy.offer(new long[] { currTime + tasks[j], server[0], server[1] });
        }

        return ans;
    }
}