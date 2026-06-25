class Solution {
    public int findDuplicate(int[] nums) {
        int max=0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>max)
            {
                max=nums[i];
            }
        }
        boolean visited[]=new boolean[max+1];
        int dup=-1;
        for(int i=nums.length-1;i>=0;i--)
        {
            if(visited[nums[i]]==true)
            dup=nums[i];

            else
            visited[nums[i]]=true;
        }
        return dup;
    }
}