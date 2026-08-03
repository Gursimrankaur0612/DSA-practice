class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[4]; // Circular array for O(1) space

        for (int i = n - 1; i >= 0; i--) {
            int take = 0;
            dp[i % 4] = Integer.MIN_VALUE;

            for (int k = 0; k < 3 && i + k < n; k++) {
                take += stoneValue[i + k];
                dp[i % 4] = Math.max(dp[i % 4], take - dp[(i + k + 1) % 4]);
            }
        }

        int diff = dp[0];
        if (diff > 0) return "Alice";
        if (diff < 0) return "Bob";
        return "Tie";
    }
}