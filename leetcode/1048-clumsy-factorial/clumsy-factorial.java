class Solution {
    public int clumsy(int n) {
        Stack<Integer> stack =new Stack<>();
        stack.push(n);

        int op=0;

        for(int curr=n-1;curr>0;curr--)
        {
            if(op==0)
            {
                stack.push(stack.pop()*curr);
            }
            else if(op==1)
            {
                stack.push(stack.pop()/curr);
            }
            else if(op==2)
            {
                stack.push(curr);
            }
            else if(op==3)
            {
                stack.push(-curr);
            }
            op=(op+1)%4;
        }
        int result=0;
        while(!stack.isEmpty())
        {
            result+=stack.pop();
        }
        return result;
    }
}