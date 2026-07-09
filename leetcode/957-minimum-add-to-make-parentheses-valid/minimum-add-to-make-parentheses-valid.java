class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack=new Stack<>();
        int count=0;
        for(char c:s.toCharArray())
        {
            if(c=='(')
            {
                stack.push(')');
                
            }
            else if(!stack.isEmpty())
            {
                stack.pop();
            }
            else
            {
                count++;
            }
        }
        return stack.size()+count;
    }
}