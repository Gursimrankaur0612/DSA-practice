class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Find middle character if length is odd
        char mid = 0;
        int halfLen = n / 2;
        int[] halfCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                mid = (char) ('a' + i);
            }
            halfCnt[i] = freq[i] / 2;
        }

        // Check if total possible palindromic permutations < k
        long totalPerms = countPermutations(halfCnt, k);
        if (totalPerms < k) {
            return "";
        }

        // Build the first half character by character
        StringBuilder firstHalf = new StringBuilder();
        for (int i = 0; i < halfLen; i++) {
            for (int c = 0; c < 26; c++) {
                if (halfCnt[c] > 0) {
                    halfCnt[c]--;
                    long count = countPermutations(halfCnt, k);

                    if (count >= k) {
                        firstHalf.append((char) ('a' + c));
                        break; // Fix character c at position i
                    } else {
                        k -= count;
                        halfCnt[c]++; // Backtrack
                    }
                }
            }
        }

        // Reconstruct the full palindromic string
        StringBuilder result = new StringBuilder(firstHalf);
        if (n % 2 != 0) {
            result.append(mid);
        }
        result.append(new StringBuilder(firstHalf).reverse());

        return result.toString();
    }

    // Helper method to compute exact multinomial permutations capped at k + 1
    private long countPermutations(int[] cnt, long k) {
        int total = 0;
        for (int c : cnt) total += c;
        if (total == 0) return 1;

        long perms = 1;
        int remaining = total;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            
            // Choose cnt[i] spots out of remaining total spots
            long combinations = nCr(remaining, cnt[i], k);
            
            // Multiply combinations while capping at k + 1 to avoid overflow
            perms = capMul(perms, combinations, k);
            if (perms > k) return k + 1;
            
            remaining -= cnt[i];
        }

        return perms;
    }

    // Computes C(n, r) capped at k + 1
    private long nCr(int n, int r, long k) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n - r) r = n - r; // Use symmetry

        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;
            if (res > k) return k + 1;
        }
        return res;
    }

    // Safe multiplication capped at k + 1
    private long capMul(long a, long b, long k) {
        if (a == 0 || b == 0) return 0;
        if (a > (k + 1) / b) return k + 1;
        return a * b;
    }
}