class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n]; // best[i] = min length of subarray sum=target up to index i
        
        // Initialize best array with a large value representing infinity
        final int INF = 1000000;
        java.util.Arrays.fill(best, INF);
        
        int left = 0;
        int currentSum = 0;
        int ans = INF;
        int minLenSoFar = INF;

        for (int right = 0; right < n; right++) {
            currentSum += arr[right];

            // Shrink window from left if current sum exceeds target
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }

            // Found a valid subarray with sum == target
            if (currentSum == target) {
                int currentLen = right - left + 1;

                // Check if there's a valid non-overlapping subarray before index 'left'
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, currentLen + best[left - 1]);
                }

                minLenSoFar = Math.min(minLenSoFar, currentLen);
            }

            // Update best[right] with the shortest valid subarray found up to 'right'
            best[right] = minLenSoFar;
        }

        return ans == INF ? -1 : ans;
    }
}