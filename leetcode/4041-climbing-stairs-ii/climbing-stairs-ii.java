class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[j] stores the minimum cost to reach step j
        int[] dp = new int[n + 1];
        
        // Base case: starting at step 0 with cost 0
        dp[0] = 0;
        
        for (int j = 1; j <= n; j++) {
            int minCost = Integer.MAX_VALUE;
            
            // Check jumps from j-1, j-2, and j-3
            for (int k = 1; k <= 3; k++) {
                int i = j - k;
                if (i >= 0) {
                    int jumpCost = dp[i] + costs[j - 1] + (j - i) * (j - i);
                    minCost = Math.min(minCost, jumpCost);
                }
            }
            
            dp[j] = minCost;
        }
        
        return dp[n];
    }
}