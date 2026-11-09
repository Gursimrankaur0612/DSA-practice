import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track the number of requests handled by each server
        int[] count = new int[k];
        
        // TreeSet to maintain sorted order of currently available servers
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            free.add(i);
        }
        
        // Min-Heap tracking busy servers: storing int[]{endTime, serverId}
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int start = arrival[i];
            int end = start + load[i];
            
            // 1. Free up all servers that finished their task before or at current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                free.add(busy.poll()[1]);
            }
            
            // 2. If all servers are busy, drop the request
            if (free.isEmpty()) {
                continue;
            }
            
            // 3. Find the target server using the preferred index (i % k)
            Integer server = free.ceiling(i % k);
            if (server == null) {
                // Wrap around to the beginning if no server index >= (i % k) is available
                server = free.first();
            }
            
            // 4. Assign the request to the chosen server
            count[server]++;
            busy.offer(new int[]{end, server});
            free.remove(server);
        }
        
        // 5. Find the maximum requests handled by any server
        int maxRequests = 0;
        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }
        
        // 6. Gather all servers that match the max request count
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
