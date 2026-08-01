class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] memo = new Integer[n][n];
        
        // Player 1 wins if score difference (Player 1 - Player 2) >= 0
        return maxScoreDiff(nums, 0, n - 1, memo) >= 0;
    }

    private int maxScoreDiff(int[] nums, int left, int right, Integer[][] memo) {
        // Base case: only one element left, the current player takes it
        if (left == right) {
            return nums[left];
        }

        // Return memoized result if available
        if (memo[left][right] != null) {
            return memo[left][right];
        }

        // Take left element vs take right element
        int takeLeft = nums[left] - maxScoreDiff(nums, left + 1, right, memo);
        int takeRight = nums[right] - maxScoreDiff(nums, left, right - 1, memo);

        // Maximize current player's relative score
        return memo[left][right] = Math.max(takeLeft, takeRight);
    }
}