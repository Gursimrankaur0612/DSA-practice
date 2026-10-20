import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // 1. Precompute total number of "drops" up to index i
        // A drop happens when nums[i] > nums[i + 1]
        int[] dropPrefix = new int[n];
        for (int i = 0; i < n - 1; i++) {
            dropPrefix[i + 1] = dropPrefix[i] + (nums[i] > nums[i + 1] ? 1 : 0);
        }

        // 2. Precompute the suffix minimums and prefix maximums 
        // to verify that blocks are sorted relative to each other.
        int[] maxInBlock = new int[n]; 
        int[] minInBlock = new int[n];

        int totalSum = 0;

        // 3. Iterate over all divisors k of n
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean isSortable = true;
            int lastMax = Integer.MIN_VALUE;

            // Check each block of size k
            for (int i = 0; i < n; i += k) {
                int start = i;
                int end = i + k - 1;

                // O(1) count of drops strictly inside the block (start to end)
                int innerDrops = dropPrefix[end] - dropPrefix[start];

                if (innerDrops > 1) {
                    isSortable = false;
                    break;
                }

                // If there's exactly 1 drop, it must be cyclically valid (end <= start)
                if (innerDrops == 1 && nums[end] > nums[start]) {
                    isSortable = false;
                    break;
                }

                // Find block min and max to ensure overall global sorted ordering
                int currentMin = nums[start];
                int currentMax = nums[start];
                for (int j = start; j <= end; j++) {
                    if (nums[j] < currentMin) currentMin = nums[j];
                    if (nums[j] > currentMax) currentMax = nums[j];
                }

                // The whole block must be greater than or equal to the maximum of the previous block
                // If there's a split/drop, the split elements must respect global order bounds
                if (innerDrops == 0) {
                    if (nums[start] < lastMax) {
                        isSortable = false;
                        break;
                    }
                } else {
                    // For 1 drop, the elements before the drop are larger than the elements after.
                    // We need to find the drop index to check bounds strictly.
                    int dropIdx = start;
                    for (int j = start; j < end; j++) {
                        if (nums[j] > nums[j + 1]) {
                            dropIdx = j;
                            break;
                        }
                    }
                    // Elements after drop (nums[dropIdx+1 ... end]) are the smallest in the block
                    if (nums[dropIdx + 1] < lastMax) {
                        isSortable = false;
                        break;
                    }
                }

                lastMax = currentMax;
            }

            if (isSortable) {
                totalSum += k;
            }
        }

        return totalSum;
    }
}
