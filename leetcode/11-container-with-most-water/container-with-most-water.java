class Solution {
    public int maxArea(int[] height) {
        int n=height.length;
        int ans=0;
       int l=0;
       int r=n-1;
       while(l<r)
       {
         int width=r-l;
         int he=Math.min(height[l],height[r]);
         int curr=width*he;
         ans=Math.max(ans,curr);

         if(height[l]<height[r])l++;
         else r--;
       }
        return ans;
    }
}