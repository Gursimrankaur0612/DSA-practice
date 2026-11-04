import java.util.TreeMap;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        
        // Step 1: Calculate the cost to equalize every single possible window of size x
        long[] windowCost = new long[n - x + 1];
        
        // Two TreeMaps to track lower and upper halves of the sliding window
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;
        
        for (int i = 0; i < n; i++) {
            // Add the new element
            int incoming = nums[i];
            if (leftSize == 0 || incoming <= left.lastKey()) {
                left.put(incoming, left.getOrDefault(incoming, 0) + 1);
                leftSum += incoming;
                leftSize++;
            } else {
                right.put(incoming, right.getOrDefault(incoming, 0) + 1);
                rightSum += incoming;
                rightSize++;
            }
            
            // Remove the old element out of the current sliding window
            if (i >= x) {
                int outgoing = nums[i - x];
                if (left.containsKey(outgoing)) {
                    int count = left.get(outgoing);
                    if (count == 1) left.remove(outgoing);
                    else left.put(outgoing, count - 1);
                    leftSum -= outgoing;
                    leftSize--;
                } else {
                    int count = right.get(outgoing);
                    if (count == 1) right.remove(outgoing);
                    else right.put(outgoing, count - 1);
                    rightSum -= outgoing;
                    rightSize--;
                }
            }
            
            // Rebalance the heaps: left should hold exactly Ceil(x / 2) elements
            int targetLeftSize = (x + 1) / 2;
            while (leftSize < targetLeftSize && rightSize > 0) {
                int firstKey = right.firstKey();
                int count = right.get(firstKey);
                if (count == 1) right.remove(firstKey);
                else right.put(firstKey, count - 1);
                rightSum -= firstKey;
                rightSize--;
                
                left.put(firstKey, left.getOrDefault(firstKey, 0) + 1);
                leftSum += firstKey;
                leftSize++;
            }
            while (leftSize > targetLeftSize) {
                int lastKey = left.lastKey();
                int count = left.get(lastKey);
                if (count == 1) left.remove(lastKey);
                else left.put(lastKey, count - 1);
                leftSum -= lastKey;
                leftSize--;
                
                right.put(lastKey, right.getOrDefault(lastKey, 0) + 1);
                rightSum += lastKey;
                rightSize++;
            }
            
            // If we have a full valid window, compute the absolute difference cost
            if (i >= x - 1) {
                long median = left.lastKey();
                // Total distance to median = (median * leftSize - leftSum) + (rightSum - median * rightSize)
                long cost = (median * leftSize - leftSum) + (rightSum - median * rightSize);
                windowCost[i - x + 1] = cost;
            }
        }
        
        // Step 2: Dynamic Programming to choose k non-overlapping windows
        // dp[j] represents the minimum operations to form j subarrays using elements seen so far
        long[] dp = new long[k + 1];
        java.util.Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0; // 0 operations needed to get 0 subarrays
        
        // nextDp tracks the states transitions for the current index iteratively
        long[][] memo = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(memo[i], Long.MAX_VALUE / 2);
        }
        memo[0][0] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (memo[i][j] >= Long.MAX_VALUE / 2) continue;
                
                // Choice 1: Skip the current element
                memo[i + 1][j] = Math.min(memo[i + 1][j], memo[i][j]);
                
                // Choice 2: Pick the window starting at index i (if possible)
                if (j < k && i <= n - x) {
                    memo[i + x][j + 1] = Math.min(memo[i + x][j + 1], memo[i][j] + windowCost[i]);
                }
            }
        }
        
        return memo[n][k];
    }
}
