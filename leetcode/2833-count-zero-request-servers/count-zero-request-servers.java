import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i; // Store original index
        }
        
        // Sort queries by time
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] result = new int[m];
        HashMap<Integer, Integer> serverCount = new HashMap<>();
        
        int left = 0, right = 0;
        int numLogs = logs.length;
        
        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int origIdx = sortedQueries[i][1];
            int windowStart = qTime - x;
            int windowEnd = qTime;
            
            // Add logs that fall within the right bound (<= windowEnd)
            while (right < numLogs && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                serverCount.put(serverId, serverCount.getOrDefault(serverId, 0) + 1);
                right++;
            }
            
            // Remove logs that fall outside the left bound (< windowStart)
            while (left < numLogs && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCount.put(serverId, serverCount.get(serverId) - 1);
                if (serverCount.get(serverId) == 0) {
                    serverCount.remove(serverId);
                }
                left++;
            }
            
            // Active servers are those with at least one request in the window
            int activeServers = serverCount.size();
            result[origIdx] = n - activeServers;
        }
        
        return result;
    }
}