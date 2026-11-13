import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        // Stores {adjustedValue, blockCount} in non-increasing order from back to front
        Deque<int[]> dq = new ArrayDeque<>();

        for (int i = nums.length - 1, j = nums.length - 1; i >= 0; --i) {
            int num = nums[i];
            int count = 1;

            // Merge smaller blocks to the right up to the current element's value
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] last = dq.pollLast();
                int nextNum = last[0];
                int nextCount = last[1];
                
                count += nextCount;
                cost += (long) (num - nextNum) * nextCount;
            }
            dq.addLast(new int[]{num, count});

            // Shrink the right end of the window until total operations <= k
            while (cost > k) {
                int[] first = dq.peekFirst();
                cost -= (first[0] - nums[j]);
                --j;
                
                if (--first[1] == 0) {
                    dq.pollFirst();
                }
            }

            // Every subarray starting at i and ending between i and j is valid
            ans += (j - i + 1);
        }

        return ans;
    }
}
