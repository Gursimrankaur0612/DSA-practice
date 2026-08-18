import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> subarrayCounts = new HashMap<>();

        // Slide window of size k
        for (int i = 0; i <= n - k; i++) {
            Set<Integer> uniqueInWindow = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                uniqueInWindow.add(nums[j]);
            }
            for (int num : uniqueInWindow) {
                subarrayCounts.put(num, subarrayCounts.getOrDefault(num, 0) + 1);
            }
        }

        int maxVal = -1;
        for (Map.Entry<Integer, Integer> entry : subarrayCounts.entrySet()) {
            if (entry.getValue() == 1) {
                maxVal = Math.max(maxVal, entry.getKey());
            }
        }

        return maxVal;
    }
}