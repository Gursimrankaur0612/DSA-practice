class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // Find the index of the number 0
        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }

        // Count how many elements are out of order reading left-to-right
        int drops = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                drops++;
            }
        }

        int minOps = Integer.MAX_VALUE;

        // Condition 1: Elements increase to the right (Clockwise)
        // There should be exactly 1 point where the value drops (from n-1 to 0)
        if (drops == 1) {
            // Strategy A: Only Left Rotations
            minOps = Math.min(minOps, zeroIdx);
            
            // Strategy B: Reverse -> Left Rotate -> Reverse again
            minOps = Math.min(minOps, n - zeroIdx + 2);
        }

        // Condition 2: Elements increase to the left (Counter-Clockwise)
        // When reading left-to-right, it should drop (n - 1) times
        if (drops == n - 1) {
            // Strategy C: Reverse -> Left Rotate
            minOps = Math.min(minOps, n - zeroIdx);
            
            // Strategy D: Left Rotate -> Reverse
            minOps = Math.min(minOps, zeroIdx + 2);
        }

        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }
}
