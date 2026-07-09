class Solution {
    public String makeGood(String s) {
        StringBuilder stack= new StringBuilder();
        for(char ch:s.toCharArray())
        {
            int length=stack.length();

            if(length >0 && Math.abs(stack.charAt(length-1)-ch)==32)
            {
                stack.deleteCharAt(length-1);
            }
            else
            {
                stack.append(ch);
            }
        }
        return stack.toString();
    }
}