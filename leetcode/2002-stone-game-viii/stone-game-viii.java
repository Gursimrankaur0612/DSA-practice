class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] pref = new int[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }

        // Base case: picking the entire array at index n - 1
        int maxDiff = pref[n - 1];

        // Process backward from index n - 2 down to 1 (since x > 1, i >= 1)
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, pref[i] - maxDiff);
        }

        return maxDiff;
    }
}