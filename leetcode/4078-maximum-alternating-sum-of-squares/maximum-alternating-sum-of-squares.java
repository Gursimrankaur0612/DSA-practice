import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] absNums = new long[n];
        for (int i = 0; i < n; i++) {
            absNums[i] = (long) Math.abs(nums[i]);
        }
        
        // Sort the absolute values
        Arrays.sort(absNums);
        
        long score = 0;
        int left = 0, right = n - 1;
        boolean add = true; // Alternating flag: true for adding (largest), false for subtracting (smallest)
        
        // Greedily pick from the extremes of the sorted array
        while (left <= right) {
            if (add) {
                score += absNums[right] * absNums[right];
                right--;
            } else {
                score -= absNums[left] * absNums[left];
                left++;
            }
            add = !add;
        }
        
        return score;
    }
}