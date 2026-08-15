class Solution {
    public int longestSubsequence(int[] nums) {
        int total=0;

        boolean hasnonzero=false;

        for(int num:nums)
        {
            total^=num;
            if(num!=0)
            {
                hasnonzero=true;
            }
        }
        if(total!=0)
        {
            return nums.length;
        }
        if(hasnonzero)
        {
            return nums.length-1;
        }
        return 0;
    }
}