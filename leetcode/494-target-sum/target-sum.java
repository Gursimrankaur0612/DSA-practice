class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum=0;

        for(int i=0;i<nums.length;i++)
        {
            sum=sum+nums[i];
        }
        
        if((sum+target)%2!=0 || sum<Math.abs(target))
        {
            return 0;
        }

        int s=(sum+target)/2;
        int a[]=new int[s+1];

        a[0]=1;

        for(int i=0;i<nums.length;i++)
        {
            for(int j=s;j>=nums[i];j--)
            {
                a[j]=a[j]+a[j-nums[i]];
            }
        }
        return a[s];
    }
}