class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[j] stores the maximum index in word1 that can start matching word2[j...m-1]
        int[] last = new int[m];
        int ptr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(j)) {
                ptr--;
            }
            last[j] = ptr;
            ptr--; // Move pointer left for the next character match
        }

        int[] result = new int[m];
        boolean usedChange = false;
        int i = 0;

        for (int j = 0; j < m; j++) {
            boolean matched = false;

            while (i < n) {
                // Option 1: Exact character match
                if (word1.charAt(i) == word2.charAt(j)) {
                    result[j] = i;
                    i++;
                    matched = true;
                    break;
                }

                // Option 2: Mismatch, but we can change word1[i] to match word2[j]
                if (!usedChange) {
                    // Check if the remaining suffix word2[j + 1 ... m - 1] fits in word1[i + 1 ... n - 1]
                    if (j + 1 == m || last[j + 1] > i) {
                        result[j] = i;
                        usedChange = true;
                        i++;
                        matched = true;
                        break;
                    }
                }

                i++;
            }

            // If we couldn't match word2[j], no valid sequence exists
            if (!matched) {
                return new int[0];
            }
        }

        return result;
    }
}