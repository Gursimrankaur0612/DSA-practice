class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums==null || nums.length<3)
            return 0;

            int totalslices=0;
            int currslices=0;
        
        for(int i=2;i<nums.length;i++)
        {
            if((nums[i]-nums[i-1])==(nums[i-1]-nums[i-2]))
            {
                currslices++;
                totalslices+=currslices;
            }
            else
            {
                currslices=0;
            }
        }
        return totalslices;
    }
}