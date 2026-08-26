class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        int minLen = Integer.MAX_VALUE;

        // Collect all indices where s[i] == '1'
        java.util.List<Integer> ones = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }

        // If total '1's are less than k, no beautiful substring exists
        if (ones.size() < k) {
            return "";
        }

        // Sliding window over every group of k ones
        for (int i = 0; i <= ones.size() - k; i++) {
            int start = ones.get(i);
            int end = ones.get(i + k - 1);
            String sub = s.substring(start, end + 1);
            int len = sub.length();

            if (len < minLen) {
                minLen = len;
                result = sub;
            } else if (len == minLen && sub.compareTo(result) < 0) {
                result = sub;
            }
        }

        return result;
    }
}