class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // 1. Build left maxes (left-to-right within blocks of size k)
        for (int i = 0; i < n; i++) {
            if (i % k == 0) {
                left[i] = nums[i]; // Start of a block
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
        }

        // 2. Build right maxes (right-to-left within blocks of size k)
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if ((i + 1) % k == 0) {
                right[i] = nums[i]; // End of a block
            } else {
                right[i] = Math.max(right[i + 1], nums[i]);
            }
        }

        // 3. Construct the maximums for each sliding window
        int[] result = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            result[i] = Math.max(right[i], left[i + k - 1]);
        }

        return result;
    }
}