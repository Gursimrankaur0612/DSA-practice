class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // dp[g1][g2] stores the number of subsequence pairs with gcd g1 and g2
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long currentWays = dp[g1][g2];

                    // Choice 1: Skip x
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + currentWays) % MOD);

                    // Choice 2: Add x to the first subsequence
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    nextDp[ng1][g2] = (int) ((nextDp[ng1][g2] + currentWays) % MOD);

                    // Choice 3: Add x to the second subsequence
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    nextDp[g1][ng2] = (int) ((nextDp[g1][ng2] + currentWays) % MOD);
                }
            }
            dp = nextDp;
        }

        // Gather all pairs where seq1 and seq2 are non-empty (g >= 1) and have equal GCD
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}