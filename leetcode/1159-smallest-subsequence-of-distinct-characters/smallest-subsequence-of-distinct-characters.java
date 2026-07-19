class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        // Record the last occurrence index of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        boolean[] seen = new boolean[26];
        StringBuilder stack = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            int currIdx = curr - 'a';
            
            // If the character is already in our optimal subsequence, skip it
            if (seen[currIdx]) {
                continue;
            }
            
            // Pop characters from the stack if they are larger than curr
            // and will appear again later in the string
            while (stack.length() > 0 && 
                   stack.charAt(stack.length() - 1) > curr && 
                   lastIndex[stack.charAt(stack.length() - 1) - 'a'] > i) {
                
                char removed = stack.charAt(stack.length() - 1);
                seen[removed - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }
            
            // Add the current character to the stack
            stack.append(curr);
            seen[currIdx] = true;
        }
        
        return stack.toString();
    }
}