class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int n = s.length();
        int lastEnd = -1; // Tracks the end index of the last chosen palindrome

        for (int i = 0; i < n; i++) {
            // Expand around center i for odd length (length k)
            // or around i and i + 1 for even length (length k or k + 1)
            for (int len : new int[]{k, k + 1}) {
                int left = i - (len - 1) / 2;
                int right = i + len / 2;

                // Check if expansion is within bounds and valid position
                if (left > lastEnd && right < n && isPalindrome(s, left, right)) {
                    count++;
                    lastEnd = right; // Update end marker
                    break;           // Greedily take the earliest valid match
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}