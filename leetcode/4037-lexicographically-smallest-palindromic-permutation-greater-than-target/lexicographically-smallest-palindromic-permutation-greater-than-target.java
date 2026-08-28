import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        char mid = 0;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                mid = (char) ('a' + i);
            }
            halfCount[i] = count[i] / 2;
        }

        if (oddCount > 1) {
            return "";
        }

        int m = n / 2;

        // Try prefix matches from length m down to 0
        for (int len = m; len >= 0; len--) {
            int[] currentHalfCount = halfCount.clone();
            boolean validPrefix = true;
            char[] prefix = new char[m];

            // Build exact match prefix up to 'len'
            for (int i = 0; i < len; i++) {
                char c = target.charAt(i);
                if (currentHalfCount[c - 'a'] > 0) {
                    prefix[i] = c;
                    currentHalfCount[c - 'a']--;
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            // If len == m, check if exact same first half produces a palindrome strictly > target
            if (len == m) {
                StringBuilder sb = new StringBuilder();
                sb.append(new String(prefix));
                if (n % 2 != 0) sb.append(mid);
                for (int i = m - 1; i >= 0; i--) sb.append(prefix[i]);
                
                String candidate = sb.toString();
                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
                continue;
            }

            // Try picking a character larger than target[len] at index 'len'
            char targetChar = target.charAt(len);
            for (int c = targetChar - 'a' + 1; c < 26; c++) {
                if (currentHalfCount[c] > 0) {
                    int[] tempCount = currentHalfCount.clone();
                    tempCount[c]--;
                    prefix[len] = (char) ('a' + c);

                    // Fill remaining positions in first half lexicographically
                    int pos = len + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (tempCount[ch] > 0) {
                            prefix[pos++] = (char) ('a' + ch);
                            tempCount[ch]--;
                        }
                    }

                    // Construct full palindrome
                    StringBuilder sb = new StringBuilder();
                    sb.append(new String(prefix));
                    if (n % 2 != 0) sb.append(mid);
                    for (int i = m - 1; i >= 0; i--) sb.append(prefix[i]);

                    return sb.toString();
                }
            }
        }

        return "";
    }
}