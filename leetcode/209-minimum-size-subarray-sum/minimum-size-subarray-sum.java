class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n=nums.length;
        int min=nums.length+1;
        int sum=0;
        int start=0;

        for(int r=0;r<nums.length;r++)
        {
            // int l=0;
            sum+=nums[r];

            while(sum>=target)
            {
                min=Math.min(min, r-start+1);
                sum-=nums[start];
                start++;
            }
        }
       if(min==nums.length+1)

       {
        return 0;
       }
       return min;
    }
}