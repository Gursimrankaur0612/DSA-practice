import java.util.Stack;
class Solution {
    public int largestRectangleArea(int[] heights) {
       Stack <Integer> stack=new Stack<>();
       int maxarea=0;
       int n=heights.length;
       for(int i=0;i<=n;i++)
       {
        int currheight=(i==n)?0:heights[i];
        while(!stack.isEmpty() && currheight<heights[stack.peek()])
        {
            int height=heights[stack.pop()];


            int width=stack.isEmpty()?i:i-stack.peek()-1;
            maxarea=Math.max(maxarea,height*width);
        }
        stack.push(i);
       }
    return maxarea;
    }
}