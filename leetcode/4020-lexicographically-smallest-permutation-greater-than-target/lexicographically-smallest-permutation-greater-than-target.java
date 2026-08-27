class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Try to match the maximum prefix target[0...i-1]
        for (int i = n - 1; i >= 0; i--) {
            int[] count = freq.clone();
            boolean possible = true;

            // Check if we have enough characters to match target[0 ... i-1]
            for (int j = 0; j < i; j++) {
                char tChar = target.charAt(j);
                if (count[tChar - 'a'] > 0) {
                    count[tChar - 'a']--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

            // At position i, try to pick the smallest character strictly greater than target[i]
            for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                if (count[c] > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0, i));
                    sb.append((char) ('a' + c));
                    count[c]--;

                    // Fill the rest in ascending order to make it lexicographically smallest
                    for (int ch = 0; ch < 26; ch++) {
                        while (count[ch] > 0) {
                            sb.append((char) ('a' + ch));
                            count[ch]--;
                        }
                    }

                    return sb.toString();
                }
            }
        }

        return "";
    }
}