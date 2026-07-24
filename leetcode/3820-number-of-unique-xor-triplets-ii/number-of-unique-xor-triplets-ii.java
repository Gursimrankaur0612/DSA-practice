class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Extract unique values from nums
        boolean[] present = new boolean[2048];
        int uniqueCount = 0;
        for (int x : nums) {
            if (!present[x]) {
                present[x] = true;
                uniqueCount++;
            }
        }
        
        int[] uniq = new int[uniqueCount];
        int idx = 0;
        for (int i = 0; i < 2048; i++) {
            if (present[i]) {
                uniq[idx++] = i;
            }
        }
        
        // Step 2: Compute all unique XOR results of 2 elements
        boolean[] has2 = new boolean[2048];
        for (int i = 0; i < uniq.length; i++) {
            for (int j = i; j < uniq.length; j++) {
                has2[uniq[i] ^ uniq[j]] = true;
            }
        }
        
        // Step 3: Compute all unique XOR results of 3 elements
        boolean[] has3 = new boolean[2048];
        for (int i = 0; i < 2048; i++) {
            if (has2[i]) {
                for (int x : uniq) {
                    has3[i ^ x] = true;
                }
            }
        }
        
        // Step 4: Count total unique XOR values
        int count = 0;
        for (boolean b : has3) {
            if (b) {
                count++;
            }
        }
        
        return count;
    }
}