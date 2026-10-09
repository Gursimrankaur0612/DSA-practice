class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0; // Represents the number of required ')'
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If open is odd, we need 1 more ')' to complete a previous pair
                if (open % 2 != 0) {
                    insertions++;
                    open--; // Completed the pair
                }
                open += 2; // Each '(' needs two ')'
            } else { // c == ')'
                open--;
                if (open < 0) {
                    // Need to insert one '('
                    insertions++;
                    open += 2; // '(' gives us 2 required ')'s, but we just consumed 1
                }
            }
        }
        
        return insertions + open;
    }
}