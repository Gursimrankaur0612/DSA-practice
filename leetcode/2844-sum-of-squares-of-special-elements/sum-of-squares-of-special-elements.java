class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                sum += nums[i - 1] * nums[i - 1];
            }
        }
        
        for (int i = 0; i < n; i++) {
            // Alternatively, using 0-indexed loop:
            // int index = i + 1;
            // if (n % index == 0) {
            //     sum += nums[i] * nums[i];
            // }
        }
        
        return sum;
    }
}