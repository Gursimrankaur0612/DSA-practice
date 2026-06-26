import java.util.*;

class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // Step 1: Compute prefix sums
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            pref[i + 1] = pref[i] + val;
        }
        
        // Step 2: Coordinate Compression
        // Values can range from -n to n, so adding 'n + 1' maps them to a positive range [1, 2n + 1]
        int offset = n + 1;
        int maxTreeSize = 2 * n + 2;
        FenwickTree bit = new FenwickTree(maxTreeSize);
        
        long totalSubarrays = 0;
        
        // Step 3: Count pairs using Fenwick Tree
        for (int j = 0; j <= n; j++) {
            int transformedValue = pref[j] + offset;
            
            // Query numbers strictly less than transformedValue
            totalSubarrays += bit.query(transformedValue - 1);
            
            // Add current prefix sum position into the Fenwick tree
            bit.update(transformedValue, 1);
        }
        
        return totalSubarrays;
    }
    
    // Simple Binary Indexed Tree implementation
    class FenwickTree {
        int[] tree;
        int size;
        
        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size];
        }
        
        public void update(int i, int delta) {
            while (i < size) {
                tree[i] += delta;
                i += i & (-i);
            }
        }
        
        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & (-i);
            }
            return sum;
        }
    }
}