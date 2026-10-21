class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        // Length 2 that is not sorted cannot be fixed since we can only pick proper substrings
        if (n == 2) return -1;
        
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 0; i < n; i++) {
            minChar = (char) Math.min(minChar, s.charAt(i));
            maxChar = (char) Math.max(maxChar, s.charAt(i));
        }
        
        // Check if 1 operation is enough
        // Condition: min char is at the start (proper suffix can be sorted) 
        // or max char is at the end (proper prefix can be sorted)
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }
        
        // Check if 3 operations are needed or if 2 suffice
        boolean hasMinInProperPrefix = false;
        boolean hasMaxInProperSuffix = false;
        
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) hasMinInProperPrefix = true;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) hasMaxInProperSuffix = true;
        }
        
        if (!hasMinInProperPrefix && !hasMaxInProperSuffix) {
            // Check edge cases where it might take 3
            // If the single max is at index 0 and single min is at index n-1
            int minCount = 0, maxCount = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == minChar) minCount++;
                if (s.charAt(i) == maxChar) maxCount++;
            }
            if (s.charAt(0) == maxChar && maxCount == 1 && s.charAt(n - 1) == minChar && minCount == 1) {
                return 3;
            }
        }
        
        return 2;
    }
}