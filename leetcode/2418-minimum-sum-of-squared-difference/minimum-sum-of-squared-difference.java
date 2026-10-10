import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long) k1 + k2;
        
        // Find the maximum difference to size our frequency array
        int maxDiff = 0;
        int[] diffCounts = new int[100001];
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            diffCounts[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }
        
        // Greedily reduce the largest differences
        for (int i = maxDiff; i > 0 && totalOps > 0; i--) {
            if (diffCounts[i] == 0) continue;
            
            // Number of elements we can reduce from difference i to i - 1
            long countToReduce = Math.min(totalOps, diffCounts[i]);
            
            diffCounts[i] -= countToReduce;
            diffCounts[i - 1] += countToReduce;
            totalOps -= countToReduce;
        }
        
        // Calculate the minimum sum of squared differences
        long minSum = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (diffCounts[i] > 0) {
                minSum += (long) diffCounts[i] * i * i;
            }
        }
        
        return minSum;
    }
}