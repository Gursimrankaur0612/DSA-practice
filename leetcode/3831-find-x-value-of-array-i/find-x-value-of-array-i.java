class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = num % k;

            // Start a new subarray consisting of only nums[i]
            nextDp[val]++;

            // Extend existing subarrays ending at the previous position
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    nextDp[(r * val) % k] += dp[r];
                }
            }

            // Accumulate counts for all subarrays ending at the current position
            for (int r = 0; r < k; r++) {
                ans[r] += nextDp[r];
            }

            dp = nextDp;
        }

        return ans;
    }
}