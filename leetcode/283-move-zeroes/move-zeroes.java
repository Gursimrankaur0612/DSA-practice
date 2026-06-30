class Solution {
    public void moveZeroes(int[] nums) {
      int n=nums.length;
      int insert=0;

      for(int i=0;i<n;i++)
      {
        if(nums[i]!=0)
        {
            nums[insert]=nums[i];
            insert++;
        }
      }
      while(insert<n)
      {
        nums[insert++]=0;
      }

    }
}






















        