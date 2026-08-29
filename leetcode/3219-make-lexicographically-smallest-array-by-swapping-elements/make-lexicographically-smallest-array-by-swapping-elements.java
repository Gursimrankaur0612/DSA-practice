import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Store value along with original index
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }
        
        // Sort pairs by value
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        while (i < n) {
            int j = i;
            List<Integer> indices = new ArrayList<>();
            
            // Expand component as long as difference between consecutive sorted values <= limit
            while (j < n && (j == i || paired[j][0] - paired[j - 1][0] <= limit)) {
                indices.add(paired[j][1]);
                j++;
            }
            
            // Sort indices to place smallest values in leftmost positions
            Collections.sort(indices);
            
            // Place sorted values into sorted original indices
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = paired[i + k][0];
            }
            
            i = j;
        }
        
        return result;
    }
}