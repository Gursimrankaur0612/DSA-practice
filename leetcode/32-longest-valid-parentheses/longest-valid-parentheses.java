class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        
        // Push -1 onto the stack to act as a base boundary index
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                // Store the index of the opening parenthesis
                stack.push(i);
            } else {
                // Pop the last unmatched '(' or previous boundary index
                stack.pop();

                if (stack.isEmpty()) {
                    // Current ')' has no matching '(', set current index as new boundary
                    stack.push(i);
                } else {
                    // Calculate valid substring length from last boundary index
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}