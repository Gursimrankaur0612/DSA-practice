import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int half = n / 2;
        
      
        char[] halfChars = s.substring(0, half).toCharArray();
        
      
        Arrays.sort(halfChars);
        
        String left = new String(halfChars);
        String right = new StringBuilder(left).reverse().toString();
        
        
        if (n % 2 == 0) {
            return left + right;
        } else {
            char mid = s.charAt(half);
            return left + mid + right;
        }
    }
}