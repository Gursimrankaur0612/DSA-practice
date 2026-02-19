class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left=1;
        int right=0;
        for(int i:piles)
        {
            right=Math.max(right,i);
        }
        while(left<right)
        {
            int mid=(left+right)/2;
            if(caneat(piles,h,mid))
            {
                right=mid; 
            }
            else
            {
                left=mid+1;
            }
        }
        return left;
    }

    private boolean caneat(int[]piles,int h,int s)
    {
        int hours=0;
        for(int i:piles)
        {
            hours+=(i+s-1)/s;
        }
        return hours<= h;
    }
}
    