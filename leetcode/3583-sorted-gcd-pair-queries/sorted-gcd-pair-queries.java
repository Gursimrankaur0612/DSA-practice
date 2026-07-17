import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Step 1: Count frequency of each number in nums
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // Step 2: Count how many elements are multiples of i
        long[] cnt = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            for (int j = i; j <= maxVal; j += i) {
                cnt[i] += freq[j];
            }
        }

        // Step 3 & 4: Calculate exact number of pairs with GCD equal to i
        long[] exactGcd = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long totalPairs = cnt[i] * (cnt[i] - 1) / 2;
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= exactGcd[j];
            }
            exactGcd[i] = totalPairs;
        }

        // Step 5: Create prefix sum array of GCD counts
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + exactGcd[i];
        }

        // Step 6: Answer each query using binary search
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            ans[i] = binarySearch(prefixSum, target);
        }

        return ans;
    }

    private int binarySearch(long[] prefixSum, long target) {
        int low = 1, high = prefixSum.length - 1;
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prefixSum[mid] > target) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid GCD
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}