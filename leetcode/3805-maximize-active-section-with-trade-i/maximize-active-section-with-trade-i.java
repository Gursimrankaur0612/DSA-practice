class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                initialOnes++;
            }
        }

        // Form augmented string t = '1' + s + '1'
        String t = "1" + s + "1";

        // Collect lengths of contiguous zero blocks and one blocks
        java.util.List<Integer> zeros = new java.util.ArrayList<>();
        
        int n = t.length();
        int i = 0;
        
        while (i < n) {
            // Skip block of ones
            while (i < n && t.charAt(i) == '1') {
                i++;
            }
            if (i >= n) break;
            
            // Count length of zero block
            int zeroCount = 0;
            while (i < n && t.charAt(i) == '0') {
                zeroCount++;
                i++;
            }
            zeros.add(zeroCount);
        }

        // Find maximum sum of two adjacent zero blocks
        int maxDelta = 0;
        for (int j = 0; j < zeros.size() - 1; j++) {
            maxDelta = Math.max(maxDelta, zeros.get(j) + zeros.get(j + 1));
        }

        return initialOnes + maxDelta;
    }
}