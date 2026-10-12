import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[32];
        
        // Count the frequency of set bits at each position
        for (int x : nums) {
            for (int i = 0; i < 30; i++) {
                if ((x & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        
        long[] maxNums = new long[k];
        
        // Greedily set bits for the top k numbers from highest bit to lowest
        for (int i = 29; i >= 0; i--) {
            for (int j = 0; j < k; j++) {
                if (bitCount[i] > 0) {
                    maxNums[j] |= (1L << i);
                    bitCount[i]--;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long totalSum = 0;
        
        // Calculate the sum of squares modulo 10^9 + 7
        for (int i = 0; i < k; i++) {
            long val = maxNums[i] % mod;
            totalSum = (totalSum + (val * val) % mod) % mod;
        }
        
        return (int) totalSum;
    }
}