class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        if(k<=0) return 0;

        int product=1;
        int start=0;
        int count=0;

        for(int r=0;r<nums.length;r++)
        {
            product*=nums[r];
            while(product>=k)
            {
                product/=nums[start];
                start++;
            }
            count+=(r-start+1);

        }
        return count;
    }
}