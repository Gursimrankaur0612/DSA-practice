class Solution {
    public int trap(int[] height) {
        if(height==null ||height.length<3)return 0;
        int left=0;
        int right=height.length-1;

        int rmax=0;
        int lmax=0;

        int ans=0;

        while(left<right)
        {
            if(height[left]<height[right])
            {
                if(height[left]>=lmax)
                {
                    lmax=height[left];
                }
                else
                {
                    ans+=lmax-height[left];
                }
                left++;
            }
            else
            {
                if(height[right]>=rmax)
                {
                    rmax=height[right];
                }
                else
                {
                    ans+=rmax-height[right];
                }
                right--;
            }
        }
        return ans;
    }
}