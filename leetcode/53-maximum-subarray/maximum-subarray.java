class Solution {
    public int maxSubArray(int[] nums) {
        int l=nums.length;
        int result=nums[0];
        int Sum=nums[0];
        for(int i=1;i<l;i++)
        {
            Sum=Math.max(Sum+nums[i],nums[i]);
            result=Math.max(result,Sum);
        }
        return result;
        
    }
}